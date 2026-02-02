package com.ssafy.test;

import java.util.Scanner;

public class fairySolution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[][] dp = new int[N+1][K+1];

        for (int i=1; i<=N; i++) {
        	int W, V;
            W = sc.nextInt();
            V = sc.nextInt();
            if (W<=K) dp[i][W]=V;
            for (int j=1; j<=K; j++) {
            	if (dp[i-1][j]==0) continue;
            	if (dp[i-1][j]>dp[i][j]) dp[i][j]=dp[i-1][j];
            	if (j+W<=K && dp[i-1][j]+V>dp[i][j+W]) dp[i][j+W]=dp[i-1][j]+V;
            }
        }
        
        int max_val = 0;
        for (int j=1; j<=K; j++) {
        	if (dp[N][j]>max_val) max_val=dp[N][j];
        }
        System.out.println(max_val);
    }
}