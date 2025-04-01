# User Guide

## Introduction

Bobotype is a minimalist, interactive CLI tool that enhances typing speed and accuracy through personalized exercises, 
real-time feedback, and progress tracking, helping users build muscle memory and confidence efficiently.

## Quick Start

1. Ensure that you have Java 17 or above installed.
1. Down the latest version of `Bobotype` from [here](https://github.com/AY2425S2-CS2113-F13-2/tp/releases/tag/v1.1).
2. Download the jar file to the folder you want to use as the home folder for your Bobotype.
3. Open a terminal and navigate to the folder where the jar file is located.
4. Run the command `java -jar Bobotype.jar` to start the application.

## Features

### Start a Typing Exercise: `start`
Starts a typing exercise with a random sentence.

Format: `start`

### Set Mode: `normal`, `timelimit`, `zen`
Set the mode to set the typing speed.

Format: `normal`, `timeLimit`, `zen`

Example:

` Select your mode: `

` Type: 'normal' or 'timeLimit' or 'zen'`

### Normal Typing Speed: `normal`
Times the typing exercise. Gives the user a choice of the level of difficulty of the typing exercise.

Format: `normal`

Example:

` Select your difficulty: `

` Type: 'easy' or 'intermediate' or 'difficult'`

### Select Difficulty Level: `easy`, `intermediate`, `difficult`
Select the difficulty level of the typing exercise.

Format: `easy`, `intermediate`, `difficult`


### Time Limit Typing Speed: `timeLimit`
Set a time limit for the typing exercise to the user's liking.

Format: `timeLimit`

Example:

` Set your time limit: `

` Type: '1' or '2' or '3' `

### Set Zen Typing Speed: `zen`
Type as long as desired. Get the typing speed of the user's attempt.

Format: `zen`

Example:

```
Welcome to Zen Mode, you can type out anything to your heart's content and find out your typing speed.
Typing 'start' will start the typingTimer and typing the command 'stop_practice' will stop the practice.
```

User types
- `start`
- `Some Sample Text`
- `stop_practice`

Returns
```
____________________________________________________________
You finished the Zen Mode Practice!
You have typed: 4 words
Typing speed (WPM): 33 WPM
Typing speed (CPM): 246 CPM
____________________________________________________________
Please type
- 'exit' to exit or
- 'start' to start the new practice.
____________________________________________________________
```

### Set Typing Difficulty: `difficulty`
Sets the difficulty of the typing exercise.

Example:
` Select your difficulty: `

` Type: 'easy' or 'intermediate' or 'difficult' `

### Get Typing Accuracy: `typingaccuracy`
Returns the typing accuracy of the user's previous typing test.

Format: `typingaccuracy`

Example: 

` Your typing accuracy is: 39.285714285714285%`

### Get High Score: `highscore`
Gets back the highest score of the user across all attempts.

Format: `highscore`

Example: 

` Your high score is: 49.0`

### Get High Score List: `highscorelist`
Gets back the top 3 highest score of the user across all attempts.

Format: `highscorelist`

Example:

` Top 3 High Scores: `

`1. 49.0`

`2. 30.5`

`3. 12.7`

### Add Target Speed: `targetspeedadd`
Add a target speed (WPM) to hit. Users will be informed when they hit their target speed.

Format: `targetspeedadd TARGET_SPEED`

Example:

`targetspeedadd 60`

### Add Target Score: `targetspeedadd`
Add a target score (effective WPM) to hit. Users will be informed when they hit their target score.

Format: `targetspeedadd TARGET_SCORE`

Example:

`targetscoreadd 60`

### Exit the application: `exit`
Exits the application.

Format: `exit`

### Saving the data
Bobotype data is saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

### Editing the data file
Bobotype data is saved as a text file `data/bobotype.txt`. 
Advanced users are welcome to update data directly by editing that data file.


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

* Start a typing exercise `start`
* Set mode `normal`, `timelimit`, `zen`
* Set difficulty level `easy`, `intermediate`, `difficult`
* Set text length `short`, `medium`, `long`
* Get typing accuracy `typingaccuracy`
* Get high score `highscore`
* Get high score list `highscorelist`
* Add target speed `targetspeedadd`
* Add target score `targetscoreadd`
