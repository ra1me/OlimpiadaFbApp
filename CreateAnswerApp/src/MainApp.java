import java.awt.FlowLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MainApp {
	

	public static void main(String[] args) {
		JFrame window = new JFrame();
		JLabel text = new JLabel();
		window.setSize(300, 300);
	    window.setLayout(new FlowLayout()); 
		window.add(text);
		window.setVisible(true);

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ClipboardFunctions clipboard = new ClipboardFunctions();
		AnswerTransformations transformAnswer = new AnswerTransformations();
		FileFunctions fileManager = new FileFunctions();
		File db = fileManager.getFile();
		FileWriter dbWriter=null;
		//JOptionPane.showMessageDialog(null, "Pracujê");
		try {
			dbWriter = new FileWriter(db,true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String s="";
		String oldText="";
		while(window.isVisible()){
			s = clipboard.getTextFromClipboard();
			String toWrite = transformAnswer.generateAnswer(transformAnswer.getTypeOfAnswer(s),s);
			
			text.setText(toWrite);
			if(toWrite.equals("error"))
				s=oldText;
			if(!s.equals(oldText)){
				oldText=s;
				
				
				try {
					dbWriter.append(toWrite+System.lineSeparator());
					dbWriter.flush();
			
				} catch (IOException e) {
					System.out.println("zle");
					e.printStackTrace();
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				text.setText(toWrite);
			}
		}
	}

}
