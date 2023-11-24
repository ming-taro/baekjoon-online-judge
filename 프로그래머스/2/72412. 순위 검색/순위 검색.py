language = ["cpp", "java", "python"]
part = ["backend", "frontend"]
career = ["junior", "senior"]
food = ["chicken", "pizza"]

def solution(info, query):
    answer = []
    
    query_info = []
    user_info = []
    
    for q in query:
        tmp = q.split(' and ')
        f, s = tmp.pop().split(" ")
        tmp.append(f)       #음식
        tmp.append(int(s))  #코테 점수
        query_info.append(tmp)
    
    for u in info:
        user_info.append(u.split(" "))
        
    user_score = {}
    
    for i in language:      #조건에서 만들 수 있는 24가지의 조건에 대한 dict
        user_score[i] = {}
        for j in part:
            user_score[i][j] = {}
            for k in career:
                user_score[i][j][k] = {}
                for l in food:
                    user_score[i][j][k][l] = []
    
    for user in user_info:
        l, p, c, f, s = user   #언어, 직군, 경력, 음식. 점수
        user_score[l][p][c][f].append(int(s))  #위에서 만든 dict에서 해당하는 곳에 유저의 점수를 저장 -> 쿼리로 검색하면 해당 유저가 있는지, 해당 쿼리에 해당하는 유저들의 점수분포 리스트를 알 수 있음

    for i in language:    
        for j in part:
            for k in career:
                for l in food:
                    user_score[i][j][k][l].sort()  #각 쿼리에 있는 점수리스트를 오름차순 정렬(이진탐색을 위해)
                    
    for query in query_info:
        l, p, c, f, s = query
        answer.append(find_query(user_score, l, p, c, f, s))
        
    return answer
    
def find_query(user_score, l, p, c, f, s):
    count = 0
    
    if l == '-':     #모든 언어에 대해 검사
        for value in language:
            count += find_query(user_score, value, p, c, f, s)
    elif p == '-':   #모든 직군에 대해 검사
        for value in part:
            count += find_query(user_score, l, value, c, f, s)
    elif c == '-':   #모든 경력에 대해 검사
        for value in career:
            count += find_query(user_score, l, p, value, f, s)
    elif f == '-':   #모든 음식에 대해 검사
        for value in food:
            count += find_query(user_score, l, p, c, value, s)
    else:
        end = len(user_score[l][p][c][f])
        start = b_search(s, user_score[l][p][c][f])
        count += end - start
    
    return count
        
def b_search(target, score_board):
    start = 0
    end = len(score_board) - 1

    while start <= end:
        mid = (start + end) // 2
        if score_board[mid] < target:  #유저가 쿼리의 기준점수보다 낮은 경우 start 조정
            start = mid + 1
        else:   
            end = mid - 1
            
    return start