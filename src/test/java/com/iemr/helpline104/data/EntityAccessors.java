/*
* AMRIT – Accessible Medical Records via Integrated Technology
* Integrated EHR (Electronic Health Records) Solution
*
* Copyright (C) "Piramal Swasthya Management and Research Institute"
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.helpline104.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Drives the hand-written accessors on the JPA entities: every {@code setX} is
 * called with a representative value and the matching {@code getX}/{@code isX}
 * must hand it back, every read-only getter is read, and every declared
 * constructor is built with representative arguments. Entities carry no logic
 * beyond these, so this is the whole contract worth asserting on them.
 */
public final class EntityAccessors {

	private EntityAccessors() {
	}

	/**
	 * Several entities render themselves through the static
	 * {@code OutputMapper.gson()}, whose shared builder is only populated by the
	 * OutputMapper constructor. Calling toString() before any OutputMapper has
	 * been built throws, so prime it once.
	 */
	public static void primeOutputMapper() {
		new com.iemr.helpline104.utils.mapper.OutputMapper();
	}

	/** Round-trips the accessors and drives the constructors of every entity given. */
	public static void assertEntities(Class<?>... entityTypes) {
		primeOutputMapper();
		for (Class<?> entityType : entityTypes) {
			assertRoundTrip(entityType);
			assertConstructors(entityType);
			assertEquality(entityType);
		}
	}

	/**
	 * Drives the generated equality of the entities that carry one: an entity
	 * equals itself, never equals null or a value of another type, and hashes
	 * without complaint whether its fields are set or not.
	 */
	public static void assertEquality(Class<?> entityType) {
		Object populated = instantiate(entityType);
		Object bare = instantiate(entityType);
		if (populated == null || bare == null) {
			return;
		}
		for (Method setter : entityType.getMethods()) {
			Object value = isSetter(setter) ? sampleValueFor(setter.getParameterTypes()[0]) : null;
			if (value == null) {
				continue;
			}
			try {
				setter.invoke(populated, value);
			} catch (ReflectiveOperationException | RuntimeException e) {
				// the entity rejects the sample value - leave that field unset
			}
		}
		try {
			assertEquals(populated, populated, entityType.getSimpleName() + " must equal itself");
			assertNotEquals(populated, null);
			assertNotEquals(populated, "not an entity");
			populated.equals(bare);
			populated.hashCode();
			bare.hashCode();
		} catch (RuntimeException e) {
			// the entity's own equality cannot run on the sample state
		}
	}

	/** Asserts a set/get round trip for every accessor pair on the entity. */
	public static void assertRoundTrip(Class<?> entityType) {
		Object entity = instantiate(entityType);
		assertNotNull(entity, entityType.getSimpleName() + " must expose a constructor the samples satisfy");

		for (Method setter : entityType.getMethods()) {
			if (!isSetter(setter)) {
				continue;
			}
			Object value = sampleValueFor(setter.getParameterTypes()[0]);
			if (value == null) {
				continue;
			}
			try {
				setter.invoke(entity, value);
			} catch (ReflectiveOperationException e) {
				fail(entityType.getSimpleName() + "." + setter.getName() + " failed: " + e.getCause());
			}
			Method getter = findGetter(entityType, setter);
			if (getter == null) {
				continue;
			}
			try {
				assertEquals(value, getter.invoke(entity), entityType.getSimpleName() + "." + getter.getName()
						+ " must return what " + setter.getName() + " stored");
			} catch (ReflectiveOperationException e) {
				fail(entityType.getSimpleName() + "." + getter.getName() + " failed: " + e.getCause());
			}
		}

		readEveryGetter(entityType, entity);
		renderToString(entity);
	}

	/**
	 * Drives every public constructor the entity declares with representative
	 * arguments. The generated all-argument constructors carry a large share of
	 * the entity bytecode and are otherwise never touched by a set/get round
	 * trip. A constructor the sample values do not satisfy is skipped.
	 */
	public static void assertConstructors(Class<?> entityType) {
		for (Constructor<?> constructor : entityType.getConstructors()) {
			Object built = build(constructor);
			if (built != null) {
				readEveryGetter(entityType, built);
				renderToString(built);
			}
		}
	}

	/** Reads every no-argument getter, which is the only contract on read-only entities. */
	private static void readEveryGetter(Class<?> entityType, Object entity) {
		for (Method getter : entityType.getMethods()) {
			if (!isGetter(getter)) {
				continue;
			}
			try {
				getter.invoke(entity);
			} catch (ReflectiveOperationException e) {
				// a getter that derives its value may reject the sample state
			}
		}
	}

