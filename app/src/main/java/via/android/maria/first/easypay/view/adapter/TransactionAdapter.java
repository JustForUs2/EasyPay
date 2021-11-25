package via.android.maria.first.easypay.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.model.Transaction;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private List<Transaction> transactionList;

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public TransactionAdapter(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public TransactionAdapter() {
        transactionList = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.setAmount("100");
        transaction.setAccountNumber("565683");
        transaction.setTransferName("Alex");
        transactionList.add(transaction);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transaction_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder viewHolder, int position) {
        Transaction transaction = transactionList.get(position);
        viewHolder.amount.setText(transaction.getAmount());
        viewHolder.receiverProviderName.setText(transaction.getTransferName());
    }

    @Override
    public int getItemCount() {
        if (transactionList == null)
        return 0;
        return transactionList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView receiverProviderName;
        TextView amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverProviderName = itemView.findViewById(R.id.receiveSender);
            amount = itemView.findViewById(R.id.amount);
        }
    }
}