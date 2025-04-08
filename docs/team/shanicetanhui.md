# Shanice Tan Hui - Project Portfolio Page

## Project: BoboType

### Overview

Bobotype is a minimalist, interactive CLI tool that enhances typing speed and accuracy through personalized
exercises, real-time feedback, and progress tracking, helping users build muscle memory and confidence
efficiently.

### Summary of Contributions

Given below are my contributions to the project.

- **Milestones:** Implemented the Milestones feature which tracks a user’s typing proficiency and automatically adjusts their 
default difficulty based on performance.

  - What it does: Tracks the user’s progress across different difficulty levels (easy, intermediate, difficult) by checking 
  if they’ve met specific score-based goals. Once a milestone is achieved, the user is promoted to the next difficulty level.

  - Justification: This feature creates a personalized and scalable experience for users, ensuring that they are always 
  challenged appropriately without needing manual configuration.

  - Highlights: The milestone logic is persisted using file I/O, allowing the user’s progress to carry over across sessions.
It integrates seamlessly with other components like high score tracking and difficulty selection, and required 
thoughtful consideration of how to evaluate user performance over time (using high score, not just session WPM).


- **Progress Report:** Developed the Progress Report system which visualizes the user’s past typing scores.

    - What it does: Stores and displays the last 10 typing scores in a simple, CLI-based bar graph format, giving users a 
clear sense of their progress over time.

    - Justification: This feature supports user motivation and self-improvement by providing tangible, visual feedback on 
their typing development.

    - Highlights: Required creating a new data persistence mechanism separate from the high score system, and designing 
  a compact, text-based visualization that works well within CLI limitations.
      The implementation ensures that every attempt is tracked by maintaining a counter across all sessions, allowing 
  each run to be associated with a distinct session number. This enables users to view progress in chronological order 
  with session labels like ```Session 14```, ```Session 13```, etc., even when displaying only the last 10 attempts.
      It also integrates with the end of a practice session to automatically update the report and reflect the most 
  recent performance.


- **Basic Ui:** Developed the initial basic Ui and style of BoboType.


- **Code contributed:** [RepoSense link](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=hyu&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=shanicetanhui&tabRepo=AY2425S2-CS2113-F13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


- **Contributions to the UG:**
- Added documentation for the `Milestones` feature.
- Added documentation for the `Progress Report` feature.
- Did minor edits to ensure uniform formatting.


- **Contributions to the DG:**
- Added the `Appendix: Instructions for Manual Testing`, `Glossary`, `Non Functional-Requirements`, `Value Proposition`,
`Project Scope`.
- Added documentation for the `Milestones` feature.
- Added documentation for the `Progress Report` feature.
- Added documentation for the `Architecture` section.


- **Contribution to team-based tasks:** [Maintaining the issue tracker](https://github.com/AY2425S2-CS2113-F13-2/tp/issues/113)


- **Review/mentoring contributions:**
  - Commented and reviewed on the following PRs:
    - [`#27`](https://github.com/AY2425S2-CS2113-F13-2/tp/pull/27)
    - [`#129`](https://github.com/AY2425S2-CS2113-F13-2/tp/pull/129)
    - [`#131`](https://github.com/AY2425S2-CS2113-F13-2/tp/pull/131)

