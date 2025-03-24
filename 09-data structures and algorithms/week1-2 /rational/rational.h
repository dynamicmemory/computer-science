#include <iostream>

class Rational {
    friend std::ostream& operator<<(std::ostream&, const Rational&);

public:
    Rational(): numerator(), denominator(1) {}
    Rational(int, int);
    void reduce();
    Rational operator+(const Rational&) const;
    Rational operator-(const Rational&) const;
    Rational operator*(const Rational&) const;
    Rational operator/(const Rational&) const;

private:
    int numerator;
    int denominator;
};

