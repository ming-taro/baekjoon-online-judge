import sys

N = int(sys.stdin.readline().rstrip())

distance_list = list(map(int, sys.stdin.readline().rstrip().split()))
price_list = list(map(int, sys.stdin.readline().rstrip().split()))

price = 0
min_price_index = end_index = N - 2;
# print("min index = %d"%price_list.index(min(price_list[:end_index + 1])))
while end_index >= 0:
  min_price = min(price_list[:end_index + 1])
  min_price_index = price_list.index(min_price)          #리터당 주유비가 가장 저렴한 지점
  
  price += price_list[min_price_index]*sum(distance_list[min_price_index:end_index + 1])
  # print("price = %d, start = %d, end = %d"%(price, min_price_index, end_index))
  end_index = min_price_index - 1 
  
print(price)