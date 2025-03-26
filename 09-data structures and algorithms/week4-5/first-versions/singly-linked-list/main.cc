#include "linkedlist.h"
#include <iostream>

int main(int argc, char*argv[]) {
    LinkedList<int> list;

    list.insert(1);
    list.insert(2);
    list.insert(3);

    bool exists1 = list.search(2);
    bool exists2 = list.search(4);

    std::cout << "Does value 2 exist in list: " << exists1 << "\n";
    std::cout << "Does value 4 exist in list: " << exists2 << "\n";

    list.remove(2);
    bool exists3 = list.search(2);
    std::cout << "Does value 2 exist in list: " << exists3 << "\n";

    return 0;
}
