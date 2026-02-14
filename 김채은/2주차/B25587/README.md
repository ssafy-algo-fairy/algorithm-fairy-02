## **🔍 문제 요약**

- N개의 도시가 있고 각 도시가 버틸 수 있는 강수량이 정해져있음
- 두 도시를 정해서 배수로 용량, 강수량을 공유할 수 있다
- 쿼리가 M개 주어지고 x,y 도시가 공사를 하거나, 현재 상태에서 홍수 날 도시 개수 출력

- 3 ≤ N 도시의 개수 ≤ 100,000
- 1 ≤ M 쿼리 개수 ≤ 100,000
- 0 ≤ 배수로 용량, 강수량 ≤ 1,000

---

## **💡문제 접근 / 풀이 전략**

- 쿼리의 개수와 도시의 개수가 크므로 쿼리 들어올 때 마다 전체 도시 계산하면 x
- 시도들...
    - HashSet [] arr → 각 도시별로 연결된 도시를 모두 갖고 있도록
        - 꺼내면서 확인
        - 새로들어온 도시를 넣을때 해당 도시와 연결된 것도 다 넣어줘야함
        - 도시가 100,000 개인데 너무 많지 않나?
    - 도시배열에 연결되어 있는거는 k라는 새로운 수를 넣도록?
        - 매번 계산하지 않게 강수량도 갱신?
        - 근데 또 어차피 같은 문제..
- **유니온파인드**
    - **Union** : 노드들을 하나의 집합으로 연결
        - (x, y)가 들어왔다고 치면 각각의 root를 찾아서 같지 않다면 x에 y를 붙이기
    - **Find** : 특정 노드의 대표 노드를 찾는다 → 두 노드의 대표노드를 통해 같은 집합인지 비교가능
        - root를 찾아가서 설정 (경로 압축)

1. **필요한 배열**
    - `cities[2][N]`→ 각 도시의 용량, 강수량 저장
    - `parent[N]` → 부모 인덱스 저장
    - `size[N]`→ root내부에 도시 몇개있는지
    - `sum [2][N]`→ 총 용량, 강수량 (root로 찾기)
2. **union()**
    - 각 도시들의 부모(루트)를 찾음 : 이미 루트가 같으면 끝
    - 각 도시들 합치기전에 이미 홍수나있었다면 제거
    - 루트끼리 비교해서 자식이 더 많은 도시를 루트도시로 설정
    - 두개 도시의 자식 합을 루트 노드에 저장 (sum)
    - 강수량과 용량 갱신
    - 현재 루트노드 기준으로 홍수 개수 갱신
    
    ```java
    	static void union(int a, int b) {
    		int na = find(a);
    		int nb = find(b);
    		if (na == nb)
    			return;
    
    		// 합치기 전 : 홍수나는 상황이면 제거
    		if (isFloodRoot(na))
    			floodCnt -= size[na];
    		if (isFloodRoot(nb))
    			floodCnt -= size[nb];
    
    		// 작은 트리를 큰 트리 밑에 붙이기위해 : na를 부모로
    		if (size[na] < size[nb]) {
    			int tmp = na;
    			na = nb;
    			nb = tmp;
    		}
    
    		parent[nb] = na;
    		size[na] += size[nb];
    		sum[0][na] += sum[0][nb];
    		sum[1][na] += sum[1][nb];
    
    		if (isFloodRoot(na))
    			floodCnt += size[na];
    	}
    ```
    
3. **find()**
    - 도시의 부모의 부모를 계속 찾음..
    
    ```java
    static int find(int x) {
    		if (parent[x] == x)
    			return x;
    		return parent[x] = find(parent[x]);
    	}
    ```
    

---

## **✅ 코드 & 소요 시간**

```java
package boj25587;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] cities;    // 용량, 강수량
	static int[] parent;    // 각 도시의 부모 배열
	static int[] size;    // root 도시 내부에 도시가 몇개있는지
	static long[][] sum;    // 총 용량, 총 강수량 -> root만
	static long floodCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());    // 도시개수
		M = Integer.parseInt(st.nextToken());   // 쿼리개수

		cities = new int[2][N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			cities[0][i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			cities[1][i] = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		size = new int[N + 1];
		sum = new long[2][N + 1];
		floodCnt = 0;

		// 초기화
		for (int i = 1; i <= N; i++) {
			parent[i] = i;    // 자기자신이 루트
			size[i] = 1;
			sum[0][i] = cities[0][i];
			sum[1][i] = cities[1][i];

			if (sum[0][i] < sum[1][i])
				floodCnt++;    // 초기 홍수
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			switch (Integer.parseInt(st.nextToken())) {
				case 1:
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					union(x, y);
					break;
				case 2:
					sb.append(floodCnt).append("\n");
					break;
			}
		}

		System.out.println(sb);
	}

	static boolean isFloodRoot(int r) {
		return sum[1][r] > sum[0][r];
	}

	static void union(int a, int b) {
		int na = find(a); // 2
		int nb = find(b); // 3
		if (na == nb)
			return;

		// 합치기 전 : 홍수나는 상황이면 제거
		if (isFloodRoot(na))
			floodCnt -= size[na]; // fc 0
		if (isFloodRoot(nb))
			floodCnt -= size[nb];

		// 작은 트리를 큰 트리 밑에 붙이기위해 : na를 부모로
		if (size[na] < size[nb]) {
			int tmp = na;
			na = nb;
			nb = tmp;
		}

		parent[nb] = na;
		size[na] += size[nb];
		sum[0][na] += sum[0][nb];
		sum[1][na] += sum[1][nb];

		if (isFloodRoot(na))
			floodCnt += size[na];
	}

	static int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}
}

```

---

## **✍️ 회고**

- 유니온 파인드 어렵다. . .

---

## **🧩 다른 풀이 (선택)**

```java

```

---
