package vn.com.misa.cukcukstarterclone.ui.setup.step1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.data.model.BusinessType;
import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * @created_by KhanhNQ on 12-Jan-21.
 */
public class SelectBusinessTypeFragment extends Fragment {

    private ISelectBusinessTypeCallback callback;

    public SelectBusinessTypeFragment() {
    }

    public static SelectBusinessTypeFragment newInstance(ISelectBusinessTypeCallback callback) {
        SelectBusinessTypeFragment fragment = new SelectBusinessTypeFragment();
        fragment.callback = callback;
        return fragment;
    }

    private final BusinessTypeAdapter businessAdapter = new BusinessTypeAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_business_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initData();
    }

    private void initView(View view) {
        try {
            RecyclerView rcvBusinessType = view.findViewById(R.id.rcvBusinessType);
            businessAdapter.setItemClickListener((item, position) -> {
                if (item.isAvailable()) goToStep2(item);
            });
            rcvBusinessType.setAdapter(businessAdapter);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    private void initData() {
        try {
            List<BusinessType> types = new ArrayList<>();
            types.add(new BusinessType(
                    0,
                    "Quán Cafe",
                    R.drawable.ic_res_type_cafe,
                    true));
            types.add(new BusinessType(
                    1,
                    "Quán Trà sữa",
                    R.drawable.ic_res_type_trasua,
                    true));
            types.add(new BusinessType(
                    2,
                    "Bún phở miến",
                    R.drawable.ic_res_type_bunmien,
                    true));
            types.add(new BusinessType(
                    3,
                    "Cơm văn phòng",
                    R.drawable.ic_res_type_comvanphong,
                    false));
            types.add(new BusinessType(
                    4,
                    "Chè kem/ăn vặt",
                    R.drawable.ic_res_type_chekemanvat,
                    false));
            types.add(new BusinessType(
                    5,
                    "Quán bánh mì",
                    R.drawable.ic_res_type_banhmi,
                    false));
            businessAdapter.loadItems(types);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    private void goToStep2(BusinessType businessType) {
        callback.selectedType(businessType);
    }

    public interface ISelectBusinessTypeCallback {
        void selectedType(BusinessType type);
    }
}
