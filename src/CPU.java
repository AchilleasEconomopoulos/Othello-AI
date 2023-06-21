import java.util.ArrayList;
import java.util.Random;


public class CPU {
    private char player;
    private int maxDepth;
    int counter=0;

    
    public CPU(char player, int maxDepth){
	this.player=player;
	this.maxDepth=maxDepth;
    }
    
    public freeMoveTile MiniMax(BoardState start, char player){
	freeMoveTile c = new freeMoveTile();
	counter=0;
	
	if(start.validMoves(player).isEmpty()){
	    return (c);
	}
	else if (player=='X'){
	    c=max(new BoardState(start),0,Integer.MIN_VALUE,Integer.MAX_VALUE);
	    System.out.println("States checked: " + counter);
	    return c;
	}
	else{
	    c=min(new BoardState(start),0,Integer.MIN_VALUE,Integer.MAX_VALUE);
	    System.out.println("States checked: " + counter);
	    return c;
	}
    }
    
    
    public freeMoveTile max(BoardState board,int depth,int a, int b){
	counter++;
	Random r = new Random();
	
	//Terminal state or maxDepth reached
	if(board.isTerminal() || depth == maxDepth){
	    return (new freeMoveTile(board.getLastMove().getX(),board.getLastMove().getY(),board.evaluate()));
	}
	
	//No available moves for Black
	ArrayList<freeMoveTile> moves = board.validMoves('X');
	if(moves.isEmpty()){
	    return min(board,depth+1,a,b);
	}
	

	freeMoveTile maxMove = new freeMoveTile(-1,-1,Integer.MIN_VALUE);

	
	for(freeMoveTile option : moves){
	    BoardState copy = new BoardState(board);
	    copy.move(option,'X');
	    
	    
	    freeMoveTile tmp = min(copy,depth+1,a,b);
	    
	    
	    if(tmp.getValue()>=b){
		return (new freeMoveTile(-1,-1,tmp.getValue()));
	    }
	    
	    if(tmp.getValue() >= maxMove.getValue()){
		
		if(tmp.getValue() == maxMove.getValue()){			//Relatively rare occurence, especially with a-b pruning
		    if(r.nextInt(2)==0){
			maxMove.setX(copy.getLastMove().getX());
			maxMove.setY(copy.getLastMove().getY());
			maxMove.setDirections(copy.getLastMove().getDirections());
			maxMove.setValue(tmp.getValue());
		    }
		}
		else{
		    maxMove.setX(copy.getLastMove().getX());
		    maxMove.setY(copy.getLastMove().getY());
		    maxMove.setDirections(copy.getLastMove().getDirections());
		    maxMove.setValue(tmp.getValue());
		}
	    }
	    
	    if(tmp.getValue() > a){
		a=tmp.getValue();
	    }
	}

	
	return maxMove;
    }
    
    public freeMoveTile min(BoardState board,int depth,int a, int b){
	counter++;
	Random r = new Random();
	
	//Terminal state or maxDepth reached
	if(board.isTerminal() || depth == maxDepth){   
	    return (new freeMoveTile(board.getLastMove().getX(),board.getLastMove().getY(),board.evaluate()));
	}
	
	//No available moves for White
	ArrayList<freeMoveTile> moves = board.validMoves('O');
	if(moves.isEmpty()){
	    return max(board,depth+1,a,b);
	}
	
	freeMoveTile minMove = new freeMoveTile(-1,-1,Integer.MAX_VALUE);
	
	
	
	for(freeMoveTile option : moves){
	    BoardState copy = new BoardState(board);
	    copy.move(option,'O');
	    
	    
	    freeMoveTile tmp = max(copy,depth+1,a,b);
	    
	    if(tmp.getValue() <= a){
		return (new freeMoveTile(-1,-1,tmp.getValue()));
	    }
	    
	    if(tmp.getValue() <= minMove.getValue()){
		
		if(tmp.getValue() == minMove.getValue()){			//Relatively rare occurence, especially with a-b pruning
		    if(r.nextInt(2)==0){
			minMove.setX(copy.getLastMove().getX());
			minMove.setY(copy.getLastMove().getY());
			minMove.setDirections(copy.getLastMove().getDirections());
			minMove.setValue(tmp.getValue());
		    }
		}
		else{
		    minMove.setX(copy.getLastMove().getX());
		    minMove.setY(copy.getLastMove().getY());
		    minMove.setDirections(copy.getLastMove().getDirections());
		    minMove.setValue(tmp.getValue());
		}
	    }
	    
	    if(tmp.getValue() < b)
		b=tmp.getValue();
	}
	
	
	return minMove;
	
    }
    
    public char getPlayer(){
	return player;
    }
    
    public int getMaxDepth(){
	return maxDepth;
    }
}

