import java.util.*;
import java.io.*;

public class boj14658 {
	static int[][] stars;
	static int K, N, M;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		stars = new int[K][2];
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			stars[k][0] = Integer.parseInt(st.nextToken());
			stars[k][1] = Integer.parseInt(st.nextToken());
		}

		int answer = K;
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < K; j++) {
				int x1 = stars[i][0];
				int y1 = stars[j][1];

				int x2 = x1 + L;
				int y2 = y1 + L;

				answer = Math.min(answer, count(x1, y1, x2, y2));
			}
		}


		System.out.println(answer);
	}

	public static int count(int x1, int y1, int x2, int y2) {
		int count = K;

		for (int[] star : stars) {
			if (star[0] >= x1 && star[0] <= x2 &&
				star[1] >= y1 && star[1] <= y2) {
				count--;
			}
		}

		return count;
	}

}
