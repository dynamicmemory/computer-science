// bank_account.h
#include <string>

class BankAccount {

public:
    BankAccount() : m_customerName(""), m_accountNumber(0), m_balance(0.0) {}
    BankAccount(const std::string&, int, double);
    void setBalance(double);
    std::string getName() const;
    int getAccountNumber() const;
    double getBalance() const;

private:
    std::string m_customerName;
    int m_accountNumber;
    double m_balance;
};
