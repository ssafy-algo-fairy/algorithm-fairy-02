package com.ssafy.algo.fairy.w2.B2015;

import java.util.*;
import java.io.*;

public class b2015 {

	static int N, K;
	static long ansCnt;

	static long[] sumArr;

	static HashMap<Long, Integer> map = new HashMap<>();

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		sumArr = new long[N];
		int sum = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			sum += Integer.parseInt(st.nextToken()); // 누적합
			sumArr[i] = sum;
		}

		ansCnt = 0;
		map.put(0L, 1); // 시작 누적합 0
		
		for (int i = 0; i < N; i++) {
			long need = sumArr[i] - K;
			ansCnt += map.getOrDefault(need, 0);

			map.put(sumArr[i], map.getOrDefault(sumArr[i], 0) + 1);
		}

		System.out.println(ansCnt);

	}

}
