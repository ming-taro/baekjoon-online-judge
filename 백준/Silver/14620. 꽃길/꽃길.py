import sys
from itertools import combinations

def get_flower_bed_list(point):
    return [point - N, point - 1, point, point + 1, point + N]

def is_possible_to_plant(my_point, other_point):
    my_point_list = get_flower_bed_list(my_point)
    other_point_list = get_flower_bed_list(other_point)
    
    for point in my_point_list:
        if point in other_point_list:
            return False
    
    return True


def get_seed_price(point):
    return price_list[point[0]] + price_list[point[1]] + price_list[point[2]]


N = int(sys.stdin.readline().rstrip())
seed_list = []

for _ in range(N):
    seed = list(map(int, sys.stdin.readline().rstrip().split()))
    seed_list += seed

price_list = [0 for _ in range(N * N)]
index_list = []

for index in range(N * N):
    if index % N == 0 or index % N == N - 1 or index < N or index >= N * (N - 1):
        continue

    index_list.append(index)  # 씨앗 심기가 가능한 번호를 리스트로 저장

    price_list[index] = (
        seed_list[index - 1]
        + seed_list[index]
        + seed_list[index + 1]
        + seed_list[index - N]
        + seed_list[index + N]
    )

point_list = list(combinations(index_list, 3))

min_price = 200 * 5 * 3

for point in point_list:
    if (
        is_possible_to_plant(point[0], point[1])
        and is_possible_to_plant(point[0], point[2])
        and is_possible_to_plant(point[1], point[2])
    ) == False:
        continue

    min_price = min(min_price, get_seed_price(point))

print(min_price)