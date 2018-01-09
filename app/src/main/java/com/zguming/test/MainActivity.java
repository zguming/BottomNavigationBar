package com.zguming.test;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{
    private ArrayList<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationBar bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_main_tab_logo_pressed, "一卡通").setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.ic_main_tab_logo_normal)).setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.ic_main_tab_ticket_pressed, "卡券").setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.ic_main_tab_ticket_normal)).setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.ic_main_tab_person_pressed, "个人").setInactiveIcon(ContextCompat.getDrawable(this,R.drawable.ic_main_tab_person_normal)).setActiveColorResource(R.color.blue))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
    }
    /**
     * 设置默认的
     */

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, YikatongFragment.newInstance("一卡通"));
        transaction.commit();
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(YikatongFragment.newInstance("一卡通"));
        fragments.add(KaquanFragment.newInstance("卡券"));
        fragments.add(UserFragment.newInstance("个人"));
        return fragments;
    }

    @Override
    public void onTabSelected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                //当前的fragment
                Fragment from = fm.findFragmentById(R.id.layFrame);
                //点击即将跳转的fragment
                Fragment fragment = fragments.get(position);
                if (fragment.isAdded()) {
                    // 隐藏当前的fragment，显示下一个
                    ft.hide(from).show(fragment);
                } else {
                // 隐藏当前的fragment，add下一个到Activity中
                    ft.hide(from).add(R.id.layFrame, fragment);
                    if (fragment.isHidden()) {
                        ft.show(fragment);
                    }
                }
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabUnselected(int position) {
        //这儿也要操作隐藏，否则Fragment会重叠
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                // 隐藏当前的fragment
                ft.hide(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }
}