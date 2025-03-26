template<class T>
class Node {

public:
    Node() : key(), next(nullptr) {}
    Node(T value, Node<T>* next_node = nullptr) : key(value), next(next_node) {}
    T key;
    Node<T>* next;
};

