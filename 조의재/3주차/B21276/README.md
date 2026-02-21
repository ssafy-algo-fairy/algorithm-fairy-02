# 가계도(트리) 복원하기 풀이 발표

---

## 🌳 문제 핵심

> 조상-자손 간의 모든 관계가 주어졌을 때, 
> **시조(Root)** 를 찾고 **직속 자식(Direct Child)** 관계만 걸러내어 원래의 가계도(트리)를 복원하는 문제입니다.

---

## 💡 핵심 알고리즘 : 위상 정렬 (Topological Sort)

```text
indegree(진입차수) = 자신을 가리키는 조상의 수
  └─ indegree == 0 인 노드들을 '시조(Root)'로 판별
      └─ Queue에 넣고 위상 정렬 시작
           └─ 연결된 자손의 indegree를 1씩 감소
               └─ indegree가 0이 되는 순간의 부모가 **직속 부모**

```
while(!que.isEmpty()){
    String now = que.poll();
    for(String name : allChild.get(now)){
        // 진입차수가 0이 되는 순간 = 현재 노드(now)가 직속 부모임
        if(--indegree[nameToNum.get(name)] == 0){
            que.add(name);
            onlyChild.get(now).add(name); // 직속 자식으로 등록
        }
    }
}
```

항목,내용
탐색 방식,위상 정렬 (Queue 활용)
이름-인덱스 매핑,"HashMap<String, Integer> (문자열 처리를 위한 인덱싱)"
자식 자동 정렬,TreeSet<String> (직속 자식을 사전순으로 자동 정렬)
조상-자손 관계,"Map<String, List<String>> allChild (주어진 모든 관계 저장)"

Key Point:
문자열(이름) 기반의 노드들을 다루기 위해 HashMap을 사용해 인덱스로 변환하여 indegree 배열을 관리한 점이 훌륭합니다. 또한, 출력 조건인 **'사전순 정렬'**을 만족시키기 위해 직속 자식 저장소로 TreeSet을 활용하여 정렬 비용을 효율적으로 처리했습니다.

🎯 결론 및 배울 점
위상 정렬의 응용: 단순히 순서를 정하는 것을 넘어, 트리 구조에서 **직속 간선(Direct Edge)**을 추려내는 데 위상 정렬이 어떻게 쓰이는지 보여주는 정석적인 코드입니다.

적절한 자료구조 선택: HashMap(빠른 접근)과 TreeSet(자동 정렬)을 목적에 맞게 혼용하여 코드의 가독성과 성능을 모두 잡았습니다.