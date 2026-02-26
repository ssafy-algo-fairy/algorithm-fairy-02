package B14438;

import java.util.*;
import java.io.*;

public class b14438 {

	static int N, M;
	static int idx, num; //
	static int left, right;

	static int[] numArr, tree;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		numArr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}

		int treeSize = 1;
		while (treeSize < N)
			treeSize *= 2;
		tree = new int[treeSize * 2];

		makeTree(0, N - 1, 1); // 세그먼트 트리 생성

		M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int query = Integer.parseInt(st.nextToken());

			if (query == 1) {
				idx = Integer.parseInt(st.nextToken()) - 1;
				num = Integer.parseInt(st.nextToken());
				updateTree(0, N - 1, 1); // 트리 업데이트
			} else {
				left = Integer.parseInt(st.nextToken()) - 1;
				right = Integer.parseInt(st.nextToken()) - 1;
				System.out.println(getMin(0, N - 1, 1)); // 구간 최솟값
			}
		}

	}

	static int makeTree(int start, int end, int node) {
		if (start == end)
			return tree[node] = numArr[start];

		int mid = (start + end) / 2;
		return tree[node] = Math.min(makeTree(start, mid, node * 2), makeTree(mid + 1, end, node * 2 + 1));
	}

	static int updateTree(int start, int end, int node) {
		if (start == end)
			return tree[node] = num;

		int mid = (start + end) / 2;
		if (idx <= mid)
			return tree[node] = Math.min(updateTree(start, mid, node * 2), tree[node * 2 + 1]);
		else
			return tree[node] = Math.min(tree[node * 2], updateTree(mid + 1, end, node * 2 + 1));
	}

	static int getMin(int start, int end, int node) {
		if (end < left || right < start)
			return Integer.MAX_VALUE;

		if (left <= start && end <= right)
			return tree[node];

		int mid = (start + end) / 2;
		return Math.min(getMin(start, mid, node * 2), getMin(mid + 1, end, node * 2 + 1));
	}

}