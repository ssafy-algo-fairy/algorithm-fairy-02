package B3178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String[] prefix = new String[N];
        String[] suffix = new String[N];

        // 결국 앞에 있는 거 끼리, 뒤에 있는 거 끼리 비교해서 겹치는 부분을 찾으면 됨.
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            // 앞부분
            prefix[i] = str.substring(0, K);
            // 뒷부분은 뒤집어서 해야 뒤에서부터 겹치는 거 계산할 수 있음
            StringBuilder sb = new StringBuilder(str.substring(K));
            suffix[i] = sb.reverse().toString();
        }

        // 정렬하면 겹치는 부분이 비교하기 편함
        Arrays.sort(prefix);
        Arrays.sort(suffix);

        // 첫 글자는 어차피 무조건 써야함
        int ans = K * 2;

        for (int i = 1; i < N; i++) {
            // 앞뒤 각각 이전 단어와 겹치지 않는 글자 수만큼 추가
            // K 길이의 글자에서 겹치는 부분을 빼주면 추가해야하는 숫자가 나옴
            ans += (K - count(prefix[i - 1], prefix[i]));
            ans += (K - count(suffix[i - 1], suffix[i]));
        }

        System.out.println(ans);
    }

    // 겹치는 숫자 개수 카운트
    static int count(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}