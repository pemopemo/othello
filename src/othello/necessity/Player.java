package othello.necessity;

import othello.constant.BoardState;
import othello.constant.GraphicConstant;

/**
 * オセロで使用するプレイヤークラス
 * 
 * @author shinya
 *
 */
public class Player implements GraphicConstant{
	
	private int    turnColor;
	
	/**
	 * コンストラクタ
	 * 最初は必ず黒のターン
	 */
	public Player(){
		turnColor = BoardState.BLACK;
	}

	/**
	 * プレイヤーのターンを変更する<br>
	 * これをしないとずっと俺のターン
	 *
	 */
	public void changeTurn(){
		if(turnColor == BoardState.BLACK){
			turnColor    = BoardState.WHITE;
		}
		else{//turnColor == WHITE
			turnColor    = BoardState.BLACK;
		}
	}
	
	public int getNowColor(){
		return turnColor;
	}

}
