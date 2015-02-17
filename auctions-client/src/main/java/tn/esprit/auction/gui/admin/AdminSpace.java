package tn.esprit.auction.gui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import tn.esprit.auction.delegate.GestionUserDelegate;
import tn.esprit.auction.domain.Client;

import tn.esprit.auction.gui.client.SubscribingSpace;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminSpace extends JFrame {

	private JPanel contentPane;
	private JTable tableClients;
	JLabel labelAdresse;
	JLabel labelImage;
	JLabel labelname;
	JLabel labelUsername;
	JLabel labelPassword;
	JLabel labelEmail;
	JLabel labelTokens;
	Client client=null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSpace frame = new AdminSpace();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminSpace() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1151, 696);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1135, 658);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1135, 647);
		panel.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("home", null, panel_1, null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Manager", null, panel_2, null);
		panel_2.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 5, 958, 530);
		panel_2.add(tabbedPane_1);
		
		JPanel panel_8 = new JPanel();
		tabbedPane_1.addTab("add", null, panel_8, null);
		panel_8.setLayout(null);
		
		JPanel panel_9 = new JPanel();
		tabbedPane_1.addTab("gestion", null, panel_9, null);
		panel_9.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Stock manager", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("delivery Man", null, panel_4, null);
		
		final JPanel panelClient = new JPanel();
		tabbedPane.addTab("Client", null, panelClient, null);
		panelClient.setLayout(null);
		
		tableClients = new JTable();
		tableClients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 int row = tableClients.getSelectedRow();
			        
			      // String  name=tableClients.getModel().getValueAt(row,0).toString();
			        String idd=tableClients.getModel().getValueAt(row,0).toString();
			        int id=Integer.parseInt(idd);
			        client= (Client)GestionUserDelegate.doFindUserById(id);
			        
			        labelname.setText(client.getFullName());
					labelUsername.setText(client.getUserName());
					labelPassword.setText(client.getPassword());
					labelEmail.setText(client.getEmail());
					labelAdresse.setText(client.getAdress());
					labelTokens.setText(client.getNumberTockens().toString());
					byte[] imageB = null;
			        try
			        {
					String ch1=client.getImageUrl();
			        String ch="c:/";
			       String ch2=ch.concat(ch1);
			        String ch3=ch2.replace('/','\\');
			        File imageF=new File(ch3);
			        System.out.println(ch3);
			        FileInputStream fis=new FileInputStream(imageF);
			        ByteArrayOutputStream bos=new ByteArrayOutputStream();
			        byte[] buf = new byte[1024]; 
			        for(int readNum;(readNum=fis.read(buf))!=-1;)
			        {
			          bos.write(buf,0,readNum);
			        }
			        imageB=bos.toByteArray();
			       
			        
			    }catch(Exception ee)
			    {
			        JOptionPane.showMessageDialog(null,ee);
			    }
			     
			            Image img = Toolkit.getDefaultToolkit().createImage(imageB);
			        ImageIcon icon =new ImageIcon(img);
			        labelImage.setIcon(icon);   

			      // detail.jLabelname.setText(name);
			}
		});
		tableClients.setFont(new Font("Tahoma", Font.BOLD, 15));
		tableClients.setBackground(new Color(253, 245, 230));
		tableClients.setBounds(544, 122, 586, 406);
		tableClients.setModel(new ListClient());
		panelClient.add(tableClients);
		
		 labelImage = new JLabel("");
		labelImage.setIcon(new ImageIcon(EspaceAdmin.class.getResource("/tn/esprit/auction/gui/authentification/User-icon.png")));
		labelImage.setBounds(117, 29, 267, 221);
		panelClient.add(labelImage);
		
		JLabel lblName = new JLabel("name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblName.setBounds(182, 276, 79, 31);
		panelClient.add(lblName);
		
		 labelname = new JLabel("---------");
		labelname.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelname.setBounds(307, 281, 141, 23);
		panelClient.add(labelname);
		
		JLabel lblUsername = new JLabel("userName");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUsername.setBounds(182, 329, 115, 31);
		panelClient.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPassword.setBounds(182, 379, 115, 31);
		panelClient.add(lblPassword);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEmail.setBounds(182, 432, 79, 31);
		panelClient.add(lblEmail);
		
		JLabel lblAdresse = new JLabel("adresse");
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAdresse.setBounds(182, 484, 79, 31);
		panelClient.add(lblAdresse);
		
		 labelUsername = new JLabel("---------");
		labelUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelUsername.setBounds(307, 337, 141, 23);
		panelClient.add(labelUsername);
		
		 labelPassword = new JLabel("---------");
		labelPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelPassword.setBounds(307, 384, 141, 23);
		panelClient.add(labelPassword);
		
		 labelEmail = new JLabel("---------");
		labelEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelEmail.setBounds(307, 437, 141, 23);
		panelClient.add(labelEmail);
		
		 labelAdresse = new JLabel("---------");
		labelAdresse.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelAdresse.setBounds(307, 489, 141, 23);
		panelClient.add(labelAdresse);
		
		JLabel lblNombreToken = new JLabel("tokens");
		lblNombreToken.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNombreToken.setBounds(182, 542, 79, 31);
		panelClient.add(lblNombreToken);
		
		 labelTokens = new JLabel("---------");
		labelTokens.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelTokens.setBounds(307, 545, 141, 23);
		panelClient.add(labelTokens);
		
		JButton btnDelete = new JButton("delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(client!=null)
				{if(GestionUserDelegate.doDeleteUser(client))
				{
					client=null;
					labelEmail.setText("------");
					labelAdresse.setText("------");
					labelname.setText("------");
					labelUsername.setText("------");
					labelPassword.setText("------");
					labelTokens.setText("------");
					labelImage.setIcon(new ImageIcon(EspaceAdmin.class.getResource("/tn/esprit/auction/gui/authentification/User-icon.png")));
					
					tableClients.setModel(new ListClient());
				    panelClient.add(tableClients);
				}}
				else 
				{
					JOptionPane.showMessageDialog(panelClient, "ouuupss you have to choose a client to delete ");
				
				
				}
			}
		});
		btnDelete.setBounds(405, 579, 89, 23);
		panelClient.add(btnDelete);
		
		JButton btnSendMail = new JButton("send Mail");
		btnSendMail.setBounds(284, 579, 89, 23);
		panelClient.add(btnSendMail);
		
		
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_6, null);
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_7, null);
	}
	                             
}