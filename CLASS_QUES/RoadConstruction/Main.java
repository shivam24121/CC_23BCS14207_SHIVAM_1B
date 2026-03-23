import java.util.*;
import java.io.*;

public class Main{
    
    static int[] parent;
    static int[] size;
    
    public static void dsu(int n){
        parent=new int[n+1];
        size=new int[n+1];
        for(int i=1;i<=n;i++){
            parent[i]=i;
            size[i]=1;
        }
    }
    public static int find(int u){
        if(u!=parent[u]){
            parent[u]=find(parent[u]);
        }
        return parent[u];
    }
    public static void union(int u,int v){
        u=find(u);
        v=find(v);
        
        if(u==v){
            return;
        }
        if(size[u]>=size[v]){
            size[u]+=size[v];
            parent[v]=u;
        }
        else{
            parent[u]=v;
            size[v]+=size[u];
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int q=Integer.parseInt(st.nextToken());
        StringBuilder sb=new StringBuilder();
        
        int max=1,component=n;
        
        dsu(n);
        
        for(int i=0;i<q;i++){
            st=new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());
            if(find(u)!=find(v)){
                component--;
                int root=find(u);
                union(u,v);
                max=Math.max(max,size[root]);
                sb.append(component+" "+max).append("\n");
           
            }
        }
        System.out.println(sb.toString());
    }
}

