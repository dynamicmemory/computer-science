"../hunter_bidens_laptop/pdiddys_guestlist/epsteins_flightlogs/random_csvs/A2_data.csv"

# Bring out data into the script
data <- read.csv("../workshop-datasets/A2_data.csv")

# quick check to make sure it worked
str(data)

# attach our dataset so we don't need to break our '$' key with excessive use.
attach(data)

##############################################################################
# Question 1 - Is there a difference in javelin performance between athletes 
# participating in the Decathlon and the Heptathlon. 
##############################################################################

# Boxplot to show the differences in each sports javelin scores
boxplot(javelin~sport, ylab="Javelin Score (meters)", xlab="Sport", 
        col=c("lightblue", "brown"))


# creating subsets to analysis scores independent of sport
decathlon <- subset(data, sport=="Decathlon")
heptathlon <- subset(data, sport=="Heptathlon")

# Create "eas'ier' to use" variables
d_javelin <- decathlon$javelin
h_javelin <- heptathlon$javelin


# Grab some sample stats for our two groups
summary(d_javelin)
summary(h_javelin)

# obtain the sacred standard devs
sd(d_javelin)
sd(h_javelin)

# checking the heptathlon data to inspect an outlier we observed in the boxplot
hist(h_javelin, breaks = 10, xlab="Javelin scores (Heptathlon)",
     main ="", col="pink")

# decathlon scores just to be sure, using a finer bin amount for more definition
hist(d_javelin, breaks = 20, xlab="Javelin scores (Decathlon)",
     main ="", col="lavender")


# The t-test
t.test(javelin~sport)


###############################################################################
# Question 2 - Is there a difference in long jump performance between athletes
# from different countries in the heptathlon
##############################################################################

# using our defined heptathlon grouping from above we will make two new groups
hep_long = heptathlon$long_jump
hep_country = heptathlon$country

# Create a boxplot to see what we are working with here
boxplot(hep_long~hep_country, ylab="Long Jump Score (meters)",
        xlab="Athletes Country", 
        col=c("lightblue", "salmon", "lightgreen", "gold"))

# lets see how many instances of athletes we have per country
table(hep_country)

# and now let's get a little convoluted by calling sapply to create a matrix
# of summary stats for long jump scores split by country... fancy i know. 
sapply(split(hep_long, hep_country), summary)

# Because im feeling lazy, we are going to do the same for the IQR's and std's
sapply(split(hep_long, hep_country), IQR)
sapply(split(hep_long, hep_country), sd)

# Creating a factor out of our countries to use in a qqplot
countries = factor(hep_country)
# Using a qqplot to double check normality of our countries scores
qqnorm(hep_long, col=countries, pch=19, cex = 0.75, main = "")
legend(-2.5, 6.5, legend = c("CAN","CZH","ITA","RUS"), 
       col = c("black", "salmon", "green", "skyblue"), pch = 19, bty = "n")


# Running the anova test
# Create our linear model 
long_jump.aov <- lm(hep_long~hep_country)
# obtain the output
anova(long_jump.aov)

# Our p value shows there is differences, so now we want to find where they are
pairwise.t.test(hep_long, hep_country, p.adjust.method = "bonf")


###############################################################################
# Question 3 - Is there a linear relationship between an athlete's age and  
# performance in the hurdles in the decathlon event? 
##############################################################################

# Again we will start by making variables for easier access to our information
dec_age = decathlon$age
dec_hurdle = decathlon$hurdles

# Since our ages are in days from birth to competition start, we want to change
# that to just "athletes age in years", so below we will adjust our decathlon 
# ages by dividing each by 365 to get the age equiv of each athlete. 
# We technically don't need to do this, but it will make interpreting our 
#results a whole lot less convoluted talking about days old vs years, which 
# is far more intuitive.
dec_age <- dec_age / 365


# Now we plot the age of our athlete vs there hurdle performance
plot(dec_age, dec_hurdle, ylab = "110m Hurdle time (seconds)",
     xlab = "Athletes age (years)", pch = 15, col = "violet", cex = 0.75)


# We had trouble analysis the strength of our relationship so below we will 
# find the correlation coef between our variables
cor(dec_age, dec_hurdle)

# Creating our linear model and checking for linearity for our test conditions
lin_reg = lm(dec_hurdle~dec_age)
abline(lin_reg)

# Get our plots to check for vairance and distribution of the residuals in our
# data
plot(lin_reg, which=1)
plot(lin_reg, which=2)


# Run a sharpio test to double check distribution of our residuals
shapiro.test(lin_reg$residuals)

# Finally getting the summary information for our model
summary(lin_reg)

# and the confidence interval
confint(lin_reg)
