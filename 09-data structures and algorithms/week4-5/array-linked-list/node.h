template<class T>

class Node {

public:
    Node() : key() {
        // Initialize node array to be "free" list

        free = 0;
        for (int i = 0; i != 4; i++)
            next[i] = i + 1;

        next[3] = -1;
    }
    int free;
    T key[4];
    int prev[4]; 
    int next[4];
};
