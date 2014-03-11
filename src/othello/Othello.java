package othello;

import othello.constant.BoardState;
import othello.constant.GraphicConstant;
import othello.necessity.Board;
import othello.necessity.Piece;
import othello.necessity.Player;

/**
 * オセロ本体部分のクラス
 *
 * @author shinya
 *
 */
public class Othello implements GraphicConstant{

	private Player player; // プレイヤー
	private Board board;   // オセロ盤
	private Piece p;       // オセロの駒

	public static final int BLACK = BoardState.BLACK;
	public static final int WHITE = BoardState.WHITE;

	/**
	 * コンストラクタ<br>
	 * プレイヤーとオセロ盤を生成
	 *
	 */
	public Othello(){
		player = new Player();
		board  = new Board(WIDTH, HEIGHT);
	}

	/**
	 * オセロの駒を置く
	 *
	 * @param x マウスのX座標
	 * @param y マウスのY座標
	 * @return boolean 置けたか置けなかったかの判定
	 */
	public boolean putPiece(int x, int y){

		int position = board.getMouseToBoardPosition(x, y);

		//ひっくり返す駒がある、且つ置く対象のマスが空白の時に限り駒を配置できる
		if(board.getPieceState(position) == Board.BLANK
				&& board.flip(position, player.getNowColor()) > 0) {

			board.setPieceState(player.getNowColor());
			player.changeTurn();
			return true;
		}

		return false;
	}

	/**
	 * オセロの駒が置けるか判断
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean hasPutPlace(int x, int y){

		int position = x * 9 + y;

		//ひっくり返す駒がある、且つ置く対象のマスが空白なら置けるんじゃね？という判断のみ
		if(board.getPieceState(position) == Board.BLANK
				&& board.virtualFlip(position, player.getNowColor()) > 0) {

			return true;
		}

		return false;
	}

	/**
	 * 終了判定
	 * @return
	 */
	public boolean isEnd(){
		int blackCnt = board.getOnBoardBlackPieceCount();
		int whiteCnt = board.getOnBoardWhitePieceCount();

		int sum = blackCnt + whiteCnt;

		// 全てのマスが埋まる、もしくは白の駒がなくなる、もしくは黒の駒がなくなるとしゅーりょー！
		if(sum == 64 || blackCnt == 0 || whiteCnt == 0){
			return true;
		}

		return false;
	}

	/**
	 * マスの横座標を渡して、実際に描画する座標を返す
	 *
	 * @param x マスの座標(1～8)
	 * @return int - 画面描画用のX座標
	 */
	public int getPieceX(int x){
		return board.getXLeft() + ( (x - 1) * board.getCell() );
	}

	/**
	 * マスのたて座標を渡して、実際の描画に使う座標を返す
	 * @param y マスの座標(1～8)
	 * @return int - 画面描画用の座標
	 */
	public int getPieceY(int y){
		return board.getYUp() + ((y - 1) * board.getCell() );
	}

	/**
	 * ゲームに使用しているボードクラスを返す
	 *
	 * @return Board - 使用中ボードクラス
	 */
	public Board getBoard(){
		return board;
	}

	/**
	 * ゲームに使用しているプレイヤークラスを返す
	 *
	 * @return Player - 使用中プレーヤークラス
	 */
	public Player getPlayer(){
		return player;
	}

	/**
	 * ゲームに使われるものと同じ型の駒クラスを返す
	 *
	 * @return Piece - 駒クラス
	 */
	public Piece getPiece(){
		return p;
	}



}
