/* Array-based double linked-list class (no explicit pointers) */
#include <exception>
#include <iostream> 
#include <stdexcept>
// Node Array class (length 10) replaces Node class 
template<class T>
class Node_Array {
public:
	Node_Array(): key() 
	{ 
		// Initialize Node Array as "free" list
		free = 0;	
		for (int i = 0; i != 10; i++) {
			next[i] = i + 1;
		}
		next[9] = -1;
	}
	int free; 	// head of "free" list
	T key[10];
	int prev[10], next[10];
};

template<class T>
class Array_Linked_List {
public:
	Array_Linked_List() { head = -1; }	// -1 represents a null array index
	bool empty() { return head == -1; }
	void insert(Node_Array<T>&, T);
	void remove(Node_Array<T>&, int);
	void remove_from_head(Node_Array<T>& NA) { remove(NA, head); }
	void remove(Node_Array<T>&, T);		// new member
	int search(Node_Array<T>&, T);		// new member
	void print_list(Node_Array<T>&);	// new member
private:
	int head;
};

// Insert key at head of list
template<class T>
void Array_Linked_List<T>::insert(Node_Array<T>& NA, T k)
{
	// Allocate object
	if (NA.free == -1)
		throw;				// free list is empty!
	int p = NA.free;		// p is now an index into a Node Array 
	NA.free = NA.next[p]; 	// update head of free list
	
	// Insert k into list
	NA.key[p] = k;
	NA.next[p] = head;
	if (!empty())
		NA.prev[head] = p;
	head = p;
	NA.prev[p] = -1;
}

// Delete arbitary node p 
template<class T>
void Array_Linked_List<T>::remove(Node_Array<T>& NA, int p)
{	
	// Reassign next and prev indices 
	if (NA.prev[p] != -1)
		NA.next[NA.prev[p]] = NA.next[p];
	else
		head = NA.next[p];
	if (NA.next[p] != -1)
		NA.prev[NA.next[p]] = NA.prev[p];
	
	// Free object
	NA.next[p] = NA.free;	// update "next" member of free list
	NA.free = p;			// update head of free list
}	

template<class T>
int Array_Linked_List<T>::search(Node_Array<T>& NA, T k) {
    // start from the head and move to the next node until we hit null 
    for (int p = head; p != -1; p = NA.next[p])
        // return the indedx of a node if its key matches the target
        if (NA.key[p] == k){
            return p;
        }
    return -1;
}

template<class T>
void Array_Linked_List<T>::remove(Node_Array<T>& NA, T k) {
  
    int index = search(NA, k);
    if (index >= 0 )
        remove(NA, index); 
    else  
        // Main has no way to handle errors so the throw statement below is commented
        // out and replaced with a stdout print so the program can continue running

       //throw std::invalid_argument("No node with key: \"" + k + "\" exists in the list");
        std::cout << "\nNo node with key: \"" << k << "\" exists in the list" << "\n\n";
}

template<class T>
void Array_Linked_List<T>::print_list(Node_Array<T>& NA) {
    // Start from the head and print each key until we hit a null index.
    for (int p = head; p != -1;  p = NA.next[p]) {
        std::cout << NA.key[p] << " ";
    }
}
