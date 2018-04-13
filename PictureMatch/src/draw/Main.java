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
		Image img = ImageIO.read(new File("target3.jpg"));
		BufferedImage bi1 = (BufferedImage) img;
		Parameters p = new Parameters(bi1.getWidth(),bi1.getHeight(),20,1000,3,bi1,2);
		
		Population gen = new Population(p);
		int i = 1;
		while(gen.getPQ().peek().fitness() > 20) {
			System.out.print("Now is the " + i + "th generation. And the best fitness is " +  gen.getPQ().peek().fitness()+ "\n");
			gen.evolution();
			i++;
		}
//		System.out.println(gen.imageList.size());
//		gen.imageList.add(new IndividualImage(p));
		// retrieve image
//	    	BufferedImage bi2 = new BufferedImage(bi1.getWidth(),bi1.getHeight(),BufferedImage.TYPE_INT_ARGB);
//	    	gen.draw(bi2.getGraphics(), gen.selection());
//	    	File outputfile = new File("saved.png");
//		ImageIO.write(gen.getPQ().peek().img, "png", outputfile);
//		System.out.println(gen.imageList.size());
//		System.out.println(gen.getPQ().peek().fitness());
//		gen.getPQ().peek().mutatePolygon();
//		gen.getPQ().peek().draw();
//		System.out.println(gen.imageList.size());
		File outputfile1 = new File("saved1.png");
		ImageIO.write(gen.getPQ().peek().img, "png", outputfile1);
		System.out.println(gen.getPQ().peek().fitness());
//		for(int i = 0; i < gen.imageList.size(); i++) {
//			System.out.println(gen.imageList.get(i).fitness());
//		}
		
//		System.out.println(gen.crossOver().fitness());
//		File outputfile2 = new File("saved2.png");
//		ImageIO.write(gen.crossOver().img, "png", outputfile2);
		
		
		
		
	}

}
