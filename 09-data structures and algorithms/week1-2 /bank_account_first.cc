#include <string>
#include <iostream>

class Bank_account {
public:
    // default constructor
    Bank_account(): account_number(), balance() {}

    // constructor?
    Bank_account(std::string a, int b, double c = 0.0) {
        customer_name = a;
        account_number = b;
        balance = c;
    }

    // Mod member function
    void set_balance(double a) {
        balance = a;
    }

    // conts read only member functions
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
    // data members (variables for storing data)
    std::string customer_name;
    int account_number;
    double balance;
};


int main() {
    // object instantiation
    Bank_account customer1;
    Bank_account customer2("david", 123, 110.0);
    
    //output values for customer 1 
    std::cout << "Name: " << customer1.get_customer_name() << std::endl;
    std::cout << "Account Number: " << customer1.get_account_number() << std::endl;
    std::cout << "Balance: " << customer1.get_balance() << std::endl;

    //output values for customer 2 
    std::cout << "Name: " << customer2.get_customer_name() << std::endl;
    std::cout << "Account Number: " << customer2.get_account_number() << std::endl;
    std::cout << "Balance: " << customer2.get_balance() << std::endl;

    // test the set member
    customer2.set_balance(2200.0);
    std::cout << "New balance: " << customer2.get_balance() << std::endl;

    return 0;
}


