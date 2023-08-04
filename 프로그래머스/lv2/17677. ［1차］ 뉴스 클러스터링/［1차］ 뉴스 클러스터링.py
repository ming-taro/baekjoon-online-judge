from collections import Counter

def solution(str1, str2):
    answer = 0
    
    str1_list = []
    str2_list = []
    
    #"AA" <= 결과값 <= "ZZ"으로 연산하는 경우 "A_"의 경우에도 맞다고 처리하게 됨. 따라서 각 자리별로 알파벳인지를 검사하는 코드로 작성해야함
    for index in range(len(str1) - 1):
        result = (str1[index].upper() + str1[index + 1].upper())
        if (result[0] >= 'A' and result[0] <= 'Z'
           and result[1] >= 'A' and result[1] <= 'Z'):
            str1_list.append(result)
    
    for index in range(len(str2) - 1):
        result = (str2[index].upper() + str2[index + 1].upper())
        if (result[0] >= 'A' and result[0] <= 'Z'
           and result[1] >= 'A' and result[1] <= 'Z'):
            str2_list.append(result)

    union_count = len(str1_list) + len(str2_list)
    
    str1_list = Counter(str1_list)    
    str2_list = Counter(str2_list)
    
    intersection_count = 0
    
    for key, value in str1_list.items():
        if key in str2_list:
            intersection_count += min(str1_list[key], str2_list[key])

    union_count -= intersection_count
            
    if union_count == 0:
        return 65536

    return ((intersection_count/union_count)*65536)//1


#자카드 유사도: J(A, B) = 두 집합 A, B의 교집합 크기/합집합 크기
#집합 A, B가 모두 공집합일 경우 J(A, B) = 1
#입력: ->두 글자씩 끊어서 다중집합의 원소로 만듦
#     ->공백이나 숫자, 특수 문자의 경우 글자쌍을 버림(ex "ab+" = {ab}, "b+"는 버림)
#     ->대문자와 소문자 차이는 무시
#출력: int(자카드 유사도*65536)