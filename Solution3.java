class Solution
{
    public int[][] nearest(int[][] grid)
    {
        int n=grid.length;
        int m=grid[0].length;
        int[][] res=new int[n][m];
        boolean[][] visited=new boolean[n][m];
        Queue<int[]> q=new LinkedList<>();
        int i,j;
        for(i=0;i<n;i++)
        {
            for(j=0;j<m;j++)    
            {
                if(grid[i][j]==1)
                {
                    q.add(new int[]{i,j,0});
                    visited[i][j]=true;
                }
            }
        }
        int[][] dir={{-1,0},{0,1},{1,0},{0,-1}};
        while(!q.isEmpty())
        {
            int[] x=q.poll();
            int r=x[0];
            int c=x[1];
            int dist=x[2];
            res[r][c]=dist;
            for(int[] d:dir)
            {
                int nr=r+d[0];
                int nc=c+d[1];
                if(nr>=0 && nr<n && nc>=0 && nc<m && !visited[nr][nc])
                {
                    visited[nr][nc]=true;
                    q.add(new int[]{nr,nc,dist+1});
                }
            }
        }
        return res;
    }
}