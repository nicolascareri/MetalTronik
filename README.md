# Metaltronik

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 9.1.0.
and [Spring Boot](https://spring.io/projects/spring-boot) version 2.2.6.RELEASE.

## Development server

+ git clone https://github.com/nicolascareri/MetalTronik.git
+ cd metaltronik/backend
+ mvn spring-boot:run
+ cd ../frontend
+ Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding (frontend package)

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build (frontend package)

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Git Development Process

1. Ubicarse en rama master y se ejecuta git pull origin master para traer la ultima versión del proyecto.
2. Crear un branch LOCALMENTE (git checkout -b {rama}), transladarse a dicha rama local y empiezar a implementar.
3. Luego de finalizar el desarollo hacer un COMMIT
4. Una vez commiteado siempre en su rama local se ejecutar GIT PULL ORIGIN MASTER para agregar a lo que se hizo nuevas modificaicones que le hayan hecho al master.
5. Una vez hecho todo esto se ejecuta GIT PUSH ORIGIN {nombre_rama_local} (Esto automaticamente crea una rama en el github con el nombre que le indicaron)
6. Dirigirse al [repositorio remoto](https://github.com/nicolascareri/MetalTronik) y abren un nuevo MERGE REQUEST


