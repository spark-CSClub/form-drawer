package org.eulir.demo;

import org.ice1000.clubActivity.drawer.*;
import org.jetbrains.annotations.NotNull;

public class Demo2 {
	//2017-2018 NBA Western Conference

	private static final int totalGames = 82;

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
		return new Drawer<>("Records", "Team")
				.column(new LayeredData("Rockets", totalGames - 67, 67))
				.column(new LayeredData("Warriors", totalGames - 58, 58))
				.column(new LayeredData("Trail Blazers", totalGames - 49, 49))
				.column(new LayeredData("Thunders", totalGames - 48, 48))
				.column(new LayeredData("Jazz", totalGames - 48, 48))
				.column(new LayeredData("Pelicans", totalGames - 48, 48))
				.column(new LayeredData("Spurs", totalGames - 47, 47))
				.column(new LayeredData("Timberwolves", totalGames - 47, 47))
				.column(new LayeredData("Nuggets", totalGames - 46, 46))
				.column(new LayeredData("Clippers", totalGames - 42, 42))
				.column(new LayeredData("Lakers", totalGames - 35, 35))
				.column(new LayeredData("Kings", totalGames - 27, 27))
				.column(new LayeredData("Mavericks", totalGames - 24, 24))
				.column(new LayeredData("Grizzlies", totalGames - 22, 22))
				.column(new LayeredData("Suns", totalGames - 21, 21));
	}

	private static @NotNull Drawer<@NotNull AbstractData> createLossesDrawer() {
		return createDrawer("Losses",
				totalGames - 67,
				totalGames - 58,
				totalGames - 49,
				totalGames - 48,
				totalGames - 47,
				totalGames - 46,
				totalGames - 42,
				totalGames - 35,
				totalGames - 27,
				totalGames - 24,
				totalGames - 22,
				totalGames - 21);
	}

	private static @NotNull Drawer<@NotNull AbstractData> createWinsDrawer() {
		return createDrawer("Wins", 67, 58, 49, 48, 47, 46, 42, 35, 27, 24, 22, 21);
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
	                                                                   int i11,
	                                                                   int i12) {
		return new Drawer<>(wins, "Team")
				.column(new SimpleData("Rockets", i))
				.column(new SimpleData("Warriors", i2))
				.column(new SimpleData("Trail Blazers", i3))
				.column(new SimpleData("Thunders", i4))
				.column(new SimpleData("Jazz", i4))
				.column(new SimpleData("Pelicans", i4))
				.column(new SimpleData("Spurs", i5))
				.column(new SimpleData("Timberwolves", i5))
				.column(new SimpleData("Nuggets", i6))
				.column(new SimpleData("Clippers", i7))
				.column(new SimpleData("Lakers", i8))
				.column(new SimpleData("Kings", i9))
				.column(new SimpleData("Mavericks", i10))
				.column(new SimpleData("Grizzlies", i11))
				.column(new SimpleData("Suns", i12));
	}
}
