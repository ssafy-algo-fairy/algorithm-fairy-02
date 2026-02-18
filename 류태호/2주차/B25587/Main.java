import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] gutter;
    static int[] rainFall;
    static int[][] cities;
    static int totalFlood = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        gutter = new int[N];
        rainFall = new int[N];
        cities = new int[N][2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            gutter[i] = Integer.parseInt(st.nextToken());
            cities[i][0] = i; // 부모
            cities[i][1] = 1; // 크기
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            rainFall[i] = Integer.parseInt(st.nextToken());
            if (rainFall[i] > gutter[i]) {
                totalFlood++; // 홍수난 도시 개수 세어두기
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("1")) {
                int city1 = Integer.parseInt(st.nextToken()) - 1;
                int city2 = Integer.parseInt(st.nextToken()) - 1;

                construction(city1, city2);
            } else {
                sb.append(totalFlood).append("\n");
            }
        }
        System.out.print(sb);
    }

    static void construction(int city1, int city2) {
        int root1 = find(city1);
        int root2 = find(city2);

        if (root1 != root2) {
            if (rainFall[root1] > gutter[root1]) {
                totalFlood -= cities[root1][1]; // 홍수난 도시였으면 크기만큼 빼줌
            }
            if (rainFall[root2] > gutter[root2]) {
                totalFlood -= cities[root2][1];
            }

            cities[root2][0] = root1;
            cities[root1][1] += cities[root2][1];
            gutter[root1] += gutter[root2];
            rainFall[root1] += rainFall[root2];

            if (rainFall[root1] > gutter[root1]) {
                totalFlood += cities[root1][1]; // 합치고 다시 계산
            }
        }
    }

    static int find(int city) {
        if (cities[city][0] == city) {
            return city;
        }
        return cities[city][0] = find(cities[city][0]);
    }
}