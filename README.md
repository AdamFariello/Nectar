# Nectar (On hiatus) 

## Introduction
--- 
### Project Description
Amazon assistant extension 
that lets the user save items to a list for:

- Price watching   
- Availability watch   
- Automatic purchases based on certain conditions   
- etc.   

Plus general features as well such as notifications

&nbsp;

### Where this repository comes from
#### Origin
This repository is the semester long group project for 
[cs431]( https://www.cs.rutgers.edu/academics/undergraduate/course-synopses/course-details/01-198-431-software-engineering ).    
The course was taken during the Spring 2023 semester.

#### Original repository link
https://github.com/DoubleAyyBatteries/Nectar/tree/main

#### Team members of the original repository (and contributions)
The members of the team can be located in: 

- The contrbution section of this repository, 
- the contrbution section of the original repository, or
- [release/Contributions.pdf]( https://github.com/AdamFariello/Nectar/blob/main/release/Contributions.pdf )

On the last option, as the file name suggests, 
it also contains each teammates contributions.

&nbsp;

### Disclamer
The project is **unfinished**.  

#### Extension functionality
The extension gui is unfinished, 
request sending plus result handling wasn’t implemented.    

If you are to follow the instructions in section, 
"Setup and Running the Program",
and run the chrome extension -- 
you will be met with a huge amount of debug/testing alert dialogues. 

#### Server functionality
The server is also unfinished, although functional by itself --
just like email sending, text message sending, request handling, 
sending messages to the extension, product tracking, 
and the amazon scraping script -- but Because the UserDao objects are incomplete, 
the server can't fully function as intended

Unit tests and integration tests were also made, and are functional -- 
but it doesn’t test everything.

&nbsp;

&nbsp;

&nbsp;

&nbsp;

&nbsp;

&nbsp;

&nbsp;

## Setup and Running the Program
---
The instructions below are based off the original repository's 
[instructions.pdf]( https://github.com/DoubleAyyBatteries/Nectar/blob/main/release/Instructions.pdf )

### Step 0: Downloading the Repository
#### Option A: Using the terminal
`
git clone https://github.com/AdamFariello/Nectar.git 
&&
cd Nectar.git
`

#### Option B: Using releases
Click on the most recent release.

&nbsp;

### Step 1: Seting up the (mysql) database
#### Download mysql
Click on one of the following operating systems to be taken to an 
installation guide for that OS.

- [Windows]( https://dev.mysql.com/doc/refman/8.0/en/windows-installation.html )
- [Mac]( https://dev.mysql.com/doc/refman/8.0/en/macos-installation.html  )
- [Linux]( https://dev.mysql.com/doc/refman/8.0/en/linux-installation.html )

For any other situation, or you need to upgrade/downgrade mysql, 
click [here]( https://dev.mysql.com/doc/refman/8.0/en/installing.html ),
and go through the table of content.

#### Starting the mysql service/daemon
Starting mysql differentiates between operating systems,
so I recommend following a tutorial, 
such as [this one]( https://phoenixnap.com/kb/start-mysql-server ).

*(
Mysql is a service/daemon; 
a process that will run everytime the pc boots after being installed
)*

#### Inializing Nectar's database
Run server/database/database.sql in a terminal, or an IDE that can run .sql files.  
For an IDE, I recommend 
[mysql workbench]( https://dev.mysql.com/downloads/workbench/ )

&nbsp;

### Step 2: Running the server
Run the file releases/Final.jar in a command prompt with a port number.
`java final.jar 8000`

*(
Remember that port number, it will be used in the next step
)*

&nbsp;

### Step 3: Setting up the chrome browser 
On chrome, go to your extensions page (`chrome://extensions/`).   
Then: toggle the developer mode button, and click load unpacked on the top left.   

&nbsp;

### Step 4: Installing the chrome extension
Navigate to the Nectar/client folder and click the gui folder.   
*(
This will load the extension in your browser. 
*)

A prompt will appear asking for your port number,    
put in the port number you used in step 2.    
*(
There should be an alert letting you know the connection has been established 
)*

Then, a dialogue will appear asking for: your username, and password.   
Enter whatever you want in both fields, click "login", and an alert will appear.   
*(
These alerts are remnants of testing the app, since of the project being unfinished
)*

&nbsp;

&nbsp;

### Questions
---
#### Why not fork the repository?
1. I want to keep the original submitted repository intact.
2. I want all the branches in the repository for development.

#### What is the functionality of the original repository?
The python scraper script, the database, 
and notifications via email and text -- are completed.   

If you're not interested in running the program,
you can look at screen shots of email and text messaging working.   
Look at page one in
[release/Contributions.pdf]( https://github.com/AdamFariello/Nectar/blob/main/release/Contributions.pdf ).   

 
#### Does this repository have the same level of functionality? 
As of right now of writing this answer -- 
(June 24, 2023) --
yes. 

#### How long will this repository be on hiatus?
For a week or few.   
Other repositories need attention first, 
plusother priorities need to be dealt with first.


