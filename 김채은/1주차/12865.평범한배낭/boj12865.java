package algo_study;

import java.io.*;
import java.util.*;

public class boj12865 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());	// 물품의 수
		int k = Integer.parseInt(st.nextToken());	// 최대 무게

		int [][] dp = new int [n+1][k+1];	// 행 : 물건, 열 : 무게
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int wgt = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
			for(int j = 1; j <= k; j++) {
				// 못 넣는 경우 (크기가 더 커서) -> 같은 무게의 쓰던 값 그대로 사용
				if(wgt > j) {
					dp[i][j] = dp[i-1][j];
				}else {	// 넣을 수 있는 경우에는 비교
					dp[i][j] = Integer.max(dp[i-1][j-wgt] + val, dp[i-1][j]);
				}
			}
		}
		
		System.out.println(dp[n][k]);

	}

}
