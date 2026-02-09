import java.io.*;
import java.util.*;

public class B23326{
    static int N, Q;
    static StringBuilder sb;
    static TreeSet<Integer> ts;
    
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
        Q = Integer.parseInt(st.nextToken());

        ts = new TreeSet<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int temp = Integer.parseInt(st.nextToken());
            if(temp == 1) ts.add(i);
        }
        int position = 0;

        for(int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            int amount = 0;

            if(st.hasMoreTokens()){
                amount = Integer.parseInt(st.nextToken());
            }

            if(query == 1){
                if(ts.contains(amount-1)){
                    ts.remove(amount-1);
                }
                else{
                    ts.add(amount-1);
                }
            }
            else if(query == 2){
                position = (position+amount) % N;
            }
            else{ // 3
                Integer temp = 0;
                if(ts.isEmpty()){
                    temp = -1;
                }
                else{
                    temp = ts.ceiling(position);
                    if(temp == null){
                        temp = ts.first() + N - position;
                    }
                    else{
                        temp -= position;
                    }
                }
                sb.append(temp+"\n");
            }
        }
    }
}
