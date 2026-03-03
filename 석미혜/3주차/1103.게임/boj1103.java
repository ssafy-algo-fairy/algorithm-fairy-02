import java.io.*;
import java.util.*;

public class boj1103 {
	static char[][] map;
	static boolean[][] visited;
	static int[][] dp;
	static int answer = -1;
	static int N, M;
	static boolean infinite = false;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M];
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		int result = dfs(0, 0);

		if (infinite)
			System.out.println(-1);
		else
			System.out.println(result);

	}

	public static int dfs(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M || map[x][y] == 'H') {
			return 0;
		}

		if (visited[x][y]) {
			infinite = true;
			return 0;
		}

		if (dp[x][y] != 0) {
			return dp[x][y];
		}

		visited[x][y] = true;

		int n = map[x][y] - '0';
		int max = 0;

		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir] * n;
			int ny = y + dy[dir] * n;

			max = Math.max(max, dfs(nx, ny) + 1);

			if (infinite)
				return 0;
		}

		visited[x][y] = false;
		dp[x][y] = max;
		return dp[x][y];
	}

}