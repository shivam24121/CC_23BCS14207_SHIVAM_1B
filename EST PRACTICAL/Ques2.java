import java.util.*;
public class TopoSort {
    public static void main(String[] args){
        Scanner obj=new Scanner(System.in);
        System.out.println("enter number of vertices");
        int n=obj.nextInt();
        ArrayList<ArrayList<Integer>>adj=new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        int[] indegree=new int[n];
        System.out.println("enter number of edges");
        int e=obj.nextInt();
        for(int i=0;i<e;i++){
            int u=obj.nextInt();
            int v=obj.nextInt();
            adj.get(u).add(v);
            indegree[v]++;
        }
        Queue<Integer>q=new ArrayDeque<>();
        for(int i=0;i<n;i++){
            if(indegree[i]==0){
                q.offer(i);
            }
        }
        int ct=0;
        while(!q.isEmpty()){
            int curr=q.poll();
            ct++;
            for(int neigh:adj.get(curr)){
                indegree[neigh]--;
                if(indegree[neigh]==0){
                    q.offer(neigh);
                }
            }
        }
        System.out.println(ct==n?"Valid topo Sort":"Invalid");
    }
}
