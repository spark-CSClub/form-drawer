package org.ice1000.clubActivity.drawer;

import org.jetbrains.annotations.NotNull;

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
public class Drawer<@NotNull Data extends @NotNull AbstractData> {
	private final @NotNull String xName;
	private final @NotNull String yName;
	private @NotNull ArrayList<@NotNull Data> data;
	private @NotNull Mode mode;
	private @NotNull Color @NotNull [] colors;

	public Drawer(@NotNull String xName, @NotNull String yName) {
		this.xName = xName;
		this.yName = yName;
		data = new ArrayList<>();
		mode = Mode.Histogram;
		colors = new Color[]{Color.BLUE, Color.ORANGE, Color.GREEN, Color.RED, Color.CYAN, Color.YELLOW};
	}

	public @NotNull Drawer<Data> writeToFile(@NotNull String fileName, int width, int height) {
		return writeToFile(new File(fileName), width, height);
	}

	public @NotNull Drawer<Data> writeToFile(@NotNull File file, int width, int height) {
		BufferedImage image = render(width, height);

		try {
			ImageIO.write(image, "PNG", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public @NotNull Drawer<Data> column(@NotNull Data height) {
		data.add(height);
		return this;
	}

	private @NotNull BufferedImage render(int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D graphics = image.createGraphics();
		int left = width >>> 3;
		int right = width - left;
		int top = height >>> 3;
		int bottom = height - top;
		graphics.setColor(Color.BLACK);
		graphics.drawString(xName, left + 5, top - 5);
		int textY = bottom + 15;
		int offset = 10;
		graphics.drawString(yName, right - offset, textY);
		graphics.drawLine(left, top, left, bottom);
		graphics.drawLine(left, bottom, right, bottom);
		if (data.size() == 0) return image;
		int maxValue = 0;
		for (Data datum : data) if (datum.value > maxValue) maxValue = datum.value;
		double proportion = (bottom - top) / (double) maxValue;
		double columnWidth = (right - left - 10.0) / data.size();
		switch (mode) {
			case Histogram:
				for (int i = 0; i < data.size(); i++) {
					Data oneData = data.get(i);
					oneData.drawHistogram(graphics, colors, i, bottom, left, offset, proportion, columnWidth);
					graphics.setColor(Color.BLACK);
					graphics.drawString(oneData.name, (int) (left + i * columnWidth + 1), textY);
				}
				break;
			case LineChart:
				if (data.size() == 1) break;
				Data lastData = data.get(0);
				for (int i = 1; i < data.size(); i++) {
					Data oneData = data.get(i);
					oneData.drawLineChart(graphics, colors, i, bottom, left, offset, proportion, columnWidth, lastData);
					graphics.setColor(Color.BLACK);
					graphics.drawString(oneData.name, (int) (left + (i - 1) * columnWidth + 1), textY);
					lastData = oneData;
				}
				break;
		}
		double maxValueDivideBy5 = maxValue / 5.0;
		for (int i = 0; i < 5; i++)
			graphics.drawString(String.valueOf(maxValueDivideBy5 * i), left + 1,
					(float) (bottom - maxValueDivideBy5 * i * proportion));
		return image;
	}

	public @NotNull Drawer<Data> colors(@NotNull Color @NotNull ... colors) {
		this.colors = colors;
		return this;
	}

	public @NotNull Drawer<Data> showInWindow(int width, int height) {
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

	public @NotNull Drawer<Data> mode(@NotNull Mode mode) {
		this.mode = mode;
		return this;
	}

	public static void main(@NotNull String @NotNull ... args) {
		new Drawer<>("国家", "胜利数")
//				.writeToFile("rendered.png", 512, 512);
				.column(new SimpleData(9, "中国"))
				.column(new SimpleData(2, "德国"))
				.column(new SimpleData(5, "朝鲜"))
				.column(new SimpleData(8, "日本"))
				.column(new SimpleData(0, "零点"))
				.column(new SimpleData(3, "WTF"))
				.mode(Mode.Histogram)
				.showInWindow(512, 512)
				.mode(Mode.LineChart)
				.showInWindow(512, 512);
	}
}
