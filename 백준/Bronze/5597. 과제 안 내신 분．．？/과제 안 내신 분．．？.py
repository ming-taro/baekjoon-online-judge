import sys

student_before = set()
student_after = set([i for i in range(1, 31)])

for i in range(28):
  student_before.add(int(sys.stdin.readline().rstrip()))
  
student = student_after - student_before
student = list(student)
student.sort()

for n in student:
  print(n)