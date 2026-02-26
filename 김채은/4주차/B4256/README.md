## **🔍 문제 요약**

- 이진트리
- 전위순회, 중위순회 결과가 주어지고, 후위순회 결과를 출력하자
- 1 ≤ n 노드개수 ≤ 1000

---

## **💡문제 접근 / 풀이 전략**

<img width="692" height="150" alt="image" src="https://github.com/user-attachments/assets/1810d7fb-2698-4ba0-948e-4c1056569b4f" />

<img width="287" height="191" alt="image" src="https://github.com/user-attachments/assets/eb1f9d33-bcf3-490a-a00c-cb1d37a113a9" />

1. preorder에서 3이 루트인것을 확인 가능 (항상 루트가 앞)
2. inorder에서 3의 위치를 통해 좌, 우 트리를 나눌 수 있음
3. preorder에서 6 루트 확인
4. inorder에서 6의 위치를 통해 좌, 우 트리 나누기
5. 반복…

**preorder에서 루트는 항상 앞에 온다는 점이 중요할듯**

---

## **✅ 코드 & 소요 시간**

```java
package boj4256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int N, root;
	static int[] preorder, inorder;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			preorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			inorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			root = 0;
			findRoot(0, N);
			sb.append("\n");
		}

		System.out.println(sb);
	}

	static void findRoot(int start, int end) {
		if (start >= end)
			return;

		int idx = -1;
		int curRoot = preorder[root++];

		for (int i = start; i < end; i++) {
			if (inorder[i] == curRoot) {
				idx = i;
				break;
			}
		}

		findRoot(start, idx); // 왼
		findRoot(idx + 1, end); // 오

		sb.append(curRoot).append(" ");
	}

}

```

40772 / 408

---

## **✍️ 회고**

- 

---

## **🧩 다른 풀이 (선택)**

```java

```

---
