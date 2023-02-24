The StockMarket.jar file is present in the res folder. In order to run the program, the jar file need not be in any specific folder, but can be run on the command prompt as is.
The program assumes that the user enters a valid ticker value and does not enter any ticker that does not exist in the real world, alongside valid number of stocks and price per stock.
We have made use of the third party library gson to store and persist the portfolio in memory. The licences for the same can be found in the link below.
https://github.com/google/gson/blob/master/LICENSE
When using the graphical user interface, it is expected that the user clears the text existing in the text field and enter the actual values.
The text fields contain the information only for helping the user know the format and information being gathered through that text field.

Set up to run the jar file:
1) Go to the location of the jar file, in the res folder.
2) Open the command prompt in the location of the jar file.
3) Enter the following command to run the jar file: java -jar <file_name>.jar [argument]
	in this case, java -jar StockMarket.jar gui for a graphical user interface and 
	java -jar StockMarket.jar for a text based interface. The argument mentioned above is optional,
	by default it is assumed to go to the text based interface.
4) The program will load and begin by displaying the menu options as detailed below.

The menu options will be displayed as:
	Enter the type of portfolio to be created:
		1 - Inflexible Portfolio
		2 - Flexible portfolio
Upon entering the type of portfolio to be created, you should see something like below in case 1 was entered:
	Enter the number corresponding to the operation to be performed: 
	1 - Create a new portfolio
	2 - Show all portfolio
	3 - Show detail of a portfolio
	4 - Check total cost of portfolio
	5 - Check total cost of portfolio on a given date
	6 - Check total value of a portfolio
	7 - Check total value of a portfolio on a given date
	8 - Save a portfolio
	9 - Retrieve a portfolio
	q/Q - quit the system

For creating 3 different stocks:
1) Enter the value "1".
	You should see a menu option something like this:
		Enter the corresponding to the operation to be performed:
		1 - Add a new stock
		f/F - close portfolio
2) Enter the value "1".
	You should then see the following command:
		Enter the stock symbol: 
	followed by,
		Enter the number of shares: 
	followed by,
		Enter the cost per stock:
3) Enter a valid ticker name, number of shares and the cost per share of the ticker.
4) On clicking enter, you should be able to see the menu options as seen in step 1, namely,
	Enter the corresponding to the operation to be performed:
	1 - Add a new stock
	f/F - close portfolio
5) Enter the value "1".
	You should then see the following command:
		Enter the stock symbol: 
	followed by,
		Enter the number of shares: 
	followed by,
		Enter the cost per stock:
6) Enter a valid ticker name, number of shares and the cost per share of the ticker.
7) On clicking enter, you should be able to see the menu options as seen in step 1, namely,
	Enter the corresponding to the operation to be performed:
	1 - Add a new stock
	f/F - close portfolio
8) Enter the value "1".
	You should then see the following command:
		Enter the stock symbol: 
	followed by,
		Enter the number of shares: 
	followed by,
		Enter the cost per stock:
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
18) Enter 5 to retrieve the cost of the portfolio on a particular date in the past.
19) Enter 6 to get the current price/value of the portfolio(as of current date).
20) Enter 7 to get the value of the portfolio for a particular date in the past, 
    in the format yyyy-mm-dd(Eg: 2022-11-02).
21) Entering 8 would save the portfolio in seperate XML files with the name of the file as the name of the portfolio.
22) Entering 9 would retrieve the XML format of the file, given it is of the right format, and parse it into create an object of portfolio type.
23) Enter "q" or "Q" to quit the execution of the program.

To create a portfolio with 2 different stocks and of flexible type, follow the following procedure:

The menu options will be displayed as:
	Enter the type of portfolio to be created:
		1 - Inflexible Portfolio
		2 - Flexible portfolio
Upon entering the type of portfolio to be created, you should see something like below in case 2 was entered:
	Enter the number corresponding to the operation to be performed:
		1   - Create a new portfolio
		2   - Show all portfolio
		3   - Show detail of a portfolio
		4   - Check total cost of portfolio
		5   - Check total cost of portfolio on a given date
		6   - Check total value of a portfolio
		7   - Check total value of a portfolio on a given date
		8   - Save a portfolio
		9   - Retrieve a portfolio
		10  - Load a portfolio
		11  - Remove a portfolio
		12  - Plot the portfolio
		q/Q - quit the system
1) Enter the value "1".
	You should see a menu option something like this:
		Enter the corresponding to the operation to be performed:
			1 - Add a new stock
			2 - Sell an existing stock
			f/F - close portfolio
2) Enter the value "1".
	You should then see the following command:
		Enter the stock symbol: 
	followed by,
		Enter the number of shares: 
	followed by,
		Enter the cost per stock:
	followed by,
		Enter the custom date in yyyy-mm-dd format: 
	followed by,
		Enter the commission: 
3) Enter a valid ticker name, number of shares, cost per share of the ticker, date of transaction and commission.
4) Enter the value "1".
	You should see a menu option something like this:
		Enter the corresponding to the operation to be performed:
			1 - Add a new stock
			2 - Sell an existing stock
			f/F - close portfolio
5) Enter the value "1".
	You should then see the following command:
		Enter the stock symbol: 
	followed by,
		Enter the number of shares: 
	followed by,
		Enter the cost per stock:
	followed by,
		Enter the custom date in yyyy-mm-dd format: 
	followed by,
		Enter the commission: 
6) Enter a valid ticker name, number of shares, cost per share of the ticker, date of transaction and commission.
7) Enter the value "f" or "F".
8) It will then prompt for the name of the portfolio as:
		Enter the name for the Portfolio:
9) Enter the name of the portfolio <portfolio_name>
10) This brings up the root menu.
11) All the operations function in the same manner as in inflexible portfolio, for the common functionalities.
12) Entering 10 would load a previously added portfolio, so that modifications to that portfolio could be performed by prompting
		Enter the name for the Portfolio: 
13) Entering 11 would remove/delete the portfolio, if it exists in memory by prompting
		Enter the name for the Portfolio: 
14) Entering 12 would prompt something like
		Enter the name for the Portfolio: 
	after entering which would prompt for the start date and end date as,
		Enter start date as 
		Enter the custom date in yyyy-mm-dd format:
	and
		Enter end date as
		Enter the custom date in yyyy-mm-dd format:
   This would then display a bar chart based on the performance of the portfolio over the given period of time.
15) Enter "q" or "Q" to quit the execution of the program.