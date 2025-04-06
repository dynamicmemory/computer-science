#include <iostream>
#include <stack>

void itob(int num, int base) {
    std::stack<char> s;
    int digit;

    while(num > 0) {
        digit = num % base;
        
        if (digit >= 10)
            digit = (digit - 10) + 'A';
        else 
            digit = '0' + digit;

        s.push(digit);

        num /= base; 
    }
    
    if (base == 16)
        std::cout << "0x";

    while(!s.empty()) {
        std::cout << s.top();
        s.pop();
        }

    std::cout << '\n';
}

int main() {
    itob(29, 16);

    return 0;
}

