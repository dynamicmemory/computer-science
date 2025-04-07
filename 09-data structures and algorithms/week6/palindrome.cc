#include <iostream>
#include <stack>
#include <queue>
#include <cctype>

bool ispalindrome(std::string word) {
    std::stack<char> s;
    std::queue<char> q;
    
    for(auto it = word.begin(); it != word.end(); it++)
        if (isalpha(*it)) {
            s.push(toupper(*it));
            q.push(toupper(*it));
        }

    bool palindrome = true;
    while (!s.empty()) {
        if (s.top() != q.front()) {
            palindrome = false;
            break;
        }
        s.pop();
        q.pop();
    }
    return palindrome;
}

int main() {
    std::string word1 = "A man, a plan, a canal: Panama";
    std::string word2 = "race car";
    std::string word3 = "roll";

    std::cout << "Is '" << word1 << "' a palindrome? " << 
        ((ispalindrome(word1)) ? "Yes" : "No")  << '\n';

    std::cout << "Is '" << word2 << "' a palindrome? " << 
        ((ispalindrome(word2)) ? "Yes" : "No")  << '\n';

    std::cout << "Is '" << word3 << "' a palindrome? " << 
        ((ispalindrome(word3)) ? "Yes" : "No")  << '\n';
    return 0;
}
