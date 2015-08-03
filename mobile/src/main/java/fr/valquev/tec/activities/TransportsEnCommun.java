package fr.valquev.tec.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import fr.valquev.tec.Client;
import fr.valquev.tec.R;
import fr.valquev.tec.adapters.SelectNetworkAdapter;
import fr.valquev.tec.objects.ISelectNetwork;
import fr.valquev.tec.objects.Network;
import fr.valquev.tec.objects.NetworkList;

public class TransportsEnCommun extends AppCompatActivity implements ISelectNetwork {

    private Context mContext;
    private Network network;
    private SearchView searchView;
    private NetworkList networkList;
    private NetworkList nwList;
    private RecyclerView list;
    private SelectNetworkAdapter adapter;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    //TODO Tabs pour les différentes fonctionnalités

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transports_en_commun);

        mContext = this;

        network = new Network(mContext);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.app_name_long);
        setSupportActionBar(toolbar);

        searchView = (SearchView) findViewById(R.id.search_network_searchview);
        list = (RecyclerView) findViewById(R.id.search_network_list);

        list.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(llm);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //TODO Récupérer la liste des network ?
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //TODO Rafraichir le network
            }

        };

        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        Client.get(Network.NETWORKS_URL, null, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                //TODO Début chargement
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
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                //TODO Gestion erreurs
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //TODO Gestion erreurs
            }

            @Override
            public void onFinish() {
                //TODO Fin chargement
            }

        });


    }

    private void refresh(String query) {
        if (!query.equals("")) {
            nwList = networkList.search(query);
        } else {
            nwList = networkList;
        }
        adapter.setNetworkList(nwList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onNetworkSelected(Network network) {
        Toast.makeText(mContext, network.getNetwork(), Toast.LENGTH_SHORT).show();
        network.regist();
    }
}