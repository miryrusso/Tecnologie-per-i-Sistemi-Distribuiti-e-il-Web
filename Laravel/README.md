## LARAVEL SCHEME
  - Install Composer & Laravel
  - Create new project : ``` laravel new name_project ```
  - Set own database on file *.env* 
  - Create Model & Table on database with ``` php artisan make:model nome_tabella -mcr ```
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
  - Start project ``` php artisan serve ```
    - If you have to change port ``` php artisan serve --port=8001 --host=0.0.0.0```
#### It is recommended to look at the projects to better understand this scheme

##### Other information
  - If you use an Ubuntu/Unix system and have installed composer and laravel on home you can find Laravel in this path: ``` .config/composer/vendor/bin/laravel ```
  - To start: ``` .config/composer/vendor/bin/laravel``` 
  - To update composer (change every year and your project may *not work* !!!) : ``` composer update ``` 
  - To use word "laravel": ``` export "PATH=~/.config/composer/vendor/bin:$PATH" ``` (every time the system reboots)
  - To try my project I suggest you to create a local database (for example use phpmyadmin)



