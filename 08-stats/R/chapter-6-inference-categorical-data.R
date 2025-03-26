# Chapter 6 - Inference for categorical Data

# Success- failure condition (again)

n = 826
pbar = 0.7

n*pbar > 10
n*(1-pbar) > 10

# With both conditions being true we can continue using the 
# normal distribution to model pbar since we don't know the 
# true value of p

# Find the se of pbar

se = (pbar * (1 - pbar) / n)**0.5

# Constuct a 95% conf interval

# qnorm will find zstar when we are working with normal distro
zstar = qnorm(0.975) 
# we want 95% conf int, so we get 0.975 i.e 0.025 each side

pbar_upper = pbar + zstar * se
pbar_lower = pbar + zstar * se

# Confindence interval for a single proportion

# 1 - Prepare, identify pbar and n
# 2 - Check, Verify conditions for pbar being nearly normal, 
#     for one proportion conf int use pbar over p to check 
#     success-failure conditions
# 3 - Calculate, If conds hold, compute se using pbar and find
#     zstar using qnorm() and conf int percentage, remember for
#     for 95% that we want 5% so thats 2.5% each side.
# 4 - Conclude, interpret the conf int in context of the problem

# Hypothesis testing for proportions

# h0 - p = 0.5, borrows would evenly support for and against
# ha - p != 0.5, majority borrowers would support or oppose

# with pbar = 0.51, successfailure conditions still hodl so i say
# yes, they do support

pbar = 0.51
se = (pbar * (1 - pbar) / n)**0.5

zscore = (pbar - 0.5) / se 

zscore = 0.59 # Rounding error throwing off my calc, so hard coded
# to be same value as textbook to make sure i was doing things right

# To get p value we use pnorm() with the zscore calc'd, but i think..
# We do 1 minus pnorm() to get the lower tail only (which is one side)
# Then we multiply it by 2 to have it on both sides since we want to 
# know if we are above 0.5 or below as oer our alt hypothesis.
pvalue = (1 - pnorm(zscore)) * 2

# Since our pvalue is greater than 0.05, we do not reject the null
# hypothesis and we can say there is not convincing evidence to prove
# that a majority of the payday loan borrowers support or oppose
# the new regulations.

# Choosing a sample size to estimate a proportion

# if you goal is to find the amount of participants needed to end
# up with a conf int less than 0.04 using a 95% conf int
# then all you need to do is solve

zstar * (pbar (1 - pbar) / n)**0.5 < 0.04

# seems complicated but it's not, we need pbar and n. N is what we
# are trying to find so we solve for it

n = zstar**2 * pbar *(1 - pbar) / 0.04**2 

zstar = qnorm(0.95)

first_model = zstar**2 * 0.017 * (1 - 0.017) / 0.01**2
second_model = zstar**2 * 0.062 * (1 - 0.062) / 0.01**2
third_model = zstar**2 * 0.013 * (1 - 0.013) / 0.01**2


# payday loan final question

1.96**2 * 0.7*(1-0.7) / 0.05**2

##########################################################
# 6.3 Testing for goodness of fit using chi-square

# Whites
z1 = (205 - 198) / 198**0.5

# Blacks
z2 = (26 -19.25) / 19.25**0.5

# hispanics
z3 = (25 - 33) / 33**0.5

# Other
z4 = (19 - 24.75) / 24.75**0.5

x2 = z1**2 + z2**2 +z3**2 + z4**2

# Guided practice 6.24

a) Less skew, moves the distribution more toward the center
b) More variablility
c) more smooth and flat

# Goodness test pracs

a)
1 -pchisq(6.25, 3)

b)
1 - pchisq(4.3, 2)

c)
1 - pchisq(5.1, 5)

d)
1 - pchisq(11.7, 7)

e)
1 - pchisq(10, 4)

f)
1 - pchisq(9.21, 3)


# To find the degrees of freedom we take k - 1 where k is = to
# number of bins or different categories. for our jury qurstion

k = 4
df = k - 1
pvalue = 1 - pchisq(x2, df)
pvalue


# Stock market question

