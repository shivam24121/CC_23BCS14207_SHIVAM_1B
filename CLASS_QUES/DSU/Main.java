class Solution {
    class DSU{
        
        int[] parent;
        int[] size;
        public void dsu(int n){
            parent=new int[n];
            size=new int[n];
            for(int i=0;i<n;i++){
                parent[i]=i;
                size[i]=1;
            }
        }
        public int find(int u){
            if(u!=parent[u]){
                parent[u]=find(parent[u]);
            }
            return parent[u];
        }
        public boolean union(int u,int v){
            
            u=find(u);
            v=find(v);
            
            if(u==v)return true;
            
            if(size[u]>size[v]){
                size[u]+=size[v];
                parent[v]=u;
            }
            else{
                size[v]+=size[u];
                parent[u]=v;
            }
            return false;
        }
    }
    public int longestCycle(int n, int[][] edges) {
        
        DSU obj=new DSU();
        obj.dsu(n);
        
        int res=-1;
        
        for(int[] e:edges){
            int u=e[0];
            int v=e[1];
            if(obj.find(u)==obj.find(v)){
                res=Math.max(res,obj.size[obj.find(u)]);
            }
            else{
                obj.union(u,v);
            }
        }
        return res;
    }
}
