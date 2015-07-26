package fr.valquev.tec.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.valquev.tec.R;
import fr.valquev.tec.objects.ISelectNetwork;
import fr.valquev.tec.objects.Network;
import fr.valquev.tec.objects.NetworkList;

public class SelectNetworkAdapter extends RecyclerView.Adapter<SelectNetworkAdapter.ViewHolder> {

    private NetworkList networkList;
    private ISelectNetwork callback;

    public SelectNetworkAdapter(Context context, NetworkList networkList) {
        this.networkList = networkList;
        callback = (ISelectNetwork) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_network_content, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Network network = networkList.get(position);

        holder.city.setText(network.getVille());
        holder.name.setText(network.getNetwork());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClick(network);
            }
        });
    }

    @Override
    public int getItemCount() {
        return networkList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView city;
        protected TextView name;

        public ViewHolder(View itemView) {
            super(itemView);

            city = (TextView) itemView.findViewById(R.id.search_network_city);
            name = (TextView) itemView.findViewById(R.id.search_network_name);
        }

    }
}
