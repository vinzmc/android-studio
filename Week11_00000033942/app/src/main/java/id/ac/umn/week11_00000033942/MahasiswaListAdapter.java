package id.ac.umn.week11_00000033942;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class MahasiswaListAdapter extends RecyclerView.Adapter<MahasiswaListAdapter.MahasiswaViewHolder> {
    private final LayoutInflater mInflater;
    private List<Mahasiswa> daftarMahasiswa;

    public MahasiswaListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.mhs_item_layout,parent,false);
        return new MahasiswaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaListAdapter.MahasiswaViewHolder holder, int position) {
        if(daftarMahasiswa != null){
            Mahasiswa mhs = daftarMahasiswa.get(position);
            holder.tvNim.setText(mhs.getNim());
            holder.tvNama.setText(mhs.getNama());
        }else{
            holder.tvNim.setText("Belum ada Mahasiswa");
        }
    }

    @Override
    public int getItemCount() {
        if(daftarMahasiswa != null){
            return daftarMahasiswa.size();
        }else{
            return 0;
        }
    }

    public Mahasiswa getMahasiswaAtPosition(int posisi){
        return daftarMahasiswa.get(posisi);
    }

    void setDaftarMahasiswa(List<Mahasiswa> mhs){
        daftarMahasiswa = mhs;
        notifyDataSetChanged();
    }

    void hapusData(int posisi){
        daftarMahasiswa.remove(posisi);
        notifyDataSetChanged();
    }

    void tambahData(Mahasiswa mhs){
        daftarMahasiswa.add(mhs);
        notifyDataSetChanged();
    }

    void updateData(int posisi, Mahasiswa mhs){
        daftarMahasiswa.set(posisi, mhs);
        notifyDataSetChanged();
        notifyItemChanged(posisi);
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvNim;
        private final TextView tvNama;
        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNim = itemView.findViewById(R.id.tvItemNim);
            tvNama = itemView.findViewById(R.id.tvItemNim);
        }
    }
}
