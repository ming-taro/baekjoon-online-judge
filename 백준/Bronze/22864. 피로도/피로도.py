import sys

A, B, C, M = map(int, sys.stdin.readline().rstrip().split())

fatigue = 0
task = 0
time = 0

if A > M:
  print(0)
  sys.exit(0)
  
while time < 24:
  if fatigue <= M - A:
    fatigue += A
    task += B
  else:
    fatigue = fatigue - C if fatigue - C > 0 else 0
    
  time += 1

print(task)