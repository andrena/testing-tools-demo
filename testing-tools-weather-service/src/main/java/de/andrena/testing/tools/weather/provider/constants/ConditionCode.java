package de.andrena.testing.tools.weather.provider.constants;

import static de.andrena.testing.tools.weather.provider.constants.TemperatureSpec.*;

public enum ConditionCode {

	TORNADO (0, "Tornado", WET),
	TROPICAL_STORM (1, "Tropensturm", WET),
	HURRICANE(2, "Hurrikan", WET),
	SEVERE_THUNDERSTORMS(3, "Schwere Gewitter", WET),
	THUNDERSTORMS(4, "Gewitter", WET),
	MIXED_RAIN_AND_SNOW(5,"Schneeregen", COOL),
	MIXED_RAIN_AND_SLEET(6, "Regen und Graupel", COOL),
	MIXED_SNOW_AND_SLEET(7, "Schnee und Graupel", COOL),
	FREEZING_DRIZZLE(8, "Gefrierender Nieselregen", COOL),
	DRIZZLE(9, "Nieselregen", WET),
	FREEZING_DRIZZLE_2(10, "Gefrierender Regen", COOL ),
	SHOWERS(11, "Schauer", WET),
	SHOWERS_2(12, "Schauer", WET),
	SNOW_FLURRIES(13, "Schneetreiben", COOL),
	LIGHT_SNOW_SHOWERS(14, "Schneeschauer", COOL),
	BLOWING_SNOW(15, "Schneeverwehungen", COOL),
	SNOW(16, "Schnee", COOL),
	HAIL(17, "Hagel", COOL),
	SLEET(18, "Schneeregen", COOL),
	DUST(19, "Dunst", WET),
	FOGGY(20, "Nebel", WET),
	HAZE(21, "Nebel", WET),
	SMOKY(22, "Smog", WARM),
	BLUSTERY(23, "stürmisch", WET),
	WINDY(24, "windig", WET),
	COLD(25, "kalt", COOL),
	CLOUDY(26, "bewölkt", NEUTRAL),
	MOSTLY_CLOUDY_NIGHT( 27, "Meist bewölkt", NEUTRAL),
	MOSTLY_CLOUDY_DAY(28, "Meist bewölkt", NEUTRAL),
	PARTLY_CLOUDY_NIHGT(29, "Teilweise bewölkt", NEUTRAL),
	PARTLY_CLOUDY_DAY(30, "Teilweise bewölkt", NEUTRAL),
	CLEAR_NIGHT (31, "Klar (Nacht)", NEUTRAL),
	SUNNY(32,"Sonnig", WARM),
	FAIR_NIGHT (33, "Heiter (Nacht)", WARM),
	FAIR_DAY (34, "Heiter (Tag)", WARM),
	MIXED_RAIN_AND_HAIL (35, "Hagelregen", COOL),
	HOT(36, "Warm", WARM),
	ISOLATED_THUNDERSTORMS (37, "Vereinzelte Gewitter", WET),
	SCATTERED_THUNDERSTORMS (38, "Vereinzelte Gewitter", WET),
	SCATTERED_THUNDERSTORMS_2 (39, "Vereinzelte Gewitter", WET),
	SCATTERED_SHOWERS (40, "Vereinzelte Schauer", WET),
	HEAVY_SNOW (41, "Schwere Schneefälle", COOL),
	SCATTERED_SNOW_SHOWERS (42, "vereinzelt Schneeschauer", COOL),
	HEAVY_SNOW_2 (43, "Schwere Schneefälle", COOL),
	PARTLY_CLOUDY (44, "Wechselnd bewölkt", NEUTRAL),
	THUNDERSHOWERS (45, "Gewitter", WET),
	SNOW_SHOWERS (46, "Schneeschauer", COOL),
	ISOLATED_THUNDERSHOWERS (47, "Leichte Gewitterschauer", WET),
	NOT_AVAILABLE (3200, "nicht verfügbar", NEUTRAL);
	
	private int code;
	private String name;
	private TemperatureSpec temparature;
	
	private ConditionCode(int code, String name, TemperatureSpec temparature) {
		this.code = code;
		this.name = name;
		this.temparature = temparature;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public TemperatureSpec getTemperatureSpec() {
		return temparature;
	}
	
	public static ConditionCode fromCode(int code) {
		for (ConditionCode conditionCode : ConditionCode.values()) {
			if(conditionCode.getCode() == code) {
				return conditionCode;
			}
		}
		return NOT_AVAILABLE;
	}
	
}
