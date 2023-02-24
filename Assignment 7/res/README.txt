The procedure to run the program:
Unzip the submitted file and copy the JAR file to desired location in an empty folder.

commands to run the program:
1. java -jar pdp.jar tf
2. java -jar pdp.jar stocker
3. java -jar pdp.jar
Option 3 for the GUI based start-up.

The program can also be run from the Main class, located in the start directory of the src folder.

Completenesss of code:
We were able to implement the re-balancing feature to an existing portfolio. 
We have done so using a few extra method that have been pulled to the interfaces to facilitate the access 
of this method in the model, to both the graphical user interface based and text based controllers.
As the program reads and writes the portfolio into a csv file, the program would have to be re-run in order to view the changes made.
So in order to view the composition of the portfolio after buying/selling shares, the program will have to be re-started for the changes to be reflected.

The changes made to the program includes:
1) 	The addition of a new balancePortfolio method in the FlexibleModelImplementation class to support the balancing of a flexible portfolio on a given date.
   	It does so for both the gui based and text based controllers, after performing the necessary validation checks.
2) 	The addition of the setBalancePortfolio in the features class to support calling the balancePortfolio method in the model.
3) 	A Balance portfolio class under commands of the text-based controller to support the balancing of portfolio feature. 
   	This class performs the necessary validation checks and retreives the inputs from the user.
4) 	The addition of showStockForWeight in the View interface to support retrieving of weight input from user .
5) 	In the homescreeen.form a new JPane for the balancing portfolio was created along with a number of spacers(both vertical and horizontal).
6) 	The JPane contains a JPanel, which contains a JCombobox which is used to select the portfolio name, JTexfield to take the user input for the date, 
   	JTable which is populated with the list of stocks present in the portfolio as soon as a valid portfolio is selected and a JButton which is used to trigger the balancing function.
7) 	In the homescrean.java class the new JCombobox (balanceComboBox1) which take the portfolio name as input is setup.
8) 	JTable (balanceStockJTable) is setup in such a way that as soon as a valid portfolio is selected, the table gets populated with the list of stocks is set up in the homescreen.java class.
9) 	The new JTextfield (balanceDateTextField) which. takes the date as input is also set up in the homescreen.java class.
10)	A new action listener has been setup for the new JButton (balanceButton) for balancing, which is triggered as soon as the button is clicked by the user in the homescreem.java class. 
11)	Error handeling for input has been handled, if the user was to not enter the date or was to submit a stock with weight equal to 0, a error dialog message pertaining to the specific error will be displayed. 
   	This has been setup in the homescreem.java class  