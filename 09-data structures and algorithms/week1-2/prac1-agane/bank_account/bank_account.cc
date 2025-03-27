#include "bank_account.h"

Bank_Account::Bank_Account() : customer_name(""), account_number(0), balance(0.0) {}

Bank_Account::Bank_Account(std::string customer_name, int account_number, double balance) 
    : customer_name(std::move(customer_name)),
      account_number(account_number),
      balance(balance) {}

void Bank_Account::set_balance(double new_balance) {
    balance = new_balance;
}

std::string Bank_Account::get_customer_name() const {
    return customer_name;
}

int Bank_Account::get_account_number() const {
    return account_number;
}

double Bank_Account::get_balance() const {
    return balance;
}


