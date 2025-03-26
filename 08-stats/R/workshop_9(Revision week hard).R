# workshop_9

Question 1

a)
1) We have 10 individual pots
2) We have 10 trials
3) Each trial is either germinated seed or not
4) There is a 0.7 probability per trial

b)
i) Mean = n*p = 10*0.7 = 7
ii) std = (n*p*(1-p))**0.5 
std = (10*0.7*(1-0.7))**0.5 = 1.45

c)
bin(10, 0.7)

d)
i) 
1 - pbinom(8, 10, 0.7)
ii)
pbinom(4, 10, 0.7)


Question 2

mean = 525
std = 40
a)
xbar = 480
z = (xbar - mean) / std

b)
z = (485 -525) /std
1 - pnorm(z)

c)
n = 16
se = std / n**0.5
i)
N(525, 10)

ii)
z = (480 - mean) / se
1 - pt(z, 15)

d) The probability that the mean weight from the 16 bulls would be 485 is higher
than the probability of one bull have a weight of 485, because finding 16 bulls
that are 40kgs lighter then the population would be rarer then just finding one


Question 3

a) I thought it would be a two means test, but the output for the two means
test is wrong, it rejects null but also has a p value > 0.05?? maybe im
missing something, so i would chose the one sample t-test even though it
makes no sense to do so, but atleast the p value is correct and it does
reject the null which lines up with my intuition with the question.

b)
h0 there is no difference between means of men and womans salary
ha there is a difference between means of men and womans salary
And the t test showed that to be true by rejecting the null hypothesis

c)
We can say that since we have a p value < 0.05 we can reject the null hypothesis
and state that there is a difference between men and woamsn salary as first
year law employees. Further more we can say that with 95% confidence that
the difference is between 9.8 and 3865.2 dollars of difference between The 
two means salary, that is woman get paid somewhere in that vacinity less.


Question 4

a)

i) 
s = 0.32
n = 15
df = n -1
xbar = 4.74
tstar = 2.145
se = s /n**0.5

upper = xbar + tstar * se 
lower = xbar - tstar * se

ii)
We are 95% confident that their is insufficent oxygen for the fishies to live as
the values obtained from our 95% confiendance interval are below the recommeded
level of 5ppm

b)
i) I would say since the pvalue is greater than 0.05 that no we do not 
reject the null hyp and do not have suffiecent evidence to say there is a
difference. BUT it clearly say that we have selected the alt hyp, furhter
stating that since it was not 0, there is a clear difference. I cant argue
with that logic, but also, why is the pvalue above 0.05 then?...

Boom turned out i was right, there was not enough evidence to support a change
in oxygen levels and the rest of that shit was a trap, rmemeber this betrayal
when the exam comes.

ii)

4.92 - 4.74


Question 5

a)
h0 - all means will be equal
ha there will be atleast one mean that is not equal to the others.

pvalue is less than 0.05, therefore we reject the null hypothesis and
say that there is a difference between atleast one of the groups.

that group is a, we see this visually with the boxplots.

b)

std = 629
n = 22
se = std / n**0.5
tstar = -3.18
mean = 1448

lower = mean + tstar * se
upper = mean - tstar * se
(1021, 1874)

so i cant read tables apparently and thought the column was the precalc'd
tstars of groups a b c d... no they were just choices for us to make... so 
ill do it again
'
n = 22
mse = 211252
se = (mse / n)**0.5
tstar = -1.98

lower = mean + tstar * se
upper = mean - tstar *se

c)

With the p value being so low, far lower then the 0.05 threshold we can reject
the null hypothesis and say that our data shows strong evidence of there being
a difference in calcium lvls vs the age of the woamn. 

We can say that the difference is found in the A group of woman against the others


Furhter more we can say with 95% confidence that the minimum calcium we could
find in a woman in group A would be 1253 which is far greater then the maximum we
could find in the other three groups, b, c, d , with maxes of 1236, 1043, 983.
With 95% conf we can say that group A shows significantly higher levels of
calcimum

d)

1 - The samples were random and independent, we assume all were independant
2 - From the boxplots we can see that the distributions were relativly even and symetric
3 - The std from group A is more then twice that of the std of group C, meaning
that we dont meet the criteria to perform this test.... but we still will anyway.
Also group B is more then doulbe C aswell.