package com.ssafy.algo.fairy.w2.B14658;

import java.util.*;
import java.io.*;

public class b14658 {

	static class Star {
		int x, y;

		public Star(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, L, K, maxCnt;

	static Star[] starList;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		starList = new Star[K];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			starList[i] = new Star(x, y);
		}

		for (int i = 0; i < K; i++) { // 트램펄린의 x좌표, y좌표는 starList의 x, y 중에서 선택
			int x = starList[i].x;
			for (int j = 0; j < K; j++) {
				int y = starList[j].y;
				int starCnt = 0;
				for (Star star : starList) { // 트램펄린 내 star 개수 카운트
					if (star.x < x || star.x > x + L || star.y < y || star.y > y + L)
						continue;
					starCnt++;
				}
				maxCnt = Math.max(maxCnt, starCnt);
			}
		}

		System.out.println(K - maxCnt);

	}

}
