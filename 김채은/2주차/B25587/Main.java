package boj25587;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] cities;    // 용량, 강수량
	static int[] parent;    // 각 도시의 부모 배열
	static int[] size;    // root 도시 내부에 도시가 몇개있는지
	static long[][] sum;    // 총 용량, 총 강수량 -> root만
	static long floodCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());    // 도시개수
		M = Integer.parseInt(st.nextToken());   // 쿼리개수

		cities = new int[2][N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			cities[0][i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			cities[1][i] = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		size = new int[N + 1];
		sum = new long[2][N + 1];
		floodCnt = 0;

		// 초기화
		for (int i = 1; i <= N; i++) {
			parent[i] = i;    // 자기자신이 루트
			size[i] = 1;
			sum[0][i] = cities[0][i];
			sum[1][i] = cities[1][i];

			if (sum[0][i] < sum[1][i])
				floodCnt++;    // 초기 홍수
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			switch (Integer.parseInt(st.nextToken())) {
				case 1:
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					union(x, y);
					break;
				case 2:
					sb.append(floodCnt).append("\n");
					break;
			}
		}
		for (int i = 1; i <= N; i++) {
			System.out.print(parent[i] + " ");
		}
		System.out.println(sb);
	}

	static boolean isFloodRoot(int r) {
		return sum[1][r] > sum[0][r];
	}

	static void union(int a, int b) {
		int na = find(a);
		int nb = find(b);
		if (na == nb)
			return;

		// 합치기 전 : 홍수나는 상황이면 제거
		if (isFloodRoot(na))
			floodCnt -= size[na]; // fc 0
		if (isFloodRoot(nb))
			floodCnt -= size[nb];

		// 작은 트리를 큰 트리 밑에 붙이기위해 : na를 부모로
		if (size[na] < size[nb]) {
			int tmp = na;
			na = nb;
			nb = tmp;
		}

		parent[nb] = na;
		size[na] += size[nb];
		sum[0][na] += sum[0][nb];
		sum[1][na] += sum[1][nb];

		if (isFloodRoot(na))
			floodCnt += size[na];
	}

	static int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}
}
