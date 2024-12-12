package TCP;

/**
 * Well actually this isn't supposed to exist because TCP is something that is used auto by computers but to simulate we have it.
 */

public class Main {
    public static void main(String[] args) {
        Internet network = new Internet();
        TCPClient user1 = new TCPClient("192.168.115.67");
        TCPClient user2 = new TCPClient("192.168.115.92");
        network.add(user1);
        network.add(user2);
        user1.connect("192.168.115.87");
        user1.connect("192.168.115.92");
        user2.connect("192.168.115.67");
        user1.connect("192.168.115.92");
        user2.disconnect("192.168.115.00");
        user2.disconnect("192.168.115.00");
        user2.disconnect("192.168.115.67");
        user2.connect("192.168.115.67");
        network.remove(user1);
    }
}