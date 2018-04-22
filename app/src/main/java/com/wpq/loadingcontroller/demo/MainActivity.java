package com.wpq.loadingcontroller.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wpq.loadingcontroller.LoadingController;
import com.wpq.loadingcontroller.LoadingInterface;

public class MainActivity extends AppCompatActivity {

    private TextView mTvTest;

    private LoadingController mLoadingController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvTest = findViewById(R.id.test);

        mLoadingController = new LoadingController(this, mTvTest)
                .setOnErrorRetryClickListener(new LoadingInterface.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showEmpty();
                    }
                })
                .setOnEmptyTodoClickListener(new LoadingInterface.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mLoadingController.dismissLoading();
                    }
                });
        showLoading();
    }

    private void showLoading() {
        mLoadingController.showLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadingController.showError();
//                mLoadingController.showError("我的是我的，你的还是我的~");
//                mLoadingController.showError("看尔乃插标卖首！", false);
//                mLoadingController.showError("尔等敢应战否？", "全军突击");
//
//                TextView custom = new TextView(LoadingControllerActivity.this);
//                custom.setTextColor(Color.RED);
//                custom.setText("吾乃常山赵子龙也！");
//                custom.setGravity(Gravity.CENTER);
//                custom.setPadding(50, 50, 50, 50);
//                mLoadingController.showError(custom);
            }
        }, 1500);
    }

    private void showEmpty() {
        mLoadingController.showLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                mLoadingController.showEmpty();
//                mLoadingController.showEmpty("观今夜天象，知天下大事~");
                mLoadingController.showEmpty("暂无数据", "去逛逛");
//                mLoadingController.showEmpty(R.mipmap.ic_launcher, "将星陨落，天命难违。。");
//                mLoadingController.showEmpty(R.mipmap.ic_launcher, "吾通晓兵法，世人皆知~", "雕虫小技");
//                mLoadingController.showEmpty(getResources().getDrawable(R.mipmap.ic_launcher_round), "千秋万载，一统江山！");
//                mLoadingController.showEmpty(getResources().getDrawable(R.mipmap.ic_launcher_round), "仙福永享，寿与天齐！", "主公，快走！");
//
//                ImageView imageView = new ImageView(LoadingControllerActivity.this);
//                imageView.setImageResource(R.mipmap.ic_launcher_round);
//                mLoadingController.showEmpty(imageView);
            }
        }, 1500);
    }
}
