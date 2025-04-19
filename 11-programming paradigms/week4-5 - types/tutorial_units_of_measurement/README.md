# Tutorial, week 4: Extension methods, DSLs and Implicits


This tutorial is a little lighter and looser - there's typically an assignment due soon.

## Part One

`PartOne.scala` contains first a demo of a using a value class to typecheck IDs. It then contains a toy demo of extension
methods, letting us do some things with strings that look like binary. The third part asks you to complete some
extension methods that will do something useful: allow vector operations on tuples of numbers.


## SI units

In this part of the tutorial, we'll play around with implicits to see if we can get
an equation for Volts, Watts, Amps, and Ohms working.
SI units is actually quite a hard problem, so we're not going to go very far.
We're just going to get the equation from the lecture working.

First, we'll do an example with prefixes (eg, "milli" for thousandths), where
I've provided the class but not the implicit conversion function.

Then we'll do one that sets up equations with units -- I've written the
machinery for handling the units, but you'll need to define a class and an
implicit conversion so that you can make things like `3.pico.Watts` compile

As we need to implement the language to get your code to compile, it doesn't use unit
tests. (If the code doesn't compile, tests can't run anyway). Instead, the SI class 
extends App so you can run it directly.


