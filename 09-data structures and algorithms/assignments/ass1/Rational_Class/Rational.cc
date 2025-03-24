#include "Rational.h"
#include <stdexcept>
#include <cstdlib>

// Overloading the stream insertion operator to print a rational number.
std::ostream& operator<<(std::ostream& output, const Rational& r) {
	
    output << r.numerator << "/" << r.denominator;

	return output;
}

// Non-member function for recursive Euclidean Algorithm to find gcd.
int greatest_common_divisor(int a, int b) {	
	
    a = abs(a), 
    b = abs(b); // negative integers have same divisor as positive integers.

	if (b == 0) 
		return a;
	else
		return greatest_common_divisor(b, a % b);
}

// Constructor
Rational::Rational(int a, int b) {

    // Check for correct rational number format on object creation
    if (b == 0)
        throw std::invalid_argument("Error: Denominator cannot 0");

	numerator = a;
    denominator = b;
	reduce();
}

// Member function to reduce fractions to lowest terms.
void Rational::reduce() {	
	
    int gcd = greatest_common_divisor(numerator, denominator);

	numerator = numerator/gcd;
	denominator = denominator/gcd;

	if (denominator < 0) {
		numerator = -numerator;
        denominator = -denominator;
    }
}

// Member function to set rational-number object following instantiation.
void Rational::set_number(int a, int b) {
    
    // For testing purposes only, no need to protect from 0 in denominator
	numerator = a;
    denominator = b;
}

// Overloading the + operator to add two rational numbers.
Rational Rational::operator+(const Rational& a) const {
	
    Rational result;

	// cross-multiply, then add together.
	result.numerator = numerator*a.denominator + a.numerator*denominator;
	result.denominator = denominator*a.denominator;
	result.reduce();

	return result;
}

// Overload the - operator to subtract two rationals
Rational Rational::operator-(const Rational& a) const {
    
    Rational result;

    // Cross multiply, then subtract rationals
    result.numerator = numerator*a.denominator - a.numerator*denominator;
    result.denominator = denominator * a.denominator;
    result.reduce();
    
    return result;
}

// Overloading the * operator for multiplication
Rational Rational::operator*(const Rational& a) const {
    
    Rational result;

    result.numerator = numerator * a.numerator;
    result.denominator = denominator * a.denominator;
    result.reduce();
    
    return result;
}

// Overlaoding the / operator for division
Rational Rational::operator/(const Rational& a) const {

    // numerator = 0 leads to rational with 0 in denominator after division 
    if (a.numerator == 0)
        throw std::invalid_argument("Error: Cannot divide by a rational with 0 in numerator");
    Rational result;

    result.numerator = numerator * a.denominator;
    result.denominator = denominator * a.numerator;
    result.reduce();

    return result;
}

// Overlading the < for rational comparison
bool Rational::operator<(const Rational& a) const {
    
    // Cross multiplying for relational comparison
    long long rat1 = numerator * a.denominator;
    long long rat2 = a.numerator * denominator;

    if (rat1 < rat2)
        return true;
    else
        return false;
}

// Overlading the > for rational comparison
bool Rational::operator>(const Rational& a) const {
    
    // Cross multiplying for relational comparison
    long long rat1 = numerator * a.denominator;
    long long rat2 = a.numerator * denominator;

    if (rat1 > rat2)
        return true;
    else
        return false;
}

// Overlading the == for rational comparison
bool Rational::operator==(const Rational& a) const {

    // Cross multiplying for relational comparison
    long long rat1 = numerator * a.denominator;
    long long rat2 = a.numerator * denominator;
    

    if (rat1 == rat2)
        return true;
    else
        return false;
}

// Overlading the != for rational comparison
bool Rational::operator!=(const Rational& a) const {
    
    // Cross multiplying for relational comparison
    long long rat1 = numerator * a.denominator;
    long long rat2 = a.numerator * denominator;

    if (rat1 != rat2)
        return true;
    else
        return false;
}
