package com.wpq.loadingcontroller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * For page loading state changed.
 *
 * @author wpq
 * @version 1.0
 */
public class LoadingController implements LoadingInterface {

    private Context context;
    private View loadingTargetView;

    // listener
    private LoadingInterface.OnClickListener onErrorRetryClickListener;
    private LoadingInterface.OnClickListener onEmptyTodoClickListener;

    private LayoutInflater inflater;
    private ViewGroup parentView;
    private int currentViewIndex;

    private View loadingView;
    private View networkErrorView;
    private View errorView;
    private View emptyView;

    private boolean isLoading = true;

    public LoadingController(@NonNull Context context, @NonNull View loadingTargetView) {
        LoadingControllerManager.getInstance().checkNull();
        this.context = context;
        this.loadingTargetView = loadingTargetView;
        init();
    }

    private void init() {
        inflater = LayoutInflater.from(context);
        if (loadingTargetView.getParent() != null) {
            parentView = (ViewGroup) loadingTargetView.getParent();
        } else {
            parentView = (ViewGroup) loadingTargetView.getRootView().findViewById(android.R.id.content);
        }
        int count = parentView.getChildCount();
        for (int i = 0; i < count; i++) {
            if (loadingTargetView == parentView.getChildAt(i)) {
                currentViewIndex = i;
                break;
            }
        }
    }

    /**
     * 切换状态
     *
     * @param view 目标View
     */
    private void showView(View view) {
        // 如果当前状态和要切换的状态相同，则不做处理，反之切换
        if (parentView.getChildAt(currentViewIndex) == view) return;
        // 先把view从父布局移除
        ViewGroup viewParent = (ViewGroup) view.getParent();
        if (viewParent != null) {
            viewParent.removeView(view);
        }
        parentView.removeViewAt(currentViewIndex);
        parentView.addView(view, currentViewIndex, loadingTargetView.getLayoutParams());
        isLoading = view == loadingView;
    }

    public LoadingController setOnErrorRetryClickListener(LoadingInterface.OnClickListener listener) {
        this.onErrorRetryClickListener = listener;
        return this;
    }

    public LoadingController setOnEmptyTodoClickListener(LoadingInterface.OnClickListener listener) {
        this.onEmptyTodoClickListener = listener;
        return this;
    }

    @Override
    public void showLoading() {
        showLoading(null);
    }

