# [BOJ] 17472 - 다리 만들기 2

### 📌 문제 정보
* **문제 링크**: [백준 17472번](https://www.acmicpc.net/problem/17472)
* **난이도**: Gold 1
* **분류**: BFS, MST (Kruskal/Prim), 구현, 브루트포스

---

### 💡 풀이 전략
이 문제는 지도상의 섬들을 연결하여 **모든 섬을 잇는 최소 다리 길이의 합**을 구하는 문제입니다. 단순한 MST 문제가 아니라, 직접 간선(다리)의 정보를 생성해야 하는 과정이 포함되어 있습니다.

1. **섬 식별 (BFS)**: 지도에서 1로 표시된 육지들을 BFS 탐색하여 각각의 섬에 고유 번호(1, 2, 3...)를 부여합니다.
2. **다리 후보 생성 (Brute-force)**: 
    * 각 섬의 모든 좌표에서 상하좌우 4방향으로 다리를 놓아봅니다.
    * 다리는 **직선**이어야 하며, 길이가 **2 이상**일 때만 유효한 간선으로 인정합니다.
    * 다른 섬에 도달하면 `(출발 섬, 도착 섬, 다리 길이)` 정보를 간선 리스트에 추가합니다.
3. **최소 신장 트리 (MST - Kruskal)**:
    * 생성된 모든 다리 후보를 길이 순으로 오름차순 정렬합니다.
    * **Union-Find** 자료구조를 사용하여 사이클을 방지하며 섬들을 연결합니다.
    * 사용된 간선의 수가 `(섬의 개수 - 1)`이면 총 길이를 출력하고, 그렇지 않으면 모든 섬을 연결할 수 없으므로 -1을 출력합니다.

---

### 💡 핵심 풀이 로직

#### 1. 섬 레이블링 (BFS)
입력받은 2차원 배열을 순회하며 방문하지 않은 육지를 발견하면 BFS로 인접한 모든 육지에 동일한 섬 번호를 매깁니다.

```java
public static void bfs(int y, int x) {
    Queue<int[]> que = new LinkedList<>();
    que.add(new int[]{y, x});
    visited[y][x] = true;
    landNum++; // 새로운 섬 번호 생성

    while (!que.isEmpty()) {
        int[] cur = que.poll();
        arr[cur[0]][cur[1]] = landNum; // 지도에 섬 번호 마킹

        for (int i = 0; i < 4; i++) {
            int ny = cur[0] + dy[i];
            int nx = cur[1] + dx[i];
            if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
            if (!visited[ny][nx] && arr[ny][nx] == 1) {
                visited[ny][nx] = true;
                que.offer(new int[]{ny, nx});
            }
        }
    }
}
```

2. 다리 놓기 (간선 추출)
각 섬의 좌표에서 바다(0)를 가로질러 다른 섬에 닿을 때까지 전진합니다.
```
// 모든 섬 좌표에서 4방향 탐색
while (true) {
    y += dy[j]; x += dx[j];
    if (y < 0 || x < 0 || y >= N || x >= M) break; // 지도 밖
    if (arr[y][x] == i) break;  // 같은 섬으로 돌아옴
    if (arr[y][x] > 0) { // 다른 섬 발견!
        if (dist >= 2) { // 길이가 2 이상일 때만 간선 추가
            edges.add(new Edge(i, arr[y][x], dist));
        }
        break;
    }
    dist++; // 바다인 경우 다리 길이 증가
}
```

3. MST 완성 (Kruskal)
간선들을 정렬하고 Union-Find를 통해 최소 비용으로 섬들을 연결합니다.

```
while (!edges.isEmpty()) {
    Edge edge = edges.poll();
    if (find(edge.from) != find(edge.to)) { // 사이클 미형성 시
        union(edge.from, edge.to);
        totalWeight += edge.weight;
        count++;
    }
}
```


✅ 복기 및 핵심 포인트
다리 제약 조건: 다리 길이가 1인 경우는 간선으로 포함시키지 않아야 하는 조건이 까다로울 수 있습니다.

섬의 개수와 간선의 관계: 최종적으로 count == landNum - 1 확인을 통해 모든 섬의 연결 여부를 판단하는 것이 중요합니다.

자료구조: PriorityQueue를 사용하여 간선을 자동으로 정렬하고, ArrayList[]를 통해 섬들의 좌표 정보를 효율적으로 관리했습니다.

🚀 복잡도 분석시간 복잡도: $O(N \times M \times \max(N, M) + E \log E)$섬 레이블링 및 좌표 저장: $O(N \times M)$간선 추출: 모든 섬 좌표($N \times M$ 이하)에서 4방향 탐색.Kruskal 알고리즘: 간선 정렬 및 Union-Find 연산.공간 복잡도: $O(N \times M)$ (지도 정보 및 방문 처리 배열 저장)