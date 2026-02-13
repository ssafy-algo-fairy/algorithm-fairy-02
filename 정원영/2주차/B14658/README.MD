Ref #79

# 🌠 BOJ 14658 - 하늘에서 별똥별이 빗발친다

## 📌 문제 설명

N × M 크기의 평면 위에 K개의 별이 떨어진다.  
한 변의 길이가 L인 정사각형 그물을 한 번 던져서  
최대한 많은 별을 잡으려고 한다.

잡지 못한 별의 최소 개수를 구하는 문제이다.

---

## 💡 풀이 아이디어

### 🔍 접근
- 우선 모서리에 별이 위치해야한다는 것은 바로 캐치했다.
  - 모서리에 있는게 최대한 트램펄린을 활용하는 거니까.
- 근데 네 모서리를 모두 따라서 돌리게 되면 시간복잡도 40억이 나왔다.
- 분명 K가 100인 것을 활용해야하는데 정확한 방법이 떠오르지 않았다.
- 그래서 한참 고뇌를 하다가 선규님한테 힌트를 살짝 받았다.
  - 그래그래 K를 활용하도록 해야지. -> 역시나 맞았다.
  - 한참 고뇌 한 끝에 떠올랐다. 결국 별 두개가 모서리에 있으면 나올 수 있는 트램펄린이 하나라는거
- 약간 그 두점을 지나는 원은 하나 밖에 없다. 이런 느낌이랄까
- 한점이 왼쪽 벽에 있다 + 한점이 오른쪽 벽에 있다 -> 두가지 경우로 나눠서 구함

---

## ⚠️ 복기
- 자꾸 한 끗이 부족하다.
- 생각하는 힘이 부족한건가? 어디가 문제이고 어떻게 해결해야할 지 감은 오는데
- 정확히 어떻게 구현해야할 지 감이 잘 안온다. 날로 먹을려고 쉬운 쪽으로 고민 하는 것 같기도
---

## 💻 구현 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14658 {

    static int n, m, l, k;

    static Star[] stars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        stars = new Star[k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i] = new Star(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int result = k;
        for (Star standard : stars) {
            int xu = standard.x + l, xd = standard.x;
            int yu = standard.y + l, yd = standard.y - l;

            ArrayList<Star> alive = new ArrayList<>();
            for (Star star : stars) {
                if (star.x < xd || xu < star.x) continue;
                if (star.y < yd || yu < star.y) continue;
                alive.add(star);
            }
            result = Math.min(result, k - reflect(alive, standard));


            xu = standard.x;
            xd = standard.x - l;
            yu = standard.y + l;
            yd = standard.y - l;

            alive = new ArrayList<>();
            for (Star star : stars) {
                if (star.x < xd || xu < star.x) continue;
                if (star.y < yd || yu < star.y) continue;
                alive.add(star);
            }
            result = Math.min(result, k - reflect(alive, standard));

        }
        System.out.println(result);
    }

    static int reflect(ArrayList<Star> alive, Star standard) {
        int temp = 0;
        for (Star aStandard : alive) {
            int uy = aStandard.y;
            int dy = aStandard.y - l;
            if (uy < standard.y) continue;

            int count = 0;
            for (Star star : alive) {
                if (star.y < dy || uy < star.y) continue;
                count++;
            }
            temp = Math.max(temp, count);
        }
        return temp;
    }

    static class Star {
        int x, y;

        public Star(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

```
