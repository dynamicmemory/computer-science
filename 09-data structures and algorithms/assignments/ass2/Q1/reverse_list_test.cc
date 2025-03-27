#include "sll.h"
#include <iostream>

template<class T>
void Linked_List<T>::print_list()
{
	for (Node<T>* p = head; p != 0; p = p->next) 
		std::cout << p->key << " ";
	std::cout << std::endl;
}

int main()
{

	Linked_List<int> a;

	a.insert(4), a.insert(3), a.insert(2); a.insert(1);

	std::cout << "Original list is: " << std::endl;
	a.print_list();

	a.reverse_list();

	std::cout << "Reversed list is: " << std::endl;
	a.print_list();

	return 0;
}
