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
public class Drawer<Data extends AbstractData> {
	private final String xName;
	private final String yName;
	private ArrayList<Data> data;
	private Mode mode;
	private Color[] colors;

	public Drawer(String xName, String yName) {
		this.xName = xName;
		this.yName = yName;
		data = new ArrayList<>();
		mode = Mode.Histogram;
		colors = new Color[]{Color.BLUE, Color.ORANGE, Color.GREEN, Color.RED, Color.CYAN, Color.YELLOW};
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

	public <AnyData extends Data> Drawer column(AnyData height) {
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
				double columnWidth = (right - left - 10.0) / data.size();
				int maxValue = 0;
				for (Data datum : data) if (datum.value > maxValue) maxValue = datum.value;
				double proportion = (height >>> 1) / (double) maxValue;
				int maxValueDivideBy5 = maxValue / 5;
				for (int i = 0; i < 5; i++) {
					graphics.drawString(String.valueOf(maxValueDivideBy5), left + 1,
							(float) (top + maxValueDivideBy5 + proportion));
				}
				for (int i = 0; i < data.size(); i++) {
					graphics.setColor(colors[i % colors.length]);
					Data oneData = data.get(i);
					int columnSize = (int) (oneData.value * proportion) + top;
					int x = (int) (left + i * columnWidth + 1);
					graphics.fillRect(x + 10,
							bottom - columnSize,
							(int) columnWidth,
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
		new Drawer<>("国家", "胜利数")
//				.writeToFile("rendered.png", 512, 512);
				.column(new SimpleData(10, "中国"))
				.column(new SimpleData(2, "德国"))
				.column(new SimpleData(5, "朝鲜"))
				.column(new SimpleData(8, "日本"))
				.mode(Mode.Histogram)
				.showInWindow(512, 512);
	}
}
