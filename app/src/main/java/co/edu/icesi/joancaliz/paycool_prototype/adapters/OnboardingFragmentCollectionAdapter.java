package co.edu.icesi.joancaliz.paycool_prototype.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.edu.icesi.joancaliz.paycool_prototype.R;
import co.edu.icesi.joancaliz.paycool_prototype.activities.Onboarding;
import co.edu.icesi.joancaliz.paycool_prototype.fragments.OnboardingFragment;

public class OnboardingFragmentCollectionAdapter extends FragmentStatePagerAdapter {
    private Context context;

    private Resources res;
    private String[] titles;
    private String[] descriptions;

    public OnboardingFragmentCollectionAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        res = context.getResources();
        titles = res.getStringArray(R.array.onboarding_titles);
        descriptions = res.getStringArray(R.array.onboarding_descriptions);
    }

    @Override
    public Fragment getItem(int i) {
        // Machetazo por corregir incoming...
        int drawable = 0;
        if(i == 0) {
            drawable = R.drawable.img_onboarding_1;
        }
        else if(i == 1) {
            drawable = R.drawable.img_onboarding_2;
        }
        else if(i == 2) {
            drawable = R.drawable.img_onboarding_3;
        }
        else if(i == 3) {
            drawable = R.drawable.img_onboarding_4;
        }
        String title = titles[i];
        String content = descriptions[i];
        OnboardingFragment onboardingFragment = OnboardingFragment.newInstance(drawable, title, content);
        return onboardingFragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
