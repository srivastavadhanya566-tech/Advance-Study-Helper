# Advance Study Helper

An automated, cross-language command-line productivity engine that allows students to process unstructured study notes, automatically build interactive flashcard review sessions, manage local directories, and visualize key learning topics.

## 🚀 Key Architectural Features

1. **Structured Note Storage & Folder Management:** Allows users to categorize text dynamically using a hash tag (`#SubjectName`), which automatically creates physical system folders and groups active study text files.
2. **Algorithmic Text Tokenization (Data Cleaning):** Processes raw string streams using custom character filters, strips out low-value semantic stop-words, and aggregates analytical text payloads.
3. **Automated Flashcard Generation & Review Mode:** Detects syntactic dividers (`-` or `:`) to parse user notes into Question-Answer matrices stored locally. Incorporates an interactive execution track (`review`) allowing students to run local terminal active-recall sessions.
4. **Cross-Language Analytical Pipeline:** Pipes runtime metrics to a structured text layer (`study_metrics.csv`) using Java File I/O workflows (`FileWriter`).
5. **Data Visualization (Python Backend):** Utilizes `pandas` to aggregate textual keyword frequency data via a `Counter` state machine, generating custom `matplotlib` data visualizations of a user's top-studied topics.

## 🛠️ Tech Stack
* **Java Core:** String Manipulation, Stream Tokenization, Multi-branch State Handling, Exception Safety.
* **Java File I/O:** `java.io.File`, `java.io.FileWriter`, `java.util.Scanner` for local system persistence.
* **Python Data Analysis:** `pandas`, `collections.Counter`, `matplotlib.pyplot` for visual metrics dashboards.Text Tokenization
