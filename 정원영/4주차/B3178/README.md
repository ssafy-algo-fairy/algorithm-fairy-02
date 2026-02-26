## **🔍 문제 요약**

- 2K개 글자로 이루어진 단어 N개
- **첫 K개 글자에서 나누어질 수 있고, 마지막 K개 글자에서 만날 수 있다**

<img width="772" height="229" alt="image" src="https://github.com/user-attachments/assets/f3a610fb-ff3f-42df-ab20-aa5cafaff68f" />
<img width="969" height="495" alt="image" src="https://github.com/user-attachments/assets/abc84ccc-bd71-4fa7-b66d-bd2323be9762" />

→ 아래 예시는 마지막 4개 글자에서 만나야하는데 그전에 만남 (D) + 첫 K개에서 나뉘어져야하는데 그 후에 갈라짐 (E)

- 1 ≤ N ≤ 10,000, 1 ≤ K ≤ 100
- 정점의 수가 가장 적은 코코스의 정점의 수 출력

---

## **💡문제 접근 / 풀이 전략**
- 사실 보자마자 그리디라는걸 온몸으로 느꼈다.
- 하지만 트라이말고 방법이 생각나지 않았다.
- 그냥 트라이로 풀어버렸다..
- 반나눠서 앞뒤로 트라이 만들고 원소 갯수를 세면 된다
- 근데 MAP을 쓰니까 메모리 초과 난다.. 그래서 배열로 바꿔줬더니 통과됐다.
- 뭔가 이상하다

## **✍️ 회고**

- 다른 사람 풀이 봣는데 벽느꼈다. 정렬을 생각해낸다고? 
- 풀이 자체가 어려운게 아닌데 그걸 떠올리는게 진짜 ㄹㅈㄷ
- "그걸 보자 정원영은 벽을 느껴버렸습니다."

---


## **✅코드 & 소요 시간**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 트라이 + 그냥 갯수 세기
 */
public class BOJ_3178 {
    static int n, k;
    static char[][] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        words = new char[n][2 * k];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine().toCharArray();
        }

        int result = 0;

        //앞에서 트라이 만들기
        Node root = new Node(' ');
        for (int i = 0; i < n; i++) {
            Node cur = root;
            for (int j = 0; j <= k - 1; j++) {
                if (cur.next[words[i][j] - 'A'] == null) cur.next[words[i][j] - 'A'] = new Node(words[i][j]);
                cur = cur.next[words[i][j] - 'A'];
            }
        }

        result += countTrie(root);

        //뒤에서 트라이 만들기
        root = new Node(' ');
        for (int i = 0; i < n; i++) {
            Node cur = root;
            for (int j = 2 * k - 1; j >= k; j--) {
                if (cur.next[words[i][j] - 'A'] == null) cur.next[words[i][j] - 'A'] = new Node(words[i][j]);
                cur = cur.next[words[i][j] - 'A'];
            }
        }

        result += countTrie(root);

        System.out.println(result);
    }

    static int countTrie(Node cur) {
        int count = 0;
        for (Node n : cur.next) {
            if (n == null) continue;
            count += countTrie(n);
            count++;
        }

        return count;
    }

    static class Node {
        char c;
        Node[] next = new Node[26];

        public Node(char c) {
            this.c = c;
        }
    }
}


```
