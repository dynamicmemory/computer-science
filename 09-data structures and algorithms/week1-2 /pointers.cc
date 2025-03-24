#include <iostream>
using std::cout;
using std::endl;

int main() {
    int x = 2;

    cout << x << endl;

    int* p = &x;

    cout << x << endl;

    *p = 1;

    cout << x << endl;

    int n;
    int* q = new int[n];
 
    cout << q << endl;
    cout << q + x << endl;
    cout << q + x + 1 << endl;

}

