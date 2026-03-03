package boj4256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int N, root;
	static int[] preorder, inorder;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			preorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			inorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			root = 0;
			findRoot(0, N);
			sb.append("\n");
		}

		System.out.println(sb);
	}

	static void findRoot(int start, int end) {
		if (start >= end)
			return;

		int idx = -1;
		int curRoot = preorder[root++];

		for (int i = start; i < end; i++) {
			if (inorder[i] == curRoot) {
				idx = i;
				break;
			}
		}

		findRoot(start, idx); // 왼
		findRoot(idx + 1, end); // 오

		sb.append(curRoot).append(" ");
	}

}
