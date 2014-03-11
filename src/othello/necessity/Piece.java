package othello.necessity;

import othello.common.Screen;
import othello.constant.BoardState;

/**
 * オセロで使用する駒クラス
 * 
 * @author shinya
 *
 */
public class Piece extends Screen {

	private int range;
	private int headColor;
	private int tailColor;
	
	/**
	 * コンストラクタ<br>
	 * 表側を最初は黒に。<br>
	 * 基本的にデフォルトコンストラクタは使用しない
	 *
	 */
	public Piece(){
		super();
		this.range = 0;
		headColor = BoardState.BLACK;
	}
	public Piece(int range){
		super();
		this.range = range;
	}

	/**
	 * 駒の大きさ(半径)を返す
	 * 
	 * @return 半径
	 */
	public int getRange(){
		return range;
	}
	
	/**
	 * 駒の大きさ(半径)を設定する
	 * 
	 * @param range 半径
	 */
	public void setRange(int range){
		this.range = range;
	}

	/**
	 * 駒の表の色をセットする
	 * 
	 * @param toColor
	 */
	public void setColor(int toColor){
		
		if(toColor == BoardState.WHITE){
			setWhiteColor();
		}
		else{//color == BLACK
			setBlackColor();
		}

	}

	/**
	 * 駒の色を黒色に設定
	 *
	 */
	private void setBlackColor(){
		headColor = BoardState.BLACK;
		tailColor = BoardState.WHITE;
	}
	
	/**
	 * 駒の色を白色に設定
	 *
	 */
	private void setWhiteColor(){
		headColor = BoardState.WHITE;
		tailColor = BoardState.BLACK;
	}

	/**
	 * 現在の駒の表側の色を返す
	 * 
	 * @return 表側の色
	 */
	public int getHeadColor(){
		return headColor;
	}

	/**
	 * 現在の駒の裏側の色を返す
	 * 
	 * @return 裏側の色
	 */
	public int getTailColor(){
		return tailColor;
	}

}
