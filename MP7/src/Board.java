
public class Board {

	/** number of rows
	 * 
	 */
	int row;
	/** number of columns
	 * 
	 */
	int column;
	/** a board contains the information
	 * 
	 */
	int[][] board;
	
	/** create a board
	 * 
	 * @param r	- number of rows
	 * @param c	- number of columns
	 */
	public Board(int r, int c) {
		this.row = r;
		this.column = c;
		this.board = new int[r][c];
	}

	/** filled the board with random generated alive and dead cells
	 * 
	 */
	public void setBoard() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				/* approximately 2/3 cells are dead, 1/3 cells are alive
				 * 
				 */
				if (Math.random() * 3 < 2) {
					board[i][j] = 0;
				} else {
					board[i][j] = 1;
				}
			}
		}
	}

}
