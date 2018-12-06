package org.eulir.demo;

import org.ice1000.clubActivity.drawer.Drawer;
import org.ice1000.clubActivity.drawer.Mode;
import org.ice1000.clubActivity.drawer.SimpleData;

import java.awt.*;

public class GrpcIO {
	public static void main(String[] args) {
		qps();
	}

	public static void latency() {
		graphic(209.7, 449.6, "rust_generic_async_streaming_ping_pong", "us");
		graphic(615.4, 844.2, "rust_protobuf_async_streaming_ping_pong", "us");
		graphic(441.9, 915.6, "rust_protobuf_async_unary_ping_pong", "us");
		graphic(858.8, 1003.9, "rust_protobuf_sync_to_async_unary_ping_pong", "us");
		graphic(195478.8, 173330.2, "rust_protobuf_async_unary_qps_unconstrained", "us");
		graphic(31599.1, 30752.5, "rust_protobuf_async_streaming_qps_unconstrained", "us");
		graphic(89567.8, 91349.9, "rust_protobuf_async_unary_ping_pong_1MB", "us");
	}

	public static void total() {
		graphic(35.4, 35.4, "rust_generic_async_streaming_ping_pong", "sec");
		graphic(35.3, 35.3, "rust_protobuf_async_streaming_ping_pong", "sec");
		graphic(35.5, 35.3, "rust_protobuf_async_unary_ping_pong", "sec");
		graphic(35.4, 35.3, "rust_protobuf_sync_to_async_unary_ping_pong", "sec");
		graphic(35.4, 35.6, "rust_protobuf_async_unary_qps_unconstrained", "sec");
		graphic(36.8, 36.8, "rust_protobuf_async_streaming_qps_unconstrained", "sec");
		graphic(35.1, 35.3, "rust_protobuf_async_unary_ping_pong_1MB", "sec");
	}

	public static void qps() {
		graphic(11064.8, 9835.8, "rust_generic_async_streaming_ping_pong", "times");
		graphic(8865.2, 7096.6, "rust_protobuf_async_streaming_ping_pong", "times");
		graphic(6215.1, 5508.0, "rust_protobuf_async_unary_ping_pong", "times");
		graphic(4970.6, 4602.1, "rust_protobuf_sync_to_async_unary_ping_pong", "times");
		graphic(233423.3, 239730.9, "rust_protobuf_async_unary_qps_unconstrained", "times");
		graphic(504370.4, 530950.2, "rust_protobuf_async_streaming_qps_unconstrained", "times");
		graphic(16.0, 16.1, "rust_protobuf_async_unary_ping_pong_1MB", "times");
	}

	private static void graphic(double zcpVal, double masterVal, String name, String unit) {
		double commonVal = Math.min(zcpVal, masterVal) * 0.9;
		new Drawer<>("Case", unit, name)
				.column(new SimpleData("zcp", zcpVal - commonVal))
				.column(new SimpleData("master", masterVal - commonVal))
				.colors(Color.BLUE, Color.ORANGE)
				.yStart(commonVal)
				.mode(Mode.Histogram)
				.font(new Font(Demo1.fontName, Font.PLAIN, 16))
//				.writeToFile(name + ".png", 412, 512);
				.showInWindow(412, 512);
	}
}
