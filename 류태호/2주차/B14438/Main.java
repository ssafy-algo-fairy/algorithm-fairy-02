package B14438;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] seg;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine().trim());
        int[] input = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        // 일반 배열로 할 시 시간초과 발생하여 세그먼트 트리 이용
        // N이 2의 거듭제곱인 경우 배열의 크기를 (2 * N - 1)로 잡으면 되지만
        // 아닐 수도 있기에 최악을 상정하여 4 * N으로 잡음
        seg = new int[4 * N];
        build(1, 1, N, input);
        // 초기에는 1번 노드(루트)가 1 ~ N 구간을 담당하겠다는 뜻

        M = Integer.parseInt(br.readLine().trim());
        for (int q = 0; q < M; q++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) {
                int idx = Integer.parseInt(st.nextToken());
                int val = Integer.parseInt(st.nextToken());
                update(1, 1, N, idx, val);
            } else {
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                sb.append(query(1, 1, N, l, r)).append('\n');
            }
        }

        System.out.print(sb);
    }

    /**
     * 세그먼트 트리를 만드는 메서드
     *
     * @param node  세그먼트 트리의 노드 번호
     * @param start 세그먼트 트리가 담당하는 구간의 시작 인덱스
     * @param end   세그먼트 트리가 담당하는 구간의 끝 인덱스
     * @param input 초기 입력 배열
     */
    static void build(int node, int start, int end, int[] input) {
        // 시작과 끝이 같다는 건 한 칸 짜리 구간으로 리프노드이고, 해당 구간의 최솟값 저장하면 끝
        if (start == end) {
            seg[node] = input[start];
            return;
        }
        int mid = (start + end) / 2;
        // 재귀로 자식 노드 만들기
        build(node * 2, start, mid, input);
        build(node * 2 + 1, mid + 1, end, input);
        // 자식노드에서 자기 구간의 최소를 저장했으니까
        // 현재 구간의 최소는 왼쪽 자식(인덱스 * 2)과 오른쪽 자식(인덱스 * 2 + 1) 중 더 작은 값
        seg[node] = Math.min(seg[node * 2], seg[node * 2 + 1]);
    }

    /**
     * 세그먼트 트리를 업데이트 하는 메서드
     *
     * @param node  세그먼트 트리의 노드 번호
     * @param start 세그먼트 트리가 담당하는 구간의 시작 인덱스
     * @param end   세그먼트 트리가 담당하는 구간의 끝 인덱스
     * @param idx   업데이트할 인덱스
     * @param val   업데이트할 값
     */
    static void update(int node, int start, int end, int idx, int val) {
        // 변경할 인덱스가 현재 노드의 담당 구간을 벗어나면 종료
        if (idx < start || idx > end) {
            return;
        }
        // 리프노드까지 왔으면 범위가 1칸이라 바꿔주기만 하면 됨
        if (start == end) {
            seg[node] = val;
            return;
        }
        // 중간에 있는 노드라면 재귀로 자식 호출
        int mid = (start + end) / 2;
        update(node * 2, start, mid, idx, val);
        update(node * 2 + 1, mid + 1, end, idx, val);
        // 자식이 바뀌었을 수도 있으니 부모도 업데이트
        seg[node] = Math.min(seg[node * 2], seg[node * 2 + 1]);
    }

    /**
     * 세그먼트 트리를 이용하여 구간의 최솟값을 구하는 메서드
     *
     * @param node  세그먼트 트리의 노드 번호
     * @param start 세그먼트 트리가 담당하는 구간의 시작 인덱스
     * @param end   세그먼트 트리가 담당하는 구간의 끝 인덱스
     * @param l     쿼리할 구간의 시작 인덱스
     * @param r     쿼리할 구간의 끝 인덱스
     */
    static int query(int node, int start, int end, int l, int r) {
        // 쿼리의 구간이 현재 노드의 담당 구간을 벗어나는 경우
        if (r < start || end < l) {
            return Integer.MAX_VALUE;
        }
        // 쿼리의 구간이 현재 노드의 담당 구간을 완전히 포함하는 경우
        if (l <= start && end <= r) {
            return seg[node];
        }
        // 쿼리의 구간이 부분적으로 겹치는 경우
        int mid = (start + end) / 2;
        return Math.min(
                query(node * 2, start, mid, l, r),
                query(node * 2 + 1, mid + 1, end, l, r)
        );
    }
}