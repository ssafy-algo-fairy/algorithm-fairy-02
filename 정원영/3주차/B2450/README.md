# 🔁 BOJ 2450 - 모양 정돈

- 🔗 문제 링크: https://www.acmicpc.net/problem/2450
- 🏷️ 분류: 완전탐색(순열 3!), 카운팅/그리디(교환 + 3-cycle), 구현

---

## 📌 문제 요약
길이 `N`의 배열에 `1, 2, 3`만 존재한다.  
배열을 “같은 숫자끼리 연속하게” 정렬하되, 숫자 블록의 순서(예: `1→2→3` 또는 `2→1→3` 등)는 자유다.

한 번의 연산은 **서로 다른 두 위치의 값을 swap(자리 바꾸기)** 하는 것이라고 할 때,  
목표 형태(세 블록이 연속)로 만들기 위한 **최소 swap 횟수**를 구한다.

---

## 💡 핵심 아이디어
- 모양이 3가지라 나올수 있는 경우의 수가 6!임
- 모양의 순서를 정하고 모양의 갯수가 정해져있으니, 들어갈 위치가 정해짐
- 배열을 순회하며 잘못된 자리에 들어간 모양을 찾음
- 해당 모양이 어떤 구간에 위치해야하는지 배열에 저장함
  - `wrongCount[1][3]` 이라면 1번 자리에서 3번으로 가야하는 모양의 개수임
- 1,3 이랑 3,1은 자리만 바꾸면 되서 1번이면 됨
- 그렇게 모두 바꾸고 남은 것들은 두번에 걸쳐서 제자리로 갈 수 있음
  - 해당 갯수들은 모두 같음. 서로가 서로에게 그런 존재니까..

---

그래서 swap 수를 어떻게 구하냐..
```java
int count = 0;
count += Math.min(wrongCount[0][1], wrongCount[1][0]);
wrongCount[0][1] -= Math.min(wrongCount[0][1], wrongCount[1][0]);

count += Math.min(wrongCount[0][2], wrongCount[2][0]);
wrongCount[0][2] -= Math.min(wrongCount[0][2], wrongCount[2][0]);

count += Math.min(wrongCount[1][2], wrongCount[2][1]);
wrongCount[1][2] -= Math.min(wrongCount[1][2], wrongCount[2][1]);

count += (wrongCount[0][1] + wrongCount[0][2]) * 2;
```

서로서로 바꿀 수 있는 거 다 바꾸고, 남은 것들은 세개씩 묶어서 계산

## 💡코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2450 {
    static int n;
    static int[] arr;
    static int[] count;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        result = Integer.MAX_VALUE;
        boolean[] visited = new boolean[4];
        count = new int[4];
        for (int i = 0; i < n; i++) count[arr[i]]++;

        permutation(visited, 0, new int[3]);

        System.out.println(result);
    }

    static void permutation(boolean[] visited, int cnt, int[] order) {
        if (cnt == 3) {
            make(order);
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (visited[i]) continue;
            order[cnt] = i;
            visited[i] = true;
            permutation(visited, cnt + 1, order);
            visited[i] = false;
            order[cnt] = 0;
        }
    }

    //order 순서대로 Idx 부터 배치
    static void make(int[] order) {
        int[][] wrongCount = new int[3][3];

        int first = 0, last = count[order[0]];
        for (int i = 0; i < 3; i++) {
            for (int j = first; j < last; j++) {
                if (arr[j] == order[i]) continue;
                if (arr[j] == order[(i + 1) % 3]) wrongCount[i][(i + 1) % 3]++;
                if (arr[j] == order[(i + 2) % 3]) wrongCount[i][(i + 2) % 3]++;
            }

            if (i != 2) {
                first = last;
                last += count[order[i + 1]];
            }
        }

        int count = 0;
        count += Math.min(wrongCount[0][1], wrongCount[1][0]);
        wrongCount[0][1] -= Math.min(wrongCount[0][1], wrongCount[1][0]);

        count += Math.min(wrongCount[0][2], wrongCount[2][0]);
        wrongCount[0][2] -= Math.min(wrongCount[0][2], wrongCount[2][0]);

        count += Math.min(wrongCount[1][2], wrongCount[2][1]);
        wrongCount[1][2] -= Math.min(wrongCount[1][2], wrongCount[2][1]);

        count += (wrongCount[0][1] + wrongCount[0][2]) * 2;

        result = Math.min(result, count);
    }


}

```
