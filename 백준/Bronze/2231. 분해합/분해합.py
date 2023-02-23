import sys

N= int(sys.stdin.readline().rstrip())
number = 0

def sum_of_num(n):
  sum = n;
  
  while n > 0:
    sum += n%10
    n //= 10
    
  return sum 

for n in reversed(range(N + 1)):
  if sum_of_num(n) == N:
    number = n

print(number)