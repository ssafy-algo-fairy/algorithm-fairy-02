package com.ssafy.test;

import java.util.*;
import java.io.*;

public class fairySolution {

	static int N;
	static int[] type;
	static long[] num;
	static ArrayList<Integer>[] goTo;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		type = new int[N + 1];
		num = new long[N + 1];
		goTo = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++)
			goTo[i] = new ArrayList<>(); // 동적 할당
		for (int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			type[i] = (c == 'W') ? 0 : 1; // 0 늑대, 1 양
			num[i] = Integer.parseInt(st.nextToken()); // 마리수
			int p = Integer.parseInt(st.nextToken()); // 갈 수 있는 섬
			goTo[p].add(i);
		}

		System.out.println(postorder(1));

	}

	static long postorder(int cur) {
		long sum = 0;
		for (int i : goTo[cur]) {
			sum += postorder(i);
		}
		if (type[cur] == 0)
			sum = Math.max(sum - num[cur], 0); // 늑대인 경우
		else
			sum += num[cur]; // 양인 경우

		return sum;
	}

}