## **🔍 문제 요약**

- 물품의 수 : 1 ≤ N ≤ 100
- 준서가 버틸 수 있는 무게 : 1 ≤ K ≤ 100,000
- 두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)
- 최대한 높은 가치의 가방을 싸고 그 가치합의 최대값 출력
- 중복 x

---

## **💡문제 접근 / 풀이 전략**

- **다이나믹 프로그래밍** : 하나의 큰 문제를 여러개의 작은 문제로 저장하여 다시 큰 문제 해결할 때 사용 **→ 쪼개서 저장해두고 재활용**
    - bottom up : 반복문
    - top down : 재귀
    - 분할정복과 차이점 : 분할정복은 중복 x

```
4 7
6 13
4 8
3 6
5 12
```

<img width="590" height="270" alt="image" src="https://github.com/user-attachments/assets/d10602f4-05bd-4048-a352-225635893ca1" />


두가지 경우를 체크하면 됨

1. **물건이 배낭 용량을 초과하는 경우에는 넣지 못함**
    
    → 같은 무게의 쓰던 값 그대로 사용
    
2. **물건 무게가 배낭 무게 이하인 경우 (넣을 수 있는 경우)**
    
    → 원래 쓰던 값과 새로운 값을 비교
    
    - 무게 3, 가치 6의 물건이 들어오는 경우 (주황색 네모 위에칸)
        - 가방의 무게가 7이므로 무게 4 (7-3)의 최대값 + 새로운 물건의 가치 VS 기존값을 비교한다 → 새로운 값이 더 크므로 갱신

최종적으로는 주황색 칸의 값이 정답이됨

---

## **✅ 코드 & 소요 시간**

```java
package algo_study;

import java.io.*;
import java.util.*;

public class boj12865 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());	// 물품의 수
		int k = Integer.parseInt(st.nextToken());	// 최대 무게

		int [][] dp = new int [n+1][k+1];	// 행 : 물건, 열 : 무게
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int wgt = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
			for(int j = 1; j <= k; j++) {
				// 못 넣는 경우 (크기가 더 커서) -> 같은 무게의 쓰던 값 그대로 사용
				if(wgt > j) {
					dp[i][j] = dp[i-1][j];
				}else {	// 넣을 수 있는 경우에는 비교
					dp[i][j] = Integer.max(dp[i-1][j-wgt] + val, dp[i-1][j]);
				}
			}
		}
		
		System.out.println(dp[n][k]);

	}

}

```

---

## **✍️ 회고**

- `Integer.max(dp[i-1][j-wgt] + val, dp[i-1][j]);`
- `Integer.max(dp[i][j-wgt] + val, dp[i-1][j]);`

위의 두 식에서 위에가 정답인 이유가 궁금했는데 (이미 이미 갱신된 최소값이니까 사용해도 되지 않는지?) 그럴경우에는 같은 물건을 두번 담는 경우가 생김

---

## **🧩 다른 풀이 (선택)**

```java

```
