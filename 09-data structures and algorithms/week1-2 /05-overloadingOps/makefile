main: main.o rational.o
	g++ main.cc rational.cc -o main

main.o: main.cc rational.h
	g++ -c main.cc -o main.o

rational.o: rational.cc rational.h
	g++ -c rational.cc -o rational.o

clean:
	rm main rational.o main.o
