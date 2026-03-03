# BOJ 4256 - 트리

## 📌 문제 설명

preorder과 inorder의 결과를 주었을 때 postorder의 결과를 출력하는 문제

## 💡 해결 아이디어

1. preorder은 항상 root값이 먼저 나오기 때문에 preorder을 순서대로 순회하면 subtree의 root값을 알 수 있다.
2. 이 root값을 이용해서 inorder에서 이 root값을 찾으면 그 root값을 기준으로 왼쪽은 left tree, 오른쪽은 right tree인 것을 알 수 있다.
3. 이를 반복해서 tree를 구성하고 postorder로 출력하면 끝

## 🧠 코드 해설

```java
	public static class Node {
		int value;
		Node left;
		Node right;

		public Node(int value) {
			this.value = value;
		}

		public void setLeft(Node n) {
			this.left = n;
		}

		public void setRight(Node n) {
			this.right = n;
		}

		public Node getLeft() {
			return left;
		}

		public Node getRight() {
			return right;
		}
	}
```

tree를 구성하기 위한 class Node

```java
public static void makeTree(Node root, int start, int rootNum, int end)
```

preorder 방식으로 tree를 만드는 함수

```java
		// left
		for (int i = start; i < rootNum; i++) {
			if (inorder[i] == preorder[index]) {
				Node left = new Node(inorder[i]);
				root.setLeft(left);
				index++;
				makeTree(left, start, i, rootNum);
				break;
			}
		}
```

rootNum(root의 inorder에서의 위치)을 기준으로 왼쪽을 탐색하면서 다음 root 값을 탐색  
index 값을 계속 올려서 preorder에서 찾는 값을 계속 갱신해준다.

```java
		// right
		for (int i = rootNum+1; i < end; i++) {
			if (inorder[i] == preorder[index]) {
				Node right = new Node(inorder[i]);
				root.setRight(right);
				index++;
				makeTree(right, rootNum+1, i, end);
				break;
			}
		}
```

오른쪽도 마찬가지

```java
	public static void postorder(Node root) {
		if (root.left != null) postorder(root.getLeft());
		if (root.right != null) postorder(root.getRight());
		sb.append(root.value + " ");

	}
```

트리 완성 후 postorder로 출력

## 🚀 느낀점

inorder에서 root의 위치를 찾으면 왼쪽은 left subtree이고 오른쪽은 right subtree라는 발상을 떠올리니 쉽게 풀린 문제였다.
