package threeXLossS;

import java.util.Scanner;

public class Game {
	
	Board game;
	Scanner s = new Scanner(System.in);
	
	public Game() {
		// TODO Auto-generated constructor stub
	}
	
	// The Player Function
	private void playerPlay() {
        
        Move move = new Move();
        // Check The Player Row Location
        while (true) {
            System.out.print("Enter row: ");
            move.row = s.nextInt();
            System.out.println();
            if ((move.row > 0) && (move.row <= this.game.board.length)) {
                break;
            }
        }
        // Check The Player Column Location
        while (true) {
            System.out.print("Enter column: ");
            move.col = s.nextInt();
            System.out.println();
            if ((move.col > 0) && (move.col <=  this.game.board[0].length)) {
                break;
            }
        }
    	// Check If The Player Location Is Empty
        if (this.game.board[move.row - 1][move.col - 1] != '_') {
        	System.out.println("Cannot move .. Please try a gain");
        	this.playerPlay();	
        }
        else {
        	// Insert The Player Chosen Move
        	move.row -= 1;
        	move.col -= 1;
        	this.game.insert('x', move);
        }
        		
    }
	
	// The Computer Function
	private void computerPlay() {
        this.game.takeMove();
    }
	
	public void start() {
		
		// Insert The Game Board Dimension and The Game Level
		System.out.println("Enter the Board width and high");
		System.out.print("Width : ");
		int row = s.nextInt();
		System.out.print("high : ");
		int col = s.nextInt();
		System.out.print("Enter the Game Level: ");
		int level = s.nextInt();
		
		if(row < 3 || col < 3)
			this.game = new Board(level);
		else
			this.game = new Board(row, col, level);
			
        System.out.println(this.game);
        
        while (true) {
        	// Player Turn
        	this.playerPlay();
        	System.out.println("_____Player Turn______");
            System.out.println(this.game);
            if(this.game.finish()) {
            	System.out.println("And the winner is... ME! -O-");
            	break;
            }
            
            // Computer Turn
            this.computerPlay();
            System.out.println("_____Computer Turn______");
            System.out.println(this.game);
            if (this.game.finish()) {
                System.out.println("I had just so bad luck, let's try again >_<!");
                break;
            }
        }
        
    }

	 
	// Driver code 
	 public static void main(String[] args) 
	{ 
		 Game lets_play = new Game();
		 lets_play.start();
	} 

}


