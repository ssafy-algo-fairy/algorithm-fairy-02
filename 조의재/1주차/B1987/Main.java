import java.io.*;
import java.util.*;

public class Main {

    static int dx[] = {1, -1, 0, 0};
    static int dy[] = {0, 0, 1, -1};

    static int R, C;
    static char arr[][];
    static int ans;
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        for(int r=0; r<R; r++){
            arr[r] = br.readLine().toCharArray();
        }

        boolean visited[][] = new boolean[R][C];
        visited[0][0] = true;
        Set<Character> set = new HashSet<>();
        set.add(arr[0][0]);
        dfs(0,0, visited, set, 1);

        System.out.println(ans);
    }

    public static void dfs(int r, int c, boolean visited[][], Set<Character> set, int count){
        
        ans = Math.max(ans, count);

        int ddy;
        int ddx;
        for(int i=0;i<4; i++){
            ddy = r+dy[i];
            ddx = c+dx[i];
            if(0<= ddy && ddy<R && 0<= ddx && ddx<C){
                if(!visited[ddy][ddx] && !set.contains(arr[ddy][ddx])){
                    set.add(arr[ddy][ddx]);
                    visited[ddy][ddx] = true;

                    dfs(ddy, ddx, visited, set, count+1);
                    
                    set.remove(arr[ddy][ddx]);
                    visited[ddy][ddx] = false;
                }
            }
        }

    }
}
