package org.eulir.demo;

import org.ice1000.clubActivity.drawer.Drawer;
import org.ice1000.clubActivity.drawer.Mode;
import org.ice1000.clubActivity.drawer.SimpleData;
import org.jetbrains.annotations.NotNull;

public class Demo1
{
	//World Cup 2018 Group A
	public static void main(@NotNull String @NotNull ... args)
	{
		new Drawer<>("Wins", "Country")
				.column(new SimpleData("Brazil", 2))
				.column(new SimpleData("Croatia", 1))
				.column(new SimpleData("Mexico", 2))
				.column(new SimpleData("Cameroon", 0))
				.mode(Mode.Histogram)
				.showInWindow(512, 512)
				.mode(Mode.LineChart)
				.showInWindow(512, 512);
		new Drawer<>("Losses", "Country")
				.column(new SimpleData("Brazil", 0))
				.column(new SimpleData("Croatia", 2))
				.column(new SimpleData("Mexico", 0))
				.column(new SimpleData("Cameroon", 3))
				.mode(Mode.Histogram)
				.showInWindow(512, 512)
				.mode(Mode.LineChart)
				.showInWindow(512, 512);
	}
}
