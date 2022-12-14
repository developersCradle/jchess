package com.chess.egine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.egine.Alliance;
import com.chess.egine.board.Board;
import com.chess.egine.board.BoardUtils;
import com.chess.egine.board.Move;
import com.chess.egine.board.Move.*;
import com.chess.egine.board.Tile;
import com.google.common.collect.ImmutableList;

public class Knight extends Piece {

	private final static int[] CANDITATE_MOVE_COORDINATES = { -17, -15, -10, -6, 6, 10, 15, 17 };

	Knight(final int piecePosition, final Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculteLegalMoves(final Board board) {

		final List<Move> legalMoves = new ArrayList();

		for (final int currentCandidateOffset : CANDITATE_MOVE_COORDINATES) {
			final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
			//if rule brakes down, we don't wana do rest of work
			if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {//this method useful to all classes

				if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset)
						|| isSecondColumnExclusion(this.piecePosition, currentCandidateOffset)
						|| isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset)
						|| isEightColumnExclusion(this.piecePosition, currentCandidateOffset)) {
					continue;
				}
				
				final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
				if (!candidateDestinationTile.isTileOccupied()) {
					legalMoves.add(new MajorMove(board,this,candidateDestinationCoordinate)); // Adding non attacking legal move
				} else {
					final Piece pieceAtDestination = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieaceAlliance();
					if (this.pieceAlliance != pieceAlliance) {
						legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
					}
				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}

	////////////////////EDGE CASES/////////////////////
	//Edge cases, when unit is first column in chess board
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset)
	{
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10 ||
				candidateOffset == 6 || candidateOffset == 15);
	}
	//Edge cases, when unit is second column in chess board
	private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset)
	{
		return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 || candidateOffset == 6);
	}
	//Edge cases, when unit is seventh column in chess board
	private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset)
	{
		return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == 10 || candidateOffset == -6);

	}
	//Edge cases, when unit is eight column in chess board
	private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.EIGHT_COLUMN[currentPosition] && (candidateOffset == -6 || candidateOffset == -15
				|| candidateOffset == 17 || candidateOffset == 10);
	}
	////////////////////////END EDGE CASES///////////////////////
}
