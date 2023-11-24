def solution(board, moves):
    count = 0

    length = len(board[0])
    last_index = []  #순서대로 1번, 2번, ... 열에 대해 가장 위에 있는 row의 위치(row == length인 경우 인형을 모두 뽑은 경우)
    
    for col in range(length):
        for row in range(length):
            if board[row][col] != 0:
                last_index.append(row)  #가장 높은 row의 위치를 유지
                break

    dolls = []  #인형을 담을 stack 바구니
    
    for move in moves:
        if last_index[move - 1] == length: #인형이 더 이상 없음
            continue
            
        row = last_index[move - 1]
        dolls.append(board[row][move - 1])
        last_index[move - 1] += 1    #인형을 하나 뽑음 -> row위치 아래로 한 칸 내림

        if len(dolls) > 1 and dolls[-1] == dolls[-2]:
            dolls.pop()   #바구니에 같은 인형 2개를 쌓으면 터트림
            dolls.pop()
            count += 2
    
    return count

#N * N크기의 정사각 격자
#사용자는 가장 위에 있는 인형을 집어 올릴 수 있음
#집어올린 인형은 바구니에 차곡차곡 쌓임(stack)
#바구니에 같은 모양의 인형 두 개를 쌓으면 터트림(pop)

#입력: 인형이 담겨진 배열 board, 크레인을 작동시킨 위치 배열 moves
#return 크레인을 모두 작동시킨 후 터트려져 사라진 인형 개수