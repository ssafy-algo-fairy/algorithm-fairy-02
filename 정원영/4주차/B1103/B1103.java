import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1103 {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int n, m;
    static char[][] map;
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        map = new char[n][m];
        memo = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                memo[i][j] = -1;
            }
        }
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        boolean[][] visited = new boolean[n][m];
        int max = findMax(0, 0, visited);
        System.out.println(max);
    }

    static int findMax(int x, int y, boolean[][] visited) {
        if (memo[x][y] != -1) return memo[x][y];

        visited[x][y] = true;

        int max = 1;
        for (int i = 0; i < 4; i++) {
            int num = map[x][y] - '0';
            int nx = x + dx[i] * num, ny = y + dy[i] * num;
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if (map[nx][ny] == 'H') continue;
            if (visited[nx][ny]) return -1;
            int result = findMax(nx, ny, visited);
            if (result == -1) return -1;

            max = Math.max(max, result + 1);
        }

        memo[x][y] = max;
        visited[x][y] = false;
        return max;
    }
}
