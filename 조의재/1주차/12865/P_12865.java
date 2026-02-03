import java.io.*;
import java.util.*;

class P_12865 {
    static class Node{
        int W;
        int V;
        public Node(int W, int V){
            this.W = W;
            this.V = V;
        }
    }
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int dp[][] = new int[N+1][K+1];
        Node info[] = new Node[N+1];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            info[i] = new Node(W,V);
        }

        for(int i=1; i<=N; i++){
            for(int j=0; j<=K; j++){
                Node now = info[i];
                if(j - now.W >= 0){
                    dp[i][j] = Math.max(dp[i-1][j-now.W]+now.V , dp[i-1][j]);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        int maxVal = 0;
        for(int i=1; i<=K; i++){
            maxVal = maxVal > dp[N][i] ? maxVal : dp[N][i];
        }
        System.out.println(maxVal);
    }
}