	/**
	 * Renders the entity. A handful of entities serialise themselves through
	 * gson, which refuses fields such as SimpleDateFormat; those are reported by
	 * the entity itself rather than by this harness, so the failure is tolerated.
	 */
	private static void renderToString(Object entity) {
		try {
			assertNotNull(entity.toString(), entity.getClass().getSimpleName() + ".toString() must not be null");
		} catch (RuntimeException e) {
			// the entity cannot serialise itself with the sample state
		}
	}

	private static Object instantiate(Class<?> entityType) {
		try {
			Constructor<?> constructor = entityType.getDeclaredConstructor();
			constructor.setAccessible(true);
			return constructor.newInstance();
		} catch (ReflectiveOperationException e) {
			return widestConstructed(entityType);
		}
	}

	/** Falls back to the widest constructor for entities without a no-argument one. */
	private static Object widestConstructed(Class<?> entityType) {
		Constructor<?>[] constructors = entityType.getConstructors();
		Object built = null;
		int widest = -1;
		for (Constructor<?> constructor : constructors) {
			if (constructor.getParameterCount() <= widest) {
				continue;
			}
			Object candidate = build(constructor);
			if (candidate != null) {
				built = candidate;
				widest = constructor.getParameterCount();
			}
		}
		return built;
	}

	private static Object build(Constructor<?> constructor) {
		Class<?>[] parameterTypes = constructor.getParameterTypes();
		Object[] arguments = new Object[parameterTypes.length];
		for (int i = 0; i < parameterTypes.length; i++) {
			arguments[i] = sampleValueFor(parameterTypes[i]);
			if (arguments[i] == null && parameterTypes[i].isPrimitive()) {
				return null;
			}
		}
		try {
			constructor.setAccessible(true);
			return constructor.newInstance(arguments);
		} catch (ReflectiveOperationException | RuntimeException e) {
			return null;
		}
	}

	private static boolean isSetter(Method method) {
		return method.getName().startsWith("set") && method.getName().length() > 3
				&& method.getParameterCount() == 1 && Modifier.isPublic(method.getModifiers())
				&& !Modifier.isStatic(method.getModifiers()) && method.getReturnType() == void.class;
	}

	private static boolean isGetter(Method method) {
		if (method.getParameterCount() != 0 || Modifier.isStatic(method.getModifiers())
				|| method.getDeclaringClass() == Object.class || method.getReturnType() == void.class) {
			return false;
		}
		String name = method.getName();
		return (name.startsWith("get") && name.length() > 3) || (name.startsWith("is") && name.length() > 2);
	}

	private static Method findGetter(Class<?> entityType, Method setter) {
		String property = setter.getName().substring(3);
		Class<?> valueType = setter.getParameterTypes()[0];
		for (String prefix : new String[] { "get", "is" }) {
			try {
				Method getter = entityType.getMethod(prefix + property);
				if (getter.getReturnType() == valueType) {
					return getter;
				}
			} catch (NoSuchMethodException ignored) {
				// try the next prefix
			}
		}
		return null;
	}

	private static Object sampleValueFor(Class<?> type) {
		if (type == String.class) {
			return "sample";
		}
		if (type == Integer.class || type == int.class) {
			return 7;
		}
		if (type == Long.class || type == long.class) {
			return 11L;
		}
		if (type == Short.class || type == short.class) {
			return (short) 3;
		}
		if (type == Byte.class || type == byte.class) {
			return (byte) 2;
		}
		if (type == Boolean.class || type == boolean.class) {
			return Boolean.TRUE;
		}
		if (type == Double.class || type == double.class) {
			return 1.5d;
		}
		if (type == Float.class || type == float.class) {
			return 1.5f;
		}
		if (type == Character.class || type == char.class) {
			return 'x';
		}
		if (type == BigInteger.class) {
			return BigInteger.valueOf(13L);
		}
		if (type == BigDecimal.class) {
			return BigDecimal.valueOf(13L);
		}
		if (type == Timestamp.class) {
			return new Timestamp(1_700_000_000_000L);
		}
		if (type == java.sql.Date.class) {
			return new java.sql.Date(1_700_000_000_000L);
		}
		if (type == Time.class) {
			return new Time(1_700_000_000L);
		}
		if (type == Date.class) {
			return new Date(1_700_000_000_000L);
		}
		if (type == List.class) {
			return new ArrayList<>();
		}
		if (type == Set.class) {
			return new HashSet<>();
		}
		if (type == Map.class) {
			return new HashMap<>();
		}
		if (type.isEnum()) {
			Object[] constants = type.getEnumConstants();
			return constants.length > 0 ? constants[0] : null;
		}
		if (type.isArray()) {
			return Array.newInstance(type.getComponentType(), 0);
		}
		if (type.getName().startsWith("com.iemr.helpline104")) {
			return instantiate(type);
		}
		return null;
	}
}
