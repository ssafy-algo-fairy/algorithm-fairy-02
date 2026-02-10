package boj3691;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	static int N, B;
	static Map<String, List<int[]>> parts;
	static Integer[] qualities;    // 중복없는 성능 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int testCase = 0; testCase < T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());    // 물건
			B = Integer.parseInt(st.nextToken());    // 예산

			parts = new HashMap<>();
			TreeSet<Integer> treeSet = new TreeSet<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				String type = st.nextToken();
				st.nextToken();
				int price = Integer.parseInt(st.nextToken());
				int quality = Integer.parseInt(st.nextToken());

				if (!parts.containsKey(type)) {
					parts.put(type, new ArrayList<>());
				}
				parts.get(type).add(new int[] {price, quality});

				treeSet.add(quality);
			}

			qualities = treeSet.toArray(new Integer[0]);

			int low = 0, high = qualities.length - 1;
			int ans = 0;

			// 이분탐색
			while (low <= high) {
				int mid = (low + high) >>> 1;
				// 성공하면 성능 높여보기
				if (checkQuality(qualities[mid])) {
					ans = qualities[mid];
					low = mid + 1;
				} else {    // 실패하면 성능 줄이기
					high = mid - 1;
				}
			}

			System.out.println(ans);
		}
	}

	static boolean checkQuality(int quality) {
		int price = 0;

		// 모든 부품 종류 순회
		for (String type : parts.keySet()) {
			int minPrice = Integer.MAX_VALUE;
			boolean found = false;

			// 같은 종류 내에서 성능 가장 좋으면서 저렴한거 찾기
			for (int[] info : parts.get(type)) {
				int p = info[0];
				int q = info[1];

				if (q >= quality && p < minPrice) {
					minPrice = p;
					found = true;
				}
			}

			// 단 하나라도 만족하지 못하는 종류가 있다면 불가능 (모든 부품 포함시켜야하므로)
			if (!found)
				return false;

			price += minPrice;
		}

		return price <= B;    // 예산 초과하지는 않는지 체크
	}
}
