

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QueensBoard {

	public static int numberOfQueens = 8;
	private int[][] chessBoard;
	private int[] queenPositions;

	public static void main(String[] args) {

		
			QueensBoard chessBoard = new QueensBoard(new int[8][8], new int[8]);

			chessBoard.placeRandomQueens(); //random queens
			System.out.println("Initial State");
			chessBoard.printBoard();
			
			int initialAttackingPairs = chessBoard.calculateCost();
//			System.out.println(initialAttackingPairs);
			
			int[] iteratedQueenPositions = new int[8];
			boolean best = false;
			
			for (int j = 0; j < 7; j++) {
				
				best = false;
				for (int i = 0; i < 7; i++) {
			
					chessBoard.moveQueen(i, j);
					chessBoard.printBoard();
						System.out.println();
						
						if (chessBoard.calculateCost() < initialAttackingPairs) {
							best = true;
							int localMin = chessBoard.calculateCost();
							iteratedQueenPositions[j] = i;
						}
						// reset to original queen position
						//chessBoard.resetQueen(i, j);

				}
			}
		} 

	public void moveQueen(int row, int col) {

		// original queen will become a 2 and act as a marker
		chessBoard[queenPositions[col]][col] = 2;

		chessBoard[row][col] = 1;

	}
	public void resetQueen(int row, int col) {

		if (chessBoard[row][col] == 1)
			chessBoard[row][col] = 0;
	}
	public QueensBoard(int[][] board, int[] positions) {

		this.chessBoard = board;
		this.queenPositions = positions;

	}

	private int[] generateQueens() {

		List<Integer> randomPosition = new ArrayList<Integer>();

		Random r = new Random();
		for (int i = 0; i < numberOfQueens; i++) {
			randomPosition.add(r.nextInt(8));
		}

		int[] randomPositions = new int[numberOfQueens];

		for (int i = 0; i < randomPosition.size(); i++) {
			randomPositions[i] = randomPosition.get(i);
		}

		return randomPositions;
	}

	public void placeRandomQueens() {

		queenPositions = generateQueens();

		for (int i = 0; i < chessBoard.length; i++) {
			chessBoard[queenPositions[i]][i] = 1;
		}

	}

	public int calculateCost() {

		int totalPairs = 0;
		int totalPairsRow = 0;
		int totalPairsDiagnalLeft = 0;
		int totalPairsDiagnalRight = 0;
		
//		int rowAttckCount = 0;
		
		//rows
		for (int i = 0; i < 8; i++) { 
//			rowAttckCount = 0;
			List<Integer> rowAttackingPairs = new ArrayList<>();
			for (int j = 0; j < 8; j++) {
				if (chessBoard[i][j] == 1) {
					//System.out.print(chessBoard.length + " ");
//					rowAttckCount ++;
					rowAttackingPairs.add(1); 			
				}
			}
	
				if (rowAttackingPairs.size() != 0){
					totalPairsRow = totalPairsRow + (rowAttackingPairs.size() - 1);
				}
				
		}
		
//		System.out.print( rowAttckCount + " ");
		
		
		// diagonal from left 
		
	for (int k = 0; k <= 14; k++) {
		List<Integer> diaginalLeftAttackingPairs = new ArrayList<>();;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (i + j - 14 == 0) {
						if (chessBoard[i][j] == 1) {
							diaginalLeftAttackingPairs.add(1);
						}
					}
				}

			}
			if (diaginalLeftAttackingPairs.size() != 0)
				totalPairsDiagnalLeft = totalPairsDiagnalLeft + (diaginalLeftAttackingPairs.size() - 1);
		}

		// check mirrored diagonal. couldn't figure out algorithm so solved brute force.
		// int pairs = checkMirrorDiagonal();
	
		totalPairs = totalPairsDiagnalLeft+totalPairsRow;
		return totalPairs ;
	}

	public void printBoard() {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(chessBoard[i][j] + " ");
			}
			System.out.println("");
		}
	}
}