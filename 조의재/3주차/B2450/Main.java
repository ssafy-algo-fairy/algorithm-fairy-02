import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb;
    static int[] arr, numCnt;
    static int[] selected;
    static boolean visited[];
    static int ans, N;
    public static void main(String args[])throws IOException{
        inputFile();
        System.out.println(sb.toString());
    }
    static void inputFile()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        numCnt = new int[4];
        visited  = new boolean[4];
        selected = new int[4];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            numCnt[arr[i]]++;
        }
        ans = Integer.MAX_VALUE;

        permutation(1);
        sb.append(ans);
    }


    static void permutation(int depth){
        if(depth == 4){
            ans = Math.min(ans, solve());
            return;
        }

        for(int i=1;i<=3; i++){
            if(!visited[i]){
                visited[i] = true;
                selected[depth] = i;
                permutation(depth+1);
                visited[i] = false;
            }
        }
    }

    static int solve(){
        int rangeNum[][] = new int[4][4];

        int idx = 1;
        for(int i=1; i<=3; i++){
            int targetNum = selected[i];
            for(int j=idx; j<idx+numCnt[targetNum]; j++){
                rangeNum[i][arr[j]]++;
            }
            idx += numCnt[targetNum];
        }

        int change12 = Math.min(rangeNum[1][selected[2]], rangeNum[2][selected[1]]);
        int change13 = Math.min(rangeNum[1][selected[3]], rangeNum[3][selected[1]]);
        int change23 = Math.min(rangeNum[2][selected[3]], rangeNum[3][selected[2]]);

        int totalChange = (change12+change13+change23);
        int cnt = 0;
        for(int i=1; i<=3; i++){
            for(int j=1; j<=3; j++){
                if(selected[i] == j) continue;
                cnt += rangeNum[i][j];
            }
        }

        cnt = cnt - (totalChange)*2;
        cnt = (cnt/3)*2;
        return totalChange+cnt;
    }
}
