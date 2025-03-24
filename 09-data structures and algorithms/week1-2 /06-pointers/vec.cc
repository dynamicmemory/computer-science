#include <iostream>

// 'interface'
class Vec {

public: 
    Vec();
    Vec(int);
    
    // Destructor for mem management
    ~Vec();
    
    // creating a copy vec constructor 
    Vec(const Vec&);

    // overloading the assignment operator 
    Vec& operator=(const Vec&);
    
    // Overloading the array op
    double& operator[](int);

private:
    int array_limit_;
    double* pointer_to_data_;
};


// Class implementation
Vec::Vec() : array_limit_(0), pointer_to_data_(0) {}

Vec::Vec(int a) {

    array_limit_ = a;
    pointer_to_data_ = new double[array_limit_];

    for (int i = 0; i != array_limit_; i++)
        pointer_to_data_[i] = 0.0;
}

Vec::~Vec() {
    delete[] pointer_to_data_;
    pointer_to_data_ = 0;
}

double& Vec::operator[](int i) {
    return pointer_to_data_[i];
}

Vec::Vec(const Vec& vec_to_copy) {
    array_limit_ = vec_to_copy.array_limit_;
    pointer_to_data_ = new double[array_limit_];

    for (int i =0; i < array_limit_; i++)
        pointer_to_data_[i] = vec_to_copy.pointer_to_data_[i];
}

Vec& Vec::operator=(const Vec& rhs) {
    if (&rhs != this) {
        delete[] pointer_to_data_;

        array_limit_ = rhs.array_limit_;
        pointer_to_data_ = new double[array_limit_];

        for (int i = 0; i < array_limit_; i++)
            pointer_to_data_[i] = rhs.pointer_to_data_[i];
    }

    return *this;
} 


// main
int main() {
    Vec v1(1);
    v1[0] = 2.0;

    Vec v2(v1);
    std::cout << v1[0] << " " << v2[0] << "\n";

    v2[0] = 4.0;
    std::cout << v1[0] << " " << v2[0] << "\n";

    return 0;
}
