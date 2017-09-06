package org.lanqiao.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet(name = "imageServlet", urlPatterns = { "/imageServlet.do" })
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String codes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			int num = rand.nextInt(61);
			sb.append(codes.charAt(num));
		}
		String s = sb.toString();
		request.getSession().setAttribute("codes", codes);
		BufferedImage bi = new BufferedImage(75, 22, BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 75, 22);
		g.setColor(Color.BLUE);
		g.drawRect(1, 1, 73, 20);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("宋体", Font.BOLD, 16));
		g.drawString(s, 20, 15);
		g.setColor(Color.BLUE);
		for (int i = 0; i < 6; i++) {
			g.drawLine(rand.nextInt(75), rand.nextInt(22), rand.nextInt(75), rand.nextInt(22));
		}
		response.setContentType("image/jpeg");
		ImageIO.write(bi, "jpg", response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
