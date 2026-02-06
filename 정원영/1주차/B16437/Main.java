import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_16437 {

    static ArrayList<Integer>[] child;
    static int[] sheep;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        sheep = new int[n + 1];
        child = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            child[i] = new ArrayList<>();
        }

        for (int i = 2; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String t = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            if ("S".equals(t)) sheep[i] = a;
            else sheep[i] = -a;
            child[p].add(i);
        }

        System.out.print(findSheep(1));
    }

    static long findSheep(int cur) {
        long sum = sheep[cur];
        for (int next : child[cur]) {
            sum += findSheep(next);
        }
        if (sum < 0) return 0;

        return sum;
    }
}
