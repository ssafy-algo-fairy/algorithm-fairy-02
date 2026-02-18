package boj16437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static ArrayList<Integer>[] island;
	static char[] type;    // S or W
	static long[] cnt;    // 마리 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		island = new ArrayList[N + 1];
		type = new char[N + 1];
		cnt = new long[N + 1];

		for (int i = 1; i <= N; i++) {
			island[i] = new ArrayList<>();
		}

		type[1] = 'S';
		cnt[1] = 0;
		for (int i = 2; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			type[i] = st.nextToken().charAt(0);
			cnt[i] = Long.parseLong(st.nextToken());
			island[Integer.parseInt(st.nextToken())].add(i);
		}

		System.out.println(dfs(1));

	}

	static long dfs(int u) {
		long sum = 0;

		// 먼저 리프노드까지 이동
		for (int v : island[u]) {
			sum += dfs(v);
		}

		if (type[u] == 'S') {    // 양이면 누적
			sum += cnt[u];
		} else {    // 늑대면 감소
			sum -= cnt[u];
			if (sum < 0)
				sum = 0;
		}

		return sum;
	}

}

