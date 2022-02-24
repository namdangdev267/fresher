package vn.com.misa.cukcukstarterclone.ui.setup.step2.event;

import vn.com.misa.cukcukstarterclone.data.model.MenuItem;

/**
 * - Mục đích Class: Lắng nghe sự kiện MenuItem thay đổi thông tin
 *
 * @created_by KhanhNQ on 21-Jan-21.
 */
public class ModifyMenuItemEvent {
    public final MenuItem item;

    public ModifyMenuItemEvent(MenuItem item) {
        this.item = item;
    }
}
