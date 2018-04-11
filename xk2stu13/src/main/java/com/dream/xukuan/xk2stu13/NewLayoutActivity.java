package com.dream.xukuan.xk2stu13;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

public class NewLayoutActivity extends AppCompatActivity {

    NavigationView navigationView;
    Toolbar toolbar;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_layout);
        //初始化toolBar
        initToolBar();
        //初始化ViewPager
        initViewPager();
        //初始化tabLayout
        initTabLayout();
        //初始化DrawerLayout
        initDrawerlayout();
        //初始化NavigationView
        initNavigationView();
        //初始化第一页的颜色
        getRGB(BitmapFactory.decodeResource(getResources(),NewLayoutFragment.getImgId(0)));
    }

    private void initNavigationView() {
        navigationView = (NavigationView) findViewById(R.id.new_naviga);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //关闭侧滑
                drawerLayout.closeDrawers();
                //弹出提示
                final Snackbar snackbar = Snackbar.make(navigationView,item.getTitle(),Snackbar.LENGTH_LONG);
                snackbar.setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
                return true;
            }
        });
    }

    private void initDrawerlayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.new_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
    }

    private void initTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.new_table);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.new_viewpager);
        viewPager.setAdapter(adapter);
        //设置监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //用来提取颜色的Bitmap
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),NewLayoutFragment.getImgId(position));
                getRGB(bitmap);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getRGB(Bitmap bitmap) {
       Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                //得到鲜亮颜色
               Palette.Swatch vibrant =  palette.getVibrantSwatch();
                //界面颜色UI统一性处理,看起来更Material一些
                tabLayout.setBackgroundColor(vibrant.getRgb());
                //
                tabLayout.setTabTextColors(vibrant.getTitleTextColor(),colorBurn(vibrant.getTitleTextColor()));
                toolbar.setBackgroundColor(vibrant.getRgb());
                if(Build.VERSION.SDK_INT>21){
                    Window window = getWindow();
                    window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
                    window.setStatusBarColor(colorBurn(vibrant.getRgb()));
                }
            }
        });

    }

    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.argb(alpha,red, green, blue);
    }


    FragmentPagerAdapter adapter =new FragmentPagerAdapter(getSupportFragmentManager()) {
        private final String[] TITLES = {"分类", "主页", "热门推荐", "热门收藏", "本月热榜", "今日热榜", "专栏"};

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            return NewLayoutFragment.getInstance(position);
        }
    };
    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.new_toolbar);
        toolbar.setTitle("测试");
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
