# PANTRY App

Environment set up steps
## Installation

1. Copy the link for this repository.
2. Open Git Bash in newly created directory for this project.
3. Enter the folowing commands:

```bash
git init .
git remote add origin https://github.com/pashakoz/Pantry.git
git pull origin master
```

## How to run web application?
1. Open this folder as Intellij IDEA project.
2. Navigate to <YOUR_ROOT_DIRECTORY>\pantry-web\src\main\resources
3. Change rows below to your DB url and credentials. 
   Also you can specify particular database after port.
```
spring.datasource.url=jdbc:postgresql://localhost:5432/
spring.datasource.username=postgres
spring.datasource.password=12345
```

## Pay Attention
Some information will be added here...
