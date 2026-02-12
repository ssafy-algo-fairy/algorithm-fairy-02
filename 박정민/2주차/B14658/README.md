# 🌠 BOJ 14658 - 하늘에서 별똥별이 빗발친다

## 📌 문제 설명

N × M 크기의 평면 위에 K개의 별이 떨어진다.  
한 변의 길이가 L인 정사각형 그물을 한 번 던져서  
최대한 많은 별을 잡으려고 한다.

잡지 못한 별의 최소 개수를 구하는 문제이다.

---

## 💡 풀이 아이디어

### 🔍 핵심 관찰

그물의 왼쪽 아래 꼭짓점 `(x, y)`는  
별의 `x좌표`와 `y좌표` 중 하나로 설정해도 충분하다.

이유는 다음과 같다.

- 최적의 위치라면 적어도 하나의 별이 그물의 경계에 걸리도록 이동시킬 수 있다.
- 따라서 모든 좌표를 탐색할 필요 없이,
  별들의 좌표만 후보로 사용하면 된다.

즉,

- `x` 후보 → 모든 별의 x좌표
- `y` 후보 → 모든 별의 y좌표

이 두 가지를 조합하면 모든 경우를 탐색할 수 있다.

---

## 🧠 알고리즘

1. 별의 좌표를 입력받는다.
2. 모든 `(star[i].x, star[j].y)` 조합을 그물의 시작점으로 설정한다.
3. 해당 위치에 그물을 놓았을 때 포함되는 별 개수를 센다.
4. 최대 포함 개수를 갱신한다.
5. `k - max` 를 출력한다.

---

## ⏱ 시간복잡도

- 바깥 2중 반복문 → `O(k²)`
- 내부 별 개수 세기 → `O(k)`

총 시간복잡도는 `O(k³)` 이다.

문제 조건에서 `K ≤ 100` 이므로

`100³ = 1,000,000`

정도의 연산으로 충분히 해결 가능하다.

---

## ⚠️ 구현 시 주의점

그물의 한 변의 길이가 `L`이므로  
좌표 범위는 **양 끝을 포함**한다.

조건은 다음과 같다.

`x ≤ star.x ≤ x + L`  
`y ≤ star.y ≤ y + L`

경계 포함 여부를 잘못 처리하면 오답이 발생한다.

---

## 💻 구현 코드

```java
package B14658;

import java.util.*;
import java.io.*;

public class Main {
    static int[][] star;
    static int n, m, l, k;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        star = new int[k][2];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            star[i][0] = Integer.parseInt(st.nextToken());
            star[i][1] = Integer.parseInt(st.nextToken());
        }

        max = 0;

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                run(star[i][0], star[j][1]);
            }
        }

        System.out.print(k - max);
    }

    public static void run(int x, int y) {
        int count = 0;

        for (int i = 0; i < k; i++) {
            if (star[i][0] < x || star[i][0] > x + l ||
                star[i][1] < y || star[i][1] > y + l) continue;

            count++;
        }

        max = Math.max(max, count);
    }
}
