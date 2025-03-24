#include <cstdlib>
#include "rational.h"

Rational::Rational(int numerator, int denominator) {
    m_numerator = numerator;
    m_denominator = denominator;

    reduce();
}

std::ostream& operator<<(std::ostream& output, const Rational& r) {
    output << r.m_numerator << "/" << r.m_denominator;

    return output;
}

int greatestCommonDivisor(int a, int b) {
    a = abs(a);
    b = abs(b);

    if (b == 0)
        return a;
    else 
        return greatestCommonDivisor(b, a%b);
}

void Rational::reduce() {
    int gcd = greatestCommonDivisor(m_numerator, m_denominator);

    m_numerator = m_numerator/gcd;
    m_denominator = m_denominator/gcd;

    if (m_numerator < 0)
        m_numerator = -m_numerator, m_denominator = -m_denominator;
}

Rational Rational::operator+(const Rational& a) const {
    Rational result;

    result.m_numerator = m_numerator*a.m_denominator + a.m_numerator*m_denominator;
    result.m_denominator = m_denominator*a.m_denominator;

    return result;
}
