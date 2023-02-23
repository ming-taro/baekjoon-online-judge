import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
dna_list = [""]*M

for row in range(N):
  dna = list(sys.stdin.readline().rstrip())
  for index in range(M):
    dna_list[index] += dna[index]
  
dna = "ACGT"
h_distance = ""
h_distance_sum = 0

for index in range(M):
  dna_count_list = [dna_list[index].count('A'), dna_list[index].count('C'),
                dna_list[index].count('G'), dna_list[index].count('T')]
  dna_index = dna_count_list.index(max(dna_count_list))
  
  h_distance += dna[dna_index]
  h_distance_sum += N - max(dna_count_list)

print(h_distance)
print(h_distance_sum)