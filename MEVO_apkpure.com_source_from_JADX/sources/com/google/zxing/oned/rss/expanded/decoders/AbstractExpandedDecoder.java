package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
import com.mevo.app.constants.BikeTypes;

public abstract class AbstractExpandedDecoder {
    private final GeneralAppIdDecoder generalDecoder;
    private final BitArray information;

    public abstract String parseInformation() throws NotFoundException, FormatException;

    AbstractExpandedDecoder(BitArray bitArray) {
        this.information = bitArray;
        this.generalDecoder = new GeneralAppIdDecoder(bitArray);
    }

    protected final BitArray getInformation() {
        return this.information;
    }

    protected final GeneralAppIdDecoder getGeneralDecoder() {
        return this.generalDecoder;
    }

    public static AbstractExpandedDecoder createDecoder(BitArray bitArray) {
        if (bitArray.get(1)) {
            return new AI01AndOtherAIs(bitArray);
        }
        if (!bitArray.get(2)) {
            return new AnyAIDecoder(bitArray);
        }
        switch (GeneralAppIdDecoder.extractNumericValueFromBitArray(bitArray, 1, 4)) {
            case 4:
                return new AI013103decoder(bitArray);
            case 5:
                return new AI01320xDecoder(bitArray);
            default:
                switch (GeneralAppIdDecoder.extractNumericValueFromBitArray(bitArray, 1, 5)) {
                    case 12:
                        return new AI01392xDecoder(bitArray);
                    case 13:
                        return new AI01393xDecoder(bitArray);
                    default:
                        switch (GeneralAppIdDecoder.extractNumericValueFromBitArray(bitArray, 1, 7)) {
                            case 56:
                                return new AI013x0x1xDecoder(bitArray, "310", BikeTypes.CHILD_SEAT);
                            case 57:
                                return new AI013x0x1xDecoder(bitArray, "320", BikeTypes.CHILD_SEAT);
                            case 58:
                                return new AI013x0x1xDecoder(bitArray, "310", BikeTypes.USE_BIKE_CLASSIC);
                            case 59:
                                return new AI013x0x1xDecoder(bitArray, "320", BikeTypes.USE_BIKE_CLASSIC);
                            case 60:
                                return new AI013x0x1xDecoder(bitArray, "310", BikeTypes.COMFORT_1_1_8_COMPUTER);
                            case 61:
                                return new AI013x0x1xDecoder(bitArray, "320", BikeTypes.COMFORT_1_1_8_COMPUTER);
                            case 62:
                                return new AI013x0x1xDecoder(bitArray, "310", BikeTypes.E_BIKE);
                            case 63:
                                return new AI013x0x1xDecoder(bitArray, "320", BikeTypes.E_BIKE);
                            default:
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("unknown decoder: ");
                                stringBuilder.append(bitArray);
                                throw new IllegalStateException(stringBuilder.toString());
                        }
                }
        }
    }
}
