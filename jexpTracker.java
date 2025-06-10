import java.io.File; // Import the File class
import java.io.FileWriter; // Import the FileWriter Class
import java.util.ArrayList;
import java.util.Scanner; // Import the Java Scanner Class
import java.io.IOException; // Import the IOException class to handle errors
import java.util.ArrayList; // Import the ArrayList Utility Class

class jexpTracker {
  public static void main(String[] args) {
    File expenseSheet = new File("expenses.txt"); // Filename to open

    System.out.println("Attempting to create expense sheet txt file.");

    try {
      if (expenseSheet.createNewFile()) {
        System.out.println("Expense Sheet Created!");
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("Error creating new expense sheet");
      e.printStackTrace();
    }

    Scanner textInput = new Scanner(System.in);
    Scanner costInput = new Scanner(System.in);
    String textToWrite = "";
    double costToWrite = 0.0;
    double totalExp = 0.0;
    ArrayList<String> expenseList = new ArrayList<String>();
    ArrayList<Double> costList = new ArrayList<Double>();

    if (expenseSheet.canWrite() == true) {
      try {
        FileWriter expenseSheetWritable = new FileWriter(expenseSheet); // Init FileWriter to a writable text file
        Scanner scnr = new Scanner(expenseSheet); // Init scanner to read text file
        // System.out.println("File is accessible!\n"); // DEBUG

        while (!textToWrite.equals("/")) {
          System.out.println("\nEnter an expense name.\nEnter '/' to end input.\n"); // Collect User input to enter into
          // the
          // text file
          textToWrite = textInput.nextLine();
          if (textToWrite.equals("/")) {
            break;
          } else {
            expenseList.add(textToWrite);
            expenseSheetWritable.write(textToWrite + ": ");
          }
          System.out.println("Enter the monthly cost in $\n");
          costToWrite = costInput.nextDouble();
          costList.add(costToWrite);
          totalExp += costToWrite;
          expenseSheetWritable.write(Double.toString(costToWrite) + " ");
        }

        // System.out.println("Writing to file!\n"); // DEBUG
        System.out.println("\n----- MONTHLY EXPENSES BREAKDOWN -----\n");

        expenseSheetWritable.close(); // Close the text file

        for (int i = 0; i < expenseList.size(); i++) { // Loop the text file and output its contents
          System.out.println(expenseList.get(i));
          System.out.print("-> $");
          System.out.printf("%.2f", costList.get(i));
          System.out.println();
        }
        System.out.println("----- TOTAL MONTHLY EXPENSE AMOUNT -----");
        System.out.print("$");
        System.out.printf("%.2f", totalExp);
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
