package com.chess.egine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.egine.Alliance;
import com.chess.egine.board.Board;
import com.chess.egine.board.BoardUtils;
import com.chess.egine.board.Move;
import com.google.common.collect.ImmutableList;

import static com.chess.egine.board.Move.*;

public class Pawn extends Piece{
	
	//8 is foward when your white
	private final static int[] CANDIDATE_MOVE_COODRINATE = { 8 , 16, 7, 9 };

	Pawn(final int piecePosition, final Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculteLegalMoves(final Board board) {
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for (final int currentCanditateOffset : CANDIDATE_MOVE_COODRINATE)
		{
			
			final int candidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * currentCanditateOffset);
		
		
			if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
				continue;
			}

			if (currentCanditateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
				// TODO more work hand non attacking move
				legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
			} else if (currentCanditateOffset == 16 && this.isFirstMove() //Pawn jump
					&& (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieaceAlliance().isBlack())
					|| (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieaceAlliance().isWhite())) {

				final int behindCoordinateDestinationCoordinate = this.piecePosition
						+ (this.getPieaceAlliance().getDirection() * 8);

				if (!board.getTile(behindCoordinateDestinationCoordinate).isTileOccupied()
						&& !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
					legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
				}

			} else if (currentCanditateOffset == 7 &&
					!((BoardUtils.EIGHT_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
					(BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())))) {
				
				if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
					final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece(); 
					if (this.pieceAlliance != pieceOnCandidate.getPieaceAlliance()) {
						//TODO more to be here
						legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
					}
				}
			}else if(currentCanditateOffset == 9)
			{
				
			}
		}

		return ImmutableList.copyOf(legalMoves);
	}



}
