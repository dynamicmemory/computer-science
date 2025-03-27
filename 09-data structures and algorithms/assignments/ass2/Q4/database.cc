#include<list>
#include<algorithm>
#include<iostream>
#include<string>
#include<fstream>
using namespace std;

int menu()
{
	int option;
	cout << endl;
	cout << "Enter one of the following options:" << endl;
	cout << "1. load reservations from file:" << endl;
	cout << "2. reserve a ticket" << endl;
	cout << "3. cancel a reservation" << endl;
	cout << "4. check reservation" << endl;
	cout << "5. display passenger list" << endl; 
	cout << "6. save passenger list" << endl;
	cout << "7. exit" << endl << endl;
	cin >> option;
	cin.get();
	return option;
}

void read_from_file(list<string>& flist, string filename)
{
	string name;
	ifstream input(filename.c_str());
	while (input >> name) 
	{					
		flist.push_back(name);
	}
	input.close();
}

void insert(list<string>& flist, string name)
{
	flist.push_back(name);
}

void remove(list<string>& flist, string name)
{
	flist.remove(name);
}

bool check_reservation(list<string>& flist, string name)
{
	list<string>::iterator i1, i2;
	i1 = flist.begin();
	i2 = flist.end();
	return (find(i1, i2, name) != i2);
}

void display_list(list<string>& flist)
{
	flist.sort();
	list<string>::iterator i1, i2;
	i1 = flist.begin();
	i2 = flist.end();
	for ( ; i1 != i2; ++i1) {
		cout << *i1 << endl;
	}
}

void save_to_file(list<string>& flist, string filename)
{
	flist.sort();
	list<string>::iterator i1, i2;
	i1 = flist.begin();
	i2 = flist.end();
	ofstream output(filename.c_str());
	for ( ; i1 != i2; ++i1) {
		output << *i1 << " ";
	}
	output.close();
}

