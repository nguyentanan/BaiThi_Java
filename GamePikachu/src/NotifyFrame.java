import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class NotifyFrame implements ActionListener {
	
	private JFrame notifyF = new JFrame();
	private JButton btnNewplay = new JButton();
	private JButton btnExit = new JButton();
	private JLabel lbThongbao = new JLabel();
	private GameFrame gameframe;
	private int widthF = 300;
	private int heightF = 200;
	
	public NotifyFrame(GameFrame gameFrame, int type)
	{
		gameframe = gameFrame;
		if (type == 1) FrameLost();
		else FrameWin();
		notifyF.setDefaultCloseOperation(0);
	}
	
	public void Visible()
	{
		notifyF.setVisible(true);
	}
	
	private void FrameLost()
	{
		notifyF.setSize(widthF, heightF);
		notifyF.setTitle("Thông báo");
		notifyF.setResizable(false);
		notifyF.setLocation(500, 300);
		notifyF.setLayout(null);
		
		/// label thông báo
		lbThongbao.setText("Bạn đã thua! Bạn có muốn chơi lại?");
		lbThongbao.setBounds(40, 30, 300, 50);
		notifyF.add(lbThongbao);
				
		/// button đồng ý
		btnNewplay.setText("Chơi mới");
		btnNewplay.setBounds(20, 100, 100, 30);
		btnNewplay.setMargin(new Insets(0,0,0,0));
		btnNewplay.addActionListener(this);
		notifyF.add(btnNewplay);
		
		/// button thoát
		btnExit.setText("Thoát");
		btnExit.setBounds(180, 100, 100, 30);
		btnExit.setMargin(new Insets(0,0,0,0));
		btnExit.addActionListener(this);
		notifyF.add(btnExit);
	}
	
	private void FrameWin()
	{
		notifyF.setSize(widthF, heightF);
		notifyF.setTitle("Thông báo");
		notifyF.setResizable(false);
		notifyF.setLocation(500, 300);
		notifyF.setLayout(null);
		
		/// label thông báo
		lbThongbao.setText("Đã hết màn! Bạn có muốn chơi lại?");
		lbThongbao.setBounds(40, 30, 300, 50);
		notifyF.add(lbThongbao);
				
		/// button đồng ý
		btnNewplay.setText("Chơi mới");
		btnNewplay.setBounds(20, 100, 100, 30);
		btnNewplay.setMargin(new Insets(0,0,0,0));
		btnNewplay.addActionListener(this);
		notifyF.add(btnNewplay);
		
		/// button thoát
		btnExit.setText("Thoát");
		btnExit.setBounds(180, 100, 100, 30);
		btnExit.setMargin(new Insets(0,0,0,0));
		btnExit.addActionListener(this);
		notifyF.add(btnExit);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == btnNewplay){
			notifyF.dispose();
			gameframe.newGame();
		}
		else if(arg0.getSource() == btnExit){
				System.exit(0);
			}
	}
}
