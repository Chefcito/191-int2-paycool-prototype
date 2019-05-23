package co.edu.icesi.joancaliz.paycool_prototype.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import co.edu.icesi.joancaliz.paycool_prototype.R;
public class TransferFragment extends Fragment implements IFragmentInteraction {

    private IFragmentInteraction listener;

    private FrameLayout fragmentContainer;

    public TransferFragment() {
        // Required empty public constructor
    }

    public static TransferFragment newInstance() {
        TransferFragment fragment = new TransferFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IFragmentInteraction) {
            listener = (IFragmentInteraction) context;
        }

        else {
            throw new RuntimeException(context.toString()
                    + " must implement IFragmentInteraction");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.from(container.getContext() ).inflate(R.layout.fragment_transfer, container, false);
        fragmentContainer = view.findViewById(R.id.fragment_transfer_fragment_container_frame_layout);
        TransferDestinationFragment transferDestinationFragment = TransferDestinationFragment.newInstance();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(fragmentContainer.getId(), transferDestinationFragment).commit();
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (listener != null) {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onFragmentInteraction(String request) {
    }

    @Override
    public void replaceFragment(int containerId, Fragment fragment, boolean stackable) {
        if(listener != null) {
            listener.replaceFragment(containerId, fragment, stackable);
        }
    }
}
