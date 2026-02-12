package com.ssafy.algo.fairy.w1.B20955;

import java.util.*;
import java.io.*;

public class b20955 {

	static int N, M, cnt;

	static int[] root;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		root = new int[N + 1];
		for (int i = 1; i <= N; i++)
			root[i] = i;

		cnt = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			union(u, v);
		}

		for (int i = 1; i <= N; i++) { // 루트 개수 만큼 트리가 존재하고 이들을 연결하면 최종적으로 -1
			if (root[i] == i)
				cnt++;
		}

		System.out.println(cnt - 1);

	}

	static void union(int u, int v) {
		int ru = find(u);
		int rv = find(v);

		if (ru == rv) // 불필요한 연결 제거
			cnt++;
		else
			root[ru] = rv;
	}

	static int find(int u) {
		if (root[u] == u)
			return u;
		else
			// find 하면서 만난 모든 값의 부모 노드를 root로 만들며 경로 압축
			return root[u] = find(root[u]);
	}

}
