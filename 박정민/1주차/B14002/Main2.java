package week1.B14002;

import java.util.*;
import java.io.*;

public class Main2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] input = new int[n+1];
		int[] dp = new int[n+1];
		int[] prev = new int[n+1];
		
		for (int i = 1; i <= n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < i; j++) {
				if (input[j] < input[i]) {
					if (dp[i] < dp[j]+1) {
						dp[i] = dp[j] + 1;
						prev[i] = j;
					}					
				}
			}
		}
		
		int max = 0;
		int maxIndex = -1;
		for (int i = 1; i <= n; i++) {
			if (dp[i] > max) {
				max = dp[i];
				maxIndex = i;
			}
		}
		sb.append(max).append("\n");
		
		Stack<Integer> s = new Stack<>();
		for (int i = 0; i < max; i++) {
			s.push(input[maxIndex]);
			maxIndex = prev[maxIndex];
		}
		
		while (!s.isEmpty()) {
			sb.append(s.pop()).append(" ");
		}
		
		System.out.print(sb);
	}

}
