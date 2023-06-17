import java.util.ArrayList;

public class BoardState {

    //Represents the Othello board tiles
    private char[][] board;
    
    private ArrayList<diskTile> bDisks;
    private ArrayList<diskTile> wDisks;
    
    private freeMoveTile lastMove;
    
    
    
    
    //Constructor
    public BoardState(){
	board = new char[8][8];
	for(int i = 0; i<8;i++)
	    for(int j = 0; j<8;j++)
		board[i][j] = ' ';
	
	bDisks = new ArrayList<>();
	wDisks = new ArrayList<>();
	
	board[4][3] = 'X'; bDisks.add(new diskTile(4,3));
	board[3][3] = 'O'; wDisks.add(new diskTile(3,3));
	board[4][4] = 'O'; wDisks.add(new diskTile(4,4));
	board[3][4] = 'X'; bDisks.add(new diskTile(3,4));
	
	lastMove = new freeMoveTile();
    }
    
    public BoardState(BoardState cp){
	board = new char[8][8];
	for(int i=0;i<8;i++){
	    for(int j=0;j<8;j++){
		board[i][j]=(cp.getBoard())[i][j];
	    }
	}
	
	bDisks = new ArrayList<>();
	for(int i=0;i<cp.getBDisks().size();i++)
	    bDisks.add(cp.getBDisks().get(i));
	
	wDisks = new ArrayList<>();
	for(int i=0;i<cp.getWDisks().size();i++)
	    wDisks.add(cp.getWDisks().get(i));
	
	lastMove = cp.lastMove;
    }
    
    
    //Prints the board
    public void print(){
	
	System.out.println("   a b c d e f g h");
	for(int i = 0; i<8;i++){
	    System.out.print((i+1) + " ");
	    
	    for(int j=0;j<8;j++)
		System.out.print("|" + board[i][j]);
	    
	    System.out.print("|");
	    System.out.print(" "+ (i+1)); 
	    System.out.println();
	}
	System.out.println("   a b c d e f g h");
	
    }
    
    
    //Represents a move on the board based on the horizontal and vertical tile labels
    public int move(freeMoveTile c,char player){
	
	lastMove = c;
	
	char opp;
	if(player == 'O') opp = 'X';
	else opp = 'O';
	
	
	//i,j are fixed coordinates of the given tile
	//X,Y are used to navigate towards the 8 directions around the given tile
	int X = c.getX();
	int Y = c.getY();
	diskTile tmp;
	
	board[c.getX()][c.getY()]=player;					//Place the current player's disk in the free tile					
	    
	tmp = new diskTile(c.getX(),c.getY());					//Temporary object

	//Add the new disk into the current player's corresponding list
	if(player=='X'){
	    bDisks.add(tmp);
	}else
	    wDisks.add(tmp);
	
	if(c.getDirections().get(0)){						//Turn all opponent disks found in the (Up+Left) direction
	   
	    
	    while(board[--X][--Y] == opp){					//While detecting opponent disks
		board[X][Y] = player;						//Turn them
		tmp = new diskTile(X,Y);
		
		if(player=='X'){						//If it's Black's turn
		    bDisks.add(tmp);						//Add the new black disk into the corresponding list
		    wDisks.remove(iOfTile2(wDisks,tmp));			// and remove him from the white disk list after finding his index
		}
		else{								//If it's White's turn
		    wDisks.add(tmp);						//Add the new white disk into the corresponding list
		    bDisks.remove(iOfTile2(bDisks,tmp));			// and remove him from the black disk list after finding his index
		}
	    }
	}
	
	X=c.getX();								//Return at the free tile's position to check the next possible direction
	Y=c.getY();
	
	if(c.getDirections().get(1)){						//Turn all opponent disks found in the (Up) direction
	    
	    while(board[--X][Y] == opp){
		board[X][Y] = player;
		tmp = new diskTile(X,Y);
		
		if(player=='X'){
		    bDisks.add(tmp);
		    wDisks.remove(iOfTile2(wDisks,tmp));
		}
		else{
		    wDisks.add(tmp);
		    bDisks.remove(iOfTile2(bDisks,tmp));
		}
	    }
	}
	
	X=c.getX();
	Y=c.getY();
	
	if(c.getDirections().get(2)){						//Turn all opponent disks found in the (Up+Right) direction
	    
	    while(board[--X][++Y] == opp){
		board[X][Y] = player;
		tmp = new diskTile(X,Y);
		
		if(player=='X'){
		    bDisks.add(tmp);
		    wDisks.remove(iOfTile2(wDisks,tmp));
		}
		else{
		    wDisks.add(tmp);
		    bDisks.remove(iOfTile2(bDisks,tmp));
		}
	    }
	}
	
	X=c.getX();
	Y=c.getY();
	
	if(c.getDirections().get(3)){						//Turn all opponent disks found in the (Left) direction
	    
	    while(board[X][--Y] == opp){
		board[X][Y] = player;
		tmp = new diskTile(X,Y);
		
		if(player=='X'){
		    bDisks.add(tmp);
		    wDisks.remove(iOfTile2(wDisks,tmp));
		}
		else{
		    wDisks.add(tmp);
		    bDisks.remove(iOfTile2(bDisks,tmp));
		}
	    }
	}
	
	X=c.getX();
	Y=c.getY();
	
	if(c.getDirections().get(4)){						//Turn all opponent disks found in the (Right) direction
	    
	    while(board[X][++Y] == opp){
		board[X][Y] = player;
		tmp = new diskTile(X,Y);
		
		if(player=='X'){
		    bDisks.add(tmp);
		    wDisks.remove(iOfTile2(wDisks,tmp));
		}
		else{
		    wDisks.add(tmp);
		    bDisks.remove(iOfTile2(bDisks,tmp));
		}
	    }
	}
	
	X=c.getX();
	Y=c.getY();
	
	if(c.getDirections().get(5)){						//Turn all opponent disks found in the (Down+Left) direction
	    
	    while(board[++X][--Y] == opp){
		board[X][Y] = player;
		tmp = new diskTile(X,Y);
		
		if(player=='X'){
		    bDisks.add(tmp);
		    wDisks.remove(iOfTile2(wDisks,tmp));
		}
		else{
		    wDisks.add(tmp);
		    bDisks.remove(iOfTile2(bDisks,tmp));
		}
	    }
	}
	
	X=c.getX();
	Y=c.getY();
	
	if(c.getDirections().get(6)){						////Turn all opponent disks found in the (Down) direction
	    
	    while(board[++X][Y] == opp){
		board[X][Y] = player;
		tmp = new diskTile(X,Y);
		
		if(player=='X'){
		    bDisks.add(tmp);
		    wDisks.remove(iOfTile2(wDisks,tmp));
		}
		else{
		    wDisks.add(tmp);
		    bDisks.remove(iOfTile2(bDisks,tmp));
		}
	    }
	}
	
	X=c.getX();
	Y=c.getY();
	
	if(c.getDirections().get(7)){						//Turn all opponent disks found in the (Down+Right) direction
	   
	    while(board[++X][++Y] == opp){
		board[X][Y] = player;
		tmp = new diskTile(X,Y);
		
		if(player=='X'){
		    bDisks.add(tmp);
		    wDisks.remove(iOfTile2(wDisks,tmp));
		}
		else{
		    wDisks.add(tmp);
		    bDisks.remove(iOfTile2(bDisks,tmp));
		}
	    }
	}
	return 1;								
    }
       
    
    public ArrayList<freeMoveTile> validMoves(char player){
	ArrayList<freeMoveTile> markedTiles = new ArrayList<>();
	
	ArrayList<diskTile> disks;
	if(player=='X') disks = bDisks;
	else disks = wDisks;
	
	
	
	char opp;
	if(player == 'O') opp = 'X';
	else opp = 'O';
	int i;
	
	
	for(diskTile disk : disks){						//Checks all the current player's disks to find all the possible valid moves
	    int X = disk.getX();
	    int Y = disk.getY();
	    boolean insideLoop=false;
	    
	    freeMoveTile c;
	    
	    
	    while(X-1>-1 && Y-1>-1 && board[--X][--Y] == opp)			//If you find an opponent disk (Up+Left) from the current disk, the first free tile in that direction will give you a valid move 
		insideLoop=true;
	    if(insideLoop==true && board[X][Y]==' '){				//Without the insideLoop flag the method also marks all the free tiles around the disk
		c = new freeMoveTile(X,Y);					//Temporary object
		if(iOfTile(markedTiles,c)==-1)					//Checks if the free tile is already added to the list by another disk that has marked a different direction from this one
										//(Here indexOf serves the purpose of a "contains" method)
		    markedTiles.add(c);
		i = iOfTile(markedTiles,c);					//The same method is called to get the exact index of the free tile on the list
										//If the tile was added during this "while" check, its index is size-1. However if it wasn't, we can't know its exact index.
		markedTiles.get(i).getDirections().set(7,true);			//If we follow the (Up+Left) path to the free tile that represents a valid move, then the valid path that starts from that tile is (Down+Right)
	    }
	    
	    X=disk.getX();							//Return at the current disk's position to check the next possible direction
	    Y=disk.getY();
	    insideLoop=false;
	    
	    while(X-1>-1 && board[--X][Y] == opp)				//If you find an opponent disk (Up) from the current disk, the first free tile in that direction will give you a valid move 
		insideLoop=true;
	    if(insideLoop==true && board[X][Y]==' '){
		c = new freeMoveTile(X,Y);
		if(iOfTile(markedTiles,c)==-1)
		    markedTiles.add(c);
		i = iOfTile(markedTiles,c);
		markedTiles.get(i).getDirections().set(6,true);			//If we follow the (Up) path to the free tile that represents a valid move, then the valid path that starts from that tile is (Down)
	    }
	    
	    X=disk.getX();
	    Y=disk.getY();
	    insideLoop=false;
	    
	    while(X-1>-1 && Y+1<8 && board[--X][++Y] == opp)			//If you find an opponent disk (Up+Right) from the current disk, the first free tile in that direction will give you a valid move 
		insideLoop=true;
	    if(insideLoop==true && board[X][Y]==' '){
		c = new freeMoveTile(X,Y);
		if((iOfTile(markedTiles,c))==-1)
		    markedTiles.add(c);
		i = iOfTile(markedTiles,c);
		markedTiles.get(i).getDirections().set(5,true);			//If we follow the (Up+Right) path to the free tile that represents a valid move, then the valid path that starts from that tile is (Down+Left)
	    }
	    
	    X=disk.getX();
	    Y=disk.getY();
	    insideLoop=false;
	    
	    while(Y-1>-1 && board[X][--Y] == opp)				////If you find an opponent disk (Left) from the current disk, the first free tile in that direction will give you a valid move 
		insideLoop=true;
	    if(insideLoop==true && board[X][Y]==' '){
		c = new freeMoveTile(X,Y);
		if((iOfTile(markedTiles,c))==-1)
		    markedTiles.add(c);
		i = iOfTile(markedTiles,c);
		markedTiles.get(i).getDirections().set(4,true);			////If we follow the (Left) path to the free tile that represents a valid move, then the valid path that starts from that tile is (Right)
	    }									//The rest while checks are the inverse of the above
	    
	    X=disk.getX();
	    Y=disk.getY();
	    insideLoop=false;
	    
	    while(Y+1<8 && board[X][++Y] == opp)
		insideLoop=true;
	    if(insideLoop==true && board[X][Y]==' '){
		c = new freeMoveTile(X,Y);
		if((iOfTile(markedTiles,c))==-1)
		    markedTiles.add(c);
		i = iOfTile(markedTiles,c);
		markedTiles.get(i).getDirections().set(3,true);
	    }
	    
	    X=disk.getX();
	    Y=disk.getY();
	    insideLoop=false;
	    
	    while(X+1<8 && Y-1>-1 && board[++X][--Y] == opp)
		insideLoop=true;
	    if(insideLoop==true && board[X][Y]==' '){
		c = new freeMoveTile(X,Y);
		if((iOfTile(markedTiles,c))==-1)
		    markedTiles.add(c);
		i = iOfTile(markedTiles,c);
		markedTiles.get(i).getDirections().set(2,true);
	    }
	    
	    X=disk.getX();
	    Y=disk.getY();
	    insideLoop=false;
	    
	    while(X+1<8 && board[++X][Y] == opp)
		insideLoop=true;
	    if(insideLoop==true && board[X][Y]==' '){
		c = new freeMoveTile(X,Y);
		if((iOfTile(markedTiles,c))==-1)
		    markedTiles.add(c);
		i = iOfTile(markedTiles,c);
		markedTiles.get(i).getDirections().set(1,true);
	    }
	    
	    X=disk.getX();
	    Y=disk.getY();
	    insideLoop=false;
	    
	    
	    while(X+1<8 && Y+1<8 && board[++X][++Y] == opp)
		insideLoop=true;
	    if(insideLoop==true && board[X][Y]==' '){
		c = new freeMoveTile(X,Y);
		if((iOfTile(markedTiles,c))==-1)
		    markedTiles.add(c);
		i = iOfTile(markedTiles,c);
		markedTiles.get(i).getDirections().set(0,true);
	    }
	}
	
	
	return markedTiles;							//Return the list with all the valid moves
    }
    
    
    //B is Max, W is Min
    public int evaluate(){
	
	//# of Available moves
	int bMoves;
	int wMoves;
	
	//# of disks that have at least one free tile around them
	int bVulDisks = 0;
	int wVulDisks = 0;
	
	//c1-f1, a3-a6, c8-f8, h3-h6
	int bEdgeDisks = 0;
	int wEdgeDisks = 0;
	
	//b1,b8,a2,a7,h2,h7,g1,g8
	int bCDisks = 0;
	int wCDisks = 0;
	
	//b2,b7,g2,g7
	int bXDisks = 0;
	int wXDisks = 0;
	
	//a1,a8,h1,h8
	int bCornerDisks = 0;
	int wCornerDisks = 0;
	
	int result;
	
	bMoves = validMoves('X').size();
	wMoves = validMoves('O').size();
	
	diskTile tmp;
	
	for(int i=0 ; i<bDisks.size();i++){
	    tmp = bDisks.get(i);
	    
	    if(freeTilesAround(tmp)) bVulDisks++;
	    
	    switch(tmp.getX()){
		case 0:
		case 7:
		    if(tmp.getY()==0 || tmp.getY()==7)
			bCornerDisks++;
		    else if(tmp.getY()==1 || tmp.getY()==6)
			bCDisks++;
		    else
			bEdgeDisks++;
		    break;
		    
		case 1:
		case 6:
		    if(tmp.getY()==0 || tmp.getY()==7)
			bCDisks++;
		    else if(tmp.getY()==1 || tmp.getY()==6)
			bXDisks++;
		    break;
	    }
	}
	
	for(int i=0 ; i<wDisks.size();i++){
	    tmp = wDisks.get(i);
	    
	    if(freeTilesAround(tmp)) wVulDisks++;
	    
	    switch(tmp.getX()){
		case 0:
		case 7:
		    if(tmp.getY()==0 || tmp.getY()==7)					//a1,h1,a8,h8
			wCornerDisks++;
		    else if(tmp.getY()==1 || tmp.getY()==6)				//b1,g1,b8,g8
			wCDisks++;
		    else
			wEdgeDisks++;
		    break;
		    
		case 1:
		case 6:
		    if(tmp.getY()==0 || tmp.getY()==7)					//a2,a7,h2,h7
			wCDisks++;
		    else if(tmp.getY()==1 || tmp.getY()==6)				//b2,b7,g2,g7
			wXDisks++;
		    break;
	    }
	}
	
	if(bDisks.size()+wDisks.size()<30)
	    result = ((bMoves - wMoves) + (wVulDisks - bVulDisks) +2*(bEdgeDisks-wEdgeDisks) - (10*(bCDisks - wCDisks)) - (25*(bXDisks - wXDisks)) + (100*(bCornerDisks - wCornerDisks)));
	else if(bDisks.size()+wDisks.size()<54)
	    result = ((bMoves - wMoves) + 20*(wVulDisks - bVulDisks) + 10*(bEdgeDisks-wEdgeDisks) - (10*(bCDisks - wCDisks)) - (25*(bXDisks - wXDisks)) + (100*(bCornerDisks - wCornerDisks)));
	else
	    result = (5*(bDisks.size()-wDisks.size())) + 2*(wVulDisks - bVulDisks) + (bMoves-wMoves) + 2*(bCornerDisks-wCornerDisks);
	
	return result;
    }
    
    
    public boolean isTerminal(){
	return (validMoves('X').isEmpty() && validMoves('O').isEmpty());
    }
    
