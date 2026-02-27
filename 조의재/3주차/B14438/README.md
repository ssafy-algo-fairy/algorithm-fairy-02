# [백준 14438] 수열과 쿼리 17 - Java 풀이

## 1. 문제 정보
- **문제 번호:** 14438
- **문제 이름:** 수열과 쿼리 17
- **알고리즘:** 자료구조, 세그먼트 트리 (Segment Tree)
- **난이도:** 골드 I
- **핵심:** 수열의 값 변경(Point Update)과 특정 구간의 최솟값(Range Minimum Query)을 $O(\log N)$에 처리

---

## 2. 코드 상세 분석

### 2.1. 기본 구조 및 입력 처리
`BufferedReader`와 `StringTokenizer`를 사용하여 대량의 입력을 빠르게 처리하고, `StringBuilder`로 출력을 모아서 처리합니다. 세그먼트 트리의 크기는 $2^k \ge N$을 만족하는 최소 크기의 2배로 할당하여 메모리 낭비를 줄였습니다.

```java
import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] arr, tree;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        inputFile();
        System.out.println(sb.toString().trim());
    }

    public static void inputFile() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        
        // 트리 사이즈 계산: 2^k 형태로 넉넉하게 할당
        int size = 1;
        while(size < N){
            size <<= 1;
        }
        size <<= 1;
        tree = new int[size];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // 트리 생성
        makeTree(1, 1, N);

        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            if(type == 1){
                // 1번 쿼리: 값 변경
                changeVal(1, left, right, 1, N);
            }
            else{ 
                // 2번 쿼리: 구간 최솟값 조회
                sb.append(findNode(1, 1, N, left, right)).append("\n");
            }
        }
    }
```

### 2.2. 세그먼트 트리 초기화 (`makeTree`)
리프 노드에는 배열의 실제 값을 저장하고, 부모 노드에는 자식 노드 중 **더 작은 값(Min)**을 저장하여 트리를 구성합니다.

```java
    static void makeTree(int node, int start, int end){
        if(start == end){
            tree[node] = arr[start];
            return;
        }
        
        int mid = (start + end) / 2;
        makeTree(node*2, start, mid);
        makeTree(node*2+1, mid+1, end);

        tree[node] = Math.min(tree[node*2], tree[node*2+1]);
    }
```

### 2.3. 값 변경 (`changeVal`)
특정 인덱스의 값이 바뀌면, 해당 리프 노드를 찾아 값을 변경하고 역으로 올라오면서 부모 노드들의 최솟값을 갱신합니다.

```java
    static void changeVal(int node, int idx, int val, int start, int end){
        // 범위를 벗어난 경우 무시
        if(end < idx || idx < start){
            return;
        }
        // 리프 노드에 도달하면 값 변경
        if(start == end){
            tree[node] = val;
            return;
        }

        int mid = (start+end)/2;
        changeVal(node*2, idx, val, start, mid); 
        changeVal(node*2+1, idx, val, mid+1, end);
        
        // 변경된 자식 값들을 이용해 부모 노드 갱신
        tree[node] = Math.min(tree[node*2], tree[node*2+1]);
    }
```

### 2.4. 구간 최솟값 조회 (`findNode`)
요청된 범위(`left ~ right`)에 포함되는 노드들만 탐색하여 최솟값을 반환합니다. 범위 밖의 노드는 `Integer.MAX_VALUE`를 반환하여 최솟값 비교에 영향을 주지 않도록 처리했습니다.

```java
    static int findNode(int node, int start, int end, int left, int right){
        // 범위 밖인 경우
        if(end < left || right < start){
            return Integer.MAX_VALUE;
        }
        // 범위 안에 완전히 포함된 경우
        if(left <= start && end <= right){
            return tree[node];
        }

        int mid = (start+end)/2;
        int lNum = findNode(node*2, start, mid, left, right);
        int rNum = findNode(node*2+1, mid+1, end, left, right);

        return Math.min(lNum, rNum);
    }
}
```

Refs #101