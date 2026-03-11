# BOJ 1103 - 게임

## 📌 문제 설명

일단 보드의 가장 왼쪽 위에 동전을 하나 올려놓는다. 그다음에 다음과 같이 동전을 움직인다.

1. 동전이 있는 곳에 쓰여 있는 숫자 X를 본다.
2. 위, 아래, 왼쪽, 오른쪽 방향 중에 한가지를 고른다.
3. 동전을 위에서 고른 방향으로 X만큼 움직인다. 이때, 중간에 있는 구멍은 무시한다.

최대 동전을 움직일 수 있는 횟수를 구하시오

## 💡 해결 아이디어

1. bfs로 풀고 visited를 방문할 경우 cyclic이니까 -1을 출력하면 되겠다.
2. bfs로 푸니까 다른 경로에서 같은 위치에 왔을 때 cyclic이 아닌데도 cyclic이라 판단하네? 백트래킹으로 바꿔야겠다.
3. 백트래킹으로 하니까 worst case에서 시간초과가 나네? dp를 추가해서 시간을 줄여야겠다.

## 🧠 코드 해설

```java
if (visited[nx][ny]) {
				dp[0][0] = -1;
				inf = true;
				return -1;
			}
```

이미 방문한 경우 cyclic이기 때문에 output을 -1로 설정 후 return;

```java
		visited[nx][ny] = true;
		maxLength = Math.max(maxLength, dfs(nx, ny, count+1) - count);
		if (inf) return -1;
		visited[nx][ny] = false;
```

백트래킹 방식으로 처리  
maxLength는 이제 내가 추가로 더 갈 수 있는 거리 중 최댓값  
dfs의 return값에서 내가 이미 간 거리를 빼면 추가로 갈 수 있는 거리를 확인할 수 있다.

```java
		dp[x][y] = maxLength;
		return count + maxLength;
```

dp값은 x, y 좌표에서 추가로 갈 수 있는 거리 중 최대 거리  
return값은 이미 간 거리(count) + 추가로 갈 수 있는 거리 중 최대 거리(dp)

## 🚀 느낀점

쉬운 문제인줄 알았는데 자꾸 뭐 하나씩 안되는 부분이 생겨서 생각보다 훨씬 어려운 문제였다.  
bfs가 안되는 이유를 알려준 정원영, 시간초과 문제 해결을 도와준 김선규에게 감사를..
