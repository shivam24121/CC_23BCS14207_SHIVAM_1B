class Solution {
    
    int[][] dp;
    int n;
    
    public int tsp(int[][] cost) {
        
        n=cost.length;
        
        dp=new int[1<<n][n];
        
        for(int[] a:dp){
            Arrays.fill(a,-1);
        }
        return solve(1,0,cost);
    }
    public int solve(int mask,int pos,int[][] cost){
        
        if(mask==((1<<n)-1)){
            return cost[pos][0];
        }
        
        if(dp[mask][pos]!=-1){
            return dp[mask][pos];
        }
        
        int res=Integer.MAX_VALUE;
        
        for(int city=0;city<n;city++){
            if((mask&(1<<city))==0){
                res=Math.min(res,cost[pos][city]+solve((mask|(1<<city)),city,cost));
            }
        }
        return dp[mask][pos]=res;
    }
}
