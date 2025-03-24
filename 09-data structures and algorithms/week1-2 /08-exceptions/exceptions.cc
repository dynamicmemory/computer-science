#include <stdexcept>
#include <iostream>

void someFunction(int numerator, int denominator) {
    if (denominator == 0)
        throw std::invalid_argument("Cannot divide by zero");
}


int main() {
    int someInteger = 0;
    int someOther = 1;
    
    // Will crash the program
    /*someFunction(someOther, someInteger);*/

    try {
        someFunction(someOther, someInteger);
    }
    catch (std::invalid_argument& exception) {
        std::cout << "Error: " << exception.what() << std::endl;
    }

    return 0;
}
