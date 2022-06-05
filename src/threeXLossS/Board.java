package threeXLossS;

public class Board {

	char[][] board = {{'_', '_', '_'}, {'_', '_', '_'}, {'_', '_', '_'}};
	char play_value = 'x';
	int level = 0;

	/**
	 * Constructors
	 */
	public Board() {
		// TODO Auto-generated constructor stub
	}

	public Board(int level) {
		this.level = level;
	}

	public Board(int row, int col) {
		// TODO Auto-generated constructor stub
		this.board = new char[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				this.board[i][j] = '_';
			}
		}
	}

	public Board(int row, int col, int level) {
		// TODO Auto-generated constructor stub
		this.board = new char[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				this.board[i][j] = '_';
			}
		}
		this.level = level;
	}

	// Insert Value In Empty Place (Needed For Player)
	public void insert(char value, Move pos) {
		if (this.board[pos.row][pos.col] == '_') {
			this.board[pos.row][pos.col] = value;
		}
	}

	// Check If The Game Is Finished
	boolean finish() {
		// Checking Rows
		for (int row = 0; row < this.board.length; row++) {
			for (int j = 0; j < this.board[0].length - 2; j++) {
				if (this.board[row][j] == this.board[row][j + 1] && this.board[row][j + 1] == this.board[row][j + 2]) {
					if (this.board[row][j] == this.play_value)
						return true;
				}
			}

		}

		// Checking Columns
		for (int col = 0; col < this.board[0].length; col++) {
			for (int i = 0; i < this.board.length - 2; i++) {
				if (this.board[i][col] == this.board[i + 1][col] && this.board[i + 1][col] == this.board[i + 2][col]) {
					if (this.board[i][col] == this.play_value)
						return true;
				}
			}

		}

		// Checking Diagonals
		///////////// Your Code ///////////
		boolean Diagonals=true;

		for (int i = 0; i < this.board.length; i++)
		{

			for (int j = 0; j < board.length; j++) {
				if(i==j){
					if(board[i][j]!=this.play_value)
					{
						Diagonals=false;
					}
				}

			}
		}



		// Checking Reversed Diagonals
		///////////// Your Code ///////////
		boolean Reversed_Diagonals=true;

		for (int i = 0; i < this.board.length; i++)
		{

			for (int j = 0; j < board.length; j++) {
				if(i+j==board.length-1){
					if(board[i][j]!=this.play_value)
					{
						Reversed_Diagonals=false;
					}
				}

			}
		}
		if(Reversed_Diagonals||Diagonals)
			return true;

		return false;
	}

	//	Evaluate the Current Board
	int evaluate(boolean isMax) {
		int utility = 0;

		///////////// Your Code ///////////

		// Checking Rows
		for (int row = 0; row < this.board.length; row++) {

			for (int j = 0; j < this.board[0].length - 2; j++) {
				if (this.board[row][j] == this.board[row][j + 1] && this.board[row][j + 1] == this.board[row][j + 2]) {
					if (this.board[row][j] == this.play_value) {
						if (isMax)
							return 10;
						return -10;
					}
				} else if ((this.board[row][j] == this.board[row][j + 1] || this.board[row][j + 1] == this.board[row][j + 2]) ) {
					if (this.board[row][j] == this.play_value)
					{
						if(isMax)
							utility++;
						else
							utility--;
					}

				}
			}

		}

		// Checking Columns
		for (int col = 0; col < this.board[0].length; col++) {
			for (int i = 0; i < this.board.length - 2; i++) {
				if (this.board[i][col] == this.board[i + 1][col] && this.board[i + 1][col] == this.board[i + 2][col]) {
					if (this.board[i][col] == this.play_value) {
						if (isMax)
							return 10;
						return -10;
					}
				} else if (this.board[i][col] == this.board[i + 1][col] || this.board[i + 1][col] == this.board[i + 2][col]) {
					if (isMax)
						utility++;
					utility--;
				}
			}
		}
		// Checking Diagonals


		for (int i = 0; i < this.board.length; i++)
		{

			for (int j = 0; j < board[0].length-2; j++) {
				if(i==j){
					if(board[i][j]==board[i+1][i+1]&&board[i+1][i+1]==board[i+2][i+2])

					{
						if(board[i][j]==this.play_value)
						{
							if (isMax)
								return 10;
							return -10;
						}
					}
					if(board[i][j]==board[i+1][i+1]||board[i+1][i+1]==board[i+2][i+2])
					{
						if (this.board[i][j] == this.play_value)
						{
							if(isMax)
								utility++;
							else
								utility--;
						}
					}
				}

			}
		}


		// Checking Reversed Diagonals
		for (int i = 0; i < this.board.length; i++)
		{

			for (int j = 0; j < board[0].length-2; j++) {
				if(i+j==board.length-1){
					if(board[i][j]==board[i-1][i-1]&&board[i-1][i-1]==board[i-2][i-2])

					{
						if(board[i][j]==this.play_value)
						{
							if (isMax)
								return 10;
							return -10;
						}
					}
					if(board[i][j]==board[i-1][i-1]||board[i-1][i-1]==board[i-2][i-2])
					{
						if (this.board[i][j] == this.play_value)
						{
							if(isMax)
								utility++;
							else
								utility--;
						}
					}
				}

			}
		}
		return utility;
	}

	// Minimax Algorithm
    int minimax(int depth, boolean isMax) {
        ///////////// Your Code ///////////


        int score=evaluate(isMax);
        if(finish() ||(depth==level))
        	return score;
        // If Max Player
       else if (isMax) {
            int best = -1000;
			for (int i = 0; i < this.board.length; i++) {
				for (int j = 0; j < this.board.length; j++) {
					if(board[i][j]=='_')
					{
						board[i][j]=this.play_value;
						int b=minimax(depth+1,false);
						board[i][j]='_';
					    if(b>best) {
							best = b;
						}
					}
				}

			}
            return best;
        }

        // If Min Player
        else {
            int best = 1000;
			for (int i = 0; i < this.board.length; i++) {
				for (int j = 0; j < this.board.length; j++) {
					if(board[i][j]=='_')
					{
						board[i][j]=this.play_value;
						int b=minimax(depth+1,true);
						board[i][j]='_';
						if(b<best){
							best=b;}

					}
				}

			}
			return best;
		}

    }


	//Alpha && Beta
	// Alpha <<<<<<< 0
	// Beta >>>>>>>> 0
	int minimax(int depth, boolean isMax,int Alpha,int Beta ) {
		///////////// Your Code ///////////


		int score=evaluate(isMax);
		if(finish() ||(depth==level))
			return score;
			// If Max Player
		else if (isMax) {
			int best = -1000;
			for (int i = 0; i < this.board.length; i++) {
				for (int j = 0; j < this.board.length; j++) {
					if(board[i][j]=='_')
					{
						board[i][j]=this.play_value;
						int b=minimax(depth+1,false,Alpha,Beta);
						if(b>best) {
							best = b;
						}
						if(Beta<= b){
							return best;
						}
						if(Alpha>= b){
							Alpha = b;
						}

						board[i][j]='_';

					}
				}

			}
			return best;
		}

		// If Min Player
		else {
			int best = 1000;
			for (int i = 0; i < this.board.length; i++) {
				for (int j = 0; j < this.board.length; j++) {
					if(board[i][j]=='_')
					{
						board[i][j]=this.play_value;
						int b=minimax(depth+1,true,Alpha,Beta);
						if(b<best){
							best=b;
						}
						if(Alpha>= b){

							return best;
						}
						if(Beta<= b){
							Beta = b;
						}
						board[i][j]='_';


					}
				}

			}
			return best;
		}

	}


	// Start From Here To Find The Best Move By Calling The Minimax Algorithm For Each Of The Computer Possible Moves
	Move bestMove() {
		///////////// Your Code ///////////
		int bestVal = -1000;
		Move bestMove = new Move();
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board.length; j++) {
				if (board[i][j] == '_') {
					board[i][j] = this.play_value;
					int b = minimax(0, false,-1000,1000);
					board[i][j] = '_';
					if (b > bestVal) {
						bestVal = b;
						bestMove.row = i;
						bestMove.col = j;
					}


				}
			}}
		return bestMove;
	}

	void takeMove() {
		Move MoveTo = this.bestMove();
		System.out.println("best row " + (MoveTo.row + 1));
		System.out.println("best col " + (MoveTo.col + 1));
		this.insert(play_value, MoveTo);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				sb.append(this.board[i][j]);
				sb.append(" | ");
			}
			sb.delete(sb.length() - 2, sb.length() - 1);
			sb.append('\n');
		}
		return sb.toString();
	}
}
