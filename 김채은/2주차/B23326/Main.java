package boj23326;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int N, Q;
	static TreeSet<Integer> sights = new TreeSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());    // 구역 개수
		Q = Integer.parseInt(st.nextToken());    // 쿼리 개수

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			if (Integer.parseInt(st.nextToken()) == 1) {
				sights.add(i);    // 명소만 저장
			}
		}

		int current = 1;
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			switch (q) {
				// 명소 지정, 해제
				case 1:
					int sight = Integer.parseInt(st.nextToken());
					if (sights.contains(sight)) {
						sights.remove(sight);
					} else {
						sights.add(sight);
					}
					break;
				// x만큼 이동
				case 2:
					int x = Integer.parseInt(st.nextToken());
					current = (current + x - 1) % N + 1;
					break;
				case 3:
					if (sights.isEmpty()) {    // 명소 x
						System.out.println("-1");
					} else {
						Integer ceiling = sights.ceiling(current);

						if (ceiling != null) {    // 오른쪽에 있는 경우
							System.out.println(ceiling - current);
						} else {    // 오른쪽에 없을 경우 초기위치부터 한번 더 보기
							System.out.println(N - current + sights.first());
						}
						break;
					}
			}
		}
	}
}
