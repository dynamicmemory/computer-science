template<class T> 

class Node {

public:
    Node() : data(), next(nullptr), prev(nullptr) {} 
    Node(T value) : data(value), next(nullptr), prev(nullptr) {} 
    ~Node() {}

    T data;
    Node<T>* next;
    Node<T>* prev;
};
