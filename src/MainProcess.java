import java.awt.Rectangle;
import java.awt.Container;
import javax.swing.JFrame;

import othello.notusing.MainPanel;
import othello.OthelloPanel;

public class MainProcess extends JFrame {

	/**
	 * コンストラクタ
	 */
    public MainProcess() {
        // タイトルを設定
        this.setTitle("Othello");

        //サイズ変更禁止
        this.setResizable(false);

        // メインパネルを作成してフレームに追加
//        MainPanel panel = new MainPanel();
        OthelloPanel panel = new OthelloPanel();

        Container contentPane = getContentPane();
        //背景を白に設定
        //panel.setBackground(Color.WHITE);
        contentPane.add(panel);

        // パネルサイズに合わせてフレームサイズを自動設定
        pack();
    }

    /**
     * スクリーンのセンタリング処理を行います
     */
    public void centering(){
        Rectangle screen = this.getGraphicsConfiguration().getBounds();
        this.setLocation(screen.x + screen.width/2  - this.getSize().width/2,
                          screen.y + screen.height/2 - this.getSize().height/2);
    }

    /**
     * メイン処理
     * @param args
     */
    public static void main(String[] args) {
        MainProcess frame = new MainProcess();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // センタリング
        frame.centering();

        frame.setVisible(true);
    }
}
