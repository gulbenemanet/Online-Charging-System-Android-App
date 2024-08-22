# OCS Mobile Application

This repository contains the mobile application part of the "Online Charging System (OCS)" project, developed as part of an internship at "i2i Systems". The OCS is a system that allows telecommunication service providers to charge their customers in real-time based on their service usage, including voice, data, and SMS.

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [API Endpoints](#api-endpoints)

## Project Overview

The OCS Mobile Application allows users to interact with the Online Charging System via an Android app. Users can register, select service packages, view their usage data, and manage their account in real-time. The app integrates with the backend system to ensure accurate and up-to-date information.

## Features

- **User Registration:** Users can sign up and create an account.
- **Package Selection:** Users can select data, SMS, and voice packages based on their preferences.
- **Real-Time Usage Tracking:** View remaining data, SMS, and voice usage in real-time.
- **Account Management:** Manage account settings, including password recovery.
- **Notifications:** Receive notifications about low balance or package expiration.

## Technologies Used

- **Android Studio:** IDE for Android app development.
- **Java:** Programming language used for app development.
- **Volley:** Library used for making network requests.

## Setup Instructions

1. **Clone the repository:**
    ```bash
    git clone https://github.com/yourusername/ocs-mobile-app.git
    ```

2. **Open the project in Android Studio:**
    - Launch Android Studio.
    - Click on `Open an existing project` and navigate to the cloned repository.

3. **Configure the project:**
    - Ensure you have the latest version of Android SDK installed.
    - Set up the required API keys if any.

4. **Run the application:**
    - Connect an Android device or start an emulator.
    - Click `Run` to build and deploy the app.

## API Endpoints

The mobile application interacts with several backend API endpoints:

- User Registration: `v1/api/auth/register`
- Get Available Packages: `/v1/api/packages/getAllPackages`
- Password Recovery: `/v1/api/forgetPassword/reset`

Refer to the API documentation for more details on request parameters and responses.
