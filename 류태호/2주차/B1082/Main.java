package B1082;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine().trim());
        p = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine().trim());

        // 가장 숫자 길게 만들어야 함

        // 숫자가 0 하나뿐인 경우
        if (N == 1) {
            System.out.println("0");
            return;
        }

        // 가장 작은 숫자 찾기
        int min = 0;
        for (int i = 1; i < N; i++) {
            if (p[i] < p[min]) {
                min = i;
            }
        }

        // 첫째 자리에 올 수 있는 가장 작은 숫자 (0 안됨)
        int first = 1;
        for (int i = 2; i < N; i++) {
            if (p[i] < p[first]) {
                first = i;
            }
        }

        // 돈 없어서 첫째 자리 숫자도 못 삼
        if (M < p[first]) {
            System.out.println("0");
            return;
        }

        int money = M;

        // 이제 하나씩 붙이면서 계산
        // 사는 개수 제한 없으니까 다 붙이면서 그리디로
        sb.append(first);
        money -= p[first];

        // 붙일 수 있는 개수
        int add = money / p[min];
        for (int i = 0; i < add; i++) {
            sb.append(min);
        }
        // 남은 돈
        money -= add * p[min];

        // 남은 돈으로 큰 숫자로 바꾸기
        // 앞에서부터
        // 문자 배열로 바꾸고 숫자로 처리
        char[] numbers = sb.toString().toCharArray();

        for (int i = 0; i < numbers.length; i++) {
            int cur = numbers[i] - '0';

            for (int j = N - 1; j >= 0; j--) {
                // 가장 큰 숫자부터 바꿀 수 있나 확인
                if (i == 0 && j == 0) {
                    // 첫자리는 0 안됨
                    continue;
                }

                // 차액
                int diff = p[j] - p[cur];
                // 차액 낼 수 있으면 바꾸기
                if (diff <= money) {
                    numbers[i] = (char) ('0' + j);
                    money -= diff;
                    break;
                }
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i]);
        }
    }
}