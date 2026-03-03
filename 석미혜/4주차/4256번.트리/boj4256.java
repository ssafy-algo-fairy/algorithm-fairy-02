package day_0226;

import java.io.*;
import java.util.*;

public class boj4256 {
	static int T, N;
	static int[] preorder;
	static int[] inorder;
	static int targetIndex;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			preorder = new int[N];
			inorder = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				preorder[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				inorder[i] = Integer.parseInt(st.nextToken());
			}

			targetIndex = 0;
			order(inorder);
			System.out.println();
		}
	}

	public static void order(int[] arr) {
		if (arr.length == 1) {
			System.out.print(arr[0] + " ");
			return;
		}

		int target = preorder[targetIndex];
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == preorder[targetIndex]) {
				index = i;
				break;
			}
		}

		if (index != 0) {
			targetIndex++;
			order(Arrays.copyOfRange(arr, 0, index));
		}
		if (index + 1 != arr.length) {
			targetIndex++;
			order(Arrays.copyOfRange(arr, index + 1, arr.length));
		}
		System.out.print(target + " ");
	}

}
