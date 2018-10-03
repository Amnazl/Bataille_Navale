
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

// the server that can be run as a console
public class Server {
    // a unique ID for each connection
    private static int uniqueId;
    // an ArrayList to keep the list of the Client
    private ArrayList<ClientThread> al;
    // to display time
    private SimpleDateFormat sdf;
    // the port number to listen for connection
    private int port;
    // to check if server is running
    private boolean keepGoing;
    // notification
    private String notif = " *** ";


    //constructor that receive the port to listen to for connection as parameter

    public Server(int port) {
        // the port
        this.port = port;
        // to display hh:mm:ss
        sdf = new SimpleDateFormat("HH:mm:ss");
        // an ArrayList to keep the list of the Client
        al = new ArrayList<ClientThread>();
    }

    public void start() {
        keepGoing = true;
        //create socket server and wait for connection requests
        try
        {
            // the socket used by the server
            ServerSocket serverSocket = new ServerSocket(port);

            // infinite loop to wait for connections ( till server is active )
            while(keepGoing)
            {
                display("Le serveur attend des clients sur le port  " + port + ".");

                // accept connection if requested from client
                Socket socket = serverSocket.accept();
                // break if server stoped
                if(!keepGoing)
                    break;
                // if client is connected, create its thread

                ClientThread t = new ClientThread(socket);
                //add this client to arraylist
                al.add(t);

                t.start();
            }
            // try to stop the server
            try {
                serverSocket.close();
                for(int i = 0; i < al.size(); ++i) {
                    ClientThread tc = al.get(i);
                    try {
                        // close all data streams and socket
                        tc.sInput.close();
                        tc.sOutput.close();
                        tc.socket.close();
                    }
                    catch(IOException ioE) {
                    }
                }
            }
            catch(Exception e) {
                display("Exception sur la fermeture du client et du serveur: " + e);
            }
        }
        catch (IOException e) {
            String msg = sdf.format(new Date()) + " Exception sur le nouveau SocketServer: " + e + "\n";
            display(msg);
        }
    }

    // to stop the server
    protected void stop() {
        keepGoing = false;
        try {
            new Socket("localhost", port);
        }
        catch(Exception e) {
        }
    }

    // Display an event to the console
    private void display(String msg) {
        String time = sdf.format(new Date()) + " " + msg;
        System.out.println(time);
    }

    // to broadcast a message to all Clients
    private synchronized boolean broadcast(String message) {

        // add timestamp to the message
        String time = sdf.format(new Date());
            String messageLf = message;
            // display message
            System.out.print(messageLf);

            // we loop in reverse order in case we would have to remove a Client
            // because it has disconnected
            for(int i = al.size(); --i >= 0;) {
                ClientThread ct = al.get(i);
                // try to write to the Client if it fails remove it from the list
                if(!ct.writeMsg(messageLf)) {
                    al.remove(i);
                    display("Disconnected Client " + ct.username + " removed from list.");
                }
            }
        return true;


    }

    // if client sent LOGOUT message to exit
    synchronized void remove(int id) {

        String disconnectedClient = "";
        // scan the array list until we found the Id
        for(int i = 0; i < al.size(); ++i) {
            ClientThread ct = al.get(i);
            // if found remove it
            if(ct.id == id) {
                disconnectedClient = ct.getUsername();
                al.remove(i);
                break;
            }
        }
        broadcast(notif + disconnectedClient + " à quitté le serveur." + notif);
    }

    /*
     *  To run as a console application
     * > java Server
     * > java Server portNumber
     * If the port number is not specified 1500 is used
     */
    public static void main(String[] args) {
        int portNumber = 18000;
        // create a server object and start it
        Server server = new Server(portNumber);
        server.start();
    }

    // One instance of this thread will run for each client
    class ClientThread extends Thread {
        // the socket to get messages from client
        Socket socket;
        BufferedReader sInput;
        //ObjectInputStream sInput;
        PrintWriter sOutput;
        // my unique id (easier for deconnection)
        int id;
        // the Username of the Client
        String username;
        // message object to recieve message and its type
        // timestamp
        String date;
        String str;

        // Constructor
        ClientThread(Socket socket) {
            // a unique id
            id = ++uniqueId;
            this.socket = socket;
            //Creating both Data Stream
            System.out.println("Thread essaye de créer des flux entrant et sortant");
            try
            {

                sOutput = new PrintWriter(socket.getOutputStream());
               // sInput  = new ObjectInputStream(socket.getInputStream());
                sInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // read the username
                username = sInput.readLine();

                broadcast(notif + username + " à rejoint le serveur." + notif);
            }
            catch (IOException e) {
                display("Exception creation d'un nouveau flux Entrant et sortant: " + e);
                return;
            }
            catch (Exception e) {
                System.out.println("dd");
            }
            date = new Date().toString() + "\n";
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        // infinite loop to read and forward message
        public void run() {
            // to loop until LOGOUT
            boolean keepGoing = true;
            while(true){
                try{
                    str = sInput.readLine();
                    System.out.println(str);
                    broadcast(str);
                }catch (IOException e){
                    break;
                }
            }
            // if out of the loop then disconnected and remove from client list
            remove(id);
            close();
        }

        // close everything
        private void close() {
            try {
                if(sOutput != null) sOutput.close();
            }
            catch(Exception e) {}
            try {
                if(sInput != null) sInput.close();
            }
            catch(Exception e) {};
            try {
                if(socket != null) socket.close();
            }
            catch (Exception e) {}
        }

        // write a String to the Client output stream
        private boolean writeMsg(String msg) {
            // if Client is still connected send the message to it
            if(!socket.isConnected()) {
                close();
                return false;
            }
            // write the message to the stream
            try {
                sOutput.println(msg);
                sOutput.flush();
            }
            // if an error occurs, do not abort just inform the user
            catch(Exception e) {
                display(notif + "Erreur d'envoie du message pour " + username + notif);
                display(e.toString());
            }
            return true;
        }
    }
}

