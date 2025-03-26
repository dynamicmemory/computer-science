# chapter 7

# Finding the t-distribution
# What is the t-distro of -2.10?
#dt(-2.10, 18) / 2
# we divide by 2 as we only want the left side or -2.10 and not both sides.

# What is the t-distro of 1.66  
#dt(1.65, 20) / 2

# What is the t-distro of 3 above and below
#dt(3, 2)

# What proportion of t-distro falls above -1.79 with 19 df
#1 - dt(-1.79, 19) /2

#dt(2.09, 18) / 2
#dt(1.67, 14)
#dt(2.37, 99)


pt(7.7, 18)

pt(-1.75, 24)

pt(-2.10, 18)

1 - pt(1.65, 20)

(1 - pt(3, 2)) * 2

#Exercise 7.1
qt(0.95, 5) #a

#b
n = 20
cl = 0.98
df = n - 1
qt(0.01, df)

#c
n = 29
cl = 0.95
df = n - 1
qt(0.975, df)

#d 
n = 12
cl = 0.99
df = n - 1
qt(0.005, df)

############ Exercise 7.3
alpha = 0.05
# Function for detecting if we reject the null hyp or not
null_hyp = function(alpha, p_value) {
  if (p_value < alpha) {
    print('We reject the null hypothesis')
  } else {
    print('We do not reject he null hypothesis')  
    
  }
}

#a
n = 11
T = 1.91
df = n - 1
p_value = (1 - pt(T, df)) * 2
null_hyp(alpha, p_value)

#b
n = 17
T = -3.45
df = n - 1
p_value = pt(T, df) * 2
null_hyp(alpha, p_value)

#c 
n = 7
T = 0.83
df = n - 1
p_value = (1 - pt(T, df)) * 2
null_hyp(alpha, p_value)

#d
n = 28
T = 2.13
df = n - 1
p_value = (1 - pt(T, df)) * 2
null_hyp(alpha, p_value)

########## EXERCISE 7.5
n = 36
lower = 18.985
upper = 21.015
difference = upper - lower
margin_error = difference / 2
sample_mean = lower + margin_error

# Confidence lvl of 95% = 0.975 (5% distributed both sides)
cl =0.975
df = n - 1
t_score = qt(cl,df)

# Margin_error = t_score * (s/sqrt(n)) - rework the equation to equal s
std = margin_error / t_score * (n**0.5)

########## EXERCISE 7.7
#a
h0 = 8 # average newyoker gets 8 hours of sleep
ha < 8 # average newyorker gets less than 8 hours of sleep

#b 
µ = 8
n = 25
df = n - 1
x = 7.73
s = 0.77
se = s / (n**0.5)
z = (x - µ) / se

#c
p_value = pt(z, df) * 2 # * 2 because z score was neg

#d
null_hyp(alpha, p_value)

#e
t_score90 = qt(0.95, df)
me = t_score90 * se
lower = x - me
upper = x + me
# Smarter way to do this is to see that 90% conf level is 0.1 alpha score
# Since p_value is 0.093 which is smaller than alpha, we reject null, therefore
# 8 wouldnt be in the interval as it would have to be outside to be rejected

################## EXERCISE 7.9
µ = 60
n = 20
s = 8
df = n - 1
se = s / (n**0.5)

# we punch in and check a t value that will return 0.025 as we are looking for 
# 0.05 t value (*2 for both sides)
p_value = pt(-2.09, df)

# we then reverse engineer (algebra) the z score equation to get the answer
z = -2.09
x1 = µ + z * se
# or
z = 2.09
x2 = µ + z * se

########## EXERCISE 7.11

#a
h0 = 5
#ha ≠ 5

µ = 5
n = 20
df = n - 1
std = 2.2
x = 4.6
cl = 0.975
se = std/ n**0.5

Tz = (x - µ) / se

p_value = pt(Tz, df) * 2
null_hyp(alpha, p_value)

