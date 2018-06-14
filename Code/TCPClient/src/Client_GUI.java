import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class Client_GUI {

	private JFrame frmQuizGame;
	private JTextField textField;
    private String message;
    private Image image,image2,imButton ;
	static String data;
	static String ipAddress;
	static int serverPort=7596;
	Socket s =null;
	DataOutputStream out;
	DataInputStream in;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client_GUI window = new Client_GUI();
					window.frmQuizGame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmQuizGame = new JFrame();
		frmQuizGame.setTitle("Quiz Game");
		frmQuizGame.setBounds(100, 100, 555, 300);
		frmQuizGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQuizGame.getContentPane().setLayout(null);
		image = new ImageIcon(this.getClass().getResource("/client.jpg")).getImage();
		image2 = new ImageIcon(this.getClass().getResource("/next.png")).getImage();
		imButton = new ImageIcon(this.getClass().getResource("/Start.png")).getImage();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Απαντήσεις", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(81, 204, 389, 46);
		frmQuizGame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btn_A = new JButton("A");
		btn_A.setBounds(10, 16, 61, 23);
		panel.add(btn_A);
		
		JButton btn_B = new JButton("B");
		btn_B.setBounds(90, 16, 61, 23);
		panel.add(btn_B);
		
		JButton btn_C = new JButton("C");
		btn_C.setBounds(161, 16, 61, 23);
		panel.add(btn_C);
		
		JButton btn_Next = new JButton("Next Question");
		btn_Next.setIcon(new ImageIcon(image2));
		btn_Next.setBounds(232, 16, 147, 23);
		panel.add(btn_Next);
		btn_Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				message="n";
				try {
					out = new DataOutputStream(s.getOutputStream());
                    in = new DataInputStream(s.getInputStream());
		            out.writeUTF(message);
		            out.flush();
				JTextArea textArea = new JTextArea();
				textArea.setBounds(20, 92, 509, 101);
				frmQuizGame.getContentPane().add(textArea);
				textArea.setText(in.readUTF());

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message="C";
				try {
					out = new DataOutputStream(s.getOutputStream());
                    in = new DataInputStream(s.getInputStream());
		            out.writeUTF(message);
		            out.flush();
				    data = in.readUTF();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            
				JOptionPane.showMessageDialog(null,data);
			}
		});
		btn_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message="B";
				try {
					out = new DataOutputStream(s.getOutputStream());
                    in = new DataInputStream(s.getInputStream());
		            out.writeUTF(message);
		            out.flush();
				    data = in.readUTF();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            
				JOptionPane.showMessageDialog(null,data);
			}
		});
		btn_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message="A";
				try {
					out = new DataOutputStream(s.getOutputStream());
                    in = new DataInputStream(s.getInputStream());
		            out.writeUTF(message);
		            out.flush();
				    data = in.readUTF();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            
				JOptionPane.showMessageDialog(null,data);
			}
		});
		
		JButton btnNewButton_3 = new JButton("START");
		btnNewButton_3.setIcon(new ImageIcon(imButton));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					InetAddress ipAddr = InetAddress.getLocalHost();
					s = new Socket(ipAddr.getHostAddress(), serverPort);
					DataInputStream in = new DataInputStream(s.getInputStream());
					DataOutputStream out = new DataOutputStream(s.getOutputStream());
					message="start";
					out.writeUTF(message);
					JTextArea textArea = new JTextArea();
					textArea.setBounds(20, 92, 509, 101);
					frmQuizGame.getContentPane().add(textArea);
					textArea.setText(in.readUTF());
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(20, 16, 114, 51);
		frmQuizGame.getContentPane().add(btnNewButton_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Ερωτήσεις", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(14, 76, 521, 124);
		frmQuizGame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(6, 16, 509, 101);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(138, 0, 397, 91);
		frmQuizGame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lbl_Image = new JLabel("");
		lbl_Image.setBounds(6, 16, 385, 68);
		panel_2.add(lbl_Image);
		lbl_Image.setIcon(new ImageIcon(image));
		

		
		
	}
}
