#include <iostream>
#include "linked_list.cc"

int main() {

    Linked_List<int> list;

    list.insert(1);
    list.insert(2);
    list.insert(3);

    list.insert(5);

    bool find_four = list.search(4);
    std::cout << find_four << '\n';

    list.insert(4);
    find_four = list.search(4);
    std::cout << find_four << '\n';
    
    list.remove(4);
    find_four = list.search(4);
    std::cout << find_four << '\n';

    return 0;
}
