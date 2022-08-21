package com.chess.egine.board;

import com.chess.egine.pieces.Piece;
/*
 * Moves are separated
 * - Attacking move
 * - NON attacking move
 * 
 */
public abstract class Move {
	
	final Board board; //Where we moved
	final Piece movedPiece; //Piece that was moved
	final int destinationCoordinates; //Where that piece moved
	
	private Move(final Board board, final Piece movedPiece, final int destinationCoordinates) {
		this.board = board;
		this.movedPiece = movedPiece;
		this.destinationCoordinates = destinationCoordinates;
	}
	
	
	public static final class MajorMove extends Move
	{

		public MajorMove(final Board board,final Piece movedPiece, final int destinationCoordinates) {
			super(board, movedPiece, destinationCoordinates);
		}
		
	}
	
	public static final class AttackMove extends Move
	{
		final Piece attackedPiece; //In attack move we want to know which piece were attacked
		public AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinates, final Piece attackedPiece) {
			super(board, movedPiece, destinationCoordinates);
			this.attackedPiece = attackedPiece;
		}
		
	}
}
