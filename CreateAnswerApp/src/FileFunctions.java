import java.io.File;
import java.io.IOException;

public class FileFunctions {
	
	File getFile(){
		File db = new File("baza_pytan.odb");
		if(!db.exists()){
			try {
				db.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return db;
	}
	
}
