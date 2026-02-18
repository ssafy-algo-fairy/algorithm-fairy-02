# [BJ 23326] 홍익대학교 - Java

### 📋 문제 개요
- **알고리즘 유형**: 자료 구조, 이진 탐색 트리 (`TreeSet`)
- **문제 핵심**: 원형으로 연결된 홍익대학교 거리에서 명소의 위치를 관리하며, 현재 위치에서 시계 방향으로 가장 가까운 명소까지의 거리를 빠르게 구하는 문제입니다.

---

### 💡 풀이 로직
1. **데이터 구조**: 
   - `TreeSet<Integer> ts`: 명소가 있는 구역 번호를 오름차순으로 저장합니다. 이진 탐색 트리 기반이므로 추가, 삭제, 검색이 $O(\log N)$에 가능합니다.
   - `position`: 현재 플레이어의 위치를 저장합니다.
2. **쿼리 처리**: 
   - **1번 쿼리**: 해당 구역이 명소가 아니면 추가, 명소라면 삭제합니다 (`ts.contains`와 `add/remove` 사용).
   - **2번 쿼리**: 현재 위치를 시계 방향으로 이동시킵니다. 원형 구조이므로 `(position + amount) % N` 연산을 수행합니다.
   - **3번 쿼리 (핵심)**: 
     - `ts.ceiling(position)`을 사용하여 현재 위치보다 크거나 같은 값 중 가장 작은 값을 찾습니다.
     - 만약 결과가 `null`이라면, 현재 위치 이후에는 명소가 없다는 뜻이므로 한 바퀴를 돌아 가장 번호가 작은 명소(`ts.first()`)를 타겟으로 잡습니다.
3. **효율성**: 명소의 위치를 정렬된 상태로 유지하며 `ceiling()`을 활용하므로 대규모 쿼리도 효율적으로 해결합니다.

---

### 💻 전체 코드

```java
import java.io.*;
import java.util.*;

public class Main {
    static int N, Q;
    static StringBuilder sb;
    static TreeSet<Integer> ts;

    public static void main(String args[]) throws IOException {
        readInput();
        System.out.print(sb);
    }

    public static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        ts = new TreeSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            if (temp == 1) ts.add(i); // 명소 위치 저장 (0-indexed)
        }

        int position = 0; // 현재 위치

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());

            if (query == 1) {
                int target = Integer.parseInt(st.nextToken()) - 1;
                if (ts.contains(target)) {
                    ts.remove(target);
                } else {
                    ts.add(target);
                }
            } 
            else if (query == 2) {
                int amount = Integer.parseInt(st.nextToken());
                position = (position + amount) % N;
            } 
            else { // 3번 쿼리: 거리 측정
                if (ts.isEmpty()) {
                    sb.append("-1\n");
                } else {
                    // 현재 위치보다 뒤에 있는 명소 중 가장 가까운 것
                    Integer targetPos = ts.ceiling(position);
                    
                    if (targetPos == null) {
                        // 뒤에 없으면 한 바퀴 돌아 첫 번째 명소까지의 거리 계산
                        int dist = (N - position) + ts.first();
                        sb.append(dist).append("\n");
                    } else {
                        // 바로 찾은 경우의 거리
                        sb.append(targetPos - position).append("\n");
                    }
                }
            }
        }
    }
}