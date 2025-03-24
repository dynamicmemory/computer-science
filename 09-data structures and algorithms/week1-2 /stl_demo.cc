#include <iostream>
#include <algorithm>
#include <vector>

using std::cout, std::cin, std::endl;

int main() {

    cout << "Enter the number of elements: ";
    int elements;
    cin >> elements;

    std::vector<int> v;
    cout << "Enter the numbers you want";
    for (int &num : v)
        cin >> num;

    // this is how you reverse the vector but you can use a for loop instead
    /*std::reverse(v.begin(), v.end()); */
    for (int i = 0, j = elements - 1; i < j; i++, j--)
        std::swap(v[i], v[j]);

    cout << "Reversed vector: ";
    for (int num : v)
        cout << num << " ";
    cout << endl;

    cout << "Enter a number to search for: ";
    int user_number;
    cin >> user_number;

    if (std::find(v.begin(), v.end(), user_number) != v.end())
        cout << user_number << " found in vector" << endl;
    else 
        cout << user_number << " not found in vector" << endl;

    return 0;
}
   
    
    /*std::vector<int> v;*/
    /**/
    /*cout << v.size() << " " << v.capacity() << endl;*/
    /**/
    /*for (int i = 0; i != 10; i++) */
    /*    v.push_back(i);*/
    /**/
    /*cout << v.size() << " " << v.capacity() << endl;*/
    /**/
    /*for (int i = 0; i != 10; i++)*/
    /*    cout << v.at(i) << endl;*/
    /**/
    /*std::vector<int>::iterator i;*/
    /*for (i = v.begin(); i !=v.end(); i++)*/
    /*    cout << *i << endl;*/
    /**/
    /*std::vector<int>::iterator i2;*/
    /*i2 = find(v.begin(), v.end(), 7);*/
    /**/
    /*if (i2 != v.end())*/
    /*    cout << "found 7" << endl;*/
    /**/
