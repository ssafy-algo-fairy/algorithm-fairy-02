package week4.B1103;

import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	static boolean inf;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		visited = new boolean[n][m];
		dp = new int[n][m];
		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i], -1);
		}
		inf = false;
		
		for (int i = 0; i < n; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if (input[j] == 'H') map[i][j] = 0;
				else map[i][j] = input[j] - '0';
			}
		}
		
		visited[0][0] = true;
		dfs(0, 0, 1);
		
		System.out.println(dp[0][0]);
	}
	
	public static int dfs(int x, int y, int count) {
		if (dp[x][y] != -1) return count + dp[x][y];
		
		int dis = map[x][y];
		
		int maxLength = 1; // 맵 밖으로 나가거나 구멍에 빠져도 1번은 간거임

		for (int d = 0; d < 4; d++) {
			int nx = x + dis * dx[d];
			int ny = y + dis * dy[d];
			
			if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
			
			if (map[nx][ny] == 0) continue;
			
			if (visited[nx][ny]) {
				dp[0][0] = -1;
				inf = true;
				return -1;
			}
			
			visited[nx][ny] = true;
			maxLength = Math.max(maxLength, dfs(nx, ny, count+1) - count);
			if (inf) return -1;
			visited[nx][ny] = false;
		}
		
		dp[x][y] = maxLength;
		return count + maxLength;
	}

}
