import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] price;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        inputFile();
        solution();
        System.out.println(sb.toString().trim());
    }

    public static void inputFile() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        price = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            price[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(st.nextToken());
    }

    public static void solution(){
        int minIdx = 0;
        int totalMoney = M;

        for(int i=1; i<N; i++){
            if(price[minIdx] > price[i]) minIdx = i;
        }
        
        if(minIdx == 0){ 
            int secondMinIdx = 1;
            for(int i=2; i<N; i++){
                if(price[secondMinIdx] > price[i]) secondMinIdx = i;
            }
            sb.append(secondMinIdx);
            totalMoney -= price[secondMinIdx];
        }

        int length = totalMoney / price[minIdx];
        for(int i=0; i<length; i++){
            sb.append(minIdx);
            totalMoney -= price[minIdx];
        }
        // 여기까지 세팅

        for(int i=0; i < length; i++){
            for(int j=N-1; j>=0; j--){
                if(j== minIdx || totalMoney == 0) break;


                int currentPrice = price[sb.charAt(i)-'0'];
                if(totalMoney+currentPrice >= price[j]){
                    sb.setCharAt(i, (char)(j+'0'));
                    totalMoney += currentPrice - price[j];
                    break;
                }
            }
        }
    }
}