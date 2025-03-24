#include "linkedlist.h"

template<class T>

// Deconstructs and erases the list from existance
LinkedList<T>::~LinkedList() {
    while (!empty()) 
        remove_from_head();
}

// Checks to see if a list is empty of not
bool LinkedList<T>::empty() {
   return head == nullptr;
}

// Removes the first element in the list
void LinkedList<T>::remove_from_head() {
    Node<T>* temp = head;
    head = head->next;
    delete temp;
}

void LinkedList<T>::insert(T value) {
    // Create a new node passing in the value and insert it into the list 
    insert(new Node<T>(value))
}

void LinkedList<T>::insert_end(T value) {
    // Create a new node with the value inserted 
    Node<T>* new_node = new Node<T>(value);

    // Check to see if its the first element in the list or not
    if (empty()) {
        head = new_node;
        return;
    }

    // Create a pointer to point at the head of the list 
    Node<T>* current = head;

    // Walk the new pointer through the list to the list element 
    while (current->next != nullptr) 
        current = current->next;

    // Set the pointer to the next element as the new element.... tada. 
    current->next = new_node; 
}

void LinkedList<T>::insert(Node<T>* value) {
    // Assign next to point to the current head 
    value->next = head;

    // Assign the head to the new start of the list 
    head = value;
}

bool LinkedList<T>::search(T value) {
    if (head != nullptr && head->data == value)
        return true;

    Node<T>* current = head;
    while (current != nullptr) {
        if (current->data == value)
            return true;
        current->next = current;
    }

    return false;
}

void LinkedList<T>::remove(T value) {
    // Check that the head isnt an empty boi or contains our value
    if (head != nullptr && head->data == value)
        remove_from_head();
        return;
    
    // Create a coulpe of pointers for some future switcharooing
    Node<T>* previous = head;
    Node<T>* current = previous->next;

    // if the current node isnt null then check its data
    while (current != nullptr) {

        // If we find a match then we want to point the previous node to the 
        // next one and delete the current node 
        if (current->data == value) {
            previous->next = current->next;
            delete current;
            return;
            }    
        // walk down that list with more switcharoooing
        previous = current;
        current = current->next;
}
