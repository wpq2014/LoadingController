package com.wpq.loading_controller;

/**
 * @author wupuquan
 * @version 1.0
 * @since 2018/4/2 10:19
 */
public class LoadingControllerManager {

    private int loadingLayoutId;
    private int networkErrorLayoutId;
    private int errorLayoutId;
    private int emptyLayoutId;

    private int networkErrorRetryViewId;
    private int errorTextViewId;
    private int errorRetryViewId;
    private int emptyImageViewId;
    private int emptyTextViewId;
    private int emptyTodoViewId;

    private LoadingControllerManager() {

    }

    public static LoadingControllerManager getInstance() {
        return LoadingControllerManagerHolder.instance;
    }

    private static class LoadingControllerManagerHolder {
        private static final LoadingControllerManager instance = new LoadingControllerManager();
    }

    /**
     * 设置Loading(加载中)布局id
     *
     * @param loadingLayoutId layoutId for Loading
     */
    public LoadingControllerManager setLoadingLayoutId(int loadingLayoutId) {
        this.loadingLayoutId = loadingLayoutId;
        return this;
    }

    /**
     * 设置网络错误布局id
     *
     * @param networkErrorLayoutId layoutId for NetworkError
     */
    public LoadingControllerManager setNetworkErrorLayoutId(int networkErrorLayoutId) {
        this.networkErrorLayoutId = networkErrorLayoutId;
        return this;
    }

    /**
     * 设置Error(失败)布局id
     *
     * @param errorLayoutId layoutId for Error
     */
    public LoadingControllerManager setErrorLayoutId(int errorLayoutId) {
        this.errorLayoutId = errorLayoutId;
        return this;
    }

    /**
     * 设置Empty(空数据)布局id
     *
     * @param emptyLayoutId layoutId for Empty
     */
    public LoadingControllerManager setEmptyLayoutId(int emptyLayoutId) {
        this.emptyLayoutId = emptyLayoutId;
        return this;
    }

    /**
     * 设置网络错误重试控件id
     *
     * @param networkErrorRetryViewId retryViewId for NetworkError
     */
    public LoadingControllerManager setNetworkErrorRetryViewId(int networkErrorRetryViewId) {
        this.networkErrorRetryViewId = networkErrorRetryViewId;
        return this;
    }

    /**
     * 设置Error(失败)文字提示控件id
     *
     * @param errorTextViewId textViewId for Error
     */
    public LoadingControllerManager setErrorTextViewId(int errorTextViewId) {
        this.errorTextViewId = errorTextViewId;
        return this;
    }

    /**
     * 设置Error(失败)重试控件id
     *
     * @param errorRetryViewId retryViewId for Error
     */
    public LoadingControllerManager setErrorRetryViewId(int errorRetryViewId) {
        this.errorRetryViewId = errorRetryViewId;
        return this;
    }

    /**
     * 设置Empty(空数据)图片提示控件id
     *
     * @param emptyImageViewId imageViewId for Empty
     */
    public LoadingControllerManager setEmptyImageViewId(int emptyImageViewId) {
        this.emptyImageViewId = emptyImageViewId;
        return this;
    }

    /**
     * 设置Empty(空数据)文字提示控件id
     *
     * @param emptyTextViewId textViewId for Empty
     */
    public LoadingControllerManager setEmptyTextViewId(int emptyTextViewId) {
        this.emptyTextViewId = emptyTextViewId;
        return this;
    }

    /**
     * 设置Empty(空数据)Todo控件id
     *
     * @param emptyTodoViewId todoViewId for Error
     */
    @SuppressWarnings("UnusedReturnValue")
    public LoadingControllerManager setEmptyTodoViewId(int emptyTodoViewId) {
        this.emptyTodoViewId = emptyTodoViewId;
        return this;
    }

    int getLoadingLayoutId() {
        return this.loadingLayoutId;
    }

    int getNetworkErrorLayoutId() {
        return this.networkErrorLayoutId;
    }

    int getErrorLayoutId() {
        return this.errorLayoutId;
    }

    int getEmptyLayoutId() {
        return this.emptyLayoutId;
    }

    int getNetworkErrorRetryViewId() {
        return this.networkErrorRetryViewId;
    }

    int getErrorTextViewId() {
        return this.errorTextViewId;
    }

    int getErrorRetryViewId() {
        return this.errorRetryViewId;
    }

    int getEmptyImageViewId() {
        return this.emptyImageViewId;
    }

    int getEmptyTextViewId() {
        return this.emptyTextViewId;
    }

    int getEmptyTodoViewId() {
        return this.emptyTodoViewId;
    }

    void checkNull() {
        if (LoadingControllerManager.this.getLoadingLayoutId() == 0) {
            throw new IllegalArgumentException("Please do LoadingControllerManager.this.setLoadingLayoutId() first!");
        }
        if (LoadingControllerManager.this.getNetworkErrorLayoutId() == 0) {
            throw new IllegalArgumentException("Please do LoadingControllerManager.this.setNetworkErrorLayoutId() first!");
        }
        if (LoadingControllerManager.this.getErrorLayoutId() == 0) {
            throw new IllegalArgumentException("Please do LoadingControllerManager.this.setErrorLayoutId() first!");
        }
        if (LoadingControllerManager.this.getEmptyLayoutId() == 0) {
            throw new IllegalArgumentException("Please do LoadingControllerManager.this.setEmptyLayoutId() first!");
        }
    }
}
