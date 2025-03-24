#include <stdexcept>
#include <iostream>

void some_function(int a) {

    if (a < 0) 
        throw std::invalid_argument("Cannot be a negative number");
}

int main() {

    int number = -1;

    //some_function(number); // will crash program

    try {
        some_function(number); 
    }
    catch (std::invalid_argument& except) {
        std::cout << "Error " << except.what() << std::endl;
    }

    std::cout << "Program goes on" << std::endl;

    return 0;
}


