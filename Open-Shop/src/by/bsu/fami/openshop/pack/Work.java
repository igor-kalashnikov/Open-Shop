package by.bsu.fami.openshop.pack;

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
}