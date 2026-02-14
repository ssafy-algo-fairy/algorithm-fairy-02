package B17472;

import java.util.*;
import java.io.*;

public class b17472 {

	static class Point {
	    int x, y;

	    Point(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }
	}

	static class Island {
		List<Point> land = new ArrayList<>(); // 섬에 포함된 땅
	}

	static class Edge {
		int node1, node2, len; // 노드와 간선 길이

		public Edge(int node1, int node2, int len) {
			this.node1 = node1;
			this.node2 = node2;
			this.len = len;
		}
	}

	static int N, M, islandNum, lenSum;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[] root;

	static List<Island> islandList = new ArrayList<>();
	static List<Edge> edgeList = new ArrayList<>();

	static int[][] Map, visited;
	static int[][] connected;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Map = new int[N][M];
		visited = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				Map[i][j] = Integer.parseInt(st.nextToken()); // 0 바다 1 땅
			}
		}

		islandNum = 0;
		for (int i = 0; i < N; i++) { // bfs를 사용해 이어진 땅 섬으로 저장
			for (int j = 0; j < M; j++) {
				if (Map[i][j] == 0 || visited[i][j] == 1)
					continue;

				islandList.add(new Island());

				Queue<Point> q = new LinkedList<>();
				int x = i, y = j;
				visited[x][y] = 1;
				Point point = new Point(x, y);
				islandList.get(islandNum).land.add(point);
				q.offer(point);
				while (!q.isEmpty()) {
					Point p = q.poll();
					for (int k = 0; k < 4; k++) {
						int nx = p.x + dx[k], ny = p.y + dy[k];
						if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1 || Map[nx][ny] == 0 || visited[nx][ny] == 1)
							continue;
						visited[nx][ny] = 1;
						Point np = new Point(nx, ny);
						islandList.get(islandNum).land.add(np);
						q.offer(np);
					}
				}

				islandNum++;
			}
		}

		connected = new int[islandNum][islandNum];

		for (int i = 0; i < islandNum; i++) { // 섬의 모든 땅에서 가까운 섬 탐색
			for (Point p : islandList.get(i).land) {
				for (int d = 0; d < 4; d++) {
					int x = p.x, y = p.y, len = 0;
					while (true) {
						x += dx[d];
						y += dy[d];
						if (x < 0 || x > N - 1 || y < 0 || y > M - 1)
							break;
						if (Map[x][y] == 1) { // 땅에 도착했을 때
							if (len < 2)
								break;
							boolean flag = false;
							for (int j = 0; j < islandNum; j++) {
								for (Point np : islandList.get(j).land) {
									if (x == np.x && y == np.y) {
										flag = true;
										connected[i][j] = (connected[i][j] == 0) ? len : Math.min(connected[i][j], len);
										break;
									}
								}
								if (flag)
									break;
							}
							break;
						}
						len++;
					}
				}
			}
		}

		for (int i = 0; i < islandNum; i++) { // 간선 정보 저장하고 오름차순 정렬
			for (int j = i + 1; j < islandNum; j++) {
				if (connected[i][j] == 0)
					continue;
				edgeList.add(new Edge(i, j, connected[i][j]));
			}
		}
		edgeList.sort((e1, e2) -> Integer.compare(e1.len, e2.len));

		root = new int[islandNum];
		for (int i = 0; i < islandNum; i++)
			root[i] = i;

		lenSum = 0; // union-find
		for (Edge edge : edgeList) {
			union(edge);
		}

		int rootCnt = 0;
		for (int i = 0; i < islandNum; i++) {
			if (root[i] == i)
				rootCnt++;
		}
		if (rootCnt > 1)
			System.out.println(-1); // root가 자기자신인 노드가 둘 이상일 때 (모든 섬 연결 불가)
		else
			System.out.println(lenSum);

	}

	static void union(Edge edge) {
		int r1 = find(edge.node1);
		int r2 = find(edge.node2);

		if (r1 == r2)
			return;

		lenSum += edge.len;
		root[r2] = r1;
		return;
	}

	static int find(int node) {
		if (root[node] == node)
			return node;

		return root[node] = find(root[node]);
	}

}