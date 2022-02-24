package vn.com.misa.cukcukstarterclone.ui.main;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.data.model.Cart;
import vn.com.misa.cukcukstarterclone.ui.login.LoginActivity;
import vn.com.misa.cukcukstarterclone.ui.main.addcartitem.NewCartItemFragment;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.AddOrderFragment;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.MenuItemDto;
import vn.com.misa.cukcukstarterclone.ui.main.checkout.CheckoutFragment;
import vn.com.misa.cukcukstarterclone.ui.main.listorders.ListOrdersFragment;
import vn.com.misa.cukcukstarterclone.ui.menu.MenuActivity;
import vn.com.misa.cukcukstarterclone.ui.order.OrderActivity;
import vn.com.misa.cukcukstarterclone.ui.report.ReportActivity;
import vn.com.misa.cukcukstarterclone.utils.SharedPreferenceHelper;
import vn.com.misa.cukcukstarterclone.utils.Utils;

public class MainActivity extends AppCompatActivity
        implements ListOrdersFragment.IListOrdersFragmentCallback,
        AddOrderFragment.IAddOrderFragmentListener {

    private ActionBarDrawerToggle drawerToggle;
    private TextView tvUsername;
    private TextView tvOrderList;
    private TextView tvMenuItems;
    private TextView tvFavServe;
    private TextView tvReport;
    private TextView tvSettings;
    private TextView tvSync;
    private TextView tvSecurity;
    private TextView tvShare;
    private TextView tvRating;
    private TextView tvReportBug;
    private TextView tvInfo;
    private TextView tvSignOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        initData();
    }

    protected void bindViews() {
        try {
            DrawerLayout drawerLayout = findViewById(R.id.drawableLayout);
            drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                    try {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (getCurrentFocus().getWindowToken() != null) {
                            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                        }
                    } catch (Exception e) {
                        Utils.handleException(e);
                    }
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);

                    try {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {
                        Utils.handleException(e);
                    }
                }
            };
            drawerLayout.addDrawerListener(drawerToggle);
            drawerToggle.syncState();

            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setTitle("");
            setSupportActionBar(toolbar);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

            tvUsername = findViewById(R.id.tvUsername);
            tvOrderList = findViewById(R.id.tvOrderList);
            tvMenuItems = findViewById(R.id.tvMenuItems);
            tvFavServe = findViewById(R.id.tvFavServe);
            tvReport = findViewById(R.id.tvReport);
            tvSettings = findViewById(R.id.tvSettings);
            tvSync = findViewById(R.id.tvSync);
            tvSecurity = findViewById(R.id.tvSecurity);
            tvShare = findViewById(R.id.tvShare);
            tvRating = findViewById(R.id.tvRating);
            tvReportBug = findViewById(R.id.tvReportBug);
            tvInfo = findViewById(R.id.tvInfo);
            tvSignOut = findViewById(R.id.tvSignOut);

            tvOrderList.setOnClickListener(v -> {
                drawerLayout.closeDrawers();
                startActivity(new Intent(this, OrderActivity.class));
            });
            tvMenuItems.setOnClickListener(v -> {
                drawerLayout.closeDrawers();
                startActivity(new Intent(this, MenuActivity.class));
            });
            tvReport.setOnClickListener(v -> {
                drawerLayout.closeDrawers();
                startActivity(new Intent(this, ReportActivity.class));
            });
            tvSettings.setOnClickListener(v -> {
                
            });
            tvSecurity.setOnClickListener(v -> {
                drawerLayout.closeDrawers();
                String url = getString(R.string.app_url);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            });
            tvShare.setOnClickListener(v -> {
                drawerLayout.closeDrawers();
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.string_share_app));
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Title");
                startActivity(Intent.createChooser(shareIntent, "Share..."));
            });
            tvRating.setOnClickListener(v -> {
                drawerLayout.closeDrawers();
                final String appPackageName = getString(R.string.app_package_name);
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.play_store_url) + appPackageName)));
                }
            });
            tvReportBug.setOnClickListener(v -> {
                drawerLayout.closeDrawers();
                String[] toEmails = {getString(R.string.email_support)};
                String[] ccEmails = {getString(R.string.email_cshk)};
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, toEmails);
                intent.putExtra(Intent.EXTRA_CC, ccEmails);
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.string_mail_title));
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.string_email_body));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            });
            tvSignOut.setOnClickListener(v -> {
                SharedPreferenceHelper.setSharedPreferenceBoolean(this, SharedPreferenceHelper.KEY_LOGIN, false);
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            });
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void initData() {
        try {
            ListOrdersFragment orderListFragment = new ListOrdersFragment();
            orderListFragment.setCallback(this);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.detailsContainer, orderListFragment);
            fragmentTransaction.commit();

            FirebaseAuth auth = FirebaseAuth.getInstance();
            tvUsername.setText(auth.getCurrentUser().getEmail().split("@")[0]);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void startNewCart() {
        AddOrderFragment addOrderFragment = AddOrderFragment.newInstance(null, false);
        addOrderFragment.setCallback(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.drawableLayout, addOrderFragment, AddOrderFragment.TAG).addToBackStack(AddOrderFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void showCartDetails(Cart cart) {
        AddOrderFragment addOrderFragment = AddOrderFragment.newInstance(cart, false);
        addOrderFragment.setCallback(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.drawableLayout, addOrderFragment, AddOrderFragment.TAG).addToBackStack(AddOrderFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void checkoutCart(Cart cart) {
        AddOrderFragment addOrderFragment = AddOrderFragment.newInstance(cart, true);
        addOrderFragment.setCallback(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.drawableLayout, addOrderFragment, AddOrderFragment.TAG).addToBackStack(AddOrderFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void addNewItemCart(MenuItemDto itemDto, String carId) {
        NewCartItemFragment newCartItemFragment = NewCartItemFragment.newInstance(itemDto, carId);
        newCartItemFragment.show(getSupportFragmentManager(), NewCartItemFragment.TAG);
    }

    @Override
    public void checkout(Cart cart) {
        CheckoutFragment checkoutFragment = CheckoutFragment.newInstance(cart);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.drawableLayout, checkoutFragment, CheckoutFragment.TAG).addToBackStack(CheckoutFragment.TAG);
        fragmentTransaction.commit();
    }
}
