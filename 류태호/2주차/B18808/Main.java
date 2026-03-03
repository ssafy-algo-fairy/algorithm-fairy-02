package B18808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][][] stickers;
    static int[][] notebook;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stickers = new int[K][][];
        notebook = new int[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            stickers[i] = new int[r][c];

            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < c; k++) {
                    stickers[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        System.out.println(solve());
    }

    static int solve() {
        for (int i = 0; i < K; i++) {
            int[][] cur = stickers[i];

            for (int j = 0; j < 4; j++) { // 회전 4번
                boolean attatched = false;

                int R = cur.length;
                int C = cur[0].length;

                for (int k = 0; k <= N - R; k++) {
                    for (int l = 0; l <= M - C; l++) {
                        if (canAttach(cur, k, l)) {
                            attach(cur, k, l);
                            attatched = true;
                            break;
                        }
                    }
                    if (attatched) {
                        break;
                    }
                }

                if (attatched) {
                    break;
                }

                cur = rotate(cur);
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (notebook[i][j] == 1) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static boolean canAttach(int[][] sticker, int row, int col) {
        int R = sticker.length;
        int C = sticker[0].length;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (sticker[i][j] == 1 && notebook[row + i][col + j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    static void attach(int[][] sticker, int row, int col) {
        int R = sticker.length;
        int C = sticker[0].length;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (sticker[i][j] == 1) {
                    notebook[row + i][col + j] = 1;
                }
            }
        }
    }

    static int[][] rotate(int[][] sticker) {
        int R = sticker.length;
        int C = sticker[0].length;
        int[][] next = new int[C][R];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                next[j][R - 1 - i] = sticker[i][j];
                // 2차원 배열 회전 공식

                // 원본 크기: R x C, 원본 좌표: (r, c)

                // 시계 방향 90도 회전
                // 새 배열 크기: C x R
                // (r, c) → (c, R - 1 - r)

                // 반시계 방향 90도 회전
                // 새 배열 크기: C x R
                // (r, c) → (C - 1 - c, r)

                // 180도 회전
                // 새 배열 크기: R x C
                // (r, c) → (R - 1 - r, C - 1 - c)
            }
        }

        return next;
    }
}