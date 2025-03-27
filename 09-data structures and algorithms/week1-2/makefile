main: main.o Bank_account.o
	g++ main.o Bank_account.o -o main

main.o: main.cc Bank_account.h
	g++ -c main.cc -o main.o

Bank_account.o: Bank_account.cc Bank_account.h
	g++ -c Bank_account.cc -o Bank_account.o

clean:
	rm Bank_account.o main.o main 


