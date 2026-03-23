class Solution {
    
    static int[] parent;
    static int[] size;
    
    static public void DSU(int n){
        
        parent=new int[n];
        size=new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
            size[i]=1;
        }
    }
    
    static public int find(int u){
        if(u!=parent[u]){
            parent[u]=find(parent[u]);
        }
        return parent[u];
    }
    
    static public void union(int u,int v){
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
            size[v]+=size[u];
            parent[u]=v;
        }
    }
    
    static int kruskalsMST(int n, int[][] edges) {
       
        DSU(n);
        int sum=0;
       
        for(int i=0;i<edges.length;i++){
            int u=edges[i][0];
            int v=edges[i][1];
            int w=edges[i][2];
            
            if(u>v){
                int temp=u;
                u=v;
                v=temp;
            }
            edges[i][0]=u;
            edges[i][1]=v;
        }
        Arrays.sort(edges,(a,b)->{
            return a[2]-b[2];
        });
        
        int current=0;
        
        for(int i=0;i<edges.length;i++){
            if(current==n-1)break;
            int u=edges[i][0];
            int v=edges[i][1];
            int w=edges[i][2];
            if(find(u)==find(v)){
                continue;
            }
            union(u,v);
            sum+=w;
            current++;
        }
        return sum;
    }
}
