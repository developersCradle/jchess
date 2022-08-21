package com.chess.egine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.egine.Alliance;
import com.chess.egine.board.Board;
import com.chess.egine.board.BoardUtils;
import com.chess.egine.board.Move;
import com.chess.egine.board.Tile;
import com.chess.egine.board.Move.AttackMove;
import com.chess.egine.board.Move.MajorMove;
import com.google.common.collect.ImmutableList;

public class Queen extends Piece {

	
	Queen(int piecePosition, Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
	}

	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -9, -8, -7, -1, 1, 7, 8, 9  };
	@Override
	public Collection<Move> calculteLegalMoves(final Board board) {
		
		final List<Move> legalMoves = new ArrayList<>();

		for (final int canditateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {

			int candidateDestinationCoordinate = this.piecePosition;

			while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
				
				if (isFirstColumnExclusion(candidateDestinationCoordinate,canditateCoordinateOffset) || 
					isEightColumnExclusion(candidateDestinationCoordinate,canditateCoordinateOffset)){
					break;
				}
				
				candidateDestinationCoordinate += canditateCoordinateOffset;

				if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

					final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
					if (!candidateDestinationTile.isTileOccupied()) {
						legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate)); // Adding non
																									// attacking legal
																									// move
					} else {
						final Piece pieceAtDestination = candidateDestinationTile.getPiece();
						final Alliance pieceAlliance = pieceAtDestination.getPieaceAlliance();
						if (this.pieceAlliance != pieceAlliance) {
							legalMoves.add(
									new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
						}
						break;
					}
				}
			}
		}

		return ImmutableList.copyOf(legalMoves);
	}
	
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset)
	{
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == 7 || candidateOffset == -1);
	}

	private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset)
	{
		return BoardUtils.EIGHT_COLUMN[currentPosition] && (candidateOffset == 9 || candidateOffset == -7 || candidateOffset == 1 );
	}
}
