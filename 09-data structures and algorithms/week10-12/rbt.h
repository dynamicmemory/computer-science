#include <iostream>

template<class T>
class Node {
public:
	Node() : color(), key() { par = 0, left = 0, right = 0; }
	Node(char e, T a = T(), Node<T>* b = 0, Node<T>* c = 0, Node<T>* d = 0) 
	{ 
		color = e, key = a, par = b, left = c, right = d; 
	}
	char color;
	T key;
	Node<T> *par, *left, *right;	
};

template<class T>
class Red_Black_Tree {
public:
	Red_Black_Tree() { sntl = new Node<T>('b'), root = sntl; }	
	~Red_Black_Tree() { postorder_delete(), delete sntl; }
	bool empty() { return root == sntl; }
	void insert(T k) { insert(new Node<T>(0, k)); } 
	void insert(Node<T>*);							
	void inorder_walk() { inorder_walk(root, 0), std::cout << std::endl; }
	void inorder_walk(Node<T>*, int);
	void postorder_delete() { postorder_delete(root); }
	void postorder_delete(Node<T>*);
	Node<T>* search(T k) { return search(root, k); }
	Node<T>* search(Node<T>*, T);
	Node<T>* minimum(Node<T>*);
	void left_rotate(Node<T>*);
	void right_rotate(Node<T>*);
	void insert_fixup(Node<T>*);
private:
	Node<T> *root, *sntl;
};

template<class T>
void Red_Black_Tree<T>::insert(Node<T>* p)
{
	Node<T>* parent = sntl;
	Node<T>* q = root;
	
	while (q != sntl) {
		parent = q;
		if (p->key < q->key)
			q = q->left;
		else
			q = q->right;
	}
	p->par = parent;
	if (parent == sntl)
		root = p;
	else if (p->key < parent->key)
		parent->left = p;
	else
		parent->right = p;
	p->left = sntl;
	p->right = sntl;
	p->color = 'r';
	insert_fixup(p);
}

template<class T>
void Red_Black_Tree<T>::inorder_walk(Node<T>* p, int counter)
{
	counter++;
	if (p != sntl) {
		inorder_walk(p->left, counter);
		std::cout << p->key << " " << "(" << counter-1 << ")" << " ";
		inorder_walk(p->right, counter);
	}	
}

template<class T>
void Red_Black_Tree<T>::postorder_delete(Node<T>* p)
{
	if (p != sntl) {
		postorder_delete(p->left);
		postorder_delete(p->right);
		delete p;
	}	
}

template<class T>
Node<T>* Red_Black_Tree<T>::search(Node<T>* p, T k)
{
	if (p == sntl || k == p->key)
		return p;
	if (k < p->key)
		return search(p->left, k);
	else
		return search(p->right, k);
}

template<class T>
Node<T>* Red_Black_Tree<T>::minimum(Node<T>* p)
{
	while (p->left != sntl)
		p = p->left;
	
	return p;
}

template<class T>
void Red_Black_Tree<T>::left_rotate(Node<T>* p)
{
	Node<T>* q = p->right;
	p->right = q->left;
	if (q->left != sntl)
		(q->left)->par = p;
	q->par = p->par;
	if (p->par == sntl)
		root = q;
	else if (p == (p->par)->left)
		(p->par)->left = q;
	else
		(p->par)->right = q;
	q->left = p;
	p->par = q; 
}

template<class T>
void Red_Black_Tree<T>::insert_fixup(Node<T>* p)
{
	while ((p->par)->color == 'r') {
		if (p->par == ((p->par)->par)->left) {
			Node<T>* q = ((p->par)->par)->right;
			if (q->color == 'r') {
				(p->par)->color = 'b';
				q->color = 'b';
				((p->par)->par)->color = 'r';
				p = (p->par)->par;
			}
			else { 
				if (p == (p->par)->right) {
					p = p->par;
					left_rotate(p);
				}
				(p->par)->color = 'b';
				((p->par)->par)->color = 'r';
				right_rotate((p->par)->par);
			}
		}
		else {
			Node<T>* q = ((p->par)->par)->left;
			
			// code goes here...
			
			
		}
	}
	root->color = 'b';
}
