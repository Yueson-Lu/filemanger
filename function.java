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
	fc.setDialogTitle("ѡ���ļ�");
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
//		�ַ���
			int WordNum=0;
//			��������
			int lineNum=0;
//			�ո���
			int SpaceNum=0;
//			������
			int nullline=0;
//			ע������
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
			String str1=Jframe.JT2.getText()+"�ļ����ƣ�"+file.getName();
			String str2="�ַ�����"+(WordNum-SpaceNum)+"		"+"�ո�����"+SpaceNum+"		"+"����������"+lineNum+"		"+"��������"+nullline+"		"+"ע������"+explainNum;
			 String   strMsg = "<html><body>"+str1+"<br>"+str2+"<body></html>"; 
			Jframe.JT2.setText(strMsg);}
			else {
			System.out.println("�ļ����ƣ�"+file.getName()+"�ַ�����"+(WordNum-SpaceNum)+"		"+"�ո�����"+SpaceNum+"		"+"����������"+lineNum+"		"+"��������"+nullline+"		"+"ע������"+explainNum);
			}
			} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			}
		
	}
	static void keycontroltest() {
		System.out.println("����������:"+"\n"+"1.-c ��ʾѡȡ�ļ�"+"\t"+"2.-l ��ʾͳ���ļ�"+"\t"+"3.-w��ʾͼ�ν���"+"\t"+"4.-z ��ʾ�����ļ�");
		Scanner sc = new Scanner(System.in);  
	        String strsc=sc.nextLine();
		if(strsc.equals("-c")) {
			System.out.println("�������ļ�·��");
			Scanner sc1 = new Scanner(System.in);  
			String strsc1=sc1.nextLine();
			File f=new File(strsc1);
			if(!f .exists()  && !f .isDirectory()) {
				System.out.println("�ļ�������");
				f .mkdir();
			
			}else {
				System.out.println("�Ѿ���ȡ�ļ�������������");
				Scanner sc2 = new Scanner(System.in); 
				String strsc2=sc2.nextLine();
				if(strsc2.equals("-l")) {
					try {
						count(f,true);
					} catch (IOException e) {
						// TODO �Զ����ɵ� catch ��s
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
				System.out.println("����ѡ���ļ�����������ͼ�ν���");
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
	
