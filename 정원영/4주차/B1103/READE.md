## 이슈번호
REf: #137

## 📝 문제 요약

형택이는 보드의 가장 왼쪽 위에 동전을 하나 올려놓는다.

동전을 위,아래,오른쪽,왼쪽 네방향으로 움직이며 동전이 있는 곳에 쓰여있는 숫자 만큼 움직인다.

‘H’는 구멍이며 이동중에 있는 구멍은 무시되고, 구멍에 도착하면 게임은 종료된다. ( 보드 바깥으로 나가도 종료) 이 게임을 오래하고싶다면 최대 몇번을 움직일 수 있는지 구하라 ( 무한번 움직일수있다면 -1)

## 💡 접근 방법

- **알고리즘/자료구조: DFS, DP**
- **핵심 아이디어: 그냥 DFS로 돌면 시간이 너무 많이 걸려서, 메모이제이션을 함께 사용**

## ✅ 풀이 과정
- 딱 보니 사이클이 생기면 무한이고, 사이클이 안생기면 횟수가 있다.
- 사이클은 방문했던 곳을 또 방문하면 사이클이다.
- dfs로 돌면서 최대로 갈 수 있는 곳을 찾는다.
- 무작정 찾으면 시간초과가 나기때문에 메모이제이션을 해준다.
- x, y에서 최대 횟수를 구하고 memo 배열에 저장하고 return 한다.

---
## 📌 회고

- 이상하게 1씩 덜 나와서 문제를 다시 읽어봤다. 
- 움직여서 구멍에 가거나 밖으로 나가는 것도 1번으로 치는 것이 문제였다.
- 그래서 갈 곳이 없으면 0이 아니라 1을 반환했다.
- 문제를 똑바로 읽자. 문제를 똑바로 읽자. 문제를 똑바로 읽자

## 💡코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1103 {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int n, m;
    static char[][] map;
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        map = new char[n][m];
        memo = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                memo[i][j] = -1;
            }
        }
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        boolean[][] visited = new boolean[n][m];
        int max = findMax(0, 0, visited);
        System.out.println(max);
    }

    static int findMax(int x, int y, boolean[][] visited) {
        if (memo[x][y] != -1) return memo[x][y];

        visited[x][y] = true;

        int max = 1;
        for (int i = 0; i < 4; i++) {
            int num = map[x][y] - '0';
            int nx = x + dx[i] * num, ny = y + dy[i] * num;
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if (map[nx][ny] == 'H') continue;
            if (visited[nx][ny]) return -1;
            int result = findMax(nx, ny, visited);
            if (result == -1) return -1;

            max = Math.max(max, result + 1);
        }

        memo[x][y] = max;
        visited[x][y] = false;
        return max;
    }
}

```
