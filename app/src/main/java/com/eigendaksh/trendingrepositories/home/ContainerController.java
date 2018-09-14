package com.eigendaksh.trendingrepositories.home;


import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.eigendaksh.trendingrepositories.R;
import com.eigendaksh.trendingrepositories.base.BaseController;
import com.eigendaksh.trendingrepositories.screens.javarepos.JavaRepositoriesController;
import com.eigendaksh.trendingrepositories.screens.javascriptrepos.JavascriptRepositoriesController;
import com.eigendaksh.trendingrepositories.screens.swiftrepos.SwiftRepositoriesController;

import butterknife.BindView;

public class ContainerController extends BaseController {

    private final String[] PAGE_TITLES = {"Java", "Swift", "JavaScript"};

    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.viewPager) ViewPager viewPager;

    private final RouterPagerAdapter pagerAdapter;

    public ContainerController() {
        pagerAdapter = new RouterPagerAdapter(this) {
            @Override
            public void configureRouter(@NonNull Router router, int position) {
                if (!router.hasRootController()) {
                    Controller page;
                    switch (position) {
                        case 0:
                            page = new JavaRepositoriesController();
                            break;
                        case 1:
                            page = new SwiftRepositoriesController();
                            break;
                        case 2:
                            page = new JavascriptRepositoriesController();
                            break;
                        default:
                            page = new JavaRepositoriesController();
                    }
                    router.setRoot(RouterTransaction.with(page));
                }
            }

            @Override
            public int getCount() {
                return PAGE_TITLES.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return PAGE_TITLES[position];
            }
        };

    }

    @Override
    protected int layoutRes() {
        return R.layout.main_container_screen;
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        if (getActivity() != null && !getActivity().isChangingConfigurations()) {
            viewPager.setAdapter(null);
        }
        tabLayout.setupWithViewPager(null);
        super.onDestroyView(view);
    }
}
