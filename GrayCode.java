/*For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.*/
public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> ls = new ArrayList<Integer>();
        if(n==0){
             ls.add(0);
             return ls;
        }
        ls.add(0);
        ls.add(1);
        if(n==1)return ls;
        int count=1;
        List<Integer> nL = null;
        while(count<n){
            nL = new ArrayList<Integer>();
            nL.addAll(ls);
            for(int i=ls.size()-1;i>=0;i--){
                int num = ls.get(i);
                num = num|1<<count;
               // System.out.println(num);
                nL.add(num);
            }
            ls = nL;
            count++;
        }
        return ls;
    }
}