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