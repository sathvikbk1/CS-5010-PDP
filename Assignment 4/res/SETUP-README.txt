The StockMarket.jar file is present in the res folder. In order to run the program, the jar file need not be in any specific folder, but can be run on the command prompt as is.
The program assumes that the user enters a valid ticker value and does not enter any ticker that does not exist in the real world, alongside valid number of stocks and price per stock.

Set up to run the jar file:
1) Go to the location of the jar file, in the res folder.
2) Open the command prompt in the location of the jar file.
3) Enter the following command to run the jar file: java -jar <file_name>.jar
	in this case, java -jar StockMarket.jar
4) The program will load and begin by displaying the menu options as detailed below.

The menu options will be displayed as:
	Enter the number corresponding to the operation to be performed: 
	1 - Create a new portfolio
	2 - Show all portfolio
	3 - Show detail of a portfolio
	4 - Check total cost of portfolio
	5 - Check total value of a portfolio
	6 - Check total value of a portfolio by a given date
	7 - Save a portfolio
	8 - Retrieve a portfolio
	q/Q - quit the system

For creating 3 different portfolios:
1) Enter the value "1".
	You should see a menu option something like this:
		Enter the corresponding to the operation to be performed:
		1 - Add a new stock
		f/F - close portfolio
2) Enter the value "1".
	You should then see the following command:
		Enter the stock symbol, number of shares and cost per stock:
3) Enter a valid ticker name, number of shares and the cost per share of the ticker.
4) On clicking enter, you should be able to see the menu options as seen in step 1, namely,
	Enter the corresponding to the operation to be performed:
	1 - Add a new stock
	f/F - close portfolio
5) Enter the value "1".
	You should then see the following command:
		Enter the stock symbol, number of shares and cost per stock:
6) Enter a valid ticker name, number of shares and the cost per share of the ticker.
7) On clicking enter, you should be able to see the menu options as seen in step 1, namely,
	Enter the corresponding to the operation to be performed:
	1 - Add a new stock
	f/F - close portfolio
8) Enter the value "1".
	You should then see the following command:
		Enter the stock symbol, number of shares and cost per stock:
9) Enter a valid ticker name, number of shares and the cost per share of the ticker.
10)On clicking enter, you should be able to see the menu options as seen in step 1, namely,
	Enter the corresponding to the operation to be performed:
	1 - Add a new stock
	f/F - close portfolio
11) Enter the value "f" or "F".
12) It will then prompt for the name of the portfolio as:
		Enter the name for the Portfolio:
13) Enter the name of the portfolio <portfolio_name>
14) This brings up the root menu.
15) Enter 2 to retrieve all the portfolios created.
16) Enter 3 to retrieve a particular portfolio by entering the name of the portfolio.
17) Enter 4 to retrieve the cost of the portfolio.
18) Enter 5 to get the current price/value of the portfolio(as of current date).
19) Enter 6 to get the value of the portfolio for a particular date in the past, 
    in the format yyyy-mm-dd(Eg: 2022-11-02).
20) Entering 7 would save the portfolio in seperate XML files with the name of the file as the name of the portfolio.
21) Entering 8 would retrieve the XML format of the file, given it is of the right format, and parse it into create an object of portfolio type.
22) Enter "q" or "Q" to quit the execution of the program.

To create a portfolio with 2 different stocks, follow the following procedure:
1) Enter the value "1".
	You should see a menu option something like this:
		Enter the corresponding to the operation to be performed:
		1 - Add a new stock
		f/F - close portfolio
2) Enter the value "1".
	You should then see the following command:
		Enter the stock symbol, number of shares and cost per stock:
3) Enter a valid ticker name, number of shares and the cost per share of the ticker.
4) On clicking enter, you should be able to see the menu options as seen in step 1, namely,
	Enter the corresponding to the operation to be performed:
	1 - Add a new stock
	f/F - close portfolio
5) Enter the value "1".
	You should then see the following command:
		Enter the stock symbol, number of shares and cost per stock:
6) Enter a valid ticker name, number of shares and the cost per share of the ticker.
7) Enter 5 to get the current price/value of the portfolio(as of current date).
8) Enter 6 to get the value of the portfolio for a particular date in the past, 
    in the format yyyy-mm-dd(Eg: 2022-11-02).
9) Entering 7 would save the portfolio in seperate XML files with the name of the file as the name of the portfolio.
10) Enter "q" or "Q" to quit the execution of the program.