package week2.B2629;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		boolean[][] dp = new boolean[n+1][30001]; // 0: -15000, 15000: 0, 30000: 15000
		dp[0][15000] = true;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= n; i++) {
			int input = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j <= 30000; j++) {
				if (dp[i-1][j]) {
					dp[i][j-input] = true;
					dp[i][j] = true;
					dp[i][j+input] = true;
				}
			}
		}
		
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < m; i++) {
			int input = Integer.parseInt(st.nextToken()) + 15000;
			if (input > 30000) sb.append("N ");
			else {
				if (dp[n][input]) sb.append("Y ");
				else sb.append("N ");
			}		
		}
		
		System.out.println(sb);

	}

}
