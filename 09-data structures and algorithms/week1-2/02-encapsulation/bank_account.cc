#include <string>
#include <iostream>

using std::cout, std::endl;

class BankAccount {

public:
    // default constructor / list of member initializers
    BankAccount(): m_customerName(""), m_accountNumber(0), m_balance(0.0) {}

    // The constructor
    BankAccount(const std::string& customerName, int accountNumber, double balance) 
        : m_customerName(customerName), m_accountNumber(accountNumber), m_balance(balance) {}

    // Functions to touch the member variables inapropriately
    void setBalance(double new_bal) {
        m_balance = new_bal;
    }

    // Your typical getters
    std::string getName() const { return m_customerName; }
    int getAccountNumber() const { return m_accountNumber; }
    double getBalance() const { return m_balance; }

private:
    // Create the member variable for the class
    std::string m_customerName;
    int m_accountNumber;
    double m_balance;
};


int main() {
    // Create some objects for our bank
    BankAccount customer1;
    BankAccount customer2("Steve", 69420, 0.99);

    // Print out the features of our class... i.e take it for a spin
    cout << "Name: " << customer1.getName() << "\n";
    cout << "Acc Num: " << customer1.getAccountNumber() << "\n";
    cout << "Balance: " << customer1.getBalance() << "\n\n";

    cout << "Name: " << customer2.getName() << "\n";
    cout << "Acc Num: " << customer2.getAccountNumber() << "\n";
    cout << "Balance: " << customer2.getBalance() << "\n\n";

    // try updating a value
    customer2.setBalance(69.420);

    cout << "Updated balance: " << customer2.getBalance() << endl;

    return 0;
}
