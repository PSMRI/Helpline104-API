# CLAUDE.md - Helpline104-API

## Project Overview

Helpline104-API is the backend service for India's 104 Health Helpline within the AMRIT platform. It provides medical advice, health information, grievance handling, disease surveillance, epidemic outbreak tracking, organ donation registration, blood request management, prescription handling, and comprehensive reporting for the helpline call centre.

## Tech Stack

- Java 17, Spring Boot 3.2.2, Maven
- Spring Data JPA / Hibernate, MySQL 8.0 (dual datasource: primary + secondary for CRM reports)
- Redis for session management
- Lombok
- SpringDoc OpenAPI (Swagger UI at `/swagger-ui.html`)
- ECS logging (logback-ecs-encoder)
- Apache POI (Excel export via `excelExporter/`)
- JaCoCo for test coverage
- Packaged as WAR for Wildfly deployment

## Build & Run

```bash
mvn clean install -DENV_VAR=local          # Build
mvn spring-boot:run -DENV_VAR=local        # Run locally
mvn -B package --file pom.xml -P <profile> # Package WAR (dev, local, test, ci, uat)
mvn test                                    # Run tests
```

Environment config: `src/main/resources/104_<ENV_VAR>.properties` is copied to `application.properties` at build time.

## Key Packages (`com.iemr.helpline104`)

- **controller/** - REST endpoints organized by domain:
  - `beneficiarycall/` - Beneficiary call management
  - `feedback/` - Feedback/grievance handling
  - `feedbackType/` - Feedback type configuration
  - `prescription/` - Medical prescription management
  - `disease/` - Disease information and lookup
  - `diseaseScreening/` - Disease screening questionnaires
  - `cdss/` - Clinical Decision Support System integration
  - `epidemicOutbreak/` - Epidemic outbreak surveillance and reporting
  - `bloodRequest/` - Blood request management
  - `bloodComponent/` - Blood component type management
  - `organDonation/` - Organ donation registration
  - `foodSafetyComplaint/` - Food safety complaint handling
  - `IMRMMR/` - Infant/Maternal Mortality Rate reporting (MDSR/CDR)
  - `balVivha/` - Child marriage (Bal Vivah) reporting
  - `scheme/` - Government scheme information
  - `directory/` - Directory service (hospitals, institutions)
  - `drugGroup/` - Drug group and mapping management
  - `callqamapping/` - Call QA mapping
  - `casesheet/` - Case sheet management
  - `snomedct/` - SNOMED CT terminology lookup
  - `covidMaster/` - COVID-19 master data
  - `hihl/` - Health Information Helpline
  - `secondaryCrmReports/` - Secondary CRM reporting
  - `sioHistory/` - Service Improvement Officer history
  - `location/` - Location and country/city lookup
  - `users/` - User/admin management
- **service/** - Business logic layer (mirrors controller structure)
- **repository/** - Spring Data JPA repositories (mirrors domain structure)
- **data/** - JPA entities organized by domain
- **secondary/** - Secondary datasource entities and repos for CRM reports
- **sms/** - SMS notification service
- **excelExporter/** - Excel report generation
- **utils/** - Utilities (Redis, HTTP, validation, session, gateway, email, exception handling)
- **config/** - Application configuration

## Architecture Notes

- Standard layered architecture: Controller -> Service -> Repository
- Dual datasource configuration: primary DB for operations, secondary DB for CRM reporting
- Auth tokens validated via HTTP interceptor calling Common-API
- CDSS integration provides clinical decision support during calls
- SNOMED CT integration for standardized medical terminology
- IMR/MMR (MDSR/CDR) module tracks maternal and infant deaths
- Excel export capability for various report types
- SMS notification integration
- Artifact ID: `Common`, group: `com.iemr.common`, final name: `104api-v3.0.0`
