package com.ssafy.test;

import java.util.*;
import java.io.*;

public class fairySolution {

	static int N, M;
	static int[][] Map;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int max_safe_cnt = 0;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		wall(0);
		System.out.println(max_safe_cnt);

	}

	static void wall(int cnt) {
		if (cnt == 3) { // 벽 3개 세운 뒤 바이러스
			virus();
			return;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (Map[i][j] != 0)
					continue;
				Map[i][j] = 1;
				wall(cnt + 1); // 빈 칸에 벽 세우고 재귀
				Map[i][j] = 0;
			}
		}
	}

	static void virus() {
		int[][] tmpMap = new int[N][M]; // tmpMap에서 바이러스 확산
		int[][] visited = new int[N][M];
		Queue<Integer> qx = new LinkedList<>();
		Queue<Integer> qy = new LinkedList<>();

		for (int i = 0; i < N; i++) { // tmpMap으로 Map 복사, 초기 바이러스 큐에 삽입
			for (int j = 0; j < M; j++) {
				tmpMap[i][j] = Map[i][j];
				if (Map[i][j] == 2) {
					qx.offer(i);
					qy.offer(j);
					visited[i][j] = 1;
				}
			}
		}
		while (!qx.isEmpty()) { // 바이러스 확산
			int x = qx.poll();
			int y = qy.poll();
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i], ny = y + dy[i];
				if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1 || tmpMap[nx][ny] != 0 || visited[nx][ny] == 1)
					continue;
				tmpMap[nx][ny] = 2;
				qx.offer(nx);
				qy.offer(ny);
				visited[nx][ny] = 1;
			}
		}

		int safe_cnt = 0; // 안전 영역 크기 계산
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmpMap[i][j] == 0)
					safe_cnt++;
			}
		}
		max_safe_cnt = Math.max(max_safe_cnt, safe_cnt);
	}

}