package com.example.progressbarlib;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

public class CustomProgressbar extends AppCompatImageView{
        private Paint paint;
        private float sweepAngle = 0.0f;
        private float startAngle = 0.0f;
        private final int increment = 45;
        private ValueAnimator animator;

    public enum Size {
            MIN, MED, MAX
        }
        public CustomProgressbar(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public CustomProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        private void init() {
            paint = new Paint();
            paint.setColor(ContextCompat.getColor(this.getContext(), android.R.color.black));
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(15);
            setupAnimator();
        }

        private void setupAnimator() {
            animator = ValueAnimator.ofFloat(0, 135);
            animator.setDuration(800);
            animator.setInterpolator(new LinearInterpolator());
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.addUpdateListener(valueAnimator -> {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                sweepAngle = animatedValue * 2.4f;
                int alpha = (int) (255 - (animatedValue / 135) * 255);
                paint.setAlpha(alpha);
                invalidate();
            });
            animator.addListener(new ValueAnimator.AnimatorListener() {
                @Override
                public void onAnimationStart(@NonNull Animator animation) {

                }

                @Override
                public void onAnimationEnd(@NonNull Animator animation) {

                }

                @Override
                public void onAnimationCancel(@NonNull Animator animation) {

                }

                @Override
                public void onAnimationRepeat(@NonNull Animator animation) {
                    startAngle += increment;
                    if (startAngle >= 360) {
                        startAngle -= 360;
                    }
                }
            });
        }

        public void setStrokeWidth(int thick) {
            paint.setStrokeWidth(thick);
            invalidate();
        }
        public void setColor(int color) {
            paint.setColor(color);
            invalidate();
        }

        public void setSize(Size size) {
            int dimension;
            switch (size) {
                case MIN:
                    dimension = 100;
                    break;
                case MAX:
                    dimension = 300;
                    break;
                case MED:
                default:
                    dimension = 200;
            }
            ViewGroup.LayoutParams params = getLayoutParams();
            params.width = dimension;
            params.height = dimension;
            setLayoutParams(params);
            invalidate();
        }

        public void start() {
            if (animator != null && !animator.isRunning()) {
                animator.start();
                this.setVisibility(View.VISIBLE);
            }
        }

        public void pause() {
            if (animator != null && animator.isRunning()) {
                animator.end();
                this.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            float width = getWidth();
            float height = getHeight();
            float radius = Math.min(width, height) / 2 - paint.getStrokeWidth() / 2;
            float centerX = width / 2;
            float centerY = height / 2;
            canvas.drawArc(centerX - radius, centerY - radius, centerX + radius, centerY + radius, startAngle, sweepAngle, false, paint);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int desiredWidth = 200;
            int desiredHeight = 200;

            int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            int heightSize = MeasureSpec.getSize(heightMeasureSpec);

            int width;
            int height;

            if (widthMode == MeasureSpec.EXACTLY) {
                width = widthSize;
            } else if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(desiredWidth, widthSize);
            } else {
                width = desiredWidth;
            }

            if (heightMode == MeasureSpec.EXACTLY) {
                height = heightSize;
            } else if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(desiredHeight, heightSize);
            } else {
                height = desiredHeight;
            }

            setMeasuredDimension(width, height);
        }
    }
