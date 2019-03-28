package com.mevo.app.data.repository;

import android.support.annotation.NonNull;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.inverce.mod.core.IM;
import com.mevo.app.Cfg;
import com.mevo.app.constants.StationTypes;
import com.mevo.app.constants.StationTypes.StationType;
import com.mevo.app.data.model.City;
import com.mevo.app.data.model.Country;
import com.mevo.app.data.model.Domain;
import com.mevo.app.data.model.Place;
import com.mevo.app.data.model.response.ResponseStationsData;
import com.mevo.app.data.network.Rest;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.Utils;
import java.util.ArrayList;
import java.util.List;

public class StationsDataRepository {
    private static final long FETCH_INTERVAL = 5000;
    private static final String TAG = "StationsDataRepository";
    private static StationsDataRepository instance;
    private List<Place> data = new ArrayList();
    private long lastFetchTime;
    private List<StationsDataListener> stationsDataListeners = new ArrayList();

    public interface StationsDataListener {
        void onDataReceived(boolean z, @NonNull List<Place> list);
    }

    public static StationsDataRepository get() {
        if (instance == null) {
            instance = new StationsDataRepository();
        }
        return instance;
    }

    private StationsDataRepository() {
    }

    public void fetchStationsData(StationsDataListener stationsDataListener) {
        this.stationsDataListeners.add(stationsDataListener);
        if (Utils.hasTimePassed(this.lastFetchTime, 5000) == null) {
            informAllStationsDataListeners(true);
        } else {
            IM.onBg().execute(new StationsDataRepository$$Lambda$0(this));
        }
    }

    final /* synthetic */ void lambda$fetchStationsData$64$StationsDataRepository() {
        ResponseStationsData responseStationsData = null;
        try {
            responseStationsData = (ResponseStationsData) Rest.getApiNextbikeMap().getStationsData(getCommaSeparatedDomainCodes(Cfg.get().bikeDomains()), null, null, null).execute().body();
        } catch (Throwable e) {
            Log.ex(TAG, e);
        }
        if (responseStationsData == null) {
            onStationsDataFetchFailed();
        } else {
            onStationsDataReceived((List) Stream.of(responseStationsData.countries != null ? responseStationsData.countries : new ArrayList()).filter(StationsDataRepository$$Lambda$2.$instance).flatMap(StationsDataRepository$$Lambda$3.$instance).withoutNulls().filter(StationsDataRepository$$Lambda$4.$instance).flatMap(StationsDataRepository$$Lambda$5.$instance).withoutNulls().filter(StationsDataRepository$$Lambda$6.$instance).collect(Collectors.toList()));
        }
    }

    static final /* synthetic */ boolean lambda$null$59$StationsDataRepository(Country country) {
        return country.cities != null ? true : null;
    }

    static final /* synthetic */ boolean lambda$null$61$StationsDataRepository(City city) {
        return city.places != null ? true : null;
    }

    static final /* synthetic */ boolean lambda$null$63$StationsDataRepository(Place place) {
        if (StationTypes.getStationType(place.getNumber(), place.getPlaceType()) == StationType.SINGLE_BIKE) {
            return place.getBookedBikes() != 1;
        } else {
            return true;
        }
    }

    private String getCommaSeparatedDomainCodes(List<Domain> list) {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(((Domain) list.get(i)).getDomainCode());
            str = stringBuilder.toString();
            if (i < list.size() - 1) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(",");
                str = stringBuilder.toString();
            }
        }
        return str;
    }

    private void onStationsDataReceived(List<Place> list) {
        this.data = list;
        this.lastFetchTime = System.currentTimeMillis();
        informAllStationsDataListeners(true);
    }

    private void onStationsDataFetchFailed() {
        this.data = new ArrayList();
        informAllStationsDataListeners(false);
    }

    private void informAllStationsDataListeners(boolean z) {
        IM.onUi().execute(new StationsDataRepository$$Lambda$1(this, z));
    }

    final /* synthetic */ void lambda$informAllStationsDataListeners$65$StationsDataRepository(boolean z) {
        for (StationsDataListener onDataReceived : this.stationsDataListeners) {
            onDataReceived.onDataReceived(z, new ArrayList(this.data));
        }
        this.stationsDataListeners.clear();
    }
}
