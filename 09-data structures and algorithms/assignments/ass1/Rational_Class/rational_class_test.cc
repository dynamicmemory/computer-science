#include "Rational.h"
#include <iterator>
#include <stdexcept>
using namespace std;

int main()
{
	// A rational-number class test.

	int flag[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};

	// Testing the constructors.

	try
	{
		Rational not_valid_object(1, 0); // object scope restricted to "try" block!
	}
	catch (std::invalid_argument &exception)
	{
		cout << "Error: " << exception.what() << endl;
		flag[0] = 1;
	}
	Rational answer;
	Rational a(5, 3);
	Rational b(-4, -8);

	// Testing the overloaded stream insertion operator.

	cout << "answer = " << answer << endl;
	cout << "a = " << a << endl;
	cout << "b = " << b << endl;

	// Testing the other overloaded operators.

	if (a == b)
		cout << "a == b: "
				 << "True"
				 << "expected: False" << endl;
	else
	{
		cout << "a == b: "
				 << "False"
				 << endl;
		flag[1] = 1;
	}

	Rational plus = a + b;
	answer.set_number(13, 6);
	cout << "a + b = " << plus << " expected: " << answer << endl;
	if (plus == answer)
		flag[2] = 1;

	Rational minus = a - b;
	answer.set_number(7, 6);
	cout << "a - b = " << minus << " expected: " << answer << endl;
	if (minus == answer)
		flag[3] = 1;

	Rational mult = a * b;
	answer.set_number(5, 6);
	cout << "a * b = " << mult << " expected: " << answer << endl;
	if (mult == answer)
		flag[4] = 1;

	Rational div = a / b;
	answer.set_number(10, 3);
	cout << "a / b = " << div << " expected: " << answer << endl;
	if (div == answer)
		flag[5] = 1;

	if (a < b)
		cout << "a < b: "
				 << "True"
				 << "expected: False" << endl;
	else
	{
		cout << "a < b: "
				 << "False" << endl;
		flag[6] = 1;
	}

	if (a > b)
	{
		cout << "a > b: "
				 << "True" << endl;
		flag[7] = 1;
	}
	else
		cout << "a > b: "
				 << "False"
				 << "expected: True" << endl;

	if (a != b)
	{
		cout << "a != b: "
				 << "True" << endl;
		flag[8] = 1;
	}
	else
		cout << "a != b: "
				 << "False"
				 << "expected: True" << endl;

	// Check all tests

	int flag_total = 0;
	for (int i = 0; i != 9; ++i)
	{
		flag_total = flag_total + flag[i];
	}

	if (flag_total == 9)
		cout << "All tests passed! But check stream insertion manually." << endl;
	else
		cout << "Failed!" << endl;
    
	return 0;
}
