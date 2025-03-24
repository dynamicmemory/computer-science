#include <ctime>
#include <iomanip>
#include <iostream>

int main() {
    clock_t start = clock();

    int n = 100;
    double a[n][n];
    double b[n][n];

    /*double sum = 0.0;*/
    /*for (int i = 0; i != n; ++i) {*/
    /*    sum += a[i];*/
    /*}*/

    /*double c[n][n];*/
    /*for (int i = 0; i != n; ++i) {*/
    /*    for (int j = 0; j != n; ++j) {*/
    /*        for (int k = 0; k != n; ++k) {*/
    /*            c[i][j] += a[i][k]*b[k][j];*/
    /*        }*/
    /*    }*/
    /*}*/

    double temp;
    for (int i = 0; i != n-1; ++i) {
        for (int j = i+1; j != n; ++j) {
            temp = a[i][j];
            a[i][j] = a[j][i];
            a[j][i] = temp;
        }
    }

    clock_t end = clock();
    double time_sec = (end - start)/(double) CLOCKS_PER_SEC;

    std::cout << std::fixed << std::setprecision(8) << time_sec << "\n";

    clock_t start2 = clock();

    n = 1000;
    double a2[n][n];
    double b2[n][n];

    /*double sum2 = 0.0;*/
    /*for (int i = 0; i != n; ++i) {*/
    /*    sum2 += a2[i];*/
    /*}*/

    /*double c2[n][n];*/
    /*for (int i = 0; i != n; ++i) {*/
    /*    for (int j = 0; j != n; ++j) {*/
    /*        for (int k = 0; k != n; ++k) {*/
    /*            c2[i][j] += a2[i][k]*b2[k][j];*/
    /*        }*/
    /*    }*/
    /*}*/

    for (int i = 0; i != n-1; ++i) {
        for (int j = i+1; j != n; ++j) {
            temp = a2[i][j];
            a2[i][j] = a2[j][i];
            a2[j][i] = temp;
        }
    }

    clock_t end2 = clock();
    double time_sec2 = (end2 - start2)/(double) CLOCKS_PER_SEC;

    std::cout << std::fixed << std::setprecision(8) << time_sec2 << "\n";

    return 0;
}
