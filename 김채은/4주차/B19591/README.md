## **🔍 문제 요약**

- **수식 계산**
    1. 수식에서 맨 앞의 연산자, 또는 맨 뒤의 연산자 먼저 계산한다. 단, 음수의 부호는 연산자로 취급하지 않는다.
    2. 곱셈, 나눗셈을 덧셈, 뺄셈보다 더 먼저 계산한다.
    3. 연산자의 우선순위가 같다면 해당 연산자를 계산했을 때 결과가 큰 것부터 계산한다. 
    4. 계산했을 때 결과 값 또한 같다면 앞에 것을 먼저 계산한다.
- 수식이 잘못들어오는 경우 x
- 맨앞에는 음수 가능 → -1 - 1 (O) 2 + -3 (x)
- 불필요한 0 가능 → 0002 (O)

- 수식 길이 ≤ 10^6 이하인 수식
- 계산 중 모든 수는 −2^63 이상 2^63 ⇒ long

---

## **💡문제 접근 / 풀이 전략**

- **파싱**
    - 맨앞에는 음수 가능
        - 음수 하나만 나오고 끝날수도 있다는 점을 유의하자…
    - 이후로는 숫자, 연산자 번갈아서 나오고 의미없는 0도 가능하다
    - 따라서 숫자를 받아주면서 연산자가 나오는 순간 숫자 저장
- 양끝에서 숫자, 연산자 빼야하므로 Array deque 사용
    - 순서에 주의해서 잘 뺏다가 넣었다가…

---

## **✅ 코드 & 소요 시간**

```java
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

```

58208 kb / 372ms

---

## **✍️ 회고**

- 더 고민은 안해봤지만 더 좋은 방법이 있을 것 같다는 생각이 드네요~~…

---

## **🧩 다른 풀이 (선택)**

```java

```

---
