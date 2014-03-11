package othello.notusing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MainPanel extends JPanel implements Runnable,MouseListener,MouseMotionListener{
    // パネルサイズ
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;

    private final int BoardID = 0;
    private final int WhiteID = 1;
    private final int BlackID = 2;
    
    private final int fontSize = 20;
    private final int strX = WIDTH - (fontSize * 8);	//480;
    private final int strY = 50;
    private final int StateX = 500;
    private final int StateY1 = 420;
    private final int StateY2 = StateY1 + 30;
    
    private int blackCnt;
    private int whiteCnt;
    
    private String result;
    
    //スレッド停止用
    private boolean running = true;

    
    //オセロクラスを宣言
    private Othello otl; 
    
    
    // 画面に表示する文字列
    private String str;
    
    //座標
    private int x = 1;
    private int y = 1;
    
    //マウスの周りに表示する円
    private int mX1, mY1, mX2, mY2;
    
    private int mouseX, mouseY;
    
    //イメージを読み込む
    private Image 	boardImg,      //盤の画像
    			  	whiteImg,      //白駒の画像
    			  	blackImg;      //黒駒の画像
    
    //フォント変更用
    private Font fnt1;
    
    //スレッドを使用
    private Thread td;
    
    //コンストラクタ(主に初期化式を記述)
    public MainPanel() {
        // パネルの推奨サイズを設定、pack()するときに必要
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // 変数の初期化
        str = "Hello World";
        
        //オセロの実体化
        otl = new Othello();
        
        //画像をロード
        readImage();

        //スレッドを使用
        useThread();
        
        //Font使用
        fnt1 = new Font("ＭＳ ゴシック", Font.PLAIN, fontSize);      
        
        // MouseListenerを登録
        addMouseListener(this);
        addMouseMotionListener(this);

    }

    //描画メソッド
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //背景を表示
        g.drawImage(boardImg, 0,0,this);
        
        // Hello World
        g.drawString(str, x, y);
        
        //駒を表示
    	for(int i=1;i<9;i++){
    		for(int j=1; j<9; j++){
    			if(otl.getState(i,j) == Othello.BLACK){
    				g.drawImage(blackImg, otl.getPieceX(j), otl.getPieceY(i), this);
    			}else if(otl.getState(i,j) == Othello.WHITE){
    				g.drawImage(whiteImg, otl.getPieceX(j), otl.getPieceY(i), this);
    			}else{
    				;
    			}
    		}
    	}

    	//色を変更
		g.setColor(Color.YELLOW);
		
		g.setFont(fnt1);

	    // さらにアンチエイリアシングをかける
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
	            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

   	
    	if(otl.getTurn() == Othello.BLACK_TURN){
    		g2.drawString("黒のターンです",strX,strY);
    	}
    	else{
    		g2.drawString("白のターンです",strX,strY);
    	}

    	//マウスの周りに円を表示
    	if(mouseX >= otl.getX_L() && mouseX <= otl.getX_R() - 3 &&
    			mouseY >= otl.getY_U() && mouseY <= otl.getY_D() - 3){
    		g.drawOval(mX1, mY1, mX2, mY2);
    	}
    	
    	g2.setColor(Color.WHITE);    	
    	
    	//駒の数をそれぞれ格納
    	blackCnt = otl.CountPiece(Othello.BLACK);
    	whiteCnt = otl.CountPiece(Othello.WHITE);
    	
    	//駒の数を表示
    	g2.drawString("黒の数：" + String.valueOf(blackCnt), StateX, StateY1);
    	g2.drawString("白の数：" + String.valueOf(whiteCnt), StateX, StateY2);
    	
    	if(blackCnt + whiteCnt >= 64){
    		result();
    	}
    	

   }
    
    private void result(){
    		
    		stopRunning();
    		
    		System.exit(0);
    		
//    		result = "黒：" + String.valueOf(blackCnt) + "  " + "白：" + String.valueOf(whiteCnt) + "\n";
//
//    		JOptionPane.showMessageDialog(null, "そこには置けません！",	"Alert!", JOptionPane.ERROR_MESSAGE);
    		
//    		if(blackCnt>whiteCnt){
//    			JOptionPane.showMessageDialog(null, result + "黒の勝ちです", "Result", JOptionPane.INFORMATION_MESSAGE);
//    		}
//    		else if(blackCnt<whiteCnt){
//    			JOptionPane.showMessageDialog(null, result + "白の勝ちです", "Result", JOptionPane.INFORMATION_MESSAGE);
//    		}
//    		else{
//    			JOptionPane.showMessageDialog(null, result + "引き分けです", "Result", JOptionPane.INFORMATION_MESSAGE);
//    		}
//    		repaint();
    }
    
    
    private void stopRunning(){
    	running = false;
    }
    
    //スレッド処理(再描画用に使用)
    public void run(){
    	
    	while(running){
    		   	
	        //再描画
	        repaint();
	        
	        // 20ミリ秒だけ休止
	        try {
	            Thread.sleep(20);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
    	}

    }
    
    private void readImage(){
        // 画像を読み込む
        boardImg = Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("../imagefile/board.png"));
        
        whiteImg = Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("../imagefile/white.png"));
        
        blackImg = Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("../imagefile/black.png"));
        
        

        // MediaTrackerに登録
        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(boardImg, BoardID);
        tracker.addImage(whiteImg, WhiteID);
        tracker.addImage(blackImg, BlackID);
        
        
        // イメージ読み込み完了まで待機
        try {
            tracker.waitForID(BoardID);
            tracker.waitForID(WhiteID);
            tracker.waitForID(BlackID);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void useThread(){
        // スレッドを起動
        td = new Thread(this);
        td.start();

    }
  
    public void mousePressed(MouseEvent e) {
    	
    	int X,Y;
    	
    	X = e.getX();
    	Y = e.getY();
    	
    	if(!otl.putPiece(X,Y)){
        	JOptionPane.showMessageDialog(null, "そこには置けません！",	"不正な配置", JOptionPane.ERROR_MESSAGE);
    	}
    	
//    	JOptionPane.showMessageDialog(null, "X：" + mX2 + "\nY：" + mY2,
//    									"Info", JOptionPane.INFORMATION_MESSAGE);
    	
   }
    

    public void mouseMoved(MouseEvent e){
       
    	mouseX = e.getX();
        mouseY = e.getY();

    	
    	mX1 = otl.getMouseX(mouseX);
    	mY1 = otl.getMouseY(mouseY);

    	mX2 = mY2 =otl.getMouseCircle();
        
    }
    
    
    public void mouseClicked(MouseEvent e){
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseDragged(MouseEvent e){
    }



}

