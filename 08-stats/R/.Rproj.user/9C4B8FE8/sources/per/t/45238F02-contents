# Workshop 6

# Is the duration of the first part of a miner birds call significantly 
# difference than the second part of the call.

# Paired t-test

data = read.csv('../workshop-datasets/Full_miner_birds.csv')

# Null hypothesis
# µ1 = µ2, there will be no significant difference between the two parts of the 
# calls

# A hypothesis
# µ1 ≠ µ2, there will be a significant difference between the two parts of the 
# calls

attach(data)

diff = (dii -di)
diff

boxplot(diff)
qqnorm(diff)

x = mean(diff)
std = sd(diff)
n = length(diff)
df = n - 1
se = std/n**0.5
T = (x - 0) / se
p_value = (1 - pt(T,df))*2
t.test(dii, di, paired = TRUE)

# Our p value is less than 0.05, therefore we reject the null hypothesis and 
# we can say that there is a significant difference between the start and the 
# end of the miner birds call.

detach(data)

#!@#$%^&^%$#$@ ZINC DATASET PAIRED T TEST !@#@#%@^&*&%($&#*)

data = read.csv('../workshop-datasets/Zinc_paired.csv') 

attach(data)  

#H0 - µ1 - µ2 = o - There will be no difference in the means of the zinc content 
# from the top of the tanks to the bottom of the tank

#Ha - µ1 ≠ µ2 - There will be significant difference in the zinc content between
# the two spots in the tank

diff = Watertank1_bottom - Watertank1_surface 

boxplot(diff)

n = length(diff)
df = n -1
x = mean(diff)
s = sd(diff)
se = s/n**0.5
T = x / se
p_value = (1 - pt(T, df))*2

# The p value is significally below 0.05 and the mean of the difference is not 0
# Therefore we can reject our null hypothesis and say that there is a significant
# difference in the average zinc content from the top of the tank to the bottom

########################## ANOVA Plant-Growth #######################

PlantGrowth

str(PlantGrowth)

attach(PlantGrowth)
?PlantGrowth

# Null hypothesis µ1 = µ2 = µ3, There is no difference between the treatments
# or the control group from the experiement.

#Ha µ1 ≠ µ2 ≠ µ3, There is a difference in average crop yeild between the different
# Treatemtns.

boxplot(weight ~ group)

qqnorm(weight, col=group, pch=19)

aggregate(weight, list(Treat=group), sd)

mod.aov = lm(weight~group, data = PlantGrowth)
anova(mod.aov)

pairwise.t.test(weight, group, p.adj='bonf')

# From this data we can reject the null hypothesis, that is to say that there a 
# difference between the control and the treatments, and from a secondary testing
# We can say that the difference is between the two different treatments as the 
# treatment 2 delivers a greater yeild.