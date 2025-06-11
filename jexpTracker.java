import java.io.BufferedWriter;
import java.io.File; // Import the File class
import java.io.FileWriter; // Import the FileWriter Class
import java.util.ArrayList; // Import the ArrayList Class for data storage
import java.util.Scanner; // Import the Java Scanner Class
import java.io.IOException; // Import the IOException class to handle errors

class jexpTracker {
  public static ArrayList<String> expenseList = new ArrayList<String>();
  public static ArrayList<Double> costList = new ArrayList<Double>();
  public static double userIncome;

  public static void editExpenseSheet(File expenseSheet, double userIncome) {
    Scanner textInput = new Scanner(System.in);
    Scanner costInput = new Scanner(System.in);
    String textToWrite = "";
    double costToWrite = 0.0;

    if (expenseSheet.canWrite() == true) {
      try {
        FileWriter expenseSheetWritable = new FileWriter(expenseSheet); // Init FileWriter to a writable text file
        BufferedWriter bw = new BufferedWriter(expenseSheetWritable);
        // System.out.println("File is accessible!\n"); // DEBUG
        expenseSheetWritable.write("Expense, Cost"); // Init CSV format
        expenseSheetWritable.write("\n");

        for (int i = 0; i < expenseList.size(); ++i) {
          bw.write(expenseList.get(i));
          bw.write("\n");
          bw.write(Double.toString(costList.get(i)));
          bw.write("\n");
        }

        while (!textToWrite.equals("/")) {
          System.out.println("\nEnter an expense name.\nEnter '/' to end input.\n"); // Collect User input to enter into
          // the
          // text file
          textToWrite = textInput.nextLine();
          if (textToWrite.equals("/")) {
            break;
          } else {
            expenseList.add(textToWrite);
            bw.write(textToWrite + ", \n");
          }
          System.out.println("Enter the monthly cost in $\n");
          costToWrite = costInput.nextDouble();
          costList.add(costToWrite);
          bw.write(Double.toString(costToWrite));
        }
        Scanner scnr = new Scanner(expenseSheet); // Init scanner to read text file
        while (scnr.hasNextLine()) {
          String currLine = scnr.nextLine();
          bw.write(currLine);
        }
        bw.close();
        scnr.close();
        expenseSheetWritable.close();

        getExpenseBreakdown(userIncome);

      } catch (IOException e) {
        System.out.println("An error occurred");
        e.printStackTrace();
      }
    } else {
      System.out.println("File could not be accessed.");
    }
  }

