package com.ssafy.algo.fairy.w4.B6549;

import java.util.*;
import java.io.*;

public class b6549 {

	static class Height {
		long h;
		int n;

		public Height(long h, int n) {
			this.h = h;
			this.n = n;
		}
	}

	static int N;
	static long maxVal;

	static PriorityQueue<Height> heightQ;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if (N == 0)
				break;

			maxVal = 0;
			heightQ = new PriorityQueue<>((h1, h2) -> (int) (h2.h - h1.h));

			for (int i = 0; i < N; i++) {
				int h = Integer.parseInt(st.nextToken());

				int lastIdx = i;
				while (!heightQ.isEmpty()) {
					Height maxH = heightQ.peek();

					if (maxH.h >= h) {
						maxVal = Math.max(maxVal, (i - maxH.n) * maxH.h);
						lastIdx = maxH.n;
						heightQ.poll();
					} else
						break;
				}
				heightQ.offer(new Height(h, lastIdx));
			}
			while (!heightQ.isEmpty()) {
				Height maxH = heightQ.poll();
				maxVal = Math.max(maxVal, (N - maxH.n) * maxH.h);
			}

			System.out.println(maxVal);

		}

	}

}
