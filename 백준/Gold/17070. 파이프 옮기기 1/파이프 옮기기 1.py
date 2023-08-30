# 17070 파이프 옮기기

n = int(input())
array = [list(map(int, input().split())) for _ in range(n)]
cnt = 0
# dfs
def dfs(x, y, d):
	global cnt
	if x == n-1 and y == n-1:
		cnt += 1
		return

	# 가로일 경우
	if d == 0 :
		if 0<=y+1<n :
			if array[x][y+1] == 0:
				dfs(x, y+1, 0)
		if  0<=x+1<n and 0<=y+1<n :
			if (array[x][y+1], array[x+1][y], array[x+1][y+1]) == (0,0,0) :
				dfs(x+1, y+1, 2)
	# 세로일 경우
	elif d == 1 :
		if 0<=x+1<n :
			if array[x+1][y] == 0 :
				dfs(x+1, y, 1)
		if 0 <= x + 1 < n and 0 <= y + 1 < n:
			if (array[x][y+1], array[x+1][y], array[x+1][y+1]) == (0,0,0) :
				dfs(x+1, y+1, 2)
	# 대각선인 경우
	elif d == 2:
		if 0 <= y+1 < n:
			if array[x][y+1] == 0 :
				dfs(x, y+1, 0)
		if 0<=x+1<n :
			if array[x+1][y] == 0 :
				dfs(x+1, y, 1)
		if 0<=x+1<n and 0<=y+1<n:
			if (array[x][y+1], array[x+1][y], array[x+1][y+1]) == (0,0,0) :
				dfs(x+1, y+1, 2)



# 시작
dfs(0, 1, 0)
print(cnt)
