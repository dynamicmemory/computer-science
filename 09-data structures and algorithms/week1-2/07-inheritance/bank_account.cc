#include "bank_account.h"
class CreditAccount : public BankAccount {

public:
    CreditAccount() : BankAccount(), creditBalance() {};
    CreditAccount(std::string, int, double, double);
    void setCreditBalance(double);
    double getCreditBalance() const;

private:
    double creditBalance;
};

