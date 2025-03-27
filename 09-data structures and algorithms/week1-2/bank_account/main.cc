// Main method
#include <iostream>
#include "bank_account.h"
using namespace std;

int main() {
    Bank_account customer1;
    Bank_account customer2("David", 123, 110.0);

    cout << "Name: " << customer1.get_customer_name() << endl;
    cout << "Account number: " << customer1.get_account_number() << endl;
    cout << "Balance: " << customer1.get_balance() << endl;

    cout << "Name: " << customer2.get_customer_name() << endl;
    cout << "Account number: " << customer2.get_account_number() << endl;
    cout << "Balance: " << customer2.get_balance() << endl;

    customer2.set_balance(2200.22);

    cout << "Balance: " << customer2.get_balance() << endl;

    return 0;
}
