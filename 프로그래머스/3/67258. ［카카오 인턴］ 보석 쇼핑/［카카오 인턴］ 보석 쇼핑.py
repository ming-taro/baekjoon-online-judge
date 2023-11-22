from collections import defaultdict

def solution(gems):
    answer = [0, len(gems) - 1]
    gem_dict = defaultdict(int)
    gem_number = len(set(gems))
    # print("총 보석 종류:", gem_number)
    
    start = 0
    end = -1
    count = 0
    
    while True:
        end += 1
        
        while True:
            gem = gems[end]         #마지막에 구매할 보석

            if gem_dict[gem] == 0:  #보석을 처음 구매하면 카운트
                count += 1

            gem_dict[gem] += 1      #보석구매

            if count == gem_number or end == len(gems) - 1 or start == len(gems) - 1: #보석을 다 구매함 or (두 번째 루프부터)진열장 마지막에 있는 보석을 구매했지만 종류가 부족한 경우
                # print("여기서 걸린다고..?")
                break
            end += 1  #다음에 구매할 보석 위치

        # print("현재 보석 종류:", count)
        # print("구매 결과:", start, end, "/ 정답:", answer)
        
        if (start == len(gems) - 1 or end == len(gems) - 1) and count < gem_number:
            # if (end - start) < (answer[1] - answer[0]):
                # answer = [start, end]
            # print("보석구매를 종료합니다..")
            break  #보석을 더이상 구매할 수 없음

        while start < len(gems) and gem_dict[gems[start]] > 1:
            gem = gems[start]     #보석 환불
            gem_dict[gem] -= 1
            start += 1

        if (end - start) < (answer[1] - answer[0]):  #전에 구매한 보석진열 길이와 현재 구매한 보석진열 길이 비교
            answer = [start, end]
        # print("환불 결과:", start, end, "/ 정답:", answer)
        # print()
                
        if end == len(gems) - 1:  #마지막 순서의 보석까지 모두 구매한 경우 종료
            # print("보석을 모두 구매했는데도..")
            break
        
        if gem_dict[gems[start]] == 1:  #start~end사이의 보석 중 start + 1부터 다시 검사하려함 -> 만약 start+1 ~ end사이의 보석중 start의 보석이 없다면 종류개수를 조정해준다
            count -= 1
            gem_dict[gems[start]] -= 1
            # print("start의 보석이 없어서 count를 조정합니다 -> count:", count)
            # print(gem_dict)
            # print()
        start += 1   #다음
        # print("다시 시작:", start, end)
        # print(gem_dict)

    answer = [answer[0] + 1, answer[1] + 1]
    
    return answer