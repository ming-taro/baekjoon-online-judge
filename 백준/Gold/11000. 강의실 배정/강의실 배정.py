import sys
from collections import deque
from queue import PriorityQueue

N = int(sys.stdin.readline())

rooms = []

for _ in range(N):
  S, T = map(int, sys.stdin.readline().split())
  rooms.append([S, T])

rooms = sorted(rooms, key=lambda x: x[0])  #빨리 시작하는 시간 순서대로 회의실 정렬

queue = PriorityQueue()
queue.put(rooms[0][1])

for room in rooms[1:]:
  first_room = queue.get()
  
  if first_room > room[0]:   #새로운 회의실이 필요한 경우
    queue.put(first_room)
  
  queue.put(room[1])

print(queue.qsize())