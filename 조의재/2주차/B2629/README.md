# ⚖️ [백준 2629] 양팔저울 - Java 풀이 분석

## 1. 문제 정보
* **문제 번호**: 2629
* **알고리즘**: 다이나믹 프로그래밍 (DP), 배낭 문제 (Knapsack)
* **핵심**: 추의 무게를 더하거나 빼서 만들 수 있는 모든 경우의 수 측정

---

## 2. 전체 소스 코드

```java
    /**
     * DP 테이블 구성 로직
     * dp[i][j]: i번째 추까지 사용했을 때, 무게 j를 만들 수 있는지 여부
     */
    public static void makeDp() {
        // 최대 무게합만큼의 공간 확보
        dp = new boolean[N + 1][total + 1];
        dp[0][0] = true;

        for (int i = 1; i <= N; i++) {
            int curW = weight[i];
            for (int j = 0; j <= total; j++) {
                if (dp[i - 1][j]) {
                    // 1. 추를 사용하지 않는 경우
                    dp[i][j] = true;
                    // 2. 추를 구슬의 반대편에 놓는 경우 (무게 더하기)
                    if (j + curW <= total) dp[i][j + curW] = true;
                    // 3. 추를 구슬과 같은 편에 놓는 경우 (무게 빼기)
                    dp[i][Math.abs(j - curW)] = true;
                }
            }
        }
    }

    public static void solution() {
        for (int i = 0; i < M; i++) {
            int marble = marbles[i];
            // 구슬 무게가 추의 총합보다 크면 절대 측정 불가
            if (marble <= total && dp[N][marble]) {
                sb.append("Y ");
            } else {
                sb.append("N ");
            }
        }
    }

```

## 3. 핵심 로직 분석

### 💡 DP 상태 전이 (State Transition)
각 추를 하나씩 추가할 때마다, 기존에 만들 수 있었던 모든 무게(`j`)에 대해 세 가지 선택지를 수행합니다.



1.  **현상 유지**: `dp[i][j] = dp[i-1][j]` (현재 추를 저울에 올리지 않음)
2.  **무게 합**: `dp[i][j + curW] = true` (기존 무게 조합이 있는 저울 반대편에 현재 추를 추가)
3.  **무게 차**: `dp[i][Math.abs(j - curW)] = true` (기존 무게 조합이 있는 저울 같은 편에 현재 추를 추가하여 차이를 측정)

### 🚀 시간 및 공간 복잡도
* **시간 복잡도**: $O(N \times \text{TotalWeight})$
    * 추의 개수 $N(30)$ $\times$ 전체 무게 합 $15,000$ $\approx$ $450,000$ 연산으로 제한 시간(1초) 내에 충분히 통과합니다.
* **공간 복잡도**: $O(N \times \text{TotalWeight})$
    * `boolean` 2차원 배열을 사용하여 메모리 제한(128MB) 내에 충분히 해결 가능합니다. (약 0.45MB 사용)
