#include <iostream>

// Array based implementation of a stack in c++
/*
template<class T>
class Stack {

public:
    Stack(): top(-1) {}
    bool empty();
    void push(T);
    T pop();

private:
    int top;
    T S[10];
};


template<class T>
bool Stack<T>::empty() {
    return top == -1;
}

template<class T>
void Stack<T>::push(T x) {
    if (top == 9)
        throw;
    top++;
    S[top] = x;
}

template<class T>
T Stack<T>::pop() {
    if (empty())
        throw;
    top--;
    T temp = S[top + 1];

    return temp;
}
*/ 

// Pointer-based implementation of stack in c++

template<class T>
class Node {

public:
    Node(): key(), next(nullptr) {}
    Node(T a, Node<T>* b = 0) : key(a), next(b) {}
    T key;
    Node<T>* next;
};

template<class T>
class Stack {

public:
    Stack(): head(nullptr) {}
    ~Stack();
    bool empty();
    void push(T);
    T pop();

private:
    Node<T>* head;
};


template<class T>
bool Stack<T>::empty() {
    return head == nullptr;
}


template<class T>
Stack<T>::~Stack() {
    while (!empty())
        pop();
}


template<class T>
void Stack<T>::push(T k) {
    head = new Node<T>(k, head);
}

template<class T>
T Stack<T>::pop() {

    if (empty())
        throw;
    Node<T>* p = head;
    head = head->next;
    T temp = p->key;
    delete p;

    return temp;
}


int main() {
    //creating an array stack 
    Stack<int> S;

    S.push(3), S.push(2), S.push(1);

    while (!S.empty())
        std::cout << S.pop() << " ";
    std::cout << std::endl;

    return 0;

    //creating a pointer stack  





}

