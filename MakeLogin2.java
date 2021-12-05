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
		
		JLabel l1 = new JLabel("����� �̸� : ");
		id = new JTextField(10);
		l1.setBounds(80, 60, 70, 30);
		id.setBounds(170, 60, 120, 30);
		ct.add(l1); ct.add(id);
		
		JLabel l2 = new JLabel("��й�ȣ : ");
		Passwd = new JPasswordField(10);
		l2.setBounds(80, 100, 70, 30);
		Passwd.setBounds(170, 100, 120, 30);
		ct.add(l2); ct.add(Passwd);
		
		b1 = new JButton("�α���");
		b2 = new JButton("���");
		b3 = new JButton("�̿��ϱ�");
		
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		b1.setBounds(20, 170, 80, 30);
		b2.setBounds(110, 170, 80, 30);
		b3.setBounds(200, 170, 90, 30);
		ct.add(b1);	ct.add(b2);	ct.add(b3);
	}
	public void actionPerformed(ActionEvent ae){
		String s = ae.getActionCommand();
		if ( s == "���" ){
			id.setText("");
			Passwd.setText("");
		}
		else if ( s == "�̿��ϱ�" ){
			NewMember my = new NewMember("�̿��ϱ�");
			my.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			my.setSize(360, 300);
			my.setLocation(400, 300);
			my.show();
		}
		else {}
	}
}
class NewMember extends JFrame implements ActionListener{ // �̿��ϱ� ��ư Ŭ����
	JTextField userName; JPasswordField Passwd;
	JComboBox tel;	JButton b1, b2;
	NewMember(String title){
		setTitle("MyDiary �̿��ϱ�");
		Container ct = getContentPane();
		ct.setLayout(new BorderLayout(0, 20));
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(5, 1));
		
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel l1 = new JLabel("����� �̸� : ");
		userName = new JTextField(10);
		p1.add(l1);	p1.add(userName);
		
		Panel p2 = new Panel();
		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel l2 = new JLabel("��й�ȣ : ");
		Passwd = new JPasswordField(10);
		p2.add(l2);	p2.add(Passwd);
		
		
		JPanel bottom = new JPanel();
		b1 = new JButton("�Է� �Ϸ�");
		b1.addActionListener(this);
		bottom.add(b1);
		ct.add(bottom, BorderLayout.SOUTH);

		top.add(p1); top.add(p2);
		ct.add(top, BorderLayout.CENTER);
		}
	public void actionPerformed(ActionEvent ae){
		String s = ae.getActionCommand();
		String t_userName = "", t_passwd = "";
		if( s.equals("���")){
			userName.setText("");	Passwd.setText("");	
		} else if ( s.equals("ID �ߺ� üũ")) {}

		
		else{
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.err.println("JDBC-ODBC ����̹��� ���������� �ε���");
			}catch(ClassNotFoundException e){
				System.out.println("����̹� �ε忡 �����߽��ϴ�.");
			}
			try {
				Connection con = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/student?serverTimezone=UTC","root","a12345");
				System.out.println("DB ���� �Ϸ�");
				Statement dbSt = con.createStatement();
				System.out.println("JDBC ����̹��� ���������� ����Ǿ����ϴ�.");
				t_userName= userName.getText(); 	t_passwd = Passwd.getText();
				

				String strSql = "INSERT INTO user_info ( userName, passwd ) VALUES (" + " ' " + t_userName + " ' , ' " + t_passwd+ " ' ); " ;
				dbSt.executeUpdate(strSql);
				System.out.println("������ ���� �Ϸ�");
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
		Login win = new Login("�α���");
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setSize(320, 300);
		win.setLocation(100, 200);
		win.show();
		}
}