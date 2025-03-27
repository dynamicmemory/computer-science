#include <iostream>
#include "vec.h"
using std::cout;
using std::endl;

int main() {

    Vec v1(1);
    v1[0] = 2.0;

    Vec v2(v1);
    cout << v1[0] << " " << v2[0] << endl;

    v2[0] = 4.0;
    cout << v1[0] << " " << v2[0] << endl;
}
