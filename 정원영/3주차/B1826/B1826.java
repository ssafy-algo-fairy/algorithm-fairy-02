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
