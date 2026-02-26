
/**
 * 수열과 쿼리 17 - 세그먼트 트리
 */
import java.util.*;
import java.io.*;

public class Main {
    static int N, M; // 수열의 크기, 쿼리의 개수
    static int[] A; // 수열
    static int[] tree;

    /**
     * 트리 초기화
     * s 부터 e 까지의 구간 최솟값을 node 번호에 저장
     */
    static int init(int s, int e, int node) {
        // 리프 노드에 도달했을 때
        if (s == e) {
            return tree[node] = A[s];
        }
        int mid = (s + e) / 2;

        // 왼쪽 자식(node*2)과 오른쪽 자식(node*2+1) 중 작은 값을 저장
        return tree[node] = Math.min(init(s, mid, node * 2), init(mid + 1, e, node * 2 + 1));
    }

    /**
     * 1번 쿼리
     * Ai 를 v 로 갱신
     */
    static int update(int s, int e, int node, int i, int v) {
        // 수정하려는 인덱스가 현재 구간 밖에 있다면 기존 값 유지
        if (i < s || i > e)
            return tree[node];

        // 리프 노드에 도달하면 값을 변경
        if (s == e)
            return tree[node] = v;

        int mid = (s + e) / 2;

        // 1. mid 부터
        return tree[node] = Math.min(update(s, mid, node * 2, i, v), update(mid + 1, e, node * 2 + 1, i, v));
    }

    /**
     * 2번 쿼리
     * Ai 부터 Aj 까지 구간의 최솟값 구하기
     */
    static int query(int s, int e, int node, int i, int j) {
        // 탐색 범위가 퀴리 범위를 완전히 벗어난 경우
        if (i > e || j < s)
            return Integer.MAX_VALUE;

        // 탐색 범위가 퀴리 범위 안에 완전히 포함된 경우
        if (i <= s && e <= j)
            return tree[node];

        int mid = (s + e) / 2;

        return Math.min(query(s, mid, node * 2, i, j), query(mid + 1, e, node * 2 + 1, i, j));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        // A 입력 받기
        A = new int[N + 1]; // 1-based
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) { // 1-based
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 트리 설정
        tree = new int[N * 4];
        init(1, N, 1);

        // query 입력 받기
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (type == 1) {
                update(1, N, 1, a, b);
            } else {
                System.out.println(query(1, N, 1, a, b));
            }
        }

    }
}
