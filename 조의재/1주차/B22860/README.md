# [BOJ] 22860 - 파일 정리 (Small)

### 📌 문제 정보
* **문제 링크**: [백준 22860번](https://www.acmicpc.net/problem/22860)
* **난이도**: Gold 3
* **분류**: 자료 구조, 그래프 탐색, 트리, 깊이 우선 탐색(DFS)

---

### 💡 풀이 전략
이 문제는 폴더와 파일로 이루어진 트리 구조를 파악하고, 특정 폴더 하위(모든 자식 노드 포함)에 존재하는 파일들의 **종류 수**와 **전체 개수**를 구하는 문제입니다.

1.  **자료구조 구축**: `Map<String, Folder>`를 활용해 트리 구조를 저장합니다. 각 `Folder` 객체는 하위 폴더 리스트, 중복 제거를 위한 `Set`, 전체 개수 카운트를 관리합니다.
2.  **후위 순회(Post-order Traversal) 기반 정보 전파**:
    * 쿼리를 처리하기 전, 최하위 폴더부터 루트(`main`)까지 정보를 합쳐 올라가는 방식을 채택했습니다.
    * 자식 폴더의 `Set`을 부모의 `Set`에 `addAll` 하고, 파일 개수를 누적시킴으로써 각 폴더가 자신의 하위 정보를 모두 갖게 만듭니다.
3.  **효율적 쿼리 처리**: 미리 모든 폴더의 정보를 완성해두었으므로, 입력되는 쿼리에 대해 $O(1)$로 즉각적인 응답이 가능합니다.



---

### 💡 핵심 풀이 로직

이 문제는 쿼리가 들어올 때마다 탐색하는 대신, **후위 순회(Post-order Traversal)**를 통해 하위 폴더의 파일 정보를 부모 폴더로 미리 전파(Propagation)하는 것이 핵심입니다.

#### 1. 폴더 객체 구조 (Data Structure)
단순 리스트가 아닌, 스스로의 하위 정보를 저장할 공간을 갖도록 설계했습니다.
```java
static class Folder {
    List<String> childFolder = new ArrayList<>(); // 트리 구조 연결
    Set<String> allChildFile = new HashSet<>();   // 파일 종류 (중복 제거)
    int fileCnt = 0;                              // 파일 전체 개수
}
```


2. 정보 전파 로직 (Post-order Traversal)
자식 폴더의 계산이 완전히 끝난 후, 그 결과를 부모에게 합치는 재귀 방식입니다.

```
public static void computeFolder(String curName) {
    Folder now = folderMap.get(curName);

    for (String child : now.childFolder) {
        // 자식 폴더를 먼저 끝까지 탐색하여 결과 완성
        computeFolder(child); 

        Folder childFolder = folderMap.get(child);
        // 완성된 자식의 정보를 현재 폴더(부모)로 합산
        now.fileCnt += childFolder.fileCnt;
        now.allChildFile.addAll(childFolder.allChildFile);
    }
}
```

3. 쿼리 최적화
전체 경로 문자열을 다 분석하지 않고, 마지막 폴더명만 사용하여 $O(1)$로 결과에 접근합니다.

```
// 예: "main/FolderA/FolderB" -> "FolderB"만 추출
String path[] = br.readLine().split("/");
String target = path[path.length - 1];

Folder now = folderMap.get(target);
sb.append(now.allChildFile.size()).append(" ").append(now.fileCnt).append("\n");
```


복기 및 핵심 포인트
미리 계산하기: 쿼리가 발생할 때마다 DFS를 돌리면 중복 계산이 발생하지만, 후위 순회로 한 번만 처리하면 전체 성능이 비약적으로 향상됩니다.

경로 처리: 쿼리는 전체 경로(main/A/B)로 들어오지만, 폴더 이름이 고유하므로 마지막 폴더명만 추출하여 Map에서 조회하는 방식으로 단순화했습니다.