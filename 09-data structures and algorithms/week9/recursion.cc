// Factorial for all the digits  
#include <iostream>

int fact(int n) {

    if (n == 0) 
        return 1;
    else 
        return n*fact(n - 1);
}

int main(int argc, char* argv[]) {
    char x;
    if (argc >= 1)
        x = std::atoi(argv[1]);
    else
        x = 0;

    std::cout << fact(x) << std::endl;

}

// Factorial for the first 9 digits only
// #include <iostream>
//
// int fact(int n) {
//
//     if (n == 0) 
//         return 1;
//     else 
//         return n*fact(n - 1);
// }
//
// int main(int argc, char* argv[]) {
//     char x;
//     if (argc >= 1)
//         x = *argv[1] - '0';
//     else
//         x = 0;
//
//     std::cout << fact(x) << std::endl;
//
// }
