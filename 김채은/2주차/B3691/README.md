## **🔍 문제 요약**

- 상근이의 예산으로 가장 좋은 컴퓨터 맞추려고함
- 컴퓨터 성능 = 가장 안좋은 부품의 성능 → 가장 안좋은 부품의 성능이 최고인 경우를 구한다
- 각 부품은 하나씩만 사용하되, 모두 써야함

- 1 ≤ n 부품의 개수≤ 1,000
- 1 ≤ b 예산 ≤ 1,000,000,000
- 부품 정보 : type name price quality
    - 0 ≤ price ≤ 1,000,000
    - 0 ≤ quality ≤ 1,000,000,000 → 더하지는 않으니까 int도 ㄱㅊ을듯
    - name에 중복 x

---

## **💡문제 접근 / 풀이 전략**

- **첫번째 시도 ⇒ 시간초과**
    - 물건별로 트리맵에 가격과 성능 저장 (가격 오름차순)
    - dfs로 탐색하면서 예산초과하면 되돌아옴
    - 한개뿐인 물건은 먼저 넣고 시작
    
    → 시간 초과 이유는 모든 조합이 다 예산 내에 들어온다던가… 그런 경우에 결국 완전탐색
    
    **→ 성능을 생각을 해야함**
    
- **두번째 시도 ⇒ 이분탐색**
    - 특정 성능 mid를 정한다 (성능 배열 중간값)
        - 트리셋으로 받은 후에 배열로 변환
    - 부품을 돌면서 성능이 mid 이상인 것 중 가장 저렴한 것을 더하면서 진행
    - 합이 예산 내에 들어온다면 성능을 높이고 아니면 반대로 (이분탐색)

---

## **✅ 코드 & 소요 시간**

```java
package boj3691;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	static int N, B;
	static Map<String, List<int[]>> parts;
	static Integer[] qualities;    // 중복없는 성능 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int testCase = 0; testCase < T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());    // 물건
			B = Integer.parseInt(st.nextToken());    // 예산

			parts = new HashMap<>();
			TreeSet<Integer> treeSet = new TreeSet<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				String type = st.nextToken();
				st.nextToken();
				int price = Integer.parseInt(st.nextToken());
				int quality = Integer.parseInt(st.nextToken());

				if (!parts.containsKey(type)) {
					parts.put(type, new ArrayList<>());
				}
				parts.get(type).add(new int[] {price, quality});

				treeSet.add(quality);
			}

			qualities = treeSet.toArray(new Integer[0]);

			int low = 0, high = qualities.length - 1;
			int ans = 0;

			// 이분탐색
			while (low <= high) {
				int mid = (low + high) >>> 1;
				// 성공하면 성능 높여보기
				if (checkQuality(qualities[mid])) {
					ans = qualities[mid];
					low = mid + 1;
				} else {    // 실패하면 성능 줄이기
					high = mid - 1;
				}
			}

			System.out.println(ans);
		}
	}

	static boolean checkQuality(int quality) {
		int price = 0;

		// 모든 부품 종류 순회
		for (String type : parts.keySet()) {
			int minPrice = Integer.MAX_VALUE;
			boolean found = false;

			// 같은 종류 내에서 성능 가장 좋으면서 저렴한거 찾기
			for (int[] info : parts.get(type)) {
				int p = info[0];
				int q = info[1];

				if (q >= quality && p < minPrice) {
					minPrice = p;
					found = true;
				}
			}

			// 단 하나라도 만족하지 못하는 종류가 있다면 불가능 (모든 부품 포함시켜야하므로)
			if (!found)
				return false;

			price += minPrice;
		}

		return price <= B;    // 예산 초과하지는 않는지 체크
	}
}

```

65508KB / 532ms

---

## **✍️ 회고**

- 어렵다…… 이분탐색도 까먹지 말고 잘 활용하기…
- 예제에는 물건 종류들이 순서대로 들어오는데, 문제 읽어보면 순서대로 들어온다는 보장도 없음~!!!

---

## **🧩 다른 풀이 (선택)**

```java

```

---
