package co.edu.icesi.joancaliz.paycool_prototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Home extends AppCompatActivity {

    private ListView challenguesListView;
    private ChallengueAdapter challengueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        challenguesListView = findViewById(R.id.home_challengues_list_view);
        challengueAdapter = new ChallengueAdapter(this);
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
    }
}

