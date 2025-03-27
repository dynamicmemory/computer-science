// bank_account.cc
#include "bank_account.h"

BankAccount::BankAccount(const std::string& customerName, int accountNumber, double balance)
    : m_customerName(customerName), m_accountNumber(accountNumber), m_balance(balance) {}

void BankAccount::setBalance(double newBalance) {
    m_balance = newBalance;
}

std::string BankAccount::getName() const {
    return m_customerName;
}

int BankAccount::getAccountNumber() const {
    return m_accountNumber;
}

double BankAccount::getBalance() const {
    return m_balance;
}


    
