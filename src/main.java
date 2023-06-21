import java.util.Scanner;
import java.util.ArrayList;
public class main {

    
    public static void main(String[] args) {
	
	Scanner s = new Scanner(System.in);
	BoardState c = new BoardState();

	ArrayList<freeMoveTile> nextMoves;
	
	int k;
	int maxDepth;
	int counter;
	int endGame = 0;
	char player,opp;
	CPU bot;
	freeMoveTile botMove;
	
	System.out.println("Choose 1 for X(Black) or 2 for O(White). Black always plays first.");
	k=s.nextInt();
	while(k!=1 && k!=2){
	    System.out.println("Please insert either 1 or 2");
	    k=s.nextInt();
	}
	
	System.out.println("Insert the maximum depth of the CPU's MiniMax tree (Minimum: 1, Maximum: 60)");
	maxDepth=s.nextInt();
	while(maxDepth<1 || maxDepth>60){
	    System.out.println("Please insert a valid maximum depth");
	    maxDepth=s.nextInt();
	}
	
	if(k==1){
	    player='X';
	    opp='O';
	    bot = new CPU(opp,maxDepth);
	    counter=1;
	}else{
	    player='O';
	    opp='X';
	    bot = new CPU(opp,maxDepth);
	    counter=2;
	}
	
	System.out.println();
	System.out.println("GAME START");
	System.out.println();
	c.print();
	System.out.println();
	
	while(endGame!=2){							//endGame becomes 2 when 
	    if(counter%2==1){							//A way of implementing turns: Odd number = Player's turn | Even number = CPU's turn
		System.out.println("Your turn");
		System.out.println("**********************************");
		
		nextMoves=c.validMoves(player);					//1. Take the valid move list
	    
		if(nextMoves.isEmpty()){					//2. If it's empty, the opponent plays again
		    System.out.println("You have no valid moves.");
		    endGame++;
		    System.out.println();
		}
		else{
		    endGame=0;
		    System.out.println("Choose one from the current valid moves:");
		    for(int i =0; i<nextMoves.size();i++){			//3. Print the available moves
			char p = (char)(nextMoves.get(i).getY() + 97);
			System.out.print((i+1) + ")" + p + "" + (nextMoves.get(i).getX() + 1) + " ");
		    }
		    System.out.println();
		    k = s.nextInt();

		    while(k<1 || k>nextMoves.size()){
			System.out.println("Please enter one of the valid moves' numbers.");
			k = s.nextInt();
		    }
		    

		    c.move(nextMoves.get(k-1),player);				//4. Make the selected move
		    c.print();							//5. Print the board that results from the move		    
		    c.printScore();						//6. Print the current score
		    System.out.println();
		}
	    }
	    else{
		System.out.println("CPU's turn");
		System.out.println("**********************************");
		
		botMove = bot.MiniMax(c,opp);					//1. Call MiniMax with the current board and the CPU's color
	
		if(botMove.compareTo(new freeMoveTile())==0){			//2. Default freeMoveTile = no moves available
		    System.out.println("CPU has no valid moves.");
		    endGame++;
		}
		else{
		    endGame=0;
		    c.move(botMove, opp);					//3. Make the move that MiniMax returned
		    
		    c.print();							//4. Print the board that results from the move
		    c.printScore();						//5. Print the current score
		    System.out.println();
		}
		
	    }
	    
	    counter++;								//Even becomes odd / odd becomes even
	    System.out.println();
	}
	
	System.out.println("**********************************");
	if(c.getBDisks().size()>c.getWDisks().size())
	    System.out.println("BLACK WINS");
	else if(c.getBDisks().size()<c.getWDisks().size())
	    System.out.println("WHITE WINS");
	else
	    System.out.println("TIE");
	System.out.println("**********************************");

    }
}

/* 2 PLAYER MODE
	while(endGame!=2){							//If both players are out of moves, the game is over
	    if (counter%2==1)
		player = 'X';
	    else player = 'O';
	    
	    
	    
	    nextMoves=c.validMoves(player);					//1. Take the valid move list
	    
	    if(nextMoves.isEmpty()){						//2. If it's empty, the opponent plays again
		System.out.println("No valid moves for " + player + ".");
		endGame++;
	    }
	    else{
		endGame=0;
		System.out.println("Choose one from the current valid moves:");
		for(int i =0; i<nextMoves.size();i++){				//3. Print the available moves
		    char p = (char)(nextMoves.get(i).getY() + 97);
		    System.out.print((i+1) + ")" + p + "" + (nextMoves.get(i).getX() + 1) + " ");
		}
		System.out.println();
		k = s.nextInt();

		while(k<1 || k>nextMoves.size()){
		    System.out.println("Please enter one of the valid moves' numbers.");
		    k = s.nextInt();
		}
		
		
		c.move(nextMoves.get(k-1),player);				//4. Make the selected move
		c.print();							//5. Print the board that results from the move
		c.printScore();							//6. Print the new score
		
		
	    }
	    
	    
	    counter++;
	    
	    
	}
	}
	}
	*/
    
