# 14500 - 테크노미로

"""
1. 정사각형 4개를 이어 붙인 것 -> 테트로미노 (5가지 케이스)
2. 테트로미노 하나를 적절히 놓아서 테트로미노가 놓인 칸에 쓰여 있는 수들의 합을 최대로하는 프로그램
"""

n, m = map(int, input().split())
array = [list(map(int, input().split())) for _ in range(n)]
visited = [[False] * m for _ in range(n)]

#	상, 하, 좌, 우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

total_result = 0
# ㅗ, ㅜ, ㅓ, ㅏ 제외한 모양들 최대값 계산
def dfs(x, y, step, total) :
	global total_result
	# 종료 조건 1) 탐색을 계속 진행해도, 최대값에 못미칠 경우
	if total + max_val * (4-step) <= total_result :
		return
	# 종료 조건 2) 블록 4개를 모두 활용한 경우
	if step == 4:
		total_result = max(total_result, total)
		return

	for i in range(4) :
		nx = x + dx[i]
		ny = y + dy[i]
		# 새로운 좌표가 유효한 범위 내에 있고, 탐색ㄱ이력이 없는 경우
		if (0 <= nx < n) and (0 <= ny < m) and not visited[nx][ny] :
			# 2번째 블록 연결 후 "ㅏ,ㅓ,ㅗ,ㅜ" 만들기
			if step == 2 :
				visited[nx][ny] = True
				# 새로운 좌표에서 탐색하지 말고, 기존 좌표로 돌아와 탐색 재개
				dfs(x, y, step+1, total + array[nx][ny])
				visited[nx][ny] = False

			visited[nx][ny] = True
			dfs(nx, ny, step+1, total + array[nx][ny])
			visited[nx][ny] = False

max_val = max(map(max, array)) # 모든 좌표 중 최대값
for i in range(n) :
	for j in range(m) :
		visited[i][j] = True # 탐색 기록
		dfs(i, j, 1, array[i][j])
		visited[i][j] = False # 탐색 기록 제거


print(total_result)