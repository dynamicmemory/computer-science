#include <iostream>
#include <string>

class Bank_account { 

public:
    Bank_account() : account_number(), balance() {}

    Bank_account(std::string a, int b, double c = 0.0) {
        customer_name = a;
        account_number = b;
        balance = c;
    }

    void set_balance(double a) {
        balance = a;
    }

    std::string get_customer_name() const {
        return customer_name;
    }

    int get_account_number() const {
        return account_number;
    }

    double get_balance() const {
        return balance;
    }

private:
    std::string customer_name;
    int account_number;
    double balance;
};


int main() {

    Bank_account account1 = Bank_account("john", 5, 90.99);

    std::string name = account1.get_customer_name();
    int number = account1.get_account_number();
    double bal = account1.get_balance();

    std::cout << "Name: " << name << std::endl;
    std::cout << "Account number: " << number << std::endl;
    std::cout << "balance: " << bal << std::endl;

    account1.set_balance(109.99);

    bal = account1.get_balance();

    std::cout << "balance: " << bal << std::endl;

    return 0;
}
