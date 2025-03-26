# Linear regression

# Exercise 8.23

a)

time-mean = 129
time-std = 113
dist-mean = 108
dist-std = 99
corr = 0.636

b1 = time-std / dist std * corr # Formula is response std / explain std * correlation

# formula for figuring intercept or b in math terms or y in stat terms is...

y - y0 = b1(x - x0) # slope point form from math just diff variable names for stats

# simplfying and replacing variables with the means, we get

y - 129 = b1(x - 108)
y = b1*x + 50.6       # after some algebra

b) intercept is 50.6
slope is 0.726

c) r^2 = corr^2
0.404

About 40% of variation in the data was due to distance between stops, or distance
between stops is responsible for around 40% of the variance in our data...

d) distance between santa barbara and la is 103miles, calc how long it takes to
get between the two cities using our model

y = 0.726*103 + 50.6 
y = 125 minutes

e) 43 is the residual value, since our residual is positive, our model under estimates
the travel time.


f) No it wouldnt be as this stop would be highly leveraged and cause significant
impact on our linear regression line


Exercise 8.31

a)
Positivly correlated as the height of an idividual increases so does their weight

b)
y = 1.0176*x - 105.0113

c)
h0 - b1 = 0, the linear model has 0 slope
ha - b1 /=0, The linear model has a slope different from 0, that is height effects weight

Since our pvalue is 0.0000??? we dont actually see where it ends, this is less than
0.05, therefore we reject the null hypothesis and can say that we have evidence that
height has some effect on weight

d) R^2 = 0.072^2 = 0.5184
This tells us that height accounts around 51% of the varience of someones weight
in out study

Exercise 8.35

a)
h0 - The linear models true slode is equal to 0
ha - the linear models true slope does not equal zero, that is to say that there is more 
murders in impoverished areas

b)
since the p value is well below 0.05 we can reject the null hyp and say that the
percentage of poverty in an area is related to the number of murders in that area

c)

x = 2.559
se = 0.39
df = 19

tstar = qt(0.975, 19)
tstar

lim1 = x + tstar*se
lim2 = x - tstar*se
lim1
lim2

FOr each percentage point that poverty is higher, we expect the murder rate to increase
by 1.7 - 3.3 per million

d) Yes, we have rejected the null hypothesis of 0 and the conf interval does not
contain 0, therefore they are in agreement.

Exercise 8.44

a)

y - y0 = b1(x - x0)
y - 3.9983 = b1(x --0.0883)
4.010 - 3.9983 = b1(0 - -0.0883)

0.0117 = b1 * 0.0883
b1 = 0.0117 / 0.0883

b1 = 1.325

b) yes it shows sufficent evidence as the slope is positive, therefore the relationship
is positively correlated.

c)
Data is linear
residuals seem even
nearly normal distribution of residuals
we assume idependence