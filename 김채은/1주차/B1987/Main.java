package boj1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int [] dr = {-1,1,0,0};
	static int [] dc = {0,0,-1,1};

	static char [][] board;
	static boolean [] visited;

	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		board = new char [R][C];
		visited = new boolean ['Z' + 1];

		for(int i = 0; i < R; i++) {
			board [i] = br.readLine().toCharArray();
		}

		visited[board[0][0]] = true;
		dfs(0,0,1);

		System.out.println(max);

	}

	static void dfs(int r, int c, int move) {
		max = Math.max(max, move);	// dfs 시작할 때 max 갱신

		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if(nr < 0 || nr >= board.length || nc < 0 || nc >= board[0].length) {
				continue;
			}

			if(!visited[board[nr][nc]]) {
				visited[board[nr][nc]] = true;
				dfs(nr, nc, move+1);
				visited[board[nr][nc]] = false;	// dfs가 끝나면 방문 여부 해제
			}
		}

	}



}
