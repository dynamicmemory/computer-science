#include "rational.h"

int main() {

    Rational a;
    Rational b(4, 3);
    Rational c(5, 10);

    std::cout << a << "\n"
              << b << "\n"
              << c << "\n";

    Rational d = b + c;

    std::cout << d << std::endl;

    return 0;
}
