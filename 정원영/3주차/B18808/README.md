#120

### 📌 문제 정보

- **번호:** 18808
- **제목:** 스티커 붙이기
- **난이도:** Gold 3
- **분류:** 구현, 시뮬레이션

---

### 💡 접근 방식
- 모든 경우를 해보는 수 밖에 없음
- 이런 경우는 시간 복잡도를 따져봐야함
  - 스티커를 4방향을 돌리기 : 4
  - 좌상단부터 우하단까지 스티커 배치해보기 : 40 * 40 = 1600
  - 실제로 스티커를 붙여보기 : 10 * 10 = 100
  - 모든 스티커에 해보기 : 100
- 결론적으로 4 * 1600 * 100 * 100 = 64000000 (6400만) -> 가능
- 이후는 그냥 구현 : 스티커 붙이기 -> 되나? -> 붙이기 -> 돌려도 보기
---

### ⚠️ 느낀 점

- 역시나 시간 복잡도 계산이 우선
- 이제 이런 시뮬은 차근히 함수만 나누면 뭐..


### 💻 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m, k;
    static ArrayList<int[][]> stickers = new ArrayList<>();
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new boolean[n][m];


        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[r][c];
            for (int j = 0; j < r; j++) {
                sticker[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            stickers.add(sticker);
        }

        for (int[][] sticker : stickers) {
            useSticker(sticker);
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j]) result++;
            }
        }

        System.out.print(result);

    }

    static void useSticker(int[][] sticker) {
        for (int k = 0; k < 4; k++) {

            if (k != 0) sticker = turnSticker(sticker);

            for (int i = 0; i <= n - sticker.length; i++) {
                for (int j = 0; j <= m - sticker[0].length; j++) {
                    if (canUse(i, j, sticker)) {
                        use(i, j, sticker);
                        return;
                    }
                }
            }
        }

    }

    static int[][] turnSticker(int[][] sticker) {
        int r = sticker.length;
        int c = sticker[0].length;

        int[][] newSticker = new int[c][r];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                newSticker[i][j] = sticker[r - 1 - j][i];
            }
        }

        return newSticker;
    }

    static void use(int sx, int sy, int[][] sticker) {
        for (int i = sx; i < sx + sticker.length; i++) {
            for (int j = sy; j < sy + sticker[0].length; j++) {
                if (sticker[i - sx][j - sy] == 0) continue;
                map[i][j] = true;
            }
        }
    }

    static boolean canUse(int sx, int sy, int[][] sticker) {
        for (int i = sx; i < sx + sticker.length; i++) {
            for (int j = sy; j < sy + sticker[0].length; j++) {
                if (sticker[i - sx][j - sy] == 0) continue;
                if (map[i][j]) return false;
            }
        }

        return true;
    }
}

```
