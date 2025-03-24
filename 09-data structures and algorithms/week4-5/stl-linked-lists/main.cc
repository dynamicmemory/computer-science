#include <iostream>
#include <list>
#include <array>
#include <string>

class Store_Inventory {

public:
    Store_Inventory(std::string a, int b = 0) {
        item_name = a;
        number_of_items = b;
    }

    bool operator == (const Store_Inventory& rhs) const {
        return item_name == rhs.item_name;
    }

    std::string item_name;
    int number_of_items;
};


//-------------------------------------------------------------------------

int main() {
    // Array of 5 linked lists 
    std::array<std::list<Store_Inventory>, 5> store;

    // Fruit (300 different items)
    store[0].push_back(Store_Inventory("tomatoes", 100));
    store[0].push_back(Store_Inventory("oranges", 200));
    store[0].push_back(Store_Inventory("apples", 50));

    // Vegetables (400 different items)
    store[1].push_back(Store_Inventory("potatoes", 100));
    store[1].push_back(Store_Inventory("lettuce", 100));

    std::cout << "Printout (all veg):" << '\n';
    std::list<Store_Inventory>::const_iterator p;
    for (p = store[1].begin(); p != store[1].end(); ++p) 
        std::cout << p->item_name << '\n';

    // Insert new Vegetables
    store[1].push_back(Store_Inventory("cucumber", 50));

    // Print all veggies
    std::cout << "Printout (all veg):" << '\n';
    for (p = store[1].begin(); p != store[1].end(); ++p) 
        std::cout << p->item_name << '\n';

    std::cout << '\n';

    // Delete sold-out veggies 
    store[1].remove(Store_Inventory("potatoes"));

    // print all veggies 
    std::cout << "Printout (all veg):" << '\n';
    for (p = store[1].begin(); p != store[1].end(); ++p)
        std::cout << p->item_name << '\n';
    std::cout << '\n';

    return 0;
}

