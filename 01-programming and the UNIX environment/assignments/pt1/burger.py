# Dictionary of all questions, items and prices to help minimise clutter in the 
#body of the code.
items = {'bun': {'val': {'milk': 0, 'gluten free': 1}, 'q': 'What type of bun for burger {b} - milk or gluten free? '},
         'sauce':{'val':{'tomato':0,'barbecue':0,'none':0},'q':"What sauce for burger {b} - tomato, barbecue or none? "},
         'patties': {'val': {'0': 0, '1': 0, '2': 3, '3': 6}, 'q': "How many {c} for burger {b} - 0-3? "},
         'cheese': {'val': {'0': 0, '1': 0, '2': 1, '3': 2}, 'q': "How many slices of {c} for Burger {b} - 0-3? "},
         'tomato': {'val': {'yes': 1, 'no': 0}, 'q': "Should Burger {b} have {c} - yes or no? "},
         'lettuce': {'val': {'yes': 1, 'no': 0}, 'q': "How about {c} for burger {b} - yes or no? "},
         'onion': {'val': {'yes': 1, 'no': 0}, 'q': "Would you like {c} on burger {b} - yes or no? "},
         'burgers': {'val': {'1': 1, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, 
'7': 7, '8': 8, '9': 9, '10': 10},
         'q': 'How many burgers would you like today - 1-10?: '}}

# Primarily this asks all the questions, either how many burgers to order or 
# what item for each burger. Checks input
# against the dictionary and returns the approiate value. If input is incorrect, 
# it will prompt user again.
def get_order(choice, burger_id):
    while True:
        answer = input(items[choice]['q'].format(b=burger_id, c=choice)).lower()
        if answer in items[choice]['val']:
            return items[choice]['val'][answer]
        else:
            print(answer, 'is an invalid option, please try again')

print('Welcome to Codetown Burger Co.')
burgers = get_order('burgers', 0)
price = burgers * 5
for num in range(burgers):
    print('Details for burger ', num + 1)
    salad_count = 0
    # Loops through the items dictionary and calls the get_order function 
    # passing it the dictionary, the item and the
    # burger number we are on. It then adds the return of the function to either 
    # the price or the salad counter.
    for i in items:
        if i == 'burgers':
            pass
        elif i.startswith(('t', 'l', 'o')):
            salad_count += get_order(i, num + 1)
        else:
            price += get_order(i, num + 1)
    # Keeps track of how much salad is on a burger, will only add to price if 
    # there is > one piece
    if salad_count > 1:
        price += salad_count - 1  # Subtracts the one free piece of salad to 
                                  # ensure we don't overcharge.

print(f"Your total for {burgers} burger(s) is:  ${price}")

