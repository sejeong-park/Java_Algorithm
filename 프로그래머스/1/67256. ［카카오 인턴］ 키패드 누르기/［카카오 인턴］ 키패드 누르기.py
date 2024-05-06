def solution(numbers, hand):
    answer = ''
    keypad={
        1:[0,0],2:[0,1],3:[0,2],
        4:[1,0],5:[1,1],6:[1,2],
        7:[2,0],8:[2,1],9:[2,2],
        '*':[3,0],0:[3,1],'#':[3,2]
    }
    def distance(left,right,num):
        # 왼쪽 거리
        left_x,left_y = abs(keypad[num][1]-keypad[left][1]) , abs(keypad[num][0]-keypad[left][0])
        left_result = left_x+left_y
        
        # 오른쪽 거리
        right_x,right_y = abs(keypad[num][1]-keypad[right][1]), abs(keypad[num][0]-keypad[right][0])
        right_result = right_x+right_y
        
        return left_result, right_result
        
        

    last_L = '*'
    last_R ='#' 
    
    for num in numbers:
        if num in [1,4,7]:# 왼쪽
            answer+='L'
            last_L = num
        elif num in [3,6,9]: 
            answer+='R'
            last_R = num
        else :
            left_dis, right_dis = distance(last_L,last_R,num)
            if left_dis>right_dis:
                answer+='R'
                last_R=num
            elif left_dis<right_dis:
                answer+='L'
                last_L = num
            elif left_dis == right_dis:
                if hand=='right':
                    answer+='R'
                    last_R = num
                elif hand=='left':
                    answer+='L'
                    last_L = num
                
        
    
    
        
    return answer