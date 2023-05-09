def is_within_range(today, period, term):
    today_year, today_month, today_day = map(int, today.split('.'))
    
    year, month, day = map(int, term.split('.'))
    month += period
    day -= 1
    
    if day <= 0:
        day += 28
        month -= 1
    
    if month <= 0:
        month += 12
        year -= 1
    
    while day > 28:
        month += 1
        day -= 28
    
    while month > 12:
        year += 1
        month -= 12
    
    if (today_year*10000 + today_month*100 + today_day > 
        year*10000 + month*100 + day):
        return True
    
    return False
    

def solution(today, terms, privacies):
    answer = []
    privacy_dic = {}
    
    for term in terms:
        privacy, period = term.split()
        privacy_dic[privacy] = int(period)
    
    number = 1
    for item in privacies:
        term, privacy = item.split()
        period = privacy_dic[privacy]
        
        if is_within_range(today, period, term):
            answer.append(number)
        number += 1
            
        
    return answer