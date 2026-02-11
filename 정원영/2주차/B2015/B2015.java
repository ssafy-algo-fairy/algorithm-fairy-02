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
