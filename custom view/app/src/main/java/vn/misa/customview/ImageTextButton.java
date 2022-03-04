package vn.misa.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageTextButton extends LinearLayout {
    public ImageTextButton(Context context) {
        this(context, null);
    }

    public ImageTextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ImageTextButton, 0, 0);
        String titleText = a.getString(R.styleable.ImageTextButton_title);
        int resourceId = a.getResourceId(R.styleable.ImageTextButton_image, 0);
        a.recycle();

        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER);
        setClickable(true);
        setBackgroundResource(R.drawable.bg_btn_image_text);
        setPadding(0, 8, 0, 8);

        LayoutInflater.from(context).inflate(R.layout.btn_image_text, this, true);

        TextView title = findViewById(R.id.title);
        title.setText(titleText);
        ImageView image = findViewById(R.id.image);
        image.setImageResource(resourceId);
    }
}
