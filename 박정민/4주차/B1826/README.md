# BOJ 1826 - 연료 채우기

## 📌 문제 설명

연료가 모자라면 주유소에 들려서 연료를 채워가면서 목적지에 도달했을 때 주유소에 들르는 최소 횟수를 구하는 문제

## 💡 해결 아이디어

1. 현재 보유한 연료의 양 = 트럭이 갈 수 있는 거리
2. 주유소를 최소한으로 들러야 하기에 이왕 들를때 연료가 제일 많은 곳을 들리는게 이상적
3. 현재 갈 수 있는 주유소 중에서 연료가 제일 많은 곳을 찾기 => greedy
4. 트럭이 갈 수 있는 거리에 있는 주유소들을 다 PriorityQueue에 저장하고 기름이 다 떨어졌을 때마다 제일 연료가 많은 주유소를 들리는 방식으로 풀면 되겠구나.

## 🧠 코드 해설

```java
		int[] oil = new int[1000001];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			oil[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
```

oil[i] = j => i: 주유소의 위치, j: 주유소에서 보유한 연료

```java
PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
```

지나간 거리에 위치한 주유소들의 연료양을 기록, 최댓값을 뽑아야 하기에 reverseOrder

```java
		// left
		for (int i = 0; i <= p; i++) {
			if (i == l) break;

			if (oil[i] != 0) q.add(oil[i]);

			if (i == p) {
				if (q.isEmpty()) {
					System.out.println(-1);
					return;
				}
				else {
					p += q.poll();
					count++;
				}
			}
		}
```

목적지에 도달하면 탈출  
지나가는 길에 주유소가 있을 경우 연료의 양을 기록  
더 이상 갈 수 없을 경우 지나갔던 주유소 중에 가장 연료가 많은 주유소의 연료만큼 연료 충전  
만약 더 이상 연료를 채울 주유소가 없을 경우 return

## 🚀 느낀점

greedy 원리를 빠르게 떠올려서 쉽게 푼 문제였다.
