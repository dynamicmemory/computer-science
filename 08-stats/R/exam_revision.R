# EXAM REVISION

# Quiz 1 - calculate the mean and variance of this data set

vals = c(44.8, 26.3, 13.6, 48.5, 2.3, 3.2)
var(vals)


vals = c(43,	34.8,	21.2,	36	,42.9,	36.3)
var(vals)

# Quiz 2 - calculate the difference in this observation to the mean

z = (105 - 100) / 5.2
pnorm(z)

# Quiz 2 - binomial distribution

attempts = 8
max attempts = 10
prob of attempt = 0.52
# dbinom for exactly
dbinom(8, 10, 0.53)

# pbinomn for atleast
pbinom(8, 10, 0.53, FALSE)
pbinom(1, 20, 0.2, FALSE)
1 - pbinom(9, 114, 0.04)

# Quiz 2 - test the null hypothesis

t = c(15, 27, 18, 22, 21, 28)
x_hat = mean(t)

z = (x_hat - 25) / 0.05
z
pnorm(z)

# Quiz 2 - run a bunch of stats on a set of data

t = c(44.85, 49.13, 28.36, 25.77, 41.12, 39.3, 48.14, 28.38)
std = sd(t)
error = 1.96 * std
error
x_hat = mean(t)
upper = x_hat + error
lower = x_hat - error

# Quiz 2 - calc the test stats and find its percentile

t = c(2, 1, 4, 15, 1, 12, 2)
x_hat = mean(t)

z = (x_hat - 8) / 1
z
pnorm(z)

# Quiz 2 - find the z score

x_hat = 51.05
std = 2.1
mu = 45.7

z = (x_hat - mu) / std
z

# Calc test stat

vals = c(19,	21,	18,	21,	22,	15)
mean(vals)
std = sd(vals)
t = (19.33 - 25) / (std/6**0.5)
t

# Calc test stat

vals = c(1,	10,	4,	2,	15,	9,	14)
x_hat = mean(vals)
se = sd(vals) / 7**0.5
t = (x_hat - 8) / se
# Calc se and conf interval 

vals = c(44.21,	26.53,	20.77,	44.4,	48.64,	32.5,	36.67,	44.64)
std = sd(vals)
x_hat = mean(vals)
se = std/8**0.5
se
upper = x_hat + se * 1.96
lower = x_hat - se * 1.96
#####################################################################
## DAY 2 

# QUIZ 2 

z = (105 - 100) / 5.2
pnorm(z)


# QUIZ 2 soccer goals

success_rate = 0.6
aatempts = 13
goals = 8

pbinom(goals, aatempts, success_rate, FALSE)
dbinom(goals, aatempts, success_rate)

pbinom(10, 111, 0.06)

# QUIZ 2 Hypothesis testing

vals = c(22,27,26,26,22,27)
x_hat = mean(vals)

z = (x_hat - 25) / 0.05
z

vals = c(11,8,15,10,13,2,14)
x_hat = mean(vals)
std = sd(vals)
se = std / (7)**0.5
z = (x_hat - 8) / se

# QUIZ 2 probability

41/42

size = 51 + 4 + 50 + 51
likes = 101 / size
home = 55 / size
overlap = 51 / size
answer = likes + home - overlap
answer

47 / (46 + 47 + 59)


# QUIZ 2 calc std error and conf ints

vals = c(49.8, 38.1, 41.22, 28.83, 39.4, 40.92, 38.44, 25.49)
x_hat = mean(vals)
std = sd(vals)
se = std / (8)**0.5

upper = x_hat + 1.96 * se
lower = x_hat - 1.96 * se

# QUIZ 2 calc sauagae standnards 

x_hat = 54.64
mu = 49.58
std = 1.26

z = (x_hat - mu) / std

############################################################################
# DAY 3 revision

z = (105 - 100) / 5.2
pnorm(z)

# four things needed for a binomal distribution
# same probabiliity per instance of task
# independence from one instance to another
# must be true or false, right or wrong task, ie binary task
# the number of trials is fixed

vals = c(22,28,29,29,15,19)
x_hat = mean(vals)
x_hat
s = sd(vals)
se = s / 6**0.5
z = (x_hat - 25) / se
z
pnorm(z)

vals = c(47.67,48.53, 40.75, 26.85, 33.57, 27.74, 49.42, 31.72)
x_hat = mean(vals)
s = sd(vals)
se = s / 8**0.5
me = 1.96 * se
upper = x_hat + me
lower = x_hat - (1.96 * se)

vals = c(7,6,14,6,1,11,9)
x_hat = mean(vals)
s = sd(vals)
se = s / 7**0.5

z = (x_hat - 8) / se

z = (50.36 - 45.05) / 4.79

total = 42 + 4 + 52 + 60
cond = 52 / total

# QUIZ 3

first = 4.65**2 / 9
second = 6.6**2 / 9

se = (first + second)**0.5
t_star = (-16.76 - -14.39) / se

se = 5.97 / 18**0.5
-4.74 / se


s1 = 0.314**2 / 50
s2 = 0.322**2 / 50
s3 = s1 + s2
s4 = s3**0.5
z = (2.974 - 2.77) / s4

# CHOII Squared terst of independence 

g1 = c(86, 51)
g2 = c(52, 18)

a = (86 - 91)**2 / 91
b = (51 - 46)**2 / 46
c = (52 - 47)**2 / 47
d = (18 - 23)**2 / 23

choice = rbind(g1, g2)

Ho: There is no associtation between sex and want of having a baby
Ha: There is an associtiation between sex and want a baby

df = (2 -1) * (2 - 1)
t = a + b + c + d

pchisq(t, df, lower.tail = FALSE)

with a p value of (X^2 = 2.44, df= 1, p-value = 0.12) which is
> 0.05 we fail to reject the null hypothesis and say that 
our data does not provide convincing evidence that there is 
an association between adult sex and the want to bear child
that is to say that our data does not provide evidence to say
that men or woman appear to want child to have child more then other.


pnorm(-1.82)

#Q12
s1 = 6.57**2 / 20
s2 = 5.54**2 / 10
s3 = s1 + s2
se = s3**0.5

z = (7.65 - 12.41) / se


white = 31
non-white = 6
total = 37

New york/Manhattan white population = 46%
New york/Manhattan non-white_expected = 54%

Ho: The proportion of white to non-white homies in the room matches expectation
Ha: The proportion of white to non-white homies in the room does not match expectation 

white_expected = 0.46 * 37
= 17.02

non_white_expected = 0.54 * 37 
= 19.98

Conditions: 
Independence - We assume independence
Sample size - Minimum expected value is > 5
DF - We have atleast two groups (non-white could be split up to more)

All conditions met for a Chi-squared goodness of fit 

race = c(31, 6)
demographic = c(0.46, 0.54)

Chit = chisq.test(race, p=demographic)
Chit

With a pvalue of 0.0000046 which is < 0.05 we can reject the null hypothesis 
and say that the evidence of that picture suggests that the proportion of white
to non-white homies in the room does not match expectation. Furthermore we can 
say that there is less then expected non-white members in the crowd and far more
then expected white members in the crowd. 

























