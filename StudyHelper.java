import java.util.Scanner;
import java.util.ArrayList; 
import java.io.File;        
import java.io.FileWriter;  
import java.io.IOException;
public class StudyHelper{
    public static void main(String[] args){
        System.out.println("Advance Study Helper");
        System.out.println("Enter Your Notes Here:");
        Scanner sc=new Scanner(System.in);
        String Foldername="";
        while(true){
        String usernotes= sc.nextLine();
        if(usernotes.trim().equalsIgnoreCase("exit")){
            System.out.println("Exiting");
            break;
        }
        if(usernotes.trim().isEmpty()){
            continue;
        }

        //Feature 3:
        if(usernotes.trim().startsWith("#")){
         Foldername= usernotes.replace("#","").trim();
        File folder=new File(Foldername);
        if(!folder.exists()){
            folder.mkdir();
            System.out.println("Folder Created: "+ Foldername);
        }else{
            System.out.println("Folder Already Exists: "+ Foldername);
        }
        continue;
        }

        //Feature 1:
        if(usernotes.trim().isEmpty()){
            System.out.println("No Notes Entered");
        }else{
            System.out.println("Processing Your Notes...");
             String[] notesArray=usernotes.trim().split(" ");
             String stopWords = " the is an a of in to and items for on with directly ";
             ArrayList <String> keytags=new ArrayList<>();
             for(int i=0;i<notesArray.length;i++){
                String word= notesArray[i].toLowerCase().trim();
                if(!word.isEmpty() && !stopWords.contains(" "+ word +" ")){
                    keytags.add(word);
                }
             }
             
             // FEATURE 4: (Placed right after keywords are fully collected)
             if (!keytags.isEmpty()) {
                try {
                    FileWriter csvWriter = new FileWriter("study_metrics.csv", true);
                    String folderLabel = Foldername.isEmpty() ? "General" : Foldername;
                    String keywordsString = String.join(" ", keytags);
                    csvWriter.write(folderLabel + "," + keywordsString + "\n");
                    csvWriter.close();
                    System.out.println("📊 Keywords logged for metrics pipeline.");
                } catch (IOException e) {
                    System.out.println("Error writing to metrics file.");
                }
             }

             // Feature 2:
             if(usernotes.contains("-") || usernotes.contains(":")){
                try{
                    FileWriter wr=new FileWriter("FlashCards.txt",true);
                    String[] parts= usernotes.split("[-:]");
                    if(parts.length>=2){
                    String Question= parts[0].trim();
                    String Answer= parts[1].trim();
                    wr.write("Q:"+ Question+ "|A:"+ Answer +"\n");
                System.out.println("FlashCard generated in FlashCards.txt");
            }
            wr.close();
                }
                catch(IOException e){
                    System.out.println("Error Writing to File");
                }
             }
            }
             System.out.println("Notes Processed Successfully");
        }
        sc.close();
    }
}