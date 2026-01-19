# AMRIT - Helpline104 Service

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0) ![branch parameter](https://github.com/PSMRI/Helpline104-API/actions/workflows/sast.yml/badge.svg)
[![DeepWiki](https://img.shields.io/badge/DeepWiki-PSMRI%2FHelpline104--API-blue)](https://deepwiki.com/PSMRI/Helpline104-API)


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

## Environment and Setup
For setting up the development environment, please refer to the [Developer Guide](https://piramal-swasthya.gitbook.io/amrit/developer-guide/development-environment-setup) .

## Setting Up Commit Hooks
This project uses Git hooks to enforce consistent code quality and commit message standards. Even though this is a Java project, the hooks are powered by Node.js. Follow these steps to set up the hooks locally:
### Prerequisites
- Node.js (v14 or later)
- npm (comes with Node.js)
### Setup Steps

1. **Install Node.js and npm**

   - Download and install from [nodejs.org](https://nodejs.org/)
   - Verify installation with:
     ```
     node --version
     npm --version
     ```
 
2. **Install dependencies**
   - From the project root directory, run:
     ```
     npm ci
     ```
   - This will install all required dependencies including Husky and commitlint

3. **Verify hooks installation**
   - The hooks should be automatically installed by Husky
   - You can verify by checking if the `.husky` directory contains executable hooks
### Commit Message Convention
 
This project follows a specific commit message format:
- Format: `type(scope): subject`
- Example: `feat(login): add remember me functionality`

Types include:
- `feat`: A new feature
- `fix`: A bug fix
- `docs`: Documentation changes
- `style`: Code style changes (formatting, etc.)
- `refactor`: Code changes that neither fix bugs nor add features
- `perf`: Performance improvements
- `test`: Adding or fixing tests
- `build`: Changes to build process or tools
- `ci`: Changes to CI configuration
- `chore`: Other changes (e.g., maintenance tasks, dependencies)

Your commit messages will be automatically validated when you commit, ensuring project consistency.

## API Guide
Detailed information on API endpoints can be found in the [API Guide](https://piramal-swasthya.gitbook.io/amrit/architecture/api-guide).

## Usage

All the features of the Helpline104 service are exposed as REST endpoints. Refer to the Swagger API documentation for detailed information on how to use the service and interact with its functionalities.

The AMRIT Helpline104 module provides a comprehensive solution for managing various aspects of your application.

## Filing Issues

If you encounter any issues, bugs, or have feature requests, please file them in the [main AMRIT repository](https://github.com/PSMRI/AMRIT/issues). Centralizing all feedback helps us streamline improvements and address concerns efficiently.  

## Join Our Community

We’d love to have you join our community discussions and get real-time support!  
Join our [Discord server](https://discord.gg/FVQWsf5ENS) to connect with contributors, ask questions, and stay updated.  
