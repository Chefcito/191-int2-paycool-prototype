package co.edu.icesi.joancaliz.paycool_prototype.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.icesi.joancaliz.paycool_prototype.Challengue;
import co.edu.icesi.joancaliz.paycool_prototype.EstablishmentOffer;
import co.edu.icesi.joancaliz.paycool_prototype.R;
import co.edu.icesi.joancaliz.paycool_prototype.activities.AccomplishChallengue;

//Este adaptador se encargará de generar el listado de beneficios que ofrece Paycool.

public class EstablishmentAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<EstablishmentOffer> establishments;
    private TextView titleTextView, descriptionTextView, companyTextView;

    public EstablishmentAdapter(Activity activity) {
        this.activity = activity;
        establishments = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return establishments.size();
    }

    @Override
    public Object getItem(int i) {
        return establishments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater li = activity.getLayoutInflater();

        View v = li.inflate(R.layout.establishment_offer, viewGroup, false);

        titleTextView = v.findViewById(R.id.establishment_offer_title_text_view);
        titleTextView.setText(establishments.get(i).getOffer() );

        descriptionTextView = v.findViewById(R.id.establishment_offer_description_text_view);
        descriptionTextView.setText(establishments.get(i).getDescription() );

        companyTextView = v.findViewById(R.id.establishment_offer_company_text_view);
        companyTextView.setText(establishments.get(i).getCompany() );

        notifyDataSetChanged();

        return v;
    }

    public void addEstablishment(EstablishmentOffer establisment) {
        establishments.add(establisment);
        notifyDataSetChanged();
    }
}
