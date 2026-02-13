# 백준 20955번: 민서의 응급 수술

## 📌 문제 개요

N개의 컴퓨터와 M개의 연결 정보가 주어진다.  
현재 그래프를 **트리 구조**로 만들기 위해 필요한 최소 연산 횟수를 구하는 문제이다.

연산은 다음 두 가지이다:

- 이미 연결된 두 컴퓨터를 다시 연결하는 경우 → 간선 제거
- 서로 다른 집합을 연결하는 경우 → 간선 추가

즉,  
1. 사이클을 제거하고  
2. 여러 연결 요소를 하나로 합쳐  
최종적으로 **트리(간선 수 = N-1, 연결 요소 1개)** 를 만들어야 한다.

---

## 🔎 해결 아이디어

### 1️⃣ Union-Find (Disjoint Set)

- 이미 같은 부모를 가지는 두 노드를 union 하면 → 사이클 발생 → 제거 필요 → count++
- 모든 union 수행 후, 연결 요소 개수를 센다.
- 연결 요소가 K개라면 하나로 만들기 위해 (K - 1)번 연결 필요 → count += (K - 1)

---

## 🧠 핵심 로직

- 사이클 발생 횟수 + (연결 요소 개수 - 1)

---

## 💻 Java 코드

```java
package week1.B20955;

import java.util.*;
import java.io.*;

public class Main {
    
    static int[] index;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        index = new int[n+1];
        
        for (int i = 1; i <= n; i++) {
            index[i] = i;
        }
        
        count = 0;
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
        
        boolean first = true;
        for (int i = 1; i <= n; i++) {
            if (index[i] == i) {
                if (first) first = false;
                else count++;
            }
        }
        
        System.out.print(count);
        
    }
    
    public static void union(int a, int b) {
        int p1 = find(a);
        int p2 = find(b);
        
        if (p1 == p2) {
            count++;
            return;
        }
        
        index[p2] = p1;
    }
    
    public static int find(int a) {
        if (index[a] == a) return a;
        
        return index[a] = find(index[a]);
    }

}
```

---

## ⏱ 시간 복잡도

- Union-Find (경로 압축 적용)
- 거의 O(N + M)

---

## 📎 정리

- 사이클 제거 횟수
- 연결 요소를 하나로 만들기 위한 연결 횟수

두 값을 더한 것이 정답이다.
