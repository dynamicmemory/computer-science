#!/usr/bin/env python
# coding: utf-8

# # Assignment 1

# Project name: COSC102 - Assignment 1 <br>
# Author: - <br>
# Email: - <br>
# Date: 03/07/2024

# ## Question 1 

# In this notebook we will be investigating the population relationship between rabbits and foxes. Some basic parameters are:
# - The only food for foxes is rabbits
# - There is unlimited growth for both populations
# - This is a closed system - No foxes or rabbits are added or subtract by outside influences

# In[156]:


# Importing what we will need to answer this question
import matplotlib.pyplot as plt
import numpy as np


# ### Task 1

# In[159]:


# First we initialise our animal population values
fox_population = 35000
rabbit_population = 70000


# ### Task 2

# In[162]:


# Next we initialise values needed for our future computations. These have been 
# provided for us and have been described as reproduction and predation rates.
c = 0.000005
d = 0.3
f = 0.5
g = 0.00001


# ### Task 3

# In[165]:


# First we are defining how many time steps or iterations we will be observing
time_steps = 50

# Here we are creating lists to store our animal populations over time
fox_population_growth = []
rabbit_population_growth = []


# ### Task 4

# In[168]:


# Reinitializing parameters we have already set above just incase you run this cell multiple times, the default
# values are always the same. (The reason they were assigned above was for assesstment sake)
fox_population = 35000
rabbit_population = 70000

fox_population_growth = []
rabbit_population_growth = []

# Iterating over all time
for step in range(time_steps):
    # Generating new population values using a pre given sum and the supplied variables from tasks 1 and 2
    new_fox_population = (((c*rabbit_population)-d)*fox_population)+fox_population 
    new_rabbit_population = ((f-(g*fox_population))*rabbit_population)+rabbit_population
    
    # Using this conditional to control for a negative population, charting a negative population does not
    # make much sense, as well as negative numbers will have undesired outcomes on our equations.
    if new_rabbit_population < 0:
        new_rabbit_population = 0
    elif new_fox_population < 0:
        new_fox_population = 0
    
    # Appending new population numbers to our above made lists
    fox_population_growth.append(new_fox_population)
    rabbit_population_growth.append(new_rabbit_population)

    # Reassigning our rabbit and fox population variables with the newly calculated rabbit and fox populations
    fox_population = new_fox_population
    rabbit_population = new_rabbit_population


# ### Task 5

# In[171]:


# Turn on a specific style for all of our plots
plt.style.use("fivethirtyeight")

# Create our figure
fig, ax=plt.subplots(figsize=(10,5))

# Create a list of each number in our time series for plotting
# Using the length of our population lists for the upper bounds
# of this list
time = np.arange(0, time_steps)

# Plot the fox population
fox = ax.plot(time,fox_population_growth,"m")

# Plot the fox population
rabbit = ax.plot(time,rabbit_population_growth,"c")

# Add a title and labels
ax.set_title("Fox v Rabbit population over Time")
ax.set_xlabel("Time")
ax.set_ylabel("Population", rotation=90)

# Setting the range of values shown on the x axis
ax.set_xlim([0,45])

# Creating the legend for our chart
ax.legend(["Fox Population", "Rabbit Population"], loc="upper right")

plt.show()


# ### Analysis of the chart

# So from our above chart, there are a few things that we can infer about the relationship between the populations of <br>
# the two animals in our experiment:
# 1. It seems that the fox population tends to lag the rabbit population. <br>
# This makes sense as with lower fox numbers the rabbits are able to reproduce<br>
# much quicker then when there is a larger number of foxes hunting them.
# 2. As this was an isolated test with no outside elements effecting the results, <br>
# our data potentially suggests to no control in the form of conservation, may lead <br>
# to a total population wipe out of both animal species.

# ## Question 2

# In this question we are looking at some historical data of tempreatures and rainfall to try gain insights into the global issue of climate change. <br>
# In the following sections we will be:
# - Calculating some basic statistics from out data
# - plotting our data  over time to real any patterns
# - Calculate moving averages of the data
# - Check the correlation between rainfall and tempreature and plotting our results
# - Locating in our data significant points such as their highest and lowest points
# 
# Lets get started

