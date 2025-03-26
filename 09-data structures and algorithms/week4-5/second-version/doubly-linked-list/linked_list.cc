#include "node.h"

template<class T>
class Linked_List {

public:
    Linked_List() : head(nullptr) {}
    ~Linked_List();

    bool empty();
    void insert(T);
    void insert(Node<T>*);
    Node<T>* search(T);
    void remove(Node<T>*);
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
    p->next = head;

    if (head != nullptr)
        head->prev = p;

    head = p;
    p->prev = nullptr;
}

template<class T>
void Linked_List<T>::insert(T value) {
    insert(new Node<T>(value));
}

template<class T>
Node<T>* Linked_List<T>::search(T value) {
    Node<T>* p = head;
    for ( ; p != nullptr && p->key != value; p = p->next)
        ;
    return p;
}

template<class T>
void Linked_List<T>::remove_from_head() {
    remove(head); 
}

template<class T>
void Linked_List<T>::remove(Node<T>* p) {
    if (p->prev != nullptr)
        (p->prev)->next = p->next;
    else 
        head = p->next;

    if (p->next != nullptr)
        (p->next)->prev = p->prev;

    delete p;
}

























