#include <iostream>
#include <stack>

//------------------------------------------------------------------------------
// QUESTION 1's FUNCTIONS

// Reversing a stack using 2 temporary stacks
void reverse_stack(std::stack<int>& S, std::stack<int>& S1, std::stack<int>& S2) {
    
    while (!S.empty()) {     // Transfer the stack to another stack, it's reversed
        S1.push(S.top());    // but in the wrong stack, we need another stack to 
        S.pop();             // reverse it again before pushing to our original stack 
    }
    while (!S1.empty()) {    // Hey I found another stack, now its in the original 
        S2.push(S1.top());   // order, but not in the original stack... once more 
        S1.pop();            // please!
    }
    while (!S2.empty()) {    // Now it's reversed and in the original stack.. ta-da
        S.push(S2.top());
        S2.pop();
    }
}

// Reversing a stack using 1 temporary stack
void reverse_stack(std::stack<int>& S, std::stack<int>& S1) {

    S1.swap(S);                   // Swap the contents of S and S1

    while (!S1.empty()) {
        S.push(S1.top());         // Now just iterate through S1 pushing each 
        S1.pop();                 // value to the original stack 
    }
}

//------------------------------------------------------------------------------
// QUESTION 2's FUNCTIONS
    
// a) Using recursion to print a stack and return it to the original state 
void print_stack_rec(std::stack<int>& S) {
    int num = S.top();        // Store what will be the popped value for later
    std::cout << num << " ";
    S.pop();

    if (!S.empty()) {         // Perform the dark art of recursion until the stack is no more
        print_stack_rec(S);
    }
    S.push(num);             // Restack that stored value once the printing is done 
}

// b) Using iteration to print a stack and return it to the original state
void print_stack_iter(std::stack<int>& S) {
        
        std::stack<int> temp;             // Temporary stack to hold all popped values 
        while (!S.empty()) {
            std::cout << S.top() << " ";
            temp.push(S.top());          // Store the printed value for later 
            S.pop();
        }

        while (!temp.empty()) {          // Just like magic we rebuild the original state
            S.push(temp.top());          // with the good ole 'while not empty trick'
            temp.pop();
        }
    }

//------------------------------------------------------------------------------
// QUESTION 3's FUNCTION

double sum(int n) {

    if (n > 1 && n % 2 == 0)
        return sum(n-1) + 1.0/n;   // Add for even numbers 
    else if (n > 1 && n % 2 != 0)
        return sum(n-1) - 1.0/n;   // Subtract for odd numbers
    else 
        return n;                  // dis our base case, n = 1
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
    std::cout << "First recursive call:\t" << '\n';
    print_stack_rec(S);
    std::cout << "Second recursive call:\t"; 
    print_stack_rec(S);
    std::cout << "\n\nIterative Printing calls\n";
    std::cout << "First iterative call:\t" << '\n';
    print_stack_iter(S);
    std::cout << "Second iterative call:\t"; 
    print_stack_iter(S);
    
//------------------------------------------------------------------------------
    // QUESTION 3
    
    std::cout << "\n\nQuestion 3:\n";
    int n = 3;
    std::cout << "The sum for the first n numbers, where n = " << n 
        << " is: " << sum(3) << std::endl;

    return 0;
}
