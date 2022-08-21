package com.chess.egine;


public enum Alliance {
	WHITE {
		@Override
		public int getDirection() {
			return -1;
		}

		@Override
		public boolean isWhite() {
			return true;
		}

		@Override
		public boolean isBlack() {
			return false;
		}
	},
	BLACK {
		@Override
		public int getDirection() {
			return 1;
		}

		@Override
		public boolean isWhite() {
			return false;
		}

		@Override
		public boolean isBlack() {
			return true;
		}
	};

	public abstract boolean isWhite();
	public abstract boolean isBlack();
	
	public abstract int getDirection();
	/*
	 * White -1
	 * Black 1
	 */

}
