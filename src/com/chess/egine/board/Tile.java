package com.chess.egine.board;

import java.util.HashMap;
import java.util.Map;

import com.chess.egine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

/*
 * We are trying to make class immutable
 * 		- We don't provide any way to mutate state on our class object 
 */

/*
 * Chess board 64 tiles
 */
/*
 * Tile is numbered from 0 - 63
 */
public abstract class Tile {

	protected final int tileCoordinate;

	/*
	 * Caching empty tiles, Effective Java book immutable chapter with caching 
	 */
	private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

	private Tile(int tileCoordinate) {
		this.tileCoordinate = tileCoordinate;
	}

	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {

		final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

		for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
			emptyTileMap.put(i, new EmptyTile(i));
		}

		// Could have also used
		//Collections.unmodifiableMap(emptyTileMap)  We could also use this from collection 
		return ImmutableMap.copyOf(emptyTileMap);
	}

	public static Tile createTile(final int tileCoordinate, final Piece piece) {
		return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
	}

	public abstract boolean isTileOccupied();

	/*
	 * Return piece from given tile
	 * 	- Empty tile return null
	 * 	- Occupied tile returns Piece
	 */
	public abstract Piece getPiece();

	public static final class EmptyTile extends Tile {

		private EmptyTile(final int tileCoordinate) {
			super(tileCoordinate);
		}

		@Override
		public boolean isTileOccupied() {
			return false;
		}

		@Override
		public Piece getPiece() {
			return null; // Null is empty
		}
	}

	public static final class OccupiedTile extends Tile {
		private final Piece pieceOnTile;

		public OccupiedTile(int tileCoordinate, final Piece pieceOnTile) {
			super(tileCoordinate);
			this.pieceOnTile = pieceOnTile;
		}

		@Override
		public boolean isTileOccupied() {
			return true;
		}

		@Override
		public Piece getPiece() {
			return this.pieceOnTile;
		}
	}
}
