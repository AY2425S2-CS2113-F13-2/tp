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

Typing Targets are facilitated by `TypingTargetList`, `TypingTarget`, `TypingTargetSpeed`, and `TypingTargetScore`.
`TypingTargetList` contains various `TypingTarget` instances, which can be of class `TypingTargetSpeed` or
`TypingTargetScore`.

The user can issue the command `targetspeedadd` or `targetscoreadd` to add a typing target for speed or score
respectively. When the target is hit when the user finishes a `normal` typing test, the program will inform the user
that their target has been successfully reached.

### Milestones Feature

#### Implementation

Milestones are faciliated by `Milestones`.

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

| Version | As a ...         | I want to ...                                                                     | So that I can ...                                                 |
|---------|------------------|-----------------------------------------------------------------------------------|-------------------------------------------------------------------|
| v1.0    | slow typer       | practise typing with different texts                                              | improve my typing speed                                           |
| v1.0    | user             | have various lengths for typing tests                                             | spend as little or as much time I want practising                 |
| v1.0    | user             | adjust the difficulty level of typing exercises                                   | challenge myself appropriately as I improve over time             |
| v1.0    | user             | track my typing speed                                                             | know how fast I can type                                          |
| v1.0    | user             | track my typing accuracy                                                          | know how accurately I can type                                    |
| v1.0    | user             | track my typing high scores                                                       | track my personal best and attempt to break it                    |
| v2.0    | goal-driven user | set specific targets for typing speed and scores and get notified when I hit them | keep track of my progress and goals                               |
| v2.0    | unmotivated user | have timed tests                                                                  | be motivated to type faster each time                             |
| v2.0    | user             | type my own words (zen mode)                                                      | train using my own texts                                          |
| v2.0    | user             | have a tool that auto-adjusts my exercises' difficulty based on my experience     | gradually improve as the difficulty increases with my skill level |
| v2.0    | user             | track milestones I achieve                                                        | keep track of my progress and give myself a sense of achievement  |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
