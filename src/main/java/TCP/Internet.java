package TCP;

import java.util.HashMap;

/**
 * Network class.
 */

public class Internet {

    private HashMap<String, TCPClient> systemOfUsers = new HashMap<>();

    /**
     * Add user to network.
     */

    public void add(TCPClient client) {
        TCPClient user = systemOfUsers.get(client.getIP());
        if (user == null) {
            client.setNetwork(this);
            systemOfUsers.put(client.getIP(),client);
            System.out.println(client.getIP() + " ADDED TO NETWORK");
        }
    }

    /**
     * Gets user from IP.
     */

    public TCPClient get(String ip) {
        return systemOfUsers.get(ip);
    }

    /**
     * Removes user from network.
     */

    public void remove(TCPClient client) {
        String ip = client.getIP();
        TCPClient user = systemOfUsers.get(ip);
        if (user != null) {
            user.close();
            systemOfUsers.remove(ip);
            System.out.println(ip + " REMOVED FROM NETWORK");
        }
    }

}
