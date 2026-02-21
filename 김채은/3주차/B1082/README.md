## **🔍 문제 요약**

- 문방구에서 0 ~ N-1 까지의 숫자를 cost[i]원에 판매
- 숫자는 중복 구매 가능
- 방번호가 0이 아니라면 0으로 시작 불가
- 최대 M원을 사용해서 만들 수 있는 가장 큰 방 번호
- 1 ≤ N ≤ 10 → 0에서 9까지
- 1 ≤ P 가격 ≤ 50
- 1 ≤ M 예산 ≤ 50

---

## **💡문제 접근 / 풀이 전략**

- **DP 문제**
    - dp[j] : 예산 j원으로 만들 수 있는 가장 큰 수
    - 같은 수를 중복해서 넣을 수 있으므로, 1 ~ j 까지 배열의 맨 앞에서부터 연속적으로 갱신
    - 숫자를 더 구매하면 뒤에 붙인 값 vs 기존 값 비교 후 갱신
- 맨 앞자리 0이 되는 경우 예외처리
- **BigInteger 고려하기 → 50자리 숫자가 나올 수 있음**

---

## **✅ 코드 & 소요 시간**

```java
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

```

---

## **✍️ 회고**

- 마지막 예제 출력을 보고 생각했어야했는데 int → BigInteger로 바꾸는 시간도 줄였어야 하지 않을까!!!
- 그리고 BigInteger 사용법도 잊지말자….

---

## **🧩 다른 풀이 (선택)**

```java

```

---
