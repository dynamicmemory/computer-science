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
	//cin.get();
	return option;
}

int main()
{
	list<string> flight_list;
	list<string>::iterator i1, i2;
	string name;

	while (true) 
	{
		switch (menu())
		{
			case 1:	
				{
					ifstream input("ticket_reservations.dat");
					while (input >> name) 
					{					
						flight_list.push_back(name);
					}
					input.close();
					break;
				}

			case 2: 
				{
					cout << "name of passenger:" << endl; 
					cin >> name;
					flight_list.push_back(name);
					break;
				}

			case 3: 
				{
					cout << "name of passenger:" << endl;
					cin >> name;
					flight_list.remove(name);
					break;
				}

			case 4: 
				{
					cout << "name of passenger:" << endl;
					cin >> name;
					i1 = flight_list.begin();
					i2 = flight_list.end();
					if (find(i1, i2, name) != i2)
						cout << "this passenger has a ticket reservation" << endl;
					else
						cout << "this passenger does not have a ticket reservation" << endl;
					break;
				}

			case 5:	
				{
					flight_list.sort();
					i1 = flight_list.begin();
					i2 = flight_list.end();
					for ( ; i1 != i2; ++i1) {
						cout << *i1 << endl;
					}
					break;
				}

			case 6: 
				{
					flight_list.sort();
					i1 = flight_list.begin();
					i2 = flight_list.end();
					ofstream output("ticket_reservations.dat");
					for ( ; i1 != i2; ++i1) {
						output << *i1 << " ";
					}
					output.close();
				}
				break;

			case 7:
				return 0;
		}
	}
	
	return 0;
}

