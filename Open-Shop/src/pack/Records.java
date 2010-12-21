package pack;

import java.util.ArrayList;

public class Records {

    private ArrayList<ArrayList<ArrayList<Work>>> records=null;
    
    public Records(){
        records=new ArrayList<ArrayList<ArrayList<Work>>>();
    }
    
    public void add(ArrayList<ArrayList<Work>> value){
        records.add(value);
    }
    
    public ArrayList<ArrayList<Work>> get(int i){
        return records.get(i);
    }
    
    public int length(){
        return records.size();
    }
    
    public void clear(){
        records.clear();
    }
    
    public boolean contains(ArrayList<ArrayList<Work>> w){
        for (int i=0; i<records.size(); i++){
            ArrayList<ArrayList<Work>> ww=records.get(i);
            if (equalse(ww, w)) return true;
        }
        return false;
    }
    
    private boolean equalse(ArrayList<ArrayList<Work>> ww, ArrayList<ArrayList<Work>> w){
        for (int j=0; j<ww.size(); j++)
            for (int p=0; p<ww.get(j).size(); p++)
                if (!(ww.get(j).get(p)).equals(w.get(j).get(p))) return false;
        return true;
    }
}
