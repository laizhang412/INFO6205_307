package gene;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
/*
 * 3. a fitness function--this is essentially a measure of how good a candidate (organism) 
 *    solution is for the problem you have chosen to solve;
 *    
 *    	a. The fitness function compares the image(DNA) generated with the target image.
 *    	b. A cutoff in the fitness function is used to improve efficiency by avoiding comparing 
 *    		RGB of every coordinates.
 */
public class IndividualImage{
	
	private Parameters parameter;
	private Random random;
	public ArrayList<Polygon> dna;
	public BufferedImage img;
	
	public IndividualImage(Parameters parameter) {
		this.parameter = parameter;
		this.random = new Random();
		this.dna = new ArrayList<Polygon>();
		
		// Generate DNA
		for(int i = 0; i < parameter.geneNum; i++) {
			this.dna.add(randomPolygon());
		}
	}
	
	public void draw() {
		this.img = new BufferedImage(parameter.width,parameter.height,BufferedImage.TYPE_INT_ARGB);
		this.img.getGraphics().setColor(Color.BLACK);
		this.img.getGraphics().clearRect(0, 0, parameter.width, parameter.height);
		for(Polygon polygon : dna) {
			polygon.draw(this.img.getGraphics());
		}
	}
	
	private Polygon randomPolygon(){
		int pointNum = random.nextInt(parameter.polygonMax - 2) + 3;
		int[] x = new int[pointNum];
		int[] y = new int[pointNum];
		for(int i = 0; i < pointNum; i++) {
			x[i] = random.nextInt(parameter.width);
			y[i] = random.nextInt(parameter.height);
		}
		int z = random.nextInt(1000);
		int[] color = new int[4];
		for(int i = 0; i < 4; i++) {
			color[i] = random.nextInt(256);
		}
		return new Polygon(color,x,y,z);
	}
	
	public double fitness() {
		BufferedImage bi1 = parameter.targetImage;
		int cutOff = parameter.cutOff;
		
		double error = 0;
		for(int x = 0; x < bi1.getWidth(); x += cutOff) {
			for(int y = 0; y < img.getHeight(); y += cutOff) {
				int rgb1 = bi1.getRGB(x, y);
				int rgb2 = img.getRGB(x, y);
				error += Math.abs((rgb1 & 0xFF) - (rgb2 & 0xFF)); // blue
				error += Math.abs(((rgb1 >>> 8) & 0xFF) - ((rgb2 >>> 8) & 0xFF)); // green
				error += Math.abs(((rgb1 >>> 16) & 0xFF) - ((rgb2 >>> 16) & 0xFF)); // red
//				error += Math.abs(((rgb1 >>> 24) & 0xFF) - ((rgb2 >>> 24) & 0xFF)); // alpha
			}
		}
		return error;
	}
	
	public void mutatePolygon() {
		for(Polygon polygon : this.dna) {
			polygon.mutatePolygon(this.parameter,this.parameter.getProbability());
		}
	}
	
	
}
