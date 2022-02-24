package vn.com.misa.cukcukstarterclone.ui.setup;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseActivity;
import vn.com.misa.cukcukstarterclone.data.model.BusinessType;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.ui.main.MainActivity;
import vn.com.misa.cukcukstarterclone.ui.setup.step1.SelectBusinessTypeFragment;
import vn.com.misa.cukcukstarterclone.ui.setup.step2.SetUpMenuFragment;
import vn.com.misa.cukcukstarterclone.ui.setup.step2.menuitemdetails.MenuItemDetailsFragment;
import vn.com.misa.cukcukstarterclone.ui.setup.step2.menuitemlist.MenuItemListSettingFragment;
import vn.com.misa.cukcukstarterclone.ui.setup.step3.FinishSetupFragment;
import vn.com.misa.cukcukstarterclone.utils.SharedPreferenceHelper;

/**
 * @created_by KhanhNQ on 12-Jan-21
 */
public class SetupActivity extends AppCompatActivity
        implements SelectBusinessTypeFragment.ISelectBusinessTypeCallback,
        SetUpMenuFragment.ISetUpMenuCallback,
        FinishSetupFragment.IFinishSetUpCallback,
        MenuItemListSettingFragment.IOnModifyMenuItem {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        initData();
    }

    private void initData() {
        initStep1Fragment();
    }

    /**
     * - Mục đích method: Hiển thị màn hình chọn loại quán
     * - Sử dụng khi: Bắt đầu vào app
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    private void initStep1Fragment() {
        Fragment step1Fragment = SelectBusinessTypeFragment.newInstance(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.flContainer, step1Fragment, "Step 1");
        fragmentTransaction.commit();
    }

    /**
     * - Mục đích method: Hiển thị màn hình setup món ăn
     *
     * @param type loại quán
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public void selectedType(BusinessType type) {
        Fragment step2Fragment = SetUpMenuFragment.newInstance(type, this, this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.flContainer, step2Fragment, SetUpMenuFragment.TAG).addToBackStack(SetUpMenuFragment.TAG);
        fragmentTransaction.commit();
    }

    /**
     * - Mục đích method: Hiển thị màn hình thêm mới món ăn
     * - Sử dụng khi: callback từ màn hình setup món ăn được gọi
     *
     * @param menuGroups danh sách nhóm món ăn
     * @created_by KhanhNQ on 13-Jan-21
     */
    @Override
    public void addNewItem(ArrayList<MenuGroup> menuGroups) {
        Fragment fragmentModifyItem = MenuItemDetailsFragment.newInstance(null, menuGroups);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.flContainer, fragmentModifyItem, MenuItemDetailsFragment.TAG).addToBackStack(MenuItemDetailsFragment.TAG);
        fragmentTransaction.commit();
    }

    /**
     * - Mục đích method: Hiển thị màn hình hoàn thành setup quán
     * - Sử dụng khi: callback từ màn hình setup món ăn được gọi
     *
     * @param menuGroups danh sách nhóm món ăn
     * @param menuItems  danh sách món ăn
     * @created_by KhanhNQ on 13-Jan-21
     */
    @Override
    public void modifiedMenu(List<MenuGroup> menuGroups, List<MenuItem> menuItems) {
        Fragment step3Fragment = FinishSetupFragment.newInstance(menuGroups, menuItems, this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.flContainer, step3Fragment, FinishSetupFragment.TAG).addToBackStack(FinishSetupFragment.TAG);
        fragmentTransaction.commit();
    }

    /**
     * - Mục đích method: Chuyển sang MainActivity sau khi hoàn thành setup quán
     * - Sử dụng khi: callback từ màn hình hoàn thành setup được gọi
     *
     * @created_by KhanhNQ on 14-Jan-21
     */
    @Override
    public void finishSetup() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        SharedPreferenceHelper.setSharedPreferenceBoolean(this, SharedPreferenceHelper.KEY_INIT_DATABASE, true);

        this.finish();
    }

    @Override
    public void showItemDetails(MenuItem menuItem, ArrayList<MenuGroup> menuGroups) {
        Fragment fragmentModifyItem = MenuItemDetailsFragment.newInstance(menuItem, menuGroups);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.flContainer, fragmentModifyItem, MenuItemDetailsFragment.TAG).addToBackStack(MenuItemDetailsFragment.TAG);
        fragmentTransaction.commit();
    }
}
