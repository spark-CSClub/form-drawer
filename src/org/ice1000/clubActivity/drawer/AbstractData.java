package org.ice1000.clubActivity.drawer;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

public abstract class AbstractData<@NotNull Data extends @NotNull AbstractData<@NotNull Data>> {
	public int value;
	public @NotNull String name;

	public AbstractData(int value, @NotNull String name) {
		this.value = value;
		this.name = name;
	}

	public abstract void drawHistogram(@NotNull Graphics2D graphics,
	                                   int index,
	                                   int bottom,
	                                   int left,
	                                   int offset,
	                                   double columnSize,
	                                   double columnWidth);

	public abstract void drawLineChart(@NotNull Graphics2D graphics,
	                                   int index,
	                                   int bottom,
	                                   int left,
	                                   int offset,
	                                   double columnSize,
	                                   double columnWidth,
	                                   @NotNull Data lastData);
}
