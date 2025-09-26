import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NotesApp {

    // Define the file name globally
    private static final String NOTES_FILE = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("--- Simple Console Notes Manager ---");

        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                switch (choice) {
                    case 1:
                        readNotes();
                        break;
                    case 2:
                        writeNewNote(scanner);
                        break;
                    case 3:
                        appendNote(scanner);
                        break;
                    case 4:
                        running = false;
                        System.out.println("\nExiting Notes App. Goodbye! 👋");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select an option between 1 and 4.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a number for your choice.");
                scanner.nextLine(); // Clear the buffer
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Read All Notes");
        System.out.println("2. Write New Note (overwrites existing file)");
        System.out.println("3. Append Note (adds to the end)");
        System.out.println("4. Exit");
    }

    // 1. READ Operation
    private static void readNotes() {
        System.out.println("\n--- Reading Notes from " + NOTES_FILE + " ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(NOTES_FILE))) {
            String line;
            boolean fileEmpty = true;
            
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                fileEmpty = false;
            }

            if (fileEmpty) {
                System.out.println("[File is currently empty.]");
            }
            
        } catch (IOException e) {
            // This usually happens if the file doesn't exist yet
            System.out.println("No notes file found. Start by writing a new note (Option 2 or 3).");
        }
    }

    // 2. WRITE Operation (Overwrite mode)
    private static void writeNewNote(Scanner scanner) {
        System.out.println("\n--- Write New Note (WARNING: This will overwrite " + NOTES_FILE + ") ---");
        System.out.println("Enter your note (type 'END' on a new line to finish):");
        
        // Use try-with-resources to ensure the writer is closed
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOTES_FILE, false))) { 
            String line;
            while (!(line = scanner.nextLine()).equalsIgnoreCase("END")) {
                writer.write(line);
                writer.newLine(); // Add a newline after each line written
            }
            System.out.println("✅ New note successfully saved and file overwritten.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // 3. APPEND Operation
    private static void appendNote(Scanner scanner) {
        System.out.println("\n--- Append Note to " + NOTES_FILE + " ---");
        System.out.print("Enter the note to append: ");
        String noteToAppend = scanner.nextLine();
        
        // FileWriter(fileName, true) enables append mode
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOTES_FILE, true))) { 
            writer.newLine(); // Add a separation line before the new note
            writer.write("--- " + java.time.LocalDateTime.now().toString() + " ---"); // Timestamp for context
            writer.newLine(); 
            writer.write(noteToAppend);
            writer.newLine(); // Ensure the last line ends with a newline
            
            System.out.println("✅ Note successfully appended.");
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }
}
