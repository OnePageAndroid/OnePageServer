package kr.nexters.onepage.domain.calculate;

public class LatLngCalculator {
	private LatLngCalculator() {
	}

	/**
	 * 두 지점간의 거리 계산 ( 거리단위 : 미터 )
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return
	 */
	public static double meterDistance(double lat1, double lon1, double lat2, double lon2) {
		double theta, dist;
		theta = lon1 - lon2;
		dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))
			* Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;    // 단위 mile 에서 km 변환.
		dist = dist * 1000.0;      // 단위  km 에서 m 로 변환
		return dist;
	}

	// 주어진 도(degree) 값을 라디언으로 변환
	private static double deg2rad(double deg){
		return (double)(deg * Math.PI / (double)180d);
	}

	// 주어진 라디언(radian) 값을 도(degree) 값으로 변환
	private static double rad2deg(double rad){
		return (double)(rad * (double)180d / Math.PI);
	}
}
