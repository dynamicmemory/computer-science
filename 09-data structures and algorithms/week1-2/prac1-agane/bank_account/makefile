main: main.o bank_account.o
	g++ main.o bank_account.o -o main 

main.o: main.cc bank_account.h
	g++ -c main.cc -o main.o

bank_account.o: bank_account.cc bank_account.h
	g++ -c bank_account.cc -o bank_account.o

clean:
	rm main.o bank_account.o main 
