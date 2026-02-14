import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb;
    static int[] capacity, flood, parent, count;
    static int N, M, ans;
    public static void main(String args[])throws IOException{
        readInput();
        System.out.println(sb);
    }

    public static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st2;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        capacity = new int[N];
        flood = new int[N];
        parent = new int[N];
        count = new int[N];
        ans = 0;

        st = new StringTokenizer(br.readLine());
        st2 = new StringTokenizer(br.readLine());
        
        for(int i=0; i<N; i++){
            capacity[i] = Integer.parseInt(st.nextToken());
            flood[i] = Integer.parseInt(st2.nextToken());
            parent[i] = i;
            count[i] = 1;

            if(capacity[i] < flood[i]) ans++;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            
            if(type == 2){
                sb.append(ans+"\n");
            }
            else{
                int land1 = Integer.parseInt(st.nextToken())-1;
                int land2 = Integer.parseInt(st.nextToken())-1;

                union(land1, land2);
            }
        }
    }

    static int find(int x){
        if(x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        int px = find(x);
        int py = find(y);

        if(px == py) return;

        if(capacity[py] < flood[py]) ans -= count[py];
        if(capacity[px] < flood[px]) ans -= count[px];

        parent[py] = px;
        capacity[px] += capacity[py];
        flood[px] += flood[py];
        count[px] += count[py];

        if(capacity[px] < flood[px]) ans += count[px];
    }
}
