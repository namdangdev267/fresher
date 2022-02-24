package vn.com.misa.cukcukstarterclone.di;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;

import vn.com.misa.cukcukstarterclone.data.repository.CartItemRepository;
import vn.com.misa.cukcukstarterclone.data.repository.CartRepository;
import vn.com.misa.cukcukstarterclone.data.repository.IUserRepository;
import vn.com.misa.cukcukstarterclone.data.repository.MenuGroupRepository;
import vn.com.misa.cukcukstarterclone.data.repository.MenuItemRepository;
import vn.com.misa.cukcukstarterclone.data.repository.OrderRepository;
import vn.com.misa.cukcukstarterclone.data.repository.ReportRepository;
import vn.com.misa.cukcukstarterclone.data.repository.UserRepository;
import vn.com.misa.cukcukstarterclone.data.source.ICartDataSource;
import vn.com.misa.cukcukstarterclone.data.source.ICartItemDataSource;
import vn.com.misa.cukcukstarterclone.data.source.IMenuItemDataSource;
import vn.com.misa.cukcukstarterclone.data.source.IOrderDataSource;
import vn.com.misa.cukcukstarterclone.data.source.IReportDataSource;
import vn.com.misa.cukcukstarterclone.data.source.IUserDataSource;
import vn.com.misa.cukcukstarterclone.data.source.local.CartItemLocalDataSource;
import vn.com.misa.cukcukstarterclone.data.source.local.CartLocalDataSource;
import vn.com.misa.cukcukstarterclone.data.source.local.MenuGroupLocalDataSource;
import vn.com.misa.cukcukstarterclone.data.source.local.MenuItemLocalDataSource;
import vn.com.misa.cukcukstarterclone.data.source.local.OrderLocalDataSource;
import vn.com.misa.cukcukstarterclone.data.source.local.ReportLocalDataSource;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.CartDao;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.CartItemDao;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.ICartDao;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.ICartItemDao;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.IMenuItemDao;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.IOrderDao;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.IReportDao;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.MenuGroupDao;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.MenuItemDao;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.OrderDao;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.ReportDao;
import vn.com.misa.cukcukstarterclone.data.source.local.database.AppDatabase;
import vn.com.misa.cukcukstarterclone.data.source.remote.MenuGroupRemoteDataSource;
import vn.com.misa.cukcukstarterclone.data.source.remote.MenuItemRemoteDataSource;
import vn.com.misa.cukcukstarterclone.data.source.remote.UserRemoteDataSource;

/**
 * - Mục đích Class: Singleton class để khởi tạo các phương thức lấy Repository
 * - Sử dụng khi: cần sử dụng các phương thức của Repository
 *
 * @created_by KhanhNQ on 09-Jan-21.
 */
public final class Injector {
    private Injector() {
    }

    /**
     * - Mục đích method: Lấy ra instance của IMenuGroupRepository
     * - Sử dụng khi: cần sử dụng các phương thức của IMenuGroupRepository
     *
     * @param context Context để khởi tạo database
     * @return {@link MenuGroupRepository}
     * @created_by KhanhNQ on 26-Jan-21
     */
    public static MenuGroupRepository getMenuGroupRepository(Context context) {
        return MenuGroupRepository.getInstance(
                MenuGroupLocalDataSource.getInstance(
                        MenuGroupDao.getInstance(
                                AppDatabase.getInstance(
                                        context
                                )
                        )
                ), MenuGroupRemoteDataSource.getInstance()
        );
    }

    /**
     * - Mục đích method: Lấy ra instance của MenuItemRepository
     * - Sử dụng khi: cần sử dụng các phương thức của MenuItemRepository
     *
     * @param context Context để khởi tạo database
     * @return {@link MenuItemRepository}
     * @created_by KhanhNQ on 12-Jan-21
     */
    public static MenuItemRepository getMenuItemRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        IMenuItemDao menuItemDao = MenuItemDao.getInstance(database);
        IMenuItemDataSource.Local menuItemLocalDataSource = MenuItemLocalDataSource.getInstance(menuItemDao);
        IMenuItemDataSource.Remote menuItemRemoteDataSource = MenuItemRemoteDataSource.getInstance();
        return MenuItemRepository.getInstance(menuItemLocalDataSource, menuItemRemoteDataSource);
    }

    /**
     * - Mục đích method: Lấy ra instance của CartRepository
     * - Sử dụng khi: cần sử dụng các phương thức của CartRepository
     *
     * @param context Context để khởi tạo database
     * @return {@link CartRepository}
     * @created_by KhanhNQ on 26-Jan-21
     */
    public static CartRepository getCartRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        ICartDao cartDao = CartDao.getInstance(database);
        ICartDataSource.Local cartLocalDataSource = CartLocalDataSource.getInstance(cartDao);
        return CartRepository.getInstance(cartLocalDataSource);
    }

    /**
     * - Mục đích method: Lấy ra instance của CartItemRepository
     * - Sử dụng khi: cần sử dụng các phương thức của CartItemRepository
     *
     * @param context Context để khởi tạo database
     * @return {@link CartItemRepository}
     * @created_by KhanhNQ on 26-Jan-21
     */
    public static CartItemRepository getCartItemRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        ICartItemDao cartItemDao = CartItemDao.getInstance(database);
        ICartItemDataSource.Local cartItemLocalDataSource = CartItemLocalDataSource.getInstance(cartItemDao);
        return CartItemRepository.getInstance(cartItemLocalDataSource);
    }

    public static OrderRepository getOrderRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        IOrderDao orderDao = OrderDao.getInstance(database);
        IOrderDataSource.Local orderLocalDataSource = OrderLocalDataSource.getInstance(orderDao);
        return OrderRepository.getInstance(orderLocalDataSource);
    }

    /**
     * - Mục đích method: Lấy ra instance của ReportRepository
     * - Sử dụng khi: cần sử dụng các phương thức của ReportRepository
     *
     * @param context Context để khởi tạo Database
     * @return {@link ReportRepository}
     * @created_by KhanhNQ on 01-Feb-21
     */
    public static ReportRepository getReportRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        IReportDao reportDao = ReportDao.getInstance(database);
        IReportDataSource.Local reportLocalDataSource = ReportLocalDataSource.getInstance(reportDao);
        return ReportRepository.getInstance(reportLocalDataSource);
    }

    public static UserRepository getUserRepository() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        IUserDataSource.Remote userRemoteDataSource = UserRemoteDataSource.getInstance(firebaseAuth);
        return UserRepository.getInstance(userRemoteDataSource);
    }
}
