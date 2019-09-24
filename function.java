package filemanger;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

public  class function extends Jframe{
	static String read() {
	JFileChooser fc = new JFileChooser();
	fc.setDialogType(JFileChooser.FILES_ONLY);
	fc.setDialogTitle("选择文件");
	fc.setMultiSelectionEnabled(false);
	fc.showSaveDialog(fc);
	if (fc.getSelectedFile()==null) {
		return null;
	}
	return fc.getSelectedFile().getPath();
}
	static void input(File file) {
		Scanner scan=null;
		try {
			scan=new Scanner(file);
		} catch (Exception e) {
			// TODO: handle exception
		}
		StringBuffer sb=new StringBuffer();
		while(scan.hasNext()) {
			sb.append(scan.nextLine()).append("\n");
		}
		Jframe.JT1.setText(sb.toString());
	}
	
	static void count(File file,Boolean b) throws IOException {
		try {
			FileReader r=new FileReader(file);
			BufferedReader br=new BufferedReader(r);
//		字符数
			int WordNum=0;
//			代码行数
			int lineNum=0;
//			空格数
			int SpaceNum=0;
//			空行数
			int nullline=0;
//			注释行数
			int explainNum=0;
			Boolean    com = true; 
			String str="";
			while((str=br.readLine())!=null) {
				 WordNum+=str.length();
				  lineNum ++;
				 str = str.trim();
				 char[] c=str.toCharArray();
				 for (int i = 0; i < c.length; i++) {
					if(c[i]==' ') {
						SpaceNum++;
					}
				}
				    if(str.matches("^[\\s&&[^\\n]]*$")) {
				     nullline ++;
				    }
				    else if (str.startsWith("/*")) {
				     explainNum ++;
				        com = true; 
				       } else if (str.startsWith("")) {
				     explainNum ++;
				       } else if (true == com) {
				     explainNum ++;
				          if(str.endsWith("*/")) {
				         com = false;
				        }
				       } 
				        if (str.startsWith("//")) {
				     explainNum ++;
				       }
					}
			if(b==false) 
			{
			String str1=Jframe.JT2.getText()+"文件名称："+file.getName();
			String str2="字符数："+(WordNum-SpaceNum)+"		"+"空格数："+SpaceNum+"		"+"代码行数："+lineNum+"		"+"空行数："+nullline+"		"+"注释行数"+explainNum;
			 String   strMsg = "<html><body>"+str1+"<br>"+str2+"<body></html>"; 
			Jframe.JT2.setText(strMsg);}
			else {
			System.out.println("文件名称："+file.getName()+"字符数："+(WordNum-SpaceNum)+"		"+"空格数："+SpaceNum+"		"+"代码行数："+lineNum+"		"+"空行数："+nullline+"		"+"注释行数"+explainNum);
			}
			} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			}
		
	}
	static void keycontroltest() {
		System.out.println("请输入命令:"+"\n"+"1.-c 表示选取文件"+"\t"+"2.-l 表示统计文件"+"\t"+"3.-w表示图形界面"+"\t"+"4.-z 表示搜索文件");
		Scanner sc = new Scanner(System.in);  
	        String strsc=sc.nextLine();
		if(strsc.equals("-c")) {
			System.out.println("请输入文件路径");
			Scanner sc1 = new Scanner(System.in);  
			String strsc1=sc1.nextLine();
			File f=new File(strsc1);
			if(!f .exists()  && !f .isDirectory()) {
				System.out.println("文件不存在");
				f .mkdir();
			
			}else {
				System.out.println("已经读取文件，请输入命令");
				Scanner sc2 = new Scanner(System.in); 
				String strsc2=sc2.nextLine();
				if(strsc2.equals("-l")) {
					try {
						count(f,true);
					} catch (IOException e) {
						// TODO 自动生成的 catch 块s
						e.printStackTrace();
					}
				}
				if(strsc2.equals("-z")) {
					getList(f.getParent(),true);
					
				}
			
			}
		}
			 if(strsc.equals("-w")) {
				new Jframe();  	  
			}
			 if(strsc.equals("-l")||strsc.equals("-z")) {
				System.out.println("请先选择文件，或者启动图形界面");
				return;
			}
	}
    static String[] getList(String strPath,Boolean b) {
        File f = new File(strPath);
        File[] files = f.listFiles(); 
        String[] filelist=new String[20];
        
		if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { 
                    getList(files[i].getAbsolutePath(),b);
                } else if (fileName.endsWith("txt")) { 
                    String strFileName = files[i].getAbsolutePath();
                    if(b) {
                    System.out.println("---" + strFileName);
                    }
                    filelist[i]=files[i].getName().toString();
                } else {
                    continue;
                }
            }

        }
        return filelist;
    }
}
	
