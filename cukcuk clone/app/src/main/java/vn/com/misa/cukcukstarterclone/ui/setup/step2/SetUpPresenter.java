package vn.com.misa.cukcukstarterclone.ui.setup.step2;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;

/**
 * @created_by KhanhNQ on 12-Jan-21.
 */
public class SetUpPresenter implements SetUpContract.Presenter {

    private SetUpContract.View view;

    @Override
    public void attach(SetUpContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
    }

    /**
     * - Mục đích method: Khởi tạo data cho quán cà phê
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public void initDataForCoffeeShop() {
        List<MenuGroup> menuGroups = new ArrayList<>();
        List<MenuItem> menuItems = new ArrayList<>();
        MenuGroup smoothie = new MenuGroup("Sinh tố", "tra_sua");

        menuItems.add(new MenuItem(30000, "file:///android_asset/data/image_item/cafe/SINHTOXOAI.jpg", "Sinh tố xoài", "", smoothie.getId()));
        menuItems.add(new MenuItem(30000, "file:///android_asset/data/image_item/cafe/SINHTOMANGCAU.jpg", "Sinh tố mãng cầu", "", smoothie.getId()));
        menuItems.add(new MenuItem(30000, "file:///android_asset/data/image_item/cafe/SINHTODUAHAU.jpg", "Sinh tố dưa hấu", "", smoothie.getId()));
        menuItems.add(new MenuItem(30000, "file:///android_asset/data/image_item/cafe/SINHTOBO.jpg", "Sinh tố bơ", "", smoothie.getId()));
        menuItems.add(new MenuItem(30000, "file:///android_asset/data/image_item/cafe/SINHTODAU.jpg", "Sinh tố dâu", "", smoothie.getId()));
        menuItems.add(new MenuItem(30000, "file:///android_asset/data/image_item/cafe/SINHTOCHANHLEO.jpg", "Sinh tố chanh leo", "", smoothie.getId()));
        menuGroups.add(smoothie);

        MenuGroup juice = new MenuGroup("Nước ép", "do_uong_coc");
        menuItems.add(new MenuItem(30000, "file:///android_asset/data/image_item/cafe/NUOCCAM.jpg", "Nước cam", "", juice.getId()));
        menuItems.add(new MenuItem(30000, "file:///android_asset/data/image_item/cafe/NUOCEPTAO.jpg", "Nước ép táo", "", juice.getId()));
        menuItems.add(new MenuItem(30000, "file:///android_asset/data/image_item/cafe/NUOCEPCAROT.jpg", "Nước ép cà rốt", "", juice.getId()));
        menuItems.add(new MenuItem(30000, "file:///android_asset/data/image_item/cafe/NUOCEPDUAHAU.jpg", "Nước ép dưa hấu", "", juice.getId()));
        menuItems.add(new MenuItem(30000, "file:///android_asset/data/image_item/cafe/NUOCEPOI.jpg", "Nước ép ổi", "", juice.getId()));
        menuItems.add(new MenuItem(30000, "file:///android_asset/data/image_item/cafe/NUOCCHANHLEO.jpg", "Nước chanh leo", "", juice.getId()));
        menuGroups.add(juice);

        MenuGroup yogurt = new MenuGroup("Sữa chua", "do_uong");
        menuItems.add(new MenuItem(20000, "file:///android_asset/data/image_item/cafe/SUACHUADANHDA.jpg", "Sữa chua đánh đá", "", yogurt.getId()));
        menuItems.add(new MenuItem(20000, "file:///android_asset/data/image_item/cafe/SUACHUACACAO.jpg", "Sữa chua cacao", "", yogurt.getId()));
        menuItems.add(new MenuItem(20000, "file:///android_asset/data/image_item/cafe/SUACHUACAPHE.jpg", "Sữa chua café", "", yogurt.getId()));
        menuItems.add(new MenuItem(30000, "file:///android_asset/data/image_item/cafe/HOAQUADAMSUACHUA.jpg", "Hoa quả dầm sữa chua", "", yogurt.getId()));
        menuGroups.add(yogurt);

        MenuGroup tea = new MenuGroup("Trà - Café", "coffee");
        menuItems.add(new MenuItem(20000, "file:///android_asset/data/image_item/cafe/CAFEDEN.jpg", "Café đen", "", tea.getId()));
        menuItems.add(new MenuItem(20000, "file:///android_asset/data/image_item/cafe/CAFENAU.jpg", "Café nâu", "", tea.getId()));
        menuItems.add(new MenuItem(20000, "file:///android_asset/data/image_item/cafe/CAFEBACXIU.jpg", "Bạc xỉu", "", tea.getId()));
        menuItems.add(new MenuItem(25000, "file:///android_asset/data/image_item/cafe/CAPPUCHINO.jpg", "Capuchino", "", tea.getId()));
        menuItems.add(new MenuItem(25000, "file:///android_asset/data/image_item/cafe/AMERICANO.jpg", "Americano", "", tea.getId()));
        menuItems.add(new MenuItem(25000, "file:///android_asset/data/image_item/cafe/ESPRESSO.jpg", "Espresso", "", tea.getId()));
        menuItems.add(new MenuItem(25000, "file:///android_asset/data/image_item/cafe/LATTE.jpg", "Latte", "", tea.getId()));
        menuItems.add(new MenuItem(25000, "file:///android_asset/data/image_item/cafe/MOCHA.jpg", "Mocha", "", tea.getId()));
        menuGroups.add(tea);

        MenuGroup otherDrink = new MenuGroup("Đồ uống khác", "do_dong_chai");
        menuItems.add(new MenuItem(20000, "file:///android_asset/data/image_item/cafe/CACAO.jpg", "Cacao", "", otherDrink.getId()));
        menuItems.add(new MenuItem(18000, "file:///android_asset/data/image_item/cafe/TRALIPTON.jpg", "Trà Lipton", "", otherDrink.getId()));
        menuItems.add(new MenuItem(18000, "file:///android_asset/data/image_item/cafe/TRADILMAH.jpg", "Trà Dilmah", "", otherDrink.getId()));
        menuItems.add(new MenuItem(20000, "file:///android_asset/data/image_item/cafe/CHANHMUOI.jpg", "Chanh muối", "", otherDrink.getId()));
        menuItems.add(new MenuItem(20000, "file:///android_asset/data/image_item/cafe/BOHUC.jpg", "Bò húc", "", otherDrink.getId()));
        menuItems.add(new MenuItem(15000, "file:///android_asset/data/image_item/cafe/COCA.jpg", "Coca", "", otherDrink.getId()));
        menuItems.add(new MenuItem(15000, "file:///android_asset/data/image_item/cafe/STING.jpg", "Sting", "", otherDrink.getId()));
        menuItems.add(new MenuItem(10000, "file:///android_asset/data/image_item/cafe/LAVIE.jpg", "Lavie", "", otherDrink.getId()));
        menuGroups.add(otherDrink);

        MenuGroup snack = new MenuGroup("Ăn vặt", "banh_ngot");
        menuItems.add(new MenuItem(15000, "file:///android_asset/data/image_item/cafe/HUONGDUONG.jpg", "Hướng dương", "", snack.getId()));
        menuItems.add(new MenuItem(30000, "file:///android_asset/data/image_item/cafe/THITBOKHO.jpg", "Khô bò", "", snack.getId()));
        menuItems.add(new MenuItem(30000, "file:///android_asset/data/image_item/cafe/KHOGA.jpg", "Khô gà", "", snack.getId()));
        menuGroups.add(snack);

        view.updateData(menuGroups, menuItems);
    }

    /**
     * - Mục đích method: Khởi tạo data cho quán trà sữa
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public void initDataForBubbleTeaShop() {

    }

    /**
     * - Mục đích method: Khởi tạo data cho quán bún phở
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public void initDataForNoodleShop() {

    }
}
