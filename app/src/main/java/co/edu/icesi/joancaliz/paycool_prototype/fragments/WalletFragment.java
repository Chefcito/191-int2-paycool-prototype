package co.edu.icesi.joancaliz.paycool_prototype.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import co.edu.icesi.joancaliz.paycool_prototype.R;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class WalletFragment extends Fragment {

    private static final int TRANSFER_REQUEST = 1;

    private Button withdrawButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        withdrawButton = view.findViewById(R.id.fragment_wallet_withdraw_button);
        withdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToTransferActivity();
            }
        });

        return view;
    }

    public void goToTransferActivity() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TRANSFER_REQUEST) {
            if(resultCode == RESULT_OK) {

            }

            else if(resultCode == RESULT_CANCELED) {

            }
        }
    }
}
