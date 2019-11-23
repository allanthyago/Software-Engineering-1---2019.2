package dao;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ManipularImagem {
	public static BufferedImage setImagemDimensao(String caminhoImg, int imgLargura,int imgAltura ) {
		Double novaImgLargura = null;
		Double novaImgAltura = null;
		Double imgProporcao = null;
		Graphics2D g2d = null;
		BufferedImage imagem = null,novaImagem=null;
		
		try {
			imagem = ImageIO.read(new File(caminhoImg));
		}catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
		novaImgLargura = (double) imagem.getWidth();
		novaImgAltura = (double) imagem.getHeight();
		
		if(novaImgLargura>=imgLargura) {
			imgProporcao = (novaImgAltura/novaImgLargura);
			novaImgLargura = (double)imgLargura;
			novaImgAltura = (novaImgLargura * imgProporcao);
		}
		while(novaImgAltura>imgAltura) {
			novaImgLargura = (double)(--imgLargura);
			novaImgAltura = (novaImgLargura * imgProporcao);
		}
		novaImagem = new BufferedImage(novaImgLargura.intValue(), novaImgAltura.intValue(), BufferedImage.TYPE_INT_RGB);
		g2d = novaImagem.createGraphics();
		g2d.drawImage(imagem, 0, 0, novaImgLargura.intValue(), novaImgAltura.intValue(), null);
		
		return novaImagem;
	}
	public static byte[] getImgBytes(BufferedImage image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "JPEG", baos);
			
		}catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
	
		return baos.toByteArray();
		
	}
	public static void exibirImagemLabel(byte[] minhaImagem, javax.swing.JLabel label) {
		if(minhaImagem !=null) {
			InputStream input = new ByteArrayInputStream(minhaImagem);
			try {
				BufferedImage imagem = ImageIO.read(input);
				label.setIcon(new ImageIcon(imagem));
			}catch(IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
		else {
			label.setIcon(null);
			label.setText("Vazio");
		}
	}

}
