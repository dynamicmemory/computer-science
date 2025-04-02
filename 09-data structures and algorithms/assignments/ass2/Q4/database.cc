#include<list>
#include<algorithm>
#include<iostream>
#include <ostream>
#include<string>
#include<fstream>
// I would remove this but unsure if it will break something on the markers end.
using namespace std;  // Bad practice 

class Passenger {

friend std::ostream& operator<<(std::ostream&, const Passenger&);

public:
    Passenger(): fname(), lname(), destination() {}
    Passenger(std::string first, std::string last) : fname(first), lname(last) {} 
    Passenger(std::string first, std::string last, std::string dest) : 
        fname(first), lname(last), destination(dest) {}
    bool operator==(const Passenger&) const;
    bool operator<(const Passenger&) const;

private:
    std::string fname;
    std::string lname;
    std::string destination;
};

std::ostream& operator<<(std::ostream& output, const Passenger& p) {
    output << p.fname << " " << p.lname << " " << p.destination;

    return output;
}

bool Passenger::operator==(const Passenger& rhs) const {
    return fname == rhs.fname && lname == rhs.lname;
}

bool Passenger::operator<(const Passenger& rhs) const {
    return lname < rhs.lname;
}

int menu(){
	int option;
    std::cout << "\nEnter one of the following options:\n"
        << "1. load reservations from file:\n"
	    << "2. reserve a ticket\n"
	    << "3. cancel a reservation\n"
	    << "4. check reservation\n"
	    << "5. display passenger list\n" 
	    << "6. save passenger list\n"
	    << "7. exit\n" << endl;
    std::cin >> option;
    std::cin.get();
	return option;
}

void read_from_file(std::list<Passenger>& flist, std::string filename) {
    std::string fname, lname, destination;
    ifstream input(filename.c_str());

    // Read in all three data memeber variables 
	while (input >> fname >> lname >> destination) {					
        // Add a new passenger object to the list from the passed in values
		flist.push_back(Passenger(fname, lname, destination));
	}
	input.close();
}

void insert(std::list<Passenger>& flist, std::string fname, std::string lname, 
        std::string destination) {

    Passenger p = Passenger(fname, lname, destination);
	flist.push_back(p);
}

void remove(list<Passenger>& flist, std::string fname, std::string lname) {
    // create a passenger object from passed in values
    Passenger p(fname, lname);    
    std::list<Passenger>::iterator i1, i2;
	i1 = flist.begin();
	i2 = flist.end();

    // iterator over the list of passengers 
	for ( ; i1 != i2; i1++) 
        // remove a passenger from the list if they match the passed in values
        if (*i1 == p) {
            flist.remove(p);
            break;
        }
}

bool check_reservation(list<Passenger>& flist, std::string fname, std::string lname) {
	list<Passenger>::iterator i1, i2;
    Passenger p(fname, lname);
	i1 = flist.begin();
	i2 = flist.end();
	return (find(i1, i2, p) != i2);
}

void display_list(std::list<Passenger>& flist) {
	flist.sort();
    std::list<Passenger>::iterator i1, i2;
	i1 = flist.begin();
	i2 = flist.end();
	for ( ; i1 != i2; ++i1) {
        std::cout << *i1 << endl;
	}
}

void save_to_file(std::list<Passenger>& flist, std::string filename) {
	flist.sort();
	list<Passenger>::iterator i1, i2;
	i1 = flist.begin();
	i2 = flist.end();
	ofstream output(filename.c_str());
	for ( ; i1 != i2; ++i1) {
		output << *i1 << "\n";
	}
	output.close();
}
