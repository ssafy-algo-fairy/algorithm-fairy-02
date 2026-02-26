package B4265;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] preorder;
    static int[] inorder;
    static int[] pos;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringTokenizer st;

        for (int TEST_CASE = 0; TEST_CASE < T; TEST_CASE++) {
            N = Integer.parseInt(br.readLine().trim());

            preorder = new int[N];
            inorder = new int[N];
            pos = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                preorder[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                inorder[i] = Integer.parseInt(st.nextToken());
            }

            /**
             * 전위순회: 루트 -> 왼 -> 오
             * 중위순회: 왼 -> 루트 -> 오
             * 후위순회: 왼 -> 오 -> 루트
             * 전위순회에서 루트를 찾고, 중위순회에서 루트의 위치를 찾음
             * 그 후에 각각 재귀로 훑고 오면 후위순회 결과가 나옴
             */
            for (int i = 0; i < N; i++) {
                // 서브트리 빠르게 구하기 위해서 중위순회의 노드의 위치를 저장해둠
                pos[inorder[i]] = i;
            }

            findPostorder(0, N - 1, 0, N - 1);
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }

    /**
     *
     * @param preL 전위순회에서 현재 서브트리의 왼쪽 끝 인덱스
     * @param preR 전위순회에서 현재 서브트리의 오른쪽 끝 인덱스
     * @param inL 중위순회에서 현재 서브트리의 왼쪽 끝 인덱스
     * @param inR 중위순회에서 현재 서브트리의 오른쪽 끝 인덱스
     */
    static void findPostorder(int preL, int preR, int inL, int inR) {
        // 끝까지 가면 종료
        if (preL > preR || inL > inR) {
            return;
        }

        // 전위순회에서는 첫 원소가 루트
        int root = preorder[preL];
        // 미리 저장해둔 중위순회의 노드의 위치 배열에서 루트의 위치를 찾아서 서브트리 크기 계산
        int rootIdx = pos[root];
        // 왼쪽 서브트리의 크기는 루트의 위치 - 왼쪽 끝 위치
        int leftSize = rootIdx - inL;
        // 전위순회에서 preL부터 leftSize만큼이 왼쪽 서브트리, 나머지가 오른쪽 서브트리가 됨
        // 중위순회에서는 왼쪽 끝부터 루트의 위치 - 1까지가 왼쪽 서브트리, 루트의 위치 + 1부터 오른쪽 끝까지가 오른쪽 서브트리가 됨
        // 왼쪽 돌고
        findPostorder(preL + 1, preL + leftSize, inL, rootIdx - 1);
        // 오른쪽 돌고
        findPostorder(preL + leftSize + 1, preR, rootIdx + 1, inR);
        // 루트 추가
        sb.append(root).append(' ');
    }
}