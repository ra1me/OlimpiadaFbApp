import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardFunctions {
	
	Clipboard clipboard; 
	public ClipboardFunctions(){
		clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
	}
	
	String getTextFromClipboard(){
		String data;
		clipboard =Toolkit.getDefaultToolkit().getSystemClipboard();
		try {
			data = (String) clipboard.getData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e) {
			data="error2";
			e.printStackTrace();
		} catch (IOException e) {
			data="error2";
			e.printStackTrace();
		} catch (Exception e){
			data="error2";
			e.printStackTrace();
		}
		
		return data;
	}
	void putStringToCliboard(String data){
		StringSelection selection = new StringSelection(data);
		clipboard.setContents(selection,selection);
	}
	
}
