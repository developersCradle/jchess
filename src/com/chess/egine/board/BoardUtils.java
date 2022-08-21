package com.chess.egine.board;

public class BoardUtils {

	public static final boolean[] FIRST_COLUMN = initColumn(0);
	public static final boolean[] SECOND_COLUMN = initColumn(1);
	public static final boolean[] SEVENTH_COLUMN = initColumn(6);
	public static final boolean[] EIGHT_COLUMN = initColumn(7);
	
	public static final boolean[] SECOND_ROW = null;
	public static final boolean[] SEVENTH_ROW = null;

	public static final int NUM_TILES = 64; //Use constants, not magic numbers
	public static final int NUM_TILES_PER_ROW = 8;
	private BoardUtils() {
		throw new RuntimeException("You cannot instatio me!");
	}

	private static boolean[] initColumn(int columNumber) {
		final boolean[] column = new boolean[NUM_TILES];
		do {
			column[columNumber] = true;
			columNumber += NUM_TILES_PER_ROW;
		} while (columNumber < NUM_TILES);
		return column;
	}

	public static boolean isValidTileCoordinate(final int coordinate) {
		return coordinate >= 0 && coordinate < NUM_TILES;
	}

}
