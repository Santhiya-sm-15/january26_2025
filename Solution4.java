class Solution {
    static char[][] fill(char mat[][]) {
        int n=mat.length,m=mat[0].length,i,j;
        boolean[][] visited=new boolean[n][m];
        int[][] dir={{-1,0},{0,1},{0,-1},{1,0}};
        Queue<int[]> q=new LinkedList<>();
        for(i=0;i<m;i++)
        {
            if(mat[0][i]=='O')
            {
                q.add(new int[]{0,i});
                visited[0][i]=true;
            }
            if(mat[n-1][i]=='O')
            {
                q.add(new int[]{n-1,i});
                visited[n-1][i]=true;
            }
        }
        for(i=0;i<n;i++)
        {
            if(mat[i][0]=='O')
            {
                q.add(new int[]{i,0});
                visited[i][0]=true;
            }
            if(mat[i][m-1]=='O')
            {
                q.add(new int[]{i,m-1});
                visited[i][m-1]=true;
            }
        }
        while(!q.isEmpty())
        {
            int[] x=q.poll();
            for(int[] d:dir)
            {
                int nr=x[0]+d[0];
                int nc=x[1]+d[1];
                if(nr>=0 && nr<n && nc>=0 && nc<m && !visited[nr][nc] && mat[nr][nc]=='O')
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
                if(!visited[i][j] && mat[i][j]!='X')
                    mat[i][j]='X';
            }
        }
        return mat;
    }
}