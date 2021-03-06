package bupt.com.vr_bupt.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.socialize.UMShareAPI;

import bupt.com.vr_bupt.R;
import bupt.com.vr_bupt.ui.channel.ChannelFragment;
import bupt.com.vr_bupt.ui.main.HomeFragment;
import bupt.com.vr_bupt.ui.me.MeFragment;
import bupt.com.vr_bupt.ui.news.NewsFragment;
import bupt.com.vr_bupt.ui.publish.PublishActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnClickListener {
    private TextView[] textViews = new TextView[4];
    private ImageView[] imageButtons = new ImageView[4];
    private RelativeLayout publishLayout;
    private HomeFragment homeFragment = null;
    private ChannelFragment channelFragment = null;
    private NewsFragment newsFragment = null;
    private MeFragment meFragment = null;
    private FragmentManager fragmentManager;
    private int mIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initViews();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        showFragment(transaction, R.id.re_zhy);
    }

    private void initViews() {
        textViews[0] = (TextView) findViewById(R.id.text_zhuy);
        textViews[1] = (TextView) findViewById(R.id.text_yyt);
        textViews[2] = (TextView) findViewById(R.id.text_se);
        textViews[3] = (TextView) findViewById(R.id.text_me);
        imageButtons[0] = (ImageView) findViewById(R.id.rb_zhuy);
        imageButtons[1] = (ImageView) findViewById(R.id.rb_yyt);
        imageButtons[2] = (ImageView) findViewById(R.id.rb_se);
        imageButtons[3] = (ImageView) findViewById(R.id.rb_me);
        publishLayout = (RelativeLayout) findViewById(R.id.re_public);
    }

    public void jianTing(View view) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mIndex = view.getId();
        showFragment(transaction, mIndex);
    }

    private void showFragment(FragmentTransaction transaction, int tag) {
        switch (tag) {
            case R.id.re_zhy://主页
                hideFragments(transaction);
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.main_con, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                setTextColor(0);
                imageButtons[0].setBackgroundResource(R.mipmap.main_home_icon_selected);
                imageButtons[1].setBackgroundResource(R.mipmap.main_business_icon_normal);
                imageButtons[2].setBackgroundResource(R.mipmap.main_service_icon_normal);
                imageButtons[3].setBackgroundResource(R.mipmap.main_me_icon_normal);
                break;

            case R.id.re_yyt://频道
                hideFragments(transaction);
                if (channelFragment == null) {
                    channelFragment = new ChannelFragment();
                    transaction.add(R.id.main_con, channelFragment);
                } else {
                    transaction.show(channelFragment);
                }
                setTextColor(1);
                imageButtons[0].setBackgroundResource(R.mipmap.main_home_icon_normal);
                imageButtons[1].setBackgroundResource(R.mipmap.main_business_icon_selected);
                imageButtons[2].setBackgroundResource(R.mipmap.main_service_icon_normal);
                imageButtons[3].setBackgroundResource(R.mipmap.main_me_icon_normal);
                break;
            case R.id.re_public://发布视频
                Intent intent = new Intent(this, PublishActivity.class);
                startActivity(intent);
                break;
            case R.id.re_se://消息
                hideFragments(transaction);
                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                    transaction.add(R.id.main_con, newsFragment);
                } else {
                    transaction.show(newsFragment);
                }
                setTextColor(2);
                imageButtons[0].setBackgroundResource(R.mipmap.main_home_icon_normal);
                imageButtons[1].setBackgroundResource(R.mipmap.main_business_icon_normal);
                imageButtons[2].setBackgroundResource(R.mipmap.main_service_icon_selected);
                imageButtons[3].setBackgroundResource(R.mipmap.main_me_icon_normal);
                break;

            case R.id.re_me://我的
                hideFragments(transaction);
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    transaction.add(R.id.main_con, meFragment);
                } else {
                    transaction.show(meFragment);
                }
                setTextColor(3);
                imageButtons[0].setBackgroundResource(R.mipmap.main_home_icon_normal);
                imageButtons[1].setBackgroundResource(R.mipmap.main_business_icon_normal);
                imageButtons[2].setBackgroundResource(R.mipmap.main_service_icon_normal);
                imageButtons[3].setBackgroundResource(R.mipmap.main_me_icon_selected);
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();
//        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (channelFragment != null) {
            transaction.hide(channelFragment);
        }

        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
        if (meFragment != null) {
            transaction.hide(meFragment);
        }

    }

    private void setTextColor(int index) {
        for (int i = 0; i < textViews.length; i++) {
            if (i == index) {
                textViews[i]
                        .setTextColor(getResources().getColor(R.color.blue));
            } else {
                textViews[i].setTextColor(getResources()
                        .getColor(R.color.black));
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
