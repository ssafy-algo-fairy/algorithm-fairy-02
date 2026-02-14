## **🔍 문제 요약**

- 좌표 평면에 별똥별이 떨어지고, 트램펄린을 설치해서 튕겨내야함
- 트램펄린 개수는 한개, 크기는 L*L
- 가장 많이 튕겨낼 수 있을 때 그래도 부딪히는 별똥별 개수를 구한다

- 1 ≤ N 가로, M 세로 ≤ 500,000
- 1 ≤ L 트램펄린 한변 ≤ 100,000
- 1 ≤ K 별똥별 개수≤ 100

---

## **💡문제 접근 / 풀이 전략**

- 모든 좌표 다 검색 시 250,000,000,000번 연산 → X
- 테케를 보고 별똥별을 각 트램펄린의 꼭지점 위치로 두고 탐색하게 했는데 아래와 같은 케이스 만족못함
- <img width="239" height="219" alt="스크린샷 2026-02-14 오후 3 48 56" src="https://github.com/user-attachments/assets/010a0a5f-a6f0-4a70-b998-3898e8184d04" />
- 별 두개를 모서리에 둔 후 꼭지점을 잡아서 다시 체크

---

## **✅ 코드 & 소요 시간**

```java
package boj14658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Star {
	int x;
	int y;

	public Star(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int N, M, L, K;
	static Star[] stars;
	static int maxCnt = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 가로 길이
		M = Integer.parseInt(st.nextToken()); // 세로 길이
		L = Integer.parseInt(st.nextToken()); // 트램펄린 한변
		K = Integer.parseInt(st.nextToken()); // 별똥별 개수

		stars = new Star[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			stars[i] = new Star(r, c);
		}

		int maxCnt = 0;

		for (int i = 0; i < K; i++) {
			for (int j = 0; j < K; j++) {
				maxCnt = Math.max(maxCnt, countStars(stars[i].x, stars[j].y));
			}
		}

		System.out.println(K - maxCnt);
	}

	static int countStars(int startX, int startY) {
		int count = 0;
		int endX = startX + L;
		int endY = startY + L;

		for (Star s : stars) {
			// 트램펄린 범위 내에 있는지 확인
			if (s.x >= startX && s.x <= endX && s.y >= startY && s.y <= endY) {
				count++;
			}
		}
		return count;
	}
}

```
---

## **✍️ 회고**

- 이런거 어케 생각하지 정말

---

## **🧩 다른 풀이 (선택)**

```java

```

---

