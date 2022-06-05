package threeXLossS;


public class Move {
	int row, col; 
	
	public Move() {
		// TODO Auto-generated constructor stub
		this.row = 0;
		this.col = 0;
	}
	
	public Move(int x, int y) {
		this.row = x;
		this.col = y;
	}
	
	public boolean is_equal(Move pos) {
		if((this.row == pos.row) && (this.col == pos.col))
			return true;
		return false;
	}

}
