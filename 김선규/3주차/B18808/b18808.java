package com.ssafy.algo.fairy.w3.B18808;

import java.util.*;
import java.io.*;

public class b18808 {

	static class Sticker {
		int x, y;
		int[][] st;

		public Sticker(int x, int y) {
			this.x = x;
			this.y = y;
			st = new int[x][y];
		}
	}

	static int N, M, K;
	static Sticker sticker;

	static int[][] notebook;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		notebook = new int[N][M];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			sticker = new Sticker(X, Y);
			for (int j = 0; j < X; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < Y; k++) {
					sticker.st[j][k] = Integer.parseInt(st.nextToken());
				}
			}

			boolean flag = false;
			for (int rot = 0; rot < 4; rot++) {
				for (int x = 0; x <= N - sticker.x; x++) {
					for (int y = 0; y <= M - sticker.y; y++) {
						if (able(x, y)) {
							flag = true;
							break;
						}
					}
					if (flag)
						break;
				}
				if (flag)
					break;

				sticker = rotation();
			}
		}

		int cnt = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (notebook[i][j] == 1)
					cnt++;

		System.out.println(cnt);

	}

	static boolean able(int x, int y) {
		boolean flag = true;

		// x, y에 붙이는 것이 가능한지 확인
		for (int i = 0; i < sticker.x; i++) {
			for (int j = 0; j < sticker.y; j++) {
				if (sticker.st[i][j] == 1 && notebook[x + i][y + j] == 1) {
					flag = false;
					break;
				}
			}
			if (!flag)
				break;
		}

		// 가능하면 붙이기
		if (flag) {
			for (int i = 0; i < sticker.x; i++)
				for (int j = 0; j < sticker.y; j++)
					if (sticker.st[i][j] == 1)
						notebook[x + i][y + j] = 1;
		}

		return flag;
	}

	static Sticker rotation() {
		Sticker newSticker = new Sticker(sticker.y, sticker.x);
		for (int i = 0; i < sticker.y; i++)
			for (int j = 0; j < sticker.x; j++)
				newSticker.st[i][j] = sticker.st[sticker.x - 1 - j][i];

		return newSticker;
	}

}