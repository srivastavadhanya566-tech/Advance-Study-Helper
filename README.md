# Study Helper

A command-line study notes tool written in Java, with a Python script for keyword analytics. Started as a 5th semester minor project and still a work in progress.

## Features

- **Notes:** saved to `notes.txt` inside the current folder
- **Folders:** type `#Physics` to create or switch to a folder
- **Keyword logging:** removes punctuation and common words, then logs the rest to `study_metrics.csv`
- **Flashcards:** a note like `term - definition` or `term: definition` also becomes a flashcard
- **Review:** type `review` to go through the flashcards one by one
- **Analytics:** type `analytics` to open a bar chart of your most frequent keywords

## Files

- `StudyHelper.java`: the main program
- `analytics.py`: reads `study_metrics.csv` and plots the top keywords
- `server.js`, `package.json`: Node.js server for a browser interface (in progress)

## Running it

Needs a JDK, and Python with `pandas` and `matplotlib` for the chart.

```bash
javac StudyHelper.java
java StudyHelper
```

Create a folder first (e.g. type `#General`), then start typing notes. Type `exit` to quit.


## Planned

- Browser interface
- Database storage instead of text files
- Edit, delete and search for notes

