#include <iostream>
#include <stack>

//------------------------------------------------------------------------------
// QUESTION 1's FUNCTIONS

// Reversing a stack using 2 temporary stacks
void reverse_stack(std::stack<int>& S, std::stack<int>& S1, std::stack<int>& S2) {
    while (!S.empty()) {
        S1.push(S.top());
        S.pop();
    }
    while (!S1.empty()) {
        S2.push(S1.top());
        S1.pop();
    }
    while (!S2.empty()) {
        S.push(S2.top());
        S2.pop();
    }
}

// Reversing a stack using 1 temporary stack
void reverse_stack(std::stack<int>& S, std::stack<int>& S1) {

    S1.swap(S);

    while (!S1.empty()) {
        S.push(S1.top());
        S1.pop();
    }
}

//------------------------------------------------------------------------------
// QUESTION 2's FUNCTIONS
    
// a) Using recursion to print a stack and return it to the original state 
void print_stack_rec(std::stack<int>& S) {
    int num = S.top();
    std::cout << num << " ";
    S.pop();

    if (!S.empty()) {
        print_stack_rec(S);
    }
    S.push(num);
}

// b) Using iteration to print a stack and return it to the original state
void print_stack_iter(std::stack<int>& S) {
        
        std::stack<int> temp;
        while (!S.empty()) {
            std::cout << S.top() << " ";
            temp.push(S.top());
            S.pop();
        }

        while (!temp.empty()) {
            S.push(temp.top());
            temp.pop();
        }
    }

//------------------------------------------------------------------------------
// QUESTION 3's FUNCTION

double sum(int n) {

    if (n > 1 && n % 2 == 0)
        return sum(n-1) + 1.0/n;
    else if (n > 1 && n % 2 != 0)
        return sum(n-1) - 1.0/n;
    else 
        return n;
}


int main(int argc, char* argv[]) {
    // QUESTION 1 
    std::stack<int> S, S1, S2;
    
    S.push(1), S.push(2), S.push(3);

    std::cout << "Question 1: \nReversing with two stacks\n";
    // Using 2 temporary stacks to reverse a stack 
    std::cout << "The top element of S is: " << S.top() << '\n';
    reverse_stack(S, S1, S2);   
    std::cout << "The top element of S is: " << S.top() << "\n\n";

    // Using 1 temporary stack to rever a stack 
    std::cout << "Reversing with one stack\n";
    std::cout << "The top element of S is: " << S.top() << '\n';
    reverse_stack(S, S1);     
    std::cout << "The top element of S is: " << S.top() << "\n\n";

//------------------------------------------------------------------------------
    // QUESTION 2
    std::cout << "Question 2:\nRecursive Printing calls\n";
    print_stack_rec(S);
    std::cout << "first recursive call" << '\n';
    print_stack_rec(S);
    std::cout << "second recursive call" << "\n\nIterative Printing calls\n";
    print_stack_iter(S);
    std::cout << "first iterative call" << '\n';
    print_stack_iter(S);
    std::cout << "second iterative call" << "\n\n";
    
//------------------------------------------------------------------------------
    // QUESTION 3
    
    std::cout << "Question 3:\n";
    int n = 3;
    std::cout << "The sum for the first n numbers, where n = " << n 
        << " is: " << sum(3) << std::endl;

    return 0;
}



// #include <iostream>
// #include <stack>
//
// // Reversing a stack using 2 temporary stacks
// void reverse_stack(std::stack<int>& S, std::stack<int>& S1, std::stack<int>& S2) {
//     while (!S.empty()) {
//         S1.push(S.top());
//         S.pop();
//     }
//     while (!S1.empty()) {
//         S2.push(S1.top());
//         S1.pop();
//     }
//     while (!S2.empty()) {
//         S.push(S2.top());
//         S2.pop();
//     }
// }
//
// // Reversing a stack using 1 temporary stack
// void reverse_stack(std::stack<int>& S, std::stack<int>& S1) {
//     S1.swap(S);
//     while (!S1.empty()) {
//         S.push(S1.top());
//         S1.pop();
//     }
// }
//
// int main(int argc, char* argv[]) {
//     std::stack<int> S, S1, S2;
//
//     // Reversing a stack using two temporary stacks 
//     S.push(1), S.push(2), S.push(3);
//     std::cout << "The top element of S is: " << S.top() << '\n';
//     reverse_stack(S, S1, S2);
//     std::cout << "The top element of S is: " << S.top() << '\n';
//
//     // Reversing a stack using one temporary stack
//     reverse_stack(S, S1);
//     std::cout << "The top element of S is: " << S.top() << '\n';
//
//     return 0;
// }
//
//
