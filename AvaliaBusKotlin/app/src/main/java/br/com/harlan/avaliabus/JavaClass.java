package br.com.harlan.avaliabus;

import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import br.com.harlan.avaliabus.model.EvaluationModel;

/**
 * Created by Harlan on 20/12/2017.
 */

public class JavaClass implements Cloneable {
    ViewPager viewPager;

    public static final int TESTE = 1;

    public JavaClass() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ArrayList<EvaluationModel> aux = new ArrayList<>();
        for (int i = aux.size(); i >= 0; i--) {

        }
    }
}
