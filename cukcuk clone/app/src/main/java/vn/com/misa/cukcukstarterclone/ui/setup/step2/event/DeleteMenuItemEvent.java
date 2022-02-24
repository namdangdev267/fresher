package vn.com.misa.cukcukstarterclone.ui.setup.step2.event;

import vn.com.misa.cukcukstarterclone.data.model.MenuItem;

/**
 * - Mục đích Class: Lắng nghe sự kiện xoá một MenuItem
 *
 * @created_by KhanhNQ on 22-Jan-21.
 */
public class DeleteMenuItemEvent {
    public final MenuItem item;

    public DeleteMenuItemEvent(MenuItem item) {
        this.item = item;
    }
}
