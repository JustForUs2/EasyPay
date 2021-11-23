package via.android.maria.first.easypay.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.model.Transaction;


public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private List<Transaction> transactions;
    private final OnListItemClicked onClickItem;

    TransactionAdapter(List<Transaction> transactions, OnListItemClicked onClickItem) {
        this.transactions = transactions;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.receiver_account.setText(transactions.get(position).getReceiverAccount());
        holder.amount_sent.setText((int) transactions.get(position).getAmount());
        holder.date.setText((CharSequence) transactions.get(position).getTransactionDate());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView receiver_account;
        TextView amount_sent;
        TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            receiver_account = itemView.findViewById(R.id.receiver_account);
            amount_sent = itemView.findViewById(R.id.amount_sent);
            date = itemView.findViewById(R.id.date);
        }

        @Override
        public void onClick(View view) {
            onClickItem.onListItemClicked(getLayoutPosition());
        }
    }

    public interface OnListItemClicked {
        void onListItemClicked(int clickedItem);
    }
}
