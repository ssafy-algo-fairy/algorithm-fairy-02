# BOJ 2629 - 양팔저울

## 📌 문제 설명

최대 500g의 추 최대 30개를 이용하여 구슬의 무게를 측정할 수 있는가를 판단하는 문제

## 💡 해결 아이디어

1. 추를 구슬과 같은 저울에 올리는 케이스는 구슬의 무게가 추가된 것이기 때문에 내가 추들로 만든 무게에서 빼면 되겠다.
2. 추 개수도 30개밖에 안되고 최대무게라 해봤자 30\*500=15000이라 -15000~15000 3만개밖에 안되네? => DP
3. array에서 음수를 표현할 수 없으니 15000을 0으로 잡고 DP를 만들어야겠다.
4. 이 추를 이용하기 전 가능한 조합에서 이 추를 뺀 값과 더한 값 또는 그대로인 값을 측정할 수 있겠다.
5. 구슬의 무게를 측정할 때 15000을 더해서 index 맞춰주기!

## 🧠 코드 해설

```java
boolean[][] dp = new boolean[n+1][30001];
		dp[0][15000] = true;
```

추를 0개 썼을 때 0의 무게를 만들 수 있음.(초기값설정)

```java
for (int i = 1; i <= n; i++) {
			int input = Integer.parseInt(st.nextToken());

			for (int j = 0; j <= 30000; j++) {
				if (dp[i-1][j]) {
					dp[i][j-input] = true;
					dp[i][j] = true;
					dp[i][j+input] = true;
				}
			}
		}
```

추의 무게를 받아서 이 추를 이용하기 전 가능한 조합에서 이 추를 뺀 값과 더한 값 또는 그대로인 값을 true로 설정

```java
for (int i = 0; i < m; i++) {
			int input = Integer.parseInt(st.nextToken()) + 15000;
			if (input > 30000) sb.append("N ");
			else {
				if (dp[n][input]) sb.append("Y ");
				else sb.append("N ");
			}
		}
```

구슬 무게 측정할 때 15000을 더해서 index 맞춰주기  
ArrayIndexOutOfBound 꼭 체크!
