package week1;

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken()); 

            for (int w = K; w >= W; w--) {

                // 선택하지 않았을때
                int notTake = dp[w];

                // 선택했을때
                int take = dp[w - W] + V;

                if (take > notTake) {
                    dp[w] = take;
                } else {
                    dp[w] = notTake;
                }
            }
        }

        System.out.println(dp[K]);
	}
}
