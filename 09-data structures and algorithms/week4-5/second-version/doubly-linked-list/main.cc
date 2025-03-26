#include <iostream>
#include "linked_list.cc"

int main() {

    Linked_List<int> list;

    list.insert(1);
    list.insert(2);
    list.insert(3);
    list.insert(5);

    if (list.search(4) == nullptr)
        std::cout << "Does not exist" << '\n';
    else 
        std::cout << "It exits" << '\n';

    list.insert(4);

    if (list.search(4) == nullptr)
        std::cout << "Does not exist" << '\n';
    else 
        std::cout << "It exits" << '\n';

    list.remove(list.search(4));

    if (list.search(4) == nullptr)
        std::cout << "Does not exist" << '\n';
    else 
        std::cout << "It exits" << '\n';

    return 0;
}
