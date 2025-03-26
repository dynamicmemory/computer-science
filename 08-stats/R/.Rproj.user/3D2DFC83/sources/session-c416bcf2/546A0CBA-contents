# Assignment 1 - STAT100

# Read in our data, assign it a variable, and attach it as our working dataset
drinking = read.csv("../workshop-datasets/drink_driving.csv")
attach(drinking)

# Function for printing summeries of our column data
print_summeries = function(list_of_variables) {
  
  for (x in list_of_variables) {
    print(summary(x))
  }
}

# Function for plotting a boxplot
boxplot_generator = function(variables, y_label) {

  boxplot(variables, names=label_names, xlab="Number of Drinks", 
          col=c('blue', 'darkgreen','lightgreen','yellow','orange','red'),
          ylab=y_label)
  
}

# list of names for our boxplots
label_names = c("0", "1", "2", "3", "4", "5")

# three lists for each of our response variables we want to plot results for
attention_variables = list(attention_none,	attention_one,	attention_two,	
                           attention_three,	attention_four,	attention_five)

iq_variables = list(iq_none,	iq_one,	iq_two,	iq_three,	iq_four,	iq_five)

coord_variables = list(coord_none,	coord_one,	coord_two,	coord_three,	
                       coord_four,	coord_five)

# Call our summeries function to get all useful information for all variables
# to be used in creating information tables for better understanding of the numbers
print_summeries(attention_variables)
print_summeries(iq_variables)
print_summeries(coord_variables)

# Call our boxplot function to display our boxplots
par(cex.lab='1.35')
boxplot_generator(attention_variables, "Attention Mistakes Made (10 min period)")
boxplot_generator(iq_variables, "IQ Scores")
boxplot_generator(coord_variables, "Balance Test Scores (seconds)")


