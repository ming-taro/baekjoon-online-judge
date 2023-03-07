import sys

n = int(sys.stdin.readline().rstrip())

price_list = list(map(int, sys.stdin.readline().rstrip().split()))

jun_price = n
jun_stoke = 0

for price in price_list:
    if jun_price == 0:
        break
    jun_stoke += jun_price // price
    jun_price %= price

sung_price = n
sung_stoke = 0

day = 2

while day < 14:
    if (
        price_list[day - 1] > price_list[day - 2]
        and price_list[day - 2] > price_list[day - 3]
    ):
        sung_price += price_list[day] * sung_stoke
        sung_stoke = 0
    elif (
        price_list[day - 1] < price_list[day - 2]
        and price_list[day - 2] < price_list[day - 3]
    ):
        sung_stoke += sung_price // price_list[day]
        sung_price = sung_price % price_list[day]
    day += 1

jun_price += jun_stoke * price_list[-1]
sung_price += sung_stoke * price_list[-1]

if jun_price > sung_price:
    print("BNP")
elif jun_price == sung_price:
    print("SAMESAME")
else:
    print("TIMING")