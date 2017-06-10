import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class PanelImage implements ActionListener {
	
	ClassMatrix matrix = new ClassMatrix();
	private final int widthPI = 600;			// chiều rộng của PanelImage
	private final int heightPI = 600;			// chiều cao của PanelImage
	private final int leverF = 5;				// mức độ chơi tối đa là 5 ( ma trận 12*12)
	private int lever;						// mức độ chơi hiện tại
	private int sizematrix=20;  // khởi tạo mặc đinh nó như dày còn size thực tế thì tùy theo cái lever
	private int numberImage;
	private JPanel PanelImage = new JPanel();
	private JButton[][] btnImage;
	private Point p1 = new Point(-1,-1);
	private Point p2 = new Point(-1,-1);
	private GameFrame frame;
	
	public PanelImage(GameFrame fr, int lv){
		frame = fr;
		lever = lv;
		matrix.newMatrix(lever * 2 + 2);
		sizematrix = matrix.getSizeMatrix();
		numberImage = sizematrix*sizematrix;
	}
	
	// thủ thiết lập các thông số cho panelImage
	public JPanel panelImage(){
		PanelImage.setSize(widthPI, heightPI);
		PanelImage.setBackground(Color.black);
		PanelImage.setLayout(null);
		btnArrImage();
		setImageBT();
		return PanelImage;
	}
	
	// thủ tục thiết lập các thông số cho mảng button
	private void btnArrImage(){
		btnImage = new JButton[sizematrix+1][sizematrix+1];
		int width = (widthPI-50)/sizematrix;
		int height = (heightPI-50)/sizematrix;
		for(int i=1; i<=sizematrix; i++)
		{
			for(int j=1; j<=sizematrix; j++)
			{
				btnImage[i][j] = new JButton();
				btnImage[i][j].setActionCommand(i + "," +j);
				btnImage[i][j].setSize(width, height);
				btnImage[i][j].setBounds((i-1)*width + 22, (j-1)*height + 22, width, height);
				btnImage[i][j].addActionListener(this);
				PanelImage.add(btnImage[i][j]);
			}
		}
	}
	
	// thủ tục xét icon cho các button hình vật
	private void setImageBT(){
		for(int i=1; i<=sizematrix; i++)
			for(int j=1; j<=sizematrix; j++)
			if(matrix.getMatrix(i, j) != 0){
				Icon icon = getIcon(matrix.getMatrix(i, j));
				btnImage[i][j].setMargin(new Insets(0,0,0,0));
				btnImage[i][j].setIcon(icon);
				btnImage[i][j].setBorder(new LineBorder(Color.black));
			}
			else
				btnImage[i][j].setVisible(false);
	}
	
	private Icon getIcon(int index) {
		int width = (widthPI-50)/sizematrix;
		int height = (heightPI-50)/sizematrix;
		Image image = new ImageIcon("icon/" + ChooseIcon.iconName + "/icon" + index + ".png").getImage();
		Icon icon = new ImageIcon(image.getScaledInstance(width, height,Image.SCALE_SMOOTH));
		return icon;
	}
	
	// hàm trả về 2 nút có thể ăn được
	public void help(){
		Point[] p = matrix.seekPoint();
		btnImage[p[0].getY()][p[0].getX()].setBorder(new LineBorder(Color.BLUE,4));
		btnImage[p[1].getY()][p[1].getX()].setBorder(new LineBorder(Color.BLUE,4));
	}
		
	// thủ tục ẩn panel
	public void obscure(){
		PanelImage.setVisible(false);
	}
		
	// thủ tục tạo hoán đổi các hình trong panel
	public void covert(){
		matrix.convertMatrix();
		setImageBT();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str = e.getActionCommand();
		int indexDot = str.lastIndexOf(",");
		int y = Integer.parseInt(str.substring(0, indexDot));
		int x = Integer.parseInt(str.substring(indexDot+1, str.length()));
		if(p1.getX() == -1) // point1 chưa có
		{
			p1.setPoint(x, y);
			btnImage[y][x].setBorder(new LineBorder(Color.red,3));
		}
		else // có point rồi gán vào point2
		{
			p2.setPoint(x, y);
			if(matrix.check(p1, p2) == true || matrix.check(p2, p1) == true)
			{
				numberImage -= 2;									// giảm số lượng đi 2
				frame.updateScore(2);
				btnImage[p1.getY()][p1.getX()].setVisible(false);	// ẩn hình tại vị trí p1
				btnImage[p2.getY()][p2.getX()].setVisible(false);	// ẩn hình tại vị trí p2
				matrix.updateMatrix(p1, p2);						// gọi thủ tục xóa 2 phần tử  tại p1 p2
				if(numberImage == 0) // hết hình
				{
					lever++;
					frame.newMap(lever);
					frame.updateScore(frame.time/(leverF-lever)); //lv càng cao, time càng ít điểm càng cao
				}
				else if(matrix.checkRoadMatrix() == false) //không còn đường đi
				{
					frame.updateConvert(1);
					covert();
				}
			}
			else
			{
				btnImage[p1.getY()][p1.getX()].setBorder(new LineBorder(Color.black));
			}
			p1.setPoint(-1, -1);

		}
	}
}
