package B17472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static int[][] dist;

    // 다리 정보 클래스
    static class Edge implements Comparable<Edge> {
        int from, to, length;

        Edge(int from, int to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
        }

        @Override
        public int compareTo(Edge o) {
            return this.length - o.length;
            // 거리 기준으로 오름차순 정렬
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 섬마다 번호 붙이기
        int landNum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    landNum++;
                    checkLand(i, j, landNum);
                }
            }
        }

        // 2. 가장 짧은 다리 찾기
        buildBridge(landNum);

        // 3. MST로 최소 연결 길이 구하기
        System.out.println(kruskal(landNum));
    }

    static void checkLand(int row, int col, int landNum) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        visited[row][col] = true;
        map[row][col] = landNum;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0], j = cur[1];

            for (int k = 0; k < 4; k++) {
                int nr = i + dr[k];
                int nc = j + dc[k];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }
                if (visited[nr][nc] || map[nr][nc] != 1) {
                    continue;
                }
                visited[nr][nc] = true;
                map[nr][nc] = landNum;
                queue.add(new int[]{nr, nc});
            }
        }
    }

    static void buildBridge(int landNum) {
        dist = new int[landNum + 1][landNum + 1];

        for (int i = 1; i <= landNum; i++) {
            for (int j = 1; j <= landNum; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    // 바다
                    continue;
                }

                int from = map[i][j];

                for (int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    int len = 0;

                    while (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                        // 같은 방향으로만 잇기
                        if (map[nr][nc] == from) {
                            // 같은 섬
                            break;
                        } else if (map[nr][nc] == 0) {
                            // 바다면 계속 잇기
                            len++;
                            nr += dr[k];
                            nc += dc[k];
                            continue;
                        } else {
                            // 다른 섬
                            int to = map[nr][nc];
                            if (len >= 2) { // 2보다 작으면 다리 아님
                                int min = Math.min(dist[from][to], len);
                                dist[from][to] = min;
                                dist[to][from] = min;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    static int kruskal(int landNum) {
        List<Edge> edges = new ArrayList<>();

        for (int i = 1; i <= landNum; i++) {
            for (int j = i + 1; j <= landNum; j++) {
                if (dist[i][j] != Integer.MAX_VALUE) {
                    edges.add(new Edge(i, j, dist[i][j]));
                }
            }
        }

        Collections.sort(edges);

        parent = new int[landNum + 1];
        for (int i = 1; i <= landNum; i++) {
            parent[i] = i;
        }

        int sum = 0;
        int landCnt = 0;

        for (Edge e : edges) {
            if (union(e.from, e.to)) {
                // 두 섬이 연결되지 않았으면 연결
                sum += e.length;
                landCnt++;
            }
        }

        if (landCnt == landNum - 1) {
            // 섬 개수 -1 만큼 다리가 있으면 모두 연결된 상태
            return sum;
        } else {
            return -1;
        }
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return false;
        }
        parent[b] = a;
        return true;
    }
}