package boj2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	static int N, K;
	static HashMap<Integer, Integer> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new HashMap<>();
		map.put(0, 1); // 현재 지점이 누적합이 될 경우

		int sum = 0;
		long cnt = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			sum += Integer.parseInt(st.nextToken());

			// 있으면 개수만큼 증가
			if (map.containsKey(sum - K))
				cnt += map.get(sum - K);

			// 현재 누적합 추가 : 없으면 넣고, 있으면 +1
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}

		System.out.println(cnt);

	}

}
