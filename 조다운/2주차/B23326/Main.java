import java.io.*;
import java.util.*;

public class Main {
    static int N, Q; // 구역의 개수, 쿼리의 개수
    static int p = 1; // 도현이 현재 위치

    static TreeSet<Integer> A = new TreeSet<>(); // 명소 위치 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++){
            if(Integer.parseInt(st.nextToken()) == 1){ 
                // 명소일 때만 추가
                A.add(i);
            }
        }

        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if(type == 3){
                // 도현이가 명소에 도달하기 위해 시계방향으로 최소 몇 칸 움직여야 하는지

                if(A.isEmpty()) {
                    sb.append("-1\n");
                } else {
                    // 현재 위치 p 보다 크거나 같은 첫 번째 명소 찾기
                    Integer target = A.ceiling(p);

                    if(target != null){
                        sb.append(target - p).append('\n');
                    } else {
                        // 현재 위치 뒤에는 없고, 한 바퀴 돌아 맨 앞에 있는 경우
                        // (N - 현재 위치) + 맨 앞 명소 위치
                        sb.append((N - p) + A.first()).append("\n");
                    }
                }
                
            } else if(st.hasMoreTokens()){
                int n = Integer.parseInt(st.nextToken());

                if(type == 1){
                    // n 번 구역이 명소 x -> 명소, 명소 -> 명소 x
                    if(A.contains(n)) A.remove(n);
                    else A.add(n);

                } else if(type == 2){
                    // 시계 방향으로 n 만큼 이동
                    p = (p + n - 1) % N + 1; // 원형 처리!
                }
            }

            
        }
        System.out.println(sb);
    }
}
