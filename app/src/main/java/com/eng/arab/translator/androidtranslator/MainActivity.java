/*
 * Copyright (c) 2016 Richard C.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eng.arab.translator.androidtranslator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.eng.arab.translator.androidtranslator.activity.AlphabetViewActivity;
import com.eng.arab.translator.androidtranslator.activity.DictionaryViewActivity;
import com.eng.arab.translator.androidtranslator.activity.NumberViewActivity;
import com.eng.arab.translator.androidtranslator.translate.TranslateViewActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int SETTINGS_ACTIVITY_ID = 1;
    private static final String VERSION = BuildConfig.VERSION_NAME;

    private SharedPreferences preferences;

    private Spinner actionBarSpinner;

    private LinearLayout srcContent;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LinearLayout mainPanel = (LinearLayout) findViewById(R.id.translate_layout);
        setOnclickListenerCards(mainPanel.getRootView());

        // Set Build Version Below
        TextView textViewVersion = (TextView) findViewById(R.id.textViewVersion);
        textViewVersion.setText("V" + VERSION);

        adModSetUp();
    }

    private void adModSetUp() {
        MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.banner_ad_unit_id));
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
    }

    /* HOW to use this Override */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        //mDrawerToggle.syncState();
    }

    /* HOW to use this Override */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void changeKeyboard() {
        String languageToLoad = "ar"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }


    private void setUpTranslatorLayout() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);


//        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_actionbar);
//        setSupportActionBar(toolbar);
//        final Context context = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? this : getSupportActionBar().getThemedContext();
//        final LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        actionBarSpinner = (Spinner)inflater.inflate(R.layout.actionbar_spinner, null);
////        actionBarSpinner.setOnItemSelectedListener(this);
//        toolbar.addView(actionBarSpinner);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
/*
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_about:
                aboutFragment();
                return true;
            default:

        }*/
        return super.onOptionsItemSelected(item);
    }

    /***************
     * FRAGMENT CALL
     ******************/
    public void translateFragment() {
        startActivity(new Intent(this, TranslateViewActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    public void dictionaryFragment() {
        Intent i = new Intent(MainActivity.this, DictionaryViewActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    public void alphabetFragment() {
        Intent i = new Intent(MainActivity.this, AlphabetViewActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    public void numberFragment() {
        Intent i = new Intent(MainActivity.this, NumberViewActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    public void monthFragment() {

    }

    public void helpFragment() {
//        Intent i = new Intent(MainActivity.this, VoiceActivity.class);
//        startActivity(i);
    }

    public void aboutFragment() {
        //startActivity(new Intent(this, AboutFragment.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//        overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
        showAboutDialog();
    }

    /*
    *
     * Minimum - Jelly Bean - 4.1.x - API level 16
    *
    */
    public void showAboutDialog() {

        new MaterialDialog.Builder(this)
                .title(getString(R.string.about_dialog_title, VERSION))
                .positiveText(R.string.dismiss)
                .content(Html.fromHtml(getString(R.string.about_body)))
                .iconRes(R.drawable.ic_translate)
                .show();
    }

    /*
        Onlcick Listeers for the Cardviews
    **/
    private void setOnclickListenerCards(View view) {
        LinearLayout cv_translate = (LinearLayout) view.findViewById(R.id.cv_translator);
        cv_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                translateFragment();
            }
        });
        LinearLayout cv_dictionary = (LinearLayout) view.findViewById(R.id.cv_dictionary);
        cv_dictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dictionaryFragment();
            }
        });
        LinearLayout cv_alphabets = (LinearLayout) view.findViewById(R.id.cv_alphabet);
        cv_alphabets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alphabetFragment();
            }
        });
        LinearLayout cv_numbers = (LinearLayout) view.findViewById(R.id.cv_number);
        cv_numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberFragment();
            }
        });
//        LinearLayout cv_months = (LinearLayout) view.findViewById(R.id.cv_month);
//        cv_months.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                monthFragment();
//            }
//        });
        LinearLayout cv_about_us = (LinearLayout) view.findViewById(R.id.cv_about_us);
        cv_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutFragment();
            }
        });
    }
}
