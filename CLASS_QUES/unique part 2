class Solution {
    public int uniquePathsWithObstacles(int[][] grid) {
        
        int n=grid.length;
        int m=grid[0].length;

        int[] dp=new int[m];

        for(int i=0;i<m;i++){
            if(grid[0][i]==1){
                break;
            }
            dp[i]=1;
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    dp[j]=0;
                }
                else{
                    if(j>0)dp[j]+=dp[j-1];
                }
            }
        }
        return dp[m-1];
    }
}
