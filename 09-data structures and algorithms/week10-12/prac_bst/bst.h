#include <iostream>

template<class T>
class Node {
public:
	Node() : key(), parent(nullptr), left(nullptr), right(nullptr) {}
	Node(T k, Node<T>* par = nullptr, Node<T>* l = nullptr, Node<T>* r = nullptr):
        key(k), parent(par), left(l), right(r) {}

	T key;
	Node<T> *parent, *left, *right;	
};

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
    Node<T>* recursive_search(Node<T>*, T);
	Node<T>* search(Node<T>*, T);
	Node<T>* minimum(Node<T>*);
	void transplant(Node<T>*, Node<T>*);
	void remove(Node<T>*);	
    void preorder_walk();
    void preorder_walk(Node<T>*);
    void postorder_walk();
    void postorder_walk(Node<T>*);
private:
	Node<T>* root;
};

template<class T>
Binary_Search_Tree<T>::~Binary_Search_Tree() {
    postorder_delete();
}

template<class T>
bool Binary_Search_Tree<T>::empty() {
    return root == nullptr;
}

template<class T>
void Binary_Search_Tree<T>::insert(T k) {
    insert(new Node<T>(k));
}

template<class T>
void Binary_Search_Tree<T>::insert(Node<T>* p) {
	Node<T>* parent = nullptr;
	Node<T>* q = root;
	
	while (q != nullptr) {
		parent = q;
		if (p->key < q->key)
			q = q->left;
		else
			q = q->right;
	}
	p->parent = parent;
	if (parent == nullptr)
		root = p;
	else if (p->key < parent->key)
		parent->left = p;
	else
		parent->right = p;
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
    postorder_delete(root);
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
    search(root, k);
}

template<class T>
Node<T>* Binary_Search_Tree<T>::recursive_search(Node<T>* p, T k) {

    if (p == nullptr || p->key == k)
        return p;
    else if (k < p->key)
        recursive_search(p->left, k);
    else
        recursive_search(p->right, k);
			
}

template<class T>
Node<T>* Binary_Search_Tree<T>::search(Node<T>* p, T k) {
	while (p != nullptr && k != p->key) {
		if (k < p->key)
			p = p->left;
		else
			p = p->right;
	}		
	return p;
}

template<class T>
Node<T>* Binary_Search_Tree<T>::minimum(Node<T>* p) {
	while (p->left != nullptr)
		p = p->left;
	
	return p;
}

template<class T>
void Binary_Search_Tree<T>::transplant(Node<T>* p, Node<T>* q) {
	if (p->parent == nullptr)
		root = q;
	else if (p == (p->parent)->left)
		(p->parent)->left = q;
	else
		(p->parent)->right = q;
	
	if (q != nullptr)
		q->parent = p->parent;
}

template<class T>
void Binary_Search_Tree<T>::remove(Node<T>* p) {
	if (p->left == nullptr)
		transplant(p, p->right);
	else if (p->right == nullptr)
		transplant(p, p->left);
	else {
		Node<T>* q = minimum(p->right);
		if (q->parent != p) {
			transplant(q, q->right);
			q->right = p->right;
			(q->right)->parent = q;
		}
		transplant(p, q);
		q->left = p->left;
		(q->left)->parent = q;
	}
	delete p;
}

template<class T>
void Binary_Search_Tree<T>::preorder_walk() {
    preorder_walk(root), std::cout << std::endl;
}

template<class T>
void Binary_Search_Tree<T>::preorder_walk(Node<T>* p) {

}

template<class T>
void Binary_Search_Tree<T>::postorder_walk() {
    postorder_walk(root), std::cout << std::endl;
}

template<class T>
void Binary_Search_Tree<T>::postorder_walk(Node<T>* p) {

}
