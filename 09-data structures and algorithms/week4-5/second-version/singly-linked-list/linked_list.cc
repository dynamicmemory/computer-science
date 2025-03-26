#include "node.h"

template<class T>
class Linked_List {

public:
    Linked_List() : head(nullptr) {}
    ~Linked_List();

    bool empty();
    void insert(T);
    void insert(Node<T>*);
    bool search(T);
    void remove(T);
    void remove_from_head();

private:
    Node<T>* head;
};

template<class T>
Linked_List<T>::~Linked_List() {
    while (!empty())
        remove_from_head();
}

template<class T>
bool Linked_List<T>::empty() {
    return head == nullptr;
}

template<class T>
void Linked_List<T>::insert(Node<T>* p) {
    // Make the new nodes ref to next, what the head of the list currently is 
    p->next = head;
    // Make the head of the list, the new node.
    head = p;
}

template<class T>
void Linked_List<T>::insert(T value) {
    // This is smarter as a nodes constructor takes val, pointer.
    insert(new Node<T>(value));
}

template<class T>
void Linked_List<T>::remove_from_head() {
    Node<T>* temp = head;
    head = head->next;
    delete temp;
}

template<class T>
bool Linked_List<T>::search(T value) {

    /*// My implementation*/
    /*Node<T>* temp = head;*/
    /*while (temp != nullptr) {*/
    /*    if (temp->key == value)*/
    /*        return true;*/
    /**/
    /*    temp = temp->next;*/
    /*}*/
    /*return false;*/
    /**/

    // Lectures implementation 
    Node<T>* p = head;
    for ( ; p != nullptr && p->key != value; p = p->next)
        ;
    return p != nullptr;
}

template<class T> 
void Linked_List<T>::remove(T value) {

    if (head->key == value)
        remove_from_head();
    else {
        Node<T>* prev = head;
        Node<T>* p = head->next;

        for ( ; p != nullptr && p->key != value; prev = p, p = p->next)
            ;

        if (p != nullptr) {
            prev->next = p->next;
            delete p;
        }
    }
}





















