# january26_2025
The problems that I solved today

1.A company is organizing a meeting and has a list of n employees, waiting to be invited. They have arranged for a large circular table, capable of seating any number of employees. The employees are numbered from 0 to n - 1. Each employee has a favorite person and they will attend the meeting only if they can sit next to their favorite person at the table. The favorite person of an employee is not themself. Given a 0-indexed integer array favorite, where favorite[i] denotes the favorite person of the ith employee, return the maximum number of employees that can be invited to the meeting.

Code:
class Solution 
{
    public int maximumInvitations(int[] favorite) 
    {
        int n=favorite.length,i;
        int[] indegree=new int[n];
        int[] depth=new int[n];
        Arrays.fill(depth,1);
        for(i=0;i<n;i++)
            indegree[favorite[i]]++;
        Queue<Integer> q=new LinkedList<>();
        for(i=0;i<n;i++)
        {
            if(indegree[i]==0)
                q.add(i);
        }
        while(!q.isEmpty())
        {
            int x=q.poll();
            int nxt=favorite[x];
            depth[nxt]=Math.max(depth[nxt],depth[x]+1);
            indegree[nxt]--;
            if(indegree[nxt]==0)
                q.add(nxt);
        }
        int twoCycle=0,longestCycle=0;
        for(i=0;i<n;i++)
        {
            if(indegree[i]==0)
                continue;
            int len=0,cur=i;
            while(indegree[cur]!=0)
            {
                indegree[cur]=0;
                len++;
                cur=favorite[cur];
            }
            if(len==2)
                twoCycle+=depth[cur]+depth[favorite[cur]];
            else
                longestCycle=Math.max(longestCycle,len);
        }
        return Math.max(twoCycle,longestCycle);
    }
}

2.Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges, check whether it contains any cycle or not. The graph is represented as an adjacency list, where adj[i] contains a list of vertices that are directly reachable from vertex i. Specifically, adj[i][j] represents an edge from vertex i to vertex j.

Code:
class Solution {
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        int n=adj.size();
        boolean[] visited=new boolean[n];
        boolean[] path=new boolean[n];
        int i;
        for(i=0;i<n;i++)
        {
            if(!visited[i])
            {
                if(dfs(i,-1,adj,visited,path))
                    return true;
            }
        }
        return false;
    }
    public boolean dfs(int src,int parent,ArrayList<ArrayList<Integer>> adj,boolean[] visited,boolean[] path)
    {
        visited[src]=true;
        path[src]=true;
        for(int a:adj.get(src))
        {
            if(!visited[a])
            {
                if(dfs(a,src,adj,visited,path))
                    return true;
            }
            else if(path[a])
                return true;
        }
        path[src]=false;
        return false;
    }
}

3.Given a binary grid of n*m. Find the distance of the nearest 1 in the grid for each cell. The distance is calculated as |i1  - i2| + |j1 - j2|, where i1, j1 are the row number and column number of the current cell, and i2, j2 are the row number and column number of the nearest cell having value 1. There should be atleast one 1 in the grid. 

Code:
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

4.Given a matrix mat where every element is either 'O' or 'X'. Replace all 'O' or a group of 'O' with 'X' that are surrounded by 'X'. A 'O' (or a set of 'O') is considered to be surrounded by 'X' if there are 'X' at locations just below, just above, just left and just right of it.

Code:
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

5.You are given an n x m binary matrix grid, where 0 represents a sea cell and 1 represents a land cell. A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid. Find the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

Code:
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

6.A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that: Every adjacent pair of words differs by a single letter. Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList. sk == endWord Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

Code:
class pair
{
    String s;
    int x;
    public pair(String s,int x)
    {
        this.s=s;
        this.x=x;
    }
}
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> s=new HashSet<>(wordList);
        Queue<pair> q=new LinkedList<>();
        q.add(new pair(beginWord,1));
        s.add(beginWord);
        int i;
        while(!q.isEmpty())
        {
            pair p=q.poll();
            String a=p.s;
            int x=p.x;
            if(a.equals(endWord))
                return x;
            for(i=0;i<a.length();i++)
            {
                char[] c=a.toCharArray();
                for(char j='a';j<='z';j++)
                {
                    c[i]=j;
                    String ns=new String(c);
                    if(s.contains(ns))
                    {
                        q.add(new pair(ns,x+1));
                        s.remove(ns);
                    }
                }
            }
        }
        return 0;
    }
}
