#include "node.h"
template<class T>

class Linked_List {

public:
    Linked_List(): head(-1) {}
    bool empty();
    void insert(Node<T>&, T);
    void remove(Node<T>&, int);
    void remove_from_head(Node<T>& na);

private:
    int head;
};

template<class T>
bool Linked_List<T>::empty() {
    return head == -1;
}

template<class T>
void Linked_List<T>::remove_from_head(Node<T>& NA) {
    remove(NA, head);
}

template<class T>
void Linked_List<T>::insert(Node<T>& NA, T key) {

    // check if there is any room in the array
    if (NA.free == -1)
        throw;
    int p = NA.free;
    NA.free = NA.next[p];
     
    NA.key = key;
    NA.next[p] = head;
    if (!empty())
        NA.prev[head] = p;
    
    NA.prev[p] = -1;
    head = p;
}

template<class T>
void Linked_List<T>::remove(Node<T>& NA, int p) {
    if (p == head) {
        head = NA.next[p];
        if (!empty())
            NA.prev[head] = -1;
    }
    else {
        if (NA.prev[p] != -1)
            NA.next[NA.prev[p]] = NA.next[p];
        if (NA.next[p] != -1)
            NA.prev[NA.next[p]] = NA.prev[p];
    }

    NA.next[p] = NA.free;
    NA.free = p;
}
