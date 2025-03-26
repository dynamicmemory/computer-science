# Workshop_5

data = read.csv("../workshop-datasets/Full_miner_birds.csv")

#------------------TEST 1 Max Freq---------------------------


#h0 = 2500, the avg max frequency of the miner bird call is around 2500
#hA > 2500, the avg max freq of the miner bird call is significantly greater
# than 2500

attach(data)

mew = 2500
x = mean(Max_freq)

# Data exploration
hist(Max_freq, breaks = 10, xlab="Frequency (Hz)", ylab="Instances")

boxplot(Max_freq)

qqnorm(Max_freq)
qqline(Max_freq, col = "Red")

# All plots show medium amount of skew, since we have a smaple size of 30
# we apparently are allowed a medium amount of skew, therefore this is fine.

# number of observations and 
n = length(Max_freq)
df = n - 1

# sample standard deviation
s = sd(Max_freq)
se = s / n**0.5

T = (x - mew) / se
p_value = (1 - pt(T, df))

# We can use the t.test() function to do all I have just done but more simply
# We want to know if the average is greater than 2500
t.test(Max_freq, mu=2500, alternative = "greater")

# Using two sided gives us the conf interval
t.test(Max_freq, mu=2500, alternative = "two.sided")

# We reject the null hypothesis 


#--------------Test 2 Duration of call per sex-----------------

#h0 = 0, there is no difference in length between calls of different sexes(di)
#hA \= 0, there is a difference in length between calls of different sexes(di)

data

boxplot(di~Sex, col = c("Red", "Blue"), names=c("Female", "Male"), ylab="di Length")

stripchart(di~Sex, method="jitter")

# Turning the sex variable into a factor to use as a colour below
Sex = factor(Sex)
qqnorm(di, col=Sex, pch = 20)

mean = aggregate(di, list(name= Sex), mean)
std = aggregate(di, list(name= Sex), sd)
length = aggregate(di, list(name= Sex), length)

mean

t.test(di ~ Sex)

# Since the p value is less than the alpha of 0.05 and more importantly the 
# differences in mean is not 0, we can reject the null hypothesis and conclude
# That we have observed evidence that there is a difference between male and 
# female calls through the first section of the call also known as the di

detach(data)

#---------------------ZINC TEST 1-----------------------

data = read.csv('../workshop-datasets/Zinc_Single.csv')

#h0 = 0.5, the average concentration of Zinc in tank 2 is not greater than 
# the safe levels of 0.5ppm
#hA > 0.5, the average concentration of zinc in tank 2 is greater than 
# the safe levels of 0.5ppm

attach(data)

n = length(WT_2)
df = n - 1

hist(WT_2, breaks=10)
boxplot(WT_2, col = "Red", ylab="Zinc concentrataion(ppm)")

qqnorm(WT_2)
qqline(WT_2)

mew = 0.5
x = mean(WT_2)
std = sd(WT_2)
se = std / n**0.5
T = (x - mew) / se
p_value = 1 - pt(T, df)

t.test(WT_2, mu= 0.5, alternative="greater")

# The likely hood that we gathered samples with the average we got is incredibly
# unlikely. Therefore since our p-value is off the charts, we can conclude with
# 95% confidence that the zinc level in tank 2 is high then the safe level
# of 0.5

detach(data)

#--------------ZINC TEST 2-----------------------

data = read.csv('../workshop-datasets/zinc_2.csv')
attach(data)

boxplot(Zinc_levels~Tank)
# looks like significant skew in tabk 1 more tests to see
stripchart(Zinc_levels~Tank, method="jitter")

tank = factor(Tank)
qqnorm(Zinc_levels, col=tank)

# Look there is a bit of skew, but I think its workable.

#h0 mu1 - mu2 = 0, the difference in Zinc levels is 0 as in there are is no difference
#hA mu1 - mu2 \= 0, there is significant difference in zinc levels between water
# tanks

means = aggregate(Zinc_levels, list(names=Tank), mean)
stds = aggregate(Zinc_levels, list(names=Tank), sd)
lengths = aggregate(Zinc_levels, list(names=Tank), length)

x1 = 0.5649
x2 = 0.7928
xbar = x1 - x2

lengths
n = 10
df = n - 1

stds
# se = sqrt(  s1^2 / n  + s2^2 / n  )
se1 = 0.147**2 / n
se2 = 0.062**2 / n
se = (se1 + se2)**0.5
T = (xbar - 0) / se
p_value = pt(T, df)

t.test(Zinc_levels ~ Tank)
