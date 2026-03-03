package boj3178;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		String[] prefix = new String[N];
		String[] suffix = new String[N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			prefix[i] = line.substring(0, K);

			StringBuilder sb = new StringBuilder();
			for (int j = 2 * K - 1; j >= K; j--) {
				sb.append(line.charAt(j));
			}
			suffix[i] = sb.toString(); // 뒤집어서 저장
		}

		Arrays.sort(prefix);
		Arrays.sort(suffix);

		long answer = 2L * K;

		for (int i = 1; i < N; i++) {
			int lp = lcp(prefix[i - 1], prefix[i], K);
			answer += (K - lp);

			int ls = lcp(suffix[i - 1], suffix[i], K);
			answer += (K - ls);
		}

		System.out.println(answer);

	}

	static int lcp(String a, String b, int K) {
		int i = 0;
		while (i < K && a.charAt(i) == b.charAt(i))
			i++;
		return i;
	}

}
