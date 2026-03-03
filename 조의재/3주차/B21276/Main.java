import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb;
    static int N;
    static int[] indegree;
    static String[] names;
    
    static HashMap<String, Integer> nameToNum = new HashMap<>();
    static Map<String, TreeSet<String>> onlyChild = new HashMap<>();
    static Map<String, List<String>> allChild = new HashMap<>();

    public static void main(String args[])throws IOException{
        inputFile();
        solution();
        System.out.println(sb.toString());
    }
    static void inputFile()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        names = new String[N];

        
        indegree = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            names[i] = st.nextToken();

            nameToNum.put(names[i], i);

            onlyChild.put(names[i], new TreeSet<>());
            allChild.put(names[i], new ArrayList<>());
        }
        
        int M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String parent = st.nextToken();

            indegree[nameToNum.get(child)]++;
            allChild.get(parent).add(child);
        }
    }

    static void solution(){
        List<String> root = new ArrayList<>();
        Queue<String> que = new LinkedList<>();

        for(int i=0; i<N; i++){
            if(indegree[i] == 0) root.add(names[i]);
        }
        root.sort((s1,s2)-> s1.compareTo(s2));

        sb.append(root.size()).append("\n");
        for(String names: root){
            sb.append(names).append(" ");
            que.add(names);
        }
        sb.append("\n");

        while(!que.isEmpty()){
            String now = que.poll();
            for(String name : allChild.get(now)){
                if(--indegree[nameToNum.get(name)] == 0){
                    que.add(name);
                    onlyChild.get(now).add(name);
                }
            }
        }

        Arrays.sort(names);
        for(int i=0; i<N; i++){
            String name[] = onlyChild.get(names[i]).toArray(new String[0]);
            
            sb.append(names[i]).append(" ").append(name.length);
            for(int j=0; j<name.length; j++){
                sb.append(" ").append(name[j]);
            }
            sb.append("\n");
        }
    }
}