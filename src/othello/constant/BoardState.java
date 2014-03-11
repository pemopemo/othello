package othello.constant;

public interface BoardState {

	/*  ボードの座標
	 * │A1│B1│C1│D1│E1│F1│G1│H1│
	 * │A2│B2│C2│D2│E2│F2│G2│H2│
	 * │A3│B3│C3│D3│E3│F3│G3│H3│
	 * │A4│B4│C4│D4│E4│F4│G4│H4│
	 * │A5│B5│C5│D5│E5│F5│G5│H5│
	 * │A6│B6│C6│D6│E6│F6│G6│H6│
	 * │A7│B7│C7│D7│E7│F7│G7│H7│
	 * │A8│B8│C8│D8│E8│F8│G8│H8│
	 */

	public static final int A1 = 10;
	public static final int B1 = 11;
	public static final int C1 = 12;
	public static final int D1 = 13;
	public static final int E1 = 14;
	public static final int F1 = 15;
	public static final int G1 = 16;
	public static final int H1 = 17;
	public static final int A2 = 19;
	public static final int B2 = 20;
	public static final int C2 = 21;
	public static final int D2 = 22;
	public static final int E2 = 23;
	public static final int F2 = 24;
	public static final int G2 = 25;
	public static final int H2 = 26;
	public static final int A3 = 28;
	public static final int B3 = 29;
	public static final int C3 = 30;
	public static final int D3 = 31;
	public static final int E3 = 32;
	public static final int F3 = 33;
	public static final int G3 = 34;
	public static final int H3 = 35;
	public static final int A4 = 37;
	public static final int B4 = 38;
	public static final int C4 = 39;
	public static final int D4 = 40;
	public static final int E4 = 41;
	public static final int F4 = 42;
	public static final int G4 = 43;
	public static final int H4 = 44;
	public static final int A5 = 46;
	public static final int B5 = 47;
	public static final int C5 = 48;
	public static final int D5 = 49;
	public static final int E5 = 50;
	public static final int F5 = 51;
	public static final int G5 = 52;
	public static final int H5 = 53;
	public static final int A6 = 55;
	public static final int B6 = 56;
	public static final int C6 = 57;
	public static final int D6 = 58;
	public static final int E6 = 59;
	public static final int F6 = 60;
	public static final int G6 = 61;
	public static final int H6 = 62;
	public static final int A7 = 64;
	public static final int B7 = 65;
	public static final int C7 = 66;
	public static final int D7 = 67;
	public static final int E7 = 68;
	public static final int F7 = 69;
	public static final int G7 = 70;
	public static final int H7 = 71;
	public static final int A8 = 73;
	public static final int B8 = 74;
	public static final int C8 = 75;
	public static final int D8 = 76;
	public static final int E8 = 77;
	public static final int F8 = 78;
	public static final int G8 = 79;
	public static final int H8 = 80;

	//ボードの状態
	public static final int BLANK = 0;
	public static final int BLACK = 1;
	public static final int WHITE = 2;
	public static final int WALL  = 4;

	//ひっくり返す方向を配列の+/-管理
	public static final int LEFT_UP    = -10;
	public static final int UP         =  -9;
	public static final int RIGHT_UP   =  -8;
	public static final int LEFT       =  -1;
	public static final int RIGHT      =   1;
	public static final int LEFT_DOWN  =   8;
	public static final int DOWN       =   9;
	public static final int RIGHT_DOWN =  10;

}
