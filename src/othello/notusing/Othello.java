package othello.notusing;

import othello.constant.BoardState;
import othello.constant.OthelloCommonConstant;

/**
 * オセロゲームを行うために使用するクラスです
 * @author Shinya
 *
 */
public class Othello implements OthelloCommonConstant, BoardState{

    //ボードのステータス
    private int CELL;
    private int X_CORNER_L;
    private int Y_CORNER_U;
    private int X_CORNER_R;
    private int Y_CORNER_D;
    
  	//盤上を管理する配列　要素数は91個
    private final int BOARD_CNT = 91; 
    private int pieceFlag[] = new int[BOARD_CNT];
	
	//どちらのターンか(最初は必ず黒のターン)
	private boolean playerTurn = BLACK_TURN;

	
	/**
	 * コンストラクタ
	 * 
	 */
	public Othello(){
	    CELL = 53;
	    X_CORNER_L = 22;
	    Y_CORNER_U = 35;
	    X_CORNER_R = 448;
	    Y_CORNER_D = 460;
	    
	    InitBoard();
	}
	public Othello(int cell, int x_L, int x_R, int y_U, int y_D){
		CELL = cell;
	    X_CORNER_L = x_L;
	    Y_CORNER_U = x_R;
	    X_CORNER_R = y_U;
	    Y_CORNER_D = y_D;
	    
	    InitBoard();

	}
	
	//各ゲッター
	/**
	 * マスの横座標を渡して、実際に描画する座標を返す
	 * 
	 * @param x マスの座標(1～8)
	 * @return X　画面描画用のX座標
	 */
	public int getPieceX(int x){
		return X_CORNER_L + ((x-1)*CELL);
	}
	
	/**
	 * マスのたて座標を渡して、実際の秒に使う座標を返す
	 * @param y マスの座標(1～8)
	 * @return Y 画面描画用の座標
	 */
	public int getPieceY(int y){
		return Y_CORNER_U + ((y-1)*CELL);
	}
	
	/**
	 * マウスの座標から、マスの横座標を求める
	 * 
	 * @param x
	 * @return 左から何マス目か(1～8)
	 */
	public int getMouseX(int x){
		return (((x - X_CORNER_L) / CELL)*CELL) +X_CORNER_L+5;
	}
	
	/**
	 * マウスのたて座標から、何マス目に相当するかを返す
	 * 
	 * @param y
	 * @return 上から何マス目か(1～8)
	 */
	public int getMouseY(int y){
		return (((y - Y_CORNER_U) / CELL)*CELL) +Y_CORNER_U+5;
	}
	
	/**
	 * マウスの周りに表示する円の直径を表示
	 * 
	 * @return
	 */
	public int getMouseCircle(){
		return CELL - (int)(CELL/7.57);
	}
	
	/**
	 * 座標を渡したマスの状態を返す
	 * 
	 * @param x 横座標
	 * @param y 縦座標
	 * @return マスの状態
	 */
	public int getState(int x, int y){
		return pieceFlag[x*9+y];
	}
	public int getX_L(){
		return X_CORNER_L;
	}
	public int getX_R(){
		return X_CORNER_R;
	}
	public int getY_U(){
		return Y_CORNER_U;
	}
	public int getY_D(){
		return Y_CORNER_D;
	}
	
	/**
	 * 今がどちらのターンかを返す
	 * 
	 * @return ターンフラグ(白か黒か)
	 */
	public boolean getTurn(){
		return playerTurn;
	}


	/**
	 * ボード盤初期化関数
	 *
	 */
	private void InitBoard(){
		int i, j;

		for(i=0;i<BOARD_CNT-1;i++){
			pieceFlag[i] = WALL;
		}

		for(i=1;i<9;i++){
			for(j=1;j<9;j++){
			pieceFlag[i*9+j] = BLANK;
			}
		}

		pieceFlag[D4] = WHITE;
		pieceFlag[D5] = BLACK;
		pieceFlag[E4] = BLACK;
		pieceFlag[E5] = WHITE;
		
	}
	
