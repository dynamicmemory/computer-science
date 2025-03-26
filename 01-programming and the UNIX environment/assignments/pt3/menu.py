"""
File: menu.py
Author: Mokoto K
Date: 02/04/2024
Description: A GUI for displaying the burger menu for Code-town burger co for use in their shops.
"""

from tkinter import Tk, ttk, PhotoImage

# Variable for the global counter used for cycling through the burger options
TIMER = 5

# A dictionary of dictionaries. Main dictionary has 4 keys(each of the burgers on the menu) and the value for each of
# these is another dictionary which contains what is on that burger, ie bun, sauce, cheese.
menu = {
    "Byte Burger": {"bun": "Milk", "sauce": "Tomato", "pattie": "1", "cheese": "No", "tomato": "No",
                    "lettuce": "Yes", "onion": "No", "price": "$5.00"},
    "Ctrl-Alt-Delicious": {"bun": "Milk", "sauce": "Barbecue", "pattie": "2", "cheese": "2", "tomato": "Yes",
                           "lettuce": "Yes", "onion": "No", "price": "$11.00"},
    "Data Crunch": {"bun": "Gluten Free", "sauce": "Tomato", "pattie": "No", "cheese": "No", "tomato": "Yes",
                    "lettuce": "Yes", "onion": "Yes", "price": "$8.00"},
    "Code Cruncher": {"bun": "Milk", "sauce": "Tomato", "pattie": "3", "cheese": "3", "tomato": "Yes",
                      "lettuce": "Yes", "onion": "Yes", "price": "$15.00"},
}


def burgers(button, burger_num):
    """Displays the burger information for whichever button was clicked.

    Takes a String (button) and an integer (burger_num) and uses the string from the button click to search a dictionary
    for the corresponding values for bun type, sauce, etc. and sets this information to the display labels in the gui.
    It then resets the .after method from tkinter to make sure that the previous countdown stops completely and then
    calls the cycle func passing it 5 for the seconds to count down from and which ever burger_num was supplied to this
    function.

    :param button: String, the name of the burger to be passed to the dictionary
    :param burger_num: Integer ranging from 0-3 depending on which burger was clicked or which burger was last cycled

    """

    global TIMER
    name["text"] = button
    bun["text"] = f"{menu[button]['bun']} bun"
    sauce["text"] = f"{menu[button]['sauce']} sauce"
    pattie["text"] = "No meat" if menu[button]['pattie'] == "No" else f"{menu[button]['pattie']} pattie(s)"
    cheese["text"] = "No cheese" if menu[button]['cheese'] == "No" else f"{menu[button]['cheese']} slices of cheese"
    tomato["text"] = "Tomato" if menu[button]['tomato'] == "Yes" else "No Tomato"
    lettuce["text"] = "Lettuce" if menu[button]['lettuce'] == "Yes" else "No Lettuce"
    onion["text"] = "Onion" if menu[button]['onion'] == "Yes" else "No Onion"
    price["text"] = f"Price: {menu[button]['price']}"

    # Chooses which burger picture to display depending on the burger number passed into the function
    if burger_num == 1:
        burger_label["image"] = byte_image
    elif burger_num == 2:
        burger_label["image"] = cad_image
    elif burger_num == 3:
        burger_label["image"] = data_image
    else:
        burger_label['image'] = code_image

    # Cancels the countdown that was occurring before this function was called, without this when this function ends,
    # the previous cycle would finish its count down then the next call to cycle would begin causing the GUI to flick
    # burger displays back-and-fourth erratically. The design choice to include this in this function is due to the
    # need to cycle between changing the display of a burger and counting down again, if not placed here, then in its
    # own function called reset which would be called here anyway, seemed somewhat redundant.
    root.after_cancel(TIMER)
    cycle(5, burger_num)


def cycle(count, burger_num):
    """ Cycles through all the burger options one at a time, waiting 5 seconds before changing to the next burger.

    Takes an integer (burger_num)and an integer (count) which is always set to 5 and using the after method from
    tkinter, loops for 1 second reducing the count by one each loop then calling itself again. If the count is
    greater than 5 it will continue doing this unless interrupted from outside the function by a button click. If
    the count falls to 0 it will call the func (burgers) and (depending on the current burger_num) pass it a
    burger name and id for the next burger to cycle to in order.

    :param count: Integer, seconds .after will count down
    :param burger_num: The burger we are up to in the cycle.
    """

    global TIMER
    if count > 0:
        # Needs to be a global variable so that it can be passed to another function to cancel the counting if a button
        # is clicked, otherwise the functions start calling over the top of each other trying to finish causing mayhem.
        TIMER = root.after(1000, cycle, count - 1, burger_num)
    elif count == 0:
        # Orders the burgers so that the auto cycle can pick up where it left off from after a button click or just
        # continue with its own cycle.
        if burger_num == 0:
            burgers("Byte Burger", 1)
        elif burger_num == 1:
            burgers("Ctrl-Alt-Delicious", 2)
        elif burger_num == 2:
            burgers("Data Crunch", 3)
        elif burger_num == 3:
            burgers("Code Cruncher", 0)


