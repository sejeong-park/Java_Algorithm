# 3079 입국 심사
# 그리디

n, m = map(int, input().split())
array = []
for _ in range(n):
	array.append(int(input()))

def binary_seach(array, target):
	left , right = 0, len(array)
	while left <= right:
		mid = (left + right) // 2
		if array[mid] == target:
			print(mid + 1)
			break
		elif array[mid] > target:
			right = mid - 1
		else:
			left = mid + 1

array.sort()
start , end = 0, min(array)  * m # time의 최소값 * 사람수 = 최대 시간
result = 0

while start <= end :
	mid = (start + end) //2
	cnt = 0
	for time in array:
		cnt += mid // time

	if cnt >= m :
		end = mid - 1
		result = mid

	else:
		start = mid + 1

print(result)


