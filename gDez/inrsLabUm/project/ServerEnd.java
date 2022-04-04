package gDez.inrsLabUm.project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class ServerEnd {
	private JFrame frmServerChat;
	private static JTextArea textArea;
	static ServerSocket server;
	static Socket con;
	private JScrollPane scrollPane;
	private static JLabel lblNewLabel_2;
	private static JLabel lblNewLabel;

	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerEnd window = new ServerEnd();
					window.frmServerChat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		serverConnection();

	}

	private static void serverConnection() throws IOException {
		server = new ServerSocket(8080);

		con = server.accept();
		lblNewLabel_2.setText("Cliente encontrado!");
		lblNewLabel_2.setForeground(new Color(0, 0, 128));
		while (true) {
			try {

				DataInputStream input = new DataInputStream(con.getInputStream());
				String string = input.readUTF();
				textArea.setText(textArea.getText() + "\n " + "Cliente: " + string);
			} catch (Exception ev) {
				textArea.setText(textArea.getText() + " \n" + "Problemas de rede ");

				try {
					Thread.sleep(2000);
					System.exit(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public ServerEnd() throws IOException {
		initialize();

	}

	private void initialize() throws IOException {
		frmServerChat = new JFrame();
		frmServerChat.getContentPane().setBackground(UIManager.getColor("MenuBar.highlight"));
		frmServerChat.setForeground(Color.WHITE);
		frmServerChat.setBackground(Color.WHITE);
		frmServerChat.setTitle("G10 LAB 01 2022 INRS: Servidor");
		frmServerChat.getContentPane().setForeground(Color.WHITE);
		frmServerChat.setBounds(100, 100, 605, 403);
		frmServerChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServerChat.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 134, 557, 157);
		frmServerChat.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		textArea.setForeground(Color.ORANGE);
		textArea.setBackground(Color.DARK_GRAY);

		lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));

		lblNewLabel.setBounds(154, 13, 242, 33);
		frmServerChat.getContentPane().add(lblNewLabel);
		if (server.isClosed()) {
			lblNewLabel.setText("O servidor está fechado!");
		} else {
			lblNewLabel.setText("Aguardando conexão...");
			lblNewLabel.setForeground(Color.GREEN);
		}
		JLabel lblNewLabel_1 = new JLabel("Status");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 22));
		lblNewLabel_1.setBounds(37, 12, 95, 30);
		frmServerChat.getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblNewLabel_2.setBounds(22, 303, 128, 30);
		frmServerChat.getContentPane().add(lblNewLabel_2);

	}
}
