# BOJ 19591 - 독특한 계산기

## 📌 문제 설명

당신은 수식을 독특한 방식으로 계산해야 한다. 수식을 계산하는 방식은 다음과 같다.

1. 수식에서 맨 앞의 연산자, 또는 맨 뒤의 연산자 먼저 계산한다. 단, 음수의 부호는 연산자로 취급하지 않는다.
2. 곱셈, 나눗셈을 덧셈, 뺄셈보다 더 먼저 계산한다.
3. 연산자의 우선순위가 같다면 해당 연산자를 계산했을 때 결과가 큰 것부터 계산한다.
4. 계산했을 때 결과 값 또한 같다면 앞에 것을 먼저 계산한다.

## 💡 해결 아이디어

1. 입력된 값을 앞뒤로 수정해야되네? => Deque
2. input을 하나씩 쪼개서 연산자가 들어올때마다 앞에 들어온 것들을 숫자로 바꿔서 deque에 저장해야겠다.
3. 연산자가 1개일 경우 앞뒤로 poll을 하면 계산을 할 수가 없으니까 if로 예외케이스를 만들어야겠다.
4. 연산자가 1개도 없을 경우(input이 하나의 숫자일 경우) 바로 출력하는 코드도 넣어야겠네.

## 🧠 코드 해설

```java
num.append(st[0]); //-부호일 수도 있으니까 for문 돌리지 않고 따로 처리
```

input의 제일 첫번째 char은 반드시 -부호 혹은 숫자니 num에 append

```java
for (int i = 1; i < st.length; i++) {
			String input = st[i];
			if (operator.contains(input)) {
				ops.add(input);
				nums.add(Long.parseLong(num.toString()));
				num = new StringBuilder();
			} else {
				num.append(input);
			}
		}
		nums.add(Long.parseLong(num.toString()));
```

연산자일 경우 앞서 저장한 num을 숫자로 바꿔서 저장  
그리고 num 초기화  
연산자가 아닐 경우 그냥 num에 이어붙이기  
마지막 숫자 저장도 잊지 않기

```java
		if (ops.isEmpty()) System.out.println(nums.poll());
```

처음부터 연산자가 1개도 없을 경우(input이 하나의 숫자일 경우) 미리 체크하고 print  
어짜피 while문은 실행되지 않음

```java
		if (ops.size() == 1) {
				long answer = calc(nums.poll(), nums.poll(), ops.poll());
				System.out.println(answer);
				break;
			}
```

ops size가 1일 경우(연산해야할 연산자가 1개 남았을 경우) 양쪽에서 poll을 하는 현재 알고리즘 특성상 오류가 날 것이므로 따로 예외케이스 체크 후 break
