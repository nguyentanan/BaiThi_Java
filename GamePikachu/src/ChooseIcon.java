import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ChooseIcon extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int widthF = 628;			// chiều rộng của frame
	private final int heightF = 300;			// chiều cao của frame
	private final int widthP = 600;			// chiều rộng của panel
	private final int heightP = 125;			// chiều cao của panel
	private final int munberIcon = 3;
	private JPanel panel;
	private JButton[] icon;
	private JLabel lbten = new JLabel();
	public static String iconName = "image1";
	
	public ChooseIcon(){
		frame();
	}
	
	private void frame(){
		this.setSize(widthF,heightF);
		this.setLayout(null);
		this.setTitle("Chọn icon hiển thị");
		this.setBackground(Color.gray);
		this.setLocation(380, 230);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);	

		
		lbten.setText("Chọn icon hiển thị");
		lbten.setBounds(10, 10, 150, 20);
		this.add(lbten);
		
		setPanel();
		setIcon();
	}
	
	public void Visible(){
		this.setVisible(true);
	}
	
	private void setPanel(){
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setSize(600,300);
		panel.setBounds(10, 50, widthP, heightP);
		panel.setLayout(null);
		this.add(panel);
	}
	
	private void setIcon(){
		icon = new JButton[3];
		int width = 5;
		int height = heightP - 10;
		for(int i=0; i<munberIcon; i++){
			icon[i] = new JButton();
			icon[i].setActionCommand(i + "");
			icon[i].setBounds(width, 5, (widthP-10)/3, height);
			icon[i].setMargin(new Insets(0,0,0,0));
			icon[i].setIcon(getIcon(i));
			icon[i].addActionListener(this);
			panel.add(icon[i]);
			width += (widthP-10)/3;

		}

	}
	
	// hàm trả về icon cho button
	private Icon getIcon(int index) {
		int width = (widthP-10)/3;
		int height = heightP - 10;
		Image image = new ImageIcon("Bicon/Bicon" + index + ".png").getImage();
		Icon icon = new ImageIcon(image.getScaledInstance(width, height,Image.SCALE_SMOOTH));
		return icon;
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == icon[0])
			iconName = "image1";
		else if (e.getSource() == icon[1]) 
			iconName = "image2";
		else if (e.getSource() == icon[2]) 
			iconName = "image3";
		this.dispose();
		
		GameFrame gfr = new GameFrame();
		gfr.Visible();
		gfr.runFrame();

	}
}
