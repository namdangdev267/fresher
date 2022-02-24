package vn.com.misa.cukcukstarterclone.ui.menu.itemlist;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.data.repository.IMenuItemRepository;

/**
 * @created_by KhanhNQ on 31-Jan-2021.
 */
public class ItemListPresenter implements ItemListContract.Presenter {

    private ItemListContract.View view;

    private final IMenuItemRepository menuItemRepository;

    public ItemListPresenter(IMenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public void attach(ItemListContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void getAllItem(String groupId) {
        menuItemRepository.getItemsByGroupId(groupId, new IOnLoadedCallback<List<MenuItem>>() {
            @Override
            public void onSuccess(List<MenuItem> data) {
                view.showAllItems(data);
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getLocalizedMessage());
            }
        });
    }

    @Override
    public void updateItem(MenuItem menuItem) {
        menuItemRepository.updateMenuItem(menuItem, new IOnLoadedCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean isSuccess) {
                if (isSuccess) view.updateItem(menuItem);
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getMessage());
            }
        });
    }

    @Override
    public void addNewItem(MenuItem menuItem) {
        menuItemRepository.addNewMenuItem(menuItem, new IOnLoadedCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean isSuccess) {
                if (isSuccess) view.showAddedItem(menuItem);
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getMessage());
            }
        });
    }

    @Override
    public void deleteItem(MenuItem menuItem) {
        menuItemRepository.deleteMenuItem(menuItem, new IOnLoadedCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean isSuccess) {
                if (isSuccess) view.removeItem(menuItem);
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getMessage());
            }
        });
    }
}
