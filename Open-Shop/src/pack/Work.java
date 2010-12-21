package pack;

public class Work{
    private int beginTime=0;
    private int workTime=0;
    private int work=0;
    
    public Work(int bt, int wt, int w){
        beginTime=bt;
        workTime=wt;
        work=w;
    }
    
    public int getBeginTime(){
        return beginTime;
    }
    
    public int getWorkTime(){
        return workTime;
    }
    
    public int getWork(){
        return work;
    }
    
    public boolean equals(Work w){
        if (beginTime!=w.getBeginTime()) return false;
        if (workTime!=w.getWorkTime()) return false;
        if (work!=w.getWork())return false;
        return true;
    }
}