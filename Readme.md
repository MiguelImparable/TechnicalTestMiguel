# **Note:** The resolution of the test at the document level is contained within the `Documents` folder.

# Installation Guide

## Steps for Execution

1. **Install Java SDK Version 23.0.1**  
   Ensure the Java SDK version 23.0.1 is installed on the server. This version is required for proper compatibility with the project dependencies.

2. **Install Gradle Version 8.11.1**  
   Download and install Gradle version 8.11.1 on the server. Gradle is the build tool required to compile and run the project.

3. **Run the Application**  
   Open PowerShell, navigate to the project's root folder, and execute the following command:
   ```bash
   .\gradlew.bat bootRun


# Project Structure

## Definitions, Acronyms, and Abbreviations

### Singleton
A design pattern that ensures a class has only one instance and provides a global point of access to that instance.

### Abstract Factory
A design pattern that allows the creation of families of related or dependent objects without specifying their concrete classes.

### Factory Method
A design pattern that defines an interface for creating an object but lets subclasses decide which class to instantiate.

### Observer Method
A design pattern where an object, called "subject," maintains a list of its dependents, called "observers," and automatically notifies them of any state changes.

### Interface
A contract in programming that defines methods to be implemented by inheriting classes without providing specific implementations.

### Abstraction
An object-oriented programming principle that hides implementation details and shows only the essential functionality of an object.

### DTO (Data Transfer Object)
An object used to transfer data between processes, reducing the number of calls and improving communication performance between application layers.

### UoW (Unit of Work)
A pattern that groups several related database operations to ensure all or none complete, maintaining data consistency.

### DI (Dependency Injection)
A technique for managing the creation and resolution of dependencies between objects using an IoC (Inversion of Control) container.

### API (Application Programming Interface)
A set of protocols and tools for building and consuming software services, enabling interaction between applications or components.

### Facade
A structural design pattern that provides a simplified interface to a set of interfaces within a subsystem, making it easier to use.

### Repository
A pattern that provides an abstraction layer between the application and the database, managing and accessing data using object collections.

### DTO Mapping
A technique that converts domain entities to DTOs and vice versa, facilitating data transfer between application layers without exposing internal logic.

### ORM (Object-Relational Mapping)
A technique that maps relational database structures to code objects, enabling data manipulation without directly using SQL.

### Microservices
An architecture where an application is developed as a collection of small, independent services that communicate with each other through APIs.

### REST (Representational State Transfer)
An architectural style for designing web services that interact with resources identified by URLs, using methods like GET, POST, PUT, and DELETE.

### Dependency Inversion Principle (DIP)
A design principle stating that high-level modules should not depend on low-level modules; both should depend on abstractions.

### SOLID
A set of five object-oriented design principles:
- **Single Responsibility**
- **Open/Closed**
- **Liskov Substitution**
- **Interface Segregation**
- **Dependency Inversion**

---

## API Description

This API is designed to leverage best practices in modern software development. The design decisions are based on the following principles and patterns:

### Scalability and Modularity with Microservices
The API is structured as a collection of microservices, allowing for independent development, testing, and deployment of each service.

### Abstraction and Decoupling
Using patterns like Abstract Factory, Facade, and Repository, the API separates concerns, enabling flexible and maintainable code.

### Simplified Communication with DTOs
Data Transfer Objects are used to transfer data efficiently between layers while maintaining encapsulation of domain logic.

### Extensibility with SOLID Principles
By adhering to the SOLID principles, the API ensures that new features can be added without impacting existing functionality.

### Consistency and Robustness with UoW and DI
The Unit of Work pattern ensures transactional consistency, while Dependency Injection simplifies dependency management and improves testability.

### Real-Time Notifications with Observer
The Observer pattern is employed for scenarios requiring real-time updates, such as notifying subscribers of state changes.

# [ServiceExtension]

---

## [APP]

### Host
- Maneja el entorno de alojamiento de la aplicación, incluyendo configuración y controladores API.
- **Subcarpetas:**
  - **Properties**: Contiene archivos de configuración como `appsettings.json` o `web.config`.
  - **Controllers**: Gestiona los puntos finales API y las solicitudes entrantes.
  - **Extensions**: Proporciona métodos de extensión para mejorar la funcionalidad.
  - **Swagger**: Configura Swagger para la documentación automática de la API.
  - **Validators**: Valida las solicitudes entrantes a nivel de controlador.

---

## [MODULE]

### Module
- Gestiona la lógica de negocio específica, flujos de trabajo, servicios y fábricas dentro de un módulo.
- **Subcarpetas:**
  - **Business**: Contiene la lógica de negocio, fábricas y flujos de trabajo dentro del módulo.
    - **Factories**: Gestiona la creación de objetos y la lógica de instanciación.
    - **Logic**: Contiene la lógica de negocio específica del módulo.
    - **WorkFlow**: Gestiona flujos de trabajo de negocio complejos.
      - **Abstractions**: Proporciona interfaces para flujos de trabajo para separar la implementación de la lógica.
  - **Services**: Gestiona servicios y fachadas dentro del módulo.
    - **Abstractions**: Define interfaces de servicio para asegurar implementaciones intercambiables.
    - **Facades**: Proporciona una interfaz simplificada para operaciones complejas.

---

## Dal

### Dal
- Maneja el acceso a los datos y las operaciones de la base de datos.
- **Subcarpetas:**
  - **Business**: Contiene entidades relacionadas con la lógica de negocio.
    - **Entities**: Representa el contexto de las entidades.
  - **Dal**: Interactúa directamente con la base de datos para acceder a los datos.
    - **Repositories**: Realiza operaciones CRUD y maneja transacciones de base de datos.
    - **UnitOfWork**: Gestiona transacciones, asegurando que múltiples operaciones de repositorio se realicen dentro de una sola transacción.

---

## Entities

### Entities
- Define las entidades de negocio, objetos de transferencia de datos, enumeraciones y lógica de validación.
- **Subcarpetas:**
  - **Business**: Contiene las entidades y la lógica relacionada con los procesos de negocio.
    - **Dto**: Transfiere datos entre capas, especialmente entre la lógica de negocio y la presentación.
    - **Entities**: Representa los modelos de datos relacionados con la lógica de negocio.
    - **Enums**: Define valores constantes que categorizan datos.
    - **Validators**: Asegura que las entidades cumplan con las reglas de negocio antes de ser procesadas.
  - **Generic**: Proporciona servicios comunes y configuraciones utilizadas en toda la aplicación.
    - **Services**: Gestiona servicios que proporcionan configuración y soporte de idiomas.
      - **Configuration**: Gestiona la configuración de toda la aplicación y de módulos específicos.
      - **Languages**: Maneja la localización e internacionalización, permitiendo el soporte multilingüe.
