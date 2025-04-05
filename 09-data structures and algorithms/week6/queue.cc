#include <iostream>

// Pointer based implementation
template<class T>
class Node {

public:
    Node() : key(), next(0) {}
    Node(T a, Node<T>* b = nullptr) : key(a), next(b) {}
    T key;
    Node<T>* next;
};

template<class T>
class Queue {

public:
    Queue() : head(nullptr), tail(nullptr) {}
    ~Queue();
    bool empty();
    void enqueue(T);
    T dequeue();

private:
    Node<T> *head;
    Node<T> *tail;
};

template<class T>
Queue<T>::~Queue() {
    while (!empty())
        dequeue();         
}

template<class T>
bool Queue<T>::empty() {
    return head == nullptr;
}

template<class T>
void Queue<T>::enqueue(T k) {
    
    if (tail != nullptr) {
        tail->next = new Node<T>(k, nullptr);
        tail = tail->next;
    }
    else {
        head = new Node<T>(k, head);
        tail = head;
    }
}

template<class T>
T Queue<T>::dequeue() {

    Node<T>* p = head;
    head = head->next;
    T temp = p->key;
    delete p;
    if (head == 0)
        tail = 0;

    return temp;
}

int main() {

    // Array based 
    // Construct to queue 
    Queue<int> Q;

    // Insert keys 
    Q.enqueue(1), Q.enqueue(2), Q.enqueue(3);

    // Print 
    while (!Q.empty())
        std::cout << Q.dequeue() << " ";
    std::cout << std::endl;


    // pointer based 
     
    return 0;
     
}



// Array based queue implementation
template<class T>
class Queue {

public:
    Queue() : head(0), tail(0) {}
    bool empty();
    void enqueue(T);
    T dequeue();

private:
    int head;
    int tail;
    T Q[10];
};

template<class T> 
bool Queue<T>::empty() {
    return head == tail;
}

template<class T> 
void Queue<T>::enqueue(T k) {

    if (head == tail + 1 || (head == 0 && tail == N))
        throw;

    Q[tail] = k;
    if (tail == N)
        tail = 0;
    else 
        tail++; 
}

template<class T>
T Queue<T>::dequeue() {
    
    if (empty())
        throw;
    T temp = Q[head];
    if (head == N)
        head = 0;
    else 
        head++;

    return temp;
}

