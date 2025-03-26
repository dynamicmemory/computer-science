# workshop 11

data <- read.csv("../workshop-datasets/Finches_Dataset.csv")

attach(data)

boxplot(Beak_Length~Sex, xlab = "Birds private parts", ylab = "how long dey beak?",
        col = c("red", "blue", "green"))

reg_lin <- lm(Tarsus~Weight)
plot(Weight, Tarsus, pch = 17, col = "lightgreen", xlab = "How fat dey",
     ylab = "yo dey length doe?")
abline(reg_lin, col = "pink")

Ho: µ_1(died) - µ_2(survived) = 0 - There is no difference in beak depth of birds that survived
the 1977 drought compared to those that died.

Ha: µ_1(died) - µ_2(survived) < 0 - The beak depth of birds that died are smaller
then the beaks of those that survived.

# Test choice
two means t test

# Test conditions

# independence - is assumed... like cmonnnn
# normal distribution

boxplot(Beak_Depth~Survived)

survived = factor(Survived)
qqnorm(Beak_Depth, col = survived)

# Yeah these are fine, we can continue

t.test(Beak_Depth~Survived, alternative = "less")
with a pvalue of 0.0015 < 0.05 we can reject the null hypothesis and say that 
the data provides significant evidence that birds that survived the 1977 drought
had deeper beaks on average.

we can say with 95% confidence that birds that died had on average 0.22m - 0.9mm
less depth in their beaks.


reg_lin <- lm(Tarsus~Weight)
plot(Weight, Tarsus, pch = 17, col = "lightgreen", xlab = "How fat dey",
     ylab = "yo dey length doe?")
abline(reg_lin, col = "pink")

Ho: b_1 = 0 - There is no relationship between weight and length of bod

Ha: b_1 ≠ 0 - There is a relastionshiop between weight and lenght of bod

# Test choice
linear reg
 
# test conditions

# Independence - all birds are birds bitch
# Normal residuals
plot(reg_lin, which = 2)
# Look its not perfect, but its good enough
# constant variance
plot(reg_lin, which=1)
# most of the data is equal variance, trails off towards the end but it is fine.
#linear relationship - yii, look at da chart bruv

summary(reg_lin)
# Equation = y^ = 0.28 + 14.62 * weight
# pvalue = b_98 = 0.27 t = 6.548, pvalue = 0.0000000000002
with a pvalue of near zero we can reject the null hypothesis and say that our data
has significant evidence to say their is a relationship between weight and length.

confint(reg_lin)

Our model accoutns for 30% of variability the data

with 95% confidence we say that for mm longer a bird is, their weight increases
between 0.19grams to 0.36grams

detach(data)
####################################
#Question: Do players from different positions perform differently on the medicine
#ball throwing test?
  
# Exploratory analysis

rug <- read.csv("../workshop-datasets/rugby_fitness.csv")

attach(rug)

rug

boxplot(Power_pass~Position)

# Test choice
# We will use anova as read the question dawg

# hypothesis

#Ho: µfr = µh = µho = µo = µs - There is no different in power pass mean scores
#between all positions 

#Ha: - There is atleast one position that has a different mean for power pass
#score compared to the others.

# Test conditions

# Independence - we assume indepenedence as each memeber did the test once
# Normal distribution
qqnorm(Power_pass)
# we observe a faily normal distro in that qq plot right there aye
# Constant variance - 
sapply(split(Power_pass, Position), sd)
(0.78 / 0.653 < 2) ? TRUE : FALSE
# It equals true and therefore passes the final test.

Power_pass.aov <- lm(Power_pass~Position)
anova(Power_pass.aov)

pairwise.t.test(Power_pass, Position, p.adjust.method = "bonf")

# since our p value is greater than 0.05 our data does not have enough evidence 
# to reject the null hypothesis and therefore we can say that we observe no 
# difference in power pass per position in ourr data




