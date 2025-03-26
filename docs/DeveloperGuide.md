# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Zen Mode feature

#### Proposed Implementation

Zen Mode is facilitated by `ZenMode`. Additionally, it implements the following operations:
- `ZenMode(TypingTimer typingTimer,Scanner sc, Ui ui)` - Constructor to create ZenMode object
- `startZenMode()` - Runs input loop to read user input and compute typing statistics

Given below is an example usage scenario and how the Zen Mode behaves at each step.

Step 1. The user selects Zen Mode when selecting the practice mode, instantiating a `ZenMode` object and running `startZenMode()`. `startZenMode()` starts `typingTimer` and reads user input.

Step 2. The user types `I am typing a sample text here`, calling `wordCounter.countWords` to count the number of words in the user's input and adds to the `wordCount`.

Step 3. The user types `stop_practice`, ending the loop and computes the typing speed.

Step 4. The typing practice results is displayed to the user with `UI.showZenModeEndGame`


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
