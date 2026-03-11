import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int T, n;
    static int[] preorder, inorder;
    static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringBuilder sb = new StringBuilder();
        
        T = Integer.parseInt(br.readLine());
    
        for (int tc = 0; tc < T; tc++) {
            n = Integer.parseInt(br.readLine());

            preorder = new int[n];
            inorder = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                preorder[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                inorder[i] = Integer.parseInt(st.nextToken());
            }
            
            answer = new StringBuilder();
            getPostorder(preorder, inorder);
            
            sb.append(answer).append('\n');
        }
        
        bw.write(sb.toString());
        bw.flush();
    }
    
    static void getPostorder(int[] preorder, int[] inorder) {
        int pivotNode = preorder[0]; // 현재 서브 트리에서의 root
        answer.insert(0, pivotNode + " ");
        
        int pivotIdx = 0;
        for (int i = 0; i < inorder.length; i++) {
        	if (inorder[i] == pivotNode) {
        		pivotIdx = i;
        		break;
        	}
        }

        // inorder에서 root 기준 left, right 구분
        int[] left, right;
        if (pivotIdx > 0) {
        	left = Arrays.copyOf(inorder, pivotIdx);
        } else {
        	left = new int[0];
        }
        if (pivotIdx < inorder.length - 1) {
            right = Arrays.copyOfRange(inorder, pivotIdx + 1, inorder.length);
        } else {
            right = new int[0];
        }
        
        if (right.length > 0) { // right 먼저 탐색
        	int from = preorder.length - right.length;
        	int to = preorder.length;
        	int[] newPreorder = Arrays.copyOfRange(preorder, from, to);
        	
        	getPostorder(newPreorder, right);
        }
        
        if (left.length > 0) { // right 다 채우고 나면 left
        	int from = 1;
        	int to = left.length + 1;
        	int[] newPreorder = Arrays.copyOfRange(preorder, from, to);
        	
        	getPostorder(newPreorder, left);
        } 
        
        if (left.length == 0 && right.length == 0) { // left도 right도 없으면 리프노드 <- 기저 조건
        	return;
        }
    }
}
