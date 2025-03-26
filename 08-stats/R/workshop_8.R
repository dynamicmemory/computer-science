# workshop-8

data = read.csv('../workshop-datasets/brush_tail_possums.csv')

attach(data)
pop

plot(totalL, headL, ylab = 'head length (cm)', xlab = 'total length (cm)',
     pch = 17)

h0 - slope = 0, that is to say there is no relationship between the total lenght
of a possum and its head lenght

ha - slope will not equal 0, that is to say that there is some relationship
between the possums length and its head size.

4 conditions are

Linearity - the data looks like it will fit a linear model well, but lets test
it by running some code

plot(headL ~ totalL, col = 'plum', pch = 17)
abline(lm(headL ~ totalL))

it seems that linearity has been met for this data from the plot provided

Constant variance - we will run a SLR plot? to see if the variance is constant

SLR = lm(headL~totalL)
plot(SLR, which = 1)

the variance seem close enough to even and consistant

Are the residuals on normally distributed

plot(SLR, which =2)
shapiro.test(SLR, col = "brown")
The sharpio test is if you have ambiguity on the qq plot line when checking
for normal residuals. If you get a Pvalue GREATER than 0.05, you are all good
otherwise the residual are distro normally.

toward the right there is a little bit of lift off from the line with some outliers
but again, it is good enough to continue.

Independence - we assume independence of our data

now lets get a summary of our data

summary(SLR)
confint(SLR)
with a p value significantly smaller then 0.05 we can reject the null hyp
and state with strong evidence from the data that a possums total length
does indeed have an effect on the head length of the possum. Our model says
that it accounts for 45% of variance in head length and with 95% confidence 
we say that we could expect an increase in heaf length of (0.44 - 0.68) per cm 
of total length increase of the possum


Now we do the same for total length and tail length

h0 = The slope = 0, that is to say there is no realtionship between total lenght
of a possum and its tail length

ha = The slope will not = 0, that is to say that there is a relationsihopp between
a possums total length and its tail length

plot(tailL~totalL, ylab='Tail length (cm)', xlab='total length(cm)', pch=17)

Now we will check for linearity

abline(lm(tailL~totalL), col = 'red')

the data seems to fit a linear model and passes the first test

constant variance

slr2 = lm(tailL~totalL)
plot(slr2, which = 1)

there does appear to be one outlier that is more further away from the rest
of the data but there is only one and it is not significant enough, our data
passes the second test

Now we test for normal distro of our residuals

plot(slr2, which=2)

the data seems struagh enough on the line and therefore passes the third test

we assume independence of out data and therefore passes the 4th and final test

summary(slr2)
confint(slr2)


SUMMARY OF data

with our p value sitting significantly below 0.05 we can reject our null
hypothesis and say that we have significant evidence that a possums tail length
has a strong relationshiop with its total length. Furthermore we can say that our
model shows that a possums total length can account for 31% of its tail length
that is to say that our model explains 31% of the variance in tail length. Finally
we can say with 95% confidence that for every cm change in a possums total lenght
we could expect a change of (0.18 - 0.34)cm of change in a possums tail.







