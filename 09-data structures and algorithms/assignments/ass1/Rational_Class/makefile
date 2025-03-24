rational_class_test: rational_class_test.o Rational.o
	g++ rational_class_test.o Rational.o -o rational_class_test

Rational.o: Rational.cc Rational.h
	g++ -g -c Rational.cc

rational_class_test.o: rational_class_test.cc Rational.h
	g++ -g -c rational_class_test.cc

clean: 
	rm rational_class_test.o Rational.o rational_class_test
