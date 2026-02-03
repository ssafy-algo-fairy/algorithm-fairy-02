package B1987;

import java.util.*;
import java.io.*;

public class Main {

	public static char[][] map;
	public static int max = 0;
	public static int[] dx = { 1, -1, 0, 0 };
	public static int[] dy = { 0, 0, 1, -1 };
	public static int r, c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		for (int i = 0; i < r; i++) {
			String input = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		run(map[0][0] + "", 0, 0);

		System.out.println(max);

	}

	public static void run(String s, int x, int y) {
		max = Math.max(max, s.length());
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < r && nx >= 0 && ny < c && ny >= 0) {
				if (!s.contains(map[nx][ny] + ""))
					run(s + map[nx][ny], nx, ny);
			}
		}
	}

}
