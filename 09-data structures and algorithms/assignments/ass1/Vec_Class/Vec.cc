// Vec.cc
#include "Vec.h"
using namespace std;

Vec::Vec() {
	array_current_size = array_limit = 0;
	pointer_to_data = 0;
}

// Constructor
Vec::Vec(int a) {
	array_current_size = 0;
	array_limit = a;

	// Allocate new memory for array.
	pointer_to_data = new double[array_limit];
}

// Overloading the copy constructor.
Vec::Vec(const Vec& vec_to_copy) {
	array_current_size = vec_to_copy.array_current_size;
	array_limit = vec_to_copy.array_limit;

	// Allocate new memory for array.
	pointer_to_data = new double[array_limit];

	// Copy data across.
	for (int i = 0; i != array_current_size; ++i) {
		pointer_to_data[i] = vec_to_copy.pointer_to_data[i];
	}
}

// Destructor
Vec::~Vec() {
	// Free array memory
	delete[] pointer_to_data;
	// Set pointer to null
	pointer_to_data = 0;
	array_current_size = array_limit = 0;
} 

// Overloading the assignment operator.
Vec& Vec::operator=(const Vec& rhs) {
	// Check for self-assignment.
	if (&rhs != this) {
		// Free lhs array memory.
		delete[] pointer_to_data;

		// Allocate new memory for lhs array.
		array_current_size = rhs.array_current_size;
		array_limit = rhs.array_limit;
		pointer_to_data = new double[array_limit];

		// Copy rhs data across.
		for (int i = 0; i != array_current_size; ++i) {
			pointer_to_data[i] = rhs.pointer_to_data[i];
		}
	}		
	return *this;
}

// Overloading the index operator
double& Vec::operator[](int i) {
	return pointer_to_data[i];
}

void Vec::push_back(const double& a) {
 
    // check if we need to resize the array
    if (array_current_size == array_limit) {
        grow();
    }

    // Add the new element and update the array size
    pointer_to_data[array_current_size] = a;
    array_current_size += 1;

}

void Vec::grow() {
    
    // allocate a new array with twice the original space
    if (array_limit == 0)
        array_limit = 1; 
    else 
        array_limit *= 2;

    double* new_array = new double[array_limit];
    
    // copy existing array elements into the new array 
    for (int i = 0; i < array_current_size; i++)
        new_array[i] = pointer_to_data[i];

    // free space from the old array 
    delete[] pointer_to_data;

    // assign new values to the member vars
    pointer_to_data = new_array;
}
