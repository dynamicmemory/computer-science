#include <iostream>
#include <fstream>
#include <list>
#include <map>

int main() {
    
    // Create map to store all words Type map[string, list[int]]
    std::map<std::string, std::list<int>> words;
    std::ifstream file("./../text_excerpt.txt");

    if (!file) {
        std::cerr << "Failed to open da file. \n";
        return 1;
    }

    std::string letter;
    std::string word = "";
    int lineNumber = 1;
    while (std::getline(file, letter)) {
       if (!(letter == " ")) 
           word += letter;
       else 
            if (words.find(word) == words.end()) {
                std::list<int> ls;
                ls.push_back(lineNumber);
                words.insert(word, ls);
            }
            else { 
                words.find(word).second.push_back(lineNumber);
                word = "";
            }

       if (letter == "\n")
           lineNumber += 1;
    }

    file.close();
    return 0;
}
