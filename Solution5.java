class Solution {
    int numberOfEnclaves(int[][] grid) {
        int n=grid.length,m=grid[0].length,i,j,cnt=0;
        boolean[][] visited=new boolean[n][m];
        Queue<int[]> q=new LinkedList<>();
        int[][] dir={{-1,0},{0,1},{0,-1},{1,0}};
        for(i=0;i<n;i++)
        {
            if(grid[i][0]==1)
            {
                q.add(new int[]{i,0});
                visited[i][0]=true;
            }
            if(grid[i][m-1]==1)
            {
                q.add(new int[]{i,m-1});
                visited[i][m-1]=true;
            }
        }
        for(j=0;j<m;j++)
        {
            if(grid[0][j]==1)
            {
                q.add(new int[]{0,j});
                visited[0][j]=true;
            }
            if(grid[n-1][j]==1)
            {
                q.add(new int[]{n-1,j});
                visited[n-1][j]=true;
            }
        }
        while(!q.isEmpty())
        {
            int[] x=q.poll();
            for(int[] d:dir)
            {
                int nr=x[0]+d[0];
                int nc=x[1]+d[1];
                if(nr>=0 && nr<n && nc>=0 && nc<m && !visited[nr][nc] && grid[nr][nc]==1)
                {
                    visited[nr][nc]=true;
                    q.add(new int[]{nr,nc});
                }
            }
        }
        for(i=0;i<n;i++)
        {
            for(j=0;j<m;j++)
            {
                if(!visited[i][j] && grid[i][j]==1)
                    cnt++;
            }
        }
        return cnt;
    }
}