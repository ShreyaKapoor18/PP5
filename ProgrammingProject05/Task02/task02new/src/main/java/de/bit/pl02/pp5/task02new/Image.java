package de.bit.pl02.pp5.task02new;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Image {
	private byte[] blob; // so that this image can be entered into the blob column
	private String name; 
	private String path; 
	
	Image(String path, String name)
	{
		// blob of type byte[]
		this.blob = readFile(path); 
		this.name = name; 
		this.path = path; 

	}
	
	/** Reads in an image file and converts it to its binary format
	 * Reutrns byte array of input image if not null
	 * 
	 * @params file is the file path of the .jpg or .png file
	 */	
	private byte[] readFile(String file) {
        ByteArrayOutputStream bos = null;
        try {
            File f = new File(file);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e2) {
            System.err.println(e2.getMessage());
        }
        return bos != null ? bos.toByteArray() : null;
    }
	
	
	
	ArrayList<String> find_metadata(String path)
	{
		/* we consider that we follow the same path for metadata files and the actual image files 
		 so we can just strip off the . in the filename and then add .meta infront of it in order to get the
		 related to a particular image. 
		 */
		ArrayList<String> meta = new ArrayList<String>(); 
		String metapath = this.path.split("\\.")[0]+ ".meta";
		try { 
			FileReader filer = new FileReader(metapath);
			BufferedReader buffr = new BufferedReader(filer);
			boolean eof = false;
			String author = "xx"; 
			String title = "yyzz"; 
			String Id; 
			while ((!eof))
			{
			  String s = buffr.readLine();
			  if(s == null){
			    eof = true;
			       }
			  else{
			    System.out.println(s);
			    if (s.contains("Title:")){
			    	title = s.split(":")[1]; }
			    else if (s.contains("Author:")) { 
			    	author = s.split(":")[1]; }
			   }
			}
			if ( author.length()>=2 && title.length()>=5){
				Id = author.substring(0, 2)+title.substring(0,4)+this.name.split("\\.(?=[^\\.]+$)")[0];
			} 
			else {
				Id = this.name.split("\\.(?=[^\\.]+$)")[0]; // dot followed by a number of non fos
			}
			meta.add(Id);
			meta.add(title); 
			meta.add(author); 
		
		} catch (Exception e) {
			/* if there existed none of such files then probably some 
			 default values shall be set and passed on in the given fields.
			 */
			meta.add(this.name.split("\\.(?=[^\\.]+$)")[0]); 
			meta.add("xx"); 
			meta.add("yyzz"); 
		}
		return meta;
		
		
	}
	

}
