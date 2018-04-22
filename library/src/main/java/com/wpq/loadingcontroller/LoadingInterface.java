package com.wpq.loadingcontroller;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * @author wpq
 * @version 1.0
 */
public interface LoadingInterface {

    /**
     * 显示Loading提示页面
     */
    void showLoading();

    /**
     * 显示Loading提示页面
     *
     * @param customView 自定义页面
     */
    void showLoading(View customView);


    /**
     * 显示错误提示页面
     */
    void showError();

    /**
     * 显示错误提示页面
     *
     * @param errorMessage 错误信息
     */
    void showError(String errorMessage);

    /**
     * 显示错误提示页面
     *
     * @param errorMessage    错误信息
     * @param showRetryButton 是否显示重试按钮，默认显示
     */
    void showError(String errorMessage, boolean showRetryButton);

    /**
     * 显示错误提示页面
     *
     * @param errorMessage    错误信息
     * @param retryButtonText 重试按钮的文字
     */
    void showError(String errorMessage, String retryButtonText);

    /**
     * 显示错误提示页面
     *
     * @param customView 自定义页面
     */
    void showError(View customView);


    /**
     * 显示空数据提示页面
     */
    void showEmpty();

    /**
     * 显示空数据提示页面
     *
     * @param emptyMessage 空数据信息
     */
    void showEmpty(String emptyMessage);

    /**
     * 显示空数据提示页面
     *
     * @param emptyMessage   空数据信息
     * @param todoButtonText Todo按钮的文字
     */
    void showEmpty(String emptyMessage, String todoButtonText);

    /**
     * 显示空数据提示页面
     * @param emptyImageResource 空数据提示图片资源id
     * @param emptyMessage 空数据信息
     */
    void showEmpty(int emptyImageResource, String emptyMessage);

    /**
     * 显示空数据提示页面
     * @param emptyImageResource 空数据提示图片资源id
     * @param emptyMessage 空数据信息
     * @param todoButtonText Todo按钮的文字
     */
    void showEmpty(int emptyImageResource, String emptyMessage, String todoButtonText);

    /**
     * 显示空数据提示页面
     * @param emptyImageDrawable 空数据提示图片
     * @param emptyMessage 空数据信息
     */
    void showEmpty(Drawable emptyImageDrawable, String emptyMessage);

    /**
     * 显示空数据提示页面
     * @param emptyImageDrawable 空数据提示图片
     * @param emptyMessage 空数据信息
     * @param todoButtonText Todo按钮的文字
     */
    void showEmpty(Drawable emptyImageDrawable, String emptyMessage, String todoButtonText);

    /**
     * 显示空数据提示页面
     *
     * @param customView 自定义页面
     */
    void showEmpty(View customView);


    /**
     * 停止Loading
     */
    void dismissLoading();

    /**
     * 是否正在Loading
     */
    boolean isLoading();

    interface OnClickListener {
        void onClick(View view);
    }

}
