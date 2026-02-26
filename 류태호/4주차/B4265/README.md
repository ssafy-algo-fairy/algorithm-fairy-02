### 📌 문제 정보

- **번호:** 4256  
- **제목:** 트리  
- **난이도:** Gold 2  
- **분류:** 트리, 재귀, 분할 정복

---

### 💡 접근 방식

> 전위 순회(preorder)와 중위 순회(inorder) 결과가 주어졌을 때, 트리를 직접 구성하지 않고도 재귀적으로 구간을 분할하여 후위 순회(postorder) 결과를 출력했습니다.

---

### 🔹 핵심 아이디어

- **전위 순회**는 항상 `루트 → 왼쪽 → 오른쪽` 순서이므로, 현재 서브트리의 루트는 `preorder[preL]`로 고정
- **중위 순회**는 `왼쪽 → 루트 → 오른쪽` 순서이므로, 루트의 위치를 기준으로 왼쪽/오른쪽 서브트리 구간을 나눠짐
- 중위 순회에서 루트의 위치를 빠르게 찾기 위해 `pos[value] = inorder에서의 인덱스` 배열을 만들어 O(1)로 접근
- 왼쪽 서브트리 크기(`leftSize`)를 이용해 preorder 구간도 동일하게 잘라 재귀 호출
- 후위 순회는 `왼쪽 → 오른쪽 → 루트`이므로, 왼쪽과 오른쪽 재귀 호출이 끝난 뒤 루트를 출력하면 됨

---

### 🔹 구현 단계

#### 1단계 - 입력 및 pos 배열 구성
- `pos[inorder[i]] = i` 형태로 중위 순회에서 각 노드 값의 위치를 저장합니다.

#### 2단계 - 재귀로 구간 분할
- `findPostorder(preL, preR, inL, inR)`는 preorder/inorder 구간이 나타내는 **같은 서브트리**의 후위 순회를 출력
- 루트 기준으로 구간을 나눈 뒤 왼쪽/오른쪽을 재귀적으로 처리하고 마지막에 루트를 출력

---

### 💻 핵심 코드

```java
static void findPostorder(int preL, int preR, int inL, int inR) {
    if (preL > preR || inL > inR) return;

    int root = preorder[preL];
    int rootIdx = pos[root];
    int leftSize = rootIdx - inL;

    findPostorder(preL + 1, preL + leftSize, inL, rootIdx - 1);
    findPostorder(preL + leftSize + 1, preR, rootIdx + 1, inR);

    sb.append(root).append(' ');
}
```

---

### ⏳ 복잡도 분석

- **시간 복잡도:** O(N)
    - 각 노드를 한 번씩만 방문
    - `pos` 배열 덕분에 inorder에서 루트 위치 탐색이 O(1)

- **공간 복잡도:** O(N)
    - preorder/inorder/pos 배열과 재귀 호출 스택

---

### ⚠️ 어려웠던 점
- 오랜만에 트리 순회(preorder, inorder, postorder)를 다시 보니 각각의 순서와 의미가 순간적으로 헷갈려서 이번에 다시 정리했습니다.
- 재귀함수 구현할 때 범위 정하는 게 헷갈려서 고민했습니다.