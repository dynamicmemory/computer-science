# Chapter 2 of Openstat textbook

# Load in the loan50 dataset
loan50 <- read.csv("./workshop-datasets/loan50.csv")

# Basic dot plot
dotchart(loan50$interest_rate)

interest_rate <- loan50$interest_rate

# stacked dot plot 
stripchart(interest_rate, method = "stack", at = 0, pch = 16, 
           main = "Stacked dot", xlab="Interest Rate", ylab = "Number of loans")

# Get the average
mean(interest_rate)

# histogram of the interest rates
hist(interest_rate, col="darkmagenta", xlab = "Interest Rate", 
     breaks = 10)

loans <- read.csv("./workshop-datasets/loans.csv")

table(loans$homeownership)
