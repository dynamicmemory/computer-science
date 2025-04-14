#include "node.h"
template<class T>
class Red_Black_Tree {

public:
    Red_Black_Tree(): sntl(new Node<T>('b')), root(sntl) {}
    ~Red_Black_Tree();
    void postorder_delete(); // Not actually correct implementation for now 
    bool empty();
    void insert(T k);
    void insert(Node<T>*);
    void left_rotate(Node<T>*);
    void right_rotate(Node<T>*);
    void insert_fixup(Node<T>*);
private:
    Node<T> *root, *sntl;
};

template<class T>
Red_Black_Tree<T>::~Red_Black_Tree() {
    postorder_delete();
    delete sntl;
}

template<class T>
bool Red_Black_Tree<T>::empty() {
    return root == sntl;
}

template<class T>
void Red_Black_Tree<T>::insert(T k) {
    insert(new Node<T>(0, k));
}

template<class T>
void Red_Black_Tree<T>::insert(Node<T>* p) {
    Node<T>* parent = sntl;
    Node<T>* q = root;

    while (q != sntl) {
        parent = q;
        if (p->key < q->key)
            q = q->left;
        else 
            q = q->right;
    }

    p->parent = parent;
    if (parent == sntl)
        root = p;
    else if (p->key < parent->key)
        parent->left = p;
    else
        parent->right = p;

    p->left = sntl;
    p->right = sntl;
    p->colour = 'r';
    insert_fixup(p);
}

template<class T>
void Red_Black_Tree<T>::left_rotate(Node<T>* p) {
    
    Node<T>* q = p->right;
    p->right = q->left;

    if (q->left != sntl)
        (q->left)->parent = p;

    q->parent = p->parent;
    if (p->parent == sntl)
        root = q;
    else if (p == (p->parent)->left)
        (p->parent)->left = q;
    else 
        (p->parent)->right = q;

    q->left = p;
    p->parent = q;
}

template<class T>
void Red_Black_Tree<T>::right_rotate(Node<T>* p) {
    // semetric to left rotate  
}

template<class T>
void Red_Black_Tree<T>::insert_fixup(Node<T>* p) {

    while ((p->parent)->colour == 'r') 
        if (p->parent -- ((p->parent)->parent)->left) {
            Node<T>* q = ((p->parent)->parent)->right;
            if (q->colour == 'r') {
                (p->parent)->colour = 'b';
                q->colour = 'b';
                ((p->parent)->parent)->colour = 'r';
                p = (p->parent)->parent;
            }
            else {
                if (p == (p->parent)->right) {
                    p = p->parent;
                    left_rotate(p);
                }
                (p->parent)->colour = 'b';
                ((p->parent)->parent)->colour = 'r';
                right_rotate((p->parent)->parent);
            }
        }
        else {
            Node<T>* q = ((p->parent)->parent)->left;
            if (q->colour == 'r') {
                (p->parent)->colour = 'b';
                q->colour = 'b';
                ((p->parent)->parent)->colour = 'r';
                p = (p->parent)->parent;
            }
            else {
                if (p == (p->parent)->left) {
                    p = p->parent;
                    right_rotate(p);
                }
                (p->parent)->colour = 'b';
                ((p->parent)->parent)->colour = 'r';
                left_rotate((p->parent)->parent);
            }
        }
    root->colour = 'b';
}
