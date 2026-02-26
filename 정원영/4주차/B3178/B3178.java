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
