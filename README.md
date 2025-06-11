# jexpTracker

A Java CLI application that allows you to track personal expenses.
The application provides multi-view budgets and automatically creates
a delimited CSV file for easy importing into other budgeting software.

## --- Usage Instructions ---

### - Clone -

`git clone https://github.com/DominickCS/jexpTracker.git`

### - Compile -

Inside the cloned directory run the following command:

`javac jexpTracker.java`

### - Run -

Run the following command to execute the application:

`java jexpTracker`

#### - TO-DO -

- [x] Incorporate secondary input stream for expenses -> 06/10/25
- [ ] Main Menu with options to create new list, view/edit existing, and delete
- [x] Add expense data to two separate arrays for better outputting. 06/10/25
- [x] If "expenses.csv" exists, import data into arraylist. 06/11/25
- [ ] Ask User for income to provide salary remainder
- [ ] Provide different expense views (Weekly, Semi-Monthly, Monthly, or All)
