package TCP;

import java.util.HashMap;
import java.util.Objects;

/**
 * Client of TCP that changes states, receives and sends messages.
 */

public class TCPClient {
    private State state = new StateLISTENING();
    private final String ip;
    private Internet network;

    /**
     * Returns IP of client.
     */

    public String getIP() {
        return this.ip;
    }

    /**
     * Creates client with IP.
     */

    public TCPClient(String ip) {
        this.ip = ip;
    }

    /**
     * Sets network of this client.
     */

    public void setNetwork(Internet network) {
        this.network = network;
    }

    /**
     * Sets state of this client.
     */

    private void setState(State state) {
        this.state = state;
    }

    /**
     * Receives message and does action that is needed according to it.
     */

    private void receive(HashMap<String, String> message) {
        if (Objects.equals(message.get("Message"), "CONNECT")) {
            State newState = new StateESTABLISHED(message.get("Source IP"));
            HashMap<String, String> messageNew = new HashMap<>();
            messageNew.put("Source IP",this.ip);
            messageNew.put("Destination IP",message.get("Source IP"));
            messageNew.put("Message","CONNECTED");
            setState(newState);
            System.out.println(this.ip + " SUCCESSFULLY CONNECTED TO " + message.get("Source IP"));
            send(messageNew);
        } else if (Objects.equals(message.get("Message"), "DISCONNECT")) {
            State newState = new StateLISTENING();
            setState(newState);
            System.out.println(this.ip + " SUCCESSFULLY DISCONNECTED FROM " + message.get("Source IP"));
        } else if (Objects.equals(message.get("Message"), "CONNECTED")) {
            State newState = new StateESTABLISHED(message.get("Source IP"));
            setState(newState);
            System.out.println(this.ip + " SUCCESSFULLY CONNECTED to " + message.get("Source IP"));
        }
    }

    /**
     * Sends message to another client if it exists.
     */

    private void send(HashMap<String, String> message) {
        String secondIP = message.get("Destination IP");
        TCPClient secondClient = network.get(secondIP);
        if (secondClient != null) {
            System.out.println(this.ip + " SENDING MESSAGE TO " + secondIP);
            secondClient.receive(message);
        } else {
            System.out.println(secondIP + " DOESN'T EXIST IN NETWORK");
        }
    }

    /**
     * Tries to connect to another client.
     */

    public void connect(String ip) {
        if (!Objects.equals(null, state.informationAboutSecondClient())) {
            System.out.println(this.ip + " ALREADY CONNECTED TO " + state.informationAboutSecondClient());
        } else {
            System.out.println(this.ip + " CONNECTING TO " + ip);
            HashMap<String, String> message = new HashMap<>();
            message.put("Source IP", this.ip);
            message.put("Destination IP", ip);
            message.put("Message", "CONNECT");
            send(message);
        }
    }

    /**
     * Tries to disconnect from another client.
     */

    public void disconnect(String ip) {
        if (Objects.equals(state.informationAboutSecondClient(), ip)) {
            System.out.println(this.ip + " DISCONNECTING FROM " + ip);
            HashMap<String, String> message = new HashMap<>();
            message.put("Source IP", this.ip);
            message.put("Destination IP", ip);
            message.put("Message","DISCONNECT");
            State StateLISTENING = new StateLISTENING();
            setState(StateLISTENING);
            System.out.println(this.ip + " SUCCESSFULLY DISCONNECTED to " + message.get("Source IP"));
            send(message);
        } else {
            System.out.println(ip + " IS NOT CONNECTED TO " + this.ip);
        }
    }

    /**
     * Closes client aka leaves network.
     */

    public void close() {
        System.out.println(this.ip + " SETTING STATE TO CLOSED");
        String connectedIP = state.informationAboutSecondClient();
        if (state.informationAboutSecondClient() != null) {
            disconnect(connectedIP);
        }
        State StateCLOSED = new StateCLOSED();
        setState(StateCLOSED);
    }
}
