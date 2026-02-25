package boj19591;

import java.io.*;
import java.util.*;

public class Main {

	static ArrayDeque<Long> numbers = new ArrayDeque<>();
	static ArrayDeque<Character> operators = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line = br.readLine();
		long cur = 0;
		boolean firstMinus = false;

		// 파싱
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);

			// 음수 입력 (맨앞)
			if (i == 0 && c == '-') {
				firstMinus = true;
			}

			// 연산자 입력
			else if (c == '-' || c == '+' || c == '/' || c == '*') {
				if (!firstMinus)
					numbers.offer(cur);
				else
					numbers.offer(-1 * cur);
				operators.offer(c);

				firstMinus = false;
				cur = 0;
			} else { // 숫자 입력
				cur = cur * 10 + (c - '0');
			}
		}
		if (!firstMinus)
			numbers.offer(cur);
		else
			numbers.offer(-1 * cur);

		// 숫자만 있는 경우
		if (operators.isEmpty()) {
			System.out.println(numbers.pollFirst());
			return;
		}

		while (operators.size() > 1) {
			char op1 = operators.peekFirst();
			char op2 = operators.peekLast();

			// 앞
			long a = numbers.pollFirst();
			long b = numbers.pollFirst();
			long f = calc(op1, a, b);
			numbers.offerFirst(b);
			numbers.offerFirst(a);

			// 뒤
			a = numbers.pollLast();
			b = numbers.pollLast();
			long l = calc(op2, b, a);
			numbers.offerLast(b);
			numbers.offerLast(a);

			if (op1 == '*' || op1 == '/') {
				if (op2 == '*' || op2 == '/') { // 비교
					if (f >= l)
						opFirst(f);
					else
						opLast(l);
				} else { // 1 > 2
					opFirst(f);
				}
			} else {
				if (op2 == '*' || op2 == '/') { // 2 > 1
					opLast(l);
				} else { // 비교
					if (f >= l)
						opFirst(f);
					else
						opLast(l);
				}
			}
		}

		System.out.println(calc(operators.pollFirst(), numbers.pollFirst(), numbers.pollFirst()));
	}

	static long calc(char op, long a, long b) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			return a / b;
		}
		return 0;
	}

	static void opFirst(long f) {
		numbers.pollFirst();
		numbers.pollFirst();
		numbers.offerFirst(f);
		operators.pollFirst();
	}

	static void opLast(long l) {
		numbers.pollLast();
		numbers.pollLast();
		numbers.offerLast(l);
		operators.pollLast();
	}
}
