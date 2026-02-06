package com.ssafy.test;

import java.util.*;
import java.io.*;

public class fairySolution {

	static int N, d, k, c;
	static int[] belt;
	static int[] chobob_cnt;

	static int p1, p2, type_cnt = 0, max_type_cnt;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 접시의 수
		d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		belt = new int[N];
		for (int i = 0; i < N; i++) {
			belt[i] = Integer.parseInt(br.readLine());
		}
		chobob_cnt = new int[d + 1];

		p1 = 0;
		p2 = k - 1;
		for (int i = 0; i < k; i++) { // 첫 윈도우 생성
			if (chobob_cnt[belt[i]] == 0)
				type_cnt++;
			chobob_cnt[belt[i]]++;
		}
		if (chobob_cnt[c] == 0) // 쿠폰 초밥 포함 x
			max_type_cnt = type_cnt + 1;
		else 					// 쿠폰 초밥 포함 o
			max_type_cnt = type_cnt;

		while (p1 < N) { // 슬라이딩 윈도우
			chobob_cnt[belt[p1]]--;
			if (chobob_cnt[belt[p1]] == 0)
				type_cnt--;
			p1++;
			p2++;
			if (p2 == N)
				p2 = 0;
			if (chobob_cnt[belt[p2]] == 0)
				type_cnt++;
			chobob_cnt[belt[p2]]++;

			if (chobob_cnt[c] == 0) // 쿠폰 초밥 포함 x
				max_type_cnt = Math.max(max_type_cnt, type_cnt + 1);
			else 					// 쿠폰 초밥 포함 o
				max_type_cnt = Math.max(max_type_cnt, type_cnt);
		}
		
		System.out.println(max_type_cnt);

	}

}