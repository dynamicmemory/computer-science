template<class T>
class Node {

public:
    Node() : key(), next(nullptr), prev(nullptr) {}
    Node(T value, Node<T>* next_node = nullptr, Node<T>* prev_node = nullptr) :
        key(value), next(next_node), prev(prev_node) {}

    T key;
    Node<T>* next;
    Node<T>* prev;
};
