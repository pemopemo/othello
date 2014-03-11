package othello;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
//import java.awt.Image;
import java.awt.MediaTracker;
//import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import othello.graph.OthelloGraphicOperator;
import othello.constant.Messages;


public class OthelloPanel extends JPanel implements MouseListener, MouseMotionListener, Messages {

    //オセロクラスを宣言
	private Othello othello;
	private OthelloGraphicOperator ope;

    private final int fontSize = 20;
    private final int strX = Othello.WIDTH - (fontSize * 8);	//480;
    private final int strY = 50;
    private final int StateX = 500;
    private final int StateY1 = 420;
    private final int StateY2 = StateY1 + 30;

    private int hintRange = 8;
    private boolean passFlg = false;


    //マウスの周りに表示する円
    private int mX1, mY1, mXY2, mXYhalf;

    private int mouseX, mouseY;

    //フォント変更用
    private Font fnt1, fnt2;


    //コンストラクタ(主に初期化式を記述)
    public OthelloPanel() {
        //オセロの実体化
        othello = new Othello();

        // パネルの推奨サイズを設定、pack()するときに必要
        setPreferredSize(new Dimension(othello.getBoard().getWidth(), othello.getBoard().getHeight()));

        //画像をロード
        readImage();

        //Font使用
        fnt1 = new Font("ＭＳ ゴシック", Font.PLAIN, fontSize);
        fnt2 = new Font("ＭＳ ゴシック", Font.PLAIN, fontSize -8);

    	mXY2 = othello.getBoard().getPiece().getRange();

    	mXYhalf = mXY2 / 2 - hintRange / 2 + 5;

        // MouseListenerを登録
        addMouseListener(this);
        addMouseMotionListener(this);

    }

    //描画メソッド
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //背景を表示
        g.drawImage(ope.getBoardImage(), 0,0,this);

    	//色を変更
		g.setColor(new Color(255,160,56));

		int canflip=0;

        //盤上に駒を順番に表示していく
    	for(int i=1;i<9;i++){
    		for(int j=1; j<9; j++){
    			if(othello.getBoard().getPieceState(i, j) == Othello.BLACK){
    				//黒駒を表示
    				g.drawImage(ope.getBlackImage(), othello.getPieceX(j), othello.getPieceY(i), this);
    			}else if(othello.getBoard().getPieceState(i, j) == Othello.WHITE){
    				//白駒を表示
    				g.drawImage(ope.getWhiteImage(), othello.getPieceX(j), othello.getPieceY(i), this);
    			}else if(othello.hasPutPlace(i, j)){
    				//ヒントを表示
    				g.fillRect(othello.getPieceX(j) + mXYhalf, othello.getPieceY(i) + mXYhalf, hintRange, hintRange);
    				canflip++;
    			}
    		}
    	}
    	if(canflip < 1){
    		passFlg=true;
    		othello.getPlayer().changeTurn();
    	}


    	//色を変更
		g.setColor(Color.YELLOW);

		g.setFont(fnt1);

	    // フォントにアンチエイリアシングをかける
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


    	if(othello.getPlayer().getNowColor() == Othello.BLACK){
    		g2.drawString(BLACK_TURN_MESSAGE, strX ,strY);
    	}
    	else{
    		g2.drawString(WHITE_TURN_MESSAGE, strX ,strY);
    	}

    	//マウスの周りに円を表示
    	if(mouseX >= othello.getBoard().getXLeft() && mouseX <= othello.getBoard().getXRight() - 3 &&
    			mouseY >= othello.getBoard().getYUp() && mouseY <= othello.getBoard().getYDown() - 3){
    		g.drawOval(mX1, mY1, mXY2, mXY2);
    	}

    	g2.setColor(Color.WHITE);

    	//駒の数を表示
    	g2.drawString(BLACK_NUM_MESSAGE + String.valueOf(othello.getBoard().getOnBoardBlackPieceCount()), StateX, StateY1);
    	g2.drawString(WHITE_NUM_MESSAGE + String.valueOf(othello.getBoard().getOnBoardWhitePieceCount()), StateX, StateY2);

    	if(othello.isEnd()){
    		System.out.println(END_MESSAGE);
    		System.exit(0);
    	}

    	if(passFlg){
    		g.setFont(fnt2);
    		g2.drawString(PASS_MESSAGE, strX-25, 240);
    	}
   }


    /**
     * イメージファイルの読み込み
     *
     */
    private void readImage(){

    	//画像を管理するオペレータークラスの生成
    	//イメージクラスへの読み込みは全てコイツが行う
    	ope = new OthelloGraphicOperator();

        // MediaTrackerに登録
        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(ope.getBoardImage(), ope.getBoardId());
        tracker.addImage(ope.getWhiteImage(), ope.getWhiteId());
        tracker.addImage(ope.getBlackImage(), ope.getBlackId());

        // イメージ読み込み完了まで待機
        try {
            tracker.waitForID(ope.getBoardId());
            tracker.waitForID(ope.getWhiteId());
            tracker.waitForID(ope.getBlackId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * マウスのボタンを押したときに呼び出されるイベントハンドラ
     */
    public void mousePressed(MouseEvent e) {

    	int X,Y;

    	X = e.getX();
    	Y = e.getY();

    	if(!othello.putPiece(X,Y)){
        	JOptionPane.showMessageDialog(null, ERROR_MESSAGE,	ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
    	}else{
    		passFlg = false;
    	}
    	repaint();
   }


    /**
     * マウスを動かした時に呼び出されるイベントハンドラ
     *
     */
    public void mouseMoved(MouseEvent e){

    	mouseX = e.getX();
        mouseY = e.getY();


    	mX1 = getCellX(mouseX);
    	mY1 = getCellY(mouseY);


    	repaint();

    }


	/**
	 * マウスの座標から、マスの横座標を求める
	 *
	 * @param x
	 * @return 左から何マス目か(1～8)
	 */
	public int getCellX(int x){
		return (((x - othello.getBoard().getXLeft()) / othello.getBoard().getCell()) * othello.getBoard().getCell())
	                         + othello.getBoard().getXLeft() + 5;
	}
    /**
	 * マウスのたて座標から、何マス目に相当するかを返す
	 *
	 * @param y
	 * @return 上から何マス目か(1～8)
	 */
	public int getCellY(int y){
		return (((y - othello.getBoard().getYUp()) / othello.getBoard().getCell()) * othello.getBoard().getCell())
				             + othello.getBoard().getYUp() + 5;
	}



    //以下要らない子達

    public void mouseClicked(MouseEvent e)  {}
    public void mouseEntered(MouseEvent e)  {}
    public void mouseExited(MouseEvent e)   {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseDragged(MouseEvent e)  {}

}

