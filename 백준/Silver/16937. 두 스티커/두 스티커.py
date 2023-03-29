import sys

H, W = map(int, sys.stdin.readline().rstrip().split())

N = int(sys.stdin.readline().rstrip())
sticker = []

for _ in range(N):
    R, C = map(int, sys.stdin.readline().rstrip().split())
    sticker.append([R, C])

sticker.sort(key=lambda x: x[0] * x[1], reverse=True)


def check_is_included_in_scope(sticker_1, sticker_2, heigth_1, height_2):
    if (
        sticker_1[heigth_1] <= H
        and sticker_2[height_2] <= H
        and sticker_1[1 - heigth_1] + sticker_2[1 - height_2] <= W
        or sticker_1[heigth_1] <= W
        and sticker_2[height_2] <= W
        and sticker_1[1 - heigth_1] + sticker_2[1 - height_2] <= H
    ):
        return True
    return False


area = 0

for i in range(N - 1):
    for j in range(i + 1, N):
        if (
            check_is_included_in_scope(sticker[i], sticker[j], 0, 0)
            or check_is_included_in_scope(sticker[i], sticker[j], 0, 1)
            or check_is_included_in_scope(sticker[i], sticker[j], 1, 0)
            or check_is_included_in_scope(sticker[i], sticker[j], 1, 1)
        ):
            area = max(
                area, sticker[i][0] * sticker[i][1] + sticker[j][0] * sticker[j][1]
            )

print(area)