    /**
     * 显示Loading页面
     *
     * @param customView 自定义页面
     */
    @SuppressLint("InflateParams")
    @Override
    public void showLoading(View customView) {
        if (customView != null) {
            loadingView = customView;
            showView(loadingView);
            return;
        }
        if (loadingView == null && LoadingControllerManager.getInstance().getLoadingLayoutId() != 0) {
            loadingView = inflater.inflate(LoadingControllerManager.getInstance().getLoadingLayoutId(), null);
        }
        if (loadingView == null) {
            return;
        }
        showView(loadingView);
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void showError() {
        showError("");
    }

    @Override
    public void showError(String errorMessage) {
        showError(errorMessage, true);
    }

    @Override
    public void showError(String errorMessage, boolean showRetryButton) {
        showError(errorMessage, showRetryButton, "", null);
    }

    @Override
    public void showError(String errorMessage, String retryButtonText) {
        showError(errorMessage, true, retryButtonText, null);
    }

    @Override
    public void showError(View customView) {
        showError("", true, "", customView);
    }

    /**
     * 显示错误提示页面
     *
     * @param errorMessage    错误信息
     * @param showRetryButton 是否显示重试按钮
     * @param retryButtonText 重试按钮的文字
     * @param customView      自定义页面
     */
    @SuppressLint("InflateParams")
    private void showError(String errorMessage, boolean showRetryButton, String retryButtonText, View customView) {
        // 网络错误
        if (!isNetworkConnected(context)) {
            showNetworkError();
            return;
        }
        // 其他错误
        if (customView != null) {
            errorView = customView;
            showView(errorView);
            return;
        }
        if (errorView == null) {
            errorView = inflater.inflate(LoadingControllerManager.getInstance().getErrorLayoutId(), null);
        }
        if (errorView == null) {
            return;
        }
        // 提示文字
        if (!TextUtils.isEmpty(errorMessage)) {
            if (LoadingControllerManager.getInstance().getErrorTextViewId() != 0) {
                View vErrorMessage = errorView.findViewById(LoadingControllerManager.getInstance().getErrorTextViewId());
                if (vErrorMessage != null && vErrorMessage instanceof TextView) {
                    ((TextView) vErrorMessage).setText(errorMessage);
                }
            }
        }
        // 重试
        // 如果有重试按钮，则点击重试按钮执行刷新
        if (LoadingControllerManager.getInstance().getErrorRetryViewId() != 0) {
            View vRetry = errorView.findViewById(LoadingControllerManager.getInstance().getErrorRetryViewId());
            if (vRetry != null) {
                if (showRetryButton) {
                    vRetry.setVisibility(View.VISIBLE);
                    vRetry.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onErrorRetryClickListener != null) {
                                onErrorRetryClickListener.onClick(v);
                            }
                        }
                    });
                    // 重试按钮文字
                    if (!TextUtils.isEmpty(retryButtonText) && vRetry instanceof TextView) {
                        ((TextView) vRetry).setText(retryButtonText);
                    }
                } else {
                    vRetry.setVisibility(View.GONE);
                }
            }
        } else { // 如果没有重试按钮，则点击整个错误页面执行刷新
            errorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onErrorRetryClickListener != null) {
                        onErrorRetryClickListener.onClick(v);
                    }
                }
            });
        }
        showView(errorView);
    }

    /**
     * 显示网络错误页面
     */
    private void showNetworkError() {
        if (networkErrorView != null) {
            showView(networkErrorView);
            return;
        }
        networkErrorView = inflater.inflate(LoadingControllerManager.getInstance().getNetworkErrorLayoutId(), null);
        if (networkErrorView == null) {
            return;
        }
        // 如果有重试按钮，则点击重试按钮执行刷新
        if (LoadingControllerManager.getInstance().getNetworkErrorRetryViewId() != 0) {
            View vRetry = networkErrorView.findViewById(LoadingControllerManager.getInstance().getNetworkErrorRetryViewId());
            if (vRetry != null) {
                vRetry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onErrorRetryClickListener != null) {
                            onErrorRetryClickListener.onClick(v);
                        }
                    }
                });
            }
        } else { // 如果没有重试按钮，则点击整个错误页面执行刷新
            networkErrorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onErrorRetryClickListener != null) {
                        onErrorRetryClickListener.onClick(v);
                    }
                }
            });
        }
        showView(networkErrorView);
    }

    @Override
    public void showEmpty() {
        showEmpty("");
    }

    @Override
    public void showEmpty(String emptyMessage) {
        showEmpty(emptyMessage, "");
    }

    @Override
    public void showEmpty(String emptyMessage, String todoButtonText) {
        showEmpty(0, emptyMessage, todoButtonText);
    }

    @Override
    public void showEmpty(int emptyImageResource, String emptyMessage) {
        showEmpty(emptyImageResource, emptyMessage, "");
    }

    @Override
    public void showEmpty(int emptyImageResource, String emptyMessage, String todoButtonText) {
        showEmpty(emptyImageResource, null, emptyMessage, todoButtonText, null);
    }

    @Override
    public void showEmpty(Drawable emptyImageDrawable, String emptyMessage) {
        showEmpty(emptyImageDrawable, emptyMessage, "");
    }

    @Override
    public void showEmpty(Drawable emptyImageDrawable, String emptyMessage, String todoButtonText) {
        showEmpty(0, emptyImageDrawable, emptyMessage, todoButtonText, null);
    }

    @Override
    public void showEmpty(View customView) {
        showEmpty(0, null, "", "", customView);
    }

    /**
     * 显示空数据提示页面
     *
     * @param emptyImageResource 空数据提示图片资源id
     * @param emptyImageDrawable 空数据提示图片
     * @param emptyMessage       空数据信息
     * @param todoButtonText     Todo按钮的文字
     * @param customView         自定义页面
     */
    @SuppressWarnings("ConstantConditions")
    @SuppressLint("InflateParams")
    private void showEmpty(int emptyImageResource, Drawable emptyImageDrawable, String emptyMessage, String todoButtonText, View customView) {
        if (customView != null) {
            emptyView = customView;
            showView(emptyView);
            return;
        }
        if (emptyView == null) {
            emptyView = inflater.inflate(LoadingControllerManager.getInstance().getEmptyLayoutId(), null);
        }
        if (emptyView == null) {
            return;
        }
        // 空数据提示图片
        if (emptyImageResource != 0 || emptyImageDrawable != null) {
            if (LoadingControllerManager.getInstance().getEmptyImageViewId() != 0) {
                View vEmptyImage = emptyView.findViewById(LoadingControllerManager.getInstance().getEmptyImageViewId());
                if (vEmptyImage != null && vEmptyImage instanceof ImageView) {
                    ImageView ivEmptyImage = (ImageView) vEmptyImage;
                    if (emptyImageResource != 0) {
                        ivEmptyImage.setImageResource(emptyImageResource);
                    } else if (emptyImageDrawable != null) {
                        ivEmptyImage.setImageDrawable(emptyImageDrawable);
                    }
                }
            }
        }
        // 空数据提示文字
        if (!TextUtils.isEmpty(emptyMessage)) {
            if (LoadingControllerManager.getInstance().getEmptyTextViewId() != 0) {
                View vEmptyMessage = emptyView.findViewById(LoadingControllerManager.getInstance().getEmptyTextViewId());
                if (vEmptyMessage != null && vEmptyMessage instanceof TextView) {
                    ((TextView) vEmptyMessage).setText(emptyMessage);
                }
            }
        }
        // 空数据Todo按钮
        if (LoadingControllerManager.getInstance().getEmptyTodoViewId() != 0) {
            View vTodo = emptyView.findViewById(LoadingControllerManager.getInstance().getEmptyTodoViewId());
            if (vTodo != null) {
                // todoButtonText不为空才显示Todo按钮，即使不是TextView
                if (!TextUtils.isEmpty(todoButtonText)) {
                    vTodo.setVisibility(View.VISIBLE);
                    vTodo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onEmptyTodoClickListener != null) {
                                onEmptyTodoClickListener.onClick(v);
                            }
                        }
                    });
                    // Todo按钮文字
                    if (vTodo instanceof TextView) {
                        ((TextView) vTodo).setText(todoButtonText);
                    }
                } else {
                    vTodo.setVisibility(View.GONE);
                }
            }
        }
        showView(emptyView);
    }

    @Override
    public void dismissLoading() {
        showView(loadingTargetView);
    }

    /**
     * 判断网络是否连接
     */
    private boolean isNetworkConnected(Context context) {
        try {
            @SuppressWarnings("ConstantConditions") @SuppressLint("MissingPermission")
            NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            return info != null && info.isConnected();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return false;
    }

    /**
     * View替换
     *
     * @param src    源View
     * @param target 目标View
     */
    private void replaceView(View src, View target) {
        ViewGroup targetParent = (ViewGroup) target.getParent();
        if (targetParent != null) {
            targetParent.removeView(target);
        }

        ViewGroup srcParent = (ViewGroup) src.getParent();
        if (srcParent != null) {
            int index = -1;
            for (int i = 0; i < srcParent.getChildCount(); i++) {
                if (srcParent.getChildAt(i) == src) {
                    index = i;
                }
            }
            srcParent.removeViewAt(index);
            srcParent.addView(target, index, src.getLayoutParams());
        }
    }
}
