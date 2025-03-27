#include <cstdlib>
#include "rational.h"


std::ostream& operator<<(std::ostream& output, const Rational& r) {

    return output << r.numerator << "/" << r.denominator;
}


int greatest_common_divisor(int a, int b) {

    a = abs(a);
    b = abs(b);

    if (b == 0) 
        return a;
    else 
        return greatest_common_divisor(b, a%b);
}


Rational::Rational(int a, int b) {
    numerator = a;
    denominator = b;

    reduce();
}


void Rational::reduce() {

    int gcd = greatest_common_divisor(numerator, denominator);

    numerator = numerator/gcd;
    denominator = denominator/gcd;

    if (denominator < 0) {
        numerator = -numerator;
        denominator = -denominator;
    }
}


Rational Rational::operator+(const Rational& a) const {

    Rational result;

    result.numerator = numerator*a.denominator + a.numerator*denominator;
    result.denominator = denominator*a.denominator;

    return result;
}


Rational Rational::operator-(const Rational& a) const {

    Rational result;

    result.numerator = numerator*a.denominator - a.numerator*denominator;
    result.denominator = denominator*a.denominator;

    return result;
}


Rational Rational::operator*(const Rational& a) const {

    Rational result(numerator*a.numerator, denominator*a.denominator);

    return result;
}

Rational Rational::operator/(const Rational& a) const {

    Rational result(numerator*a.denominator, denominator*a.numerator);

    return result;
}
