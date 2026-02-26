package com.ssafy.algo.fairy.w4.B1103;

import java.util.*;
import java.io.*;

public class b1103 {

	static class Point {
		int x, y, turn;

		public Point(int x, int y, int turn) {
			this.x = x;
			this.y = y;
			this.turn = turn;
		}
	}

	static int N, M;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int[][] Map, visited, dp;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Map = new int[N][M];
		visited = new int[N][M];
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			char[] cArr = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (cArr[j] == 'H')
					Map[i][j] = 0;
				else
					Map[i][j] = cArr[j] - '0';
			}
		}

		dfs(0, 0, 1);

		System.out.println(dp[0][0]);

	}

	static int dfs(int x, int y, int turn) {
		if (visited[x][y] != 0)
			return dp[x][y] = -1;
		if (dp[x][y] != 0)
			return dp[x][y];

		int maxVal = 0;
		visited[x][y] = 1;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i] * Map[x][y], ny = y + dy[i] * Map[x][y];
			if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1 || Map[nx][ny] == 0)
				continue;

			int n = dfs(nx, ny, turn + 1);
			if (n == -1)
				return dp[x][y] = -1;
			maxVal = Math.max(maxVal, n);
		}

		visited[x][y] = 0;
		return dp[x][y] = maxVal + 1;
	}

}
