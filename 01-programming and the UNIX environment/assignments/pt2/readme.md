# COSC110 - ASSIGNMENT 2

This program is a solution for the good people at Codetown burger co. In an effort to update their UI, they
need a way to be able to better understand their customers purchasing habbits. This program will help with
this problem and all that is needed is a list of burgers ordered in the form a correctly formatted file. 
The program will read this file and provide the user with the amount of times this burger was ordered and 
the cost of this burger. Hopefully after studying these outputs the good people of Codetown Burger will be able
to develop a menu that makes sense for both them and their customers.

## How to launch
Start the program by running: 
```
'python3 order.py'
```

##How to use
- First you need to supply the program with a file named "orders.txt" failure 
to do so will result in the program prompting you to check your file and then exiting.
- Secondly that file must follow the correct formatting of (bun, sauce, number of patties, number of cheese slices, 
tomato, lettuce, onion) where bun and sauce are strings from the menu, patties and cheese are inegters between 0 - 3
and the salad options are either "yes" or "no" 
- If correct file has been provided and the formatting is correct, you will be asked to enter the number of unique 
orders you would like to query
- The number you input must be a positive integer
- If you're input is anything else, you will be asked to resubmit.
- If the number of orders you wish to query is larger than the amount in the data-set
it will simply provide all the orders availble in the data-set

The program will then fetch all orders that satisfy the users request and print them to the terminal in decending 
order in the following format: the type of order, the amount of times it was ordered and the price of the order, 
It will then exit.

## Example Interactions

When asking for the 4 top orders in the file:

- How many top burger orders would you like to know? 4
- ('milk', 'barbecue', 3, 3, True, True, True) 	 4 	 $15
- ('milk', 'tomato', 2, 1, True, False, False) 	 2 	 $8
- ('gluten free', 'barbecue', 1, 0, False, True, False) 	 2 	 $6

When asking for just the top order in the file:
- How many top burger orders would you like to know? 1
- ('milk', 'barbecue', 3, 3, True, True, True) 	 4 	 $15

If the file provided has incorrectly formatted: 

- Error Reading the data
- Please ensure each line of orders.txt starts with the bun type (milk or gluten free), followed by a comma, then 
the sauce type (tomato, barbecue or none), followed by a comma, then the number of patties (0-3), followed by a 
comma, then the number of slices of cheese (0-3), followed by a comma, then whether tomato is included 
(yes or no), followed by a comma, then whether lettuce is included (yes or no), followed by a comma, then whether 
onion is included (yes or no).

If the file you provided is named incorrectly or not in the correct directory:

- Please check the file you have provided is named "orders.txt" and is in the same directory as this program.


## Contact Information
- Author: 
- Email: 
