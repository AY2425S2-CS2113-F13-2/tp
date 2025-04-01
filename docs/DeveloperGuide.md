# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

## Design
### Architecture

{Insert architecture diagrams and explain the overall architecture of the code.}

### Ui Component

The API of this component is specified in `Ui.java`.

### Logic Component

### Model Component

### Storage Component

### Common Classes

## Implementation

### Typing Accuracy

#### Proposed Implementation

The typing accuracy of the user is facilitated by `TypingAccuracy`. It implements the following operations:
- `TypingAccuracy(ArrayList<String> userText)` - Constructor to create TypingAccuracy object with user input
- `setTestText` - Set's the test text to the corresponding text being tested
- `updateUserInput` - Update's the user input when they type a new line
- `getTypingAccuracy` - Computes and returns typing accuracy

### Zen Mode Feature

#### Proposed Implementation

Zen Mode is facilitated by `ZenMode`. Additionally, it implements the following operations:

- `ZenMode(TypingTimer typingTimer,Scanner sc, Ui ui)` - Constructor to create ZenMode object
- `startZenMode()` - Runs input loop to read user input and compute typing statistics

Given below is an example usage scenario and how the Zen Mode behaves at each step.

Step 1. The user selects Zen Mode when selecting the practice mode, instantiating a `ZenMode` object and running
`startZenMode()`. `startZenMode()` starts `typingTimer` and reads user input.

Step 2. The user types `I am typing a sample text here`, calling `wordCounter.countWords` to count the number of words
in the user's input and adds to the `wordCount`.

Step 3. The user types `stop_practice`, ending the loop and computes the typing speed.

Step 4. The typing practice results is displayed to the user with `UI.showZenModeEndGame`

### Typing Targets Feature

#### Proposed Implementation

Typing Targets are facilitated by `TypingTargetList`, `TypingTarget`, `TypingTargetSpeed`, and `TypingTargetScore`.
`TypingTargetList` contains various `TypingTarget` instances, which can be of class `TypingTargetSpeed` or
`TypingTargetScore`.

The user can issue the command `targetspeedadd` or `targetscoreadd` to add a typing target for speed or score
respectively. When the target is hit when the user finishes a `normal` typing test, the program will inform the user
that their target has been successfully reached.

### Milestones Feature

#### Implementation

Milestones are facilitated by `Milestones` and managed in coordination with `AutoAdjust`.
Additionally, it implements the following operations:

- `Milestones(String filePath)` - Loads milestone progress from a file (or initializes it if not found).

- `checkAndUpdate(String difficulty, double wpm)` - Checks whether the user has hit the milestone (e.g., 60 WPM in easy)
and updates their progress if so. Promotes to the next difficulty and writes to file.

- `getCurrentDifficulty()` - Returns the current default difficulty that will be suggested for practice.

- `evaluate(int wpm)` - Invokes checkAndUpdate to determine whether the user should be promoted. If a milestone is hit, 
the user is congratulated with Ui.showMilestoneAchieved.

Given below is an example usage scenario of how Milestones feature behave at each step.
Step 1. The user starts a practice session in normal or timedLimit mode. `Milestones.getCurrentDifficulty()` is used to 
determine their default difficulty (e.g., "easy").

Step 2. The user completes the round and achieves a sufficiently high WPM (e.g., 61 WPM in easy mode).
`AutoAdjust.evaluate(wpm)` is called at the end of the session.

Step 3. `Milestones.checkAndUpdate(difficulty, wpm)` checks if the user qualifies for a milestone. Since 61 WPM is the 
goal for "easy"(60), the milestone is achieved.

Step 4. The Milestones class promotes the user to the next difficulty (e.g., from "easy" to "intermediate") and updates 
`data/milestones.txt`.
`Ui.showMilestoneAchieved(...)` is then called to notify the user of their achievement and promotion.

### Highscore List Feature

#### Implementation

