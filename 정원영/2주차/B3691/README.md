🔍 문제 요약
상근이의 예산으로 가장 좋은 컴퓨터 맞추려고함

컴퓨터 성능 = 가장 안좋은 부품의 성능 → 가장 안좋은 부품의 성능이 최고인 경우를 구한다

각 부품은 하나씩만 사용하되, 모두 써야함

1 ≤ n 부품의 개수≤ 1,000

1 ≤ b 예산 ≤ 1,000,000,000

부품 정보 : type name price quality

0 ≤ price ≤ 1,000,000
0 ≤ quality ≤ 1,000,000,000

💡문제 접근 / 풀이 전략
이분 탐색이라는걸 mm에서 봐버려서 바로 풀어버렸다..
- quality에 재한을 두고 해당 quality보다 높은 부품 중 가장 싼 걸 고르는 방식
- 해당 quality로 만들 수 있다면 quality를 높여서 재시도
- 가능한 quality를 이분탐색으로 찾기

✅ 코드 & 소요 시간
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_3691 {

    static int n, b;
    static HashMap<String, ArrayList<Spec>> parts;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            parts = new HashMap<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String type = st.nextToken();
                String name = st.nextToken();
                int price = Integer.parseInt(st.nextToken());
                int quality = Integer.parseInt(st.nextToken());
                parts.putIfAbsent(type, new ArrayList<>());

                parts.get(type).add(new Spec(price, quality));
            }

            result = Integer.MIN_VALUE;
            findResult();
            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }

    static void findResult() {
        int left = 0, right = 1000000000;

        while (left <= right) {
            int targetQuality = (left + right) / 2;

            boolean canMake = make(targetQuality);

            if (canMake) {
                left = targetQuality + 1;
                result = Math.max(result, targetQuality);
            } else {
                right = targetQuality - 1;
            }
        }
    }

    static boolean make(int minQuality) {
        int sum = 0;
        for (ArrayList<Spec> specs : parts.values()) {
            int minPrice = Integer.MAX_VALUE;
            for (Spec spec : specs) {
                if (spec.quality >= minQuality) {
                    minPrice = Math.min(minPrice, spec.price);
                }
            }
            if (minPrice == Integer.MAX_VALUE) return false;
            sum += minPrice;
        }

        if (sum <= b) return true;
        else return false;
    }

    static class Spec {
        int price, quality;

        public Spec(int price, int quality) {
            this.price = price;
            this.quality = quality;
        }
    }
}

```

✍️ 회고
처음에 딱 봤을때 몰랐는데 숫자가 저렇게 크면 logn만에 풀어야한다.
dp도 logn이 들고 배열 크기도 많이 드니까..
범위에서 힌트를 얻자

Ref #63