z1 = ((717 - 743) / 743**0.5)**2
z2 = ((369 - 338) / 338**0.5)**2
z3 = ((155 - 154) / 154**0.5)**2
z4 = ((69 - 70) / 70**0.5)**2
z5 = ((28 - 32) / 32**0.5)**2
z6 = ((14 - 14) / 14**0.5)**2
z7 = ((10 - 12) / 12**0.5)**2

x2 = z1 + z2+ z3+ z4 + z5 + z6 + z7
# Repeat this for z2, z3, z4, etc, then squaring each vlaue and
# adding them together to get our test stat X^2

pvalue = 1 - pchisq(x2, 6)

# Running this idea on btc... wait one moment

# These are our day numbers and number on instances of each
1 = 158, 2 = 77, 3 = 26, 4 = 15, 5 = 7, 6+ = 7
# This is the total number of instances
total = 290

# The geometric model generating the values for our distro
day1 = ((1 - 0.545)**0 * 0.545) * total
day2 = ((1 - 0.545)**1 * 0.545) * total
day3 = ((1 - 0.545)**2 * 0.545) * total
day4 = ((1 - 0.545)**3 * 0.545) * total
day5 = ((1 - 0.545)**4 * 0.545) * total
day6 = ((1 - 0.545)**5 * 0.545) * total

# The observed values of our distro
z1 = ((158 - day1) / day1**0.5)**2
z2 = ((77 - day2) / day2**0.5)**2
z3 = ((26 - day3) / day3**0.5)**2
z4 = ((15 - day4) / day4**0.5)**2
z5 = ((7 - day5) / day5**0.5)**2
z6 = ((7 - day6) / day6**0.5)**2

x2 = z1 + z2 + z3 + z4 +z5 +z6

1 - pchisq(x2, 5)


########################################################
# Exercises

# 6.31

a) It is not like the normal distro it has one param, df

b) True it is also right skewed

c)True

d) No, the opposite is true


# 6.33

a)
h0 - the proportion of students who consumed the book followed
the professors prediction

ha - The distribution of how the cosumption of the book happened
deviated from the professors prediction

b)
buy = 0.6 * 126
print = 0.25 * 126
read = 0.15 * 126

c)
Independence, each student is a person... yes
each bin or cat has more than 5

d)
df = 2

z1 = ((71 - buy) / buy**0.5)**2
z2 = ((30 - print) / print**0.5)**2
z3 = ((25 - read) / read**0.5)**2

x2 = z1 + z2 + z3

pvalue = 1 - pchisq(x2, df)

e) Sincethe pvalue is larger than 0.05 we do not reject the null, that 
we have sufficient evidence that the professors predictions were
inaccurate

#######################################################################
######## 6.4 - Two-way chi squared inference   #####################


1 - pchisq(40.13, 2)

# Since the p value is microscopic we can reject the null hypothesis and say 
# that the data shows strong evidence that the  question asked about the ipod
# greatly effects the honestly of the seller.


h0 - the type of treatment had no effect on the primary outcome

ha - the type of treatment does have an effect on the primary outcome

ls_fail = 319 * 234/ 699
ls_succ = 380 * 234 / 699
met_fail = 319 * 232 / 699
met_suc = 380 * 232 / 699
rosi_fail = 319 * 233 / 699
rosi_suc = 380 * 233 / 699

z1 = (109 - ls_fail)**2 / ls_fail
z2 = (125 - ls_succ)**2 / ls_succ
z3 = (120 - met_fail)**2 / met_fail
z4 = (112 - met_suc)**2 / met_suc
z5 = (90 - rosi_fail)**2 / rosi_fail
z6 = (143 - rosi_suc)**2 / rosi_suc

x2 = z1 + z2 + z3 + z4 + z5 + z6

rows = 3
cols = 2
df = (rows - 1) * (cols - 1)

pvalue = 1 - pchisq(x2, df)

# since the pvalue is 0.01 which is significantly less then our alpha of 0.05
# we can reject the null hypothesis and say that the data provides strong 
# evidence that there is a difference with failure and success rates amoung
# difference treatments.


# How to make a table of data for doing chi bois
NSAIDS = c(18, 57)
ace = c(24,148)
nopain = c(103, 659)

painkiller = rbind(NSAIDS, ace, nopain)
colnames(painkiller) = c("miscarriage", "No miscarriage")

painkiller

chit = chisq.test(painkiller)
chit

chit$stdres
