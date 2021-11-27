package via.android.maria.first.easypay.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.model.Recipient;

// FRAGMENT B
public class SelectRecipientFragment extends Fragment {
    private Button addRecipientCTA;
    private TextInputEditText recipientName, recipientAccountNum, recipientSortCode;

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
        View view = inflater.inflate(R.layout.fragment_select_recipient, container, false);
        findViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addRecipientCTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle result = new Bundle();

                Recipient recipient = new Recipient();
                recipient.setSortCode(recipientSortCode.getText().toString());
                recipient.setAccountNumber(recipientAccountNum.getText().toString());
                recipient.setOwnerName(recipientName.getText().toString());

                result.putSerializable("bundleKey", recipient);
                getParentFragmentManager().setFragmentResult("requestKey", result);
            }
        });
    }

    private void findViews(View view) {
        addRecipientCTA = view.findViewById(R.id.add_recipient_for_transfer);
        recipientName = view.findViewById(R.id.name);
        recipientAccountNum = view.findViewById(R.id.account_num);
        recipientSortCode = view.findViewById(R.id.sort_code);
    }
}