# Workshop 2

# calculating some stats
count <- c(4, 3, 7, 3, 4)


mean(count)
# Varience
var(count)
# Standard deviation
sd(count)
summary(count)

# Load in our dataset
email <- read.csv('./workshop-datasets/email50.csv')

# inspect data
head(email)
# inspect shape
dim(email)
# inspect col names
names(email)

attach(email)
search()
# Create a scatter plot by plotting the line_breaks by the num_chars
plot(line_breaks ~ num_char)

# Make it look pretty
plot(line_breaks~num_char, xlab='Number of Characters', ylab='Number of Lines',
     pch=21, cex=2, lwd=1.5, col='deepskyblue3', bg='lightblue')

# calc the mean (numerical variables)
mean(num_char)

# calc counts (categorical variables)
table(spam)

# Create a histogram
hist(num_char, xlab='Number of Characters', ylab='Frequency', col='green',
     breaks=50)

# Calculate the variance and standard deviation of the iris sepal widths
var(iris$Sepal.Width)
sd(iris$Sepal.Width)

# Boxplot of the number of characters column
boxplot(num_char, ylab='Number of Characters')

# Get a summary analysis of our dataset
summary(num_char)

# barplot of specific categorical data
size_of_num <- table(number)
total_number_num <- sum(size_of_num)
barplot(size_of_num, xlab='Number', ylab='Count', col='darkorchid')

# Same as above but now calculated as percentage or portion
barplot((size_of_num / total_number_num), xlab='Number', ylab='Proportion'
        , col='mediumturquoise')


# Mosaic plot of the data
mosaicplot(size_of_num, xlab='', main='', color = c('darkgreen', 'deepskyblue3',
                                                    'orangered'))


mosaicplot(table(spam, number), xlab='', main='', color = c('darkgreen', 'deepskyblue3',
                                                            'orangered'))


# THE ISLAND first look

balance <- read.csv('./workshop-datasets/balance.csv')
deatach(email)
attach(balance)

boxplot(Balance~School_Year, col=c('salmon', 'cadetblue'), ylab= 'Balancing time(s)',
        xlab = 'School year level')






