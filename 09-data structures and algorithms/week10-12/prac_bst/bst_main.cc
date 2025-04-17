#include "bst.h"
#include <list>

int main(int argc, char* argv[]) {

    Binary_Search_Tree<int> btree;
    std::list<int> ls = {14,3,8,44,7,2,99,5,9,1,4};

    for (int i : ls) 
        btree.insert(i);

    btree.inorder_walk();
    btree.preorder_walk();
    btree.postorder_walk();
    btree.inorder_leaf_height();
}
