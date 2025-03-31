#include "database.cc"

int main()
{
	list<Passenger> flight_list;
	int flag[] = {0,0,0,0,0,0,0,0,0,0,0,0};

	insert(flight_list, "Alberto", "Martinez", "Mexico City");
	insert(flight_list, "Ming", "Liu", "Shenzhen");
	insert(flight_list, "Zac", "Zimmerman", "Los Angeles");

	// Testing display_list() as terminal output only
	display_list(flight_list);

	// Testing insert()
	list<Passenger>::iterator i1, i2;
	i1 = flight_list.begin();
	i2 = flight_list.end();
	{Passenger current_passenger("Alberto", "Martinez");
	if (find(i1, i2, current_passenger) != i2)
		flag[0] = 1;}
	{Passenger current_passenger("Ming", "Liu");
	if (find(i1, i2, current_passenger) != i2)
		flag[1] = 1;}
	{Passenger current_passenger("Zac", "Zimmerman");
	if (find(i1, i2, current_passenger) != i2)
		flag[2] = 1;}
	
	remove(flight_list, "Alberto", "Martinez");

	// Testing remove()
	i1 = flight_list.begin();
	i2 = flight_list.end();
	{Passenger current_passenger("Alberto", "Martinez");
	if (find(i1, i2, current_passenger) == i2)
		flag[3] = 1;}
	{Passenger current_passenger("Ming", "Liu");
	if (find(i1, i2, current_passenger) != i2)
		flag[4] = 1;}
	{Passenger current_passenger("Zac", "Zimmerman");
	if (find(i1, i2, current_passenger) != i2)
		flag[5] = 1;}
	
	// Testing check_reservation()
	if (check_reservation(flight_list, "Ming", "Liu"))
		flag[6] = 1;
	if (!check_reservation(flight_list, "Ming", "Schneider"))
		flag[7] = 1;
	if (!check_reservation(flight_list, "Abelard", "Liu"))
		flag[8] = 1;

	save_to_file(flight_list, "ticket_reservations.dat");
	list<Passenger> flight_list2;
	read_from_file(flight_list2, "ticket_reservations.dat");

	// Testing save_to_file() and read_from_file()
	i1 = flight_list2.begin();
	i2 = flight_list2.end();
	{Passenger current_passenger("Alberto", "Martinez");
	if (find(i1, i2, current_passenger) == i2)
		flag[9] = 1;}
	{Passenger current_passenger("Ming", "Liu");
	if (find(i1, i2, current_passenger) != i2)
		flag[10] = 1;}
	{Passenger current_passenger("Zac", "Zimmerman");
	if (find(i1, i2, current_passenger) != i2)
		flag[11] = 1;}


	int flag_total = 0;
	for (int i = 0; i != 12; ++i) {
		flag_total = flag_total + flag[i];
	}

	if (flag_total == 12)
		cout << "All tests passed!" << endl;
	else
		cout << "Failed!" << endl;

	
	return 0;
}