# ------------------------------GUI CODE BELOW------------------------------------
root = Tk()
root.configure(width=1200, height=1200, padx=10, pady=10)
root.title("Place your order")

logo_frame = ttk.Frame(root, width=800, height=400, padding=5)
button_frame = ttk.Frame(root, width=800, height=400, padding=5)
information_frame = ttk.Frame(root, width=600, height=400, padding="20 5 20 5", borderwidth=5)
image_frame = ttk.Frame(root, width=500, height=500, padding=5)

# Frame Grids
logo_frame.grid(column=0, row=0, columnspan=2, sticky="N S E W")
button_frame.grid(column=0, row=1, columnspan=2, sticky="N S E W")
image_frame.grid(column=1, row=2, sticky="N S E W")
information_frame.grid(column=0, row=2, sticky="N S E W")
information_frame.grid_propagate(False)

# Burger pictures
byte_image = PhotoImage(file="images/byte_burger.png")
cad_image = PhotoImage(file="images/cad_burger.png")
data_image = PhotoImage(file="images/data_burger.png")
code_image = PhotoImage(file="images/code_burger.png")
burger_label = ttk.Label(image_frame, image=byte_image, padding=100)
burger_label.grid(sticky="N S E W")

style = ttk.Style()
style.theme_use('clam')
style.configure("TLabel", font=("courier", 20))
style.configure("TButton", font=("courier", 30))

# Logo Frame widgets
shop_name_label = ttk.Label(logo_frame, text="Welcome to Codetown Burger Co", font=("courier", 50))
logo = PhotoImage(file="images/logo.png")
logo_label = ttk.Label(logo_frame, image=logo)

# Logo grids
logo_label.grid(column=0, row=0, sticky="N S E W")
shop_name_label.grid(column=1, row=0, sticky="N S E W")

# Burger information labels
name = ttk.Label(information_frame, font=("courier", 50))
bun = ttk.Label(information_frame)
sauce = ttk.Label(information_frame)
pattie = ttk.Label(information_frame)
cheese = ttk.Label(information_frame)
lettuce = ttk.Label(information_frame)
tomato = ttk.Label(information_frame)
onion = ttk.Label(information_frame)
price = ttk.Label(information_frame)

# Burger information Grids
name.grid(column=0, row=0)
bun.grid(column=0, row=1)
sauce.grid(column=0, row=2)
pattie.grid(column=0, row=3)
cheese.grid(column=0, row=4)
lettuce.grid(column=0, row=5)
tomato.grid(column=0, row=6)
onion.grid(column=0, row=7)
price.grid(column=0, row=8)

# Buttons for the four burgers, Lambda lets me pass in a name and burger id associated to the button that is clicked
byte_burger_butt = ttk.Button(button_frame, text="Byte Burger", command=lambda: burgers("Byte Burger", 1))
cad_burger_butt = ttk.Button(button_frame, text="Ctrl-Alt-Delicious", command=lambda: burgers("Ctrl-Alt-Delicious", 2))
data_crunch_butt = ttk.Button(button_frame, text="Data Crunch", command=lambda: burgers("Data Crunch", 3))
code_cruncher_butt = ttk.Button(button_frame, text="Code Cruncher", command=lambda: burgers("Code Cruncher", 0))

# Grids for the buttons
byte_burger_butt.grid(column=0, row=0, sticky="N S E W")
cad_burger_butt.grid(column=1, row=0, sticky="N S E W")
data_crunch_butt.grid(column=2, row=0, sticky="N S E W")
code_cruncher_butt.grid(column=3, row=0, sticky="N S E W")

# Column re-size config
root.columnconfigure(0, weight=1)
root.columnconfigure(1, weight=1)
image_frame.columnconfigure(0, weight=1)
logo_frame.columnconfigure(0, weight=1)
logo_frame.columnconfigure(1, weight=1)
button_frame.columnconfigure(0, weight=1)
button_frame.columnconfigure(1, weight=1)
button_frame.columnconfigure(2, weight=1)
button_frame.columnconfigure(3, weight=1)
information_frame.columnconfigure(0, weight=1)

# Row re-size configs
root.rowconfigure(0, weight=0)
root.rowconfigure(1, weight=0)
root.rowconfigure(2, weight=1)
image_frame.rowconfigure(0, weight=1)
logo_frame.rowconfigure(0, weight=1)
button_frame.rowconfigure(0, weight=1)
information_frame.rowconfigure(0, weight=1)
information_frame.rowconfigure(1, weight=1)
information_frame.rowconfigure(2, weight=1)
information_frame.rowconfigure(3, weight=1)
information_frame.rowconfigure(4, weight=1)
information_frame.rowconfigure(5, weight=1)
information_frame.rowconfigure(6, weight=1)
information_frame.rowconfigure(7, weight=1)
information_frame.rowconfigure(8, weight=1)

burgers("Byte Burger", 1)

root.mainloop()
