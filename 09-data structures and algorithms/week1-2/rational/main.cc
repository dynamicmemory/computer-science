#include "rational.h"
using namespace std;

int main() {

    // Create some rational objects
    Rational a;
    Rational b(4, 3);
    Rational c(5, 10);

    // Print them to std out
    cout << a << endl;
    cout << b << endl;
    cout << c << endl;

    // Try addition with two objects
    Rational d = b + c;

    cout << "Addition: " << d << endl;

    // Try subtraction 
    Rational e = b - c;

    cout << "Subtracton: " << e << endl;

    // Try multi
    Rational f = b * c;

    cout << "Multiplication: " << f << endl;

    // Try division

    Rational g = b / c;

    cout << "Division: " << g << endl;

    return 0;
}

