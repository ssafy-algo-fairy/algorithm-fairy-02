import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m, k;
    static ArrayList<int[][]> stickers = new ArrayList<>();
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new boolean[n][m];


        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[r][c];
            for (int j = 0; j < r; j++) {
                sticker[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            stickers.add(sticker);
        }

        for (int[][] sticker : stickers) {
            useSticker(sticker);
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j]) result++;
            }
        }

        System.out.print(result);

    }

    static void useSticker(int[][] sticker) {
        for (int k = 0; k < 4; k++) {

            if (k != 0) sticker = turnSticker(sticker);

            for (int i = 0; i <= n - sticker.length; i++) {
                for (int j = 0; j <= m - sticker[0].length; j++) {
                    if (canUse(i, j, sticker)) {
                        use(i, j, sticker);
                        return;
                    }
                }
            }
        }

    }

    static int[][] turnSticker(int[][] sticker) {
        int r = sticker.length;
        int c = sticker[0].length;

        int[][] newSticker = new int[c][r];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                newSticker[i][j] = sticker[r - 1 - j][i];
            }
        }

        return newSticker;
    }

    static void use(int sx, int sy, int[][] sticker) {
        for (int i = sx; i < sx + sticker.length; i++) {
            for (int j = sy; j < sy + sticker[0].length; j++) {
                if (sticker[i - sx][j - sy] == 0) continue;
                map[i][j] = true;
            }
        }
    }

    static boolean canUse(int sx, int sy, int[][] sticker) {
        for (int i = sx; i < sx + sticker.length; i++) {
            for (int j = sy; j < sy + sticker[0].length; j++) {
                if (sticker[i - sx][j - sy] == 0) continue;
                if (map[i][j]) return false;
            }
        }

        return true;
    }
}
