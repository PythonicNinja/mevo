package com.mevo.app.presentation.main.user_details;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import com.annimon.stream.Stream;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.constants.LocationData;
import com.mevo.app.data.model.UserDetails;
import com.mevo.app.presentation.adapters.CountryAdapter;
import com.mevo.app.presentation.custom_views.CustomInput;
import com.mevo.app.presentation.custom_views.ProgressOverlayView;
import com.mevo.app.presentation.custom_views.TopToast;
import com.mevo.app.presentation.main.MainFragment;
import com.mevo.app.tools.MaskWatcherZipCode;
import com.mevo.app.tools.NetTools;
import com.mevo.app.tools.NetTools.CallsToken;
import com.mevo.app.tools.ProblemReportHelper;
import com.mevo.app.tools.ProblemReportHelper.ProblemReportListener;
import com.mevo.app.tools.Tools;
import com.mevo.app.tools.UserManager;
import com.mevo.app.tools.Validator;
import java.util.ArrayList;
import java.util.Arrays;
import retrofit2.Call;

public class UserDetailsFragment extends MainFragment implements OnClickListener {
    private static final String TAG = "UserDetailsFragment";
    private CallsToken callsToken;
    private CustomInput cityContainer;
    private TextInputEditText cityEdit;
    private TextInputLayout cityLayout;
    private CountryAdapter countriesAdapter;
    private Spinner countrySpinner;
    private CustomInput emailContainer;
    private TextInputEditText emailEdit;
    private TextInputLayout emailLayout;
    private CustomInput firstNameContainer;
    private TextInputEditText firstNameEdit;
    private TextInputLayout firstNameLayout;
    private CustomInput lastNameContainer;
    private TextInputEditText lastNameEdit;
    private TextInputLayout lastNameLayout;
    private CustomInput peselContainer;
    private TextInputEditText peselEdit;
    private TextInputLayout peselLayout;
    private CustomInput phoneContainer;
    private TextInputEditText phoneEdit;
    private TextInputLayout phoneLayout;
    private ProgressOverlayView progressOverlayView;
    private ScrollView scrollView;
    private CustomInput streetContainer;
    private TextInputEditText streetEdit;
    private TextInputLayout streetLayout;
    private Button updateButton;
    private CustomInput zipCodeContainer;
    private TextInputEditText zipCodeEdit;
    private TextInputLayout zipCodeLayout;

    /* renamed from: com.mevo.app.presentation.main.user_details.UserDetailsFragment$1 */
    class C04591 implements OnItemSelectedListener {
        public void onNothingSelected(AdapterView<?> adapterView) {
        }

        C04591() {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (UserDetailsFragment.this.countriesAdapter.getItem(UserDetailsFragment.this.countrySpinner.getSelectedItemPosition()).getCode().equals("PL") != null) {
                UserDetailsFragment.this.zipCodeEdit.addTextChangedListener(new MaskWatcherZipCode("##-###"));
                if (UserDetailsFragment.this.zipCodeEdit.getText().toString().matches("^\\d{2}-\\d{3}?") == null) {
                    UserDetailsFragment.this.zipCodeEdit.setText("");
                }
            }
        }
    }

    /* renamed from: com.mevo.app.presentation.main.user_details.UserDetailsFragment$2 */
    class C08342 implements ProblemReportListener {
        public void onImageSent(boolean z) {
        }

        C08342() {
        }

        public void onProblemReported(int i) {
            TopToast.show(C0434R.string.user_update_success, 1, TopToast.DURATION_SHORT);
            UserDetailsFragment.this.progressOverlayView.hide();
        }

        public void onProblemReportFailed(int i) {
            TopToast.show(C0434R.string.error_ocurred, 0, TopToast.DURATION_SHORT);
            UserDetailsFragment.this.progressOverlayView.hide();
        }
    }

