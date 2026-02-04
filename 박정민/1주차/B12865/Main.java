package B12865;

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] things = new int[n][2];
		int[] dp = new int[k+1];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			things[i][0] = Integer.parseInt(st.nextToken());
			things[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < n; i++) {
			int weight = things[i][0];
			int value = things[i][1];
			
			for (int j = k; j - weight >= 0; j--) {
				dp[j] = Math.max(dp[j], dp[j-weight] + value);
			}
		}
		
		int max = 0;
		for (int i = 0; i <= k; i++) {
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
	}
}
