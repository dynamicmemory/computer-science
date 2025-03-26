# A few guided questions from chapter 4 on distribution

height_mean <- 70
std <- 3.3
x1 <- 67
x2 <- 76

z1 <- (x1 - height_mean) / std
z2 <- (x2 - height_mean) / std

# pnorm() finds percentile if you have z score
pnorm(z1)
pnorm(z2)

# qnorm() finds z score if you have percentile
qnorm(0.68)


pnorm(1)


# Worlds population in terms of number of people in a std dev
# 1std = 5,508,000,000 inside and 2,592,000,000 outside 
# 2std = 7,695,000,000 inside and 405,000,000 outside
# 3std = 8,075,700,000 inside and 24,300,000 outside
