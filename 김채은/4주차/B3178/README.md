## **🔍 문제 요약**

- 2K개 글자로 이루어진 단어 N개
- **첫 K개 글자에서 나누어질 수 있고, 마지막 K개 글자에서 만날 수 있다**

<img width="772" height="229" alt="image" src="https://github.com/user-attachments/assets/f3a610fb-ff3f-42df-ab20-aa5cafaff68f" />
<img width="969" height="495" alt="image" src="https://github.com/user-attachments/assets/abc84ccc-bd71-4fa7-b66d-bd2323be9762" />

→ 아래 예시는 마지막 4개 글자에서 만나야하는데 그전에 만남 (D) + 첫 K개에서 나뉘어져야하는데 그 후에 갈라짐 (E)

- 1 ≤ N ≤ 10,000, 1 ≤ K ≤ 100
- 정점의 수가 가장 적은 코코스의 정점의 수 출력

---

## **💡문제 접근 / 풀이 전략**

<aside>
💡

**트라이 (Trie)**

- 문자열을 저장하고 빠르게 탐색하기 위한 트리 형태 자료구조 → 접두사 검색에 빠르다
- 검색과 삽입 O(L) 시간

**특징**

- 루트노드 공백
- 자식노드는 각 단어들의 첫 글자
- 파란색은 endOfWord
</aside>

- **나누기** : 트라이로 처리 가능 (접두사)
- **합치기** : k이후의 글자들이 어딘가에라도 있는지 find, 없으면 생성
    - 즉 접미사를 검색해야함…

**⇒ 결론적으로는 트라이도 안써도됨!!! (접미사 검색때문에)**

- 글자 입력 받고 사전순 정렬 (앞, 뒤 k개씩 잘라서)
- 접미사는 뒤집어서 저장한다
- 차이나는 개수를 더해서 계산 → 이때 사전순 정렬이므로 자신보다 위에 있는 것이랑만 비교한다

---

## **✅ 코드 & 소요 시간**

```java
package boj3178;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		String[] prefix = new String[N];
		String[] suffix = new String[N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			prefix[i] = line.substring(0, K);

			StringBuilder sb = new StringBuilder();
			for (int j = 2 * K - 1; j >= K; j--) {
				sb.append(line.charAt(j));
			}
			suffix[i] = sb.toString(); // 뒤집어서 저장
		}

		Arrays.sort(prefix);
		Arrays.sort(suffix);

		long answer = 2L * K;

		for (int i = 1; i < N; i++) {
			int lp = lcp(prefix[i - 1], prefix[i], K);
			answer += (K - lp);

			int ls = lcp(suffix[i - 1], suffix[i], K);
			answer += (K - ls);
		}

		System.out.println(answer);

	}

	static int lcp(String a, String b, int K) {
		int i = 0;
		while (i < K && a.charAt(i) == b.charAt(i))
			i++;
		return i;
	}

}

```

29512 / 448

---

## **✍️ 회고**

- 접미사를 어떻게 처리해야할지 감이 안와서 찾아보다가 생각보다 간단한 방법으로 풀수있다는 사실을 알게되었다…
- 문제가 진짜 이해하기 힘들었는데 예시 3번이랑 그림대조해보고 앞뒤 K개씩이라는 것에 집중

---

## **🧩 다른 풀이 (선택)**

```java

```

---
