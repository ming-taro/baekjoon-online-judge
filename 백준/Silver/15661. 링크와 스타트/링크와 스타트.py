import sys
from itertools import combinations


def get_other_team_list(team_list):
    other_team_list = []
    number_of_team = len(team_list)
    team_index = 0

    for index in range(N):
        if team_index < number_of_team and index == team_list[team_index]:
            team_index += 1
        else:
            other_team_list.append(index)

    return other_team_list


def calculate_team_status(team_list):
    number_of_people = len(team_list)
    status = 0

    for i in range(number_of_people - 1):
        for j in range(i + 1, number_of_people):
            status += S[team_list[i]][team_list[j]] + S[team_list[j]][team_list[i]]

    return status


def get_diffenrence(team_list, other_team_list):
    team_status = calculate_team_status(team_list)
    other_team_status = calculate_team_status(other_team_list)

    return abs(team_status - other_team_status)


def calculate_difference_of_team_status(team_list):
    other_team_list = get_other_team_list(team_list)

    return min(
        get_diffenrence(team_list, other_team_list),
        get_diffenrence(other_team_list, team_list),
    )


N = int(sys.stdin.readline().rstrip())
S = []

for _ in range(N):
    S.append(list(map(int, sys.stdin.readline().rstrip().split())))

member_index = [i for i in range(N)]
min_status_difference = 19 * 100

for i in range(1, N // 2 + 1):
    team_start_list = list(combinations(member_index, N - i))

    for team_start in team_start_list:
        min_status_difference = min(
            min_status_difference, calculate_difference_of_team_status(team_start)
        )

print(min_status_difference)
