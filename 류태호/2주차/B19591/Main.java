package B19591;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();
        Deque<String> dq = new ArrayDeque<>();

        long number;
        int i = 0;

        if (expression.charAt(0) == '-') {
            number = 0;
            // -로 시작하는 경우 미리 처리
            i++;
            while (i < expression.length() && 0 <= expression.charAt(i) - '0' && expression.charAt(i) - '0' <= 9) {
                number = number * 10 + (expression.charAt(i++) - '0');
            }
            number *= -1;
        } else {
            number = 0;
        }

        for (; i < expression.length(); i++) {
            char c = expression.charAt(i);
            // 연산자 만나면 숫자 넣고 연산자 넣기
            if (c == '*' || c == '/' || c == '+' || c == '-') {
                dq.addLast(String.valueOf(number));
                dq.addLast(String.valueOf(c));
                number = 0;
            } else {
                // 숫자는 이어붙이기
                number = number * 10 + (c - '0');
            }
        }
        // 마지막 남은 숫자도 넣기
        dq.addLast(String.valueOf(number));

        long a, b, c, d;
        String op1, op2;
        while (true) {
            if (dq.size() == 1) {
                System.out.println(dq.pollFirst());
                break;
            } else if (dq.size() == 3) {
                a = Long.parseLong(dq.pollFirst());
                String op = dq.pollFirst();
                b = Long.parseLong(dq.pollFirst());
                System.out.println(calculate(a, b, op));
                break;
            } else if (dq.size() == 5) {
                // 5인 경우에는 중간 숫자가 두 번 활용되어서 따로 처리
                a = Long.parseLong(dq.pollFirst());
                op1 = dq.pollFirst();
                b = Long.parseLong(dq.pollFirst());
                op2 = dq.pollFirst();
                c = Long.parseLong(dq.pollFirst());

                int compare = higher(op1, op2);
                long ans1 = calculate(a, b, op1);
                long ans2 = calculate(b, c, op2);

                // 앞의 연산 해야하는 경우
                if (compare == 1 || (compare == 0 && ans1 >= ans2)) {
                    // 정답 먼저 맨앞에 넣고
                    dq.addFirst(String.valueOf(ans1));
                    // 맨 뒤에 연산자 넣고
                    dq.addLast(op2);
                    // 그 뒤에 숫자 넣기
                    dq.addLast(String.valueOf(c));
                }
                // 뒤의 연산 해야하는 경우
                else {
                    // 정담 먼저 맨 뒤에 넣고
                    dq.addLast(String.valueOf(ans2));
                    // 맨 앞에 연산자 넣고
                    dq.addFirst(op1);
                    // 그 앞에 숫자 넣기
                    dq.addFirst(String.valueOf(a));
                }
            } else {
                // 7 이상이면 문제대로 처리
                a = Long.parseLong(dq.pollFirst());
                op1 = dq.pollFirst();
                b = Long.parseLong(dq.pollFirst());

                d = Long.parseLong(dq.pollLast());
                op2 = dq.pollLast();
                c = Long.parseLong(dq.pollLast());

                int compare = higher(op1, op2);
                long ans1 = calculate(a, b, op1);
                long ans2 = calculate(c, d, op2);

                if (compare == 1 || (compare == 0 && ans1 >= ans2)) {
                    // 위와 마찬가지로 복구
                    // 정답 넣고
                    dq.addFirst(String.valueOf(ans1));
                    // 안 쓴 뒤쪽 데이터 복구
                    dq.addLast(String.valueOf(c));
                    dq.addLast(op2);
                    dq.addLast(String.valueOf(d));
                } else {
                    dq.addLast(String.valueOf(ans2));
                    // 안 쓴 앞쪽 데이터 복구
                    dq.addFirst(String.valueOf(b));
                    dq.addFirst(op1);
                    dq.addFirst(String.valueOf(a));
                }
            }
        }
    }

    static int higher(String op1, String op2) {
        int p1 = (op1.equals("*") || op1.equals("/")) ? 1 : 0;
        int p2 = (op2.equals("*") || op2.equals("/")) ? 1 : 0;
        if (p1 > p2) {
            return 1;
        }
        if (p2 > p1) {
            return 2;
        }
        return 0;
    }

    static long calculate(long a, long b, String op) {
        if (op.equals("+")) {
            return a + b;
        }
        if (op.equals("-")) {
            return a - b;
        }
        if (op.equals("*")) {
            return a * b;
        }
        return a / b;
    }
}