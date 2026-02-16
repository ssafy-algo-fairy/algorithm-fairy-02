## **🔍 문제 요약**

- 추의 개수 → 무게, 구슬의 개수 → 무게가 차례로 주어진다
- 주어진 추만을 사용하여 구슬의 무게를 알 수 있는지 확인하여 Y or N

- 추의 개수 ≤ 30
- 1 ≤ 추의 무게 (자연수, 중복 o) ≤ 500
- 구슬의 개수 ≤ 7
- 1 ≤ 구슬의 무게 (자연수) ≤ 40,000

---

## **💡문제 접근 / 풀이 전략**

- 구하기 위해서는 크게 세가지 경우 → 합 or 차 or 0
- if) 가능한 모든 부분집합을 구한 후 사이사이에 +, - 를 또 조합
    - 30의 부분집합 → 2^30 = 10억… ❌
- 추를 추가할 때 마다 → **합, 차 갱신**
    - ex) 추 1,4 / 구슬 3,2
        1. 추 1 : {0, 1}
        2. 추 4 : 기존 값에다가 -4, +4 → {0, 1, 3, 4, 5}
        3. 반복…
    - **이때 음수 유지할 필요없음 (절댓값만 기억)**
        - ex) -1, 1 이 있고 여기다가 -4, 4를 넣는 상황이라면 -1 + 4 == -4 + 1 → 좌우 위치만 바꾼것
        - 따라서 |1 - 4| , 1 + 4만 갱신해주면됨
- 추의 무게 500 * 개수 30 ⇒ 15000 넘는 구슬은 ❌

---

## **✅ 코드 & 소요 시간**

```java
package boj2629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static HashSet<Integer> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 추
		N = Integer.parseInt(br.readLine());
		set.add(0);
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			makeMarble(Integer.parseInt(st.nextToken()));
		}

		// 구슬
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int marble = Integer.parseInt(st.nextToken());
			if (marble <= 15000 && set.contains(marble)) {
				sb.append("Y").append(" ");
			} else {
				sb.append("N").append(" ");
			}
		}

		System.out.println(sb);
	}

	static void makeMarble(int n) {
		HashSet<Integer> next = new HashSet<>(set);
		for (Integer marble : set) {
			next.add(Math.abs(marble - n));
			next.add(marble + n);
		}
		set = next;
	}
}

```

15528 KB / 120ms

---

## **✍️ 회고**

- set에다가 바로 add하지 말고 새로운 셋을 만들어준 후 추가하고 바꿔줘야함 !!
    - set은 인덱스가 없으므로 size 만들어놓고해도 의미없음
- 이런것도 DP라고 하는건가??

---

## **🧩 다른 풀이 (선택)**

```java

```

---
