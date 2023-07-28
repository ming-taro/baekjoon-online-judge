def solution(id_list, report, k):
    id_length = len(id_list)
    
    result = [0 for _ in range(id_length)]
    id_dict = {}
    report_list = [[False for _ in range(id_length)] for _ in range(id_length)]
    
    for index in range(id_length):
        id_dict[id_list[index]] = index
    
    for r in report:
        reporter, reported_person = r.split(" ")
        reporter_index = id_dict[reporter]
        reported_person_index = id_dict[reported_person]
        
        report_list[reported_person_index][reporter_index] = True
    
    for reported_person in range(id_length):
        #해당 유저가 받은 신고 횟수가 k이상인 경우만 각 신고자에게 신고 메일을 보냄
        if report_list[reported_person].count(True) < k:
            continue

        for report_person in range(id_length):
            if report_list[reported_person][report_person]:
                result[report_person] += 1
    
    return result


#게시판 불량 이용자를 신고하고 처리 결과를 메일로 발송하는 시스템
#1. 각 유저는 한 번에 한 명의 유저만 신고 가능
#2. 한 유저를 여러 번 신고 가능, but 동일 유저에 대한 신고 횟수는 1회로 처리됨
#3. k번 이상 신고된 유저는 게시판 이용이 정지됨
#   (해당 유저를 신고한 모든 유저에게 정지 사실을 메일로 발송함)
#   (유저가 신고한 모든 내용을 취합하여 마지막에 한꺼번에 게시판 이용 정지 + 메일 발송)
#+ 자기 자신을 신고하는 경우는X

#id_list: 유저 목록 / report: "이용자id 신고한id" / k: k번이상 신고당한 유저는 게시판 이용 정지
#return [각 유저가 받은 결과 메일 수]