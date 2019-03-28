package com.google.zxing.datamatrix.encoder;

class C40Encoder implements Encoder {
    public int getEncodingMode() {
        return 1;
    }

    C40Encoder() {
    }

    public void encode(EncoderContext encoderContext) {
        StringBuilder stringBuilder = new StringBuilder();
        while (encoderContext.hasMoreCharacters()) {
            char currentChar = encoderContext.getCurrentChar();
            encoderContext.pos++;
            int encodeChar = encodeChar(currentChar, stringBuilder);
            int codewordCount = encoderContext.getCodewordCount() + ((stringBuilder.length() / 3) * 2);
            encoderContext.updateSymbolInfo(codewordCount);
            int dataCapacity = encoderContext.getSymbolInfo().getDataCapacity() - codewordCount;
            if (!encoderContext.hasMoreCharacters()) {
                StringBuilder stringBuilder2 = new StringBuilder();
                if (stringBuilder.length() % 3 == 2 && (dataCapacity < 2 || dataCapacity > 2)) {
                    encodeChar = backtrackOneCharacter(encoderContext, stringBuilder, stringBuilder2, encodeChar);
                }
                while (stringBuilder.length() % 3 == 1 && ((encodeChar <= 3 && dataCapacity != 1) || encodeChar > 3)) {
                    encodeChar = backtrackOneCharacter(encoderContext, stringBuilder, stringBuilder2, encodeChar);
                }
            } else if (stringBuilder.length() % 3 == 0) {
                encodeChar = HighLevelEncoder.lookAheadTest(encoderContext.getMessage(), encoderContext.pos, getEncodingMode());
                if (encodeChar != getEncodingMode()) {
                    encoderContext.signalEncoderChange(encodeChar);
                    break;
                }
            }
        }
        handleEOD(encoderContext, stringBuilder);
    }

    private int backtrackOneCharacter(EncoderContext encoderContext, StringBuilder stringBuilder, StringBuilder stringBuilder2, int i) {
        int length = stringBuilder.length();
        stringBuilder.delete(length - i, length);
        encoderContext.pos--;
        stringBuilder = encodeChar(encoderContext.getCurrentChar(), stringBuilder2);
        encoderContext.resetSymbolInfo();
        return stringBuilder;
    }

    static void writeNextTriplet(EncoderContext encoderContext, StringBuilder stringBuilder) {
        encoderContext.writeCodewords(encodeToCodewords(stringBuilder, 0));
        stringBuilder.delete(0, 3);
    }

    void handleEOD(EncoderContext encoderContext, StringBuilder stringBuilder) {
        int length = stringBuilder.length() % 3;
        int codewordCount = encoderContext.getCodewordCount() + ((stringBuilder.length() / 3) * 2);
        encoderContext.updateSymbolInfo(codewordCount);
        int dataCapacity = encoderContext.getSymbolInfo().getDataCapacity() - codewordCount;
        if (length == 2) {
            stringBuilder.append('\u0000');
            while (stringBuilder.length() >= 3) {
                writeNextTriplet(encoderContext, stringBuilder);
            }
            if (encoderContext.hasMoreCharacters() != null) {
                encoderContext.writeCodeword('þ');
            }
        } else if (dataCapacity == 1 && length == 1) {
            while (stringBuilder.length() >= 3) {
                writeNextTriplet(encoderContext, stringBuilder);
            }
            if (encoderContext.hasMoreCharacters() != null) {
                encoderContext.writeCodeword('þ');
            }
            encoderContext.pos -= 1;
        } else if (length == 0) {
            while (stringBuilder.length() >= 3) {
                writeNextTriplet(encoderContext, stringBuilder);
            }
            if (dataCapacity > 0 || encoderContext.hasMoreCharacters() != null) {
                encoderContext.writeCodeword('þ');
            }
        } else {
            throw new IllegalStateException("Unexpected case. Please report!");
        }
        encoderContext.signalEncoderChange(0);
    }

    int encodeChar(char c, StringBuilder stringBuilder) {
        if (c == ' ') {
            stringBuilder.append('\u0003');
            return 1;
        } else if (c >= '0' && c <= '9') {
            stringBuilder.append((char) ((c - '0') + 4));
            return 1;
        } else if (c >= 'A' && c <= 'Z') {
            stringBuilder.append((char) ((c - 'A') + 14));
            return 1;
        } else if (c >= '\u0000' && c <= '\u001f') {
            stringBuilder.append('\u0000');
            stringBuilder.append(c);
            return 2;
        } else if (c >= '!' && c <= '/') {
            stringBuilder.append('\u0001');
            stringBuilder.append((char) (c - '!'));
            return 2;
        } else if (c >= ':' && c <= '@') {
            stringBuilder.append('\u0001');
            stringBuilder.append((char) ((c - ':') + 15));
            return 2;
        } else if (c >= '[' && c <= '_') {
            stringBuilder.append('\u0001');
            stringBuilder.append((char) ((c - '[') + 22));
            return 2;
        } else if (c >= '`' && c <= '') {
            stringBuilder.append('\u0002');
            stringBuilder.append((char) (c - '`'));
            return 2;
        } else if (c >= '') {
            stringBuilder.append("\u0001\u001e");
            return encodeChar((char) (c - ''), stringBuilder) + '\u0002';
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Illegal character: ");
            stringBuilder2.append(c);
            throw new IllegalArgumentException(stringBuilder2.toString());
        }
    }

    private static String encodeToCodewords(CharSequence charSequence, int i) {
        char charAt = (char) (((((charSequence.charAt(i) * 1600) + (charSequence.charAt(i + 1) * 40)) + charSequence.charAt(i + 2)) + 1) % 256);
        return new String(new char[]{(char) (r0 / 256), charAt});
    }
}
