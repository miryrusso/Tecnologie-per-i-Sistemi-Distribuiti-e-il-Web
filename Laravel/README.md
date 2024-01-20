## LARAVEL scheme
  - Install Composer & Laravel
  - Create new project : ``` laravel new name_project ```
  - Set own database on file *.env*
  - Create Model with ``` php artisan make:model nome_tabella -mcr ```
  - */database/migrations* you find file named with the same name as your tables
    - Write table fields
    - Code to write foreign key ``` foreign("*field*")->references("*id*")->on("*table*")->onDelete("cascade"); ```
  - Migrate with ``` php artisan migrate ```
  - Create View
    - index: see a list of all your items within the database
    - create: add items
    - edit: motify one or more field
    - show: insert method 'delete'
  - Create Model
  - Create Routes to move between the various paths
  - Create Controllers
    - contains all functions with view names
    - store: save on database
#### It is recommended to look at the projects to better understand this scheme
