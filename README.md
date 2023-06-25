# Nectar (On hiatus) 

## Table of Contents
---
- [Introduction](#Introduction)
- [Setup](#Setup)
- [Running the extension](#Running-the-extension)
- [Contributions](#Contributions)
- [Questions](#Questions)

&nbsp;

&nbsp;

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

## Setup
---
The instructions below are based off 
[instructions.pdf]( https://github.com/DoubleAyyBatteries/Nectar/blob/main/release/Instructions.pdf )
from the original repository 

### Step 0: Downloading the Repository
You can input the following in the terminal:   
` git clone https://github.com/AdamFariello/Nectar.git `

Or alternatively, just download the file: "Final.jar"

&nbsp;

### Step 1: Seting up the (mysql) database
#### Download and Installing mysql
Click on one of the following operating systems below to be taken to an 
download + installation guide for it.

- [Windows]( https://dev.mysql.com/doc/refman/8.0/en/windows-installation.html )
- [Mac]( https://dev.mysql.com/doc/refman/8.0/en/macos-installation.html  )
- [Linux]( https://dev.mysql.com/doc/refman/8.0/en/linux-installation.html )
- [For Anything Else]( https://dev.mysql.com/doc/refman/8.0/en/installing.html )

*(
When installing, choose: "Server Only"
*)

#### Starting the mysql service/daemon
Starting mysql differentiates between operating systems.  
I recommend following a tutorial, such as 
[this one]( https://phoenixnap.com/kb/start-mysql-server ).

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
Run the following in the terminal:    
`java final.jar {port number}`

Example:   
`java final.jar 8000"`

*(
Remember that port number, it will be used in the next step
)*

&nbsp;

### Step 3: Preparing the chrome browser  
On chrome, go to your extensions page (`chrome://extensions/`).   

Then: toggle the "developer mode" button at the top right corner of the page. 
 
Click "load unpacked" top left corner of the page.    
Go to the folder where the files were downloaded -> client -> gui 

When you load the extension, a prompt will appear, go to section "Running"

&nbsp;

&nbsp;

## Running the extension
---
When you the extension is loaded, a prompt will appear, 
asking for a port number, enter the port number used back in part 2.  
(An alert will appear, letting you know that the connection has been established.)

Then, a dialogue will appear, asking for your: username, and password.   
Enter whatever you want in both fields, click "login", 
and you will be flooded with alerts.   
*( 
The reason they're there is was for testing the app, 
but since the project is unfinished,
they've been left in
)*

&nbsp;

&nbsp;

### Contributions
---
#### Preface
##### Link to original contribution file
https://github.com/DoubleAyyBatteries/Nectar/blob/main/release/Contributions.pdf

##### Why are they listed here
Since this repository was duplicated from the original repository,
the contributions to the original repository are listed below.   
The order of the contributions is solely based on reading comprehension.        

The contributions listed are also only based off the main branch.   

##### When the contributions are updated
Contributions listed below may be deleted, 
(because they don't fit the the project anymore).   
Appendations will be added below for the deleted Contributions.

##### What is the purpose of "*" ?
Its usage refers to, all sub-folders, and files, within that folder 

&nbsp;

#### Rood-Landy Thezard rt576
- Nectar/server/application/src/bl/*    
(everything except: scripts folder, and UserDao.java)
- Nectar/server/application/src/server/*
- Nectar/server/application/src/tests/*
- Nectar/release/*
- Nectar/client/gui/model/*

&nbsp;

#### Adam Fariello ajf270
- Nectar/server/database/*
- Nectar/server/application/src/databaseCode/*
- Nectar/server/application/src/bl/UserDao.java   

&nbsp;

#### Fiaz Mushfeq fm398
- Nectar/server/application/src/bl/scripts     
(Started coding the scraper script)
- Nectar/test/*

&nbsp;

#### Akshat Adlakha aa2040
- Nectar/server/application/src/bl/scripts/*     
(Finished coding the scraper script)
- Nectar/client/gui/*    
(everything except model folder)

&nbsp;

&nbsp;

### Questions
---
*(
Questions below are questions not answered earlier in the README
)*

#### Why not fork the repository?
1. I want to keep the original submitted repository intact.
2. I want all the branches in the repository for development.

&nbsp;

#### What is the functionality of the original repository?
The python scraper script, the database, 
and notifications via email and text -- are completed.   

If you're not interested in running the program,
you can look at screen shots of email and text messaging working.   
Look at page one in
[release/Contributions.pdf]( https://github.com/AdamFariello/Nectar/blob/main/release/Contributions.pdf ).   

&nbsp;

#### Does this repository have the same level of functionality? 
As of right now of writing this answer -- 
(June 24, 2023) --
yes. 

&nbsp;

#### How long will this repository be on hiatus?
For a week or few.   
Other repositories need attention first, 
plusother priorities need to be dealt with first.


