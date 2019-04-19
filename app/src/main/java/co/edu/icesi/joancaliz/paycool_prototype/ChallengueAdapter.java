package co.edu.icesi.joancaliz.paycool_prototype;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ChallengueAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Challengue> challengues;

    private TextView titleTextView, descriptionTextView, pointsTextView;

    public ChallengueAdapter(Activity activity) {
        this.activity = activity;
        challengues = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return challengues.size();
    }

    @Override
    public Object getItem(int i) {
        return challengues.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater li = activity.getLayoutInflater();
        View v = li.inflate(R.layout.challengue, viewGroup, false);

        titleTextView = v.findViewById(R.id.challengue_title_text_view);
        titleTextView.setText("TOSTAO'");

        descriptionTextView = v.findViewById(R.id.challengue_description_text_view);
        descriptionTextView.setText("Paga cualquier producto en Tostao' con Paycool");

        pointsTextView = v.findViewById(R.id.challengue_points_text_view);
        pointsTextView.setText("20 puntos");

        notifyDataSetChanged();

        return v;
    }

    public void addChallengue(Challengue challengue) {
        challengues.add(challengue);
        notifyDataSetChanged();
    }

    public void goToAccomplishChallengueActivity(int index) {
        Intent intent = new Intent(activity, AccomplishChallengue.class);
        activity.startActivity(intent);
    }
}
