"""
File: orders.py
Author: Mokoto K
Date: 02/04/2024
Description: A program for reading a file of burger orders and printing out the orders and their total costs.
"""

error_message = "Please ensure each line of orders.txt follows this structure" \
                "((milk or gluten free),(tomato, barbecue or none),(0-3),(0-3),(yes or no),(yes or no),(yes or no))."

# A dictionary used for checking that the file provided is correctly structured. The keys are integers so that they
# can be iterated over in a loop to individually check all elements in each given line and the values are lists of
# correct input corresponding to those keys.
items = {
    0: ['milk', 'gluten free'],
    1: ['tomato', 'barbecue', 'none'],
    2: ['0', '1', '2', '3'],
    3: ['0', '1', '2', '3'],
    4: ["yes", "no"],
    5: ["yes", "no"],
    6: ["yes", "no"],
}


def convert_to_tuple(order):
    """Converts a list of values into a tuple of values.

    Takes a list (order) which are all strings and initially checks each element in that list against a dictionary
    of valid values. If an elements value does not match, this function will return "None", otherwise it will
    convert each element into the desired datatype and return a tuple of all values.

    params
    order : List of strings

    returns
    None
    Tuple
    """
   
    for item in range(len(order)):
        # A check for correct formatting of file against the provided dictionary of expected values.
        if order[item] not in items[item]:
            print("Error Reading the data")
            return None

        # The item in the list will become True if it is "yes" or False if it is "no", otherwise it will remain as is
        # if its value is neither. This is to deal with converting the salad options into booleans
        order[item] = True if order[item] == "yes" else False if order[item] == "no" else order[item]

    return order[0], order[1], int(order[2]), int(order[3]), order[4], order[5], order[6]


def create_dictionary(f):
    """Reads a file and creates a sorted dictionary of keys and values.

    Takes a File f, opens it then iterates through it sending each line to the convert_to_tuple function and
    receives either None or a Tuple in return. If None is returned it returns None to the original call, otherwise
    it adds the Tuple to a dictionary with the value being how many times that Tuple has appeared in the file, which
    is then sorted into descending order and return as a list.

    param
    f : File, a .txt file provided

    returns
    None, or
    A sorted list of tuples in descending order
    """

    d = {}

    with open(f) as fin:

        for line in fin:
            # Assigns key as a call to convert_to_tuple func passing it a line from the file.
            key = convert_to_tuple(line.strip().split(","))

            # If the line passed does not match expected inputs, then it will return None causing None to be return here
            # prompting the user to check that the file they provided is correct. If the input is correct, the line will
            # be added to a dictionary as a key and the value will be incremented by 1 for every identical key provided
            if key is None:
                print(error_message)
                return None
            d[key] = d.get(key, 0) + 1

    # Returns a list of tuples with the key value pairs from the dictionary switched and sorted in decending order
    return sorted(((value, key) for key, value in d.items()), reverse=True)


def get_user_input():
    """Will persistently ask a question until answered in correct format

    Asks user for input to a question and returns the answer if it is a positive integer, otherwise it
    loops until one is provided.

    returns
    User input : integer > 0
    """

    while True:
        try:
            number_of_orders = int(input('How many top burger orders would you like to know? '))
        except ValueError:
            print("Invalid value, Please make sure your entering an integer greater then 0")
        else:
            if number_of_orders > 0:
                print(f"The top {number_of_orders} burger orders were:")
                return number_of_orders
            else:
                print("Integer was not positive, please try again")


def get_cost(t):
    """Calculates the total cost for a given Tuple

    Takes a Tuple (t) as input and works through each item in the Tuple, appending price depending on the items
    value. Returns the total price

    param
    t : Tuple containing a customers order

    returns
    Total price of the order
    """

    price, salad_count = 5, 0

    # A series of If else statements comparing each item from the order against fixed values to determine the price.
    price += 1 if t[0] == 'gluten free' else 0
    price += (t[2] - 1) * 3 if t[2] > 1 else 0  # Math to find the correct cost of more than 1 pattie
    price += t[3] - 1 if t[3] > 1 else 0        # Math to find the correct cost of more than one slices of cheese

    for i in range(4, 7):
        salad_count += 1 if t[i] else 0
    # This adds up all pieces of salad and only charges user if they go over 1 piece.
    price += salad_count - 1 if salad_count > 1 else 0

    return price


def print_orders(f):
    """Prints an order from a given file as well as how many times it was ordered and the total cost for that order

    Takes a File (f) and calls the create_dictionary function passing it the file, if the return is None then it returns
    None prompting the user to check the file provided is correct, otherwise it returns a dictionary of orders. Next it
    calls the get_user_input function which returns a positive integer from the user and iterates through the dictionary
    that integer amount of times. Finally it calls the get_cost function printing the return to the terminal along with
    the contents of the order and how many times it has been ordered.

    param
    f : File

    returns
    None
    """

    d = create_dictionary(f)
    if d is None:
        return None

    try:
        for num in range(get_user_input()):
            order, amount = d[num][1], d[num][0]
            cost = get_cost(order)
            print(f"{order} \t {amount} \t ${cost}")

    except IndexError:
        # Although it is not ideal to have an error be passed, in this case we don't want anything to happen if the
        # index error is present. We want to print the maximum amount of orders if the user inputs an amount larger than
        # the size of the list, printing an error or returning anything else will interrupt our intended programming.
        pass


def main():
    """The main launcher of the program which provides additional checks for any user error"""

    # Checks to make sure the file isn't empty, is correctly named and is in the correct directory
    try:
        print("File contains no data") if open("orders.txt").readline() == "" else print_orders("orders.txt")
    except FileNotFoundError:
        print('Check the file you have provided is named "orders.txt" and is in the same directory as this program.')


if __name__ == "__main__":
    main()

