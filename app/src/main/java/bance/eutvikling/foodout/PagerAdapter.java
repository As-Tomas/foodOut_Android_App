package bance.eutvikling.foodout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    private String json;

    PagerAdapter(FragmentManager fm, int NumOfTabs, String json){
        super(fm,NumOfTabs);
        this.mNumOfTabs = NumOfTabs;
        this.json=json;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                //TabFragmentas1 tab1 = new TabFragmentas1();
                TabDeliveryFragment tab1=TabDeliveryFragment.newInstance();
                tab1.setJson(this.json);
                return tab1;

            case 1:
                PickUpFragment tab2 = new PickUpFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

