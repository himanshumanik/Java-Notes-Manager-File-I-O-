# Java File I/O – Notes App 📝

This repository contains the solution for **Task 4: Java File I/O – Notes App**.

## Objective

The objective was to create a command-line utility for managing text-based notes using fundamental Java File I/O classes. The application can read, overwrite, and append content to a designated file.

## Technical Details

* **File Name**: All notes are stored in a file named `notes.txt` in the same directory where the program is executed.
* **Reading**: The `readNotes()` method uses **`FileReader`** wrapped in a **`BufferedReader`** for efficient line-by-line reading.
* **Writing (Overwrite)**: The `writeNewNote()` method uses **`FileWriter(NOTES_FILE, false)`** (the default mode) wrapped in a **`BufferedWriter`**. It completely replaces the existing content of `notes.txt`.
* **Appending**: The `appendNote()` method uses **`FileWriter(NOTES_FILE, true)`** (setting the `append` flag to true) to add new content to the end of the file.
* **Error Handling**: `try-with-resources` blocks are used to ensure the `FileReader` and `FileWriter` objects are always closed automatically, even if an `IOException` occurs.

## How to Run

1.  Save the code as `NotesApp.java`.
2.  **Compile the Java file:**
    ```bash
    javac NotesApp.java
    ```
3.  **Run the application:**
    ```bash
    java NotesApp
    ```

## Example Commands

| Menu Option | Action | Java I/O Class Used |
| :--- | :--- | :--- |
| **1. Read All Notes** | Displays all content from `notes.txt`. | `BufferedReader`/`FileReader` |
| **2. Write New Note** | Prompts for multi-line input and **overwrites** the file. | `FileWriter(..., false)` |
| **3. Append Note** | Prompts for a single line and **adds** it to the end. | `FileWriter(..., true)` |
| **4. Exit** | Closes the application. | N/A |
