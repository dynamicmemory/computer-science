main: main.o vec.o
	g++ main.o vec.o -o main

main.o: main.cc vec.h
	g++ -c main.cc -o main.o

vec.o: vec.cc vec.h
	g++ -c vec.cc -o vec.o

clean:
	rm main.o vec.o main
