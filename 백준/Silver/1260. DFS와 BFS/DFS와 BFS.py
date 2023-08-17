from collections import deque

import sys
input=sys.stdin.readline

n,m,v=map(int,input().split())
graph=[[] for _ in range(n+1)]
graph[0]=[0,0]
visited=[False for _ in range(n+1)]

for _ in range(m):
    start,end=map(int,input().split())
    graph[start].append(end)
    graph[end].append(start)
    graph[start].sort()
    graph[end].sort()
    # print(graph)

def DFS(graph,start,visited):
    visited[start]=True
    print(start, end=" ")
    for i in graph[start]:
        if not visited[i]:
            DFS(graph,i,visited)

def BFS(graph,start,visited):
    visited[start]=True
    queue=deque([start])

    while queue:
        v=queue.popleft()
        print(v,end=" ")
        for i in graph[v]:
            if not visited[i]:
                queue.append(i)
                visited[i]=True


DFS(graph,v,visited)
# BFS 탐색을 위한 visited 초기화
visited=[False for _ in range(n+1)]
print()
BFS(graph,v,visited)
