package org.ice1000.clubActivity.drawer;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author ice1000
 */
@SuppressWarnings("WeakerAccess")
public abstract class AbstractData<@NotNull Data extends @NotNull AbstractData<@NotNull Data>> {
	public @NotNull String name;

	public AbstractData(@NotNull String name) {
		this.name = name;
	}

	public abstract double maxValue();

	public abstract void drawHistogram(@NotNull Graphics2D graphics,
	                                   @NotNull Color @NotNull [] colors,
	                                   int index,
	                                   int bottom,
	                                   int left,
	                                   int offset,
	                                   double proportion,
	                                   double columnWidth);

	public abstract void drawLineChart(@NotNull Graphics2D graphics,
	                                   @NotNull Color @NotNull [] colors,
	                                   int index,
	                                   int bottom,
	                                   int left,
	                                   int offset,
	                                   double columnSize,
	                                   double columnWidth,
	                                   @NotNull Data lastData);
}
