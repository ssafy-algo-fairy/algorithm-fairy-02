import java.util.*;
import java.io.*;

public class boj15961 {
	private static int max = 0;
	private static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 접시의 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

		int[] sushi = new int[n];
		int[] check = new int[d+1];
		for (int i = 0; i < n; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
			if (i < k) { // 초기 k개의 연속된 초밥
				check[sushi[i]]++;
			}
		}
		
		int count = 0;

		// 초기 k개 초밥 종류 수 계산
		for (int i = 1; i <= d; i++) {
		    if (check[i] > 0) count++;
		}

		max = Math.max(max, count + (check[c] == 0 ? 1 : 0));

		for (int i = 1; i < n; i++) {
		    int removeIdx = sushi[(i - 1) % n];
		    int addIdx = sushi[(i + k - 1) % n];

		    if (check[removeIdx]-- == 1) count--;
		    if (check[addIdx]++ == 0) count++;

		    max = Math.max(max, count + (check[c] == 0 ? 1 : 0));
		}

		System.out.println(max);
	}

}
