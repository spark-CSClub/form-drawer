package org.ice1000.clubActivity.drawer;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Arrays;

/**
 * @author ice1000
 */
@SuppressWarnings("WeakerAccess")
public class LayeredData extends AbstractData<@NotNull LayeredData> {
	private class Layer implements Comparable<Layer> {
		int index;
		double value;

		@Override
		public int compareTo(@NotNull LayeredData.Layer o) {
			return Double.compare(value, o.value);
		}

		public Layer(int index, double value) {
			this.index = index;
			this.value = value;
		}
	}

	private @NotNull Layer @NotNull [] values;
	private double maxValue;

	public LayeredData(@NotNull String name, double... rawValues) {
		super(name);
		values = new Layer[rawValues.length];
		for (int i = 0; i < rawValues.length; i++) {
			double val = rawValues[i];
			if (val > maxValue) maxValue = val;
			values[i] = new Layer(i, val);
		}
		Arrays.sort(values);
	}

	@Override
	public double maxValue() {
		return maxValue;
	}

	@Override
	public void drawHistogram(@NotNull Graphics2D graphics,
	                          @NotNull Color @NotNull [] colors,
	                          int index,
	                          int bottom,
	                          int left,
	                          int offset,
	                          double proportion,
	                          double columnWidth) {
		for (int i = values.length - 1; i >= 0; i--) {
			Layer value = values[i];
			int columnSize = (int) (value.value * proportion);
			graphics.setColor(colors[value.index % colors.length]);
			int x = (int) (left + index * columnWidth + 1);
			graphics.fillRect(x + offset,
					bottom - columnSize,
					(int) columnWidth - 5,
					columnSize);
		}
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
	                          @NotNull LayeredData lastData) {
		for (Layer value : values) {
			graphics.setColor(colors[value.index % colors.length]);
			int columnSize = (int) (value.value * proportion);
			Layer lastLayer = null;
			for (Layer layer : lastData.values) if (layer.index == value.index) lastLayer = layer;
			if (lastLayer == null) throw new IllegalStateException("Incorrect input data");
			int lastColumnSize = (int) (lastLayer.value * proportion);
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
}
