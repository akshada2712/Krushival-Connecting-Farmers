# Krushival - Farmer's E-Commerce Android Application

A mobile application connecting farmers directly with customers, eliminating middlemen and ensuring fair trade. Built during the COVID-19 pandemic to digitize agricultural commerce.


## 🌾 Overview

Krushival is an Android-based e-commerce platform specifically designed for farmers to sell their products directly to consumers. The application aims to:
- Eliminate middlemen in agricultural sales
- Provide farmers with direct market access
- Enable digital transformation in agriculture
- Ensure fair pricing for both farmers and consumers

## ✨ Features

- **User Authentication**
  - Firebase-based secure authentication
  - Separate signup/signin for farmers and customers
  - Password recovery functionality

- **Product Management**
  - Category-wise product listing
  - Product addition interface for farmers
  - Detailed product descriptions and images

- **Shopping Experience**
  - Cart functionality
  - Multiple address management
  - Order tracking
  - Integrated payment gateway (dummy, Razorpay-ready)

## 🛠️ Tech Stack

- Android (Java)
- Firebase
  - Authentication
  - Realtime Database
  - Cloud Storage
- XML for layouts
- Gradle build system

## ⚙️ Setup & Installation

1. Clone the repository:
```bash
git clone https://github.com/akshada2712/Krushival.git
```

2. Open in Android Studio

3. Configure Firebase:
   - Create a new Firebase project
   - Add your `google-services.json` to the app directory
   - Enable Authentication and Realtime Database

4. Build and run:
```bash
./gradlew build
```

## 🔧 Requirements

- Android Studio 
- JDK 8 or higher
- Android SDK
- Firebase account
- Minimum Android API 21+

## 📝 Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/krushival/
│   │   │       ├── activities/
│   │   │       ├── adapters/
│   │   │       ├── models/
│   │   │       └── utils/
│   │   └── res/
│   └── androidTest/
├── build.gradle
└── google-services.json
```
