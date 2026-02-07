import java.io.*;
import java.util.*;

public class Main{
    static int N, M;
    static Map<String, Folder> folderMap = new HashMap<>();
    static class Folder{
        List<String> childFolder = new ArrayList<>();
        Set<String> allChildFile = new HashSet<>();
        int fileCnt = 0;
    }
    public static void main(String args[])throws IOException{
        readInput();
    }

    public static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0; i < N+M; i++){
            st = new StringTokenizer(br.readLine());
            String parent = st.nextToken();
            String name = st.nextToken();
            boolean isFolder = st.nextToken().equals("1") ? true : false;

            folderMap.putIfAbsent(parent, new Folder());

            if(isFolder){
                folderMap.putIfAbsent(name, new Folder());
                folderMap.get(parent).childFolder.add(name);
            }
            else{ 
                Folder folder = folderMap.get(parent);
                folder.allChildFile.add(name);
                folder.fileCnt++;
            }
        }
        computeFolder("main");

        int Q = Integer.parseInt(br.readLine());
        while (Q --> 0){
            String path[] = br.readLine().split("/");
            String target = path[path.length-1];
            
            Folder now = folderMap.get(target);
            sb.append(now.allChildFile.size() + " " + now.fileCnt + "\n");
        }

        System.out.println(sb);
    }

    public static void computeFolder(String curName){
        Folder now = folderMap.get(curName);

        for(String child : now.childFolder){
            computeFolder(child);
            
            Folder childFolder = folderMap.get(child);

            now.fileCnt += childFolder.fileCnt;
            now.allChildFile.addAll(childFolder.allChildFile);
        }
    }
}