  // New Expense Sheet Creation Method
  public static void populateExpenseSheet(File expenseSheet, double userIncome) {
    Scanner textInput = new Scanner(System.in);
    Scanner costInput = new Scanner(System.in);
    String textToWrite = "";
    double costToWrite = 0.0;

    if (expenseSheet.canWrite() == true) {
      try {
        FileWriter expenseSheetWritable = new FileWriter(expenseSheet); // Init FileWriter to a writable text file
        expenseSheetWritable.write("Expense, Cost"); // Init CSV format
        expenseSheetWritable.write(" ");
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
            expenseSheetWritable.write("\n");
            expenseSheetWritable.write(textToWrite + ", \n");
          }
          System.out.println("Enter the monthly cost in $\n");
          costToWrite = costInput.nextDouble();
          costList.add(costToWrite);
          expenseSheetWritable.write(Double.toString(costToWrite));
        }
        expenseSheetWritable.close();
        scnr.close();

        getExpenseBreakdown(userIncome);

      } catch (IOException e) {
        System.out.println("An error occurred");
        e.printStackTrace();
      }
    } else {
      System.out.println("File could not be accessed.");
    }
  }

  public static void importData(File expenseSheet) {
    int i = 0;

    try {
      Scanner fileReader = new Scanner(expenseSheet);
      // System.out.println("----- CURRENT EXPENSE SHEET CONTENTS -----");
      fileReader.nextLine();

      while (fileReader.hasNextLine()) {
        if (i % 2 == 0) {
          String currLine = fileReader.nextLine();
          // System.out.println(currLine);
          expenseList.add(currLine);
        } else if (i % 2 != 0) {
          String currLine = fileReader.nextLine();
          // System.out.println(currLine);
          costList.add(Double.parseDouble(currLine));
        }
        ++i;
      }
      fileReader.close();

      // System.out.println("Printing contents of lists:\n");
      // for (int j = 0; j < expenseList.size(); ++j) {
      // System.out.println(expenseList.get(j));
      // System.out.println(costList.get(j));
      // }
    }

    catch (IOException e) {
      System.out.println("Error in the importData method.");
      e.printStackTrace();
    }
  }

  public static void getExpenseBreakdown(double userIncome) {
    double totalExp = 0.0;
    for (int i = 0; i < costList.size(); ++i) {
      totalExp += costList.get(i);
    }

    // System.out.println("Writing to file!\n"); // DEBUG
    System.out.println("\n----- MONTHLY EXPENSES BREAKDOWN -----\n");

    for (int i = 0; i < expenseList.size(); i++) { // Loop the text file and output its contents
      System.out.println(expenseList.get(i));
      System.out.print("-> $");
      System.out.printf("%.2f", costList.get(i));
      System.out.println();
    }
    System.out.println("\n----- TOTAL MONTHLY EXPENSE AMOUNT -----");
    System.out.print("$");
    System.out.printf("%.2f", totalExp);
    System.out.println(" ");

    double incomeRemainder = ((userIncome / 12) - totalExp);

    System.out.println("----- MONTHLY INCOME REMAINDER -----");
    System.out.print("$");
    System.out.printf("%.2f", incomeRemainder);
    System.out.println(" ");
    System.out.println("----- BI-WEEKLY INCOME REMAINDER -----");
    System.out.print("$");
    System.out.printf("%.2f", (incomeRemainder / 2));
    System.out.println(" ");
    System.out.println("----- WEEKLY INCOME REMAINDER -----");
    System.out.print("$");
    System.out.printf("%.2f", (incomeRemainder / 4));
    System.out.println(" ");

  }

  public static void createFile(double userIncome) {
    System.out.println("Attempting to create expense sheet file.");
    File expenseSheet = new File("expenses.csv"); // Filename to open
    try {
      expenseSheet.createNewFile();
      Scanner expSheetReader = new Scanner(expenseSheet); // Read expense sheet to determine contents
      if (expenseSheet.createNewFile()) {
        System.out.println("Expense Sheet Created!");
        populateExpenseSheet(expenseSheet, userIncome);
      } else if (!expSheetReader.hasNextLine()) {
        System.out.println("Empty expense sheet detected.\n");
        populateExpenseSheet(expenseSheet, userIncome);
      } else {
        System.out.println("File already exists would you like to edit " + expenseSheet + "?\nY/N\n");
        Scanner userInput = new Scanner(System.in);
        String userResponse = userInput.next();
        if (userResponse.charAt(0) == 'Y' || userResponse.charAt(0) == 'y') {
          importData(expenseSheet);
          editExpenseSheet(expenseSheet, userIncome);
        } else if (userResponse.charAt(0) == 'N' || userResponse.charAt(0) == 'n') {
          importData(expenseSheet);
          getExpenseBreakdown(userIncome);
        }
      }
      expSheetReader.close();
    }

    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void setIncome() {
    Scanner incomeInput = new Scanner(System.in);
    System.out.println("Enter your Annual Salary.");
    userIncome = incomeInput.nextDouble();
  }

  public static double getIncome() {
    return userIncome;
  }

  public static void main(String[] args) {
    System.out.println("Welcome to J-ExpTracker!");
    System.out.println("=== MAIN MENU ===\nType your selection to start\n");
    System.out.println("1. (E)xpense Tracking\n2. (Q)uit\n");

    Scanner userOptScnr = new Scanner(System.in);
    if (userOptScnr.hasNext()) {
      String userOpt = userOptScnr.next();
      if (userOpt.charAt(0) == 'E' || userOpt.charAt(0) == 'e') {
        setIncome();
        createFile(getIncome());
      } else if (userOpt.charAt(0) == 'Q' || userOpt.charAt(0) == 'q') {
        return;
      }
    }
  }
}
