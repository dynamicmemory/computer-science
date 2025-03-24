#include <iostream>
#include "bank_account.h"

using std::cout;
using std::endl;

int main() {

    Bank_Account customer1;
    Bank_Account customer2("Vladimir", 123, 99.99);

    cout << "Name: " << customer1.get_customer_name() << endl;
    cout << "Account number: " << customer1.get_account_number() << endl;
    cout << "Balance: " << customer1.get_balance() << endl;

    cout << "Name: " << customer2.get_customer_name() << endl;
    cout << "Account number: " << customer2.get_account_number() << endl;
    cout << "Balance: " << customer2.get_balance() << endl;

    customer2.set_balance(199.99);

    cout << "Updated Balance: " << customer2.get_balance() << endl;

    return 0;
}
