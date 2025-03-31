#include "database.cc"

int main()
{
	list<string> flight_list;
	string name;

	while (true) 
	{
		switch (menu())
		{
			case 1:	
				{
					read_from_file(flight_list, "ticket_reservations.dat");
					break;
				}

			case 2: 
				{
					cout << "name of passenger:" << endl; 
					cin >> name;
					insert(flight_list, name);
					break;
				}

			case 3: 
				{
					cout << "name of passenger:" << endl;
					cin >> name;
					remove(flight_list, name);
					break;
				}

			case 4: 
				{
					cout << "name of passenger:" << endl;
					cin >> name;
					if (check_reservation(flight_list, name))
						cout << "this passenger has a ticket reservation" << endl;
					else
						cout << "this passenger does not have a ticket reservation" << endl;
					break;
				}

			case 5:	
				{
					display_list(flight_list);
					break;
				}

			case 6: 
				{
					save_to_file(flight_list, "ticket_reservations.dat");
				}
				break;

			case 7:
				return 0;
		}
	}
	
	return 0;
}

