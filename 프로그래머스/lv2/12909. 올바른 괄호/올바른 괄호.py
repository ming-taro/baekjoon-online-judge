def solution(s):
    answerList = []
    
    for i in s:
        if i == '(':
            answerList.append(i)
        else :   #')'
            if len(answerList) > 0:
                answerList.pop()
            else:
                return False    
    
    if len(answerList) > 0:
        return False
    
    return True