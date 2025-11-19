# Appointment Scheduler

A modular appointment management system designed for scheduling resources such as doctors, rooms, and service domains.

## Project Overview

This project implements a complete scheduling platform that includes:

* Doctor management and specialization mapping
* Doctor availability by dates and hours
* Room management with domain-based compatibility
* Customer management
* Appointment creation and management
* Availability checks and conflict detection
* Activity logging for system actions

## Technologies

* **Backend:** Spring Boot (Java)
* **ORM:** JPA / Hibernate
* **Database:** PostgreSQL
* **API:** RESTful
* **Build Tool:** Maven / Gradle
* **Frontend (optional):** React or Angular

## Core Entities

### Doctor

Represents system doctors and their specializations.

### DoctorAvailability 

Represents doctor availability by date and time.

### Customer

Represents clients in the system.

### User

System users responsible for accessing and managing data.

### Specialization

Represents service domains.

### Room

Represents available rooms.

### RoomSpecialization

Defines allowed specializations per room.

### Appointment

Represents scheduled appointments including doctor, room, specialization, time range, and customer.

### ActivityLog

Tracks system user actions for auditing.

## Core Features

* Create, update, and cancel appointments
* Assign rooms according to domain compatibility
* Prevent scheduling conflicts
* Validate doctor availability based on work hours
* Track user actions with an audit log
* Expose a full REST API for system integration

## Architecture Layers

* **Entity Layer** – Data models and structure
* **Repository Layer** – Data access logic
* **Service Layer** – Business logic and validations
* **Controller Layer** – REST API endpoints

## Development Process

1. Database schema and entity planning
2. Model and entity implementation
3. Repository layer creation
4. Business logic implementation in service layer
5. Controller creation for API exposure
6. Availability validation and room compatibility rules
7. Activity log implementation
