package filemanger;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.MenuBar;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Jframe {
JFrame frame;
JPanel jp1;
JPanel jp2;
JMenuBar JMB;
JMenu JM;
JMenuItem JMI;
static JTextArea JT1;
static JLabel JT2;
static JTextArea JT3;
JScrollPane js;



Jframe(){
frame=new JFrame("文件管理");
frame.setSize(600,800);
frame.setVisible(true);
frame.setResizable(true);


jp1=new JPanel();
JM=new JMenu("文件");
JMB=new JMenuBar();
JMB.add(JM);
JMI=new JMenuItem("读取文件");
JM.add(JMI);
jp1.setLayout(new GridLayout(1, 6, 30, 30));
jp1.add(JMB);
frame.getContentPane ().add(BorderLayout.NORTH,jp1);

jp2=new JPanel();
JT1=new JTextArea();
js=new JScrollPane();
js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
js.setViewportView(JT1);
JT1.setEditable(true);
JT1.setLineWrap(false);


JT2=new JLabel();
JT2.setText("统计："+"\n");


JT3=new JTextArea();
JT3.setEditable(false);

jp2.setLayout(new BorderLayout());
jp2.add(BorderLayout.CENTER,JT1);
jp2.add(BorderLayout.NORTH,JT2);
jp2.add(BorderLayout.EAST,JT3);
frame.getContentPane ().add(BorderLayout.CENTER,jp2);



frame.validate();  

JMI.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==JMI) {
			String f=function.read();
			File file = new File(f);
			function.input(file);
			try {
				
				function.count(file,false);
				String[] str=new String[20];
			for (int i = 0; i < function.getList(file.getParent(),false).length; i++) {
				str[i]=function.getList(file.getParent(),false)[i];
			}
			for (int j = 0; j < str.length; j++) {
				if(str[j]!=null) {
					JT3.setText(JT3.getText()+"\n"+str[j]);
				}else {
					break;
				}
				
			}	
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
	}
});

frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}
