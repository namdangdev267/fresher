package vn.com.misa.cukcukstarterclone.ui.setup.step2.event;

import vn.com.misa.cukcukstarterclone.data.model.MenuItem;

/**
 * - Mục đích Class: Lắng nghe sự kiện thêm mới một MenuItem
 *
 * @created_by KhanhNQ on 22-Jan-21.
 */
public class AddNewMenuItemEvent {
    public final MenuItem item;

    public AddNewMenuItemEvent(MenuItem item) {
        this.item = item;
    }
}
