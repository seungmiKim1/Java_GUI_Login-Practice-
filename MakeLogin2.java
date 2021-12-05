import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

class Login extends JFrame implements ActionListener{
	JTextField id; JPasswordField Passwd;
	JButton b1, b2, b3;
	Login(String title){
		setTitle(title);
		Container ct = getContentPane();
		ct.setLayout(null);
		
		JLabel l1 = new JLabel("사용자 이름 : ");
		id = new JTextField(10);
		l1.setBounds(80, 60, 70, 30);
		id.setBounds(170, 60, 120, 30);
		ct.add(l1); ct.add(id);
		
		JLabel l2 = new JLabel("비밀번호 : ");
		Passwd = new JPasswordField(10);
		l2.setBounds(80, 100, 70, 30);
		Passwd.setBounds(170, 100, 120, 30);
		ct.add(l2); ct.add(Passwd);
		
		b1 = new JButton("로그인");
		b2 = new JButton("취소");
		b3 = new JButton("이용하기");
		
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		b1.setBounds(20, 170, 80, 30);
		b2.setBounds(110, 170, 80, 30);
		b3.setBounds(200, 170, 90, 30);
		ct.add(b1);	ct.add(b2);	ct.add(b3);
	}
	public void actionPerformed(ActionEvent ae){
		String s = ae.getActionCommand();
		if ( s == "취소" ){
			id.setText("");
			Passwd.setText("");
		}
		else if ( s == "이용하기" ){
			NewMember my = new NewMember("이용하기");
			my.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			my.setSize(360, 300);
			my.setLocation(400, 300);
			my.show();
		}
		else {}
	}
}
class NewMember extends JFrame implements ActionListener{ // 이용하기 버튼 클래스
	JTextField userName; JPasswordField Passwd;
	JComboBox tel;	JButton b1, b2;
	NewMember(String title){
		setTitle("MyDiary 이용하기");
		Container ct = getContentPane();
		ct.setLayout(new BorderLayout(0, 20));
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(5, 1));
		
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel l1 = new JLabel("사용자 이름 : ");
		userName = new JTextField(10);
		p1.add(l1);	p1.add(userName);
		
		Panel p2 = new Panel();
		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel l2 = new JLabel("비밀번호 : ");
		Passwd = new JPasswordField(10);
		p2.add(l2);	p2.add(Passwd);
		
		
		JPanel bottom = new JPanel();
		b1 = new JButton("입력 완료");
		b1.addActionListener(this);
		bottom.add(b1);
		ct.add(bottom, BorderLayout.SOUTH);

		top.add(p1); top.add(p2);
		ct.add(top, BorderLayout.CENTER);
		}
	public void actionPerformed(ActionEvent ae){
		String s = ae.getActionCommand();
		String t_userName = "", t_passwd = "";
		if( s.equals("취소")){
			userName.setText("");	Passwd.setText("");	
		} else if ( s.equals("ID 중복 체크")) {}

		
		else{
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.err.println("JDBC-ODBC 드라이버를 정상적으로 로드함");
			}catch(ClassNotFoundException e){
				System.out.println("드라이버 로드에 실패했습니다.");
			}
			try {
				Connection con = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/student?serverTimezone=UTC","root","a12345");
				System.out.println("DB 연결 완료");
				Statement dbSt = con.createStatement();
				System.out.println("JDBC 드라이버가 정상적으로 연결되었습니다.");
				t_userName= userName.getText(); 	t_passwd = Passwd.getText();
				

				String strSql = "INSERT INTO user_info ( userName, passwd ) VALUES (" + " ' " + t_userName + " ' , ' " + t_passwd+ " ' ); " ;
				dbSt.executeUpdate(strSql);
				System.out.println("데이터 삽입 완료");
				dbSt.close();
				con.close();
			}catch ( SQLException e) {
				System.out.println( "SQLException : " + e.getMessage());
			}
		}
	}
}
class MakeLogin2 {
	public static void main(String args[]){
		Login win = new Login("로그인");
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setSize(320, 300);
		win.setLocation(100, 200);
		win.show();
		}
}