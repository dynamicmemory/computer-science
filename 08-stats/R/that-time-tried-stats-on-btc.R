data = read.csv('../../../Documents/btc-daily-csv/BTC-1D-PRICE-HISTORY.csv')

attach(data)

difference = (close - open) / open

hist(difference, breaks = 50)
boxplot(difference)

p = mean(difference)
n = length(close)

# Success failure conditions
n*p
n*(1 - p)
# Passes the tests

std = sd(difference)

se = (p*(1 - p) / n)**0.5

x = 0.1
z = (x - p) / std

qnorm(0.9, p, std)

#mu = mean(difference)
#s = sd(difference)
#n = length(open)
#df = n - 1
#se = s / n**0.5
#x = 1
#T = (x - mu) / s

#p_value = (1 - pnorm(T, mu, s)) * 2
