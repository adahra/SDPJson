package es.hol.adahra.sdpjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * Created by blackshadow on 20/07/16.
 */
public class CustomAdapter extends BaseAdapter {
    private String[] sDaftarNama;
    private String[] sDaftarUsername;
    private String[] sDaftarPassword;
    private String[] iDaftarGambar;
    private Context cContext;
    private static LayoutInflater layoutInflater = null;

    public CustomAdapter(CustomListActivity customListActivity, String[] sDaftarNama, String[] sDaftarUsername, String[] sDaftarPassword, String[] iDaftarGambar) {
        this.sDaftarNama = sDaftarNama;
        this.sDaftarUsername = sDaftarUsername;
        this.sDaftarPassword = sDaftarPassword;
        this.iDaftarGambar = iDaftarGambar;
        cContext = customListActivity;
        layoutInflater = (LayoutInflater) cContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public CustomAdapter(CustomListActivity customListActivity, String[] sDaftarNama, String[] iDaftarGambar) {
        this.sDaftarNama = sDaftarNama;
        this.iDaftarGambar = iDaftarGambar;
        cContext = customListActivity;
        layoutInflater = (LayoutInflater) cContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return sDaftarNama.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        Holder holder = new Holder();
        View rowView;
        rowView = layoutInflater.inflate(R.layout.custom_list, null);
        holder.tvDaftarNama = (TextView) rowView.findViewById(R.id.tvListNama);
        holder.tvDaftarUsername = (TextView) rowView.findViewById(R.id.tvListUsername);
        holder.tvDaftarPassword = (TextView) rowView.findViewById(R.id.tvListPassword);
        holder.ivDaftarGambar = (ImageView) rowView.findViewById(R.id.ivListGambar);
        try {
            Glide.with(cContext).load(iDaftarGambar[i]).into(holder.ivDaftarGambar);
        } catch (Exception e) {

        }
        holder.tvDaftarNama.setText(sDaftarNama[i]);
        // holder.ivDaftarGambar.setImageResource(iDaftarGambar[i]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(cContext, "You Clicked "+ sDaftarNama[i], Toast.LENGTH_LONG).show();
            }
        });

        return rowView;
    }

    public class Holder {
        public TextView tvDaftarNama;
        public TextView tvDaftarUsername;
        public TextView tvDaftarPassword;
        public ImageView ivDaftarGambar;
    }
}
