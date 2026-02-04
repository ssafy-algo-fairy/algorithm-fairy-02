import java.util.*;
import java.io.*;

public class boj1987 {
    static int max = 0;
    static char[][] board;
    static int R;
    static int C;

    static int[] dx = { -1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    static boolean[] visited = new boolean[26];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        for(int i=0;i<R;i++){
            board[i] = br.readLine().toCharArray();
        }

        go(0,0,0);
        
        System.out.println(max);
    }

    static void go(int x, int y, int count){
        int idx = board[x][y]-'A';
        
        if(visited[idx]){
            return;
        }
        visited[idx] = true;
        count +=1;
        max = Math.max(max, count);
        
        for(int i=0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx>=0 && ny>=0 && nx<R && ny<C){
                go(nx, ny, count);
            }
        }
        
        visited[idx] = false;
        
    }
}
