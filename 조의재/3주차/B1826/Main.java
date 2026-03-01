import java.io.*;
import java.util.*;

public class Main{
    
    static StringBuilder sb;
    static int N, L, P;
    static Node gasStation[];
    public static void main(String args[])throws IOException{
        inputFile();
        System.out.println(solve());
    }
    static class Node{
        int start;
        int amount;
        public Node(int start, int amount){
            this.start = start;
            this.amount = amount;
        }
    }
    static void inputFile()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        gasStation = new Node[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());
            gasStation[i] = new Node(start, amount);
        }

        Arrays.sort(gasStation, (s1,s2) -> {
            if(s1.start == s2.start) return Integer.compare(s2.amount, s1.amount);
            return Integer.compare(s1.start, s2.start);
        });

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
    }
    static int solve(){
        PriorityQueue<Integer> pq = new PriorityQueue<>((s1,s2)-> Integer.compare(s2,s1));
        int answer = 0;
        int idx = 0;
        
        while(P<L){
            while(idx < N && gasStation[idx].start <= P){
                pq.add(gasStation[idx].amount);
                idx++;
            }
            if(pq.isEmpty()) return -1;

            P += pq.poll();
            answer++;
        }

        return answer;
    }
}
