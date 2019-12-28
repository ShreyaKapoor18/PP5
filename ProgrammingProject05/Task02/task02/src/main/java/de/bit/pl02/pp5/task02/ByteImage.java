package de.bit.pl02.pp5.task02;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ByteImage {

	private byte[] byte_array;
	
	public ByteImage(byte[] byte_array) {
		this.byte_array = byte_array;
		
	}

	/** Reads in a byte array and converts it to an .jpg file
	 * 
	 * @throws IOException if input output error has occurred
	 * @throws FileNotFoundException if file was not found 
	 * @params bytefile is the path of the file that stores 
	 * 			the binary form of the image to be converted  
	 * @params outputpath is the path for the .jpg file to be saved at 
	 */
	public static void byteToImage(byte[] byte_array, String outputpath) throws IOException {
		//BufferedImage bImage = ImageIO.read(new File(bytefile));
		//ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
		      //ImageIO.write(bImage, "jpg", bos);
		      byte [] data = byte_array;
		      ByteArrayInputStream bis = new ByteArrayInputStream(data);
		      BufferedImage bImage2 = ImageIO.read(bis);
		      ImageIO.write(bImage2, "jpg", new File(outputpath+".jpg"));
		      System.out.println("Image was created.");	
	      } catch (FileNotFoundException e) {
	            System.err.println(e.getMessage());
	      } catch (IOException e2) {
	            System.err.println(e2.getMessage());
	      }
	}
}
