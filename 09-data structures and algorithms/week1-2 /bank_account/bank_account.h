// The class interface
#include <string>

class Bank_account {

public:
    Bank_account(): account_number(), balance() {}
    Bank_account(std::string, int, double);
    void set_balance(double);
    std::string get_customer_name() const;
    int get_account_number() const;
    double get_balance() const;

private:
    std::string customer_name;
    int account_number;
    double balance;
};


class Credit_account: public Bank_account {

public:
    Credit_account(): Bank_account(), credit_balance() {};
    Credit_account(std::string, int, double, double);
    void set_credit_bal(double);
    double get_credit_bal() const;

private:
    double credit_balance;
};