#b
t_star = qt(0.975, df)
margin_error = t_star * se

upper = x + margin_error
lower = x - margin_error

######### EXERCISE 7.13
# Brute force the sum for margin of error
# margin_error = z * std / n**0.5
# 10 = 1.96  * 100 / n**0.5 # 1.96 is 95 conf level

#----------7.2 Paired Data---------------

# 7.2.2 Inference for paired data

#h0 = 0, there is no difference in prices from books offered by both services
#hA \= 0, there is a difference in prices from books offered by both services

n = 68
df = n - 1

s = 13.42
se = s / n**0.5

x = 3.58
T = (x - 0) / se
p_value = (1- pt(T,df)) *2

# Since the p value is < 0.05 we reject the null hypot and say that on average
# one service is cheaper than the other, amazon in this case.

########### EXERCISE 7.19

ci = qt(0.975, df) * se
upper = x + ci
lower = x - ci
sprintf("%1.2f - %1.2f", lower, upper)

# UCLA Students should always check amazon before buying a txtbook as we are 95%
# confident they may find a book cheaper there.


#########EXERCISE 7.15

# Non-paired test, we would want to do a two sample means test to spot the 
# difference. This is because it is the same location we would be comparing
# it too, just one year earlier compared to comparing the air quality of one
# city to another.

# So im dead wrong on this answer and apparently I dont get it at all and it is
# pretty much the opposite of what I said.....

#########EXERCISE 7.17

#a - Paired
#b - Two sampled means???
#c - Paired
#d - Paired

#########EXERCISE 7.19

#a - Since it is the sample weather stations that we are taking the samples
# from they are from the same source and therefore pair that data daawggg

#b - h0 = 0, there will be 0 days difference exceededing 90 degrees for the two
# different years
#    hA > 0, there will be more days exceeding 90 degrees in 2018 compaered to
# 1948

#c - Stations are all independent and no outliers

#d 
x = 2.9
s = 17.2
n = 197
df = n - 1

se = s / n**0.5
T = x / se
p_value = 1 - pt(T, df)

#e - with alpha = 0.05, the p_value is far less than this, so we reject the null
# hypothesis that there were no change in number of days above 90 degrees
# and infact there were significantly more.

#f - Type 1 error, we rejected the null when it could have been true. But we didnt
# make that error.

#g - Based on the hypt testing, I would not expect to see 0 within the conf 
# Interval.

#----------7.3 Difference of two means---------------

# Example 7.22
n = 9
x1 = 3.5
x2 = -4.33
x = x1 - x2

s1 = 5.17
s2 = 2.76
# se = s1^2 + s2^2 / n ^0.5 
se = (((s1)**2 + (s2)**2)/n)**0.5

df = n -1

ci = 0.025
t_star = qt(ci, df)

margin_error = t_star * se 

lower = x + margin_error
upper = x - margin_error


# Example 7.23
#h0 = there will be no difference in averages between the smoking and non
# smoking group, µ1 - µ2 = 0
#hA = there will be difference in the averages of the two groups µ1 - µ2 ≠ 0

# Example 7.24
#a 
n1 = 100
n2 = 50
x2 = 6.78
x1 = 7.18
x = x1 - x2 
x

#b
s1 = (1.60**2) / n1
s2 = (1.43**2) / n2
se = (s1 + s2)**0.5
se

# Example 7.25 
alpha = 0.05
µ = 0 # From h0
T = (x - µ) / se
df = n2 - 1 # n2 is the smaller of the two n's
p_value = (1 - pt(T, df)) * 2
# P_value is greater then alpha, therefore we do not reject the null hyp, and 
# so we cannot say that smoking changes bab size from dis hospital


# Example 7.28
# h0 = 0 there is no difference in avgs across both exams
# hA ≠ 0 there is a difference in avgs across both exams

µ = 0 
alpha = 0.01

n1 = 30
n2 = 27
n = 27
df = n - 1

