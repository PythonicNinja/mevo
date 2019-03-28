package com.mevo.app.presentation.main.statitons_map.bottom_sheets;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback;
import android.view.View;
import com.mevo.app.presentation.adapters.CurrentRentalsAdapter;
import com.mevo.app.presentation.custom_views.QrView;
import com.mevo.app.presentation.main.statitons_map.bottom_sheets.CurrentRentalsDialogView.MyRentalsListener;
import com.mevo.app.tools.BotomSheetHelper;

public class HomeBottomSheetManager {
    private View dimView;
    private int myRentalsPeek = 0;
    private BottomSheetBehavior myRentalsSheetBehavior;
    private CurrentRentalsDialogView myRentalsSheetView;
    private QrView qrButton;
    private BottomSheetBehavior reserveSheetBehavior;
    private BookingDialogView reserveSheetView;

    /* renamed from: com.mevo.app.presentation.main.statitons_map.bottom_sheets.HomeBottomSheetManager$1 */
    class C08301 extends BottomSheetCallback {
        C08301() {
        }

        public void onStateChanged(@NonNull View view, int i) {
            if (i == 4 && HomeBottomSheetManager.this.dimView != null) {
                HomeBottomSheetManager.this.dimView.setVisibility(8);
            }
        }

        public void onSlide(@NonNull View view, float f) {
            if (HomeBottomSheetManager.this.dimView != null) {
                HomeBottomSheetManager.this.dimView.setVisibility(0);
                HomeBottomSheetManager.this.dimView.setAlpha(f);
            }
            if (((double) f) > 0.8d) {
                HomeBottomSheetManager.this.myRentalsSheetBehavior.setPeekHeight(0);
            } else if (HomeBottomSheetManager.this.myRentalsSheetBehavior.getPeekHeight() != HomeBottomSheetManager.this.myRentalsPeek) {
                HomeBottomSheetManager.this.myRentalsSheetBehavior.setPeekHeight(HomeBottomSheetManager.this.myRentalsPeek);
            }
        }
    }

    /* renamed from: com.mevo.app.presentation.main.statitons_map.bottom_sheets.HomeBottomSheetManager$2 */
    class C08312 extends BottomSheetCallback {
        C08312() {
        }

        public void onStateChanged(@NonNull View view, int i) {
            if (i == 4 && HomeBottomSheetManager.this.dimView != null) {
                HomeBottomSheetManager.this.dimView.setVisibility(8);
            }
            if (i == 3) {
                HomeBottomSheetManager.this.myRentalsSheetView.setWasExpanded(1);
                HomeBottomSheetManager.this.myRentalsSheetBehavior.setPeekHeight(HomeBottomSheetManager.this.myRentalsSheetView.getHeaderHeight());
            }
        }

        public void onSlide(@NonNull View view, float f) {
            if (HomeBottomSheetManager.this.dimView != null && HomeBottomSheetManager.this.qrButton != null) {
                HomeBottomSheetManager.this.dimView.setVisibility(0);
                HomeBottomSheetManager.this.dimView.setAlpha(f);
                if (((double) f) > 0.8d) {
                    HomeBottomSheetManager.this.qrButton.setGone();
                } else {
                    HomeBottomSheetManager.this.qrButton.setVisible();
                }
            }
        }
    }

    /* renamed from: com.mevo.app.presentation.main.statitons_map.bottom_sheets.HomeBottomSheetManager$3 */
    class C08323 implements MyRentalsListener {
        C08323() {
        }

        public void onItemsLoaded(CurrentRentalsAdapter currentRentalsAdapter, int i) {
            if (!(currentRentalsAdapter == null || HomeBottomSheetManager.this.myRentalsSheetView == null)) {
                if (HomeBottomSheetManager.this.myRentalsSheetBehavior != null) {
                    if (currentRentalsAdapter.getItemCount() != 0) {
                        if (!HomeBottomSheetManager.this.myRentalsSheetView.wasExpanded()) {
                            if (currentRentalsAdapter.getItemCount() == 1) {
                                HomeBottomSheetManager.this.myRentalsSheetBehavior.setPeekHeight(HomeBottomSheetManager.this.myRentalsSheetView.getHeaderHeight() + i);
                            } else {
                                HomeBottomSheetManager.this.myRentalsSheetBehavior.setPeekHeight((int) (((double) HomeBottomSheetManager.this.myRentalsSheetView.getHeaderHeight()) + (((double) i) * 1.5d)));
                            }
                            HomeBottomSheetManager.this.myRentalsPeek = HomeBottomSheetManager.this.myRentalsSheetBehavior.getPeekHeight();
                        }
                    }
                    HomeBottomSheetManager.this.myRentalsSheetBehavior.setPeekHeight(HomeBottomSheetManager.this.myRentalsSheetView.getHeaderHeight());
                    HomeBottomSheetManager.this.myRentalsPeek = HomeBottomSheetManager.this.myRentalsSheetBehavior.getPeekHeight();
                }
            }
        }

        public void onHeaderClick() {
            BotomSheetHelper.toggleSheet(HomeBottomSheetManager.this.myRentalsSheetBehavior);
        }
    }

    public HomeBottomSheetManager(CurrentRentalsDialogView currentRentalsDialogView, BookingDialogView bookingDialogView, View view, QrView qrView) {
        this.myRentalsSheetView = currentRentalsDialogView;
        this.reserveSheetView = bookingDialogView;
        this.dimView = view;
        this.qrButton = qrView;
        setupSheets();
        setupPeekHeight();
    }

    private void setupSheets() {
        this.myRentalsSheetBehavior = BottomSheetBehavior.from(this.myRentalsSheetView);
        this.reserveSheetBehavior = BottomSheetBehavior.from(this.reserveSheetView);
        this.reserveSheetBehavior.setBottomSheetCallback(new C08301());
        this.myRentalsSheetBehavior.setBottomSheetCallback(new C08312());
    }

    private void setupPeekHeight() {
        this.myRentalsSheetBehavior.setPeekHeight(this.myRentalsSheetView.getHeaderHeight());
        this.myRentalsPeek = this.myRentalsSheetBehavior.getPeekHeight();
        this.myRentalsSheetView.setListener(new C08323());
        this.reserveSheetView.setListener(new HomeBottomSheetManager$$Lambda$0(this));
    }

    final /* synthetic */ void lambda$setupPeekHeight$260$HomeBottomSheetManager() {
        BotomSheetHelper.toggleSheet(this.reserveSheetBehavior);
    }

    public void showReserveSheet(boolean z) {
        if (z) {
            this.myRentalsSheetBehavior.setState(4);
            this.reserveSheetBehavior.setState(3);
            return;
        }
        hideSheets();
    }

    public void showMyRentalsSheet(boolean z) {
        if (z) {
            this.myRentalsSheetBehavior.setState(3);
            this.reserveSheetBehavior.setState(4);
            return;
        }
        hideSheets();
    }

    private void hideSheets() {
        this.myRentalsSheetBehavior.setState(4);
        this.reserveSheetBehavior.setState(4);
    }

    public void onDestroy() {
        this.myRentalsSheetView = null;
        this.reserveSheetView = null;
        this.dimView = null;
        this.qrButton = null;
    }
}
