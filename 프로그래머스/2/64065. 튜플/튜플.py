def solution(s):
    s_list = s[2:-2].split('},{')   #각 튜플별로 나눈 문자열 리스트
    s_list.sort(key=lambda x: len(x))
    
    result_set = set()      #set은 문자열로 관리
    result = [int(s_list[0])]        #첫 번째 원소
    
    result_set.add(s_list[0])

    for i in range(1, len(s_list)):
        numbers = set(s_list[i].split(','))
        for number in numbers:
            if number not in result_set:  #튜플 원소중에 없는 원소일 경우 추가
                result_set.add(number)
                result.append(int(number))
        
    return result

#n개의 요소를 가진 튜플
#튜플의 성질: 중복원소 가능 / 원소에 정해진 순서가 있으므로 순서가 다르면 다른 튜플 / 원소개수 유한

#중복원소가 없는 튜플이 주어짐