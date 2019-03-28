package com.mevo.app.presentation.register.register_step_one;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import com.annimon.stream.Stream;
import com.inverce.mod.core.IM;
import com.mevo.app.C0434R;
import com.mevo.app.constants.BikeTypes;
import com.mevo.app.constants.LocationData;
import com.mevo.app.data.model.CountryCode;
import com.mevo.app.data.model.RegistrationData;
import com.mevo.app.presentation.adapters.CountryAdapter;
import com.mevo.app.presentation.custom_views.AppToolbar.AppToolbarListener;
import com.mevo.app.presentation.custom_views.CustomInput;
import com.mevo.app.presentation.main.contact_us.ContactUsFragment;
import com.mevo.app.presentation.register.RegisterFragment;
import com.mevo.app.presentation.register.register_step_two.RegisterStepTwo;
import com.mevo.app.tools.MaskWatcherZipCode;
import com.mevo.app.tools.Screen;
import com.mevo.app.tools.UiTools;
import com.mevo.app.tools.Validator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterStepOne extends RegisterFragment implements AppToolbarListener, OnClickListener {
    private EditText cityEdit;
    private CustomInput cityEditContainer;
    private CountryAdapter countriesAdapter;
    private Spinner countryEdit;
    private CustomInput countryEditContainer;
    private TextView countryText;
    private EditText emailEdit;
    private CustomInput emailEditContainer;
    private EditText firstNameEdit;
    private CustomInput firstNameEditContainer;
    boolean isAdded = false;
    private Button keepGoing;
    private EditText lastNameEdit;
    private CustomInput lastNameEditContainer;
    private EditText peselEdit;
    private CustomInput peselEditContainer;
    private EditText phoneNumberEdit;
    private CustomInput phoneNumberEditContainer;
    private ScrollView scrollView;
    private TextView spinnerText;
    private EditText streetEdit;
    private CustomInput streetEditContainer;
    private TextInputLayout textInputCity;
    private TextInputLayout textInputCountry;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputFirstName;
    private TextInputLayout textInputLastName;
    private TextInputLayout textInputPesel;
    private TextInputLayout textInputPhone;
    private TextInputLayout textInputStreet;
    private TextInputLayout textInputZipCode;
    private EditText zipCodeEdit;
    private CustomInput zipCodeEditContainer;

    /* renamed from: com.mevo.app.presentation.register.register_step_one.RegisterStepOne$1 */
    class C04601 implements OnItemSelectedListener {
        public void onNothingSelected(AdapterView<?> adapterView) {
        }

        C04601() {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (i != 0) {
                RegisterStepOne.this.countryText.setVisibility(null);
                if (RegisterStepOne.this.countriesAdapter.getItem(RegisterStepOne.this.countryEdit.getSelectedItemPosition()).getCode().equals("PL") != null) {
                    RegisterStepOne.this.zipCodeEdit.addTextChangedListener(new MaskWatcherZipCode("##-###"));
                    if (RegisterStepOne.this.zipCodeEdit.getText().toString().matches("^\\d{2}-\\d{3}?") == null) {
                        RegisterStepOne.this.zipCodeEdit.setText("");
                        return;
                    }
                    return;
                }
                return;
            }
            RegisterStepOne.this.countryText.setVisibility(4);
        }
    }

    public void onBackClick() {
    }

    public void onSettingsClick() {
    }

    public static RegisterStepOne newInstance() {
        return new RegisterStepOne();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(C0434R.layout.fragment_register_step_1, viewGroup, false);
        findViews(layoutInflater);
        setListeners();
        setAdapters2();
        return layoutInflater;
    }

    private void findViews(View view) {
        this.keepGoing = (Button) view.findViewById(C0434R.id.fragment_register_step_one_keep_going_button);
        this.firstNameEdit = (EditText) view.findViewById(C0434R.id.fragment_register_first_name);
        this.firstNameEditContainer = (CustomInput) view.findViewById(C0434R.id.fragment_register_first_name_container);
        this.lastNameEdit = (EditText) view.findViewById(C0434R.id.fragment_register_last_name);
        this.lastNameEditContainer = (CustomInput) view.findViewById(C0434R.id.fragment_register_last_name_container);
        this.emailEdit = (EditText) view.findViewById(C0434R.id.fragment_register_email);
        this.emailEditContainer = (CustomInput) view.findViewById(C0434R.id.fragment_register_email_container);
        this.cityEdit = (EditText) view.findViewById(C0434R.id.fragment_register_city);
        this.cityEditContainer = (CustomInput) view.findViewById(C0434R.id.fragment_register_city_container);
        this.streetEdit = (EditText) view.findViewById(C0434R.id.fragment_register_street);
        this.streetEditContainer = (CustomInput) view.findViewById(C0434R.id.fragment_register_street_container);
        this.zipCodeEdit = (EditText) view.findViewById(C0434R.id.fragment_register_zip_code);
        this.zipCodeEditContainer = (CustomInput) view.findViewById(C0434R.id.fragment_register_zip_container);
        this.phoneNumberEdit = (EditText) view.findViewById(C0434R.id.fragment_register_number_edit);
        this.phoneNumberEditContainer = (CustomInput) view.findViewById(C0434R.id.fragment_register_number_container);
        this.peselEdit = (EditText) view.findViewById(C0434R.id.fragment_register_pesel);
        this.peselEditContainer = (CustomInput) view.findViewById(C0434R.id.fragment_register_pesel_container);
        this.countryEditContainer = (CustomInput) view.findViewById(C0434R.id.fragment_register_country_container);
        this.countryEdit = (Spinner) view.findViewById(C0434R.id.fragment_register_country_spinner);
        this.textInputPhone = (TextInputLayout) view.findViewById(C0434R.id.text_input_phone);
        this.textInputEmail = (TextInputLayout) view.findViewById(C0434R.id.text_input_email);
        this.textInputFirstName = (TextInputLayout) view.findViewById(C0434R.id.text_input_first_name);
        this.textInputLastName = (TextInputLayout) view.findViewById(C0434R.id.text_input_last_name);
        this.textInputStreet = (TextInputLayout) view.findViewById(C0434R.id.text_input_street);
        this.textInputZipCode = (TextInputLayout) view.findViewById(C0434R.id.text_input_zip_code);
        this.textInputCity = (TextInputLayout) view.findViewById(C0434R.id.text_input_city);
        this.textInputCountry = (TextInputLayout) view.findViewById(C0434R.id.text_input_country);
        this.textInputPesel = (TextInputLayout) view.findViewById(C0434R.id.text_input_pesel);
        this.spinnerText = (TextView) view.findViewById(C0434R.id.spinner_validator_text);
        this.countryText = (TextView) view.findViewById(C0434R.id.id_text_view);
        this.scrollView = (ScrollView) view.findViewById(C0434R.id.scroll_view);
    }

    private void setAdapters2() {
        List arrayList = new ArrayList(Arrays.asList(LocationData.COUNTRY_CODES));
        arrayList.add(0, new CountryCode(IM.resources().getString(C0434R.string.registration_country_hint), "1"));
        this.countriesAdapter = new CountryAdapter(arrayList);
        this.countryEdit.setAdapter(this.countriesAdapter);
    }

    private void setListeners() {
        this.keepGoing.setOnClickListener(this);
        this.countryEdit.setOnItemSelectedListener(new C04601());
        this.phoneNumberEditContainer.setValidator(null, new RegisterStepOne$$Lambda$0(this));
        this.emailEditContainer.setValidator(null, new RegisterStepOne$$Lambda$1(this));
        this.firstNameEditContainer.setValidator(null, new RegisterStepOne$$Lambda$2(this));
        this.lastNameEditContainer.setValidator(null, new RegisterStepOne$$Lambda$3(this));
        this.streetEditContainer.setValidator(null, new RegisterStepOne$$Lambda$4(this));
        this.zipCodeEditContainer.setValidator(null, new RegisterStepOne$$Lambda$5(this));
        this.cityEditContainer.setValidator(null, new RegisterStepOne$$Lambda$6(this));
        this.countryEditContainer.setValidator(null, new RegisterStepOne$$Lambda$7(this));
        this.phoneNumberEdit.setOnFocusChangeListener(new RegisterStepOne$$Lambda$8(this));
        this.emailEdit.setOnFocusChangeListener(new RegisterStepOne$$Lambda$9(this));
        this.firstNameEdit.setOnFocusChangeListener(new RegisterStepOne$$Lambda$10(this));
        this.lastNameEdit.setOnFocusChangeListener(new RegisterStepOne$$Lambda$11(this));
        this.streetEdit.setOnFocusChangeListener(new RegisterStepOne$$Lambda$12(this));
        this.zipCodeEdit.setOnFocusChangeListener(new RegisterStepOne$$Lambda$13(this));
        this.cityEdit.setOnFocusChangeListener(new RegisterStepOne$$Lambda$14(this));
        this.countryEdit.setOnFocusChangeListener(new RegisterStepOne$$Lambda$15(this));
        this.peselEdit.setOnFocusChangeListener(new RegisterStepOne$$Lambda$16(this));
        this.peselEditContainer.setValidator(null, new RegisterStepOne$$Lambda$17(this));
    }

    final /* synthetic */ Pair lambda$setListeners$305$RegisterStepOne(String str) {
        if (Validator.isPhoneNumberValid(str) != null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.login_screen_wrong_phone_number) : "");
    }

    final /* synthetic */ Pair lambda$setListeners$306$RegisterStepOne(String str) {
        if (Validator.isEmailValid(str) != null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.email_error) : "");
    }

    final /* synthetic */ Pair lambda$setListeners$307$RegisterStepOne(String str) {
        if (Validator.isFirstNameValid(str) != null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.first_name_error) : "");
    }

    final /* synthetic */ Pair lambda$setListeners$308$RegisterStepOne(String str) {
        if (Validator.isLastNameValid(str) != null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.last_name_error) : "");
    }

    final /* synthetic */ Pair lambda$setListeners$309$RegisterStepOne(String str) {
        if (Validator.isStreetValid(str) != null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.street_error) : "");
    }

    final /* synthetic */ Pair lambda$setListeners$310$RegisterStepOne(String str) {
        if (this.countriesAdapter.getItem(this.countryEdit.getSelectedItemPosition()).getCode().equals("PL")) {
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

    final /* synthetic */ Pair lambda$setListeners$311$RegisterStepOne(String str) {
        if (Validator.isCityValid(str) != null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.city_error) : "");
    }

    final /* synthetic */ Pair lambda$setListeners$312$RegisterStepOne(String str) {
        if (Validator.isCountryValid(str) != null) {
            return new Pair(Boolean.valueOf(true), null);
        }
        return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.country_error) : "");
    }

    final /* synthetic */ void lambda$setListeners$313$RegisterStepOne(View view, boolean z) {
        if (z) {
            this.textInputPhone.setHint(IM.resources().getString(C0434R.string.phone_number_hint));
        } else {
            this.textInputPhone.setHint(IM.resources().getString(C0434R.string.registration_phone_number_hint));
        }
    }

    final /* synthetic */ void lambda$setListeners$314$RegisterStepOne(View view, boolean z) {
        if (z) {
            this.textInputEmail.setHint(IM.resources().getString(C0434R.string.email_address_hint));
        } else {
            this.textInputEmail.setHint(IM.resources().getString(C0434R.string.registration_email_address_hint));
        }
    }

    final /* synthetic */ void lambda$setListeners$315$RegisterStepOne(View view, boolean z) {
        if (z) {
            this.textInputFirstName.setHint(IM.resources().getString(C0434R.string.first_name_hint));
        } else {
            this.textInputFirstName.setHint(IM.resources().getString(C0434R.string.registration_first_name_hint));
        }
    }

    final /* synthetic */ void lambda$setListeners$316$RegisterStepOne(View view, boolean z) {
        if (z) {
            this.textInputLastName.setHint(IM.resources().getString(C0434R.string.last_name_hint));
        } else {
            this.textInputLastName.setHint(IM.resources().getString(C0434R.string.registration_last_name_hint));
        }
    }

    final /* synthetic */ void lambda$setListeners$317$RegisterStepOne(View view, boolean z) {
        if (z) {
            this.textInputStreet.setHint(IM.resources().getString(C0434R.string.address_hint));
        } else {
            this.textInputStreet.setHint(IM.resources().getString(C0434R.string.registration_address_hint));
        }
    }

    final /* synthetic */ void lambda$setListeners$318$RegisterStepOne(View view, boolean z) {
        if (z) {
            this.textInputZipCode.setHint(IM.resources().getString(C0434R.string.zip_code_hint));
        } else {
            this.textInputZipCode.setHint(IM.resources().getString(C0434R.string.registration_zip_code_hint));
        }
    }

    final /* synthetic */ void lambda$setListeners$319$RegisterStepOne(View view, boolean z) {
        if (z) {
            this.textInputCity.setHint(IM.resources().getString(C0434R.string.city_hint));
        } else {
            this.textInputCity.setHint(IM.resources().getString(C0434R.string.registration_city_hint));
        }
    }

    final /* synthetic */ void lambda$setListeners$320$RegisterStepOne(View view, boolean z) {
        if (z) {
            this.textInputCountry.setHint("");
        } else {
            this.textInputCountry.setHint(IM.resources().getString(C0434R.string.country_hint));
        }
    }

    final /* synthetic */ void lambda$setListeners$321$RegisterStepOne(View view, boolean z) {
        if (z) {
            this.textInputPesel.setHint(IM.resources().getString(C0434R.string.pesel_hint));
        } else {
            this.textInputPesel.setHint(IM.resources().getString(C0434R.string.registration_pesel_hint));
        }
    }

    final /* synthetic */ Pair lambda$setListeners$322$RegisterStepOne(String str) {
        if (!isSelectedCountryPl() && TextUtils.isEmpty(str)) {
            return new Pair(Boolean.valueOf(true), null);
        }
        if (Validator.isPeselValid(str) && Validator.isPeselYearsOld(str, 13)) {
            return new Pair(Boolean.valueOf(true), null);
        }
        if (Validator.isPeselValid(str) == null) {
            return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.pesel_error) : "");
        }
        return new Pair(Boolean.valueOf(false), getContext() != null ? getContext().getString(C0434R.string.pesel_min_age, new Object[]{BikeTypes.USE_BIKE_CLASSIC}) : "");
    }

    private boolean isSelectedCountryPl() {
        if (!"PL".equals(this.countriesAdapter.getItem(this.countryEdit.getSelectedItemPosition()).getCode())) {
            if (!"1".equals(this.countriesAdapter.getItem(this.countryEdit.getSelectedItemPosition()).getCode())) {
                return false;
            }
        }
        return true;
    }

    public void onClick(View view) {
        if (getActivityInterface() != null) {
            if (view.getId() == C0434R.id.fragment_register_step_one_keep_going_button) {
                UiTools.hideSoftInput(view);
                setDataAndProceed();
            }
        }
    }

    private boolean checkAllFieldsValid() {
        this.phoneNumberEditContainer.checkValid();
        this.emailEditContainer.checkValid();
        this.firstNameEditContainer.checkValid();
        this.lastNameEditContainer.checkValid();
        this.streetEditContainer.checkValid();
        this.cityEditContainer.checkValid();
        this.zipCodeEditContainer.checkValid();
        this.peselEditContainer.checkValid();
        checkSpinnersValid();
        return this.phoneNumberEditContainer.isValid().booleanValue() && this.emailEditContainer.isValid().booleanValue() && this.firstNameEditContainer.isValid().booleanValue() && this.lastNameEditContainer.isValid().booleanValue() && this.zipCodeEditContainer.isValid().booleanValue() && this.streetEditContainer.isValid().booleanValue() && this.cityEditContainer.isValid().booleanValue() && this.peselEditContainer.isValid().booleanValue();
    }

    private void setDataAndProceed() {
        if (getActivityInterface() != null) {
            if (checkAllFieldsValid()) {
                RegistrationData registrationData = getActivityInterface().getRegistrationData();
                registrationData.firstname = this.firstNameEdit.getText().toString().trim();
                registrationData.lastname = this.lastNameEdit.getText().toString().trim();
                registrationData.email = this.emailEdit.getText().toString().trim();
                registrationData.city = this.cityEdit.getText().toString().trim();
                registrationData.streetAdress = this.streetEdit.getText().toString().trim();
                registrationData.zipCode = this.zipCodeEdit.getText().toString().trim();
                registrationData.country = this.countriesAdapter.getItem(this.countryEdit.getSelectedItemPosition()).getCode();
                registrationData.phoneNumber = this.phoneNumberEdit.getText().toString().trim();
                registrationData.pesel = this.peselEdit.getText().toString().trim();
                getActivityInterface().changeFragment(RegisterStepTwo.newInstance());
                return;
            }
            scrollToFirstError();
        }
    }

    private void scrollToFirstError() {
        Iterable arrayList = new ArrayList();
        arrayList.add(this.phoneNumberEditContainer);
        arrayList.add(this.emailEditContainer);
        arrayList.add(this.firstNameEditContainer);
        arrayList.add(this.lastNameEditContainer);
        arrayList.add(this.streetEditContainer);
        arrayList.add(this.zipCodeEditContainer);
        arrayList.add(this.cityEditContainer);
        arrayList.add(this.peselEditContainer);
        CustomInput customInput = (CustomInput) Stream.of(arrayList).filter(RegisterStepOne$$Lambda$18.$instance).findFirst().orElse(null);
        if (customInput != null) {
            this.scrollView.scrollTo(0, customInput.getTop());
            customInput.requestFocus();
        }
    }

    public void onContactClick() {
        if (getActivityInterface() != null) {
            getActivityInterface().changeFragment(ContactUsFragment.newInstance(), true);
        }
    }

    private void checkSpinnersValid() {
        if (this.countriesAdapter.getItem(this.countryEdit.getSelectedItemPosition()).getCode().equals("1")) {
            this.countryEdit.setBackground(ResourcesCompat.getDrawable(IM.context().getResources(), C0434R.drawable.custom_input_invalid_edit_text_bg, null));
            this.spinnerText.setPadding(Screen.dpToPx(12.0f, IM.context()), 0, 0, 0);
            this.spinnerText.setTextSize(0, IM.context().getResources().getDimension(C0434R.dimen.text_size_input_error));
            this.spinnerText.setVisibility(0);
            this.spinnerText.setText(IM.resources().getString(C0434R.string.country_error));
            return;
        }
        this.countryEdit.setBackground(ResourcesCompat.getDrawable(IM.context().getResources(), C0434R.drawable.custom_input_valid_edit_text_bg, null));
        this.spinnerText.setVisibility(8);
    }
}
