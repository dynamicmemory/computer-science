template<class T>

class Node {

public:
    Node(): key() {
        free = 0;

        for (int i = 0; i != 4; i++)
            next[i] = i + 1;
        next[3] = -1;
    }

    T key;
    int free;
    int next[4];
    int prev[4];
};

 
