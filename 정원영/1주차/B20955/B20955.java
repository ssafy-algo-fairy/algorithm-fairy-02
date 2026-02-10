import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20955 {

    static int n;
    static long m;
    static int[] parent;
    static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        result = 0;
        for (long i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        for (int i = 1; i <= n; i++) {
            if (parent[i] == i) result++;
        }

        System.out.println(result - 1);
    }

    static int find(int child) {
        int root = child;
        while (root != parent[root]) {
            root = parent[root];
        }

        while (child != root) {
            int temp = parent[child];
            parent[child] = root;
            child = temp;
        }

        return root;
    }

    static void union(int c1, int c2) {
        int p1 = find(c1);
        int p2 = find(c2);

        if (p1 == p2) result++;
        parent[p1] = p2;
    }
}
