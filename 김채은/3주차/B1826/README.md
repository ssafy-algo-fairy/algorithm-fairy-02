## **🔍 문제 요약**

- 성경이 → 주유소 1 → 주유소 2 → …. → 마을
- 이때 최소 횟수로 주유소에 들러야함
- 1km당 1L가 필요

- 1 ≤ L 마을까지의 거리 ≤ 1,000,000
- 1 ≤ P 원래있던 연료의 양 ≤ 1,000,000
- 1 ≤ N 주유소 개수 ≤ 10,000
- 1 ≤ a 주유소까지 거리 ≤ 1,000,000
- 1 ≤ b 채울 수 있는 연료의 양 ≤ 100

---

## **💡문제 접근 / 풀이 전략**

- 다음 주유소까지 갈 수 있는지만 판단
    - 갈 수 있다면 : 현재 주유소의 연료량 큐에 넣음 (PriorityQueue 내림차순 정렬)
    - 못 간다면 : 갈 수 있을 때 까지 주유소에 있는거 빼서 넣음
- **주유소가 거리순으로 나온다는 보장이 X;;**

---

## **✅ 코드 & 소요 시간**

```java
package boj1826;

import java.io.*;
import java.util.*;

public class Main {

	static int N, L, P;
	static ArrayList<Node> gasStation = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			gasStation.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken()); // 마을까지 거리
		P = Integer.parseInt(st.nextToken()); // 현재 연료
		gasStation.add(new Node(L, 0));

		// 거리순 정렬
		gasStation.sort(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.loc - o2.loc;
			}
		});

		System.out.println(drive());
	}

	static int drive() {
		int curDist = 0;
		int answer = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		for (int i = 0; i <= N; i++) {

			// 도착 계산
			if (curDist + P >= L) {
				return answer;
			}

			int move = gasStation.get(i).loc - curDist;

			if (P >= move) { // 다음 위치까지 이동 가능
				pq.offer(gasStation.get(i).oil);
				curDist += move;
				P -= move;
			} else { // 다음 위치까지 이동 불가능
				while (!pq.isEmpty()) {
					P += pq.poll();
					answer++;

					if (P >= move) {
						pq.offer(gasStation.get(i).oil);
						curDist += move;
						P -= move;
						break;
					}
				}

				if (pq.isEmpty() && P < move)
					return -1; // 다 넣어도 이동 불가
			}
		}
		return answer;
	}
}

class Node {
	int loc;
	int oil;

	Node(int loc, int oil) {
		this.loc = loc;
		this.oil = oil;
	}
}

```

---

## **✍️ 회고**

- 당연히 순서대로 나올줄 알고 배열로 풀다가 바꿔서 거리순 정렬했슴다..

---

## **🧩 다른 풀이 (선택)**

```java

```

---
