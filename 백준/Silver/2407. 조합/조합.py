import sys
import math
from fractions import Fraction

n, m = map(int, sys.stdin.readline().rstrip().split())

print(Fraction(math.factorial(n), math.factorial(n - m)*math.factorial(m)))