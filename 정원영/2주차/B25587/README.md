## 👻문제 요약
침수 조건: 각 도시는 강수량이 배수 용량보다 많으면 침수됨.
배수로 연결: 두 도시를 연결하면 배수 시설을 공유하는 하나의 그룹이 됨.
운명 공동체: 그룹 전체의 강수량 합이 배수 용량 합보다 크면, 그 그룹에 속한 모든 도시가 침수됨.
요구 사항: 배수로를 연결하는 명령과 현재 침수된 총 도시 수를 묻는 질문에 실시간으로 답해야 함.

## 👻접근 방식
- 처음엔 union-find로 합치고 N을 돌면서 홍수난거 뭐잇나 체크했음
- 근데 생각해보니 쿼리가 10만개 인데 N도 10만개 이니 n<sup>2</sup> 하면 시간초과임
- 그래서 핵심은 두가지임

## 👻핵심
- 홍수나는 지점을 result 변수에 초기화 해둠
- union 할 때 해당 지점이 홍수나던 지점이면 result에서 해당 집합의 크기만큼 빼줌
- 합치고 나서 홍수가 나면 다시 집합 크기만큼 더해줌
- 집합의 크기는 집합의 루트에 음수로 보관함
- 쿼리 날라올때 그냥 result를 출력해주면 됨

```java
static void union(int c1, int c2) {
        int p1 = find(c1);
        int p2 = find(c2);
        if (p1 == p2) return;

        int childCount1 = -parents[p1];
        int childCount2 = -parents[p2];

        //넘치고 있었다면 빼주기
        if (limits[p1] < rains[p1]) result -= childCount1;
        if (limits[p2] < rains[p2]) result -= childCount2;

        //강수량 합치기
        limits[p1] += limits[p2];
        rains[p1] += rains[p2];
        if (limits[p1] < rains[p1]) result += childCount1 + childCount2;

        //p1에 합쳐주고, p2의 부모를 p1으로
        parents[p1] -= childCount2;
        parents[p2] = p1;
    }
```

childCount가 해당 집합에서 원소가 몇개 있나를 음수로 표현한 것

## 👻전체 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_25587 {
    static int n, m, result;
    static int[] parents;
    static int[] limits;
    static int[] rains;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parents[i] = -1;
        }

        limits = new int[n + 1];
        rains = new int[n + 1];

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.arraycopy(input, 0, limits, 1, n);

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.arraycopy(input, 0, rains, 1, n);

        //범람 초기화
        result = 0;
        for (int i = 1; i <= n; i++) {
            if (limits[i] < rains[i]) result++;
        }

        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (input[0] == 2) {
                sb.append(result).append("\n");
            } else {
                union(input[1], input[2]);
            }
        }


        System.out.print(sb);
    }

    static int find(int child) {
        if (parents[child] < 0) return child; //루트면 return

        return parents[child] = find(parents[child]);
    }

    static void union(int c1, int c2) {
        int p1 = find(c1);
        int p2 = find(c2);
        if (p1 == p2) return;

        int childCount1 = -parents[p1];
        int childCount2 = -parents[p2];

        //넘치고 있었다면 빼주기
        if (limits[p1] < rains[p1]) result -= childCount1;
        if (limits[p2] < rains[p2]) result -= childCount2;

        //강수량 합치기
        limits[p1] += limits[p2];
        rains[p1] += rains[p2];
        if (limits[p1] < rains[p1]) result += childCount1 + childCount2;

        //p1에 합쳐주고, p2의 부모를 p1으로
        parents[p1] -= childCount2;
        parents[p2] = p1;
    }
}

```

