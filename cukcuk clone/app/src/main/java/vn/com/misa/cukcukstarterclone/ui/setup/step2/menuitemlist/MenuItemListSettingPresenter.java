package vn.com.misa.cukcukstarterclone.ui.setup.step2.menuitemlist;

import vn.com.misa.cukcukstarterclone.ui.setup.step2.menuitemlist.MenuItemListSettingContract.View;

/**
 * @created_by KhanhNQ on 13-Jan-21.
 */
public class MenuItemListSettingPresenter implements MenuItemListSettingContract.Presenter {

    private View view;

    /**
     * - Mục đích method: Gắn view vào presenter
     * - Sử dụng khi: View vừa được khởi tạo
     *
     * @param view View implement {@link View}
     * @created_by KhanhNQ on 14-Jan-21
     */
    @Override
    public void attach(View view) {
        this.view = view;
    }

    /**
     * - Mục đích method: Bỏ liên kết giữa view và presenter
     * - Sử dụng khi: view bị hủy
     *
     * @created_by KhanhNQ on 14-Jan-21
     */
    @Override
    public void detach() {
        view = null;
    }
}
