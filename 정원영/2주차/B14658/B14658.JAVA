import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14658 {

    static int n, m, l, k;

    static Star[] stars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        stars = new Star[k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i] = new Star(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int result = k;
        for (Star standard : stars) {
            int xu = standard.x + l, xd = standard.x;
            int yu = standard.y + l, yd = standard.y - l;

            ArrayList<Star> alive = new ArrayList<>();
            for (Star star : stars) {
                if (star.x < xd || xu < star.x) continue;
                if (star.y < yd || yu < star.y) continue;
                alive.add(star);
            }
            result = Math.min(result, k - reflect(alive, standard));


            xu = standard.x;
            xd = standard.x - l;
            yu = standard.y + l;
            yd = standard.y - l;

            alive = new ArrayList<>();
            for (Star star : stars) {
                if (star.x < xd || xu < star.x) continue;
                if (star.y < yd || yu < star.y) continue;
                alive.add(star);
            }
            result = Math.min(result, k - reflect(alive, standard));

        }
        System.out.println(result);
    }

    static int reflect(ArrayList<Star> alive, Star standard) {
        int temp = 0;
        for (Star aStandard : alive) {
            int uy = aStandard.y;
            int dy = aStandard.y - l;
            if (uy < standard.y) continue;

            int count = 0;
            for (Star star : alive) {
                if (star.y < dy || uy < star.y) continue;
                count++;
            }
            temp = Math.max(temp, count);
        }
        return temp;
    }

    static class Star {
        int x, y;

        public Star(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
