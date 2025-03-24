#include "node.h"

template<class T>
class LinkedList {

public:
    LinkedList() : head(nullptr), tail(nullptr) {}
    ~LinkedList();
    
    bool empty();
    T front();
    void remove_from_head();
    void insert_at_tail(T);

private:
    Node<T> *head, *tail;
};


template<class T>
LinkedList<T>::~LinkedList<T>() {
    while (!empty())
        remove_from_head();
}

template<class T>
bool LinkedList<T>::empty() {
    return head == nullptr;
}

template<class T>
T LinkedList<T>::front() {
    return head->data;
}

template<class T>
void LinkedList<T>::remove_from_head() {
    if (head == tail)
        delete tail;

    Node<T>* temp = head; 
    head = head->next;
    delete temp;
}

template<class T>
void LinkedList<T>::insert_at_tail(T) {
    Node<T>* temp = head;
    for ( ; head->next != nullptr; head = head->next)
        ;
   tail = head->next;
   head = temp;
}
