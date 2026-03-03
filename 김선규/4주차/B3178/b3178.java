package com.ssafy.algo.fairy.w4.B3178;

import java.util.*;
import java.io.*;

public class b3178 {

	static int N, K;

	static List<String> front;
	static List<String> backRev;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		front = new ArrayList<>(N);
		backRev = new ArrayList<>(N);

		for (int i = 0; i < N; i++) {
			String s = br.readLine().trim();
			front.add(s.substring(0, K));
			String back = s.substring(K, 2 * K);
			backRev.add(new StringBuilder(back).reverse().toString());
		}

		System.out.println(trie());
	}

	static long trie() { // 앞에서부터 K, 뒤에서부터 K를 따로 정렬
		Collections.sort(front);
		long sumFront = 0;
		for (int i = 1; i < front.size(); i++) {
			sumFront += cmp(front.get(i - 1), front.get(i));
		}
		
		Collections.sort(backRev);
		long sumBack = 0;
		for (int i = 1; i < backRev.size(); i++) {
			sumBack += cmp(backRev.get(i - 1), backRev.get(i));
		}
		
		return 2 * N * K - sumFront - sumBack;
	}

	static int cmp(String a, String b) { // 글자 바뀌는 부분(경로 갈라지는 부분) 체크
		int i = 0;
		while (i < K && a.charAt(i) == b.charAt(i))
			i++;
		
		return i;
	}

}
