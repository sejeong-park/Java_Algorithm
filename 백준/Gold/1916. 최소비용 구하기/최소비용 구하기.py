# 1916 최소 비용 구하기

import heapq
import sys

input = sys.stdin.readline

n = int(input())
m = int(input())
INF = int(1e9)
graph = [[] for _ in range(n+1)]
distance = [INF] * (n+1)

for _ in range(m):
	a, b, c = map(int, input().split())
	graph[a].append((b, c)) 	# b, cost
start, end = map(int, input().split())

def dijkstra(start):
	# start 노드 초기화
	queue = []
	heapq.heappush(queue, (0, start))
	distance[start] = 0

	while queue:
		dist, now = heapq.heappop(queue)

		if distance[now] < dist :
			continue

		for node, node_cost in graph[now]:
			cost = dist + node_cost

			if cost < distance[node]:
				distance[node] = cost
				heapq.heappush(queue, (cost, node))

dijkstra(start)

print(distance[end])