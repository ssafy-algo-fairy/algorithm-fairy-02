import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_25587 {
    static int n, m, result;
    static int[] parents;
    static int[] limits;
    static int[] rains;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parents[i] = -1;
        }

        limits = new int[n + 1];
        rains = new int[n + 1];

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.arraycopy(input, 0, limits, 1, n);

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.arraycopy(input, 0, rains, 1, n);

        //범람 초기화
        result = 0;
        for (int i = 1; i <= n; i++) {
            if (limits[i] < rains[i]) result++;
        }

        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (input[0] == 2) {
                sb.append(result).append("\n");
            } else {
                union(input[1], input[2]);
            }
        }


        System.out.print(sb);
    }

    static int find(int child) {
        if (parents[child] < 0) return child; //루트면 return

        return parents[child] = find(parents[child]);
    }

    static void union(int c1, int c2) {
        int p1 = find(c1);
        int p2 = find(c2);
        if (p1 == p2) return;

        int childCount1 = -parents[p1];
        int childCount2 = -parents[p2];

        //넘치고 있었다면 빼주기
        if (limits[p1] < rains[p1]) result -= childCount1;
        if (limits[p2] < rains[p2]) result -= childCount2;

        //강수량 합치기
        limits[p1] += limits[p2];
        rains[p1] += rains[p2];
        if (limits[p1] < rains[p1]) result += childCount1 + childCount2;

        //p1에 합쳐주고, p2의 부모를 p1으로
        parents[p1] -= childCount2;
        parents[p2] = p1;
    }
}