# In[177]:


# First we start with importing all we will need to perform what we want to perform
import pandas as pd
import numpy as np


# ### Task 1. Data Cleaning

# In[180]:


# Now we load in our dataset as a pandas dataframe
climate_data = pd.read_csv("climate_data.csv")


# Check out data to see what we are working with

# In[183]:


climate_data.head()


# In[185]:


climate_data.info()


# As we see from our info we are working with a data set with three column: Date, Tempreature and precipitation. <br> 
# It looks like we have all values for date and tempreature but we are clealy missing some Precipitation data. <br>
# We have a few options on what we can replace it with. For this type of dataset I think it is approitate to replace <br>
# then values with the mean of the series.

# In[188]:


# First we get the mean of the precipitation column as that is the column we have identified with missing values
rain_mean = climate_data.Precipitation.mean()

# Fill the nan values with the mean and reassign our data
climate_data = climate_data.fillna(rain_mean)

# Check to make sure it worked
climate_data.info()


# ### Task 2. Descriptive Statistics

# #### Range Function

# In[192]:


# Function to calculate the range of our series
def calc_range(*series):
    """
    Calculates the range of a pandas series
    param: Series - series, a pandas dataseries
    """
    # For each series, get the max and minus the min of the to find the range
    for set in series:
        range = max(set) - min(set)
        print(f"- Range: {round(range, 4)}")


# #### Mean Function

# In[195]:


# Function to calculate the average
def calc_mean(*series):
    """
    Calculates the mean of a pandas series
    param: Series - series, a pandas dataseries
    """
    # initialize variables sum and length for calculation
    sum, length = 0, 0

    # Iterates through all series passed into the function
    for set in series:
        # Iterates through all values in a given series and adds them together
        # into the sum variables, and adds a +1 to the length variable to calc
        # how many values appear in the series. We then divide the sum of values
        # by the length of the series we round the avg and return that number.
        for entry in set:
            sum += entry
            length+= 1
        return round(sum/length, 4)


# #### Median Function

# In[198]:


# Function to calculate the median
def calc_median(*series):
    """
    Calculates the median of a pandas series
    param: Series - series, a pandas dataseries
    """

    # Iterate through all series
    for set in series:
        # Using the .sort_values function from pandas to sort our series in
        # ascending order.
        set.sort_values()

        # Using the .size function from pandas to find the mid point of our
        # series by dividng the total entries by two and rounding up.
        mid_point = round(set.size / 2)
        print(f"- Median: {round(set[mid_point], 4)}")


# #### Standard Deviation Function

# In[201]:


# Function to calculate the standard deviation
import math # needed for square rooting

def calc_std(*series):
    """
    Calculates the standard deviation of a pandas series
    param: Series - series, a pandas dataseries
    """
    # Iterate through each series 
    for set in series:
        # Get the mean for the series
        mean = calc_mean(set)

        # Subtract the mean from every entry in the series and square the result
        deviations = [(entry - mean)**2 for entry in set]

        # Get the mean of the above deviations
        variance = calc_mean(deviations)

        # Take the square root of the above variance to get the standard deviation
        std = math.sqrt(variance)

        # return the result
        return round(std,4)



# #### Total Statistics Function

# In[204]:


def calculate_stats(*series):
    """
    Calls a series of statistical functions to find the range, mean, median and
    standard deviation for any number of given pandas series
    param: series, a pandas dataseries
    """

    # Iterates through all series
    for set in series:
        # Prints the series name, and calls all statistical functions for the series.
        print(f"{set.name} Stats:")
        calc_range(set)
        print(f"- Mean: {calc_mean(set)}")
        calc_median(set)
        print(f"- Standard Deviation: {calc_std(set)}")
        print()


# In[206]:


# Assign our two columns of data into separate variables
temperature = climate_data.Temperature
precipitation = climate_data.Precipitation

# Call our statistics calculation method, passing in our series
calculate_stats(temperature, precipitation)


# In[208]:


# Alternatively you can use the .mean and .median functions built into pandas to evaluate
# these values, as well as the .describe function for a few other interesting stats

climate_data.describe()


# ### Task 3. Time Series Analysis

