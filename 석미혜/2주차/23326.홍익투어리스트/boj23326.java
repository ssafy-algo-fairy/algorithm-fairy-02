import java.util.*;
import java.io.*;

public class boj23326 {
	private static String[] map;
	private static int N;
	static TreeSet<Integer> set = new TreeSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		int idx = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			if (st.nextToken().equals("1")) {
				set.add(i); // 1인 위치만 저장
			}
		}

		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int query = Integer.parseInt(st.nextToken());

			switch (query) {
			case 1:
				int x = Integer.parseInt(st.nextToken()) - 1;
				if (set.contains(x))
					set.remove(x);
				else
					set.add(x);
				break;
			case 2:
				int n = Integer.parseInt(st.nextToken());
				idx = (idx + n) % N;
				break;
			case 3:
				if (set.isEmpty()) {
					System.out.println(-1);
					break;
				}

				Integer next = set.ceiling(idx);
				if (next != null) {
					System.out.println(next - idx);
				}else {
					System.out.println((N - idx) + set.first());
				}
				break;
			}
		}

	}

}