package boj14658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Star {
	int x;
	int y;

	public Star(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int N, M, L, K;
	static Star[] stars;
	static int maxCnt = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 가로 길이
		M = Integer.parseInt(st.nextToken()); // 세로 길이
		L = Integer.parseInt(st.nextToken()); // 트램펄린 한변
		K = Integer.parseInt(st.nextToken()); // 별똥별 개수

		stars = new Star[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			stars[i] = new Star(r, c);
		}

		int maxCnt = 0;

		for (int i = 0; i < K; i++) {
			for (int j = 0; j < K; j++) {
				maxCnt = Math.max(maxCnt, countStars(stars[i].x, stars[j].y));
			}
		}

		System.out.println(K - maxCnt);
	}

	static int countStars(int startX, int startY) {
		int count = 0;
		int endX = startX + L;
		int endY = startY + L;

		for (Star s : stars) {
			// 트램펄린 범위 내에 있는지 확인
			if (s.x >= startX && s.x <= endX && s.y >= startY && s.y <= endY) {
				count++;
			}
		}
		return count;
	}
}
