#include "vec.h"

Vec::Vec() {

    array_limit = 0;
    dp = 0;
}

Vec::Vec(int a) {
    array_limit = a; // This seems redundant, just pass in a to the new array 

    dp = new double[array_limit];

    for (int i = 0; i != array_limit; i++) 
        dp[i] = 0.0;
}

Vec::Vec(const Vec& copy) {
    
    array_limit = copy.array_limit;

    dp = new double[array_limit];

    for (int i = 0; i !=array_limit; i++) 
        dp[i] = copy.dp[i];
}

Vec& Vec::operator=(const Vec& rhs) {

    if (&rhs != this) {
        delete[] dp;
        array_limit = rhs.array_limit;
        dp = new double[array_limit];

        for (int i = 0; i != array_limit; ++i) 
            dp[i] = rhs.dp[i];
    }
    return *this;
}

Vec::~Vec() {

    delete[] dp;
    dp = 0;
}

double& Vec::operator[](int i) {

    return dp[i];
}
