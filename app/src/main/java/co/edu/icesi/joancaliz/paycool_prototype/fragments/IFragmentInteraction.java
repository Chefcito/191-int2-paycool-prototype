package co.edu.icesi.joancaliz.paycool_prototype.fragments;

import android.support.v4.app.Fragment;

public interface IFragmentInteraction {
    void onFragmentInteraction(String request);
    void replaceFragment(int containerId, Fragment fragment);
}
