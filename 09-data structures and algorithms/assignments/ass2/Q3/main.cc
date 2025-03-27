#include <iostream>
#include <string>
#include "dll_array.h"

/* ***THIS FUNCTION WON'T COMPILE UNTIL THE NEW MEMBERS ARE IMPLEMENTED.*** */

int main()
{
	Node_Array<std::string> NA;

	Array_Linked_List<std::string> L;
	
	L.insert(NA, "dog."), L.insert(NA, "lazy"), L.insert(NA, "the");
	L.insert(NA, "over"), L.insert(NA, "jumps"), L.insert(NA, "fox");
	L.insert(NA, "brown"), L.insert(NA, "quick"), L.insert(NA, "The");
	
	std::cout << std::endl << "Original list: " << std::endl;
	L.print_list(NA), std::cout << std::endl;
	
	L.remove(NA, "there");
	
	std::cout << "List after removing \"there\": " << std::endl;
	L.print_list(NA), std::cout << std::endl;
	
	L.remove(NA, "jumps");
	
	std::cout << "List after removing \"jumps\": " << std::endl;
	L.print_list(NA), std::cout << std::endl;

	return 0;
}
