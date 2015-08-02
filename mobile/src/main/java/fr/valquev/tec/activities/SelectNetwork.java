package fr.valquev.tec.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import fr.valquev.tec.Client;
import fr.valquev.tec.R;
import fr.valquev.tec.Utils;
import fr.valquev.tec.adapters.SelectNetworkAdapter;
import fr.valquev.tec.objects.ISelectNetwork;
import fr.valquev.tec.objects.Network;
import fr.valquev.tec.objects.NetworkList;

public class SelectNetwork extends AppCompatActivity implements ISelectNetwork {

    private Context mContext;
    private SearchView searchView;
    private NetworkList networkList;
    private NetworkList nwList;
    private RecyclerView list;
    private SelectNetworkAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_network);

        mContext = this;

        setSupportActionBar((Toolbar) findViewById(R.id.search_network_toolbar));

        searchView = (SearchView) findViewById(R.id.search_network_searchview);
        list = (RecyclerView) findViewById(R.id.search_network_list);

        list.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(llm);

        Client.get(Network.NETWORKS_URL, null, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                networkList = new Gson().fromJson(response.toString(), NetworkList.class);
                nwList = networkList;
                adapter = new SelectNetworkAdapter(mContext, nwList);
                list.setAdapter(adapter);

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        refresh(query);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        refresh(newText);
                        return false;
                    }
                });
            }

            @Override
            public void onFinish() {
            }

        });
    }

    private void refresh(String query) {
        if (!query.equals("")) {
            nwList = networkList.search(query);
        } else {
            nwList = networkList;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(Network network) {
        network.regist();
    }
}
