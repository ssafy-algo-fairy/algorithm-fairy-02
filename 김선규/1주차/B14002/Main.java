package com.ssafy.test;

import java.util.Scanner;

public class fairySolution {
	
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] dp = new int [N][3];
        for (int i=0; i<N; i++) {
        	int num = sc.nextInt();
        	int max_len = 0, max_idx = 0;
        	for (int j=0; j<i; j++) {
        		if (dp[j][0]<num && dp[j][1]>max_len) { // 배열의 마지막 숫자가 받은 숫자보다 작은 가장 긴 배열
        			max_len = dp[j][1];
        			max_idx = j;
        		}
        	}
        	dp[i][0] = num;
        	dp[i][1] = max_len+1;
        	dp[i][2] = max_idx;
        	//System.out.println(dp[i][0] + " " + dp[i][1] + " " + dp[i][2]);
        }
        
        int ans = 0, ans_idx = 0;
        for (int i=0; i<N; i++) {
        	if (dp[i][1]>ans) {
        		ans = dp[i][1]; // 가장 긴 배열 길이
        		ans_idx = i;
        	}
        }
        int[] ans_arr = new int[ans];
        for (int i=ans-1; i>=0; i--) { // 가장 긴 배열 역추적
        	ans_arr[i] = dp[ans_idx][0];
        	ans_idx = dp[ans_idx][2];
        }
        System.out.println(ans);
        for (int i=0; i<ans; i++) {
        	System.out.print(ans_arr[i]+" ");
        }
        
    }
    
}