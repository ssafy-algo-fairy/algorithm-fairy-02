## 이슈
Ref : #125

## 📝 문제 요약

N명으 사람들이 살고 있는 석호촌의 계보를 복원하고자하자.

현재 살고있는 사람들의 이름과 x→y 형태의 조상을 표시하는 입력을 받아 계보를 완성하라

## 💡 접근 방법

- **알고리즘/자료구조: 위상정렬**

## ✅ 풀이 과정

- 만약 모든 조상을 알고 있는 것이 아니라면 어려웠을 것이다.
- 규칙을 통해 부모간의 상대적인 위치를 파악해야하기 때문이다.
  - 자식의 자식을 따라가며 추가하려는 자식이 이미 자식에 있는지 파악 등
- 하지만 이번 문제는 모든 조상을 알기 때문에 조상의 수만 세어주면 됨
- in_degree로 조상의 수를 표현하였고, 정확히 1 차이 날때만 직계라고 보면 됨
- 루트가 여러개일 수 있기때문에 자식을 Map에 저장해 줌

## 📌 회고
- 위상 정렬은 꽤나 자주나오는데 패턴이 어느정도 정해져있는 것 같음
- 문제를 똑바로 읽자. 처음에 모든 조상이 주어지는 지 모르고 엄청 헤맴
- 문제를 똑바로 읽자. 문제를 똑바로 읽자. 문제를 똑바로 읽자.

## 🧑‍💻코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class BOJ_21276 {
    static Map<String, Set<String>> children;
    static Map<String, Integer> indegrees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        children = new TreeMap<>();
        indegrees = new TreeMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            String name = st.nextToken();
            children.put(name, new TreeSet<>());
            indegrees.put(name, 0);
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String parent = st.nextToken();

            children.get(parent).add(child);
            indegrees.put(child, indegrees.get(child) + 1);
        }

        int rootCount = 0;
        List<String> roots = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : indegrees.entrySet()) {
            if (entry.getValue() == 0) {
                rootCount++;
                roots.add(entry.getKey());
            }
        }

        sb.append(rootCount).append("\n");
        for (String root : roots) sb.append(root).append(" ");
        sb.append("\n");

        for (String name : children.keySet()) {
            sb.append(name).append(" ");

            int indegree = indegrees.get(name);
            int directCount = 0;
            List<String> directChildren = new ArrayList<>();

            for (String child : children.get(name)) { //직계 찾기
                if (indegrees.get(child) == indegree + 1) {
                    directCount++;
                    directChildren.add(child);
                }
            }

            sb.append(directCount).append(" ");
            for (String child : directChildren) sb.append(child).append(" ");
            sb.append("\n");
        }

        System.out.println(sb);
    }
}

```
