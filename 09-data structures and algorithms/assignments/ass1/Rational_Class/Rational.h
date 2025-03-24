// Rational.h
// A rational number class.
#include <iostream>

class Rational {

friend std::ostream& operator<<(std::ostream&, const Rational&);

public:
	Rational(): numerator(), denominator(1) {  }
	Rational(int, int);							// Constructor now validates object
	void reduce();
	void set_number(int, int);

    // arithmetic operators
	Rational operator+(const Rational&) const; 	//Addition
	Rational operator-(const Rational&) const; 	// Subtraction
	Rational operator*(const Rational&) const; 	// Multiplication
	Rational operator/(const Rational&) const;	// Division

    // relational operators
	bool operator<(const Rational&) const;	// Less-than
	bool operator>(const Rational&) const;	// Greater-than
	bool operator==(const Rational&) const; // Equal-to
	bool operator!=(const Rational&) const; // Not-equal-to

private:
	int numerator;
	int denominator;
};