Highscore List is facilitated by `highScoreList` and managed in coordination with `Highscore`.
Additionally, it implements the following operations:

- `getHighscoreList()` - loads the highscore list from storage file.
- `saveScoreList(ArrayList<Double> newHighScoreList)` - saves the highscore list to storage file.
- `readHighScoreList()` - reads the highscore list from storage file.
- `updateHighScore(Double accuracy, int wpm)` - updates the highscore list by adding the new highscore to the list.
  Then sorting the list into the top 3 highscores.
- `showHighscoreList()` - displays the highscore list to the user.

Given below is an example usage scenario of how Highscore List feature behave at each step.

Step 1. The user starts a practice session in normal mode.

Step 2. The user completes the round and the typing accuracy and words per minute (wpm) are calculated.

Step 3. `state.updateHighscoreList(typingAccuracyDouble, typingSpeedWPM)` adds the new highscore to the highscore list.
Then, the list is sorted into the top 3 highscores.

### Highscore Feature

#### Implementation

Highscores are facilitated by `Highscores` and managed in coordination with `TypingTest`.
Additionally, it implements the following operations:

- `getHighscore()` - loads the top highscore from the highscore List.
- `updateHighscore(Double accuracy, int wpm)` - updates the highscore list by adding the new highscore to the list.
  Then sorting the list into the top 3 highscores.
- `showHighscore()` - displays the highscore to the user.

Given below is an example usage scenario of how Highscore feature behave at each step.

Step 1. The user starts a practice session in normal mode. 

Step 2. The user completes the round and the typing accuracy and words per minute (wpm) are calculated. 
`state.updateHighscore(typingAccuracyDouble, typingSpeedWPM)` is called at the end of the session.

Step 3. `state.updateHighscore(typingAccuracyDouble, typingSpeedWPM)` adds the new highscore to the highscore list.
Then, the list is sorted into the top 3 highscores.

Step 4. `getHighscore()` will take the top highscore in the highscore list and save it.

Step 5. `state.showHighscore()` displays the highscore to the user.

## Product scope

### Target user profile

Has a desire to improve typing speed and accuracy.

Prefers desktop applications to web or mobile apps.

Enjoys or is comfortable using Command-Line Interface (CLI) tools.

Prefers keyboard-based interactions to mouse navigation.

Is motivated by progress tracking, stats, and milestone achievements.

### Value proposition

Helps users practice and improve typing in a distraction-free CLI environment by offering a faster, 
lightweight alternative to bloated GUI typing apps, ideal for keyboard-centric users.

## User Stories

| Version | As a ...         | I want to ...                                                                    | So that I can ...                                                 |
|---------|------------------|----------------------------------------------------------------------------------|-------------------------------------------------------------------|
| v1.0    | slow typer       | practise typing with different texts                                             | improve my typing speed                                           |
| v1.0    | user             | have various lengths for typing tests                                            | spend as little or as much time I want practising                 |
| v1.0    | user             | adjust the difficulty level of typing exercises                                  | challenge myself appropriately as I improve over time             |
| v1.0    | user             | track my typing speed                                                            | know how fast I can type                                          |
| v1.0    | user             | track my typing accuracy                                                         | know how accurately I can type                                    |
| v1.0    | user             | track my typing high score                                                       | track my personal best and attempt to break it                    |
| v2.0    | goal-driven user | set specific targets for typing speed and scores and get notified when I hit them | keep track of my progress and goals                               |
| v2.0    | unmotivated user | have timed tests                                                                 | be motivated to type faster each time                             |
| v2.0    | user             | type my own words (zen mode)                                                     | train using my own texts                                          |
| v2.0    | user             | have a tool that auto-adjusts my exercises' difficulty based on my experience    | gradually improve as the difficulty increases with my skill level |
| v2.0    | user             | track milestones I achieve                                                       | keep track of my progress and give myself a sense of achievement  |
| v2.0    | user             | view the top 3 highscores                                                        | keep track of my progress and past performances.                  |
## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
