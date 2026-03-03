## **🔍 문제 요약**

- 직사각형 보드 → 1~9 숫자와 구멍
- 동전을 올린 후 다음과 같이 움직임
    1. 동전이 있는곳의 숫자 X를 보고
    2. 상하좌우 방향을 고른 후
    3. 그 방향으로 X 만큼 이동 (중간 구멍 무시)
- 구멍에 빠지거나 바깥으로 나갈 시 게임 종료
- 최대 몇번 움직일 수 있는가 (무한 -1)
- 1 ≤ N, M ≤ 50

---

## **💡문제 접근 / 풀이 전략**

- 빠지거나 종료되는 경우에도 카운팅 → 마지막에 +1
- visit 배열으로 무한 반복 체크
- move 배열로 방문 횟수 체크
- **기존 방식 (Top down) 시간 초과**
    - dfs(depth, ….) 로 자식들에게 전달
    - 각 칸마다 move 비교해야함
    - 최악의 경우 모든 경로 다 비교
- **현재 방식 (Bottom up)**
    - 해당 칸에서 앞으로 최대 몇개 갈 수 있는지 계산
    - 각 칸을 한번만 탐색

---

## **✅ 코드 & 소요 시간**

```java
package boj1103;

import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static char [][] board;
	static int [][] move;
	static boolean [][] visited;
	static int [] mr = {-1, 1, 0, 0};
	static int [] mc = {0, 0, -1, 1};

	public static void main(String [] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new char [R][C];
		for(int i = 0; i < R; i++) {
			board[i] = br.readLine().toCharArray();
		}

		move = new int [R][C];
		visited = new boolean[R][C];

		System.out.println(dfs(0, 0));
	}

	public static int dfs(int cr, int cc) {
		if(cr < 0 || cc < 0 || cr >= R || cc >= C || board[cr][cc] == 'H')	return 0;

		if(visited[cr][cc]) {	// 사이클 발생
			System.out.println(-1);
			System.exit(0);
		}

		if(move[cr][cc] > 0) return move[cr][cc];

		visited[cr][cc] = true;
		int maxForThisNode = 0;
		int cnt = board[cr][cc] - '0';
		for(int i = 0; i < 4; i++) {
			int nr = cr + cnt * mr[i];
			int nc = cc + cnt * mc[i];

			// 다음 노드에서의 최대 이동 횟수 + 1 (현재 노드)
			maxForThisNode = Math.max(maxForThisNode, dfs(nr, nc) + 1);
		}
		visited[cr][cc] = false;

		return move[cr][cc] = maxForThisNode;
	}

}

```

17144KB / 140 ms

---

## **✍️ 회고**

- Bottom up 방식

---

## **🧩 다른 풀이 (선택)**

```java

```

---
