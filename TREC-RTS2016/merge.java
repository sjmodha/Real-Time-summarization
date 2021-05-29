import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception
	{
		//String sourceFile1Path = "C:\\Users\\rishabS\\Downloads\\btp\\first\\sorted";
				//String sourceFile2Path = "C:\\Users\\rishabS\\Downloads\\btp\\first\\sorted";
		 
				String mergedFilePath = "/home/rishab/Downloads/btp/merge_B_Jelinek0.1_0.6_normal_0.4_22_15_26_18_final_luchen_Queries_imp_jac.txt";
				//FileReader reader=new FileReader("C:\\Users\\rishabS\\Downloads\\btp\\first\\sorted\\1_output.txt");
//				File[] files = new File[2];
//				files[0] = new File(sourceFile1Path);
//				files[1] = new File(sourceFile2Path);
				
				//System.out.println(files[0].listFiles());
		 
				File mergedFile = new File(mergedFilePath);
		 
				//mergeFiles(files, mergedFile);
				mergeFiles(mergedFile);
	}
	
	public static void mergeFiles(File mergedFile) { 
		FileWriter fstream = null;
		BufferedWriter out = null;
		try {
			fstream = new FileWriter(mergedFile, false);
			 out = new BufferedWriter(fstream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
 
		for (File f : getAllLogFiles()) {
			System.out.println("merging: " + f.getName());
			FileInputStream fis;
			try {
				fis = new FileInputStream(f);
				BufferedReader in = new BufferedReader(new InputStreamReader(fis));
 
				String aLine;
				int temp = 0 ;
				while ((aLine = in.readLine()) != null) {
					if(temp==0){
						//3System.out.println(aLine);
						temp++;
					}
					out.write(aLine);
					out.newLine();
				}
 
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
 
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}
	
	public static File[] getAllLogFiles(){
		File folder = new File("/home/rishab/Downloads/btp/merge/fin/");
		File[] files = folder.listFiles();
		
		return files;
		}

}
