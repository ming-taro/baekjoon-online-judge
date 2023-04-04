import sys
from collections import Counter
from itertools import combinations

word = Counter(list(sys.stdin.readline().rstrip()))

N = int(sys.stdin.readline().rstrip())
book_list = []

for _ in range(N):
    price, title = map(str, sys.stdin.readline().rstrip().split())
    book_list.append([title, int(price)])

number_list = [i for i in range(N)]


def combine_title_list(index_list):
    title_list = []

    for index in index_list:
        title_list += book_list[index][0]

    return title_list


def is_word_cominations_possible(word, title_list):
    title_list = Counter(title_list)

    for w in word:
        if word[w] > title_list[w]:
            return False

    return True


def get_total_price(index_list):
    price = 0

    for index in index_list:
        price += book_list[index][1]

    return price


min_price = 1600001

for n in range(1, N + 1):
    index_list = list(combinations(number_list, n))

    for index in index_list:
        if is_word_cominations_possible(word, combine_title_list(index)):
            min_price = min(min_price, get_total_price(index))

if min_price == 1600001:
    print(-1)
else:
    print(min_price)
