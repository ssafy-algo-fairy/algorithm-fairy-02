import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 완탐으로도가 풀 수 있을 듯도 섬의 수가 6개 이하니까
 * 근데 완탐을 어떻게 해야할 지 감이 안오네?
 * 각 섬을 연결하는 가장 짧은 걸 구하고, mst하면 될 듯?
 * 겹치는 건 어차피 둘다 세야하는거니까 가능한 방식
 * 1. 섬의 갯수를 구한다.
 * 2. 각 섬에서 시작해서, 각자 다른 섬으로의 가장 짧은 길을 구한다.
 * 3. 찾아가는건 한 점에서 4방향 잡고 쭉 가보기 -> 나오면 멈춤
 */

public class BOJ_17472_1 {

    static int[][] map;
    static boolean[][] visited;
    static int n, m;
    static int islandCount = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static PriorityQueue<Edge> pq;
    static int result;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        updateMap();
        makeEdges();
        makeResult();

        System.out.println(result);
    }

    static void makeResult() {
        result = 0;

        parent = new int[islandCount + 1];
        for (int i = 1; i <= islandCount; i++) {
            parent[i] = i;
        }

        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int p1 = find(e.st);
            int p2 = find(e.end);
            if (p1 == p2) continue;

            union(p1, p2);
            result += e.cost;
        }

        int rootCount = 0;
        for (int i = 1; i <= islandCount; i++) {
            if (parent[i] == i) rootCount++;
        }

        if (rootCount > 1) result = -1;
    }

    static int find(int child) {
        if (parent[child] == child) return child;

        return parent[child] = find(parent[child]);
    }

    static void union(int c1, int c2) {
        int p1 = find(c1);
        int p2 = find(c2);
        parent[p1] = p2;
    }

    static void makeEdges() {
        pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) continue;
                makeEdge(i, j);
            }
        }
    }

    static void makeEdge(int sx, int sy) {
        int islandNum = map[sx][sy];
        for (int i = 0; i < 4; i++) {
            int tsx = sx, tsy = sy;
            int cost = 0;
            while (true) {
                int nx = tsx + dx[i], ny = tsy + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;
                if (map[nx][ny] == islandNum) break;

                if (map[nx][ny] != 0) {
                    if (cost > 1) pq.add(new Edge(map[sx][sy], map[nx][ny], cost));
                    break;
                }
                cost++;
                tsx = nx;
                tsy = ny;
            }
        }
    }

    static void updateMap() {
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) continue;
                if (visited[i][j]) continue;
                makeIsland(i, j);
            }
        }
    }

    static void makeIsland(int sx, int sy) {
        visited[sx][sy] = true;
        islandCount++;
        map[sx][sy] = islandCount;
        Queue<Pos> q = new ArrayDeque<>();
        q.add(new Pos(sx, sy));

        while (!q.isEmpty()) {
            Pos cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i], ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 0) continue;
                map[nx][ny] = islandCount;
                visited[nx][ny] = true;
                q.add(new Pos(nx, ny));
            }
        }
    }

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge {
        int st, end, cost;

        public Edge(int st, int end, int cost) {
            this.st = st;
            this.end = end;
            this.cost = cost;
        }
    }

}
