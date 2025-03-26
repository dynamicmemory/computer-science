# chapter 3 - Probability 

# 3.2 Conditional probability
# 
# consider a matrix that looks like this
#                                 truth
#                          fashion    not   total
# mach-learn pred_fashion.  197.      22.    219
#            pred_not.      112.      1491.  1603
#            Total.         309.      1513.  1822

# machine learning algo predicted photo was about fashion
# P(mach_learn is pred_fashion given truth is fashion) = 197 / 309 = 0.63

# P(truth is fashion given machine learn pred-not)
# = 112 / 1603 = 0.07
# 
# Marginal and joint probabilities
# 
# marginal is when it is predicting just one thing like what did the mach learn
# predict was fashion
# = 219 / 1822 = 0.12
# 
# Joint probs are for two or more outcomes
# P(mach_learn is pred_fashion AND truth is fashion) = 197 / 1822 = 0.11
# and is the key word there
# 
# and = , and , = and for joint probabilities
# 
# To reiterate
# 
# If a probaility is based on a single variable, it is a marginal probability
# The probability of outcomes fro two or more variables or processes is called
# a joint probability.
# 
# Table proportions are used to summerise joint probabilities by taking every
# value and dividing it by the total
# 
# mach_learn is pred_fashion and truth is fashion = 0.1081
# mach_learn is pred_fashion and truth is not = 0.0121
# mach_learn is pred_not and truth is fashoin = 0.0615
# mach_learn is pred_not and truth is not = 0.8183

P(truth is fashion) can be obtained by summing all instances of truth being
fashion in the table, 0.1081 + 0.0615

Conditional probability

p(truth is fashion given mach_learn pred_fashion) 197/219
we can rewrite this like
P(truth is fashion | mach_learn pred_fashion)

to break this down further what we want is 
cases where truth is fashion and mach_learn is pred_fashion / mach_learn pred_fashion

using the proportions table it looks like this

0.1081 / 0.1081 + 0.0121 = 0.9

same as above, this leads to the general formula for conditional probability

The condirtional probability of outcome A given condtion B is computed as the following
P(A and B) / P(B)



A) write out the probability that the ml pred was correct, if the pohoto was about fashion

P(mach_learn pred_fash | truth fashion)
P(mach_learn pred_fashion and truth is fashion) / P(truth is fashion)

0.1081 / 0.1081 + 0.0615 = 0.64

Guided Prac 3.30

a) determine the probability that the algo is incorrect if it is known the photo is about fashon
P(mach_learn pred_not | truth fashion)
mach_learn pred_not and truth_fashion / truth_fashion
0.0615 / 0.1081 + 0.0615 = 0.36

b) 1
c) Because we are adding all instances of the mach_learn algo prediting the fashion
images, both if it is correct and if it is incorrect, therefore it should equal to 1
as it is all instances.


BOSTON SMALLPOX DATA

lived_vac = 238
lived_not = 5136
died_vac = 6
died_not = 844

total = 6224

write out the formal notation and find the prob that someone who was not vacced
died of small pox

P(died | no_vacc)
0.1366 / 0.9608 = 0.1411

find the prob someone who was vaccd died of smallpox
P(died | vaccd)
0.001 / 0.0392 = 0.0255

Much small amount who were vaccd died

The study was observational
No
Poverty in general is confound in 17xx, plus probably a downswing of jobs so people
probabl;y died from other related things at the time due to lack of work and money


GENERAL MULTIPLICATION RULE

For events tyhat might not be independent

If A and B represent two outcomes or events, then
P(A and B) = P(A|B) * P(B)
It is usful to think of A as the outcomes of interest and B as the condition

Given that 96%  not vacc and 85% of not vaccd lived How could we figure the prob
Thata resident was not vaccd and lived
A = 0.85
B = 0.96
A|B * B = 0.812


P(vaccd) = 0.0392
P(lived|vaccd) = 0.9754

P(person was vaccd and person lived)

P(lived | vaccd)
0.9754 * 0.0392 = 0.0382

INDEPENDENCE OF CONDITIONAL PROBABIKITES

If two events are independentm then knoiwing the outcome of one should provide no information about 
the other

Let X and Y represent the outcomes of rolling two dice
a) 1/6
b) 1/6 * 1/6
c) P(Y|X) = P(Y and B) / P(B) = 0.16666
d) no thery are the same

A midterm = 0.13
A final | A mid = 0.47
A final | not mid = 0.11


STATS FAILURE DUE TO TREE DIAGRAMS
a)
        -----0.97 = 0.7566
 ---0.78
        -----0.03 = 0.0234

        -----0.57 = 0.1254
----0.22
        -----0.43 = 0.0946

b) 0.7566 + 0.1254 = 0.882

c) P(tree diagram | passed)
P(A and B) / P(B)
0.75660 / 0.7566 + 0.1254 = 0.8578



