package day_0213;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class boj25587 {
	static int count = 0;
	static int N;
	static int[] A, B, parent, size;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 도시의 개수
		int M = Integer.parseInt(st.nextToken()); // 쿼리의 개수

		A = new int[N]; // 배수로 용량
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		parent = new int[N];
		size = new int[N];

		B = new int[N]; // 강수량 용량
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
			parent[i] = i;
			size[i] = 1;
			// 홍수가 날 도시의 개수 초기화
			if (A[i] < B[i])
				count++;
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int query = Integer.parseInt(st.nextToken());
			if (query == 2) {
				System.out.println(count);
			} else {
				int x = Integer.parseInt(st.nextToken()) - 1; // 인덱스를 0부터 시작으로 맞추기
				int y = Integer.parseInt(st.nextToken()) - 1;
				union(x, y);
			}
		}
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return;

		if (A[x] < B[x])
			count -= size[x];
		if (A[y] < B[y])
			count -= size[y];

		if (x > y) {
			parent[x] = y;
			size[y] += size[x];
			A[y] += A[x];
			B[y] += B[x];

			if (A[y] < B[y])
				count += size[y];
		} else {
			parent[y] = x;
			size[x] += size[y];
			A[x] += A[y];
			B[x] += B[y];

			if (A[x] < B[x])
				count += size[x];
		}
	}

	public static int find(int x) {
		if (parent[x] == x)
			return parent[x];
		else
			return find(parent[x]);
	}
}
