public class diskTile{
    protected int x;
    protected int y;
    
    diskTile(){
	x=-1;
	y=-1;
    }

    diskTile(int x,int y){
	this.x=x;
	this.y=y;
    }
    
    public int getX(){
	return x;
    }
    public int getY(){
	return y;
    }

    public int compareTo(diskTile b){
	if (x==b.x && y==b.y) return 0;
	else return 1;
    }
    
    public void setX(int x){
	this.x=x;
    }
    
    public void setY(int y){
	this.y=y;
    }
    
}


