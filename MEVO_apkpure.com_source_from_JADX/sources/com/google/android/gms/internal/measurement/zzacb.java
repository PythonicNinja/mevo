package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzacb {
    private final ByteBuffer zzbzc;

    private zzacb(ByteBuffer byteBuffer) {
        this.zzbzc = byteBuffer;
        this.zzbzc.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzacb(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    private static int zza(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < '') {
            i2++;
        }
        int i3 = length;
        while (i2 < length) {
            StringBuilder stringBuilder;
            char charAt = charSequence.charAt(i2);
            if (charAt < 'ࠀ') {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 'ࠀ') {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if ('?' <= charAt2 && charAt2 <= '?') {
                            if (Character.codePointAt(charSequence, i2) < 65536) {
                                stringBuilder = new StringBuilder(39);
                                stringBuilder.append("Unpaired surrogate at index ");
                                stringBuilder.append(i2);
                                throw new IllegalArgumentException(stringBuilder.toString());
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i3 += i;
                if (i3 < length) {
                    return i3;
                }
                long j = ((long) i3) + 4294967296L;
                stringBuilder = new StringBuilder(54);
                stringBuilder.append("UTF-8 length does not fit in int: ");
                stringBuilder.append(j);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
        }
        if (i3 < length) {
            return i3;
        }
        long j2 = ((long) i3) + 4294967296L;
        stringBuilder = new StringBuilder(54);
        stringBuilder.append("UTF-8 length does not fit in int: ");
        stringBuilder.append(j2);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    private static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        int i = 0;
        int arrayOffset;
        int remaining;
        char charAt;
        if (byteBuffer.hasArray()) {
            try {
                int i2;
                byte[] array = byteBuffer.array();
                arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
                remaining = byteBuffer.remaining();
                int length = charSequence.length();
                remaining += arrayOffset;
                while (i < length) {
                    i2 = i + arrayOffset;
                    if (i2 >= remaining) {
                        break;
                    }
                    char charAt2 = charSequence.charAt(i);
                    if (charAt2 >= '') {
                        break;
                    }
                    array[i2] = (byte) charAt2;
                    i++;
                }
                if (i == length) {
                    arrayOffset += length;
                } else {
                    arrayOffset += i;
                    while (i < length) {
                        int i3;
                        char charAt3 = charSequence.charAt(i);
                        if (charAt3 < '' && arrayOffset < remaining) {
                            i3 = arrayOffset + 1;
                            array[arrayOffset] = (byte) charAt3;
                        } else if (charAt3 < 'ࠀ' && arrayOffset <= remaining - 2) {
                            i3 = arrayOffset + 1;
                            array[arrayOffset] = (byte) ((charAt3 >>> 6) | 960);
                            arrayOffset = i3 + 1;
                            array[i3] = (byte) ((charAt3 & 63) | 128);
                            i++;
                        } else if ((charAt3 < '?' || '?' < charAt3) && arrayOffset <= remaining - 3) {
                            i3 = arrayOffset + 1;
                            array[arrayOffset] = (byte) ((charAt3 >>> 12) | 480);
                            arrayOffset = i3 + 1;
                            array[i3] = (byte) (((charAt3 >>> 6) & 63) | 128);
                            i3 = arrayOffset + 1;
                            array[arrayOffset] = (byte) ((charAt3 & 63) | 128);
                        } else if (arrayOffset <= remaining - 4) {
                            i3 = i + 1;
                            if (i3 != charSequence.length()) {
                                charAt = charSequence.charAt(i3);
                                if (Character.isSurrogatePair(charAt3, charAt)) {
                                    i = Character.toCodePoint(charAt3, charAt);
                                    i2 = arrayOffset + 1;
                                    array[arrayOffset] = (byte) ((i >>> 18) | 240);
                                    arrayOffset = i2 + 1;
                                    array[i2] = (byte) (((i >>> 12) & 63) | 128);
                                    i2 = arrayOffset + 1;
                                    array[arrayOffset] = (byte) (((i >>> 6) & 63) | 128);
                                    arrayOffset = i2 + 1;
                                    array[i2] = (byte) ((i & 63) | 128);
                                    i = i3;
                                    i++;
                                } else {
                                    i = i3;
                                }
                            }
                            i--;
                            StringBuilder stringBuilder = new StringBuilder(39);
                            stringBuilder.append("Unpaired surrogate at index ");
                            stringBuilder.append(i);
                            throw new IllegalArgumentException(stringBuilder.toString());
                        } else {
                            StringBuilder stringBuilder2 = new StringBuilder(37);
                            stringBuilder2.append("Failed writing ");
                            stringBuilder2.append(charAt3);
                            stringBuilder2.append(" at index ");
                            stringBuilder2.append(arrayOffset);
                            throw new ArrayIndexOutOfBoundsException(stringBuilder2.toString());
                        }
                        arrayOffset = i3;
                        i++;
                    }
                }
                byteBuffer.position(arrayOffset - byteBuffer.arrayOffset());
                return;
            } catch (Throwable e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        }
        int length2 = charSequence.length();
        while (i < length2) {
            arrayOffset = charSequence.charAt(i);
            if (arrayOffset >= 128) {
                if (arrayOffset < 2048) {
                    remaining = (arrayOffset >>> 6) | 960;
                } else {
                    if (arrayOffset >= 55296) {
                        if (57343 >= arrayOffset) {
                            remaining = i + 1;
                            if (remaining != charSequence.length()) {
                                charAt = charSequence.charAt(remaining);
                                if (Character.isSurrogatePair(arrayOffset, charAt)) {
                                    i = Character.toCodePoint(arrayOffset, charAt);
                                    byteBuffer.put((byte) ((i >>> 18) | 240));
                                    byteBuffer.put((byte) (((i >>> 12) & 63) | 128));
                                    byteBuffer.put((byte) (((i >>> 6) & 63) | 128));
                                    byteBuffer.put((byte) ((i & 63) | 128));
                                    i = remaining;
                                    i++;
                                } else {
                                    i = remaining;
                                }
                            }
                            i--;
                            stringBuilder = new StringBuilder(39);
                            stringBuilder.append("Unpaired surrogate at index ");
                            stringBuilder.append(i);
                            throw new IllegalArgumentException(stringBuilder.toString());
                        }
                    }
                    byteBuffer.put((byte) ((arrayOffset >>> 12) | 480));
                    remaining = ((arrayOffset >>> 6) & 63) | 128;
                }
                byteBuffer.put((byte) remaining);
                arrayOffset = (arrayOffset & 63) | 128;
            }
            byteBuffer.put((byte) arrayOffset);
            i++;
        }
    }

    public static int zzao(int i) {
        return i >= 0 ? zzas(i) : 10;
    }

    private final void zzap(int i) throws IOException {
        byte b = (byte) i;
        if (this.zzbzc.hasRemaining()) {
            this.zzbzc.put(b);
            return;
        }
        throw new zzacc(this.zzbzc.position(), this.zzbzc.limit());
    }

    public static int zzaq(int i) {
        return zzas(i << 3);
    }

    public static int zzas(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (i & -268435456) == 0 ? 4 : 5;
    }

    private final void zzas(long j) throws IOException {
        while ((j & -128) != 0) {
            zzap((((int) j) & 127) | 128);
            j >>>= 7;
        }
        zzap((int) j);
    }

    public static int zzat(long j) {
        return (j & -128) == 0 ? 1 : (j & -16384) == 0 ? 2 : (j & -2097152) == 0 ? 3 : (j & -268435456) == 0 ? 4 : (j & -34359738368L) == 0 ? 5 : (j & -4398046511104L) == 0 ? 6 : (j & -562949953421312L) == 0 ? 7 : (j & -72057594037927936L) == 0 ? 8 : (j & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    public static int zzb(int i, zzacj zzacj) {
        i = zzaq(i);
        int zzwb = zzacj.zzwb();
        return i + (zzas(zzwb) + zzwb);
    }

    public static zzacb zzb(byte[] bArr, int i, int i2) {
        return new zzacb(bArr, 0, i2);
    }

    public static int zzc(int i, long j) {
        return zzaq(i) + zzat(j);
    }

    public static int zzc(int i, String str) {
        return zzaq(i) + zzfr(str);
    }

    public static int zzf(int i, int i2) {
        return zzaq(i) + zzao(i2);
    }

    public static int zzfr(String str) {
        int zza = zza(str);
        return zzas(zza) + zza;
    }

    public static zzacb zzj(byte[] bArr) {
        return zzb(bArr, 0, bArr.length);
    }

    public final void zza(int i, double d) throws IOException {
        zzg(i, 1);
        long doubleToLongBits = Double.doubleToLongBits(d);
        if (this.zzbzc.remaining() < 8) {
            throw new zzacc(this.zzbzc.position(), this.zzbzc.limit());
        }
        this.zzbzc.putLong(doubleToLongBits);
    }

    public final void zza(int i, float f) throws IOException {
        zzg(i, 5);
        i = Float.floatToIntBits(f);
        if (this.zzbzc.remaining() < 4) {
            throw new zzacc(this.zzbzc.position(), this.zzbzc.limit());
        }
        this.zzbzc.putInt(i);
    }

    public final void zza(int i, long j) throws IOException {
        zzg(i, 0);
        zzas(j);
    }

    public final void zza(int i, zzacj zzacj) throws IOException {
        zzg(i, 2);
        zzb(zzacj);
    }

    public final void zza(int i, boolean z) throws IOException {
        zzg(i, 0);
        byte b = (byte) z;
        if (this.zzbzc.hasRemaining()) {
            this.zzbzc.put(b);
            return;
        }
        throw new zzacc(this.zzbzc.position(), this.zzbzc.limit());
    }

    public final void zzar(int i) throws IOException {
        while ((i & -128) != 0) {
            zzap((i & 127) | 128);
            i >>>= 7;
        }
        zzap(i);
    }

    public final void zzb(int i, long j) throws IOException {
        zzg(i, 0);
        zzas(j);
    }

    public final void zzb(int i, String str) throws IOException {
        zzg(i, 2);
        try {
            i = zzas(str.length());
            if (i == zzas(str.length() * 3)) {
                int position = this.zzbzc.position();
                if (this.zzbzc.remaining() < i) {
                    throw new zzacc(position + i, this.zzbzc.limit());
                }
                this.zzbzc.position(position + i);
                zza((CharSequence) str, this.zzbzc);
                int position2 = this.zzbzc.position();
                this.zzbzc.position(position);
                zzar((position2 - position) - i);
                this.zzbzc.position(position2);
                return;
            }
            zzar(zza(str));
            zza((CharSequence) str, this.zzbzc);
        } catch (Throwable e) {
            zzacc zzacc = new zzacc(this.zzbzc.position(), this.zzbzc.limit());
            zzacc.initCause(e);
            throw zzacc;
        }
    }

    public final void zzb(zzacj zzacj) throws IOException {
        zzar(zzacj.zzwa());
        zzacj.zza(this);
    }

    public final void zze(int i, int i2) throws IOException {
        zzg(i, 0);
        if (i2 >= 0) {
            zzar(i2);
        } else {
            zzas((long) i2);
        }
    }

    public final void zzg(int i, int i2) throws IOException {
        zzar((i << 3) | i2);
    }

    public final void zzk(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this.zzbzc.remaining() >= length) {
            this.zzbzc.put(bArr, 0, length);
            return;
        }
        throw new zzacc(this.zzbzc.position(), this.zzbzc.limit());
    }

    public final void zzvt() {
        if (this.zzbzc.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", new Object[]{Integer.valueOf(this.zzbzc.remaining())}));
        }
    }
}
