import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, d, k, c;
    static int sushi[];
    static int type[];
    static int typeCnt;
    static int ans;
    public static void main(String args[]) throws IOException{
        readInput();
        findAnswer();
        System.out.println(ans);
    }
    public static void findAnswer() {
        Queue<Integer> que = new LinkedList<>();
        for(int i=0; i<k; i++){
            if(type[sushi[i]]++ == 0) typeCnt++;
            que.add(sushi[i]);
        }

        ans = Math.max(ans, typeCnt + (type[c] == 0 ? 1 : 0));

        for (int i = k; i < N + k - 1; i++) {
            int sushiIdx = i%N;
            
            if(0 == type[sushi[sushiIdx]]++) typeCnt++;

            int removeNum = que.poll();
            if(0 == --type[removeNum]) typeCnt--;

            que.add(sushi[sushiIdx]);
            ans = Math.max(ans, typeCnt + (type[c] == 0 ? 1 : 0));
        }
    }

    public static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushi = new int[N];
        type = new int[d+1];
        for(int i=0; i<N; i++){
            sushi[i] = Integer.parseInt(br.readLine());
        }
    }
}
