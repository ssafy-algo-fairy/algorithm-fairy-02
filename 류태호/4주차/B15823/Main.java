package B15823;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] cards;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cards = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        // 이분탐색, 슬라이딩 윈도우
        int answer = 0;
        int max = N / M; // 최대 수량
        while (answer < max) {
            // 이분탐색을 위해 중간값 계산
            int mid = (answer + max + 1) / 2;
            if (canBuy(mid)) {
                answer = mid;
            } else {
                max = mid - 1;
            }
        }
        System.out.println(answer);
    }

    static boolean canBuy(int buyCnt) {
        if (buyCnt == 0) {
            // 0개면 안됨
            return true;
        }

        // 카드 번호의 마지막 위치를 저장하는 배열
        int[] last = new int[500001];
        Arrays.fill(last, -1);

        int cnt = 0;
        int start = 0;

        for (int i = 0; i < N; i++) {
            int card = cards[i];
            // 슬라이딩 윈도우로
            // start ~ i에서 중복 발생하면 다음 인덱스로 이동
            if (last[card] >= start) {
                // 마지막 위치가 start보다 크다는 것은 start ~ i에서 중복이 발생했다는 의미
                start = last[card] + 1;
                // start 위치 변경
            }
            last[card] = i;
            // 마지막 위치 업데이트

            if (i - start + 1 == buyCnt) {
                // 윈도우 길이가 buyCnt만큼 채워졌으면 팩 하나 확정
                cnt++;
                if (cnt >= M) {
                    // 다 채웠으면 리턴
                    return true;
                }

                // start 위치 갱신
                start = i + 1;
            }
        }
        return false;
    }
}