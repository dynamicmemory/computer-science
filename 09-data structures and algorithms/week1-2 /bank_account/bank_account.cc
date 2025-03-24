// Bank_account.cc the member function immplementation
#include <cstdlib>
#include <string>
#include "bank_account.h"

Bank_account::Bank_account(std::string a, int b, double c = 0.0) {

    customer_name = a;
    account_number = b;
    balance = c;
}

void Bank_account::set_balance(double a) {
    balance = a;
}

std::string Bank_account::get_customer_name() const {
    return customer_name;
}

int Bank_account::get_account_number() const {
    return  account_number;
}

double Bank_account::get_balance() const {
    return balance;
}

