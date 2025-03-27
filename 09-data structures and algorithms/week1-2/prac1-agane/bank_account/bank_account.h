#include <string>

class Bank_Account {

public:
    Bank_Account();
    Bank_Account(std::string customer_name, int account_number, double balance = 0.0);

    void set_balance(double amount);

    std::string get_customer_name() const;
    int get_account_number() const;
    double get_balance() const;

private:
    std::string customer_name;
    int account_number;
    double balance;
};
