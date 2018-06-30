package org.ice1000.clubActivity.drawer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author ice1000
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
public class Drawer {
	private final String xName;
	private final String yName;
	private ArrayList<OneData> data;
	private Mode mode;
	private Color[] colors;

	public Drawer(String xName, String yName) {
		this.xName = xName;
		this.yName = yName;
		data = new ArrayList<>();
		mode = Mode.Histogram;
		colors = new Color[]{Color.BLUE, Color.CYAN, Color.RED, Color.GREEN, Color.YELLOW};
	}

	public Drawer writeToFile(String fileName, int width, int height) {
		return writeToFile(new File(fileName), width, height);
	}

	public Drawer writeToFile(File file, int width, int height) {
		BufferedImage image = render(width, height);

		try {
			ImageIO.write(image, "PNG", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public Drawer column(OneData height) {
		data.add(height);
		return this;
	}

	private BufferedImage render(int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D graphics = image.createGraphics();
		int left = width >>> 3;
		int right = width - left;
		int top = height >>> 3;
		int bottom = height - top;
		graphics.setColor(Color.BLACK);
		graphics.drawString(xName, left + 5, top - 5);
		int textY = bottom + 15;
		graphics.drawString(yName, right - 10, textY);
		graphics.drawLine(left, top, left, bottom);
		graphics.drawLine(left, bottom, right, bottom);
		if (data.size() == 0) return image;
		switch (mode) {
			case Histogram:
				int columnWidth = (right - left) / data.size();
				int maxValue = 0;
				for (OneData datum : data) if (datum.value > maxValue) maxValue = datum.value;
				double proportion = (height >>> 1) / (double) maxValue;
				for (int i = 0; i < data.size(); i++) {
					graphics.setColor(colors[i % colors.length]);
					OneData oneData = data.get(i);
					int columnSize = (int) (oneData.value * proportion) + top;
					int x = left + i * columnWidth + 1;
					graphics.fillRect(x,
							bottom - columnSize,
							columnWidth,
							columnSize);
					graphics.setColor(Color.BLACK);
					graphics.drawString(oneData.name, x, textY);
				}
				break;
			case LineChart:
				break;
		}
		return image;
	}

	public Drawer showInWindow(int width, int height) {
		BufferedImage render = render(width, height);
		new JFrame("Rendered Image Preview") {{
			setLayout(new BorderLayout());
			add(new JComponent() {
				@Override
				protected void paintComponent(Graphics g) {
					g.drawImage(render, 0, 0, width, height, this);
					super.paintComponent(g);
				}
			}, BorderLayout.CENTER);
			pack();
			setSize(width, height);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}}.setVisible(true);
		return this;
	}

	public Drawer mode(Mode mode) {
		this.mode = mode;
		return this;
	}

	public static void main(String... args) {
		new Drawer("国家", "胜利数")
//				.writeToFile("rendered.png", 512, 512);
				.column(new OneData(10, "中国"))
				.column(new OneData(2, "德国"))
				.column(new OneData(5, "朝鲜"))
				.column(new OneData(8, "日本"))
				.mode(Mode.Histogram)
				.showInWindow(512, 512);
	}
}
