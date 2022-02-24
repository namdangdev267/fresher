package vn.com.misa.cukcukstarterclone.ui.report;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.ui.report.details.ReportDetailsFragment;
import vn.com.misa.cukcukstarterclone.ui.report.overall.ReportOverallFragment;
import vn.com.misa.cukcukstarterclone.utils.Utils;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_report);

        initViews();
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        TextView tvReportOverall = findViewById(R.id.tvReportOverall);
        TextView tvReportDetails = findViewById(R.id.tvReportDetails);

        tvReportOverall.setOnClickListener(v -> showReportOverall());
        tvReportDetails.setOnClickListener(v -> showReportDetails());
    }

    private void showReportOverall() {
        try {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.root, new ReportOverallFragment(), ReportOverallFragment.TAG).addToBackStack(ReportOverallFragment.TAG);
            fragmentTransaction.commit();
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    private void showReportDetails() {
        try {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.root, new ReportDetailsFragment(), ReportDetailsFragment.TAG).addToBackStack(ReportDetailsFragment.TAG);
            fragmentTransaction.commit();
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }
}
