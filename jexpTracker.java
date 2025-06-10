import java.io.File; // Import the File class
import java.io.FileWriter; // Import the FileWriter Class
import java.util.Scanner; // Import the Java Scanner Class
import java.io.IOException; // Import the IOException class to handle errors

class jexpTracker {
  public static void main(String[] args) {
    File expenseSheet = new File("expenses.txt"); // Filename to open
    Scanner textInput = new Scanner(System.in);
    String textToWrite = "";
    if (expenseSheet.canWrite() == true) {
      try {
        FileWriter expenseSheetWritable = new FileWriter(expenseSheet); // Init FileWriter to a writable text file
        Scanner scnr = new Scanner(expenseSheet); // Init scanner to read text file
        System.out.println("File is accessible!\n");
        if (!scnr.equals(""))  {
          System.out.println("File is not empty, clearing...\n");
          expenseSheetWritable.write(""); // Init file, empty contents
          }

        System.out.println("Enter text to input into the file.\n"); // Collect User input to enter into the text file
        while (!textToWrite.equals("/")) {
          textToWrite = textInput.nextLine();
          if (!textToWrite.equals("/")) {
            expenseSheetWritable.write(textToWrite + " ");
          }
        }

        System.out.println("Writing to file!\n");
        System.out.println("FILE CONTENTS:\n");

        expenseSheetWritable.close(); // Close the text file
        
        while (scnr.hasNext()) { // Loop the text file and output its contents
          String word = scnr.next();
          System.out.print(word);
          System.out.println();
        }

      }
      catch (IOException e) {
        System.out.println("An error occurred");
        e.printStackTrace();
      }
    }
    else {
      System.out.println("File could not be accessed.");
    }
  }
}
