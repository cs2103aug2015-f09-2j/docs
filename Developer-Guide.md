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
-[Notable Methods from APIs](#api)

<a name="archi"></a>Architecture
=================================
![alt text](https://github.com/cs2103aug2015-f09-2j/docs/blob/master/Images/Developer-Guide/architecture.png)

Chronos consists of the following components:
-**[GUI](#gui)**: Handles user interaction
-**[Logic](#logic)**: Executes user commands and mediates between GUI and Storage 
-**[Parser](#parser)**: Manipulates strings and handles JSON conversion
-**[Storage](#sto)**: Manages data operations

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
-String-Task: `createItem(String)`
-Task-JSON: `convertToTaskArray(JSONArray)`, `retrieveTask(String, JSONArray)`

<a name="sto"></a>Storage
========================
![alt text](https://github.com/cs2103aug2015-f09-2j/docs/blob/master/Images/Developer-Guide/storage.png)

Storage's main functions are to read from and write to the text file. At runtime, it keeps the contents in a `JSONArray` and makes sure every change is written into the file. It also provides support for undo, redo and cd commands. 








