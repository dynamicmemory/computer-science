#include "node.h"

template<class T>

class LinkedList {

public:
    LinkedList() : head(nullptr)  {}
    ~LinkedList();

    bool empty();
    void remove_from_head();

    void insert(T);
    void insert(Node<T>*);
    void remove(Node<T>*);
    Node<T>* search(T);

    Node<T>* head = nullptr;
};

// wipe out the lists bloodline
template<class T>
LinkedList<T>::~LinkedList<T>() {
    while(!empty())
        remove_from_head();
}

// Check for an empty list
template<class T>
bool LinkedList<T>::empty() {
    return head == nullptr;
}

// Remove an element from the head of the list 
template<class T>
void LinkedList<T>::remove_from_head() {
    remove(head);
}

// Insert a value into the list 
template<class T>
void LinkedList<T>::insert(T data) {
    insert(new Node<T>(data));
}

// Insert a new node to the start of the list
template<class T>
void LinkedList<T>::insert(Node<T>* p) {
    p->next = head;

    if (head != nullptr)
        head->prev = p;
    
    head = p;
}

// Remove an element from the list
template<class T>
void LinkedList<T>::remove(Node<T>* p) {
    if (p->prev != nullptr) 
        (p->prev)->next = p->next;
    else 
        head = p->next;

    if (p->next != nullptr)
        (p->next)->prev = p->prev;

    delete p;
}

// Search for a value in the list 
template<class T> 
Node<T>* LinkedList<T>::search(T value) {
    Node<T>* p = head;
    while (p != nullptr) {
        if (p->data == value)
            return p;
        p = p->next;
    }
    return nullptr;
}

