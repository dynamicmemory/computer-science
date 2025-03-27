/* General single linked-list class */

template<class T>
class Node {
public:
	Node() : key() { next = 0; }
	Node(T a, Node<T>* b = 0) { key = a; next = b; }
	T key;
	Node<T>* next;	
};

template<class T>
class Linked_List {
public:
	Linked_List() { head = 0; }	
	~Linked_List();
	bool empty() { return head == 0; }
	void insert(T);
	void insert(Node<T>*);
	bool search(T);
	void remove(T);
	void remove_from_head();
	void reverse_list();
	void print_list();
private:
	Node<T>* head;
};

template<class T>
Linked_List<T>::~Linked_List()	
{
	while (!empty()) 
		remove_from_head();
}

// Insert new node with key "k" at head
template<class T>
void Linked_List<T>::insert(T k)	
{
	insert(new Node<T>(k));
}

// Insert a node at head
template<class T>
void Linked_List<T>::insert(Node<T>* p)	
{
	p->next = head;
	head = p;
}

// Delete node from head
template<class T>
void Linked_List<T>::remove_from_head()
{
	Node<T>* p = head;
	head = head->next;
	delete p;
}

// Search list for node with key "k"
template<class T>
bool Linked_List<T>::search(T k)
{
	Node<T>* p = head;
	for ( ; p != 0 && p->key != k; p = p->next);
	return p != 0;
}

// Delete node with key "k"
template<class T>
void Linked_List<T>::remove(T k)
{
	// List assumed not empty
	// If k is in head
	if (head->key == k) 
		remove_from_head();
	else {
		// k is not in head
		Node<T>* pred = head;
		Node<T>* p = head->next;
		for ( ; p != 0 && p->key != k; pred = pred->next, p = p->next);
		if (p != 0)	{ 	
			// k is in list
			pred->next = p->next;
			delete p; 
		}
	}
}

template<class T>
void Linked_List<T>::reverse_list() {
    
    Node<T>* current = head;
    Node<T>* next = nullptr;
    Node<T>* prev = nullptr;

    // switch next to point to what previously came in the list until null.
    while (current) {
            next = current->next;                
            current->next = prev; 
            prev = current;
            current = next;
    }
    head = prev;
}

