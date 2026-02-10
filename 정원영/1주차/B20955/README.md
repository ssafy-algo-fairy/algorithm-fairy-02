## **🔍 문제 요약**
- N개의 정점과 M개의 간선으로 이루어진 뉴런 구조가 주어진다.
- 주어진 구조를 하나의 트리(Tree) 형태로 개조해야 한다.
  - 트리의 조건: 모든 정점이 연결되어 있어야 하며, 사이클(Cycle)이 없어야 함.

- 사용할 수 있는 연산:
  - 두 뉴런 연결하기 (간선 추가)
  - 이미 연결된 두 뉴런 끊기 (간선 제거)

모든 뉴런을 하나의 트리로 만들기 위한 최소 연산 횟수를 출력.
---

## **💡문제 접근 / 풀이 전략**
- 기본적으로 유니온 파인드가 바로 떠오름
- 이어지지 않은건 이어줘야함 -> 집합의 개수를 파악해서 (집합개수 - 1) 해주면 됨
- find로 이미 연결된 노드면 끊어줘야함 -> 결과 + 1

---

## **✅코드 & 소요 시간**

15분 정도 걸림

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20955 {

    static int n;
    static long m;
    static int[] parent;
    static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        result = 0;
        for (long i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        for (int i = 1; i <= n; i++) {
            if (parent[i] == i) result++;
        }

        System.out.println(result - 1);
    }

    static int find(int child) {
        int root = child;
        while (root != parent[root]) {
            root = parent[root];
        }

        while (child != root) {
            int temp = parent[child];
            parent[child] = root;
            child = temp;
        }

        return root;
    }

    static void union(int c1, int c2) {
        int p1 = find(c1);
        int p2 = find(c2);

        if (p1 == p2) result++;
        parent[p1] = p2;
    }
}

```

---

## **✍️ 회고**

- 초반에 문제 이해를 잘못했다. 문제를 꼼꼼히..

---

## **🧩 다른 풀이 (선택)**

```java

```

---
