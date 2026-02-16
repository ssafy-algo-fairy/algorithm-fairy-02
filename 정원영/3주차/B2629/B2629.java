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
