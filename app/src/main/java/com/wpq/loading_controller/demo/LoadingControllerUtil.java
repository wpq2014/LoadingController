package com.wpq.loading_controller.demo;

import com.wpq.loading_controller.LoadingControllerManager;

/**
 * @author wupuquan
 * @version 1.0
 * @since 2018/4/3 13:31
 */
public class LoadingControllerUtil {

    private LoadingControllerUtil() {

    }

    public static void init() {
        LoadingControllerManager.getInstance()
                .setLoadingLayoutId(R.layout.loading_controller_loading) // required
                .setNetworkErrorLayoutId(R.layout.loading_controller_network_error) // required
                .setErrorLayoutId(R.layout.loading_controller_error) // required
                .setEmptyLayoutId(R.layout.loading_controller_empty) // required
                .setNetworkErrorRetryViewId(R.id.loading_controller_tv_network_error_retry)
                .setErrorTextViewId(R.id.loading_controller_tv_error_message)
                .setErrorRetryViewId(R.id.loading_controller_tv_error_retry)
                .setEmptyImageViewId(R.id.loading_controller_iv_empty)
                .setEmptyTextViewId(R.id.loading_controller_tv_empty_message)
                .setEmptyTodoViewId(R.id.loading_controller_tv_empty_retry);
    }
}
