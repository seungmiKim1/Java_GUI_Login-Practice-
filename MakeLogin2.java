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
		
		JLabel l1 = new JLabel("LoginID : ");
		id = new JTextField(8);
		l1.setBounds(80, 60, 70, 30);
		id.setBounds(170, 60, 120, 30);
		ct.add(l1); ct.add(id);
		
		JLabel l2 = new JLabel("PASSWORD");
		Passwd = new JPasswordField(8);
		l2.setBounds(80, 100, 70, 30);
		Passwd.setBounds(170, 100, 120, 30);
		ct.add(l2); ct.add(Passwd);
		
		b1 = new JButton("로그인");
		b2 = new JButton("취소");
		b3 = new JButton("회원가입");
		b2.addActionListener(this);
		b3.addActionListener(this);
		b1.setBounds(30, 170, 80, 30);
		b2.setBounds(120, 170, 80, 30);
		b3.setBounds(210, 170, 80, 30);
		ct.add(b1);	ct.add(b2);	ct.add(b3);
	}
	
	public void actionPerformed(ActionEvent ae){
		String s = ae.getActionCommand();
		if ( s == "취소" ){
			id.setText("");
			Passwd.setText("");
		}
		else if ( s == "회원가입" ){
			NewMember my = new NewMember("회원가입");
			my.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			my.setSize(360, 300);
			my.setLocation(400, 300);
			my.setVisible(true);
		}
		else {}
	}
}
class NewMember extends JFrame implements ActionListener{
	JTextField id, name, tel_number, address; JPasswordField Passwd;
	String code[] = {"010" ,"070" ,"02" ,"031" ,"032"};
	JComboBox tel;	JButton check, b1, b2;
	NewMember(String title){
		setTitle(title);
		Container ct = getContentPane();
		ct.setLayout(new BorderLayout(0, 20));
		
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(5, 1));
		
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel l1 = new JLabel("ID             :");
		id = new JTextField(8);
		check = new JButton("ID 중복체크");
		p1.add(l1);	p1.add(id);	p1.add(check);
		
		Panel p2 = new Panel();
		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel l2 = new JLabel("PASSWORD");
		Passwd = new JPasswordField(8);
		p2.add(l2);	p2.add(Passwd);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel l3 = new JLabel("이름             :");
		name = new JTextField(8);
		p3.add(l3);	p3.add(name);
		
		JPanel p4 = new JPanel();
		p4.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel l4 = new JLabel("연락처          :");
		tel = new JComboBox(code);
		tel_number = new JTextField(10);
		p4.add(l4);	p4.add(tel);	p4.add(tel_number);
		
		JPanel p5 = new JPanel();
		p5.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel l5 = new JLabel("주소		:");
		address = new JTextField(20);
		p5.add(l5);	p5.add(address);
		top.add(p1);	top.add(p2);	top.add(p3);	top.add(p4);	top.add(p5);
		ct.add(top, BorderLayout.CENTER);
		
		JPanel bottom = new JPanel();
		b1 = new JButton("확인");
		b2 = new JButton("취소");
		b1.addActionListener(this);
		b2.addActionListener(this);
		bottom.add(b1);	bottom.add(b2);
		ct.add(bottom, BorderLayout.SOUTH);
		}
	
	public void actionPerformed(ActionEvent ae){
		String s = ae.getActionCommand();
		String t_id = "", t_passwd = "", t_name = "", t_tel = "", t_tel_number = "", t_address ="";
		if( s.equals("취소")){
			id.setText("");	Passwd.setText("");		name.setText("");
			tel_number.setText("");	address.setText("");
			tel.setSelectedItem((Object) code[0]);
		} 
		
		else if ( s.equals("ID 중복 체크")) {}
		
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
				t_id = id.getText(); 	t_passwd = Passwd.getText();
				t_name = name.getText();	t_tel = (String) tel.getSelectedItem();
				t_tel_number = tel_number.getText();	t_address = address.getText();
				
				String strSql = "INSERT INTO student_info ( id, passwd, name, tel, tel_number, address) VALUES ("+"'" + t_id + "' , '" + t_passwd + "' , '" + t_name + "' , '" + t_tel + "' , '" + t_tel_number + "' , '" + t_address + "');";
				System.out.println(strSql);
				dbSt.executeUpdate(strSql);
				
				
				System.out.println("데이터 삽입 완료");
				dbSt.close();
				con.close();
			} catch ( SQLException e) {
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
		win.setVisible(true);
		}
}
	
	