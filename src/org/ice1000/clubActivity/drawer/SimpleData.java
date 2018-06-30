package org.ice1000.clubActivity.drawer;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author ice1000
 */
@SuppressWarnings("WeakerAccess")
public class SimpleData extends AbstractData<@NotNull SimpleData> {
	public int value;

	public SimpleData(int value, @NotNull String name) {
		super(name);
		this.value = value;
	}

	@Override
	public int maxValue() {
		return value;
	}

	@Override
	public void drawHistogram(@NotNull Graphics2D graphics,
	                          @NotNull Color @NotNull [] colors,
	                          int index,
	                          int bottom,
	                          int left,
	                          int offset,
	                          double columnSize,
	                          double columnWidth) {
		graphics.setColor(colors[index % colors.length]);
		int x = (int) (left + index * columnWidth + 1);
		graphics.fillRect(x + offset,
				(int) (bottom - columnSize),
				(int) columnWidth,
				(int) columnSize);
	}

	@Override
	public void drawLineChart(@NotNull Graphics2D graphics,
	                          @NotNull Color @NotNull [] colors,
	                          int index,
	                          int bottom,
	                          int left,
	                          int offset,
	                          double proportion,
	                          double columnWidth,
	                          @NotNull SimpleData lastData) {
		graphics.setColor(colors[index % colors.length]);
		int columnSize = (int) (value * proportion);
		int lastColumnSize = (int) (lastData.value * proportion);
		int x = (int) (left + (index - 1) * columnWidth + 1);
		int nextX = (int) (left + index * columnWidth + 1);
		graphics.drawLine(nextX + offset,
				bottom - columnSize,
				x + offset,
				bottom - lastColumnSize);
		graphics.drawLine(nextX + offset - 1,
				bottom - columnSize,
				x + offset - 1,
				bottom - lastColumnSize);
	}
}
