# Workshop 3

# Normal and binomial distributions

# Exercise 4.43
z = 1.56
pnorm(z)


# Exercise 4.45a

1 - pnorm(100, 89, 15)


# Binomial distribution

# finding exactly one of 3 attempts with a 25% chance
dbinom(1, 3, 0.25)

# Finding exactly two of 3 attempts with a 25% chance
dbinom(2, 3, 0.25)

# Finding AT LEAST 1 of 3 attempts with a 25% chance
1 - dbinom(0, 3, 0.25)

# Finding AT MOST 2 of 3 attempts with a 25% chance
pbinom(2, 3, 0.25)

# Note the change from d to p there
# dbinom(x, size=n, prob=p) will calculate P(X=x)
# pbinom(x, size=n, prob=p) will calculate P(X<=x)


# LOADING IN SOME DATA

data = read.csv('../workshop-datasets/rugby_fitness.csv')
str(data)
attach(data)

# main='' == no title
hist(Jump_height, main='')

# Breaks are bins
hist(Jump_height, breaks=5)

hist(Jump_height, breaks=8, main='Jumps scores', xlab='Heights', ylab='Freq', c='red')


# Density
# Plots kind of a distribution looking plot, like a line version of a histo
plot(density(Jump_height))

plot(density(Power_pass))

# Boxplots

boxplot(Sprint1_10m)

summary(Sprint1_10m)


# Practice
boxplot(Sprint1_20m)
summary(Sprint1_20m)

boxplot(Sprint1_total)
summary(Sprint1_total)


boxplot(Height~Position)


boxplot(Yoyo_stage~Position, xlab='Position', col='red')


# Scatterplots

plot(Agility_right ~ Sprint1_total)

plot(Agility_right ~ Sprint1_total, xlab="20m sprint time total (secs)",
     ylab='Agility in right direction (m)',
     pch=20, col='chocolate4')

summary(data)

plot(Mass ~ Height)
# As we can see from the scatter plot, we have a relationsihip between
# the height of the player and their weight. As a player is taller their
# Weight increases... our study has proven nothing as with most humans
# This is true, but still.. fascinating.... wooooooow

# Multi-plots baby

Position = factor(Position)
pairs(~Jump_height+Sprint1_total+Agility_left+Agility_right+Yoyo_stage,
      col=Position)
# The benifits is we can see correlations or trends accross all variables 
# compared to a single variable, giving us the ability to find keys variables
# in the dataset that effect multiple other things... fuck english today.