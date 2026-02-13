import java.io.*;
import java.util.*;

public class Main{
    static int parent[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[])throws IOException{
        readInput();
        System.out.println(sb);
    }

    public static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for(int i=1;i<=N;i++){
            parent[i] = i;
        }

        int cycleCnt = 0;
        int needConnect = -1;
        for(int i=0;i<M; i++){
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            if(!union(left, right)){
                cycleCnt++;
            }
        }

        for(int i=1; i<=N; i++){
            if(parent[i] == i){
                needConnect++;
            }
        }

        sb.append(needConnect + cycleCnt);
    }

    static boolean union(int left, int right){
        left = find(left);
        right = find(right);

        if(left != right){
            parent[right] = left;
            return true;
        }
        return false;
    }
    static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
}
