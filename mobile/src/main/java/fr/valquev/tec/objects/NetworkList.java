package fr.valquev.tec.objects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.valquev.tec.Utils;

public class NetworkList implements Iterable {

    private List<Network> networks;

    public NetworkList() {
        networks = new ArrayList<>();
    }

    public NetworkList search(String query) {
        NetworkList result = new NetworkList();

        for(Network network : networks) {
            String toSearch = Utils.removeAccent(network.getVille() + network.getNetwork());
            if(toSearch.toLowerCase().trim().contains(Utils.removeAccent(query).toLowerCase().trim())) {
                result.add(network);
            }
        }

        return result;
    }

    private void add(Network network) {
        networks.add(network);
    }

    public Network get(int position) {
        return networks.get(position);
    }

    @Override
    public Iterator iterator() {
        return networks.iterator();
    }

    public List<Network> getNetworkList() {
        return networks;
    }

    public int size() {
        return networks.size();
    }
}