    //indexOf for a specific ArrayList
    private int iOfTile(ArrayList<freeMoveTile> a, diskTile b){
	int i = 0;
	
	while(i<a.size()){
	    if(b.compareTo(a.get(i))==0)
		return i;
	    
	    i++;
	}
	return -1;
    }
    
    //indexOf for a specific ArrayList
    private int iOfTile2(ArrayList<diskTile> a, diskTile b){
	int i = 0;
	
	while(i<a.size()){
	    if(b.compareTo(a.get(i))==0)
		return i;
	    
	    i++;
	}
	return -1;
    }
    
    //Calculates the amount of free tiles around the given disk (used as an evaluation function)
    private boolean freeTilesAround(diskTile d){
	boolean flag = false;
	
	switch(d.getX()){
	    case 0:								//Upper edge
		switch(d.getY()){
		    case 0:							//Left upper corner
			if(board[d.getX()][d.getY()+1]==' ' || board[d.getX()+1][d.getY()]==' ' || board[d.getX()+1][d.getY()+1]==' ')
			    flag = true;
			break;
			
		    case 7:							//Right upper corner
			if(board[d.getX()][d.getY()-1]==' ' || board[d.getX()+1][d.getY()]==' ' || board[d.getX()+1][d.getY()-1]==' ')
			    flag = true;
			break;
			
		    default:							//Non-corner
			if(board[d.getX()][d.getY()+1]==' ' || board[d.getX()+1][d.getY()]==' ' || board[d.getX()+1][d.getY()+1]==' ' || board[d.getX()][d.getY()-1]==' ' || board[d.getX()+1][d.getY()-1]==' ')
			    flag = true;
			break;
		}
		break;
		
	    case 7:								//Lower edge
		switch(d.getY()){
		    case 0:							//Left lower corner
			if(board[d.getX()][d.getY()+1]==' ' || board[d.getX()-1][d.getY()]==' ' || board[d.getX()-1][d.getY()+1]==' ')
			    flag = true;
			break;
			
		    case 7:							//Right lower corner
			if(board[d.getX()][d.getY()-1]==' ' || board[d.getX()-1][d.getY()]==' ' || board[d.getX()-1][d.getY()-1]==' ')
			    flag = true;
			break;
			
		    default:							//Non-corner
			if(board[d.getX()][d.getY()+1]==' ' || board[d.getX()-1][d.getY()]==' ' || board[d.getX()-1][d.getY()+1]==' ' || board[d.getX()][d.getY()-1]==' ' || board[d.getX()-1][d.getY()-1]==' ')
			    flag = true;
			break;
		}
		break;
		
	    default:								//No horizontal edge
		switch(d.getY()){
		    case 0:							//Left edge
			if(board[d.getX()-1][d.getY()]==' ' || board[d.getX()-1][d.getY()+1]==' ' || board[d.getX()][d.getY()+1]==' ' || board[d.getX()+1][d.getY()+1]==' ' || board[d.getX()+1][d.getY()]==' ')
			    flag = true;
			break;
		    
		    case 7:							//Right edge
			if(board[d.getX()-1][d.getY()]==' ' || board[d.getX()-1][d.getY()-1]==' ' || board[d.getX()][d.getY()-1]==' ' || board[d.getX()+1][d.getY()-1]==' ' || board[d.getX()+1][d.getY()]==' ')
			    flag = true;
			break;
			
		    default:							//Non-edge
			if(board[d.getX()-1][d.getY()-1]==' ' || board[d.getX()-1][d.getY()]==' ' || board[d.getX()-1][d.getY()+1]==' ' || board[d.getX()][d.getY()+1]==' ' || board[d.getX()+1][d.getY()+1]==' ' || board[d.getX()+1][d.getY()]==' ' || board[d.getX()+1][d.getY()-1]==' ' || board[d.getX()][d.getY()-1]==' ')
			    flag = true;
			break;
		}
		break;
	}
	
	return flag;
    }
      
    public void printScore(){
	System.out.println();
	System.out.println("              SCORE");
	System.out.println("-----------------------------------");
	System.out.println("Black: " + bDisks.size());
	System.out.println("White: " + wDisks.size());
	System.out.println("-----------------------------------");
    }
    
    public char[][] getBoard(){
	return board;
    }
    
    public ArrayList<diskTile> getBDisks(){
	return bDisks;
    }
    
    public ArrayList<diskTile> getWDisks(){
	return wDisks;
    }
    
    public freeMoveTile getLastMove(){
	return lastMove;
    }
    
    /* Used for closed set implementation
    public boolean compareTo(BoardState b){
	boolean flag = true;
	int i=0;
	int j;
	while(flag && i<8){
	    j=0;
	    while(flag && j<8){
		if(board[i][j]!=b.getBoard()[i][j])
		    flag = false;
		j++;
	    }
	    i++;
	}
	return flag;
    }*/

}
