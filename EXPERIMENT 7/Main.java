class Solution {

    static class Pair{
        int node;
        int distance;
        int stop;
        Pair(int n,int d,int s){
            this.node=n;
            this.distance=d;
            this.stop=s;
        }
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        
        ArrayList<ArrayList<int[]>> adj=new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int[] e:flights){
            int u=e[0];
            int v=e[1];
            int w=e[2];
            adj.get(u).add(new int[]{v,w});
        }

        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;

        Queue<Pair>q=new LinkedList<>();
        q.offer(new Pair(src,0,0));
        

        while(!q.isEmpty()){
            Pair curr=q.poll();
            int currNode=curr.node;
            int currDist=curr.distance;
            int currStop=curr.stop;

            if(currStop>k) continue;

            for(int[] neigh:adj.get(currNode)){
                int nextNode=neigh[0];
                int nextDist=neigh[1];
                int cost=nextDist+currDist;

                if(cost<dist[nextNode]){
                    dist[nextNode]=cost;
                    q.offer(new Pair(nextNode,cost,currStop+1));
                }   
            }
        }
        return dist[dst]==Integer.MAX_VALUE?-1:dist[dst];
    }
} 
