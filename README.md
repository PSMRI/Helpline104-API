# AMRIT - Helpline104 Service

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0) ![branch parameter](https://github.com/PSMRI/Helpline104-API/actions/workflows/sast-and-package.yml/badge.svg)

The AMRIT Helpline104 service aims to provide comprehensive assistance to individuals in need. It offers a range of services such as medical advice, counseling, grievance resolution, and support during health emergencies. The helpline is designed to cater to the specific needs of callers, ensuring they receive the appropriate assistance.

## Features

- **Medical Advice**: Callers can receive medical advice from trained professionals through the helpline. The service provides guidance and recommendations based on the caller's health concerns.

- **Counseling**: The helpline offers counseling services to individuals who require emotional support. Trained professionals are available to provide guidance and help callers cope with various challenges.

- **Grievance Resolution**: Callers can raise general grievances, which are then tracked using a unique Grievance ID. This ensures that concerns are properly addressed and resolved in a timely manner.

- **Directory Service**: The helpline provides a directory service to help callers locate relevant healthcare facilities, doctors, and other medical resources. This feature assists individuals in finding the appropriate assistance they need.

- **Epidemic Outbreak Information**: During epidemic outbreaks, the helpline offers information and guidance to callers. This helps individuals stay informed about the outbreak, preventive measures, and available healthcare services.

- **Clinical Decision Support System (CDSS)**: The helpline incorporates a basic CDSS to provide clinical support for selected chief complaints. This system aids in providing accurate and reliable clinical guidance to callers.

- **Medicine Prescription and Delivery**: Medical officers (MO) within the helpline have the authority to prescribe medicines to callers. Prescriptions are delivered to beneficiaries through SMS, ensuring they have access to the necessary medication.

- **Psychiatric Consultation**: The helpline offers psychiatric consultations through a dedicated role known as the Program Director (PD). This allows individuals to receive mental health support and guidance when needed.

- **Maternal and Child Death Review**: The helpline has systems in place for Maternal Death Surveillance and Response (MDSR) and child death review (CDR). These systems enable the tracking and monitoring of maternal and child deaths, ensuring appropriate actions are taken.

## Building From Source

To build the Helpline104 microservice from source, follow these steps:

### Prerequisites

- JDK 1.8
- Maven
- Redis
- Spring Boot V2
- MySQL

### Installation

1. Clone the repository to your local machine.
2. Install the dependencies and build the module:
   - Run `mvn clean install` in the project directory.
3. Run the development server:
   - Start the Redis server.
   - Run `mvn spring-boot:run` in the project directory.
   - Open your browser and go to `http://localhost:8080/swagger-ui.html#!/` to access the Swagger API documentation.

## Usage

All the features of the Helpline104 service are exposed as REST endpoints. Refer to the Swagger API documentation for detailed information on how to use the service and interact with its functionalities.

The AMRIT Helpline104 module provides a comprehensive solution for managing various aspects of your application.
