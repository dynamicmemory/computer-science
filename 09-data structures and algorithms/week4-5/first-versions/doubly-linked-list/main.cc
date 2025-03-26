#include <iostream>
#include "linkedlist.h"

int main() {

    LinkedList<int> list;
    list.insert(1);
    list.insert(2);
    list.insert(3);

    /*int value = list.search(2)->data;*/
    std::cout << list.search(2)->data << "\n";
    
    list.remove(list.search(2));

    std::cout << list.search(2)->data << "\n";

    return 0;
}
