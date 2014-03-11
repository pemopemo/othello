package othello.necessity;

import othello.common.Counter;
import othello.common.Screen;
import othello.constant.BoardScreenConstant;
import othello.constant.BoardState;

/**
 * オセロボード盤クラス
 * 
 * @author shin
 *
 */
public class Board extends Screen implements BoardScreenConstant,BoardState{

	private int xLeft;
    private int xRight;
    private int yUp;
    private int yDown;
    private int cell;

	private Piece piece;
	
  
  	//盤上を管理する配列　要素数は91個
    private static final int BOARD_CNT = 91; 
    private int pieceFlag[] = new int[BOARD_CNT];
    private int now;

    /**
     * コンストラクタ
     *
     */
    public Board(){
    	super();
    	init();
    }
    public Board(int width, int height){
    	super(width, height);
    	init();
    }

    /**
     * ボードの初期化<br>
     * 画像の領域設定をする
     *
     */
    private void init(){
    	cell   = CELL;
    	xLeft  = X_LEFT;
    	xRight = X_RIGHT;
    	yUp    = Y_UP;
    	yDown  = Y_DOWN;
    	now    = 0;
    	
    	this.piece = new Piece(cell - (int)(cell / 7.57));

    	initBoard();
    }
    
	/**
	 * ボード盤の駒配置を最初の状態にする
	 *
	 */
	private void initBoard(){

		int i, j;

		for(i=0;i<BOARD_CNT-1;i++){
			pieceFlag[i] = WALL;
		}

		for(i=1;i<9;i++){
			for(j=1;j<9;j++){
			pieceFlag[i*9+j] = BLANK;
			}
		}
		//真ん中に白黒二つずつ配置する
		pieceFlag[D4] = WHITE;
		pieceFlag[D5] = BLACK;
		pieceFlag[E4] = BLACK;
		pieceFlag[E5] = WHITE;
		
		
	}


	/**
	 * まだひっくり返せる？
	 * @return YesかNoか
	 */
    public boolean hasNext(){

    	if(pieceFlag.length-1 <= now){
    		return true;
    	}
    	
    	return false;
    }
    
    /**
     * マウスの座標から、盤上の座標へ変換<br>
     * どうでもいいがメソッド名が長い
     * 
     * @param x マウスのX座標
     * @param y マウスのY座標
     * @return 盤上の座標変換値
     */
	public int getMouseToBoardPosition(int x, int y){
		x = (x - getXLeft() ) / getCell() + 1;
		y = (y - getYUp()   ) / getCell() + 1;
		
		return  y * 9 + x;

	}
	
	/**
	 * ヒント表示用ひっくり返すわけではなく<br>
	 * ひっくり返せるかの数を数える
	 * @param position
	 * @param color
	 * @return
	 */
	public int virtualFlip(int position, int color){
    	piece.setColor(color);

    	Counter sum = new Counter();
		
		setPosition(position);
		
		sum.plus( virtualFlipLine(LEFT_UP   ) );
		sum.plus( virtualFlipLine(UP        ) );
		sum.plus( virtualFlipLine(RIGHT_UP  ) );
		sum.plus( virtualFlipLine(LEFT      ) );
		sum.plus( virtualFlipLine(RIGHT     ) );
		sum.plus( virtualFlipLine(LEFT_DOWN ) );
		sum.plus( virtualFlipLine(DOWN      ) );
		sum.plus( virtualFlipLine(RIGHT_DOWN) );

		return sum.getValue();
	}
	private int virtualFlipLine(int forward){

		Counter virtualflipedCount = new Counter();

		int putPosition = getPosition();
		
		next(forward);
		
		while(getPieceState() == piece.getTailColor()){
			next(forward);
		}
		
		if(getPieceState() == BoardState.BLANK || getPieceState() == BoardState.WALL){
			setPosition(putPosition);
			return 0;
		}

		preview(forward);

		while(putPosition != getPosition()){
			preview(forward);
			virtualflipedCount.countUp();
		}
		
		setPosition(putPosition);

		return virtualflipedCount.getValue();
	}
	
	
	/**
	 * コマをひっくり返す処理
	 * 
	 * @param position コマを置こうとする場所
	 * @param color ひっくり返すコマの色
	 * @return　ひっくり返したコマの合計
	 */
    public int flip(int position, int color){
    	
    	piece.setColor(color);

    	Counter sum = new Counter();
		
		setPosition(position);
		
		sum.plus( flipLine(LEFT_UP   ) );
		sum.plus( flipLine(UP        ) );
		sum.plus( flipLine(RIGHT_UP  ) );
		sum.plus( flipLine(LEFT      ) );
		sum.plus( flipLine(RIGHT     ) );
		sum.plus( flipLine(LEFT_DOWN ) );
		sum.plus( flipLine(DOWN      ) );
		sum.plus( flipLine(RIGHT_DOWN) );

		return sum.getValue();
	}

