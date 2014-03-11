package othello.common;

/**
 * 二次元画像を扱うスーパークラス<br>
 *  
 * @author shinya
 *
 */
public abstract class Screen{

	private int width;
	private int height;
	
	/**
	 * コンストラクタ
	 * 
	 */
	public Screen(){
		width  = 0;
		height = 0;
	}
	public Screen(int width, int height){

		this.width  = width;
		this.height = height;
	}
	public Screen(Screen args){
		this.width  = args.getWidth();
		this.height = args.getHeight();
	}

	/**
	 * 横幅を返す
	 * 
	 * @return width int -  横幅
	 */
	public int getWidth(){
		return width;
	}
	/**
	 * 横幅をセット
	 * 
	 * @param width int - 横幅
	 */
	public void setWidth(int width){
		this.width = width;
	}

	/**
	 * 縦幅を返す
	 * 
	 * @return height int - 縦幅
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * 縦幅をセット
	 * 
	 * @param height int - 縦幅 
	 */
	public void setHeight(int height){
		this.height = height;
	}
}
