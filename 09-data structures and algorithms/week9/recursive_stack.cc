#include <iostream>
#include <stack>

// Recursive factorial with time O(n) and space O(n)
int f_rec(int n) {
    if (n == 0)
        return 1;
    else 
        return n*f_rec(n-1);
}

// iterative factorial with time O(n) and space O(1)
int f_iter(int n, int a) {
    if (n == 0)
        return a;
    else 
        return f_iter(n-1, n*a);
}

// iterative factorial with time O(n) and space O(1)
int f_loop(int n) {
    int a = 1;
    
    for (; n != 0; n--, a*=n)
        ;

    return a;
}

// reverse recursion
void reverse_rec() {
    char ch;

    std::cin.get(ch);
    if (ch != '\n') {
        reverse_rec();
        std::cout << ch;
    }
}

// reverse iterator
void reverse_loop() {
    char ch;
    std::stack<char> S;

    std::cin.get(ch);
    while (ch != '\n') {
        S.push(ch);
        std::cin.get(ch);
    }

    while (!S.empty()) {
        std::cout << S.top();
        S.pop();
    }

    std::cout << std::endl;
}


int main() {

    return 0;
}
