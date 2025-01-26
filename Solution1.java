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