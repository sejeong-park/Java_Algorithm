from collections import deque
def solution(order):
    answer = 0
    
    sub = []
    order = deque(order)
    for i in range(1, len(order) + 1) :
        sub.append(i)   # sub에 추가한다. // sub는 역순
        
        # 택배
        while sub :
            if sub[-1] == order[0] :
                order.popleft() # order은 0 index를 빼준다.
                sub.pop()       # sub은 마지막에서 빼주고
                answer += 1
            else:
                break
            
    return answer