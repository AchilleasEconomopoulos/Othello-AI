import java.util.ArrayList;

public class freeMoveTile extends diskTile {

    private ArrayList<Boolean> directions;
    private int value;
    
    freeMoveTile(){
	super();
	directions = new ArrayList();
    }

    freeMoveTile(int x,int y){
	super(x,y);
	
	//0=(Up+Left), 1=(Up), 2=(Up+Right), 3=(Left), 4=(Right), 5=(Down+Left), 6=(Down), 7=(Down+Right)
	directions = new ArrayList(8);
	for(int i =0 ;i<8;i++)
	    directions.add(false);
    }
    
    freeMoveTile(int x,int y,int value){
	super(x,y);
	
	//0=(Up+Left), 1=(Up), 2=(Up+Right), 3=(Left), 4=(Right), 5=(Down+Left), 6=(Down), 7=(Down+Right)
	directions = new ArrayList(8);
	for(int i =0 ;i<8;i++)
	    directions.add(false);
	
	this.value = value;
    }
    
    
    
    public void setValue(int x){
	value = x;
    }
    
    public void setDirections(ArrayList<Boolean> directions){
	for (int i=0;i<directions.size();i++){
	    (this.directions).set(i,directions.get(i));
	}
    }
    
    public int getValue(){
	return value;
    }
    
    public ArrayList<Boolean> getDirections(){
	return directions;
    }
}
