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