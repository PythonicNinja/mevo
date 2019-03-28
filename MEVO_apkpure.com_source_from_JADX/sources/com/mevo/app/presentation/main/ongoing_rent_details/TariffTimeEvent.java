package com.mevo.app.presentation.main.ongoing_rent_details;

import java.util.Comparator;
import java.util.Objects;

public class TariffTimeEvent {
    public final int itemId;
    public final long timestampSeconds;
    public final Type type;

    public static class TimestampComparator implements Comparator<TariffTimeEvent> {
        public int compare(TariffTimeEvent tariffTimeEvent, TariffTimeEvent tariffTimeEvent2) {
            return Long.compare(tariffTimeEvent.timestampSeconds, tariffTimeEvent2.timestampSeconds);
        }
    }

    public enum Type {
        RENT_START,
        RENT_END,
        BOOKING_START,
        BOOKING_END,
        DAY_START,
        DAY_END,
        TARIFF_END
    }

    public TariffTimeEvent(int i, long j, Type type) {
        this.itemId = i;
        this.timestampSeconds = j;
        this.type = type;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            if (getClass() == obj.getClass()) {
                TariffTimeEvent tariffTimeEvent = (TariffTimeEvent) obj;
                if (this.itemId != tariffTimeEvent.itemId || this.timestampSeconds != tariffTimeEvent.timestampSeconds || this.type != tariffTimeEvent.type) {
                    z = false;
                }
                return z;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.itemId), Long.valueOf(this.timestampSeconds), this.type});
    }
}
