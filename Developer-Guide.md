Table of Contents
=================
- [Architecture](#archi)
- [GUI](#GUI)
  - [`CommandBarController` calss](#cbc)
  - [`Summary` class](#sum)
  - [`DetailedView` class](#detail)
- [Logic](#logic)
  - [`Feedback` class](#fb)
  - [`Command` class](#cmd)
  - [`Task` class](#task)
- [Parser](#parser)
- [Storage](#sto)
- [Instructions for Setting up and Testing](#instruction)
  - [Development Environment](#deve)
  - [Eclipse Tools](#eclipse)
  - [Coding Convention](#code)
  - [Testing](#test)
- [Notable Methods from APIs](#api)
  - [GUI component](#guiapi)
  - [Logic component](#logicapi)
  - [Parser component](#parserapi)
  - [Storage component](#storageapi)

<a name="archi"></a>Architecture
=================================
![alt text](https://github.com/cs2103aug2015-f09-2j/docs/blob/master/Images/Developer-Guide/architecture.png)

Chronos consists of the following components:
**- [GUI](#gui)**: Handles user interaction
**- [Logic](#logic)**: Executes user commands and mediates between GUI and Storage 
**- [Parser](#parser)**: Manipulates strings and handles JSON conversion
**- [Storage](#sto)**: Manages data operations

For reference, important methods mentioned in the guide are described in detail in **[Notable Methods from APIs](#api)**.

<a name="GUI"></a>GUI
======================
![alt text](https://github.com/cs2103aug2015-f09-2j/docs/blob/master/Images/Developer-Guide/GUI.png)

The GUI is responsible for receiving all user input, passing this to Logic via its `handleCommand(String)` method, and display the resulting feedback (consisting of text and data) from Logic. It is implemented using JavaFX and consists of three classes: **`CommandBarController`**, **`Summary`** and **`DetailedView`**.

<a name="cbc"></a>`CommandBarController` class
---------------------------------------
Displays the command bar, feedback messages as well as the current date. Whenever the user presses **Enter**, it will take in the entered text in the command bar via `onKeyPress(KeyEvent)`, pass it to `Logic` and update the feedback `String` via `displayFeedback(String)` below the command bar.

<a name="sum"></a>`Summary` class
--------------------------
Displays the list of tasks in a table containing its id, due date, description,category,number of notes and priority. It is updated via `display(ObservableList<Task>)`.

<a name="detail"></a>`DetailedView` class
-----------------------------------
Displays the detailed information about a particular task when the user asks for it. It shows all the notes in the task. It is updated via `display(Task)`.

<a name="logic"></a>Logic
=========================
![alt text](https://github.com/cs2103aug2015-f09-2j/docs/blob/master/Images/Developer-Guide/logic.png)

Logic acts as the ‘middleman’ between GUI and Storage. Communication with the GUI is handled mainly via `executeUserCommand(String)`. Logic makes use of the following classes: **`Feedback`**, **`Command`** and **`Task`**. 

<a name="fb"></a>`Feedback` class
---------------------------
Serves as the data packet that Logic returns to the GUI after every user command. It contains a feedback `String` and, depending on the command, an `ArrayList` of `Task`s. Whether or not a `Feedback` object has data can be checked via its `hasData()` method.

<a name="cmd"></a>`Command` class
--------------------------
Executes the user input described in the command list. Each command is designated with a type depending on its operation, which range from data manipulation to prompting the GUI to change views (Summary or Detailed View). It is also in charge of sending data to Storage.

<a name="task"></a>`Task` class
--------------------------
Task is the Chronos’ main entity class, and is used by both Logic and GUI. It contains to-do list attributes such as description, due dates, categories and priorities and related manipulation methods. 

<a name="parser"></a>Parser
=========================
![alt text](https://github.com/cs2103aug2015-f09-2j/docs/blob/master/Images/Developer-Guide/parser.png)

Parser is mainly in charge of String manipulation and class conversions involving `String`s and `JSON` from Logic. It is also responsible for generating task ID’s. It handles the following conversions:
- String-Task: `createItem(String)`
- Task-JSON: `convertToTaskArray(JSONArray)`, `retrieveTask(String, JSONArray)`

<a name="sto"></a>Storage
========================
![alt text](https://github.com/cs2103aug2015-f09-2j/docs/blob/master/Images/Developer-Guide/storage.png)

Storage's main functions are to read from and write to the text file. At runtime, it keeps the contents in a `JSONArray` and makes sure every change is written into the file. It also provides support for undo, redo and cd commands. 

<a name="instruction"></a>Instructions for Setting up and Testing
===================================
<a name="deve"></a>Developement Environment
------------------------------------
- [JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (8u60 or greater)
- [Eclipse](https://eclipse.org/downloads/) (MARS)
- [Git](https://git-scm.com/downloads) (if you don't want to use EGit)

<a name="eclipse"></a>Eclipse Tools
----------------------
Chronos makes use of the following tools with Eclipse. They can be downloaded from the  web or the Eclipse marketplace.
- [e(fx)clipse](http://www.eclipse.org/efxclipse/install.html)
- [EGit](http://www.eclipse.org/egit/download/)
- JUnit: already included in Eclipse

<a name="code"></a>Coding Convention
------------------------
Chronos obeys the following [Java coding standard](https://docs.google.com/document/pub?id=1iAESIXM0zSxEa5OY7dFURam_SgLiSMhPQtU0drQagrs&amp&embedded=true).

<a name="test"></a>Testing
---------------------------
Chronos uses JUnit to perform unit tests on the components. Tests are placed in the **src/test** folder and storage files for testing are placed in the **src/test/testFiles** folder. 

<a name="api"></a>Notable Methods from APIs
============================================
<a name="guiapi"></a>GUI component
---------------------------------
####`GUI` class

Return type | Method and Description
------------|---------------
void | `handleCommand(String)`: calls Logic to execute the entered command String

####`CommandBarController` class

Return type | Method and Description
-------------|-------------
void | `onKeyPress(KeyEvent)`: gets input once Enter is pressed and sends it to GUI
void | `displayFeedback(String)`: updates the feedback message

####`Summary` class

Return type | Method and Description
------------|------------
void | `display(ObservableList<Task>)`: updates the table with tasks

####`DetailedView` class

Return type | Method and Description
-----------|-------------
void | `display(Task)`: displays details and notes of a specific task

<a name="logicapi"></a>Logic Component
-------------------------------------
####`Logic` class

Return type | Method and Description
------------|-----------
Feedback | `executeUserCommand(String)`: creates a Command out of user input and executes it

####`Command` class

Return type | Method and Description
------------|------------
Feedback | `execute()`: executes the command based on its type
Feedback | `add(String)`: creates a new Task and updates storage accordingly
Feedback | `delete(String)`: deletes a specified Task and updates storage accordingly
Feedback | `display(String)`: finds and returns a list of tasks from storage that fall under certain criteria to the GUI for display
Feedback | `update(String)`: searches for an already stored task and updates it accordingly

<a name="parserapi"></a>Parser component
--------------------------
####`Parser` class

Return type | Method and Description
-----------|---------
JSONObject | `createItem(String)`: This method is called when add command in Command Class is called. The string received will be split into its different categories and put into a JSONObject object and thus will be returned.
ArrayList<Task> | `convertToTaskArray(JSONArray)`: This method receives a JSONArray which is the data in the stroage file and will be converted to ArrayList and returned back to Logic for display purposes.
ArrayList<String> | `parseUpdateString(String)`: This method is called when update command in Command class is called. The string received from Logic will be split to add the id, field to update and updated information to an arraylist and thus returned to Command class for the updating process.
Task | `retrieveTask(String, JSONArray)`: This method is called when search or view command is called in Command class. The string received is the id of the item that the user wants to search or view. This method will then convert the selected item from JSONObject to Task object and return it to Command class.

<a name="storageapi"></a>Storage Component
------------------------------
####`Storage` class

Return type | Method and Description
-----------|----------
Storage | `Storage(String filePath)`: Create the file if not exist, else read in the String in the file and parse it into JSONArray entries_
void | `storeTemp()`: To be called before add, delete, update command to store the not changed yet JSONArray entries_ as JSONArray temp_entries_ in case the user wants to undo
void | `storeChanges()`: To be called after add, delete, update command to write the changed JSONArray entries_ to file
void | `swapTemp()`: To be called by undo/redo command if the last command is add, delete or update to achieve undo/redo in both memory and file
void | `swapFile()`: To be called by undo/redo if the last command is cd
void | `changeDirectory(String newDirectory)`: Write file in the newDirectory and delete the old file














