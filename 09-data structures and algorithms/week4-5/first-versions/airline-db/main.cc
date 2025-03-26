#include <list>
#include <algorithm>
#include <iostream>
#include <string>
#include <fstream>

int menu() {
    int option;
    std::cout << '\n';
    std::cout << "Enter one of the following options: " << '\n';
    std::cout << "1. Load reservation from file: " << '\n';
    std::cout << "2. Reserve a ticket: " << '\n';
    std::cout << "3. Cancel a reservation: " << '\n';
    std::cout << "4. Check reservation: " << '\n';
    std::cout << "5. Display passenger: " << '\n';
    std::cout << "6. Save passenger list: " << '\n';
    std::cout << "7. exit: " << '\n' << std::endl;
    std::cin >> option;
    std::cin.get();

    return option;
}

int main() {
    std::list<std::string> flight_list;
    std::list<std::string>::iterator i1, i2;
    std::string name;

    while (true) {
        switch (menu()) {
            case 1: {
                        flight_list.clear(); // TODO : Add a better check to not wipe when loading data.
                        /*std::cout << flight_list.size() << '\n';*/

                        std::ifstream input("ticket_reservations.dat");
                        while (input >> name) 
                            flight_list.push_back(name);
                        input.close();
                        break;
                    }

            case 2: {
                        // Print useful info and instructions to the user
                        std::cout << "Enter your surname to reserve a ticket: ";
                        std::cin >> name;
                      
                        // Add their name to the flight db
                        flight_list.insert(std::next(flight_list.begin()), name);
                        
                        // Output useful info to the user
                        std::cout << '\n' << "Reservation for: " << name << " was successful" << '\n';
                        break;
                    }

            case 3: {
                        // Print useful info and instructions to the customer
                        std::cout << "Enter the surname to cancel a reservation for: ";
                        std::cin >> name;
                        bool deleted = false;    // used to display correct output message

                        // iterators over list of passengers and removes desired passenger if exists
                        for (auto i1 = flight_list.begin(); i1 != flight_list.end(); i1++) {
                            if (*i1 == name) {
                                flight_list.remove(name);
                                deleted = true;
                                break;
                            }
                        }

                        // Prints correct output message
                        if (deleted)
                            std::cout << "Reservation for: " << name << " was successfully cancelled" << '\n';
                        else 
                            std::cout << "Reservation for: " << name << " does not exist?" << '\n';
                        break;
                    }

            case 4: {
                        // Print useful info and instructions to the customer
                        std::cout << "Enter the surname to check a reservation: ";
                        std::cin >> name;
                        bool passenger_exists = false;    // used to display correct output message
                        int i = 1;                        // keeps track of passengers seat number
                  
                        // iterator through customers and find if a customer exists and thier seat number
                        for (auto i1 = flight_list.begin(); i1 != flight_list.end(); i1++, i++) {
                            if (*i1 == name) {
                                passenger_exists = true;
                                break;
                            }
                        }

                        // Print out correct output
                        if (passenger_exists)
                            std::cout << name << " has a ticket reserved on this flight for seat number: " << i << '\n'; 
                        else
                            std::cout << name << " does not have a reservation on this flight" << '\n'; 
                        break;
                    }

            case 5: {
                        // uses an iterator to print out the full list of passengers
                        std::cout << "Flight Manisfest" << '\n';
                        for (auto i1 = flight_list.begin(); i1 != flight_list.end(); i1++)
                            std::cout << *i1 << '\n';
                        break;
                    }

            case 6: {
                        std::ofstream outFile("ticket_reservations.dat", std::ios::out);

                        if (!outFile) {
                            std::cerr << "Error: Could not open file \n";
                            return 1;
                        }

                        for (auto i1 = flight_list.begin(); i1 != flight_list.end(); i1++)
                            outFile << *i1 << '\n';

                        std::cout << "Flight manifest successfully updated" << std::endl;

                        outFile.close();



                        break;
                    }

            case 7:
                    return 0;
        }
    }
    return 0;
}
