package B14502;

import java.util.*;
import java.io.*;

public class Main {
	static int[][] map;
	static LinkedList<Node> virus;
	static int n, m;
	static int max = 0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		virus = new LinkedList<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int a = Integer.parseInt(st.nextToken());
				map[i][j] = a;
				if (a == 2) virus.add(new Node(i, j));
			}
		}
		
		dfs(0);
		System.out.println(max);

		
	}
	
	public static void dfs(int depth) {
		if (depth == 3) {
			int[][] copyMap = new int[n][];
			for (int i = 0; i < n; i++) {
				copyMap[i] = map[i].clone();
			}
			
 			for (Node node : virus) {
				int x = node.getX();
				int y = node.getY();
				spread(copyMap, x, y);	
			}
 			
 			int count = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (copyMap[i][j] == 0) count++;
				}
			}
			max = Math.max(max, count);
			return;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					dfs(depth+1);
					map[i][j] = 0;
				}
			}
		}
	}
	
	public static class Node {
		int x;
		int y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
	}
	
	public static void spread(int[][] map, int x, int y) {
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < n && nx >= 0 && ny < m && ny >= 0) {
				if (map[nx][ny] == 0) {
					map[nx][ny] = 2;
					spread(map, nx, ny);
				}
			}
		}
		
	}

}
