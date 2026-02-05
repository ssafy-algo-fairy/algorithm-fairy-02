## **🔍 문제 요약**

- 세로 R칸, 가로 C칸의 보드 ( 1 ≤ R, C ≤ 20)
- 좌측 상단 칸에 말은 상하좌우 이동 가능 → 동일한 알파벳이 적힌 칸을 두 번 지날 수 X
- 말이 최대 몇 칸을 지날 수 있는가

---

## **💡문제 접근 / 풀이 전략**

- BFS로 모든 경로 탐색시 ? → 중복을 어케거르지 → 경로별로 중복 세는 방법??
- DFS 사용? → 재귀로 풀되 돌아오면서 visited 해제?

---

## **✅ 코드 & 소요 시간**

```java
package boj1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int [] moveX = {-1,1,0,0};
	static int [] moveY = {0,0,-1,1};

	static char [][] board;
	static boolean [] visited;

	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		board = new char [r][c];
		visited = new boolean ['Z' + 1];

		for(int i = 0; i < r; i++) {
			board [i] = br.readLine().toCharArray();
		}

		visited[board[0][0]] = true;
		dfs(0,0,1);

		System.out.println(max);

	}

	static void dfs(int x, int y, int move) {
		max = Math.max(max, move);	// dfs 시작할 때 max 갱신

		for(int i = 0; i < 4; i++) {
			int tmpX = x + moveX[i];
			int tmpY = y + moveY[i];

			if(tmpX < 0 || tmpX >= board.length || tmpY < 0 || tmpY >= board[0].length) {
				continue;
			}

			if(!visited[board[tmpX][tmpY]]) {
				visited[board[tmpX][tmpY]] = true;
				dfs(x + moveX[i], y + moveY[i], move+1);
				visited[board[tmpX][tmpY]] = false;	// dfs가 끝나면 방문 여부 해제
			}
		}
	}
}

```

---

## **✍️ 회고**

- `move++` / `++move`쓰면 안되는 이유
    - 상하좌우 모두 탐색해야 하므로 move 자체의 값은 건들면 안됨
- `visited = new boolean ['Z'];` 로 선언해서 에러났었음 !!
    - Z에 해당하는 위치가 필요하므로 Z+1로
    - 애초에 26개로 초기화하고 `-’A’`로 해도 될듯

- 개인적으로 다들 반례같은거 어떻게 찾아보는지 궁금해요~! 저는 사실 백준 질문게시판을 뒤져서 반례 찾는 편인데.. 코테에서도 뒤져볼수는 없는거니까 ㅠ.ㅠ

---

## **🧩 다른 풀이 (선택)**

```java

```
