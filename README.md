# The Unfinished Tome Web Application

This web application is intended to allow users to create and login to create a game account for ‘The Unfinished Tome’ LARP game, 
from which they can create a character to play the game, and spend XP to buy skills.

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

**Built With**
* [Maven](https://maven.apache.org/) - Dependency Management
**License**
See License.MD
**Versioning**
Current Version 1.0.0
**Author**
Dr James Monks – Project Developer (https://github.com/JMonks14)
**Acknowledgements**
Vinesh Ghela, Alan Davies and Shafeeq Muhammed have provided valuable assistance with various aspects of this project. 
