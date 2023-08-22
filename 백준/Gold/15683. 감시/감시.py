# 15683 감시
import sys
import copy
from collections import deque
"""
CCTV는 회전 시킬 수 있는데, 회전은 항상 90도 방향
감시 하려고 하는 방향이 가로 또는 세로
0 : 빈칸
6 : 벽 : CCTV는 벽을 통과할 수 없다.
1 ~ 5 : CCTV의 번호
CCTV의 방향을 적절히 정해서, 사각 지대의 최소 크기 구하라 ! -> CCTV가 적게 있는 경우
"""
input = sys.stdin.readline
n, m = map(int, input().split())
array = [list(map(int, input().split())) for _ in range(n)]


# 상, 하, 좌, 우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

num_list = {
	1: [[0], [1], [2], [3]],
	2 : [[0,1], [2,3]],
	3 : [[0,3], [3,1], [1, 2], [2, 0]],
	4 : [[2, 0, 3], [0, 3, 1], [2, 1, 3], [0, 2, 1]],
	5 : [[0,1,2,3]]
}


# CCTV 위치 찾기
cctv = []
for x in range(n) :
	for y in range(m) :
		if 1 <= array[x][y] <= 5 :
			cctv.append([array[x][y], x, y])

def fill(x, y, cctv_direction, array) :
	for direction in cctv_direction :
		nx = x
		ny = y
		while True :
			nx += dx[direction]
			ny += dy[direction]
			if not (0<=nx<n and 0<=ny<m) :
				break
			if array[nx][ny] == 6 :
				break
			if array[nx][ny] == 0:
				array[nx][ny] = '#'



def dfs(depth, array) :
	global min_value

	if depth == len(cctv) :
		count = 0
		# array의 0이 있는 것 세기
		for i in range(n) :
			count += array[i].count(0)
		min_value = min(min_value, count)
		return
	array_copy = copy.deepcopy(array)
	num, x, y = cctv[depth]
	for i in num_list[num] :
		fill(x, y, i, array_copy)
		dfs(depth + 1, array_copy)
		array_copy = copy.deepcopy(array)


min_value = int(1e9)
dfs(0, array)
print(min_value)

