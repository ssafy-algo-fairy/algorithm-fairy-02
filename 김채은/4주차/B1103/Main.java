package boj1103;

import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static char [][] board;
	static int [][] move;
	static boolean [][] visited;
	static int [] mr = {-1, 1, 0, 0};
	static int [] mc = {0, 0, -1, 1};

	public static void main(String [] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new char [R][C];
		for(int i = 0; i < R; i++) {
			board[i] = br.readLine().toCharArray();
		}

		move = new int [R][C];
		visited = new boolean[R][C];

		System.out.println(dfs(0, 0));
	}

	public static int dfs(int cr, int cc) {
		if(cr < 0 || cc < 0 || cr >= R || cc >= C || board[cr][cc] == 'H')	return 0;

		if(visited[cr][cc]) {	// 사이클 발생
			System.out.println(-1);
			System.exit(0);
		}

		if(move[cr][cc] > 0) return move[cr][cc];

		visited[cr][cc] = true;
		int maxForThisNode = 0;
		int cnt = board[cr][cc] - '0';
		for(int i = 0; i < 4; i++) {
			int nr = cr + cnt * mr[i];
			int nc = cc + cnt * mc[i];

			// 다음 노드에서의 최대 이동 횟수 + 1 (현재 노드)
			maxForThisNode = Math.max(maxForThisNode, dfs(nr, nc) + 1);
		}
		visited[cr][cc] = false;

		return move[cr][cc] = maxForThisNode;
	}

}

