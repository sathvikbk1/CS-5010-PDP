Employing the MVC approach, mimicing a platform that holds stock data and portfolios, 
the basic functionalities required for the same have been successfully implemented.

The features of the program that are tested to be working are as follows:
1) Allow the creation of either a flexible portfolio or an inflexible portfolio
2) Create 0 or more portfolios
3) Add 1 or more stocks to the portfolio, and additionally be able to sell a stock if it is a flexible portfolio
4) View the contents of all portfolios
5) View contents of a specific portfolio
6) Retrieve the total cost of the portfolio
7) Retrieve the total cost of the portfolio on a particular date
8) Retrieve the current value of the portfolio
9) Retrieve the value of the portfolio on a particular date(valid date where the stock value for that date is available)
10) Save each of the portfolios as a seperate XML file, based on a specific format(name of the file -> name of the portfolio)
11) Retrieve the portfolio from an XML file and convert it to an object of portfolio class, given the format of the XML file is of compatible type
12) Load an existing portfolio and make modifications to its composition, given that the portfolio is of flexible type
13) Remove an existing portfolio, given that the portfolio is of flexible type
14) Visualize the portfolio in terms of a bar chart, only possible if the portfolio is a flexible one
15) Sell 1 or more stocks already existent in the portfolio.
16) Create an investment strategy by adding the stocks, their weightage and the corresponding commissions for each transactoion.
17) The program can be run with both a graphical user inter and a text based interface.