//****************************************************************************//
 /*MINIMAX WITH CLOSED SET

    public freeMoveTile MiniMax(BoardState start, char player){
	
	counter=0;
	freeMoveTile c = new freeMoveTile();
	
	if(start.validMoves(player).isEmpty()){
	    return (c);
	}
	else if (player=='X'){
	    c=max(new BoardState(start),0,Integer.MIN_VALUE,Integer.MAX_VALUE,new ArrayList<BoardState>());
	    System.out.println("States checked: " + counter);
	    return c;
	}
	else{
	    c=min(new BoardState(start),0,Integer.MIN_VALUE,Integer.MAX_VALUE,new ArrayList<BoardState>());
	    System.out.println("States checked: " + counter);
	    return c;
	}
    }
    
    
    public freeMoveTile max(BoardState board,int depth,int a, int b, ArrayList<BoardState> checked){
	counter++;
	
	//Terminal state or max depth reached
	if(board.isTerminal() || depth == maxDepth)
	    return board.getLastMove();
	
	//No available moves for Black
	ArrayList<freeMoveTile> moves = board.validMoves('X');
	if(moves.isEmpty()){
	    return min(board,depth+1,a,b,checked);
	}
	
	int maxValue = Integer.MIN_VALUE;
	freeMoveTile maxMove = new freeMoveTile();
	
	
	for(freeMoveTile option : moves){
	    BoardState copy = new BoardState(board);
	    copy.move(option,'X');
	    
	    if(containsBoard(checked,copy))
		return copy.getLastMove();
	    else
		checked.add(copy);
	    
	    freeMoveTile tmp = min(copy,depth+1,a,b,checked);
	    
	    if(tmp.getValue()>=b){
		return (new freeMoveTile(-1,-1,tmp.getValue()));
	    }
	    
	    if(tmp.getValue() > maxValue){
		maxValue = tmp.getValue();
		maxMove.setX(copy.getLastMove().x);
		maxMove.setY(copy.getLastMove().y);
		maxMove.setDirections(copy.getLastMove().getDirections());
		maxMove.setValue(tmp.getValue());
	    }
	    
	    if(tmp.getValue() > a){
		a=tmp.getValue();
	    }
	}
	
	return maxMove;
    }
    
    public freeMoveTile min(BoardState board,int depth,int a, int b,ArrayList<BoardState> checked){
	counter++;
	
	//Terminal state or max depth reached
	if(board.isTerminal() || depth == maxDepth){
	    return board.getLastMove();
	}
	
	//No available moves for White
	ArrayList<freeMoveTile> moves = board.validMoves('O');
	if(moves.isEmpty()){
	    return max(board,depth+1,a,b,checked);
	}
	
	int minValue = Integer.MAX_VALUE;
	freeMoveTile minMove = new freeMoveTile();
	
	
	for(freeMoveTile option : moves){
	    BoardState copy = new BoardState(board);
	    copy.move(option,'O');
	    
	    if(containsBoard(checked,copy))
		return copy.getLastMove();
	    else
		checked.add(copy);
	    
	    freeMoveTile tmp = max(copy,depth+1,a,b,checked);
	   
	    if(tmp.getValue() <= a){
		return (new freeMoveTile(-1,-1,tmp.getValue()));
	    }
	    
	    if(tmp.getValue() < minValue){
		minValue = tmp.getValue();
		minMove.setX(copy.getLastMove().x);
		minMove.setY(copy.getLastMove().y);
		minMove.setDirections(copy.getLastMove().getDirections());
		minMove.setValue(tmp.getValue());
	    }
	    
	    if(tmp.getValue() < b)
		b=tmp.getValue();
	}
	
	return minMove;
	
    }
    
    
    private boolean containsBoard(ArrayList<BoardState> list, BoardState board){
	boolean flag=false;
	int i=0;
	while(!flag && i<list.size()){
	    if(board.compareTo(list.get(i)))
		flag = true;
	    i++;
	}
	
	return flag;
    }
}*/


//****************************************************************************//
/* MINIMAX WITH DEPTH EXPANSION ON CRITICAL STATES
 * THIS PART OF THE CODE REPLACES THE
 * if(board.isTerminal() || depth == maxDepth)
 * CHECK IN BOTH min AND max METHODS


	//Terminal state or (maxDepth+2) reached
	if(board.isTerminal() || depth > maxDepth +2){				//The MiniMax tree can be expanded by up to 2 levels
	    return (new freeMoveTile(board.getLastMove().getX(),board.getLastMove().getY(),board.evaluate()));
	}
	
	
	//This expands the MiniMax tree in case a critical board state is detected
	//The only critical state considered here is when the score is +120 to +130 in favour of the CPU
	//Board states in this range probably reflect the scenario where the CPU is either close to going up +1 corner
	//  or still hasn't taken advantage of being up +1 corner

	if(depth == maxDepth){
	    int score = board.evaluate();
	    if(player=='X'){
		if(score<120 || score>130)
		    return (new freeMoveTile(board.getLastMove().getX(),board.getLastMove().getY(),score));
	    }else{
		if(score>(-120)||score<(-130))
		    return (new freeMoveTile(board.getLastMove().getX(),board.getLastMove().getY(),score));
	    } 
	}
*/
