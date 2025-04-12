#include <iostream>
#include "node.cc"

template<class T>
class Binary_Search_Tree {

public:
    Binary_Search_Tree(): root(nullptr) {}
    ~Binary_Search_Tree();
    bool empty(); 
    void insert(T k);
    void insert(Node<T>*);
    void inorder_walk();
    void inorder_walk(Node<T>*);
    void postorder_delete();
    void postorder_delete(Node<T>*);
    Node<T>* search(T k);
    Node<T>* search(Node<T>*, T k);
    Node<T>* minimum(Node<T>*);
    void transplant(Node<T>*, Node<T>*);
    void remove(Node<T>*);
private:
    Node<T>* root;
};

template<class T>
Binary_Search_Tree<T>::~Binary_Search_Tree() {
    return postorder_delete();
}

template<class T>
bool Binary_Search_Tree<T>::empty() {
    return root == nullptr;
}

template<class T>
void Binary_Search_Tree<T>::insert(T k) {
    return insert(new Node<T>(k));
}

template<class T>
void Binary_Search_Tree<T>::insert(Node<T>* ) {
}

template<class T>
void Binary_Search_Tree<T>::inorder_walk() {
    inorder_walk(root), std::cout << std::endl;
}

template<class T>
void Binary_Search_Tree<T>::inorder_walk(Node<T>* p) {

    if (p != nullptr) {
        inorder_walk(p->left);
        std::cout << p->key << " ";
        inorder_walk(p->right);
    }
}

template<class T>
void Binary_Search_Tree<T>::postorder_delete() {
    return postorder_delete(root);
}

template<class T>
void Binary_Search_Tree<T>::postorder_delete(Node<T>* p) {
    
    if (p != nullptr) {
        postorder_delete(p->left);
        postorder_delete(p->right);
        delete p;
    }
}

template<class T>
Node<T>* Binary_Search_Tree<T>::search(T k) {
    return search(root, k);
}

template<class T>
Node<T>* Binary_Search_Tree<T>::search(Node<T>* p, T k) {

    while (p != nullptr && k != p->key) {
        if (k < p->key)
            p = p->left;
        else
            p = p->right;
    }
}

template<class T>
Node<T>* Binary_Search_Tree<T>::minimum(Node<T>* p) {

    while (p->left != nullptr)
        p = p->left;

    return p;
}

template<class T>
void Binary_Search_Tree<T>::transplant(Node<T>* p, Node<T>* p1) {

}

template<class T>
void Binary_Search_Tree<T>::remove(Node<T>* p) {

}
