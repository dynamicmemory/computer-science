main: main.o
	g++ main.o -o main 

main.o: main.cc linked_list.cc
	g++ -c main.cc -o main.o

clean:
	rm main.o main 
