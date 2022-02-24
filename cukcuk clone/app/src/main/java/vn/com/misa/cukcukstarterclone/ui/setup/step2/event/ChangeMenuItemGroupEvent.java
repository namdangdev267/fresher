package vn.com.misa.cukcukstarterclone.ui.setup.step2.event;

import vn.com.misa.cukcukstarterclone.data.model.MenuItem;

/**
 * - Mục đích Class: Lắng nghe sự kiện MenuItem thay đổi nhóm
 *
 * @created_by KhanhNQ on 21-Jan-21.
 */
public class ChangeMenuItemGroupEvent {
    public final String originalGroupId;
    public final MenuItem item;

    public ChangeMenuItemGroupEvent(String originalGroupId, MenuItem item) {
        this.originalGroupId = originalGroupId;
        this.item = item;
    }
}