    public static UserDetailsFragment newInstance() {
        return new UserDetailsFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_user_details, viewGroup, false);
        findViews(layoutInflater);
        setAdapters();
        setListeners();
        this.callsToken = new CallsToken();
        fillFields(UserManager.getUserDetails());
        return layoutInflater;
    }

    public void onStart() {
        super.onStart();
        fetchCurrentUserDetails();
    }

    public void onStop() {
        super.onStop();
        NetTools.cancelSavedOngoingCalls(this.callsToken);
    }

    private void findViews(View view) {
        this.firstNameEdit = (TextInputEditText) view.findViewById(C0434R.id.fragment_user_details_first_name);
        this.lastNameEdit = (TextInputEditText) view.findViewById(C0434R.id.fragment_user_details_last_name);
        this.emailEdit = (TextInputEditText) view.findViewById(C0434R.id.fragment_user_details_email);
        this.cityEdit = (TextInputEditText) view.findViewById(C0434R.id.fragment_user_details_city);
        this.streetEdit = (TextInputEditText) view.findViewById(C0434R.id.fragment_user_details_street);
        this.zipCodeEdit = (TextInputEditText) view.findViewById(C0434R.id.fragment_user_details_zip_code);
        this.updateButton = (Button) view.findViewById(C0434R.id.fragment_user_details_update_button);
        this.peselEdit = (TextInputEditText) view.findViewById(C0434R.id.fragment_user_details_pesel);
        this.phoneEdit = (TextInputEditText) view.findViewById(C0434R.id.fragment_user_details_phone);
        this.countrySpinner = (Spinner) view.findViewById(C0434R.id.fragment_user_details_country);
        this.firstNameContainer = (CustomInput) view.findViewById(C0434R.id.fragment_user_details_first_name_container);
        this.lastNameContainer = (CustomInput) view.findViewById(C0434R.id.fragment_user_details_last_name_container);
        this.emailContainer = (CustomInput) view.findViewById(C0434R.id.fragment_user_details_email_container);
        this.cityContainer = (CustomInput) view.findViewById(C0434R.id.fragment_user_details_city_container);
        this.streetContainer = (CustomInput) view.findViewById(C0434R.id.fragment_user_details_street_container);
        this.zipCodeContainer = (CustomInput) view.findViewById(C0434R.id.fragment_user_details_zip_code_container);
        this.peselContainer = (CustomInput) view.findViewById(C0434R.id.fragment_user_details_pesel_container);
        this.phoneContainer = (CustomInput) view.findViewById(C0434R.id.fragment_user_details_phone_container);
        this.firstNameLayout = (TextInputLayout) view.findViewById(C0434R.id.fragment_user_details_first_name_layout);
        this.lastNameLayout = (TextInputLayout) view.findViewById(C0434R.id.fragment_user_details_last_name_layout);
        this.emailLayout = (TextInputLayout) view.findViewById(C0434R.id.fragment_user_details_email_layout);
        this.cityLayout = (TextInputLayout) view.findViewById(C0434R.id.fragment_user_details_city_layout);
        this.streetLayout = (TextInputLayout) view.findViewById(C0434R.id.fragment_user_details_street_layout);
        this.zipCodeLayout = (TextInputLayout) view.findViewById(C0434R.id.fragment_user_details_zip_code_layout);
        this.peselLayout = (TextInputLayout) view.findViewById(C0434R.id.fragment_user_details_pesel_layout);
        this.phoneLayout = (TextInputLayout) view.findViewById(C0434R.id.fragment_user_details_phone_layout);
        this.scrollView = (ScrollView) view.findViewById(C0434R.id.scrollView);
        this.progressOverlayView = (ProgressOverlayView) view.findViewById(C0434R.id.fragment_user_details_progress_view);
    }

    private void setListeners() {
        this.countrySpinner.setOnItemSelectedListener(new C04591());
        this.updateButton.setOnClickListener(this);
        this.firstNameContainer.setValidator(null, new UserDetailsFragment$$Lambda$0(this));
        this.lastNameContainer.setValidator(null, new UserDetailsFragment$$Lambda$1(this));
        this.emailContainer.setValidator(null, new UserDetailsFragment$$Lambda$2(this));
        this.cityContainer.setValidator(null, new UserDetailsFragment$$Lambda$3(this));
        this.streetContainer.setValidator(null, new UserDetailsFragment$$Lambda$4(this));
        this.zipCodeContainer.setValidator(null, new UserDetailsFragment$$Lambda$5(this));
        this.peselContainer.setValidator(null, new UserDetailsFragment$$Lambda$6(this));
        this.phoneContainer.setValidator(null, new UserDetailsFragment$$Lambda$7(this));
        this.firstNameEdit.setOnFocusChangeListener(new UserDetailsFragment$$Lambda$8(this));
        this.lastNameEdit.setOnFocusChangeListener(new UserDetailsFragment$$Lambda$9(this));
        this.emailEdit.setOnFocusChangeListener(new UserDetailsFragment$$Lambda$10(this));
        this.cityEdit.setOnFocusChangeListener(new UserDetailsFragment$$Lambda$11(this));
        this.streetEdit.setOnFocusChangeListener(new UserDetailsFragment$$Lambda$12(this));
        this.zipCodeEdit.setOnFocusChangeListener(new UserDetailsFragment$$Lambda$13(this));
        this.peselEdit.setOnFocusChangeListener(new UserDetailsFragment$$Lambda$14(this));
        this.phoneEdit.setOnFocusChangeListener(new UserDetailsFragment$$Lambda$15(this));
    }

    final /* synthetic */ Pair lambda$setListeners$285$UserDetailsFragment(String str) {
        if (Validator.isFirstNameValid(str) != null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.first_name_error) : "");
    }

    final /* synthetic */ Pair lambda$setListeners$286$UserDetailsFragment(String str) {
        if (Validator.isLastNameValid(str) != null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.last_name_error) : "");
    }

    final /* synthetic */ Pair lambda$setListeners$287$UserDetailsFragment(String str) {
        if (Validator.isEmailValid(str) != null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.email_error) : "");
    }

    final /* synthetic */ Pair lambda$setListeners$288$UserDetailsFragment(String str) {
        if (Validator.isCityValid(str) && Tools.isNullorEmpty(str) == null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.city_error) : "");
    }

    final /* synthetic */ Pair lambda$setListeners$289$UserDetailsFragment(String str) {
        if (Validator.isStreetValid(str) && Tools.isNullorEmpty(str) == null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.street_error) : "");
    }

    final /* synthetic */ Pair lambda$setListeners$290$UserDetailsFragment(String str) {
        if (this.countriesAdapter.getItem(this.countrySpinner.getSelectedItemPosition()).getCode().equals("PL")) {
            if (Validator.isZipCodeValid(str) != null) {
                return new Pair(Boolean.valueOf(true), null);
            }
            return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.zip_code_error) : "");
        } else if (Validator.isZipCodeValidForAnotherCountry(str) != null) {
            return new Pair(Boolean.valueOf(true), null);
        } else {
            return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.zip_code_error) : "");
        }
    }

    final /* synthetic */ Pair lambda$setListeners$291$UserDetailsFragment(String str) {
        if (Validator.isPeselValid(str) != null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.pesel_error) : "");
    }

    final /* synthetic */ Pair lambda$setListeners$292$UserDetailsFragment(String str) {
        if (Validator.isPhoneNumberValid(str) != null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.login_screen_wrong_phone_number) : "");
    }

    final /* synthetic */ void lambda$setListeners$293$UserDetailsFragment(View view, boolean z) {
        if (z) {
            this.firstNameLayout.setHint(IM.resources().getString(C0434R.string.first_name_hint));
        } else {
            checkIfHintShouldHaveStarOnStart(this.firstNameEdit, this.firstNameLayout, C0434R.string.first_name_hint, C0434R.string.registration_first_name_hint);
        }
    }

    final /* synthetic */ void lambda$setListeners$294$UserDetailsFragment(View view, boolean z) {
        if (z) {
            this.lastNameLayout.setHint(IM.resources().getString(C0434R.string.last_name_hint));
        } else {
            checkIfHintShouldHaveStarOnStart(this.lastNameEdit, this.lastNameLayout, C0434R.string.last_name_hint, C0434R.string.registration_last_name_hint);
        }
    }

    final /* synthetic */ void lambda$setListeners$295$UserDetailsFragment(View view, boolean z) {
        if (z) {
            this.emailLayout.setHint(IM.resources().getString(C0434R.string.email_address_hint));
        } else {
            checkIfHintShouldHaveStarOnStart(this.emailEdit, this.emailLayout, C0434R.string.email_address_hint, C0434R.string.registration_email_address_hint);
        }
    }

    final /* synthetic */ void lambda$setListeners$296$UserDetailsFragment(View view, boolean z) {
        if (z) {
            this.cityLayout.setHint(IM.resources().getString(C0434R.string.city_hint));
        } else {
            checkIfHintShouldHaveStarOnStart(this.cityEdit, this.cityLayout, C0434R.string.city_hint, C0434R.string.registration_city_hint);
        }
    }

    final /* synthetic */ void lambda$setListeners$297$UserDetailsFragment(View view, boolean z) {
        if (z) {
            this.streetLayout.setHint(IM.resources().getString(C0434R.string.address_hint));
        } else {
            checkIfHintShouldHaveStarOnStart(this.streetEdit, this.streetLayout, C0434R.string.address_hint, C0434R.string.registration_address_hint);
        }
    }

    final /* synthetic */ void lambda$setListeners$298$UserDetailsFragment(View view, boolean z) {
        if (z) {
            this.zipCodeLayout.setHint(IM.resources().getString(C0434R.string.zip_code_hint));
        } else {
            checkIfHintShouldHaveStarOnStart(this.zipCodeEdit, this.zipCodeLayout, C0434R.string.zip_code_hint, C0434R.string.registration_zip_code_hint);
        }
    }

    final /* synthetic */ void lambda$setListeners$299$UserDetailsFragment(View view, boolean z) {
        if (z) {
            this.peselLayout.setHint(IM.resources().getString(C0434R.string.pesel_hint));
        } else {
            checkIfHintShouldHaveStarOnStart(this.peselEdit, this.peselLayout, C0434R.string.pesel_hint, C0434R.string.registration_pesel_hint);
        }
    }

    final /* synthetic */ void lambda$setListeners$300$UserDetailsFragment(View view, boolean z) {
        if (z) {
            this.phoneLayout.setHint(IM.resources().getString(C0434R.string.phone_number_hint));
        } else {
            checkIfHintShouldHaveStarOnStart(this.phoneEdit, this.phoneLayout, C0434R.string.phone_number_hint, C0434R.string.registration_phone_number_hint);
        }
    }

    private void setAdapters() {
        this.countriesAdapter = new CountryAdapter(new ArrayList(Arrays.asList(LocationData.COUNTRY_CODES)));
        this.countrySpinner.setAdapter(this.countriesAdapter);
    }

    private void checkIfHintShouldHaveStarOnStart(TextInputEditText textInputEditText, TextInputLayout textInputLayout, int i, int i2) {
        if (Tools.isNullorEmpty(textInputEditText.getText().toString()) == null) {
            textInputLayout.setHint(IM.resources().getString(i));
        } else {
            textInputLayout.setHint(IM.resources().getString(i2));
        }
    }

    private void fillFields(UserDetails userDetails) {
        if (userDetails != null) {
            this.firstNameEdit.setText(userDetails.getFirstname());
            this.lastNameEdit.setText(userDetails.getLastname());
            this.emailEdit.setText(userDetails.getEmail());
            this.cityEdit.setText(userDetails.getCity());
            this.streetEdit.setText(userDetails.getAddress());
            this.countrySpinner.setSelection(this.countriesAdapter.getCountryIndex(userDetails.getCountry()));
            this.zipCodeEdit.setText(userDetails.getZipCode());
            this.peselEdit.setText(userDetails.getPesel());
            this.phoneEdit.setText(Tools.phoneNumberFormatter(userDetails.getPhoneNumber()));
            checkIfHintShouldHaveStarOnStart(this.phoneEdit, this.phoneLayout, C0434R.string.phone_number_hint, C0434R.string.registration_phone_number_hint);
            checkIfHintShouldHaveStarOnStart(this.peselEdit, this.peselLayout, C0434R.string.pesel_hint, C0434R.string.registration_pesel_hint);
            checkIfHintShouldHaveStarOnStart(this.zipCodeEdit, this.zipCodeLayout, C0434R.string.zip_code_hint, C0434R.string.registration_zip_code_hint);
            checkIfHintShouldHaveStarOnStart(this.streetEdit, this.streetLayout, C0434R.string.address_hint, C0434R.string.registration_address_hint);
            checkIfHintShouldHaveStarOnStart(this.cityEdit, this.cityLayout, C0434R.string.city_hint, C0434R.string.registration_city_hint);
            checkIfHintShouldHaveStarOnStart(this.emailEdit, this.emailLayout, C0434R.string.email_address_hint, C0434R.string.registration_email_address_hint);
            checkIfHintShouldHaveStarOnStart(this.lastNameEdit, this.lastNameLayout, C0434R.string.last_name_hint, C0434R.string.registration_last_name_hint);
            checkIfHintShouldHaveStarOnStart(this.firstNameEdit, this.firstNameLayout, C0434R.string.first_name_hint, C0434R.string.registration_first_name_hint);
        }
    }

    public void onClick(View view) {
        if (view.getId() == C0434R.id.fragment_user_details_update_button) {
            update();
        }
    }

    private boolean checkAllFieldsValid() {
        this.firstNameContainer.checkValid();
        this.lastNameContainer.checkValid();
        this.emailContainer.checkValid();
        this.cityContainer.checkValid();
        this.streetContainer.checkValid();
        this.zipCodeContainer.checkValid();
        this.phoneContainer.checkValid();
        boolean z = false;
        if (this.countriesAdapter.getItem(this.countrySpinner.getSelectedItemPosition()).getCode().equals("PL")) {
            this.peselContainer.checkValid();
            if (this.firstNameContainer.isValid().booleanValue() && this.lastNameContainer.isValid().booleanValue() && this.emailContainer.isValid().booleanValue() && this.cityContainer.isValid().booleanValue() && this.streetContainer.isValid().booleanValue() && this.zipCodeContainer.isValid().booleanValue() && this.peselContainer.isValid().booleanValue() && this.phoneContainer.isValid().booleanValue()) {
                z = true;
            }
            return z;
        }
        if (this.firstNameContainer.isValid().booleanValue() && this.lastNameContainer.isValid().booleanValue() && this.emailContainer.isValid().booleanValue() && this.cityContainer.isValid().booleanValue() && this.streetContainer.isValid().booleanValue() && this.zipCodeContainer.isValid().booleanValue() && this.phoneContainer.isValid().booleanValue()) {
            z = true;
        }
        return z;
    }

    private void fetchCurrentUserDetails() {
        this.progressOverlayView.show();
        UserManager.fetchUserDetailsFromServer(this.callsToken, new UserDetailsFragment$$Lambda$16(this));
    }

    final /* synthetic */ void lambda$fetchCurrentUserDetails$301$UserDetailsFragment(boolean z, Call call, UserDetails userDetails) {
        if (z) {
            fillFields(userDetails);
        } else if (!call.isCanceled()) {
            onUserDetailsRequestFailed();
        }
        this.progressOverlayView.hide();
    }

    private void onUserDetailsRequestFailed() {
        TopToast.show(C0434R.string.error_ocurred, 0, TopToast.DURATION_SHORT);
    }

    private void update() {
        if (checkAllFieldsValid()) {
            this.progressOverlayView.show();
            new ProblemReportHelper(new C08342()).reportProblem("message", null, null, createMessage(), null, null);
            return;
        }
        scrollToFirstError();
    }

    private String createMessage() {
        return "ZMIANA DANYCH OSOBOWYCH: ".concat(getMessagePart(ReportUserEnum.EMAIL, this.emailEdit.getText().toString())).concat(getMessagePart(ReportUserEnum.FORENAME, this.firstNameEdit.getText().toString())).concat(getMessagePart(ReportUserEnum.NAME, this.lastNameEdit.getText().toString())).concat(getMessagePart(ReportUserEnum.ADDRESS, this.streetEdit.getText().toString())).concat(getMessagePart(ReportUserEnum.ZIP, this.zipCodeEdit.getText().toString())).concat(getMessagePart(ReportUserEnum.CITY, this.cityEdit.getText().toString())).concat(getMessagePart(ReportUserEnum.COUNTRY, this.countriesAdapter.getItem(this.countrySpinner.getSelectedItemPosition()).getCode())).concat(getMessagePart(ReportUserEnum.MOBILE, this.phoneEdit.getText().toString())).concat(getMessagePart(ReportUserEnum.PESEL, this.peselEdit.getText().toString()));
    }

    private String getMessagePart(ReportUserEnum reportUserEnum, String str) {
        if (str.isEmpty()) {
            str = new StringBuilder();
            str.append(reportUserEnum.keyName);
            str.append(": ");
            return str.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(reportUserEnum.keyName);
        stringBuilder.append(": ");
        stringBuilder.append(str);
        stringBuilder.append(" ");
        return stringBuilder.toString();
    }

    private void scrollToFirstError() {
        Iterable arrayList = new ArrayList();
        arrayList.add(this.phoneContainer);
        arrayList.add(this.emailContainer);
        arrayList.add(this.firstNameContainer);
        arrayList.add(this.lastNameContainer);
        arrayList.add(this.streetContainer);
        arrayList.add(this.zipCodeContainer);
        arrayList.add(this.cityContainer);
        arrayList.add(this.peselContainer);
        CustomInput customInput = (CustomInput) Stream.of(arrayList).filter(UserDetailsFragment$$Lambda$17.$instance).findFirst().orElse(null);
        if (customInput != null) {
            this.scrollView.scrollTo(0, customInput.getTop());
            customInput.requestFocus();
        }
    }
}