# In[211]:


# Create a function that will plot out all the values in our dataframe onto individual charts
def createAChart(x, y, title):
    """
    Creates a chart of all series passed to it. 
    
    Takes an input, x which is a pandas series for the x-axis and another input, y which is a list
    of tuples for the y-axis. It then plots each y-axis values one at a time until there are no 
    more left, at which point it applies all the style changes, appends a legend and presents the
    chart.
    params: 
    - Pandas series: x - the x coordinates for a plot
    - List: y - a tuple of all the why values with the desired colour and label for the series
    - String : title - the title for the created chart.
    Returns: None
    """
    
    # Create the figure
    fig, ax=plt.subplots(figsize=(15,6))

    # Plot results
    for series in range(len(y)):
        ax.plot(x, y[series][0],  y[series][1], label=y[series][2], linewidth=2)

    # Add titles, labels and fix the x axis values to display correctly
    ax.set_title(title)
    ax.set_xlabel(x.name)
    ax.set_ylabel(y[0][0].name)
    ax.set_xticks(ax.get_xticks()[::90])
    plt.xticks(rotation=45)
    plt.legend()
    
    plt.show() 


# #### Temperature over time & Moving average

# In[214]:


# Calculate the 30 period moving average for temperature
temp_moving_avg = temperature.rolling(30).mean()

# Create a list of tuples for our function, the tuple conatains our y values, their colours and labels
y = [(temperature, "b", "Temperature"), (temp_moving_avg, "r", "30 period Avg")]

# Call our function for temperature
createAChart(climate_data.Date, y, "Temperature over Time")


# #### Precipitation over Time

# In[217]:


# Create our precipitation 30 period moving average
prec_moving_avg = precipitation.rolling(30).mean()

y = [(precipitation, "r", "Percipitation"), (prec_moving_avg, "b", "30 period avg")]

# Call our function for precipitation
createAChart(climate_data.Date, y, "Precipitation over Time")


# #### Comparison of moving Averages

# Before doing a correlation analysis, I thought it might be a good idea to see if we can visualize any correlation in the data. Are there any matching trends,<br> 
# or are the two series perhaps inverse to one another or always in-sync. Below we compare both of the series moving averages that we calculated above to see <br> 
# if we can spot any similarities.

# In[221]:


# Create the plot
fig, ax=plt.subplots(figsize=(20,10))

# Create a second axis for the plot
ax2 = ax.twinx()

# Plot the first series to the first axis and the second series to the second axis
ax.plot(climate_data.Date, temp_moving_avg, "r")
ax2.plot(climate_data.Date, prec_moving_avg, "b")

# Set the titles and labels and ticks for the chart.
ax.set_title("Temperature vs Precipitation moving averages")
ax.set_xlabel("Date")
ax.set_ylabel("Temperature", color = "r", fontsize=24)
ax2.set_ylabel("Precipitation", color= "b", fontsize=24)
ax.set_xticks(ax.get_xticks()[::90])
ax.tick_params(axis='x', rotation=45)
plt.grid()
plt.show() 


# In general it would seem that there is very little correlation between the two series. Towards the middle of the chart we can see <br>
# a small amount of inverse correlation with the temperature dropping to its lowest average and the precipitation rising to one of its peaks. <br>
# But this seems to be the only itme the two series act in such an obvious inverse manner like this. Towards the end of the chart we do see that <br>
# both data series start to rend downwards in what appears to be somewhat of a lock step manner, but when compared to the rest of the dataset, this <br>
# one moment in time is not enough to make any meaningful predictictions or assumptions on whether or not there is a connection from one dataset to <br>
# the other. Lets now use pandas .corr function to see if there is any correlation between the original sets of climate data.

# ### Task 4. Correlation analysis

# To calculate the correlation between the temperature and precipitation we need to:
# 1. Get the averages of our two series
# 2. Calculate the difference between each of the values of our series and their average's
# 3. Multiply those two differences together.
# 4. Add the sum of every product from step 3 together
# 5. Divide the number produced in step 4 by the size of the whole series to get the covariance
# 6. Now divide the covariance by the product of both standard deviations of our two series
# 7. You now have the correlation coefficient, (time to drop out and go to wall street!)

