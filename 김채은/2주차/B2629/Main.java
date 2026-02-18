package boj2629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static HashSet<Integer> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 추
		N = Integer.parseInt(br.readLine());
		set.add(0);
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			makeMarble(Integer.parseInt(st.nextToken()));
		}

		// 구슬
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int marble = Integer.parseInt(st.nextToken());
			if (marble <= 15000 && set.contains(marble)) {
				sb.append("Y").append(" ");
			} else {
				sb.append("N").append(" ");
			}
		}

		System.out.println(sb);
	}

	static void makeMarble(int n) {
		HashSet<Integer> next = new HashSet<>(set);
		for (Integer marble : set) {
			next.add(Math.abs(marble - n));
			next.add(marble + n);
		}
		set = next;
	}
}
