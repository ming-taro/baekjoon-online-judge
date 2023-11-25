class Cell:
    def __init__(self, val = 0, prev=None, next=None):
        self.val = val
        self.prev = prev
        self.next = next

def solution(n, k, cmd):
    answer = ['O' for _ in range(n)]
    cells = []
    trash = []  #stack -> 삭제된 노드 순서대로 저장
    
    for i in range(n):  #셀 연결리스트 생성
        new_cell = Cell(i)
        
        if i != 0:
            new_cell.prev = cells[-1]   #이전 생성 노드를 prev로 연결
            cells[-1].next = new_cell   #현재 노드를 이전 노드의 next로 연결
        cells.append(new_cell)
    
    current_cell = cells[k]  #초기세팅 -> k번째 행 선택
    
    for i in range(len(cmd)):
        input = cmd[i].split(' ')
        command = input[0]
        if command == "U" or command == "D":
            move = int(input[1])
        
        if command == "U": #위로 이동
            for count in range(move):
                if current_cell.prev == None:  #첫번째 노드에 도착하면 종료
                    break
                current_cell = current_cell.prev #이전 노드로 이동
        elif command == "D": #아래로 이동
            for count in range(move):
                if current_cell.next == None:  #마지막 노드에 도착하면 종료
                    break
                current_cell = current_cell.next #다음 노드로 이동
        elif command == "C":  #행 삭제
            prev_cell = current_cell.prev
            next_cell = current_cell.next
            
            trash.append(current_cell.val)  #삭제한 행 번호 저장
            answer[current_cell.val] = 'X'
            
            if prev_cell != None:  #첫 행이 아닌 경우 이전 노드 연결
                prev_cell.next = next_cell
            if next_cell != None:  #마지막 행이 아닌 경우 다음 노드 연결
                next_cell.prev = prev_cell
            
            if next_cell == None:  #삭제하려는 행이 마지막 행인 경우 이전 셀 선택
                current_cell = prev_cell
            else:
                current_cell = next_cell
        else:  #최근에 삭제한 행 복구
            last_cell = cells[trash.pop()]
            answer[last_cell.val] = 'O'

            prev_cell = last_cell.prev
            next_cell = last_cell.next
            
            if prev_cell != None:
                prev_cell.next = last_cell
            if next_cell != None:
                next_cell.prev = last_cell
            
        
    return "".join(answer)

#명령어 기반으로 행을 선택, 삭제, 복구하는 프로그램
#한 번에 한 행만 선택할 수 있음
#U X: X칸 위의 행을 선택
#D X: X칸 아래의 행을 선택
#C: 현재 행 삭제, 아래 행 선택(마지막 행일 경우 윗 행 선택)
#Z: 가장 최근 삭제된 행 복구(현재 선택된 행은 바뀌지X)