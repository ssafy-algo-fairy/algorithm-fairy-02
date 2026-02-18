# 🗺️ 백준 23326번 – 홍익 투어리스트

## 📌 문제 요약

원형으로 배치된 `n`개의 관광지 중 일부는 **명소(1)**, 나머지는 **일반 장소(0)**이다.  
도현이는 현재 위치에서 시작해 다음과 같은 `q`개의 쿼리를 처리해야 한다.

### 쿼리 종류
1. **명소 토글**  
   - 특정 위치의 값을 `0 ↔ 1`로 변경
2. **이동**  
   - 현재 위치에서 시계 방향으로 `k`칸 이동 (원형)
3. **탐색**  
   - 현재 위치부터 시계 방향으로 가장 가까운 명소(1)까지의 거리 출력  
   - 없다면 `-1` 출력

---

## ❗ 문제의 핵심

- 배열이 **원형 구조**
- 명소의 상태가 **동적으로 변경**
- 탐색 쿼리가 많음 (`n ≤ 500,000, q ≤ 100,000`)

👉 매 쿼리마다 배열 전체를 순회하면 **시간 초과**

---

## 💡 해결 아이디어

### 핵심 발상
> **“0은 필요 없고, 1이 있는 위치만 빠르게 찾자”**

- 명소(1)의 인덱스만 관리
- 정렬 + 범위 탐색이 가능한 자료구조 필요

👉 **`TreeSet` 사용**

---

## 🧠 자료구조 선택 이유 – TreeSet

`TreeSet`은 다음 연산을 모두 `O(log n)`에 처리할 수 있다.

- `add(x)` : 명소 추가
- `remove(x)` : 명소 제거
- `ceiling(x)` : x 이상 중 가장 가까운 명소
- `first()` : 가장 앞에 있는 명소

원형 구조도 쉽게 처리 가능하다.

---

## 🛠️ 구현 전략

### 초기화
- 입력 배열을 순회하며 값이 `1`인 인덱스를 `TreeSet`에 저장

### 쿼리 처리
- **1번 (토글)**  
  - 배열 값 변경 + TreeSet에 추가/삭제
- **2번 (이동)**  
  - 현재 위치를 `(현재 + k) % n`으로 갱신
- **3번 (탐색)**  
  - `ceiling(현재 위치)`로 다음 명소 탐색  
  - 없다면 원형이므로 `first()` 사용

---

## ⏱️ 시간복잡도 분석

| 쿼리 | 시간복잡도 |
|----|----|
| 1번 | `O(log n)` |
| 2번 | `O(1)` |
| 3번 | `O(log n)` |

👉 전체 시간복잡도: **`O(q log n)`**

---

## ✅ 최종 코드

```java
package B23326;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        
        int[] location = new int[n];
        TreeSet<Integer> s = new TreeSet<>();
        int dohyeon = 0;
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            location[i] = Integer.parseInt(st.nextToken());
            if (location[i] == 1) s.add(i);
        }
        
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            
            switch (query) {
                case 1:
                    int x = Integer.parseInt(st.nextToken()) - 1;
                    if (location[x] == 0) {
                        location[x] = 1;
                        s.add(x);
                    } else {
                        location[x] = 0;
                        s.remove(x);
                    }
                    break;
                    
                case 2:
                    dohyeon = (dohyeon + Integer.parseInt(st.nextToken())) % n;
                    break;
                    
                case 3:
                    int output = -1;
                    if (!s.isEmpty()) {
                        Integer next = s.ceiling(dohyeon);
                        if (next == null) {
                            output = n - dohyeon + s.first();
                        } else {
                            output = next - dohyeon;
                        }
                    }
                    sb.append(output).append("\n");
                    break;
            }
        }
        System.out.print(sb);
    }
}
