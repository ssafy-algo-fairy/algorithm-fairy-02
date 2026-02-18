## **🔍 문제 요약**

- 홍익대학교는 N개의 구역이 원형으로 배치된 모습
    - 1 → 2 → … → N → 다시 1
- 명소만 방문하려고함
- 1번에서 출발
- 쿼리
    - 1 i : i가 명소가 아니라면 명소 지정, 명소였다면 지정해제
    - 2 x : 시계방향으로 x만큼 이동
    - 3 : 명소로 가기위해 최소 얼만큼 움직여야하는지 출력. 없으면 -1
        - 3번 쿼리는 하나 이상존재

- 1 ≤ N 구역의 개수 ≤ 500,000
- 1 ≤ Q 쿼리의 개수 ≤ 100,000
- 1 ≤ x ≤ 10^9

---

## **💡문제 접근 / 풀이 전략**

- 일단 스포당한 바로는 트리셋 써야함
    - **트리셋**이란?
        - Tree : 자동 정렬 (내부적으로는 이진탐색트리(Red Black Tree))
        - Set : 중복 X
        - set.first(), last()로 최소 최대 뽑을 수 있고, 검색이 빠르다
    - 그냥 배열 대신 트리셋을 써야하는 이유는?
        - 최악의 경우 500,000개 구역을 100,000번 탐색해야함 → 500억번 연산 시간초과

- **풀이 과정**
    1. treeset에 명소만 저장
    2. 현재 위치 관리
    3. 명령
        - 1 → `set.contains`로 명소 여부 확인 및 갱신
        - 2 → 현재 위치 갱신 (원형임을 주의)
        - 3 →
            - `set.ceiling(current)` : 주어진 값보다 크거나 같은 값 중 가장 작은 값 반환
            - 명소가 오른쪽에 없을 경우 0번 위치부터 다시 한번 보기
            - 없는 경우 -1

---

## **✅ 코드 & 소요 시간**

```java
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
					if(sights.contains(sight)) {
						sights.remove(sight);
					}else{
						sights.add(sight);
					}
					break;
				// x만큼 이동
				case 2:
					int x = Integer.parseInt(st.nextToken());
					current = (current + x - 1) % N + 1;
					break;
				case 3:
					if(sights.isEmpty()) {	// 명소 x
						System.out.println("-1");
					}else{
						Integer ceiling = sights.ceiling(current);

						if(ceiling != null) {	// 오른쪽에 있는 경우
							System.out.println(ceiling - current);
						}else{	// 오른쪽에 없을 경우 초기위치부터 한번 더 보기
							System.out.println(N - current + sights.first());
						}
					break;
				}
			}
		}
	}
}

```

---

## **✍️ 회고**

- 트리셋이라는 걸 몰랐으면 오래걸렸을듯...ㅎ..ㅎ..... 이미 들었는데 어떡해..

---

## **🧩 다른 풀이 (선택)**

```java

```

---
