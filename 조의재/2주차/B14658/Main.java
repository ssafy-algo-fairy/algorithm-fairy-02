import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb;
    static int N,M,L,K;
    static int[][] star;
    static int ans;
    public static void main(String args[])throws IOException{
        readInput();
        System.out.println(sb);
    }

    public static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ans = 0;
        star = new int[K][2];
        for(int k=0; k<K; k++){
            st = new StringTokenizer(br.readLine());

            star[k][0] = Integer.parseInt(st.nextToken()); //x
            star[k][1] = Integer.parseInt(st.nextToken()); //y
        }

        for(int i=0;i<K;i++){
            for(int j=0; j<=i; j++){
                int minX = Math.min(star[i][0], star[j][0]);
                int minY = Math.min(star[i][1], star[j][1]);

                int count = find(minX, minY);
                ans = Math.max(ans, count);
            }
        }
        sb.append(K - ans);
    }

    static int find(int x, int y){
        int count = 0;
        for(int i=0; i<K; i++){
            int sx = star[i][0];
            int sy = star[i][1];

            if(x<= sx && sx <= x+L && y <= sy && sy <= y+L) count++;
        }

        return count;
    }
}
