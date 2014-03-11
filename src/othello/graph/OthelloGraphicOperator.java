package othello.graph;


import java.awt.Image;

import othello.common.Counter;
import othello.constant.ImgPathConstant;


/**
 * 画像を取り扱うクラス
 * 
 * @author shin
 *
 */
public class OthelloGraphicOperator implements ImgPathConstant{
	
    private GraphSet boardImg;      //盤の画像
  	private GraphSet whiteImg;      //白駒の画像
  	private GraphSet blackImg;      //黒駒の画像
  	
  	private Counter cnt;
  	
  	public OthelloGraphicOperator(){
  		
  		cnt = new Counter();
  		
  		boardImg = new GraphSet(BOARD_IMG_PATH, cnt.getValue());
  		cnt.countUp();
  		
  		blackImg = new GraphSet(BLACK_IMG_PATH, cnt.getValue());
  		cnt.countUp();
  		
  		whiteImg = new GraphSet(WHITE_IMG_PATH, cnt.getValue());
  		cnt.countUp();
  		
  	}
  	
  	public Image getBoardImage(){
  		return boardImg.getImg();
  	}
  	public Image getBlackImage(){
  		return blackImg.getImg();
  	}
  	public Image getWhiteImage(){
  		return whiteImg.getImg();
  	}
  	
  	public int getBoardId(){
  		return boardImg.getId();
  	}
  	public int getBlackId(){
  		return blackImg.getId();
  	}
  	public int getWhiteId(){
  		return whiteImg.getId();
  	}


}
