## **🔍 문제 요약**

- 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 8)
- 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치
    - 2의 개수는 2보다 크거나 같고, 10보다 작거나 같은 자연수
    - 빈 칸의 개수는 3개 이상
- 벽을 3개 세웠을 때 안전 영역의 최대 크기를 출력

---

## **💡문제 접근 / 풀이 전략**

- ~~2에서 시작?? → 아닌듯~~
- 의재님한테 받은 힌트
    - bfs + dfs
    - 결국 완전탐색을 해야함
  
1. DFS가 빈칸 3개 골라서 map에 벽 세움
2. depth==3 → bfs 실행
3. bfs:
    - map을 virusMap으로 복사
    - virusMap에서 바이러스를 퍼뜨림
4. countSafe()로 virusMap의 0 개수 세기
5. max 갱신
6. DFS로 돌아가 벽을 하나 지우고 다른 벽 위치 시도

---

## **✅ 코드 & 소요 시간**

```java
package boj14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static boolean[][] visited;
	static int[][] virusMap;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int max = 0;
	static int R, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		virusMap = new int[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt).toArray();
		}

		dfs(0, 0);
		System.out.println(max);
	}

	/**
	 * 벽 위치를 지정하는 함수
	 * @param depth
	 */
	static void dfs(int start, int depth) {
		if (depth == 3) {
			bfs();
			max = Math.max(max, countSafe());
			return;
		}

		for (int idx = start; idx < R * C; idx++) {
			int r = idx / C;
			int c = idx % C;

			if (map[r][c] == 0) {
				map[r][c] = 1;    // 벽세우기
				dfs(idx + 1, depth + 1);
				map[r][c] = 0;    // 백트래킹
			}
		}
	}

	/**
	 * 현재 지도를 바이러스 전파 이후의 상태로 만드는 함수
	 */
	static void bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		visited = new boolean[R][C];
		copyMap();

		// 바이러스 큐에 넣음
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (virusMap[i][j] == 2) {
					queue.offer(new Point(i, j));
					visited[i][j] = true;
				}
			}
		}

		while (!queue.isEmpty()) {
			Point top = queue.poll();

			for (int k = 0; k < 4; k++) {
				int nr = dr[k] + top.r;
				int nc = dc[k] + top.c;

				if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;

				if (virusMap[nr][nc] == 0 && !visited[nr][nc]) {
					virusMap[nr][nc] = 2;
					visited[nr][nc] = true;
					queue.offer(new Point(nr, nc));
				}
			}
		}

	}

	/**
	 * 현재 지도의 안전영역 개수를 세는 함수
	 * @return cnt
	 */
	static int countSafe() {
		int cnt = 0;
		for (int i = 0; i < R ; i++) {
			for (int j = 0; j < C; j++) {
				if (virusMap[i][j] == 0)	cnt++;
			}
		}
		return cnt;
	}

	/**
	 * 맵 복사
	 */
	static void copyMap() {
		for (int i = 0; i < R; i++) {
			virusMap[i] = map[i].clone();
		}
	}
}

class Point {
	int r;
	int c;

	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
```

98244KB / 388ms

---

## **✍️ 회고**

- 

---

## **🧩 다른 풀이 (선택)**

```java

```

---
