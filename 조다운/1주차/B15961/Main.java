import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 접시 수
        int d = Integer.parseInt(st.nextToken()); // 메뉴에 있는 초밥 가짓수
        int k = Integer.parseInt(st.nextToken()); // 먹을 수 있는 접시 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] rail = new int[n];
        for (int i = 0; i < n; i++) {
            rail[i] = Integer.parseInt(br.readLine());
        }

        int[] visited = new int[d + 1]; // 방문한 초밥 (지금 먹고 있는 구간에 i번 초밥이 몇개가 들었는지)
        int cnt = 0; // 중복 초밥 개수를 위한 개수 세기
        int ans = 0;

        for (int i = 0; i < k; i++) {
            if (visited[rail[i]] == 0)
                cnt++;
            visited[rail[i]]++;
        }

        for (int i = 0; i < n; i++) {
            // 맨 앞에 초밥 지우고
            visited[rail[i]]--;
            if (visited[rail[i]] == 0)
                cnt--;

            // 새로운 초밥 맨 뒤에 추가하고
            if (visited[rail[(i + k) % n]] == 0)
                cnt++;
            visited[rail[(i + k) % n]]++;

            int tmp = 0;
            if (visited[c] == 0) { // 추가 초밥은 연속될 필요는 없음
                tmp = cnt + 1;
            } else {
                tmp = cnt;
            }

            ans = Math.max(ans, tmp);
        }

        System.out.println(ans);

    }
}
