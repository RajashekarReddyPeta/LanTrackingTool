/*Raja SHekar reddy Peta
spl final project*/

import java.awt.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.border.*;
class LanTracking extends JFrame implements ActionListener
{
	JButton b1,b2,b3,b4;
	JLabel l1;
	JTextArea ta;
	Container c;
	Box b;
	JPanel jp1,jp2,jp3;
	LanTracking()
	{
		c=getContentPane();
		l1=new JLabel("Lan Tracking Tool");
		b1=new JButton("IP Address");
		b2=new JButton("Ping");
		b3=new JButton("Ipconfig");
		b4=new JButton("Mac");
		b=Box.createHorizontalBox();
		ta=new JTextArea(25,70);
		c.setLayout(new BorderLayout());
		jp1=new JPanel();
		l1.setForeground(Color.gray);
		l1.setFont(new Font("Sanserif",Font.BOLD,20));
		jp1.add(l1);
		jp2=new JPanel();

		
		b.add(new JScrollPane(ta));
		jp2.add(b1);
		jp2.add(b3);		
		jp2.add(b2);
		jp2.add(b4);
		jp3=new JPanel();
	
		Border bd=BorderFactory.createTitledBorder("Status Window");
		jp1.setBackground(Color.orange);
		jp2.setBackground(Color.orange);
		jp3.setBackground(Color.orange);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		jp3.setBorder(bd);
		jp3.add(b);
		c.add("North",jp1);
		c.add("South",jp2);
		c.add("Center",jp3);

		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(c);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	public void actionPerformed(ActionEvent ae)
	{
		DataInputStream dis=null;
		Runtime r=Runtime.getRuntime();
		String config_info="";
		InetAddress inet1=null;
		if(ae.getSource()==b1)//hostname,ipaddress
		{
			try
			{
				inet1=InetAddress.getLocalHost();
				String ip=inet1.getHostAddress();
				String name=inet1.getHostName();
				ta.setText(ip+"	 "+name);
			
		
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		
		}
		else if(ae.getSource()==b2)//ping
		{
			String ping_info="";
			StringWriter sw=new StringWriter();
			PrintWriter pw=new PrintWriter(sw);
			String ipadd=JOptionPane.showInputDialog("Enter IP address to ping");
			try
			{
				Process p=r.exec("ping "+ipadd);
				dis=new DataInputStream(p.getInputStream());
				while((ping_info=dis.readLine())!=null)
				{
					pw.println(ping_info);
					String content=sw.toString();
					ta.setText(content);
				}

			}
			catch (Exception e)
			{	
				e.printStackTrace();
			}
			
		}
		else if(ae.getSource()==b3)//ipconfig
		{
			StringWriter sw=new StringWriter();
			PrintWriter pw=new PrintWriter(sw);
			try
			{
				Process p=r.exec("ipconfig -all");
				dis=new DataInputStream(p.getInputStream());
				
				while((config_info=dis.readLine())!=null)
				{
					pw.println(config_info);
					String content=sw.toString();//convert in String
					ta.setText(content);
				}	
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			

		}
		else if(ae.getSource()==b4)//arp
		{
			String arp_info="";
			try
			{
				
				StringWriter sw=new StringWriter();
				PrintWriter pw=new PrintWriter(sw);
				Process p=r.exec("arp -a");
				dis=new DataInputStream(p.getInputStream());
				while((arp_info=dis.readLine())!=null)
				{
					pw.println(arp_info);
					String content=sw.toString();
					ta.setText(content);
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void main(String st[])
	{
		LanTracking lt=new LanTracking();
		lt.setSize(600,600);
		lt.setVisible(true);
		lt.setLocation(120,50);
		lt.setTitle("Lan Tracking Tool");
	}
}