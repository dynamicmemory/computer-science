# chapter5 - inference

# 1. Create a set of 250 million entries, where 88% of them are 
#   support and 12% or not

pop_size = 250000000
possible_entries = c(rep('Support', 0.88 * pop_size), rep('Against', 0.12 * pop_size))

# 2. Sample 100 entries without replacement
sampled_entries = sample(possible_entries, size=1000)

# 3. Compute p-hat: count the number that are 'support', then divide by the sample size
sum(sampled_entries == 'Support') / 1000


# Now I will try to simulate doing this simulation 10000 times

# So c is kind of like our list of values that we would be using in python
# so create an empty list
results = c()

# I dont think we need the x here at all but iterate through values 1 to 10000
# and do everything within the braces
for (x in 1:100000) {
  sampled_entries = sample(possible_entries, size=1000)
  sim = sum(sampled_entries == 'Support') / 1000
  
  # So it seems the way it works in r is you use append like python, but you 
  # add the list and the new value in the statement
  results = append(results, sim)
  
}

# Print out a hist of the values
hist(results)


pnorm(-2)

qnorm(0.995)
