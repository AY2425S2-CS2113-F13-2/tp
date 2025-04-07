# Viswanathan Ravisankar - Project Portfolio Page

## Project: BoboType

### Overview

Bobotype is a minimalist, interactive CLI tool that enhances typing speed and accuracy through personalized 
exercises, real-time feedback, and progress tracking, helping users build muscle memory and confidence 
efficiently.

### Summary of Contributions

Given below are my contributions to the project.

- **Parser:**  Added the parser to interpret input from user.
  - What it does: Allows the user to type in commands and get the appropriate response.
  - Justification: This implementation allows the development of new commands to be easy to implement.
  - Highlights: This enhancement affects existing and commands to be added in the future.

- **Typing Accuracy:** Added the ability to view typing accuracy.
  - What it does: allows the user to view the typing accuracy of their previous test in normal mode.
  - Justification: This feature improves the product significantly because viewing their typing accuracy 
  shows their performance in typing
  - Highlights: This enhancement required an in-depth analysis of design alternatives.
  
- **Zen Mode:** Added the ability to type freely
  - What it does: allows the user to type any words they wish rather than following a pre-determined typing test and
  view their results for parameters such as word count and typing speed
  - Justification: This feature improves the product significantly because users have more freedom to practice typing

- **Code contributed:** [RepoSense link](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=ravi-viswa105&tabRepo=AY2425S2-CS2113-F13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

- **Project management:** Managed releases v1.0 - v2.1 (3 releases) on GitHub

- **Documentation:** 
  - User Guide:
    - Added documentation for the feature `ZenMode`
  - Developer's Guide:
    - Added Architecture diagram
    - Edited `Command` class diagram
    - Added class diagram for parsing logic
    - Added implementation detail of the `ZenMode` command
    - Added implementation detail of the `TypeAccuracy` class

- **Community: **
  - PRs reviewed (with non-trivial review comments): [#34](https://github.com/AY2425S2-CS2113-F13-2/tp/pull/34),
  [#58](https://github.com/AY2425S2-CS2113-F13-2/tp/pull/58),
  [#128](https://github.com/AY2425S2-CS2113-F13-2/tp/pull/128)