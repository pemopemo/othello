package othello.common;

/**
 * カウントを行うクラス<br>
 * ボタンを押す感覚で、カウントォォォ～
 * 
 * @author shinya
 *
 */
public class Counter {

	private int count;   // カウンターの値
	private int step;    // カウントアップする単位
	
	/**
	 * デフォルトコンストラクタ<br>
	 * 0から始めて1ずつ増える
	 * 
	 */
	public Counter(){
		count = 0;
		step  = 1;
	}
	/**
	 * コンストラクタ<br>
	 * 引数から始めて1ずつ増える
	 * 
	 * @param start
	 */
	public Counter(int start){
		count = start;
		step  = 1;
	}
	
	/**
	 * コンストラクタ<br>
	 * startから始めてstepValueの値ずつ増えていく
	 * 
	 * @param start 初期値
	 * @param stepValue 増える値
	 */
	public Counter(int start, int stepValue){
		count = start;
		step  = stepValue;
	}

	/**
	 * 現在の値を単位数だけカウンターを増やす<br>
	 * デフォルトでは1増える
	 *
	 */
	public void countUp(){
		count += step;
	}
	
	/**
	 * 現在の値を単位数だけカウンターを減らす<br>
	 * デフォルトでは1減る
	 * 
	 */
	public void countDown(){
		count -= step;
	}

	/**
	 * 引数で渡った数の分だけカウンターを増やす
	 * 
	 * @param value 増加値
	 */
	public void plus(int value){
		count += value;
	}

	/**
	 * 引数で渡った数の分だけカウンターを減らす
	 * 
	 * @param value 減少値
	 */
	public void minus(int value){
		count -= value;
	}

	/**
	 * カウンターをリセットする<br>
	 * リセットは、基本的に現在の値を0に戻して単位数を1にする
	 *
	 */
	public void reset(){
		count = 0;
		step  = 1;
	}

	/**
	 * 現在のカウンターの値を取得
	 * 
	 * @return 現在値
	 */
	public int getValue(){
		return count;
	}
	
	/**
	 * カウンターの値を変更する
	 * 
	 * @param value 変更したい値
	 */
	public void setValue(int value){
		count = value;
	}
	
	/**
	 * 増やす単位を取得
	 * 
	 * @return 単位
	 */
	public int getStepValue(){
		return step;
	}
	
	/**
	 * 増やす単位を設定する
	 * 
	 * @param stepValue
	 */
	public void setStepValue(int stepValue){
		step = stepValue;
	}
}
