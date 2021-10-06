package id.ac.umn.mauricemarvin_00000033942_if570l_c_uts.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.umn.mauricemarvin_00000033942_if570l_c_uts.R;
import id.ac.umn.mauricemarvin_00000033942_if570l_c_uts.SfxViewerActivity;

public class SfxAdapter extends RecyclerView.Adapter<SfxAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mItemView = mInflater.inflate(R.layout.sfx_viwer_adapter, parent, false);
        return new ViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull SfxAdapter.ViewHolder holder, int position) {
        Sfx sfx = mSfx.get(position);

        TextView title = holder.title;
        title.setText(sfx.getName());

        TextView subTitle = holder.subTitle;
        subTitle.setText(sfx.getCategory());

        Button deleteBtn = holder.deleteBtn;
        deleteBtn.setOnClickListener(v -> removeItem(holder.getAdapterPosition()));

        LinearLayout rvRow = holder.rvRow;
        rvRow.setOnClickListener(v -> {
            Sfx element = mSfx.get(holder.getAdapterPosition());
            Context context = v.getContext();
            Intent intent = new Intent(context, SfxViewerActivity.class);
            intent.putExtra("ID", element.getId());
            intent.putExtra("NAME", element.getName());
            intent.putExtra("CATEGORY", element.getCategory());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mSfx.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public final TextView title;
        public final TextView subTitle;
        public final Button deleteBtn;
        public final LinearLayout rvRow;
        final SfxAdapter mAdapter;

        public ViewHolder(@NonNull View itemView, SfxAdapter adapter) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            title = itemView.findViewById(R.id.itemTitle);
            subTitle = itemView.findViewById(R.id.itemSubTitle);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            rvRow = itemView.findViewById(R.id.recycleViewRow);

            this.mAdapter = adapter;
//            itemView.setOnClickListener(this);
        }
    }

    public void removeItem(int position) {
        mSfx.remove(position);
        notifyItemRemoved(position);
    }

    private final List<Sfx> mSfx;
    private final LayoutInflater mInflater;

    public SfxAdapter(Context context, List<Sfx> sfxs) {
        mInflater = LayoutInflater.from(context);
        mSfx = sfxs;
    }
}