# In[226]:


# Now lets go through the above steps and calculate the correlation between the temperature and precipitation

# Get the mean for the series
temp_mean = calc_mean(temperature)
prec_mean = calc_mean(precipitation)

# Create a list of all the differences of one series multiplied by the differences of the other series
covar_ls = [((temperature[i]-temp_mean) * (precipitation[i]-prec_mean)) for i in range(len(temperature))]

# Iterate and add together all of the above calculated products
sum = 0
for entry in covar_ls:
    sum += entry

# Divide the sumation by the total number of entries to create the covariance
covariance = sum/(len(covar_ls))

# Divide the covariance by the product of the standard deviations of our pandas series
correlation = covariance / (calc_std(temperature) * calc_std(precipitation))

# Print out the results
print("Calculated Correlation between Temperature and Precipitation")
print(round(correlation, 8))

# Print a blank line
print()

# Alternatively we could just use the .corr function built into pandas to achieve the same result.
print("Pandas Built in Correlation between Temperature and Precipitation")
pand_corr = temperature.corr(precipitation)
print(round(pand_corr, 8))


# This correlation metric shows us that there is practically no correlation at all between our data series as <br>
# a score of 1 would be considered a perfect correlation and -1 would be a negative correlation, and 0 indicates <br>
# no correlation at all. Our data set is very close to zero, indicating practically no correlation exists.

# In[229]:


# Create a figure
fig, ax=plt.subplots(figsize=(10,8))

# Add a scatter plot to our figure
ax.scatter(temperature, precipitation, color="c" ,edgecolor="b" ,s=80)

# Create our linear regression using the formula for a linear function
# y = m*x+b and add it to our plot
m, b = np.polyfit(temperature, precipitation, 1)
ax.plot(temperature, m*temperature+b, "m")

# Add titles and labels
ax.set_title("Relationship between Temperature and Precipitation", fontsize=20)
ax.set_xlabel("Temperature")
ax.set_ylabel("Percipitation", rotation=90)
ax.annotate("Regression Line", xy=(25.5,3.2), xytext=(29,5), arrowprops=dict(facecolor="black", shrink=0.05))

plt.show()


# Finally looking at our scatter plot of all of our data points combined, we see what has already been<br>
# confirmed above, No correlation, just a cloud of data sitting in the center with a slight horizontal<br>
# line sitting right where our regression line is sitting.

# ### Task5. Extreme Events Analysis

# In[233]:


# Create a function to plot our results
def extreme_events(series):
    """
    Takes a pandas series, sorts it in both ascending and decending order
    then plots the top ten results from each sorting to a subplot of two
    scatter plots. 
    param: series, Series - A pandas data series
    """
    # Use pandas sort_values function to sort the values
    highest = series.sort_values(ascending=False)
    lowest = series.sort_values()

    # Create the figure
    fig, ax=plt.subplots(1,2,figsize=(14,5))

    # Create a numpy array for the 10 
    time = np.arange(0,10) 

    ax[0].scatter(time, highest[:10], color="m")
    ax[1].scatter(time, lowest[:10], color="c")

    # Add titles and labels to all of our plots
    ax[0].set_title(f"Highest {series.name}", fontsize=15)
    ax[1].set_title(f"Lowest {series.name}", fontsize=15)

    ax[0].set_xlabel("Days", fontsize=12)
    ax[1].set_xlabel("Days", fontsize=12)
    ax[0].set_ylabel(series.name, fontsize=12)


# In[235]:


# Call the plotting function for temperature
extreme_events(temperature)


# In[237]:


# Call the plotting function for precipitation
extreme_events(precipitation)


# ### Final Analysis

# Overall this was an interesting experiment we performed on two years of temperature and precipitation data. We learnt that <br>
# there does not seem to be much correlation between the day to day temperatures and rainfall (according to the dataset we used) <br>
# Perhaps with a bigger dataset with different weather variables that is collected over a much longer timeframe (100s of years), we  <br>
# may perhaps be able to see a much stronger correlation between variables or a more cyclic pattern of the weather over time, or perhaps <br>
# not, our experiements in this notebook suggest otherwise for now.

# In[ ]:




