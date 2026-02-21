import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static List<int[]>[] landPosition;
    static int[] parent;

    static boolean[][] visited;
    static int[][] arr;

    static int landNum; 
    static PriorityQueue<Edge> edges = new PriorityQueue<>();
    static StringBuilder sb;

    static class Edge implements Comparable<Edge> {
        int from, to, weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        inputFile();
        connetBridge();
        solveMST(); 
        System.out.println(sb);
    }

    public static void inputFile() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];
        landNum = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && arr[i][j] == 1) {
                    bfs(i, j);
                }
            }
        }
    }

    public static void bfs(int y, int x) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{y, x});
        visited[y][x] = true;

        landNum++;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int cy = cur[0];
            int cx = cur[1];
            arr[cy][cx] = landNum;

            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
                if (!visited[ny][nx] && arr[ny][nx] == 1) {
                    visited[ny][nx] = true;
                    que.offer(new int[]{ny, nx});
                }
            }
        }
    }

    public static void connetBridge() {
        landPosition = new ArrayList[landNum + 1];
        for (int i = 1; i <= landNum; i++) {
            landPosition[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] > 0) {
                    landPosition[arr[i][j]].add(new int[]{i, j});
                }
            }
        }

        for (int i = 1; i <= landNum; i++) {
            for (int[] yx : landPosition[i]) {
                for (int j = 0; j < 4; j++) {
                    int y = yx[0];
                    int x = yx[1];
                    int dist = 0;

                    while (true) {
                        y += dy[j];
                        x += dx[j];

                        if (y < 0 || x < 0 || y >= N || x >= M) break;
                        if (arr[y][x] == i) break;  
                        if (arr[y][x] > 0) {
                            if (dist >= 2) {
                                edges.add(new Edge(i, arr[y][x], dist));
                            }
                            break;
                        }
                        dist++;
                    }
                }
            }
        }
    }

    public static void solveMST() {
        parent = new int[landNum + 1];
        for (int i = 1; i <= landNum; i++) parent[i] = i;

        int totalWeight = 0;
        int count = 0;

        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                totalWeight += edge.weight;
                count++;
            }
        }

        if (count == landNum - 1) sb.append(totalWeight);
        else sb.append(-1);
    }

    public static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }
    
}