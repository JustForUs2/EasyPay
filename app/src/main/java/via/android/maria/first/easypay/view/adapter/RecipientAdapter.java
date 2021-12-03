package via.android.maria.first.easypay.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import via.android.maria.first.easypay.R;
import via.android.maria.first.easypay.model.Recipient;

public class RecipientAdapter extends RecyclerView.Adapter<RecipientAdapter.ViewHolder> {
    private List<Recipient> recipientList;
    private OnListItemClickListener onListItemClickListener;

    public RecipientAdapter(OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }

    public RecipientAdapter() {
    }

    public void setRecipientListWithListener(List<Recipient> recipientList, OnListItemClickListener onListItemClickListener) {
        this.recipientList = recipientList;
        this.onListItemClickListener = onListItemClickListener;
    }

    public void setRecipientList(List<Recipient> recipientList) {
        this.recipientList = recipientList;
    }

    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recipient_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipientAdapter.ViewHolder viewHolder, int position) {
        Recipient recipient = recipientList.get(position);
        viewHolder.recipientName.setText(recipient.getOwnerName());
    }

    @Override
    public int getItemCount() {
        if (recipientList == null)
            return 0;
        return recipientList.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView recipientName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipientName = itemView.findViewById(R.id.recipientName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