	/**
	 * 駒が置けるかどうかのチェック＆実際に配置<br>
	 * 0ならfalseを返す
	 * 
	 * @param x マスの横座標
	 * @param y マスの縦座標
	 * @return 駒の配置成功フラグ
	 */
	public boolean putPiece(int x, int y){
		
		x = (x - X_CORNER_L) / CELL + 1;
		y = (y - Y_CORNER_U) / CELL + 1;

		int m =  y * 9 + x;
		
		if(pieceFlag[(y*9)+x] == BLANK && playerTurn == BLACK_TURN){
			if(FlipBlack(m)>0){
				pieceFlag[(y*9)+x] = BLACK;
				playerTurn = WHITE_TURN;
				return true;
			}
			else{
				return false;
			}
		}
		else if(pieceFlag[(y*9)+x] == BLANK && playerTurn == WHITE_TURN){
			if(FlipWhite(m)>0){
				pieceFlag[(y*9)+x] = WHITE;
				playerTurn = BLACK_TURN;
				return true;
			}
			else{
				return false;
			}

		}
		else{
			return true;
		}

		
	}
	
	/**
	 * 黒の駒を返す処理<br>
	 * 戻り値は返した駒の数
	 * 
	 * @param マスの場所
	 * @return ひっくり返した駒の数
	 */
	private int FlipBlack(int m){

		int n,sum=0;
		n = m;
		
		sum += FlipLineBlack(m,-10,n);
		sum += FlipLineBlack(m, -9,n);
		sum += FlipLineBlack(m, -8,n);
		sum += FlipLineBlack(m, -1,n);
		sum += FlipLineBlack(m,  1,n);
		sum += FlipLineBlack(m,  8,n);
		sum += FlipLineBlack(m,  9,n);
		sum += FlipLineBlack(m, 10,n);

		return sum;
	}


	/**
	 * 一方向に黒駒を返す処理
	 * 
	 * @param m
	 * @param dir
	 * @param num
	 * @return 返した駒の数
	 */
	private int FlipLineBlack(int m, int dir, int num){

		int i = 0;
		
		m += dir;

		while(pieceFlag[m] == WHITE){
			m += dir;
		}
		
		if(pieceFlag[m] == BLANK || pieceFlag[m] == WALL){
			return 0;
		}

		m -= dir;

		while(num != m){
			pieceFlag[m] = BLACK;
			m -= dir;
			i++;
		}

		return i;
	}




	/*========================
	   白の駒を返す処理
	   戻り値は返した駒の数
	==========================*/
	private int FlipWhite(int m){

		int n,sum=0;
		n = m;
		
		sum += FlipLineWhite(m,-10,n);
		sum += FlipLineWhite(m, -9,n);
		sum += FlipLineWhite(m, -8,n);
		sum += FlipLineWhite(m, -1,n);
		sum += FlipLineWhite(m,  1,n);
		sum += FlipLineWhite(m,  8,n);
		sum += FlipLineWhite(m,  9,n);
		sum += FlipLineWhite(m, 10,n);

		return sum;
	}

	/*===========================
	   一方向に白駒を返す処理。
	   戻り値は返した駒の数
	=============================*/
	private int FlipLineWhite(int m, int dir, int Num){

		int i = 0;
		
		m += dir;

		while(pieceFlag[m] == BLACK){
			m += dir;
		}
		
		if(pieceFlag[m] != WHITE){
			return 0;
		}

		m -= dir;

		while(Num != m){
			pieceFlag[m] = WHITE;
			m -= dir;
			i++;
		}

		return i;
	}
	
	/**
	 * 駒の数を計算する
	 * 
	 * @param 駒の色(白か黒か)
	 * @return 引数で受け取った色の駒の数
	 */
	public int CountPiece(int PieceColor){

		int i;
		int counter=0;

		if(PieceColor==WHITE){
			for(i=A1;i<=H8;i++){
				if(pieceFlag[i]==WHITE){
					counter++;
				}
			}
		}
		else if(PieceColor==BLACK){
			for(i=A1;i<=H8;i++){
				if(pieceFlag[i]==BLACK){
					counter++;
				}
			}
		}
		
		return counter;
	}
	
	/**
	 * 白黒問わず、全ての駒の数を数える
	 * 
	 * @return 全ての駒の数
	 */
	public int countAllPiece(){
		
		int cnt = 0;
				
		for(int i=A1;i<=H8;i++){
			if(pieceFlag[i]==WHITE || pieceFlag[i]==BLACK){
				cnt++;
			}
		}		
		return cnt;
	}


}
