#include <cstddef>
#include <iostream>

template<class T>
class Node {
public:
	Node(): color(), key(), parent(nullptr), left(nullptr), right(nullptr) {}
	Node(char c, T k = T(), Node<T>* par = nullptr, Node<T>* l = nullptr, 
            Node<T>* r = nullptr):
		color(c), key(k), parent(par), left(l), right(r) {}
	
	char color;
	T key;
	Node<T> *parent, *left, *right;	
};

template<class T>
class Red_Black_Tree {
public:
	Red_Black_Tree(): sntl(new Node<T>('b')), root(sntl) {} 	
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
	p->parent = parent;
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
void Red_Black_Tree<T>::right_rotate(Node<T>* p)
{
	Node<T>* q = p->left;
	p->left = q->right;
	if (q->right != sntl)
		(q->right)->parent = p;
	q->parent = p->parent;
	if (p->parent == sntl)
		root = q;
	else if (p == (p->parent)->right)
		(p->parent)->right = q;
	else
		(p->parent)->left = q;
	q->right = p;
	p->parent = q; 
}

template<class T>
void Red_Black_Tree<T>::insert_fixup(Node<T>* p)
{
	while ((p->parent)->color == 'r') {
		if (p->parent == ((p->parent)->parent)->left) {
			Node<T>* q = ((p->parent)->parent)->right;
			if (q->color == 'r') {
				(p->parent)->color = 'b';
				q->color = 'b';
				((p->parent)->parent)->color = 'r';
				p = (p->parent)->parent;
			}
			else { 
				if (p == (p->parent)->right) {
					p = p->parent;
					left_rotate(p);
				}
				(p->parent)->color = 'b';
				((p->parent)->parent)->color = 'r';
				right_rotate((p->parent)->parent);
			}
		}
		else {
			Node<T>* q = ((p->parent)->parent)->left;
			if (q->color == 'r') {
				(p->parent)->color = 'b';
				q->color = 'b';
				((p->parent)->parent)->color = 'r';
				p = (p->parent)->parent;
			}
			else { 
				if (p == (p->parent)->left) {
					p = p->parent;
					right_rotate(p);
				}
				(p->parent)->color = 'b';
				((p->parent)->parent)->color = 'r';
				left_rotate((p->parent)->parent);
			}
		}
	}
	root->color = 'b';
}
