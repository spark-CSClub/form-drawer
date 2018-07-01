package org.eulir.demo;

import org.ice1000.clubActivity.drawer.*;
import org.jetbrains.annotations.NotNull;

public class Demo3 {
	private static final int totalGames = 35;

	//NCAA Men's Basketball Div1
	//Last Updated- March 12, 2018
	public static void main(@NotNull String @NotNull ... args) {
		createWinsDrawer()
				.mode(Mode.Histogram)
				.showInWindow(1200, 1200);
		createWinsDrawer()
				.mode(Mode.LineChart)
				.showInWindow(1200, 1200);

		createLossesDrawer()
				.mode(Mode.Histogram)
				.showInWindow(1200, 1200);
		createLossesDrawer()
				.mode(Mode.LineChart)
				.showInWindow(1200, 1200);

		createRecordsDrawer()
				.mode(Mode.Histogram)
				.showInWindow(1200, 1200);
		createRecordsDrawer()
				.mode(Mode.LineChart)
				.showInWindow(1200, 1200);
	}

	private static @NotNull Drawer<@NotNull AbstractData> createRecordsDrawer() {
		return new Drawer<>("Team", "Records", "NCAA Men's Basketball Div1 Records")
				.column(new LayeredData("Virginia", totalGames - 31, 31))
				.column(new LayeredData("Villanova", totalGames - 30, 30))
				.column(new LayeredData("Xavier", totalGames - 28, 28))
				.column(new LayeredData("Kansas", totalGames - 27, 27))
				.column(new LayeredData("Michigan State", totalGames - 29, 29))
				.column(new LayeredData("Cincinnati", totalGames - 30, 30))
				.column(new LayeredData("Michigan", totalGames - 28, 28))
				.column(new LayeredData("Gonzaga", totalGames - 30, 30))
				.column(new LayeredData("Duke", totalGames - 26, 26))
				.column(new LayeredData("North Carolina", totalGames - 25, 25))
				.column(new LayeredData("Purdue", totalGames - 28, 28));
	}

	private static @NotNull Drawer<@NotNull AbstractData> createLossesDrawer() {
		return createDrawer("Losses",
				totalGames - 31,
				totalGames - 30,
				totalGames - 28,
				totalGames - 27,
				totalGames - 29,
				totalGames - 30,
				totalGames - 28,
				totalGames - 30,
				totalGames - 26,
				totalGames - 25,
				totalGames - 28);
	}

	private static @NotNull Drawer<@NotNull AbstractData> createWinsDrawer() {
		return createDrawer("Wins", 31, 30, 28, 27, 29, 30, 28, 30, 26, 25, 28);
	}

	private static @NotNull Drawer<@NotNull AbstractData> createDrawer(String wins,
	                                                                   int i,
	                                                                   int i2,
	                                                                   int i3,
	                                                                   int i4,
	                                                                   int i5,
	                                                                   int i6,
	                                                                   int i7,
	                                                                   int i8,
	                                                                   int i9,
	                                                                   int i10,
	                                                                   int i11) {
		return new Drawer<>("Team", wins, "NCAA Men's Basketball Div1" + wins)
				.column(new SimpleData("Virginia", i))
				.column(new SimpleData("Villanova", i2))
				.column(new SimpleData("Xavier", i3))
				.column(new SimpleData("Kansas", i4))
				.column(new SimpleData("Michigan State", i5))
				.column(new SimpleData("Cincinnati", i6))
				.column(new SimpleData("Michigan", i7))
				.column(new SimpleData("Gonzaga", i8))
				.column(new SimpleData("Duke", i9))
				.column(new SimpleData("North Carolina", i10))
				.column(new SimpleData("Purdue", i11));
	}
}
