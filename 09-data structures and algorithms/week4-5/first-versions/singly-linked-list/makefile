main: main.o 
	g++ main.o -o main 

main.o: main.cc linkedlist.h node.h
	g++ -c main.cc -o main.o

clean: 
	rm main main.o 
