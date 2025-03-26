#include <iostream>
#include "bank_account.h"

int main() {

    BankAccount customer1;
    BankAccount customer2("Steve", 420, 0.99);

    std::cout << "Name: " << customer1.getName() << "\n"
              << "Acc Num: " << customer1.getAccountNumber() << "\n"
              << "Balance: " << customer1.getBalance() << "\n\n";

    std::cout << "Name: " << customer2.getName() << "\n"
              << "Acc Num: " << customer2.getAccountNumber() << "\n"
              << "Balance: " << customer2.getBalance() << "\n\n";

    customer2.setBalance(69.420);

    std::cout << "Updated Balance: " << customer2.getBalance() << std::endl;

    return 0;
}
