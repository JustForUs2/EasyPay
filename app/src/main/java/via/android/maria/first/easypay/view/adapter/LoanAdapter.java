package via.android.maria.first.easypay.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.model.Loan;

public class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.ViewHolder> {
    private List<Loan> loanList;

    public LoanAdapter(List<Loan> loanList) {
        this.loanList = loanList;
    }

    // convert single list item from XML to View object and store them in VIewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.loan_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Loan loan = loanList.get(position);
        viewHolder.loanType.setText(loan.getType());
        viewHolder.description.setText(loan.getDescription());
        viewHolder.additionalInfo.setText(loan.getAdditionalInfo());
    }

    @Override
    public int getItemCount() {
        if (loanList == null)
            return 0;
        return loanList.size();
    }

    // create a ViewHolder class to hold the views sent to the RecyclerView
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView loanType, description, additionalInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            loanType = itemView.findViewById(R.id.main_heading_loan);
            description = itemView.findViewById(R.id.description_loan);
            additionalInfo = itemView.findViewById(R.id.additional_info_loan);
        }
    }
}
