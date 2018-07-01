package org.eulir.demo;

import org.ice1000.clubActivity.drawer.AbstractData;
import org.ice1000.clubActivity.drawer.Drawer;
import org.ice1000.clubActivity.drawer.Mode;
import org.ice1000.clubActivity.drawer.SimpleData;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author ice1000
 * @author EULIR
 */
public class Demo1 {
	private static final String fontName = "Ubuntu";

	//World Cup 2018 Group A
	public static void main(@NotNull String @NotNull ... args) {
		createDrawer()
				.mode(Mode.Histogram)
				.showInWindow(512, 512);
		createDrawer()
				.mode(Mode.LineChart)
				.showInWindow(512, 512);
		createDrawer2()
				.mode(Mode.Histogram)
				.showInWindow(512, 512);
		createDrawer2()
				.mode(Mode.LineChart)
				.showInWindow(512, 512);
	}

	@Contract(pure = true)
	private static @NotNull Drawer<@NotNull AbstractData> createDrawer2() {
		return new Drawer<>("Country", "Losses", "World Cup 2018 Group A Loss balls")
				.font(new Font(fontName, Font.PLAIN, 16))
				.column(new SimpleData("Brazil", 0))
				.column(new SimpleData("Croatia", 2))
				.column(new SimpleData("Mexico", 0))
				.column(new SimpleData("Cameroon", 3));
	}

	@Contract(pure = true)
	private static @NotNull Drawer<@NotNull AbstractData> createDrawer() {
		return new Drawer<>("Country", "Wins", "World Cup 2018 Group A Winning balls")
				.font(new Font(fontName, Font.PLAIN, 16))
				.column(new SimpleData("Brazil", 2))
				.column(new SimpleData("Croatia", 1))
				.column(new SimpleData("Mexico", 2))
				.column(new SimpleData("Cameroon", 0));
	}
}
