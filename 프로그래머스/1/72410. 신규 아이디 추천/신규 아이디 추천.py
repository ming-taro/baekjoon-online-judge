import re

def solution(new_id):
    answer = ''
    
    new_id = new_id.lower()  #1단계    
    new_id = re.sub('[^a-z0-9-_.]', '', new_id)  #2단계
    
    new_id = re.sub('[.]{2,}', '.', new_id)    #3단계
    
    new_id = re.sub('^[.]+', '', new_id)  #4단계
    new_id = re.sub('[.]+$', '', new_id)
    
    if new_id == '':  #5단계
        new_id = 'a'
    
    if len(new_id) >= 16:  #6단계
        new_id = new_id[:15]
    if new_id != '' and new_id[-1] == '.':  
        new_id = new_id[:-1]
    
    if len(new_id) <= 2:   
        new_id += new_id[-1]*(3 - len(new_id))  #7단계
    
    return new_id