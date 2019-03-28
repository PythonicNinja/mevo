package com.mevo.app.tools.bike_tools;

import android.support.annotation.NonNull;
import com.inverce.mod.core.IM;
import com.inverce.mod.events.Event.Bus;
import com.mevo.app.App;
import com.mevo.app.C0434R;
import com.mevo.app.data.model.BookingItem;
import com.mevo.app.data.model.Place;
import com.mevo.app.data.repository.NextbikeApiRepository;
import com.mevo.app.modules.firebase_cloud_messaging.NotificationsHelper;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.dialogs.BikeReservedDialog;
import com.mevo.app.presentation.dialogs.InfoDialog.Builder;
import com.mevo.app.presentation.main.MainActivityInterface;
import com.mevo.app.presentation.main.statitons_map.RefreshMapAndSheetListener;

public class BookingRepository {

    public interface BookingListener {
        void onBooking(boolean z, boolean z2);
    }

    final /* bridge */ /* synthetic */ void bridge$lambda$0$BookingRepository() {
        showNotification();
    }

    private void tryBookBike(String str, @NonNull BookingListener bookingListener) {
        new NextbikeApiRepository().bookBike(str, new BookingRepository$$Lambda$0(bookingListener));
    }

    static final /* synthetic */ void lambda$tryBookBike$331$BookingRepository(@NonNull BookingListener bookingListener, BookingItem bookingItem, boolean z, boolean z2) {
        if (!z || bookingItem == null) {
            bookingListener.onBooking(null, z2);
        } else {
            bookingListener.onBooking(true, z2);
        }
    }

    public void onBookingDecision(String str, String str2, @NonNull BookingListener bookingListener) {
        MainActivityInterface mainActivityInterface = IM.activity() != null ? (MainActivityInterface) IM.activity() : null;
        if (mainActivityInterface != null) {
            mainActivityInterface.setProgressViewVisibility(true);
            tryBookBike(str, new BookingRepository$$Lambda$1(this, str2, bookingListener, mainActivityInterface));
        }
    }

    final /* synthetic */ void lambda$onBookingDecision$332$BookingRepository(String str, @NonNull BookingListener bookingListener, MainActivityInterface mainActivityInterface, boolean z, boolean z2) {
        if (z) {
            ((RefreshMapAndSheetListener) Bus.post(RefreshMapAndSheetListener.class)).refreshAll();
            str = BikeReservedDialog.newInstance(str);
            str.setFinishListener(new BookingRepository$$Lambda$2(this));
            str.show();
        } else if (z2) {
            new Builder().setText((int) C0434R.string.inactive_account_cant_book).setNeutralButton((int) C0434R.string.close, null).build().show();
        } else {
            TopToast.show(C0434R.string.reserve_bike_failed, 0, TopToast.DURATION_LONG);
        }
        bookingListener.onBooking(z, z2);
        mainActivityInterface.setProgressViewVisibility(false);
    }

    public void onBookingDecision(Place place, String str, @NonNull BookingListener bookingListener) {
        onBookingDecision(String.valueOf(place.getUid()), str, bookingListener);
    }

    private void showNotification() {
        NotificationsHelper.sendNotification(App.getAppContext().getString(C0434R.string.app_name), IM.context().getString(C0434R.string.dialog_bike_reserved_correctly), App.getAppContext());
    }
}
