#include <iostream>
#include <cassert>
#include <stdexcept>

// Sum the first N numbers of a sequence
long sumfirstn(long n) {
    
    if (n <= 0)
        throw std::invalid_argument("Number must be a positive integer");
    else if (n == 1)
        return n;
    else 
        return sumfirstn(n-1) + n;
}

int mcCarthy91(int n) {
    
    if (n > 111 || n <= 0)
        throw std::invalid_argument("Number must be a positive integer less than 101");
    else if (n > 100)
        return n - 10;
    else 
        return mcCarthy91(mcCarthy91(n + 11));
}

int tailrecfibonakki(int n, int a, int b) {

    if (n <= 0)
        return a;
    else
        return tailrecfibonakki(n-1, b, a+b);
}

int iterfibonakki(int n) {
    int a = 1;
    int b = 1;
    int c;
    while (n > 0) {
        n--;
        c = a;
        a = b;
        b = c+b;
    }

    return a;
}

int fibonakki(int n) {

    if (n <= 2)
        return 1;
    else 
        return fibonakki(n-1) + fibonakki(n-2);
}

void print_decimal_to_binary_iter(int dec) {
    
    if (dec / 2 != 0)
        print_decimal_to_binary_iter(dec / 2);
    else 
        std::cout << "0";

    std::cout << dec%2;
}


// Trying out testing
void test_add() {
    long n = 3;

    // Sum of first n numbers test
    assert(sumfirstn(n) == n*(n+1)/2);
    std::cout << "All Sum of N numbers tests passed" << std::endl;

    //mcCarthy91 tests 
    assert(mcCarthy91(1) == 91);
    assert(mcCarthy91(100) == 91);
    assert(mcCarthy91(101) == 91);
    // assert(mcCarthy91(2000) == std::invalid_argument);
    std::cout << "All mcCarthy91 tests passed" << std::endl;

    //tailrecfibonakki tests 
    assert(tailrecfibonakki(6, 0, 1) == 8);
    assert(tailrecfibonakki(6, 1, 1) == 13);
    std::cout << "All tail recursive fibonakki tests passed" << std::endl;

    // iterative fibbo 
    assert(iterfibonakki(6) == 13);
    assert(iterfibonakki(0) == 1);
    assert(iterfibonakki(1) == 1);

    // fibnakki tests 
    assert(fibonakki(6) == 8);
    assert(fibonakki(0) == 1);
    assert(fibonakki(1) == 1);
    std::cout << "All fibonakki tests passed" << std::endl;

    // assert(print_decimal_to_binary_iter(15, "") == "01111");
    // std::cout << "All binary conversion tests passed" << std::endl;

    // fib memoized
    int fib_mem(int n) {

        static int a[10000];
        a[0] = 1;
        a[1] = 1;

        if (!a[n-1])
            a[n-1] = fib_mem(n-1) + fib_mem(n-2);

        return a[n-1];
    }

}

int main() {
    test_add();

    std::cout << sumfirstn(3) << std::endl;
    std::cout << tailrecfibonakki(6, 0, 1) << std::endl;
    std::cout << iterfibonakki(6) << std::endl;

    print_decimal_to_binary_iter(15);
    std::cout << "\n";

    print_decimal_to_binary_iter(27);
    std::cout << "\n";


    return 0;
}

