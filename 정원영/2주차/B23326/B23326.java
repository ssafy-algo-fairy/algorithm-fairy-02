import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_23326 {

    static TreeSet<Integer> good;
    static int n, q;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        good = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == 0) continue;
            good.add(i);
        }

        int cur = 0;
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("1")) {
                int num = Integer.parseInt(st.nextToken());
                if (good.contains(num - 1)) good.remove(num - 1);
                else good.add(num - 1);
            } else if (cmd.equals("2")) {
                int num = Integer.parseInt(st.nextToken());
                cur = (cur + num) % n;
            } else {
                int nextIdx = findNext(cur);
                if (nextIdx == -1) System.out.println(-1);
                else if (cur <= nextIdx) System.out.println(nextIdx - cur);
                else System.out.println(n - cur + nextIdx);
            }
        }
    }

    static int findNext(int target) {
        Integer result = good.ceiling(target);
        if (result != null) return result;

        result = good.higher(-1);
        if (result != null) return result;

        return -1;
    }
}
