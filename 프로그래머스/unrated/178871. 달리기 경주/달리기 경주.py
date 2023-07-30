def solution(players, callings):
    player_rank = {}
    
    for rank in range(len(players)):
        player_rank[players[rank]] = rank
    
    for call in callings:
        winner_rank = player_rank[call]       #현재 이름이 불린 선수의 순위
        
        loser_name = players[winner_rank - 1] #1. 두 선수의 dict rank값 업데이트 
        player_rank[call] -= 1          
        player_rank[loser_name] += 1
        
        players[winner_rank] = players[winner_rank - 1] #2. players에서 두 선수의 이름 바꾸기
        players[winner_rank - 1] = call
        
    return players

#선수가 자기 앞의 선수를 추월할 때, 추월한 선수의 이름을 부름
#ex) 1등 mumu, 2등 soe, 3등 poe -> "soe"선수를 불렀다면 mumu를 추월해 1등을 차지했다는 의미
#players: 현재 선수들의 등수 / callings: 해설진이 부른 이름
#1등인 선수의 이름은 불리지X

#풀이)
#1. 이름이 불린 선수와 앞선 선수의 dict 값을 변경한다
#2. players에서 이름이 불린 선수와 앞선 선수의 이름을 변경한다(인덱스값이 등수)