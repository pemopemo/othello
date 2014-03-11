package othello.graph;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * 画像の情報を持つクラス
 * 
 * @author shin
 *
 */
public class GraphSet {
	
	private Image img;
	private int    id;

	public GraphSet(String graphName, int id){
		img = Toolkit.getDefaultToolkit().getImage(  getClass().getResource(graphName)  );
		this.id = id;
	}
	
	public Image getImg(){
		return img;
	}
	public void setImg(Image image){
		this.img = image;
	}
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
}
