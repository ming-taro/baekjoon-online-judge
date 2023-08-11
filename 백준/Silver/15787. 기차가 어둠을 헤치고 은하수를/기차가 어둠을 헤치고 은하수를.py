import sys

N, M = map(int, sys.stdin.readline().rstrip().split(" "))

train = [[False for _ in range(21)] for _ in range(N + 1)]

for _ in range(M):
  command = list(map(int, sys.stdin.readline().rstrip().split(" ")))
  
  if command[0] == 1:   #승차
    train[command[1]][command[2]] = True
  elif command[0] == 2: #하차
    train[command[1]][command[2]] = False
  elif command[0] == 3: #한 칸씩 뒤로 이동
    if train[command[1]][20]:
      train[command[1]][20] = False
    for seat in range(20, 1, -1):
      if train[command[1]][seat - 1]:
        train[command[1]][seat] = True
        train[command[1]][seat - 1] = False
  else:   #한 칸씩 앞으로 이동
    if train[command[1]][1]:
      train[command[1]][1] = False
    for seat in range(1, 20):
      if train[command[1]][seat + 1]:
        train[command[1]][seat] = True
        train[command[1]][seat + 1] = False

train_list = []

for number in range(1, N + 1):
  if train[number] not in train_list:
    train_list.append(train[number])

print(len(train_list))