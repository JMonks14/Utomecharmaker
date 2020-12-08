# The Unfinished Tome Web Application

This web application is intended to allow users to create and login to create a game account for ‘The Unfinished Tome’ LARP game, 
from which they can create a character to play the game, and spend XP to buy skills.

## For All Users

### Account Registration

To begin using the features of the application while browsing the website use the navbar at the top of the page to navigate to ‘Account’ 
and you will be directed to the Register/Login page. 
Using the form on the right-hand side of the page you will be able to create a new game account. 
If you have already created a game account you may login to you account by entering your username and password in the form on the left.

### Account home page

This page will load immediately after logging in. 
From here you can see your account details on the -hand side of the page, and use the buttons there to edit them as you wish. 
The right-hand side will display the name of your current character as well as a button linking to a page to view more details of them, 
or a link to create a character if you do no currently have one.
 
You may also click “logout” and you will be logged out of your account and returned to the website home page.

### Create Character page

On this page you can enter a name for your character, choose a race and region of origin from the dropdown menus,
 and enter a background for your character in the box provided.

### Character home page

On this page you will see your character’s name, statistics, skills and spells displayed, as well as how much XP you have available to spend. 
If you have at least 1 XP available you can buy a skill buy clicking “buy a skill” button, 
if you wish to reset your character’s abilities you can do so by clicking the “reset skills” button. 
This will delete all of your current skills and spells and reset your character statistics to their default values.  
If you wish to retire your character or report their death you may do so by clicking the relevant button. 
This will set the character’s status to dead and they will no longer be considered to be your active character. 
You will then be redirected to the account home page, and you may create a new character if you wish to do so.

### Buy skill page

You will be redirected to this page if you click the “buy skill” button on the character home page. 
You can choose which tree you would like to buy a skill from by using the dropdown menu near the top of the page.  
This will load an image of the selected tree as well as a second dropdown which will allow you to select any skill from that tree for 
which your character has any prerequisites. A description of the skill you select will be displayed, 
along with a button which will allow you to confirm the choice. Alternatively you may use the dropdown menus to choose a different skill. 
The exception is if you choose the “cantrips” skill in the magic tree. This will load another dropdown menu, 
from which you may choose a spell you wish to buy. You may then proceed as if you had chosen any other skill.

### Note on Screen size

Where webpages are formatted to display different information on either side of the screen on medium to large displays, 
the content intended to appear on the right-hand side will be appear below the left-hand side content on smaller displays.

### Technologies Used

The backend application was written in java using springboot framework. The frontend is written in html/javascript using visual code studio.
 Mockito and junit 4 were used for backend API testing. Selenium and Junit 4 were used for front-end web-application testing. 
The database is hosted on a GCP web instance of mySQL.

## For Admins and Developers

### The Admin Role

This application has 2 user roles: 'Users' and 'Admins'. Admins have access to all of the features available to regular users, as well as the admin console, 
which allows adjusting of the current maximum xp, querying the information on players and characters in the database and the capability to grant or remove admin 
status.

### Project Structure

All application files are located in the TomeBackendApp-1 folder.
This is a Spring Boot application, built with java files organised into packages according to their function: controllers, services, repositories, entities, exceptions and security,
located in the src/main folder. Webpages are displayed using the thymeleaf dependency, mapped to by APIs located in controllers.PageController.class. 
HTML pages are located in src/main/resources/templates. Webpage resources, including: css, image and javascript files are located in src/main/resources/static.

### How the application works

The web-app consists of 3 main parts: the front-end, back-end and database. The backend java code handles requests sent from browsers by interacting with the front-end. Requests may return HTML
pages, retrieve information from the database or edit the information in the database. Most requests sent by interacting with front-end features are sent using javascript fetch functions. These 
requests work by interacting with REST APIs coded in the various controller classes.

### Editing the application

All java code writing, editing and file structure updates during primary devlopment was done using the [Spring Tool Suite](https://spring.io/tools). This is recommended 
This is the recommended platform for all further high-level application editing and development. Other editors such as Visual Studio Code may be easier for writing and editing 
HTML and javascript files, but any files added or edited should be checked in spring tool suite to ensure that changes are recognised when running the application for testing purposes.

### The Database

The application is designed to connect to a MySQL database. This can be hosted on a the local machine or a cloud server. The connection settings can be set in application.properties.
A database starter file can be found in this repository. This can be imported into an instance of MySQL, which will create a database called 'utome', which contains
fully populated skills and spells tables. The skill and spell tables each contain a 'null'entry with id=0 for foreign key constraint purposes. The skill and spell id values are vital to 
determine correct prerequisites for skills. Additionally, many Id values are hard-coded into buyskill.js for the purpose of updating character stats. Any design changes to skills and
spells will need to account for this, and may require editing of the database and/or javascript code to ensure that the application presents up-to-date options.

### Security

The application is secured with spring security. This means that all requests to site pages which require users to be logged in to view will automatically redirect to the login page. 
CSRF tokens are used to protect against CSRF atacks on all PUT, POST and DELETE requests. The admin console can only be viewed by users with the 'Admin' role. As the only way to use 
the application to give a user the 'Admin' role normally is using the admin console, an alternative procedure will needed to introduce the first site admin to an initially empty database. 

The recommended prodecure for adding the first Admin to a fresh database is as follows:
1. Run a development version of the application, and use it to set up a player account.

2. Disable crsf protection is the app - devlopment version in security.SecurityConfiguration.

3. Use Postman (or alternative HTTP request manager of your choice) to grant the 'Admin' role to the newly created account.

4. Re-enable csrf protection.

Alternatively the database can be directly edited, but this is not recommended.

#### Built With

[Maven](https://maven.apache.org/) - Dependency Management

#### License
See License.MD

#### Versioning
Current Version 0.0.1

#### Author
Dr James Monks – Project Developer (https://github.com/JMonks14)

#### Acknowledgements
Vinesh Ghela, Alan Davies and Shafeeq Muhammed have provided valuable assistance with various aspects of this project. 
