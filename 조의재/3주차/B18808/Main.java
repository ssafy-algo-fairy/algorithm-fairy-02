import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb;
    static int N, M, K;
    static int R,C;

    static int arr[][];
    static int sticker[][];
    public static void main(String args[])throws IOException{
        inputFile();
        System.out.println(sb.toString());
    }
    static void inputFile()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());

            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            sticker = new int[R][C];

            for(int j=0; j<R; j++){
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<C; k++){
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            solve();
        }
        sb.append(countAns());
    }
    static void solve(){
        for(int dir = 0; dir<4; dir++){
            for(int i=0; i<= N-R; i++){
                for(int j=0; j<= M-C; j++){
                    if(isAttach(i,j)) return;
                }
            }
            rotate();
        }
    }

    static boolean isAttach(int y, int x){
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(arr[y+i][x+j]==1 && sticker[i][j]==1) return false;
            }
        }

        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(sticker[i][j]==1) arr[y+i][x+j] = 1;
            }
        }
        return true;
    }

    static void rotate(){
        int rotated[][] = new int[C][R];
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                rotated[j][R-1-i] = sticker[i][j];
            }
        }
        sticker = rotated;
        
        R = R ^ C;
        C = R ^ C;
        R = R ^ C;
    }

    static int countAns(){
        int count = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if (arr[i][j] == 1) count++;
            }
        }
        return count;
    }
}