    /**
     * 一方向にコマをひっくり返す処理
     * 
     * @param forward 進むマスの方向
     * @return ひっくり返したコマの数
     */
	private int flipLine(int forward){

		Counter flipedCount = new Counter();

		int putPosition = getPosition();
		
		next(forward);
		
		while(getPieceState() == piece.getTailColor()){
			next(forward);
		}
		
		if(getPieceState() == BoardState.BLANK || getPieceState() == BoardState.WALL){
			setPosition(putPosition);
			return 0;
		}

		preview(forward);

		while(putPosition != getPosition()){
			setPieceState(piece.getHeadColor());
			preview(forward);
			flipedCount.countUp();
		}
		
		setPosition(putPosition);

		return flipedCount.getValue();
	}


	/**
	 * 次のマスへ
	 *
	 */
    private void next(){
    	now++;
    }
    
    /**
     * 渡された数値分、盤上を進む
     * 
     * @param i 戻るマス数
     */
    private void next(int i){
    	now += i;
    }
    
    /**
     * 前のマスへ
     *
     */
    private void preview(){
    	now --;
    }
    
    /**
     * 渡された数値分、盤上を戻る
     * 
     * @param i 戻るマス数
     */
    private void preview(int i){
    	now -= i;
    }

    /**
     * 現在の盤上インデックスを返す
     * 
     * @return インデックス
     */
    private int getPosition(){
    	return now;
    }
    
    /**
     * 現在の盤上インデックスを設定する
     * 
     * @param position
     */
    private void setPosition(int position){
    	now = position;
    }
    private int getPieceState(){
    	return pieceFlag[now];
    }
    
    
    /**
     * 指定座標のマスの状態を返す
     * 
     * @param x
     * @param y
     * @return
     */
    public int getPieceState(int x, int y){
    	return getState(x * 9 + y);
    }
    public int getPieceState(int index){
    	return getState(index);
    }
    
    /**
     * 渡された盤上インデックスの状態を返す
     * 
     * @param index
     * @return 状態(白か黒か空白か壁か)
     */
    private int getState(int index){
    	return pieceFlag[index];
    }
    
    /**
     * 現在のマスに駒をセット(白か黒か)
     * 
     * @param state
     */
    public void setPieceState(int state){
    	pieceFlag[now] = state;
    }
    
   /**
    * マスの幅を取得
    * 
    * @return
    */
    public int getCell(){
    	return cell;
    }
    
    /**
     * 画面のボード左端の座標を取得
     * 
     * @return
     */
	public int getXLeft(){
    	return xLeft;
    }
	
	/**
	 * 画面のボード右端の座標を取得
	 * 
	 * @return
	 */
    public int getXRight(){
    	return xRight;
    }
    
    /**
     * 画面のボード座標上端を取得
     * 
     * @return
     */
    public int getYUp(){
    	return yUp;
    }
    
    /**
     * 画面のボード座標下端を取得
     * 
     * @return
     */
    public int getYDown(){
    	return yDown;
    }
    
    /**
     * ゲームに使われる駒クラスを返す
     * 
     * @return 駒クラス
     */
    public Piece getPiece(){
    	return piece;
    }
    
//    private void plusFlipedCount(int count){
//    	if(piece.getHeadColor() == BLACK){
//    		blackCnt.plus(count);
//    	}else{
//    		whiteCnt.plus(count);
//    	}
//    }

    /**
     * 盤上の黒駒の数を数えて返す
     * 
     * @return 黒駒の数
     */
    public int getOnBoardBlackPieceCount(){
    	
    	int sum = 0;

    	for(int i = 0;i<BOARD_CNT; i++){
    		if(pieceFlag[i] == BLACK){
    			sum++;
    		}
    	}

    	return sum;
    }

    /**
     * 盤上の白駒の数を数えて返す
     * 
     * @return 白駒の数
     */
    public int getOnBoardWhitePieceCount(){
    	
    	int sum = 0;
    	
    	for(int i = 0;i<BOARD_CNT; i++){
    		if(pieceFlag[i] == WHITE){
    			sum++;
    		}
    	}
    	return sum;
    }

}
