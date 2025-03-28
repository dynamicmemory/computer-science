// Node Array class (length 4) replaces Node class 
template<class T>
class Node_Array {
public:
	Node_Array(): key() 
	{ 
		// Initialize Node Array as "free" list
		free = 0;	
		for (int i = 0; i != 4; i++) {
			next[i] = i + 1;
		}
		next[3] = -1;
	}
	int free; 	// head of "free" list
	T key[4];
	int prev[4], next[4];
};

template<class T>
class Array_Linked_List {
public:
	Array_Linked_List() { head = -1; }	// -1 represents a Null array index
	bool empty() { return head == -1; }
	void insert(Node_Array<T>&, T);
	void remove(Node_Array<T>&, int);
	void remove_from_head(Node_Array<T>& NA) { remove(NA, head); }
private:
	int head;
};
