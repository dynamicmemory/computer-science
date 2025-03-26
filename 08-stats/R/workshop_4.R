# Workshop 4 Hypothesis Testing

# A study finds that uni students get 7 hours avg a night. Lets see if une
# Students are the same.

# H0 - There will be no change in amount of avg sleep of a une student

# Ha - There will be a significant change in avg hours of sleep

# H0 - mean = 7

# Ha - mean > 7

# Out of a class of 441 we select 40 at random as our sample

sleep = read.csv("../workshop-datasets/UNE_sleep.csv")

attach(sleep)

hist(Hours, breaks=50, main='Hours slept by UNE student', xlab="Hours", 
     ylab='Numb of students', col="purple")
summary(Hours)

qqnorm(Hours)
qqline(Hours, col='purple')

# Sample size
n = 40
# Sample mean
xbar = mean(Hours)
# Sampel standard dev
s_dev = sd(Hours)
# sameple standard error
se = s_dev / (n**0.5)
# Get z score for 95%
z = qnorm(0.975)

conf_int_lower = xbar - z * se 
conf_int_upper = xbar + z * se

h0 = 7
h0_z = (xbar - h0) / se

p_value = 1 - pnorm(h0_z, mean = 0, sd = 1)

# From what I can tell, the results we see are so unlikely and rare that we 
# must reject the naught hypothesis and choose the alternative that une 
# Studnets get more than 7 hours asleep on avg

##########################ANOTEHR EXAMPLE#########################

# Movie example 

movies = read.csv('../workshop-datasets/movies.csv')
attach(movies)

hist(Running.time)
boxplot(Running.time)

xbar = mean(Running.time)
std = sd(Running.time)
n = 129
#H0 - mean = 106, our movies collect will have a mean run time of 106
#Ha - mean ≠ 106, our movies will have a different mean run time then 106

se = std / sqrt(n)

xbar - z * se
xbar + z * se

h0_z = (xbar - 106) / se
h0_z
pnorm(h0_z) * 2

#Conclusion

# Because our p value is greater than alpha of 0.05 we fail to reject our naight hypothesis
# We have 95% confindence that our sample mean falls between 101.67 and 106.25
# with our sample mean equaling 103ish 