package id.ac.umn.week06c_00000033942;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;

import org.jetbrains.annotations.NotNull;

public class TombolFling extends AppCompatButton {
    public TombolFling(@NonNull @NotNull Context context) {
        super(context);
    }

    public TombolFling(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TombolFling(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                FlingAnimation fling = new FlingAnimation(
                        this, DynamicAnimation.ROTATION_X);
                fling.setStartVelocity(150)
                        .setFriction(0.11f)
                        .start();
                break;
            default:
        }
        return super.onTouchEvent(event);
    }

}
