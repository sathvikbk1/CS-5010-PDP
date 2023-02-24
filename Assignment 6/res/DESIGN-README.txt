The program is designed in the Model, Controller, View design. 
The basic functionalities of the program are handled by the model, while the interactions with the user interactions are handled by the view
and the controller overlooks and transfers control between the model and the view to perform these operations.

The Model includes:
	1) An API call package which consists of the interface and the class that calls AlphaVantage API and
	   retrieves the data from the API for a particular ticker in csv format and parses it a HashMap, 
	   which holds only the date and the closing value of the ticker on each of those days.
	2) A file package which consists of the interface and the class that is called to save a portfolio
	   as an XML file in a certain format, where the name of the file is the name of the portfolio, 
	   and to retrieve the portfolio from the XML format to an object of portfolio class, given the XML format is of compatible type.
	   This implemenatation involves an abstract class, a class for saving and retrieving files of inflexible portfolio 
	   and another class to handle the same operations for the flexible portfolio.
	3) A portfolio package which consists of the interface and the class that creates and mimics a portfolio
	   that is capable of storing multiple stocks in a list format, mapped to the name of the portfolio.
	   This implemenatation involves an abstract class, a class for mimicing an inflexible portfolio 
	   and another class to handle the same operations for the flexible portfolio.
	4) A stock package which consists of the interface and the class that create and mimics the storage format of 
	   the stock that is capable of storing the ticker name, number of stocks and cost of each stock, 
	   and additionally the date of transaction and commission in the case of stocks pertaining to flexible portfolio, 
	   in an object of the stock class. This implemenatation involves an abstract class, a class for mimicing stocks in an 
	   inflexible portfolio and another class to handle the same operations for the flexible portfolio.
	5) Changes made to the model includes the addition of a function namely, dollar cost averaging which allows the user to recurringly 
	   make investments on a fixed set of stocks with a certain strategy in mind. The information required for the same are acquired during the 
	   creation of the strategy. Additionally, this strategy is added to the contents of the portfolio over time, when executed.

The Controller includes:
	An interface and a class that handles and overlooks all operations handled by the program.
	It possesses the methods that are responsible for recieving the commands passed by the user, 
	passing them to appropriate classes in model, recieve the results from the model and display
	the results to the display screen through the view.
	This process continues till the program ends.
	The new controller introduced into the program allow for a graphical user interfacem and is used to deal with Action commands.
	The controller based on Action Commands, will decide which view to open and which function to run (inorder to get and display information).
	The changes made to the controller includes the addition of menu option and a helper function to handle the creation of investment plans.
  
The View includes:
	An interface and a class that handles what is to be displayed to the user. The different methods that
	display the appropriate messages to the output screen are called at appropriate times by the controller.
	The view does not handle any computation or results.
	A set of views are created for each action to be performed on the Stock Market application. 
	There exists different views for different functionalities, all linked together and disposed off once their use is served.
  	Some of the views take in user-inputs inorder to perfrom the appropriate actions. Te input is taken in from the views through a JTextfield.