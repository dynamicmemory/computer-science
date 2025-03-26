# Workshop_10

red = 123
white = 102
yellow = 81

# h0 - there will be an even distribution of flowers in the field
# ha - there will not be an even distro of flowers

total = red + white + yellow
expected = total * (1/3)

# expected is greater than 5 therefore we have enough samples
# in our expected to continue

counts = c(123, 102, 81)

chit = chisq.test(counts)
chit

Since our pvalue is is less than 0.05 we can reject the null
hypothesis and say that there is not an even number of colours
in the flower field

chit$stdres

we can also say that there is more red coloured flowers
and less yellow flowers

##################################################

red = 0.4
white = 0.35
yellow = 0.25

h0 - the flowers in the field follow the distribution 
percentages above

ha - the flowers in the field deviate from the above 
proportions

props = c(0.4, 0.35, 0.25)

chit = chisq.test(counts, p=props)
chit

We can say that since our p value is greater than 0.05 we
do not have enough evidence to reject the null hypothesis
that the distribution of colours in the field differs form
to provided propotions

chit$expected

#####################################################

3 fields and 3 colours

red = c(123, 240, 100)
white = c(102, 200, 35)
yellow = c(81, 163, 47)

tulips = rbind(red, white, yellow)
colnames(tulips) = c('A','B','C')
tulips

mosaicplot(tulips, main= 'tulip colour per field',
           color= c('pink', 'gray', 'lightblue'))

chit = chisq.test(tulips)
chit

chit$expected

since our pvalue is less than 0.05 we reject the null hypothesis
and can say that we have enough evidence that shows that there is
an association between the proportion of colours of tulips
between fields, that is to say that there is a similiar
proportions between fields.


######################################################
#### SHARKIES

yoy = c(89, 58, 34, 61)
adult = c(123, 101, 180, 154)
sharks = rbind(yoy, adult)
colnames(sharks) = c("summer", "autumn", "winter", "spring")
sharks

#ho: shark age is independent of season
#ha: shark age is associated with the seasons

mosaicplot(sharks, color = c("red", "orange", "blue", "green"))

#conditions
# we assume independence
# expected values: 48 is the smallest which is greater than 5
# we have more than 2 groups (4 seasons)

# all conditions have been met
chit = chisq.test(sharks)
chit

chit$expected

# the results of our test are an X2 value of 38 with 3 degrees of freedom and a
# p value of 0.0000002 which is less than 0.05, therefore we can reject our null
# hypothesis that shark age is independent of season and say our data provides
# strong evidence that there appears to be an association between the season and
# the number of adult and young sharks in the estry


###########################################################
############# Possums

data = read.csv('../workshop-datasets/brush_tail_possums.csv')
str(data)

attach(data)

possums = table(pop,sex)
possums
#ho: the sex and the population the possums are found in are independent
#ha: the sex and the population the possums are found in are associated

chit_poss = chisq.test(table(pop,sex))
chit_poss

female_total = 19 + 5 + 2 + 2 + 7 + 3 + 5
male_total = 13 + 7 + 4 + 5 + 6 + 10 + 14
total = female_total + male_total

a_total = 19 + 13

a = female_total * a_total / total
repeat for every row.....

pop_combined = ifelse(pop == "C" | pop == "D", "CD", pop)

possums = table(pop_combined, sex)

chit = chisq.test(possums)
chit

chit$expected

We observe that there is insufficient evidence to reject the null hypothesis
we an X2 value of 9.2 with 5 degrees of freedom and a p value of 0.1011, so
we say that the sex of a possum and the population it is found is independent

mosaicplot(possums)
mosaicplot(chit$expected)
