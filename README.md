# Digital Kitchen v0.5
Cookbook database and meal planner!

## Table of Contents
- [Digital Kitchen v0.5](#digital-kitchen-v05)
  - [Table of Contents](#table-of-contents)
  - [What's new!](#whats-new)
  - [Project Description](#project-description)
  - [Configuration](#configuration)
    - [application.properties](#applicationproperties)
  - [Usage](#usage)
    - [Create](#create)
    - [Search/Browse](#searchbrowse)
  - [Planned Functionality](#planned-functionality)
    - [Edit](#edit)
    - [Meal planning](#meal-planning)
    - [Misc.](#misc)

## What's new!
* ### Searching and Browsing
  Find any recipe by any of its fields with the new search function!
  
## Project Description
This project was born from both an interest in learning new technologies (specifically the spring boot framework and learning tech stacks in general) as well as creating a management system for my partner and I's vast amount of recipes. This project connects with a MYSQL database used to hold information pertaining to recipes and allows the user to interact with them.

## Configuration
### application.properties
```digitalkitchen\backend\src\main\resources\```
``` 
spring.datasource.url= <url>
spring.datasource.username= <username>
spring.datasource.password= <password>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
server.port=8080
server.servlet.context-path=/digitalkitchen

spring.mvc.allowed-origins=http://localhost:3000
spring.mvc.allowed-methods=GET, POST, PUT, DELETE
spring.mvc.allowed-headers=Content-Type 
```

## Usage
### Create
After pressing create you will be brought to the recipe creation form. Here there are 4 different forms all navigated with the next and previous buttons underneath them.The forms are the recipe general information, ingredients, steps, and tags. For submission, the name, author, and category fields on the general information are required as well as at least 1 step and 1 ingredient. Above the submission button is a checkbox to signify there are more recipes to be input. If this is selected on submission, the form will be restarted, if not the user will be displayed the recipe they just input. If the recipe is a duplicate, a pop up will appear prompting the user to view the recipe or close the popup.

### Search/Browse
After pressing search you will be presented the search bar. This search bar is capable of searching the entire database for recipes with a filter to query any possible detail. Type in a keyword or the name of the recipes along with any other details into the filter and be presented with all of your options

After the search returns, a list of recipes will be presented. Clicking a recipe will bring it into display view showing you all the data relevant to this recipe. Clicking the arrow buttons will take you through the list while remaining in display view. The edit button is currently without functionality, to be added soon. 

## Planned Functionality
upcoming updates:
### Edit
* added to search/browse functionality
### Meal planning
* meal planner functionality
  * new meal objects that contain various recipes to create 1 meal
* grocery functionality
  * grocery list made of all the planned meals
* and more...
### Misc.
* full css styling
* bug fixes
* and more...
