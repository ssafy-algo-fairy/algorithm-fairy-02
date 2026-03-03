package B2629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] weight;
    static int[] bead;
    static boolean[] dp = new boolean[40001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine().trim());
        weight = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine().trim());
        bead = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            bead[i] = Integer.parseInt(st.nextToken());
        }
        // dp[i] = 양 쪽 무게의 차이가 i인 경우가 가능한지 여부
        // 양 쪽 무게의 차이를 만들 수 있으면, 그 차이만큼의 구슬의 무게를 확인할 수 있음
        dp[0] = true;

        for (int w : weight) {
            boolean[] temp = new boolean[40001];

            for (int d = 0; d <= 40000; d++) {
                // 현재 무게가 가능하지 않으면 확장 불가
                if (!dp[d]) {
                    continue;
                }
                // 현재 무게에서 추를 같은 쪽에 추가하는 경우
                temp[d + w] = true;

                // 현재 무게에서 추를 반대쪽에 추가하는 경우
                temp[Math.abs(d - w)] = true;
            }

            // 새로 만들어진 dp 추가
            for (int d = 0; d <= 40000; d++) {
                if (temp[d]) {
                    dp[d] = true;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            int x = bead[i];
            if (dp[x]) {
                sb.append("Y");
            } else {
                sb.append("N");
            }
            sb.append(" ");
        }

        System.out.println(sb);
    }
}