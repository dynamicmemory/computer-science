template<class T>
class Node {

public:
    Node(): colour(), key(), parent(nullptr), left(nullptr), right(nullptr){}
    Node(char c, T k = T(), Node<T>* par = nullptr, Node<T>* l = nullptr, 
            Node<T>* r = nullptr): colour(c), key(k), parent(par), left(l), right(r) {}

    char colour;
    T key;
    Node<T> *parent, *left, *right;
};
