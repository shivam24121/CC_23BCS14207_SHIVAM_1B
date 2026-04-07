class Solution {
    public int[] dijkstra(int n, int[][] edges, int src) {
        
        ArrayList<ArrayList<int[]>>adj=new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for(int[] e:edges){
            int u=e[0];
            int v=e[1];
            int w=e[2];
            adj.get(u).add(new int[]{v,w});
            adj.get(v).add(new int[]{u,w});
        }
        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        PriorityQueue<int[]>pq=new PriorityQueue<>((a,b)->Integer.compare(a[1],b[1]));
        pq.offer(new int[]{src,0});
        
        while(!pq.isEmpty()){
            int[] curr=pq.poll();
            int u=curr[0];
            int door=curr[1];
            
            if(dist[u]<door)continue;
            
            for(int[] neigh:adj.get(u)){
                if(dist[u]+neigh[1]<dist[neigh[0]]){
                    dist[neigh[0]]=dist[u]+neigh[1];
                    pq.offer(new int[]{neigh[0],dist[neigh[0]]});
                }
            }
        }
        return dist;
    }
}
