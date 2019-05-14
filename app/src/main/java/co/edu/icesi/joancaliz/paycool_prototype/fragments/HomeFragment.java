package co.edu.icesi.joancaliz.paycool_prototype.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import co.edu.icesi.joancaliz.paycool_prototype.Challengue;
import co.edu.icesi.joancaliz.paycool_prototype.R;
import co.edu.icesi.joancaliz.paycool_prototype.activities.Home;
import co.edu.icesi.joancaliz.paycool_prototype.adapters.ChallengueAdapter;

public class HomeFragment extends Fragment {

    private View view;

    private ListView challenguesListView;
    private ChallengueAdapter challengueAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        challenguesListView = view.findViewById(R.id.fragment_home_challengues_list_view);
        challengueAdapter = new ChallengueAdapter(getActivity() );
        challenguesListView.setAdapter(challengueAdapter);

        challenguesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                challengueAdapter.goToAccomplishChallengueActivity(i);
            }
        });

        challengueAdapter.addChallengue(new Challengue() );
        challengueAdapter.addChallengue(new Challengue() );
        challengueAdapter.addChallengue(new Challengue() );

        return view;
    }
}
