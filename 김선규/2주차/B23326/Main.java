package com.ssafy.test;

import java.util.*;
import java.io.*;

public class fairySolution {

	static int N, Q, cur;
	static TreeSet<Integer> ts = new TreeSet<>();

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		cur = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken()); // 명소 1 아니면 0
			if (n == 1)
				ts.add(i);
		}

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int query = Integer.parseInt(st.nextToken());
			if (query == 1) { // 1 i : i번 구역이 명소가 아니었다면 명소로 지정되고, 명소였다면 지정이 풀리게 된다.
				int n = Integer.parseInt(st.nextToken()) - 1;
				if (ts.contains(n))
					ts.remove(n);
				else
					ts.add(n);
			} else if (query == 2) { // 2 x : 도현이가 시계방향으로 x만큼 이동한다.
				int n = Integer.parseInt(st.nextToken());
				cur = (cur + n) % N;
			} else { // 3 : 도현이가 명소에 도달하기 위해 시계방향으로 최소 몇 칸 움직여야 하는 지 출력한다.
					 // 	명소가 존재하지 않는 경우 -1을 출력한다.
				if (ts.isEmpty())
					System.out.println(-1);
				else if (ts.ceiling(cur) != null)
					System.out.println(ts.ceiling(cur) - cur);
				else
					System.out.println(N - cur + ts.first());
			}
		}

	}

}