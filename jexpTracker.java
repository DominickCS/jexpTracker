import java.io.File; // Import the File class
import java.io.FileWriter; // Import the FileWriter Class
import java.util.Scanner; // Import the Java Scanner Class
import java.io.IOException; // Import the IOException class to handle errors

class jexpTracker {
  public static void main(String[] args) {
    File expenseSheet = new File("expenses.txt"); // Filename to open
    Scanner textInput = new Scanner(System.in);
    Scanner costInput = new Scanner(System.in);
    String textToWrite = "";
    double costToWrite = 0.0;
    if (expenseSheet.canWrite() == true) {
      try {
        FileWriter expenseSheetWritable = new FileWriter(expenseSheet); // Init FileWriter to a writable text file
        Scanner scnr = new Scanner(expenseSheet); // Init scanner to read text file
        // System.out.println("File is accessible!\n"); // DEBUG
        if (!scnr.equals("")) {
          System.out.println("File is not empty, clearing...\n");
          expenseSheetWritable.write(""); // Init file, empty contents
        }

        while (!textToWrite.equals("/")) {
          System.out.println("Enter an expense name.\nEnter '/' to end input.\n"); // Collect User input to enter into
                                                                                   // the
          // text file
          textToWrite = textInput.nextLine();
          if (textToWrite.equals("/")) {
            break;
          } else {
            expenseSheetWritable.write(textToWrite + ": ");
          }
          System.out.println("Enter the monthly cost in $");
          costToWrite = costInput.nextDouble();
          expenseSheetWritable.write(Double.toString(costToWrite));
        }

        // System.out.println("Writing to file!\n"); // DEBUG
        System.out.println("\n----- MONTHLY EXPENSES -----\n");

        expenseSheetWritable.close(); // Close the text file

        while (scnr.hasNext()) { // Loop the text file and output its contents
          String word = scnr.nextLine();
          System.out.print(word);
          System.out.println();
        }
        textInput.close();
        costInput.close();
        scnr.close();

      } catch (IOException e) {
        System.out.println("An error occurred");
        e.printStackTrace();
      }
    } else {
      System.out.println("File could not be accessed.");
    }
  }
}
