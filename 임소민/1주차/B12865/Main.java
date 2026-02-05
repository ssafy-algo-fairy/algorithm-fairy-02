import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] arr;
    static int[][] dp;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게
        
        arr = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 무게
            arr[i][1] = Integer.parseInt(st.nextToken()); // 가치
        }
        
        dp = new int[N + 1][K + 1];
        visited = new boolean[K + 1];
        dynamicProgramming();
        
        int answer = 0;
        for (int i = 0; i <= K; i++) {
            answer = Math.max(dp[N][i], answer);
        }
        
        System.out.println(answer);
    }
    
    private static void dynamicProgramming() {
        for (int i = 1; i <= N; i++) { // i번째 물건
            int weight = arr[i][0];
            int value = arr[i][1];
            
            for (int j = 0; j <= K; j++) { // 무게 j
            	// i번째 물건 X: [이전 단계 결과 vs 이번 단계에서 갱신되었을 수도 있을 최적값] 중 더 큰 가치로 갱신
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
                
                // i번째 물건 O
                if (visited[j]) { // 이전 무게(합)이 j면 거기서 현재 무게, 가치 더하기
                	int sum = weight + j;
                	if (sum <= K) {
                		dp[i][sum] = dp[i - 1][j] + value;
                		visited[sum]= true; 
                	}
                }
                // i번째 물건 O
                if (j == weight) { // [i번째 물건만 담는 것 vs 이전 단계 결과] 중 더 큰 가치로 갱신
                	dp[i][j] = Math.max(value, dp[i][j]);
                	visited[j] = true;
                }

                
            }
        }
        
    }
    
}
