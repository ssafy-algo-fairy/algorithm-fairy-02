## **🔍 문제 요약**

- 1 ≤ i ≤ j ≤ N 정수에 대해 A[i]부터  A[j]까지의 부분합이 주어짐
- 전체 부분합 개수는 N * (N+1) / 2
- 이중 주어진 합을 만족하는 부분합이 몇개일까

- 1 ≤ N ≤ 200,000
- |K| ≤ 2,000,000,000

---

## **💡문제 접근 / 풀이 전략**

**시도들…**
- 그냥 누적합 for 문 두개 돌렸다가 아니라 다를까 시간초과..
- 예상했지만 아이디어가 떠오르지 않음

정확한 k를 요구한다는 걸 캐치했어야 함.. 비교 해볼 필요가 없으니까

**⭐ 접근 포인트**

- 그냥 앞에서 나온 누적합을 map에 넣어뒀다가 조건이 만족하는걸 O(1)만에 찾아내는게 핵심
- 해당 아이디어만 있으면 일반적인 누적합
- 코드를 깔끔하게 짜둬서 봐두면 좋을 듯..?

---

## **✅ 코드 & 소요 시간**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_2015 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long result = 0;
        HashMap<Long, Long> sums = new HashMap<>();
        sums.put(0L, 1L);
        long sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            sum += Long.parseLong(st.nextToken());

            result += sums.getOrDefault(sum - k, 0L);

            sums.put(sum, sums.getOrDefault(sum, 0L) + 1);
        }

        System.out.println(result);
    }
}


```

32212 KB / 296ms

---

## **✍️ 회고**

- 채은님 리드미를 슬쩍 봤는데 map 어쩌고 하길래 힌트를 얻어서 풀었습니다...
- 아직 갈길이 멀다.. 
- 생각지도 못했다. 체크해두고 다음에 한번 더...

  ref #70  