x1 = 79.4
x2 = 74.1
x = x1 - x2

s1 = 14**2 / n1
s2 = 20**2 / n2
se = (s1 + s2)**0.5

T = (x - µ) / se
p_value = (1 - pt(T, df)) * 2

# Pvalue is greater than alpha therefore we do not reject the null hypothesis 
# and there is no evidence the tests were unfair and most likely the students
# are just whinning little bitches



#----------7.5 Comparing many means with ANOVA---------------

# Guilded prac 7.42
# 
# Ho: The average on base percentage is equal across the three positions.
# Ha: The average on base percentage varies across some or all of the groups.

k = 3
dfg = k - 1
n = 160 + 205 + 64
df = n - k
f = 0.00803 / 0.00158



# Exercise 7.37
# 
# Ho: diff = 0 or µ1 - µ2 ... µn = 0, There is no difference in the weight of 
# chickens when eating a different type of seed.
# 
# Ha: diff ≠ 0 or µ1 - µ2 ... µn ≠ 0, There is a difference in weight of chickens
# when eating different seed.
# 
# Since the p value is significantly below our alpha of 0.05, we can conclude that 
# there is sufficant evidence that we can reject the null hypothesis that there is 
# no difference in weight between chickens who eat a different seed. From the data
# we can see that the horsebean chickens have the smallest weights and the sunflower
# chickens have the highest weight.

# Exercise 7.39
# 
# a) 
# Ho: There is no difference in physical activity no matter how much coffee you consume
# Ha: There is a difference in physical activity depending on how much coffee you
# consume
# 
# b) The std are all simnilar so that is ok to proceed with the anova test, but the
# amount of participants in each study is wildly different so im unsure if that is
# ok for this test. Everything else looks fine.
# 
# c)



# 
# d) With such a small p value we can conclude that there is significant evidence
# for us to reject the null hypothesis and say that there does seem to be a connection
# with amount of caffine consumed and physical activity dontCheck()


#Exercise 7.41

# a) 
# Ho: µ1 = µ2 =.... µn, there is no difference in gpa across any field of study
# Ha: µ1 ≠ µ2 ≠ ... µn, there is a difference in gpa between atleast two fields
# of study
# 
# b) Since the p value is greater than 0.05, there is not enough evidence in the data
# to reject the null hypothesis and therefore we cannot say that there is a difference
# between the average gpa and these three majors.
# 
# c) 198
# 
# Exercise 7.42
# 
# a) 
# Ho: there is no difference in work hours across all the grouypd in the study
# Ha: There is a difference in work hours in atleast one of the jobs in the study
# compared to the otheres.
# 
# b) The deviations are all fairly similiart so this is ok
# There is many outliers in a few of the groups but the amount of participants 
# is large so this is ok, as well as with the skew, infact the amount of participants
# only really helps the skew and not the outliers, but there are no egregious outliers
# and they seem evenly spread on both sides. The skew overall isnt bad. We assume
# there was independence as we can not varify.
# 
# c)
# n = 1172
# k = 5
# dfg = k - 1
# df = n - k
# totaldf = dfg+df
# Mean_sq = 501.64
# ssg = dfg * Mean_sq
# sse = 267382
# mse = 1 / df * sse
# msg = 501.64
# f = msg / mse
# 
# d) The test statistic shown in the table above is a pvalue=0.068 (f=2.19, df=4,11678) 
# As the p value is gre4ater than 0.05 we do not reject the null hypothesis. The data
# provides strong evidence that the mean level of hours worked amoung the 5 difference
# education groups are not different. Edicational attainment doesnt seem to appear
# to be asscoiated with significantly different working hours in the us as per the
# findings of this study.
# 
# Exercise 7.43
# 
# a) I dont know
# b) Yes This is true
# c) False, the variance across groups should always be relativly close. Using the rule of thumb
# we say that no variance for a group should be more than 2 times away from the others.
# d) False, you can never relax the independecnce parameter for an anova test.

