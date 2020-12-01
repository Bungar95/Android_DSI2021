package hr.cnzd.dsi2021.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import hr.cnzd.dsi2021.Presenters.IViewPagerActivity;
import hr.cnzd.dsi2021.Presenters.ViewPagerActivityPresenter;
import hr.cnzd.dsi2021.R;
import hr.cnzd.dsi2021.Utilities.Adapters.ViewPagerPicassoAdapter;
import me.relex.circleindicator.CircleIndicator3;

public class ViewPagerActivity extends AppCompatActivity implements IViewPagerActivity.View {

    List<Integer> sliderItems = new ArrayList<>();
    private ViewPager2 viewPager2;
    private IViewPagerActivity.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mPresenter = new ViewPagerActivityPresenter(this);
        mPresenter.created();
    }

    @Override
    public void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_chevron_left_24));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initViewPager();
        initIndicator();
    }

    @Override
    public void initViewPager() {
        sliderItems = getIntent().getIntegerArrayListExtra("list");
        viewPager2 = findViewById(R.id.view_pager2); // binding w/ butterknife breaks the pager
        viewPager2.setAdapter(new ViewPagerPicassoAdapter(sliderItems, this));
    }

    @Override
    public void initIndicator() {
        //https://github.com/ongakuer/CircleIndicator
        CircleIndicator3 indicator = findViewById(R.id.indicator);
        indicator.setViewPager(viewPager2);
    }
}