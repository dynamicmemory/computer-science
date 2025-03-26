#include "node.h"
#include <filesystem>

template<class T>
class LinkedList {

public:
    LinkedList() : head(-1) {}
    bool empty();
    void insert(Node<T>&, T);
    void remove(Node<T>&, int);
    void remove_from_head(Node<T>& NA);

private:
    int head;
};

// Returns if head is null or not, or neg or not
template<class T>
bool LinkedList<T>::empty() {
    return head == -1;
}


// Removes the head of the array 
template<class T>
void LinkedList<T>::remove_from_head(Node<T>& NA) {
    remove(NA, head);
}


template<class T>
void LinkedList<T>::remove(Node<T>& NA, int p) {

    if (NA.prev[p] != -1)
        NA.next[NA.prev[p]] = NA.next[p]; 
    else
        head = NA.next[p];

    if (NA.next[p] != -1)
        NA.prev[NA.next[p]] = NA.prev[p];

    NA.next[p] = free;
    NA.free = p;
}


template<class T>
void LinkedList<T>::insert(Node<T>& NA, T k) {
    
    if (NA.free == -1)
        throw;

    int p = NA.free;
    NA.free = NA.next[p];

    NA.key[p] = k;
    NA.next[p] = head;

    // If the head is not empty, then there was a previous 
    if (!empty())
        NA.prev[head] = p;

    head = p;
    NA.prev[p] = -1;
}
