package boj1082;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {
	static int N, M;
	static int[] cost;
	static BigInteger[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());    // 물건 개수
		cost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		M = Integer.parseInt(br.readLine());    // 예산
		dp = new BigInteger[M + 1];
		Arrays.fill(dp, BigInteger.ZERO);

		dp();
		System.out.println(dp[M]);
	}

	// 뒤에서부터 체크
	static void dp() {

		for (int i = N - 1; i >= 0; i--) {    // 물건
			BigInteger num = new BigInteger(String.valueOf(i));

			for (int j = 1; j <= M; j++) {    // 예산
				// 0으로 시작 방지
				if (i == 0 && dp[j].compareTo(BigInteger.ZERO) == 0)
					continue;
				// 기존값에 새로운 숫자 붙인 값과 기존 값 비교 후 갱신
				if (j >= cost[i]) {
					if (dp[j - cost[i]].multiply(BigInteger.TEN).add(num).compareTo(dp[j]) >= 0) {
						dp[j] = dp[j - cost[i]].multiply(BigInteger.TEN).add(num);
					}
				}
			}

		}
	}

}
