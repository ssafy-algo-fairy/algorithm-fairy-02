import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_3691 {

    static int n, b;
    static HashMap<String, ArrayList<Spec>> parts;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            parts = new HashMap<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String type = st.nextToken();
                String name = st.nextToken();
                int price = Integer.parseInt(st.nextToken());
                int quality = Integer.parseInt(st.nextToken());
                parts.putIfAbsent(type, new ArrayList<>());

                parts.get(type).add(new Spec(price, quality));
            }

            result = Integer.MIN_VALUE;
            findResult();
            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }

    static void findResult() {
        int left = 0, right = 1000000000;

        while (left <= right) {
            int targetQuality = (left + right) / 2;

            boolean canMake = make(targetQuality);

            if (canMake) {
                left = targetQuality + 1;
                result = Math.max(result, targetQuality);
            } else {
                right = targetQuality - 1;
            }
        }
    }

    static boolean make(int minQuality) {
        int sum = 0;
        for (ArrayList<Spec> specs : parts.values()) {
            int minPrice = Integer.MAX_VALUE;
            for (Spec spec : specs) {
                if (spec.quality >= minQuality) {
                    minPrice = Math.min(minPrice, spec.price);
                }
            }
            if (minPrice == Integer.MAX_VALUE) return false;
            sum += minPrice;
        }

        if (sum <= b) return true;
        else return false;
    }

    static class Spec {
        int price, quality;

        public Spec(int price, int quality) {
            this.price = price;
            this.quality = quality;
        }
    }
}
