# Roderick Kong Zhang - Project Portfolio Page

## Project: BoboType

### Overview

Bobotype is a minimalist, interactive CLI tool that enhances typing speed and accuracy through personalized exercises,
real-time feedback, and progress tracking, helping users build muscle memory and confidence efficiently.
Given below are my contributions to the project.

### Summary of Contributions

- **Typing Timer**: Added the typing timer to be able to get the duration taken by the user to complete typing.
  - What it does: This implementation allows the time taken by the user to complete typing to be retrieved.
  - Justification: This implementation is crucial as it enables the program to be able to calculate the typing speed of the user (words/min).
  - Highlights: This enhancement affects existing methods and methods to be added in the future. Additional error handling has been implemented, only allowing the typing duration to be retrieved when the Typing Timer has been started and stopped consecutively, allowing for easier debugging.

- **Typing Targets**: Added the ability to list, add, and remove typing targets.
  - What it does: This feature allows users to set targets for themselves, specifically with respect to their typing speed (WPM) and typing score (effective WPM = typing speed * accuracy). Users can manage their typing targets (list, add, remove) and will be informed when they hit their set targets.
  - Justification: This feature improves the product significantly as it allows users to set typing goals for themselves concretely and strive to achieve them, giving them more motivation to practice.
  - Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation too was challenging as it required many classes to be created, to allow for different typing targets (speed and score) to be represented, manipulated in a list, and saved to the hard disk for reloading.
  
- **Marked Text**: Added the ability to format text to highlight mistakes made by the user when typing.
  - What it does: This feature allows users to view what they have typed correctly/incorrectly at a glance by highlighting the text typed in green/red respectively.
  - Justification: This feature improves the product significantly in terms of the user experience as they can get real-time feedback on the text typed after each line, emphasising their mistakes clearly to improve their typing efficiency.
  - Highlights: This enhancement affects many existing features and features to be added in the future, since future modes will also require formatting of texts to highlight mistakes. It also required an in-depth analysis of design alternatives due to some of the limitations of Java. Initially, the idea was to format text entered by the user character-by-character as the user completes the typing test. However, It is very difficult to receive user input character-by-character without pressing "enter" in Java, across multiple OSes. After hours spent researching on possible methods to make character-by-character input work, I settled on formatting the text line-by-line instead and worked on the implementation.

- **Code contributed**: [RepoSense link](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=rodi-314&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=rodi-314&tabRepo=AY2425S2-CS2113-F13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

- **Contributions to the UG**:
  - Added documentation for all `target` commands:
    - `target list`
    - `target add speed SPEED`
    - `target add score SCORE`
    - `target remove TARGET_INDEX`
  - Edited sections "Saving the data", "Editing the data file", and "FAQ" to account for all data files to be saved.

- **Contributions to the DG**: 
  - Added and formatted the table for user stories
  - Update `Command` class diagram with new commands
  - Added implementation and diagrams for the following features:
    - Typing Targets
    - Marked Text

- **Contributions to team-based tasks**:
  - Suggested improvements to better organise the UG for readability
  - Helped to edit documentation for parts not done by me but are affected by my code changes
  - Cleaned up the UI significantly for consistently and readability (specifically in terms of indentation and separation between user input and program output)
  - Provided many JUnit tests to significantly increase test coverage

- **Review/mentoring contributions**:
  - PRs reviewed (with non-trivial comments): [#28](https://github.com/AY2425S2-CS2113-F13-2/tp/pull/28), [#49](https://github.com/AY2425S2-CS2113-F13-2/tp/pull/49), [#52](https://github.com/AY2425S2-CS2113-F13-2/tp/pull/52)

- **Project management**:
  - Consistently added issues and linked them to PRs for better tracking of tasks and features implemented
  - Assigned bugs surfaced during the PE-D that are relevant to my code to myself and linked them to PRs that fixed the corresponding bugs
  - Assigned corresponding issues to milestones to better track progress
  - Strong adherence to the forking workflow
