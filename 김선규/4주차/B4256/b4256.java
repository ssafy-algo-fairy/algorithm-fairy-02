package com.ssafy.algo.fairy.w4.B4256;

import java.util.*;
import java.io.*;

public class b4256 {

	static int N;

	static int[] preorder;
	static int[] inorder;

	// p c1 c2
	// c1 p c2
	// c1 c2 p
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {

			N = Integer.parseInt(br.readLine());

			preorder = new int[N];
			inorder = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				preorder[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				inorder[i] = Integer.parseInt(st.nextToken());

			div(0, 0, N);
			System.out.println();

		}

	}

	static void div(int preP, int inP, int size) {
		int parent = preorder[preP];

		int left = 0;
		for (int i = inP; inorder[i] != parent; i++) {
			left++;
		}
		int right = size - 1 - left;
		
		if (left >= 1)
			div(preP + 1, inP, left);

		if (right >= 1)
			div(preP + left + 1, inP + left + 1, right);

		System.out.print(parent + " ");
	}

}
