package com.mevo.app.constants;

public class BikeTypes {
    public static final String BIKE_HELMET = "12";
    public static final String BIKE_TRAILER = "10";
    public static final String CHILD_SEAT = "11";
    public static final String CLASSIC = "5";
    public static final String COMFORT = "4";
    public static final String COMFORT_1_1_8 = "14";
    public static final String COMFORT_1_1_8_COMPUTER = "15";
    public static final String DEFAULT = "0";
    public static final String E_BIKE = "17";
    public static final String KIDS_BIKE_20 = "7";
    public static final String KIDS_BIKE_24 = "8";
    public static final String PEDELEC = "6";
    public static final String RACK_PARKING_SPOT = "16";
    public static final String TANDEM = "9";
    public static final String TANDEM_POLAND = "18";
    public static final String TOURING_BIKE_26 = "1";
    public static final String TOURING_BIKE_28 = "2";
    public static final String UNDEFINED = "undefined";
    public static final String USE_BIKE = "3";
    public static final String USE_BIKE_CLASSIC = "13";
    public static final String VP_ELECTRIC = "37";
    public static final String VP_KIDS_4 = "34";
    public static final String VP_KIDS_6 = "35";
    public static final String VP_TANDEM = "26";

    public enum BikeGroup {
        STANDARD,
        SMARTBIKE,
        KIDS_4,
        KIDS_6,
        TANDEM
    }

    public static BikeGroup getBikeGroupByBikeNumber(int i) {
        if (i >= 24001 && i <= 24100) {
            return BikeGroup.TANDEM;
        }
        if (i >= 24101 && i <= 24250) {
            return BikeGroup.SMARTBIKE;
        }
        if (i >= 24251 && i <= 24350) {
            return BikeGroup.KIDS_4;
        }
        if (i < 24351 || i > 24450) {
            return BikeGroup.STANDARD;
        }
        return BikeGroup.KIDS_4;
    }

    public static BikeGroup getBikeGroupByBikeType(String str) {
        if (str == null) {
            return BikeGroup.STANDARD;
        }
        Object obj = -1;
        switch (str.hashCode()) {
            case 54:
                if (str.equals(PEDELEC) != null) {
                    obj = 7;
                    break;
                }
                break;
            case 55:
                if (str.equals(KIDS_BIKE_20) != null) {
                    obj = null;
                    break;
                }
                break;
            case 56:
                if (str.equals(KIDS_BIKE_24) != null) {
                    obj = 2;
                    break;
                }
                break;
            case 57:
                if (str.equals(TANDEM) != null) {
                    obj = 9;
                    break;
                }
                break;
            case 1571:
                if (str.equals(COMFORT_1_1_8) != null) {
                    obj = 4;
                    break;
                }
                break;
            case 1572:
                if (str.equals(COMFORT_1_1_8_COMPUTER) != null) {
                    obj = 5;
                    break;
                }
                break;
            case 1574:
                if (str.equals(E_BIKE) != null) {
                    obj = 6;
                    break;
                }
                break;
            case 1575:
                if (str.equals(TANDEM_POLAND) != null) {
                    obj = 10;
                    break;
                }
                break;
            case 1604:
                if (str.equals("26") != null) {
                    obj = 11;
                    break;
                }
                break;
            case 1633:
                if (str.equals(VP_KIDS_4) != null) {
                    obj = 1;
                    break;
                }
                break;
            case 1634:
                if (str.equals(VP_KIDS_6) != null) {
                    obj = 3;
                    break;
                }
                break;
            case 1636:
                if (str.equals(VP_ELECTRIC) != null) {
                    obj = 8;
                    break;
                }
                break;
            default:
                break;
        }
        switch (obj) {
            case null:
            case 1:
                return BikeGroup.KIDS_4;
            case 2:
            case 3:
                return BikeGroup.KIDS_6;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return BikeGroup.SMARTBIKE;
            case 9:
            case 10:
            case 11:
                return BikeGroup.TANDEM;
            default:
                return BikeGroup.STANDARD;
        }
    }
}
