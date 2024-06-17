import math

def solution(arrayA, arrayB):
    answer = 0
    # gcd 초기화
    gcd_a, gcd_b = arrayA[0], arrayB[0]
    for i in range(1, len(arrayA)) :
        gcd_a = math.gcd(gcd_a, arrayA[i])
        gcd_b = math.gcd(gcd_b, arrayB[i])
    
    for i in range(len(arrayA)) :
        if arrayA[i] % gcd_b == 0:
            gcd_b = 0
            break
    for i in range(len(arrayB)) :
        if arrayB[i] % gcd_a == 0:
            gcd_a = 0
            break
            
    return max(gcd_a, gcd_b)