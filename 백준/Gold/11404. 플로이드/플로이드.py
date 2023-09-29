# 11404 플로이드

n = int(input())
m = int(input())
INF = int(1e9)
array = list([INF] * (n+1) for _ in range(n+1))

for _ in range(m):
	a, b, c = map(int, input().split())
	array[a][b] = min(c, array[a][b])


for k in range(1, n+1):
	for a in range(1, n+1):
		for b in range(1, n+1):
			# 시작도시와 도착도시가 같은 경우는 없다.
			if a == b:
				array[a][b] = 0
			else:
				array[a][b] = min(array[a][b] , array[a][k] + array[k][b])


for a in range(1, n+1) :
	for b in range(1, n+1):
		if array[a][b] == INF:
			print(0, end = " ")
		else:
			print(array[a][b] , end = " ")
	print()