import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class space {
    public static void main(String[] args) {

        // --- 1. SETUP TOOLS ---
        // Tool to read input from keyboard
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Box ---");
        System.out.println("Type anything to save. Commands: 'exit', 'show', 'search'");

        // --- 2. START INFINITE LOOP ---
        // Keep the program running forever until stopped
        while (true) {

            // Read what the user types
            String text = scanner.nextLine();

            // ===========================
            // OPTION 1: EXIT PROGRAM
            // ===========================
            if (text.equals("exit")) {
                System.out.println("Goodbye!");
                break; // Stop the loop
            }

            // ===========================
            // OPTION 2: SHOW ALL MEMORIES
            // ===========================
            if (text.equals("show")) {
                System.out.println("\n--- Reading Memory ---");
                try {
                    File file = new File("memory.txt");       // Select file
                    Scanner fileReader = new Scanner(file);   // Tool to read file

                    // Loop: Read line by line until end
                    while (fileReader.hasNextLine()) {
                        String line = fileReader.nextLine();
                        System.out.println(line);
                    }
                    fileReader.close(); // Important: Close file

                } catch (Exception error) {
                    System.out.println("No memories found yet!");
                }
                System.out.println("----------------------\n");
                continue; // Jump back to start (Don't save "show")
            }

            // ===========================
            // OPTION 3: SEARCH MEMORY
            // ===========================
            if (text.equals("search")) {
                System.out.print("Enter keyword: ");
                String keyword = scanner.nextLine(); // Ask for filter word

                System.out.println("--- SEARCH RESULTS ---");
                try {
                    File file = new File("memory.txt");
                    Scanner reader = new Scanner(file);

                    // Loop through all lines
                    while (reader.hasNextLine()) {
                        String line = reader.nextLine();
                        
                        // FILTER: Only print if line contains the keyword
                        if (line.contains(keyword)) {
                            System.out.println(line);
                        }
                    }
                    reader.close(); // Important: Close file
                    
                } catch (Exception e) {
                    System.out.println("Error reading memory.");
                }
                continue; // Jump back to start (Don't save "search")
            }

            // ===========================
            // OPTION 4: SAVE TO FILE (Default)
            // ===========================
            System.out.println(" >> Memorized: " + text);

            try {
                // 'true' means append (add to end, don't delete old data)
                FileWriter writer = new FileWriter("memory.txt", true);
                
                writer.write(text + "\n"); // Write text + New Line
                writer.close();            // Save and close
                
            } catch (Exception error) {
                System.out.println("Error saving to file!");
            }

        } // End of while loop
    }
}