public class Solution {
     class IntervalComparator implements Comparator<Interval>{
        public int compare(Interval a, Interval b){
            return a.start-b.start;
        }
    }
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ans = new ArrayList<Interval>();
        if(intervals.size()==0) return ans;
        Collections.sort(intervals, new IntervalComparator());
        Interval compare = intervals.get(0);
       // boolean f = false;
        for(Interval i: intervals){
            if(i.start>=compare.start&&i.start<=compare.end){
                compare.start = Math.min(compare.start, i.start);
                compare.end = Math.max(compare.end, i.end);
                //f = true;
                continue;
            }else if(i.start>compare.end){
                ans.add(compare);
                compare = i;
            }
           
            
        }
         ans.add(compare);
        return ans;
    }
}