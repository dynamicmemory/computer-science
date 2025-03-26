template<class T>

class Node {

public:
    Node() : data(), next(nullptr) {}
    Node(T value) : data(value), next(nullptr) {}
    ~Node() {}
    T data;
    Node<T>* next;
};
