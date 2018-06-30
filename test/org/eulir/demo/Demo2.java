package org.eulir.demo;

import org.ice1000.clubActivity.drawer.Drawer;
import org.ice1000.clubActivity.drawer.LayeredData;
import org.ice1000.clubActivity.drawer.Mode;
import org.ice1000.clubActivity.drawer.SimpleData;
import org.jetbrains.annotations.NotNull;

public class Demo2
{
	//2017-2018 NBA Western Conference

	private static final int totalGames = 82;

	public static void main(@NotNull String @NotNull ... args)
	{
		new Drawer<>("Wins", "Team")
				.column(new SimpleData("Rockets", 67))
				.column(new SimpleData("Warriors", 58))
				.column(new SimpleData("Trail Blazers", 49))
				.column(new SimpleData("Thunders", 48))
				.column(new SimpleData("Jazz", 48))
				.column(new SimpleData("Pelicans", 48))
				.column(new SimpleData("Spurs", 47))
				.column(new SimpleData("Timberwolves", 47))
				.column(new SimpleData("Nuggets", 46))
				.column(new SimpleData("Clippers", 42))
				.column(new SimpleData("Lakers", 35))
				.column(new SimpleData("Kings", 27))
				.column(new SimpleData("Mavericks", 24))
				.column(new SimpleData("Grizzlies", 22))
				.column(new SimpleData("Suns", 21))
				.mode(Mode.Histogram)
				.showInWindow(1200, 1200)
				.mode(Mode.LineChart)
				.showInWindow(1200, 1200);

		new Drawer<>("Losses", "Team")
				.column(new SimpleData("Rockets", totalGames - 67))
				.column(new SimpleData("Warriors", totalGames - 58))
				.column(new SimpleData("Trail Blazers", totalGames - 49))
				.column(new SimpleData("Thunders", totalGames - 48))
				.column(new SimpleData("Jazz", totalGames - 48))
				.column(new SimpleData("Pelicans", totalGames - 48))
				.column(new SimpleData("Spurs", totalGames - 47))
				.column(new SimpleData("Timberwolves", totalGames - 47))
				.column(new SimpleData("Nuggets", totalGames - 46))
				.column(new SimpleData("Clippers", totalGames - 42))
				.column(new SimpleData("Lakers", totalGames - 35))
				.column(new SimpleData("Kings", totalGames - 27))
				.column(new SimpleData("Mavericks", totalGames - 24))
				.column(new SimpleData("Grizzlies", totalGames - 22))
				.column(new SimpleData("Suns", totalGames - 21))
				.mode(Mode.Histogram)
				.showInWindow(1200, 1200)
				.mode(Mode.LineChart)
				.showInWindow(1200, 1200);

		new Drawer<>("Records", "Team")
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
				.column(new LayeredData("Suns", totalGames - 21, 21))
				.mode(Mode.Histogram)
				.showInWindow(1200, 1200)
				.mode(Mode.LineChart)
				.showInWindow(1200, 1200);
	}
}
