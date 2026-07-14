import java.util.Scanner;
import java.util.ArrayList; 
import java.io.File;         
import java.io.FileWriter;  
import java.io.IOException;

public class StudyHelper {
    public static void main(String[] args) {
        System.out.println("Advance Study Helper");
        System.out.println("Enter Your Notes Here:");
        Scanner sc = new Scanner(System.in);
        String Foldername = "";
        
        while (true) {
            String usernotes = sc.nextLine();
            if (usernotes.trim().equalsIgnoreCase("exit")) {
                System.out.println("Exiting");
                break;
            }
            //Feature 5
            if(usernotes.trim().equalsIgnoreCase("review")){
                try{
                File fr= new File("FlashCards.txt");
                  if(!fr.exists()){
                    System.out.println("No Flashcards Found. Please Create Some First.");
                  }
                  Scanner Filereader= new Scanner(fr);
                  while(Filereader.hasNextLine()){
                    String line=Filereader.nextLine();
                    String[] parts=line.split("\\|");
                    if(parts.length>=2){
                        String question= parts[0].trim();
                        String answer=parts[1].trim();
                        System.out.println("Question:" + "\n"+ question);
                        System.out.println("Press Enter to see the answer...");
                        sc.nextLine();
                        System.out.println("Answer:" + "\n"+ answer);
                    }
                  }   Filereader.close();

                  System.out.println("Returning to Note Taking Mode. Enter Your Notes Here:");
                }catch(IOException e){
                    System.out.println("Error Reading Flashcards");
                }
            }
            if (usernotes.trim().isEmpty()) {
                continue;
            }

            // Feature 3: Folder Management
            if (usernotes.trim().startsWith("#")) {
                Foldername = usernotes.replace("#", "").trim();
                File folder = new File(Foldername);
                if (!folder.exists()) {
                    folder.mkdir();
                    System.out.println("Folder Created: " + Foldername);
                } else {
                    System.out.println("Folder Already Exists: " + Foldername);
                }
                continue;
            }

            // Feature 1 & 4: Note Processing & Keyword Logging
            
                System.out.println("Processing Your Notes...");
                String currentFolder = Foldername.isEmpty() ? "General" : Foldername;
                try{
                    FileWriter notes= new FileWriter(currentFolder + "/notes.txt",true);
                    notes.write(usernotes+"\n");
                    notes.close();
                    System.out.println("Notes Saved " );
                } catch (IOException e) {
                    System.out.println("Notes Could Not Be Saved");
                }
                
                String Cleannotes = "";
                for (int i = 0; i < usernotes.length(); i++) {
                    char ch = usernotes.charAt(i);
                    if (Character.isLetterOrDigit(ch) || ch == ' ') {
                        Cleannotes = Cleannotes + ch;
                    } else {
                        Cleannotes = Cleannotes + " ";
                    }
                }
                
               
                String[] notesArray = Cleannotes.trim().split(" ");
                String stopWords = " the is an a of in to and items for on with directly ";
                ArrayList<String> keytags = new ArrayList<>();
                
                for (int i = 0; i < notesArray.length; i++) {
                    String word = notesArray[i].toLowerCase().trim();
                    if (!word.isEmpty() && !stopWords.contains(" " + word + " ")) {
                        keytags.add(word);
                    }
                }
                 
                // FEATURE 4: Write Clean Keywords to CSV
                if (!keytags.isEmpty()) {
                    try {
                        FileWriter csvWriter = new FileWriter("study_metrics.csv", true);
                       
                        String keywordsString = String.join(" ", keytags);
                        csvWriter.write(currentFolder + "," + keywordsString + "\n");
                        csvWriter.close();
                        System.out.println("📊 Keywords logged for metrics pipeline.");
                    } catch (IOException e) {
                        System.out.println("Error writing to metrics file.");
                    }
                }

                // Feature 2: Flashcard Generation
                if (usernotes.contains("-") || usernotes.contains(":")) {
                    try {
                        FileWriter wr = new FileWriter("FlashCards.txt", true);
                        String[] parts = usernotes.split("[-:]");
                        if (parts.length >= 2) {
                            String Question = parts[0].trim();
                            String Answer = parts[1].trim();
                            wr.write("Q:" + Question + "|A:" + Answer + "\n");
                            System.out.println("FlashCard generated in FlashCards.txt");
                        }
                        wr.close();
                    } catch (IOException e) {
                        System.out.println("Error Writing to File");
                    }
                }
            
            System.out.println("Notes Processed Successfully");
        }
        sc.close();
    }
}