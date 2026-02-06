## **🔍 문제 요약**

- 각 섬에서 1번 섬으로 가는 경로는 유일하다
    - 양들은 1번 섬으로 가는 경로로 이동하며, 늑대들은 본인 섬에서 양 잡아먹음
    - 양을 항상 잡을 수 있고 늑대 한마리 = 양 한마리만 잡아먹음
- 얼마나 많은 양이 1번 섬에 도달가능한지

- 1 ~ N번 섬 존재 (2 ≤ *N* ≤ 123,456)
- t : w(늑대), s(양)
- a : 개체수 (1 ≤ a ≤ 10^9) → 오버플로우 주의
- p : 어떤 곳과 연결되어 있는지 (1≤ p ≤ N)

---

## **💡문제 접근 / 풀이 전략**

- 첫번째 시도 : DFS → 경로에 늑대를 저장하면서 체크하려했는데 오류 😦
    
    ```java
    package boj16437;
    
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.ArrayList;
    import java.util.StringTokenizer;
    
    public class Main {
    
    	static int N;
    	static ArrayList<Integer>[] island;
    	static String[][] live;
    	static long sum;
    	static int [] wolves;
    
    	public static void main(String[] args) throws IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    		N = Integer.parseInt(br.readLine());
    		island = new ArrayList[N + 1];
    		live = new String[N + 1][2];
    		wolves = new int [N+1];
    
    		for (int i = 1; i <= N; i++) {
    			island[i] = new ArrayList<>();
    		}
    
    		for (int i = 2; i <= N; i++) {
    			StringTokenizer st = new StringTokenizer(br.readLine());
    
    			live[i][0] = st.nextToken();	// 종류
    			live[i][1] = st.nextToken();	// 마리수
    
    			island[Integer.parseInt(st.nextToken())].add(i);
    		}
    
    		live[1][0] = "S";
    		live[1][1] = "0";
    
    		dfs(1);
    
    		System.out.println(sum);
    
    	}
    
    	static void dfs(int start){
    		if(live[start][0].equals("S")){	// 양인 경우 해당 경로의 늑대 수 만큼 뻄
    			int sheep = Integer.parseInt(live[start][1]);
    			for(int i = 2; i < start; i++){
    				sheep -= wolves[i];
    			}
    			if(sheep > 0)	sum += sheep;
    		}else{	// 늑대인 경우 체크
    			wolves[start] = Integer.parseInt(live[start][1]);
    		}
    
    		// 리프노드
    		if(island[start].isEmpty()){
    			return;
    		}
    
    		for(int i = 0; i < island[start].size(); i++){
    			dfs(island[start].get(i));
    			wolves[island[start].get(i)] = 0;
    		}
    	}
    
    }
    
    ```
    

⇒ 리프노드에서부터 탐색

- 애초에 마지막 노드로 이동 후
    - 양이면 더하고
    - 늑대면 뺌

---

## **✅ 코드 & 소요 시간**

```java
package boj16437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static ArrayList<Integer>[] island;
	static char[] type;    // S or W
	static long[] cnt;    // 마리 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		island = new ArrayList[N + 1];
		type = new char[N + 1];
		cnt = new long[N + 1];

		for (int i = 1; i <= N; i++) {
			island[i] = new ArrayList<>();
		}

		type[1] = 'S';
		cnt[1] = 0;
		for (int i = 2; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			type[i] = st.nextToken().charAt(0);
			cnt[i] = Long.parseLong(st.nextToken());
			island[Integer.parseInt(st.nextToken())].add(i);
		}

		System.out.println(dfs(1));

	}

	static long dfs(int u) {
		long sum = 0;

		// 먼저 리프노드까지 이동
		for (int v : island[u]) {
			sum += dfs(v);
		}

		if (type[u] == 'S') {    // 양이면 누적
			sum += cnt[u];
		} else {    // 늑대면 감소
			sum -= cnt[u];
			if (sum < 0)
				sum = 0;
		}

		return sum;
	}

}

```

73924kb / 1200ms

---

## **✍️ 회고**

- 그렇게 어려운 문제가 아닌거같은데 접근을 잘못해서 오래걸렸다…

---

## **🧩 다른 풀이 (선택)**

```java

```

---
