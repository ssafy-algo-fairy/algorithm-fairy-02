package com.ssafy.algo.fairy.w3.B2450;

import java.util.*;
import java.io.*;

public class b2450 {

	static int N, minChange;

	static int[] num; // 처음 숫자
	static int[] numCnt = new int[4]; // 1,2,3 개수
	static int[] selected = new int[4];

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		num = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			numCnt[num[i]]++;
		}

		minChange = Integer.MAX_VALUE;
		int[][] diff = new int[4][4]; // [처음 숫자][결과 숫자] // diff[1][2]: 1인 자리를 2로 바꾸는 개수
		findMinDiff(0, diff);

		System.out.println(minChange);

	}

	static void findMinDiff(int idx, int[][] diff) {
		if (idx == N) {
			int n12 = Math.min(diff[1][2], diff[2][1]); // 1,2 자리 바꾸기
			int n13 = Math.min(diff[1][3], diff[3][1]); // 1,3 자리 바꾸기
			int n23 = Math.min(diff[2][3], diff[3][2]); // 2,3 자리 바꾸기
			diff[1][2] -= n12;
			diff[1][3] -= n13;
			diff[2][3] -= n23;
			int n123 = diff[1][2]; // 1,2,3 로테이션
			int n132 = diff[1][3]; // 1,3,2 로테이션
			minChange = Math.min(minChange, n12 + n13 + n23 + 2 * (n123 + n132));

			return;
		}

		// 1,2,3이 연속하는 순열 만들고 처음 숫자와 차이 누적
		for (int i = 1; i <= 3; i++) {
			if (selected[i] == 1)
				continue;

			selected[i] = 1;
			int[][] newDiff;
			newDiff = copy(diff);
			for (int j = 0; j < numCnt[i]; j++) {
				if (num[idx + j] != i) {
					newDiff[num[idx + j]][i]++;
				}
			}
			findMinDiff(idx + numCnt[i], newDiff);
			selected[i] = 0;
		}
	}

	static int[][] copy(int[][] arr) {
		int[][] nArr = new int[4][4];

		for (int i = 1; i <= 3; i++)
			for (int j = 1; j <= 3; j++)
				nArr[i][j] = arr[i][j];

		return nArr;
	}

}