import sys

str = sys.stdin.readline().rstrip().split()

N = int(str[0])
M = int(str[1])

group1 = set()
group2 = set()

for i in range(N):
  group1.add(sys.stdin.readline().rstrip())
  
for i in range(M):
  group2.add(sys.stdin.readline().rstrip())
  
group = group1.intersection(group2)

group_list = list(group)
group_list.sort()

print(len(group_list))
for name in group_list:
  print(name)
