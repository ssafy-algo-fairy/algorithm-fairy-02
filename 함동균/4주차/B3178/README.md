# README.md — 코코스: 최소 정점 수 (Trie 2개로 노드 수 합산)

## 1. 문제 요약
- 길이 `2K`인 단어 `N`개가 주어진다.
- 각 글자는 “정점”에 적히며, 각 단어는 그래프 위의 어떤 경로로 표현되어야 한다.
- 단어 경로는 다음 구조 제약을 만족해야 한다.
  - 첫 정점 in-degree = 0
  - 다음 K-1개 정점 in-degree = 1  → **앞 K구간에서는 ‘합류(merge)’가 금지**
  - 다음 K-1개 정점 out-degree = 1 → **뒤 K구간에서는 ‘분기(branch)’가 금지**
  - 마지막 정점 out-degree = 0
- 목표: 모든 단어가 경로로 존재하도록 하면서 **정점 수가 최소인 그래프의 정점 수** 출력

---

## 2. 핵심 관찰
단어(2K)를 정확히 반으로 쪼개면 된다.

- `prefix = word[0..K-1]` (앞 K글자)
- `suffix = word[K..2K-1]` (뒤 K글자)

제약 때문에
- 앞 K구간은 **중간 합류가 있으면 안 되므로**, 여러 단어가 공유할 수 있는 형태는 “접두사 공유”뿐이다 → **Prefix Trie가 최적**
- 뒤 K구간은 **중간 분기가 있으면 안 되므로**, 여러 단어가 공유할 수 있는 형태는 “접미사 공유(뒤에서 합류)”뿐이다 → **Suffix를 뒤집어 넣는 Trie(= reversed suffix trie)가 최적**

따라서 최소 정점 수는
- `prefixTrie`에 모든 `prefix`를 넣으며 생기는 정점 수 +
- `suffixTrie`에 모든 `suffix`를 **뒤에서부터** 넣으며 생기는 정점 수
의 합으로 계산할 수 있다.

---

## 3. 구현 방식(제공 코드 그대로)

### 3.1 Trie 구조
```java
static class Trie {
    int size;          // 지금까지 새로 생성된 노드 수 누적
    Trie[] child;      // 자식 포인터(레퍼런스) 배열

    Trie() {
        size = 0;
        child = new Trie[26];
    }
}
````

* 자식은 `int[26]`이 아니라 **`Trie[26]` 레퍼런스 배열**로 관리한다.
* `size`는 “현재 Trie에 존재하는 전체 노드 수”를 매번 다시 세는 게 아니라,
  **삽입 과정에서 새로 생긴 노드 수(grown)를 누적**해 둔 값이다.
* 루트 노드는 `size`에 포함시키지 않는다(새로 생성되는 자식 노드만 카운트).

---

### 3.2 Prefix 삽입: `input(String str, int idx)`

* `str`은 길이 K의 prefix
* `idx`번째 문자를 따라 내려가며, 없으면 새 노드를 만든다.
* 새 노드를 만들 때마다 `grown`이 증가하고, 그 값을 위로 반환하며 `size += grown`으로 누적한다.

핵심:

* `child[letter] == null`이면 새 노드 생성 → “정점 +1”
* 반환값 `grown` = 이번 삽입으로 **새로 생긴 노드 수**

---

### 3.3 Suffix 삽입: `rinput(String str, int idx)`

* `str`은 길이 K의 suffix
* 단, `str.charAt(str.length()-1-idx)`로 **뒤에서부터** 삽입한다.
* 즉, suffix를 역순으로 Trie에 넣는 효과가 있다.
* 마찬가지로 새 노드가 생길 때마다 `grown`을 통해 `size`를 누적한다.

---

## 4. 정답 계산

메인 로직은 매우 단순하다.

1. 모든 단어에 대해

   * `trie.input(word.substring(0, k), 0)`  // prefix Trie
   * `rtrie.rinput(word.substring(k), 0)`   // suffix(역순) Trie
2. 최종 출력

   * `trie.getSize() + rtrie.getSize()`

즉,

* prefix 쪽에서 필요한 최소 정점 수 +
* suffix 쪽에서 필요한 최소 정점 수
  를 더한 값이 정답이다.

---

## 5. 시간/공간 복잡도

* 각 단어마다 prefix K글자 + suffix K글자 삽입
* 총 시간: `O(N*K)`
* 노드 수도 최악 `O(N*K)`이지만 실제로는 접두/접미 공유로 줄어든다.
* 알파벳 26개 고정이므로 자식 접근은 O(1)이다.

---

## 6. 오늘의 학습 포인트

* 문제의 “앞 K는 합류 금지 / 뒤 K는 분기 금지” 조건을
  각각 **Prefix Trie / Reversed Suffix Trie**로 분리하면 최적 구조가 자연스럽게 나온다.
* 노드 수는 따로 BFS로 세지 않고,
  삽입 시 **새로 생성되는 노드 수만 누적**해도 전체 노드 수를 얻을 수 있다.
* 구현에서 핵심은

  * prefix는 정방향 삽입
  * suffix는 **역방향 삽입(rinput)**
    이다.

---
