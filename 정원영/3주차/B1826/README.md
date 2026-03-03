### 📋 문제 개요
- **알고리즘 유형**: 그리디 알고리즘 (Greedy), 자료구조 (우선순위 큐)
- **문제 핵심**: 시작점에서 목적지까지 이동하는 동안 최소한의 횟수로 주유소를 방문해야 합니다. 현재 보유한 연료로 갈 수 있는 주유소들을 모두 파악한 뒤, 그중에서 **가장 많은 연료를 보충할 수 있는 곳을 우선적으로 선택(Greedy)**하여 이동 거리를 갱신하는 것이 핵심입니다.

---

### 💡 풀이 로직
- 매번 주유소를 갈지 안갈지 판단할 필요가 없음
- 최대한 가고 더 못한다? -> 아 사실 앞에서 기름 넣었어요~~ 라고 하면 됨
- 지나온 것 중에 가장 많이 넣을 수 있는 곳에서 넣었어요~~ 하면 됨
---

### 💻 코드 주요 부분 설명
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1826_1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<GasStation> q = new PriorityQueue<>((a, b) -> Integer.compare(a.pos, b.pos));
        PriorityQueue<GasStation> passed = new PriorityQueue<>((a, b) -> Integer.compare(b.fuel, a.fuel));
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int fuel = Integer.parseInt(st.nextToken());
            q.add(new GasStation(pos, fuel));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int cur = 0;
        int dest = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());

        int count = 0;
        while (true) {
            cur += fuel;
            if (cur >= dest) break;
            while (!q.isEmpty() && q.peek().pos <= cur) {
                passed.add(q.poll());
            }

            if (passed.isEmpty()) {
                count = -1;
                break;
            }

            fuel = passed.poll().fuel;
            count++;
        }

        System.out.println(count);
    }

    static class GasStation {
        int pos, fuel;

        public GasStation(int pos, int fuel) {
            this.pos = pos;
            this.fuel = fuel;
        }
    }
}

```

Refs #135 
