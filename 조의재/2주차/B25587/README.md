# B25587 - Flooded Land

## 문제 설명
이 문제는 특정 지역의 토지 용량과 침수 수치에 대한 정보를 바탕으로, 여러 개의 토지를 연결하는 연합을 관리하는 문제입니다. 사용자는 두 가지 유형의 쿼리를 수행할 수 있습니다: 
1. 두 개의 토지를 연결하는 연합을 생성하는 쿼리
2. 현재 침수된 토지의 수를 출력하는 쿼리

## 난이도
- **난이도:** Gold 3 (백준 기준)

## 입출력
- **입력:**
  - 첫째 줄: 정수 N (토지의 수), M (쿼리 수)
  - 둘째 줄: 각 토지의 용량 (capacity)
  - 셋째 줄: 각 토지의 침수 수치 (flood)
  - 이후 M개의 줄: 쿼리 정보
- **출력:**
  - 각 쿼리 유형 2에 대해 현재 침수된 토지 수를 출력

## 시간 복잡도
- **입력 처리:** O(N + M)
- **쿼리 처리:** 각 쿼리는 `find`와 `union` 연산을 포함하며, 이는 거의 O(1) 시간에 수행될 수 있습니다. 전체 쿼리 처리 시간은 O(M).
- **총 시간 복잡도:** O(N + M)

## 공간 복잡도
- **배열:** `capacity`, `flood`, `parent`, `count` 각 N 크기의 배열이 필요하므로 O(N).
- **총 공간 복잡도:** O(N)

## 핵심 코드 분석


Type 1: Union (연합 생성)
```
else {
    int land1 = Integer.parseInt(st.nextToken()) - 1;
    int land2 = Integer.parseInt(st.nextToken()) - 1;

    union(land1, land2);
}
```


Type 2: Count (침수된 토지 수 출력)
```
if(type == 2){
    sb.append(ans + "\n");
}
```


4. find 메소드
```
static int find(int x){
    if(x == parent[x]) return x;
    else return parent[x] = find(parent[x]);
}
```

5. union 메소드
```
    static void union(int x, int y){
        int px = find(x);
        int py = find(y);

        if(px == py) return;

        if(capacity[py] < flood[py]) ans -= count[py];
        if(capacity[px] < flood[px]) ans -= count[px];

        parent[py] = px;
        capacity[px] += capacity[py];
        flood[px] += flood[py];
        count[px] += count[py];

        if(capacity[px] < flood[px]) ans += count[px];
    }
```