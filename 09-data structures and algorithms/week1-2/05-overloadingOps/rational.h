#include <iostream>

class Rational {

friend std::ostream& operator<<(std::ostream&, const Rational&);

public:
    Rational() : m_numerator(0), m_denominator(1) {}
    Rational(int, int);
    void reduce();
    Rational operator+(const Rational&) const;
    Rational operator-(const Rational&) const;
    Rational operator*(const Rational&) const;
    Rational operator/(const Rational&) const;
private:
    int m_numerator;
    int m_denominator;
};
