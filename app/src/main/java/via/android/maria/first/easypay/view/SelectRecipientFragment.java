package via.android.maria.first.easypay.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import via.android.maria.first.easypay.R;

public class SelectRecipientFragment extends Fragment {

    public SelectRecipientFragment() {
    }

    public static SelectRecipientFragment newInstance(String param1, String param2) {
        SelectRecipientFragment fragment = new SelectRecipientFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_recipient, container, false);
    }
}