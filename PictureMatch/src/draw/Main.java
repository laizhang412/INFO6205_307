package draw;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import gene.Population;
import gene.Parameters;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Image img = ImageIO.read(new File("target.jpg"));
		BufferedImage bi1 = (BufferedImage) img;
		Parameters p = new Parameters(bi1.getWidth(),bi1.getHeight(),10,100,5,bi1,100);
		Population gen = new Population(p);
//		gen.imageList.add(new IndividualImage(p));
		// retrieve image
//	    	BufferedImage bi2 = new BufferedImage(bi1.getWidth(),bi1.getHeight(),BufferedImage.TYPE_INT_ARGB);
//	    	gen.draw(bi2.getGraphics(), gen.selection());
	    	File outputfile = new File("saved.png");
		ImageIO.write(gen.selection().img, "png", outputfile);
		for(int i = 0; i < gen.imageList.size(); i++) {
			System.out.println(gen.imageList.get(i).fitness());
		}
		
		
		System.out.println("least error: " + gen.selection().fitness());
		
	}

}