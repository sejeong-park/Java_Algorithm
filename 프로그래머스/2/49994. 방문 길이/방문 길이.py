"""
좌표 경겨가 왼쪽 위, 왼쪽 아래, 오른쪽 위, 오른쪽 아래 4가지 포인트에서 5까지만 있다.
캐릭터가 "처음 걸어본 길의 길이!"
(x, y) -> (nx, ny)
(nx, ny) -> (x, y)
두 케이스는 같은 간선이다.
"""
def solution(dirs):
    answer = 0
    dirs = dirs.replace('U', '0').replace('D','1').replace('R','2').replace('L', '3')

    dx = [-1, 1 ,0, 0]
    dy = [0, 0, 1, -1]

    x, y = 0, 0
    visited = set() # 포인트에서 방문 여부를 확인하는 것이 아니라, 현재 지점에서 어디간 면적을 카운트해야한다.
    for d in dirs :
        nx = x + dx[int(d)]
        ny = y + dy[int(d)]
        # 격자 내에서만 
        if -5<=nx<=5 and -5<=ny<=5 :
            visited.add(((x, y), (nx, ny))) # 원래포인트 -> 이동포인트
            visited.add(((nx, ny),(x, y)))      
            x, y = nx, ny
    # print(len(visited) , "::", visited)
    return len(visited)//2