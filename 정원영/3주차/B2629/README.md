## **🔍 문제 요약**

- 추의 개수 → 무게, 구슬의 개수 → 무게가 차례로 주어진다
- 주어진 추만을 사용하여 구슬의 무게를 알 수 있는지 확인하여 Y or N

- 추의 개수 ≤ 30
- 1 ≤ 추의 무게 (자연수, 중복 o) ≤ 500
- 구슬의 개수 ≤ 7
- 1 ≤ 구슬의 무게 (자연수) ≤ 40,000

---

## **💡문제 접근 / 풀이 전략**
- 유사 배낭문제다~~
- 왼쪽에 넣으면 빼주고, 오른쪽에 넣으면 더해주는 방식
- 음수가 가능하기 때문에 +15000 해줬따~~


## **✍️ 회고**

- 왼쪽과 오른쪽의 차만 보면 되기때문에 절댓값으로 해도된다고 함!!
- 즉 15000 안더해주고 그냥 절댓값으로..
- 1차원 배열이면 가능하긴 한데 공간복잡도 계산해보니 충분해서 그냥 해버렸다..
- B형에선 똑바로 하는걸로..?
- DP는 항상 어렵다 그래도 맛있따 야밀리~~

## **✅코드 & 소요 시간**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2629 {

    static int chuCount;

    static boolean[][] dp;
    static int[] chus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        chuCount = Integer.parseInt(br.readLine());
        dp = new boolean[chuCount + 1][30002];

        chus = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp[0][15000] = true;
        for (int i = 0; i < chuCount; i++) {
            int chu = chus[i];
            for (int j = 0; j <= 30001; j++) {
                if (!dp[i][j]) continue;
                dp[i + 1][j + chu] = true;
                dp[i + 1][j - chu] = true;
                dp[i + 1][j] = true;
            }
            dp[i + 1][chu + 15000] = true;
        }

        int ballCount = Integer.parseInt(br.readLine());

        int[] balls = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int ball : balls) {
            if (ball + 15000 > 30000 || !dp[chuCount][ball + 15000]) sb.append("N ");
            else sb.append("Y ");
        }

        System.out.println(sb);

    }

}

```


ref #99 
