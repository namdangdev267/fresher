package vn.com.misa.cukcukstarterclone.ui.setup.step2.menuitemdetails;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.textfield.TextInputEditText;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.ui.setup.step2.event.AddNewMenuItemEvent;
import vn.com.misa.cukcukstarterclone.ui.setup.step2.event.ChangeMenuItemGroupEvent;
import vn.com.misa.cukcukstarterclone.ui.setup.step2.event.ModifyMenuItemEvent;
import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuItemDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuItemDetailsFragment extends Fragment {

    public static final String TAG = "MenuItemDetailsFragment";

    public static final String KEY_MODIFY_ITEM = "key_modify_item";
    public static final String ARG_MODIFIED_ITEM = "modified_item";

    public static final String KEY_ADD_ITEM = "key_add_item";
    public static final String ARG_ADD_ITEM = "added_item";

    private static final String ARG_ITEM = "item";
    private static final String ARG_GROUPS = "groups";

    private static final int FLAG_CREATE_NEW_ITEM = 0;
    private static final int FLAG_UPDATE_ITEM = 1;
    private int flag = -1;

    private static final int IMAGE_PICKER_REQUEST_CODE = 101;

    private MenuItem mItem;
    private List<MenuGroup> mGroups = new ArrayList<>();
    private String originalGroupId;
    private String selectedGroupId;

    List<String> units = new ArrayList<>();

    public MenuItemDetailsFragment() {
    }

    private ImageView ivMenuItemDetails;
    private TextInputEditText edtItemName;
    private TextInputEditText edtItemPrice;
    private AutoCompleteTextView tvItemUnit;
    private AutoCompleteTextView tvMenuItemGroup;
    private Button btnSave;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MenuItemDetailsFragment.
     */
    public static MenuItemDetailsFragment newInstance(MenuItem item, ArrayList<MenuGroup> menuGroups) {
        MenuItemDetailsFragment fragment = new MenuItemDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_ITEM, item);
        args.putParcelableArrayList(ARG_GROUPS, menuGroups);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mItem = getArguments().getParcelable(ARG_ITEM);
            mGroups = getArguments().getParcelableArrayList(ARG_GROUPS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_item_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindViews(view);
        initData();
    }

    /**
     * @created_by KhanhNQ on 21-Jan-21
     */
    protected void bindViews(View view) {
        try {
            ivMenuItemDetails = view.findViewById(R.id.ivMenuItemDetails);
            edtItemName = view.findViewById(R.id.edtItemName);
            edtItemPrice = view.findViewById(R.id.edtItemPrice);
            tvItemUnit = view.findViewById(R.id.tvItemUnit);
            tvMenuItemGroup = view.findViewById(R.id.tvMenuItemGroup);
            btnSave = view.findViewById(R.id.btnSave);

            ArrayAdapter<MenuGroup> groupAdapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, mGroups);
            tvMenuItemGroup.setAdapter(groupAdapter);

            units = Arrays.asList(getResources().getStringArray(R.array.unit_spinner));
            ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, units);
            tvItemUnit.setAdapter(unitAdapter);

            tvMenuItemGroup.setOnItemClickListener((parent, v, position, id) -> {
                selectedGroupId = ((MenuGroup) parent.getItemAtPosition(position)).getId();
            });

            edtItemPrice.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() == 0) {
                        edtItemPrice.setText("0");
                        edtItemPrice.requestFocus();
                        edtItemPrice.selectAll();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            btnSave.setOnClickListener(v -> saveItem());

            ivMenuItemDetails.setOnClickListener(v -> {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_PICKER_REQUEST_CODE);
            });

            Toolbar toolbar = view.findViewById(R.id.toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(v -> popFragment());
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    private void popFragment() {
        try {
            requireActivity().getSupportFragmentManager().popBackStack(TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * @created_by KhanhNQ on 21-Jan-21
     */
    protected void initData() {
        try {
            if (mItem != null) {
                flag = FLAG_UPDATE_ITEM;
                editItem();
            } else {
                mItem = new MenuItem();
                flag = FLAG_CREATE_NEW_ITEM;
                addNewItem();
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    private void editItem() {
        Glide.with(requireContext())
                .load(mItem.getImageUrl())
                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(8)))
                .placeholder(R.drawable.placeholder)
                .into(ivMenuItemDetails);

        edtItemPrice.setText(new DecimalFormat("###,###,###").format(mItem.getPrice()));
        edtItemName.setText(mItem.getName());
        tvItemUnit.setText(mItem.getUnit(), false);

        for (MenuGroup group : mGroups) {
            if (group.getId().equals(mItem.getGroupId())) {
                originalGroupId = group.getId();
                selectedGroupId = group.getId();

                tvMenuItemGroup.setText(group.getTitle(), false);
                break;
            }
        }
    }

    private void addNewItem() {
        Glide.with(requireContext())
                .load(R.drawable.placeholder)
                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(8)))
                .into(ivMenuItemDetails);

        edtItemPrice.setText(R.string.init_price_value);
        tvItemUnit.setText(units.get(0), false);

        MenuGroup initGroup = mGroups.get(0);
        selectedGroupId = initGroup.getId();
        tvMenuItemGroup.setText(initGroup.getTitle(), false);
    }

    /**
     * - Mục đích method: Lấy ra ảnh được chọn từ gallery
     *
     * @created_by KhanhNQ on 22-Jan-21
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == IMAGE_PICKER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                String path = getPathFromCameraData(data, requireActivity());
                if (path != null) {
                    Uri imageUri = Uri.fromFile(new File(path));
                    Glide.with(requireContext())
                            .load(imageUri)
                            .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(8)))
                            .placeholder(R.drawable.placeholder)
                            .into(ivMenuItemDetails);
                    mItem.setImageUrl(path);
                }
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Lấy ra đường dẫn của ảnh đầu vào
     *
     * @return đường dẫn tới ảnh
     * @created_by KhanhNQ on 22-Jan-21
     */
    public static String getPathFromCameraData(Intent data, Context context) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }

    /**
     * - Mục đích method: Lưu dữ liệu của MenuItem sau khi chỉnh sửa
     *
     * @created_by KhanhNQ on 22-Jan-21
     */
    private void saveItem() {
        try {
            Bundle bundle = new Bundle();

            if (Objects.requireNonNull(edtItemName.getText()).toString().isEmpty()) {
                edtItemName.setError(requireContext().getString(R.string.menu_item_name_should_not_be_empty));
            } else if (Objects.requireNonNull(edtItemPrice.getText()).toString().isEmpty()) {
                edtItemPrice.setError(requireContext().getString(R.string.menu_item_price_should_not_be_empty));
            } else {
                mItem.setName(edtItemName.getText().toString());
                mItem.setPrice(Float.parseFloat(edtItemPrice.getText().toString().replace(".", "")));
                mItem.setUnit(tvItemUnit.getText().toString());
                mItem.setGroupId(selectedGroupId);

                switch (flag) {
                    case FLAG_CREATE_NEW_ITEM:
                        bundle.putParcelable(ARG_ADD_ITEM, mItem);
                        getParentFragmentManager().setFragmentResult(KEY_ADD_ITEM, bundle);

                        EventBus.getDefault().post(new AddNewMenuItemEvent(mItem));

                        break;
                    case FLAG_UPDATE_ITEM:
                        bundle.putParcelable(ARG_MODIFIED_ITEM, mItem);
                        getParentFragmentManager().setFragmentResult(KEY_MODIFY_ITEM, bundle);

                        if (originalGroupId.equals(selectedGroupId)) {
                            EventBus.getDefault().post(new ModifyMenuItemEvent(mItem));
                        } else {
                            EventBus.getDefault().post(new ChangeMenuItemGroupEvent(originalGroupId, mItem));
                        }

                        break;
                }

                getParentFragmentManager().popBackStack(TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }
}
