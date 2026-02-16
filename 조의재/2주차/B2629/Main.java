import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static StringBuilder sb;
    static int[] weight, marbles;
    static boolean[][] dp;
    static int total;

    public static void main(String[] args) throws IOException {
        inputFile();
        makeDp();
        solution();
        System.out.println(sb.toString().trim());
    }

    public static void inputFile() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        weight = new int[N + 1];
        total = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
            total += weight[i];
        }

        M = Integer.parseInt(br.readLine());
        marbles = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            marbles[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void makeDp() {
        dp = new boolean[N + 1][total + 1];
        dp[0][0] = true;

        for (int i = 1; i <= N; i++) {
            int curW = weight[i];
            for (int j = 0; j <= total; j++) {
                if (dp[i - 1][j]) {
                    dp[i][j] = true;
                    if (j + curW <= total) dp[i][j + curW] = true;
                    dp[i][Math.abs(j - curW)] = true;
                }
            }
        }
    }

    public static void solution() {
        for (int i = 0; i < M; i++) {
            int marble = marbles[i];
            if (marble <= total && dp[N][marble]) {
                sb.append("Y ");
            } else {
                sb.append("N ");
            }
        }
    }
}