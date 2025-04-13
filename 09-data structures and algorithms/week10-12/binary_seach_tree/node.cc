template<class T>
class Node {

public:
    Node(): key(), parent(nullptr), left(nullptr), right(nullptr) {}
    Node(T k, Node<T>* par = nullptr, Node<T>* l = nullptr, Node<T>* r = nullptr) :
        key(k), parent(par), left(l), right(r) {}
    T key;
    Node<T> *parent; 
    Node<T> *left;
    Node<T> *right; 
};
