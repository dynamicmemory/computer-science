# workshop 7

data = read.csv("../workshop-datasets/mice_diets.csv")

# h0 µ1 = µ2 = µ3, in other words there is no difference in whatever chem level
# it was we are measuring when a mice has a different diet to others
# 
# ha the chem level of what we are measuring is different in atleast one group

attach(data)

boxplot(IgG_intensity~group)

fac = factor(group)
qqnorm(IgG_intensity, col=fac, pch=19)

mod.aov = lm(IgG_intensity~group, data = data)
anova(mod.aov)

# So the one way ANOVA shows a p value of 0.00000000000028.... something liek that
# therefore we are gonna giga reject the null hypothesis as we have suffiecent 
# evidence to say that there is a different between protein diets when looking at
# whatever this chemical is and its effects


pairwise.t.test(IgG_intensity, group, p.adj='bonf')

# From the pairwise test we can see that Casein protien appears to be significally
# different from the other two protein compounds, but there is insuffient evidence
# that shows that the other two proteins are ddifferent from one another

detach(data)

data = read.csv("../workshop-datasets/fake_lm_data.csv")
data

attach(data)


lm1 = lm(y1~x)
plot(y1~x)
abline(lm1, col='purple')

plot(lm1, which = 1)
plot(lm1, which = 2)


#y2

lm2 = lm(y2~x)
plot(y2 ~ x)
abline(lm2, col='green')

plot(lm2, which=1)
plot(lm2, which=2)

# From the provided graphs we can see that the data is not linear
# 
# It looks like the data has a normal distribution
# 
# We can also see that the variance isnt exactly even throughout the data
# 
# We assume independence of the data
# 
# Overall... badboi plot, nuh uh

#y3

lm3 = lm(y3~x)
plot(y3~x)
abline(lm3, col='blue')

plot(lm3, which=1)
plot(lm3, which=2)

# The plot does not look to be linear, but infact has a curve in the data
# 
# The distribution looks to be normal
# 
# The variance doesnt seem to be consistent throughout the data though one could
# argure it is close enogugh
# 
# We assume independence of the data

#y4

lm4 = lm(y4~x)
plot(y4~x)
abline(lm4, col='red')

plot(lm4, which = 1)
plot(lm4, which = 2)

# The data looks slightly curved, thoufh i could be convinced it is linear.... I would
# say that its not
# 
# Variance does not look to be fully consistent throughout the data
# 
# The distribution dfoes llook to be normal, though htere are a few extreme outliers
# which would cause problems
# 
# We assume independence 

#y5

lm5 = lm(y5~x)
plot(y5~x)
abline(lm5, col = 'orange')

plot(lm5, which = 1)
plot(lm5, which = 2)

# The data looks to be linear, we would apply a linear regression to this data
# 
# We assume indepenedence
# 
# The disrtibution looks to be normal according to the qq plot
# 
# The varience looks to be consistent through the data

# BANG BONUS ROUND

my_x = runif(100, min=0, max=5)

my_y = 6 + 0.2 * my_x + rnorm(100, mean = 0, sd = 1)

my_lm = lm(my_y ~ my_x)
plot(my_y ~ my_x)
abline(my_lm, col = 'pink')

plot(my_lm, which = 1)
plot(my_lm, which = 2)
