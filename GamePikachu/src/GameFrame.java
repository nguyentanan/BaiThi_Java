import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int widthF = 800;			// chiều rộng của frame
	private final int heightF = 638;			// chiều cao của frame
	private final int scoreF = 0;			// điểm mặc định là 0
	private final int covertF = 5;
	private final int helpF = 5;
	private final int leverminF = 1;		// mức độ chơi mặc định ban đầu
	private final int levermaxF = 5;
	
	public final int timeF = 300;			// thời gian chơi mặc định
	
	private int lever = leverminF;
	private int score = scoreF;		// điểm
	private int covert = covertF;
	private int help = helpF;
	
	public int time = timeF;		// thời gian chơi
	
	private JFrame frame = new JFrame();

	private JLabel lbLever = new JLabel();
	private JLabel lbScore = new JLabel();			// label hiển thị điểm
	
	private JPanel panelTime_ = new JPanel();
	private JPanel panelTime = new JPanel();
	private JButton btnNewgame;						// nút ấn chơi game mới
	private JButton btnHelp = new JButton();		// nút ấn trợ giúp
	private JButton btnCovert = new JButton();
	
	private PanelImage panelImage;
	
	public GameFrame(){
		frame();	
		panelImage = new PanelImage(this,lever);
		frame.add(panelImage.panelImage());
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE); // tắt frame khi ấn vào nút X
	}
	
	private void frame(){
		frame.setSize(widthF, heightF);
		frame.setTitle("Trò chơi tìm cặp");
		frame.setLayout(null);
		frame.setLocation(200, 50);
		frame.setResizable(false);
		frame.setBackground(Color.BLUE);

		frame.add(CreatePanel());
	}
	
	public void Visible(){
		frame.setVisible(true);
	}
	
	public void Dispose(){
		frame.dispose();
	}
	
	private JPanel CreatePanel(){
		JPanel mainpn = new JPanel();
		
		mainpn.setSize(200, 650);
		mainpn.setBackground(Color.white);
		mainpn.setBounds(610, 0, 200, 650);
		mainpn.setLayout(null);
		
		// thiết lập thông số hiển thị mức độ hiện tại của người chơi
		lbLever.setText("Mức độ: " + lever);
		lbLever.setBounds(10, 140, 100, 50);
		mainpn.add(lbLever);
		
		// thiết lập thông số cho label hiển thị điểm của người chơi
		lbScore.setText("Điểm: " + score);
		lbScore.setBounds(10, 170, 100, 50);
		mainpn.add(lbScore);
		
		// thiết lập thông số cho thanh thời gian
		JLabel lbtime = new JLabel();
		lbtime.setText("Thời gian");
		lbtime.setBounds(10, 215, 100, 20);
		mainpn.add(lbtime);
		panelTime.setBackground(Color.black);
		panelTime.setLayout(null);
		panelTime.setBounds(10, 240, 150, 20);
		mainpn.add(panelTime);
		panelTime_.setBackground(Color.BLUE);
		panelTime_.setBounds(0, 0, 150, 20);
		panelTime.add(panelTime_);
		
		// thiết lập thông số cho nút trợ giúp
		btnNewgame = new JButton();
		btnNewgame.setText("Chơi mới");
		btnNewgame.setMargin(new Insets(0,0,0,0));
		btnNewgame.setBounds(10, 290, 150, 30);
		btnNewgame.addActionListener(this);
		mainpn.add(btnNewgame);
		
		// thiết lập thông số cho nút hoán đổi
		btnCovert.setText("Hoán đổi (" + covert + ")");
		btnCovert.setMargin(new Insets(0,0,0,0));
		btnCovert.setBounds(10, 340, 150, 30);
		btnCovert.addActionListener(this);
		mainpn.add(btnCovert);
		
		// thiết lập thông số cho nút trợ giúp
		btnHelp.setText("Trợ giúp ("+ help + ")");
		btnHelp.setMargin(new Insets(0,0,0,0));
		btnHelp.setBounds(10, 390, 150, 30);
		btnHelp.addActionListener(this);
		mainpn.add(btnHelp);

		return mainpn;
	}
	
	// cập nhật điểm số
	public void updateScore(int del){
		score += del;
		lbScore.setText("Điểm: "+String.valueOf(score));
	}
	
	// thay đổi hình thời gian
	public void reducedTime(){
		if(time > -1)
			time -= 1;
		panelTime_.setBounds(0 - (timeF-time)/(timeF/150), 0, 150, 20);
		if (time >= timeF * 4 / 6) panelTime_.setBackground(Color.BLUE);
		else if(time < timeF * 4 / 6 && time > timeF * 1 / 6) panelTime_.setBackground(Color.ORANGE);
		else if (time <= timeF *1 / 6) panelTime_.setBackground(Color.RED);
	}
	
	// cập nhật lần đổi
	public void updateConvert(int cv)
	{
		if(covert == 0)
		{
			time = 0;
			NotifyFrame fr = new NotifyFrame(this, 1);
			fr.Visible();
		}
		else
		{
			covert -= cv;
			btnCovert.setText("Hoán đổi (" + covert + ")");
		}
	}
	
	public void newGame(){

		panelImage.obscure(); // gọi thủ tục ẩn cái này đi (thủ tục được viết bên myPanel)
		lever = leverminF;
		panelImage = new PanelImage(this,lever);
		frame.add(panelImage.panelImage());
		time = timeF;		// gán lại thời gian mặc định
		score = scoreF;		// gán điểm lại bằng điểm mặc định, mặc định là 0
		lbScore.setText("Điểm: 0");
		lbLever.setText("Mức độ: " + lever);
		covert = covertF;
		updateConvert(0);
		help = helpF;
		btnHelp.setText("Trợ giúp (" + help + ")");
		
		frame.dispose();
		ChooseIcon ci = new ChooseIcon();
		ci.Visible();
	}
	
	// thủ tục qua màn
	public void newMap(int lv){
		panelImage.obscure(); // ẩn panel Image
		if(lv <= levermaxF)
		{
			lever = lv;
			time = timeF;
			panelImage = new PanelImage(this,lever);
			lbLever.setText("Mức độ: " + lever);
			frame.add(panelImage.panelImage());
		}
		else 
		{
			time = 0;
			NotifyFrame fr = new NotifyFrame(this, 0);
			fr.Visible();
		}
		
		
	}
	
	// thủ tục hoán đổi các vị trí con vật trong màn chơi (khác với new nó chỉ hoán đổi chứ không làm mới các con vật)
	public void convert(){
		if(covert >0 ){
			panelImage.covert();
			updateConvert(1);
		}
	}
	
	// thủ tục tìm kiếm 2 nút ăn
	public void help(){
		if(help > 0){
			help--;
			btnHelp.setText("Trợ giúp ("+ help + ")");
			panelImage.help();
		}
	}

	public void runFrame() {
		RunFrame rf = new RunFrame(this);
		rf.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnNewgame)
			newGame();
		else if (e.getSource() == btnCovert)
			convert();
		else if(e.getSource() == btnHelp)
			help();
	}
}
