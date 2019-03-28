package com.mevo.app.constants;

import com.google.zxing.client.result.ExpandedProductParsedResult;
import com.mevo.app.data.model.CountryCode;
import com.mevo.app.data.model.LanguageCode;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;

public class LocationData {
    public static final CountryCode[] COUNTRY_CODES = new CountryCode[]{new CountryCode("Poland", "PL"), new CountryCode("Afghanistan", "AF"), new CountryCode("Åland Islands", "AX"), new CountryCode("Albania", "AL"), new CountryCode("Algeria", "DZ"), new CountryCode("American Samoa", "AS"), new CountryCode("Andorra", "AD"), new CountryCode("Angola", "AO"), new CountryCode("Anguilla", "AI"), new CountryCode("Antarctica", "AQ"), new CountryCode("Antigua and Barbuda", "AG"), new CountryCode("Argentina", "AR"), new CountryCode("Armenia", "AM"), new CountryCode("Aruba", "AW"), new CountryCode("Australia", "AU"), new CountryCode("Austria", "AT"), new CountryCode("Azerbaijan", "AZ"), new CountryCode("Bahamas", "BS"), new CountryCode("Bahrain", "BH"), new CountryCode("Bangladesh", "BD"), new CountryCode("Barbados", "BB"), new CountryCode("Belarus", "BY"), new CountryCode("Belgium", "BE"), new CountryCode("Belize", "BZ"), new CountryCode("Benin", "BJ"), new CountryCode("Bermuda", "BM"), new CountryCode("Bhutan", "BT"), new CountryCode("Bolivia (Plurinational State of)", "BO"), new CountryCode("Bonaire, Sint Eustatius and Saba", "BQ"), new CountryCode("Bosnia and Herzegovina", "BA"), new CountryCode("Botswana", "BW"), new CountryCode("Bouvet Island", "BV"), new CountryCode("Brazil", "BR"), new CountryCode("British Indian Ocean Territory", "IO"), new CountryCode("Brunei Darussalam", "BN"), new CountryCode("Bulgaria", "BG"), new CountryCode("Burkina Faso", "BF"), new CountryCode("Burundi", "BI"), new CountryCode("Cabo Verde", "CV"), new CountryCode("Cambodia", "KH"), new CountryCode("Cameroon", "CM"), new CountryCode("Canada", "CA"), new CountryCode("Cayman Islands", "KY"), new CountryCode("Central African Republic", "CF"), new CountryCode("Chad", "TD"), new CountryCode("Chile", "CL"), new CountryCode("China", "CN"), new CountryCode("Christmas Island", "CX"), new CountryCode("Cocos (Keeling) Islands", "CC"), new CountryCode("Colombia", "CO"), new CountryCode("Comoros", "KM"), new CountryCode("Congo", "CG"), new CountryCode("Congo (Democratic Republic of the)", "CD"), new CountryCode("Cook Islands", "CK"), new CountryCode("Costa Rica", "CR"), new CountryCode("Côte d'Ivoire", "CI"), new CountryCode("Croatia", "HR"), new CountryCode("Cuba", "CU"), new CountryCode("Curaçao", "CW"), new CountryCode("Cyprus", "CY"), new CountryCode("Czechia", "CZ"), new CountryCode("Denmark", "DK"), new CountryCode("Djibouti", "DJ"), new CountryCode("Dominica", "DM"), new CountryCode("Dominican Republic", "DO"), new CountryCode("Ecuador", "EC"), new CountryCode("Egypt", "EG"), new CountryCode("El Salvador", "SV"), new CountryCode("Equatorial Guinea", "GQ"), new CountryCode("Eritrea", "ER"), new CountryCode("Estonia", "EE"), new CountryCode("Ethiopia", "ET"), new CountryCode("Falkland Islands (Malvinas)", "FK"), new CountryCode("Faroe Islands", "FO"), new CountryCode("Fiji", "FJ"), new CountryCode("Finland", "FI"), new CountryCode("France", "FR"), new CountryCode("French Guiana", "GF"), new CountryCode("French Polynesia", "PF"), new CountryCode("French Southern Territories", "TF"), new CountryCode("Gabon", "GA"), new CountryCode("Gambia", "GM"), new CountryCode("Georgia", "GE"), new CountryCode("Germany", "DE"), new CountryCode("Ghana", "GH"), new CountryCode("Gibraltar", "GI"), new CountryCode("Greece", "GR"), new CountryCode("Greenland", "GL"), new CountryCode("Grenada", "GD"), new CountryCode("Guadeloupe", "GP"), new CountryCode("Guam", "GU"), new CountryCode("Guatemala", "GT"), new CountryCode("Guernsey", "GG"), new CountryCode("Guinea", "GN"), new CountryCode("Guinea-Bissau", "GW"), new CountryCode("Guyana", "GY"), new CountryCode("Haiti", "HT"), new CountryCode("Heard Island and McDonald Islands", "HM"), new CountryCode("Holy See", "VA"), new CountryCode("Honduras", "HN"), new CountryCode("Hong Kong", "HK"), new CountryCode("Hungary", "HU"), new CountryCode("Iceland", "IS"), new CountryCode("India", Operation.IN), new CountryCode("Indonesia", "ID"), new CountryCode("Iran (Islamic Republic of)", "IR"), new CountryCode("Iraq", "IQ"), new CountryCode("Ireland", "IE"), new CountryCode("Isle of Man", "IM"), new CountryCode("Israel", "IL"), new CountryCode("Italy", "IT"), new CountryCode("Jamaica", "JM"), new CountryCode("Japan", "JP"), new CountryCode("Jersey", "JE"), new CountryCode("Jordan", "JO"), new CountryCode("Kazakhstan", "KZ"), new CountryCode("Kenya", "KE"), new CountryCode("Kiribati", "KI"), new CountryCode("Korea (Democratic People's Republic of)", "KP"), new CountryCode("Korea (Republic of)", "KR"), new CountryCode("Kuwait", "KW"), new CountryCode("Kyrgyzstan", ExpandedProductParsedResult.KILOGRAM), new CountryCode("Lao People's Democratic Republic", "LA"), new CountryCode("Latvia", "LV"), new CountryCode("Lebanon", ExpandedProductParsedResult.POUND), new CountryCode("Lesotho", "LS"), new CountryCode("Liberia", "LR"), new CountryCode("Libya", "LY"), new CountryCode("Liechtenstein", "LI"), new CountryCode("Lithuania", "LT"), new CountryCode("Luxembourg", "LU"), new CountryCode("Macao", "MO"), new CountryCode("Macedonia (the former Yugoslav Republic of)", "MK"), new CountryCode("Madagascar", "MG"), new CountryCode("Malawi", "MW"), new CountryCode("Malaysia", "MY"), new CountryCode("Maldives", "MV"), new CountryCode("Mali", "ML"), new CountryCode("Malta", "MT"), new CountryCode("Marshall Islands", "MH"), new CountryCode("Martinique", "MQ"), new CountryCode("Mauritania", "MR"), new CountryCode("Mauritius", "MU"), new CountryCode("Mayotte", "YT"), new CountryCode("Mexico", "MX"), new CountryCode("Micronesia (Federated States of)", "FM"), new CountryCode("Moldova (Republic of)", "MD"), new CountryCode("Monaco", "MC"), new CountryCode("Mongolia", "MN"), new CountryCode("Montenegro", "ME"), new CountryCode("Montserrat", "MS"), new CountryCode("Morocco", "MA"), new CountryCode("Mozambique", "MZ"), new CountryCode("Myanmar", "MM"), new CountryCode("Namibia", "NA"), new CountryCode("Nauru", "NR"), new CountryCode("Nepal", "NP"), new CountryCode("Netherlands", "NL"), new CountryCode("New Caledonia", "NC"), new CountryCode("New Zealand", "NZ"), new CountryCode("Nicaragua", "NI"), new CountryCode("Niger", "NE"), new CountryCode("Nigeria", "NG"), new CountryCode("Niue", "NU"), new CountryCode("Norfolk Island", "NF"), new CountryCode("Northern Mariana Islands", "MP"), new CountryCode("Norway", "NO"), new CountryCode("Oman", "OM"), new CountryCode("Pakistan", "PK"), new CountryCode("Palau", "PW"), new CountryCode("Palestine, State of", "PS"), new CountryCode("Panama", "PA"), new CountryCode("Papua New Guinea", "PG"), new CountryCode("Paraguay", "PY"), new CountryCode("Peru", "PE"), new CountryCode("Philippines", "PH"), new CountryCode("Pitcairn", "PN"), new CountryCode("Portugal", "PT"), new CountryCode("Puerto Rico", "PR"), new CountryCode("Qatar", "QA"), new CountryCode("Réunion", "RE"), new CountryCode("Romania", "RO"), new CountryCode("Russian Federation", "RU"), new CountryCode("Rwanda", "RW"), new CountryCode("Saint Barthélemy", "BL"), new CountryCode("Saint Helena, Ascension and Tristan da Cunha", "SH"), new CountryCode("Saint Kitts and Nevis", "KN"), new CountryCode("Saint Lucia", "LC"), new CountryCode("Saint Martin (French part)", "MF"), new CountryCode("Saint Pierre and Miquelon", "PM"), new CountryCode("Saint Vincent and the Grenadines", "VC"), new CountryCode("Samoa", "WS"), new CountryCode("San Marino", "SM"), new CountryCode("Sao Tome and Principe", "ST"), new CountryCode("Saudi Arabia", "SA"), new CountryCode("Senegal", "SN"), new CountryCode("Serbia", "RS"), new CountryCode("Seychelles", "SC"), new CountryCode("Sierra Leone", "SL"), new CountryCode("Singapore", "SG"), new CountryCode("Sint Maarten (Dutch part)", "SX"), new CountryCode("Slovakia", "SK"), new CountryCode("Slovenia", "SI"), new CountryCode("Solomon Islands", "SB"), new CountryCode("Somalia", "SO"), new CountryCode("South Africa", "ZA"), new CountryCode("South Georgia and the South Sandwich Islands", "GS"), new CountryCode("South Sudan", "SS"), new CountryCode("Spain", "ES"), new CountryCode("Sri Lanka", "LK"), new CountryCode("Sudan", "SD"), new CountryCode("Suriname", "SR"), new CountryCode("Svalbard and Jan Mayen", "SJ"), new CountryCode("Swaziland", "SZ"), new CountryCode("Sweden", "SE"), new CountryCode("Switzerland", "CH"), new CountryCode("Syrian Arab Republic", "SY"), new CountryCode("Taiwan, Province of China", "TW"), new CountryCode("Tajikistan", "TJ"), new CountryCode("Tanzania, United Republic of", "TZ"), new CountryCode("Thailand", "TH"), new CountryCode("Timor-Leste", "TL"), new CountryCode("Togo", "TG"), new CountryCode("Tokelau", "TK"), new CountryCode("Tonga", "TO"), new CountryCode("Trinidad and Tobago", "TT"), new CountryCode("Tunisia", "TN"), new CountryCode("Turkey", "TR"), new CountryCode("Turkmenistan", "TM"), new CountryCode("Turks and Caicos Islands", "TC"), new CountryCode("Tuvalu", "TV"), new CountryCode("Uganda", "UG"), new CountryCode("Ukraine", "UA"), new CountryCode("United Arab Emirates", "AE"), new CountryCode("United Kingdom", "GB"), new CountryCode("United States of America", "US"), new CountryCode("United States Minor Outlying Islands", "UM"), new CountryCode("Uruguay", "UY"), new CountryCode("Uzbekistan", "UZ"), new CountryCode("Vanuatu", "VU"), new CountryCode("Venezuela", "VE"), new CountryCode("Viet Nam", "VN"), new CountryCode("Virgin Islands (British)", "VG"), new CountryCode("Virgin Islands (U.S.)", "VI"), new CountryCode("Wallis and Futuna", "WF"), new CountryCode("Western Sahara", "EH"), new CountryCode("Yemen", "YE"), new CountryCode("Zambia", "ZB"), new CountryCode("Zimbabwe", "ZW")};
    public static final LanguageCode[] LANGUAGE_CODES = new LanguageCode[]{new LanguageCode("English", "EN"), new LanguageCode("German", "DE"), new LanguageCode("Polish", "PL"), new LanguageCode("Russian", "RU")};
    public static final String NO_SELECTION_COUNTRY = "1";
}