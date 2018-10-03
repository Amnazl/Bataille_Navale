
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;



public class Client  {

    private String notif = " *** ";

    private BufferedReader sInput;
   // private ObjectOutputStream sOutput;
    private PrintWriter sOutput;
    private Socket socket;
    private String server, username;
    private int port;
    public static String m ;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    Client(String server, int port, String username) {
        this.server = server;
        this.port = port;
        this.username = username;
        this.start();

    }


    public boolean start() {

        try {
            socket = new Socket(server, port);
        }
        catch(Exception ec) {
            display("Error connectiong to server:" + ec);
            return false;
        }

        String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
       // display(msg);

        /* Creating both Data Stream */
        try
        {
            sInput  = new BufferedReader(new InputStreamReader(socket.getInputStream()));//socket.getInputStream());
           // sOutput = new ObjectOutputStream(socket.getOutputStream());
            sOutput = new PrintWriter(socket.getOutputStream());
        }
        catch (IOException eIO) {
            display("Exception creating new Input/output Streams: " + eIO);
            return false;
        }

        // creates the Thread to listen from the server
        new ListenFromServer().start();
        // Send our username to the server this is the only message that we
        // will send as a String. All other messages will be ChatMessage objects
        try
        {
            //sOutput.write(username);
            sOutput.println(username);
            sOutput.flush();
        }
        catch (Exception eIO) {
            display("Exception doing login : " + eIO);
            disconnect();
            return false;
        }
        // success we inform the caller that it worked
        return true;
    }

    /*
     * To send a message to the console
     */
    private void display(String msg) {
        this.m = msg;


    }

    /*
     * To send a message to the server
     */
    void sendMessage(String str) {
        try {

            //sOutput.write(str);
            sOutput.println(str);
            sOutput.flush();
        }
        catch(Exception e) {
            display("Exception writing to server: " + e);
        }
    }


    void getMessage(){
        try {
            sInput.readLine();
        }
        catch(IOException e) {
            display("Exception writing to server: " + e);
        }
    }

    public String getMessage2(){
        System.out.println(m);

        return m;

    }

    /*
     * When something goes wrong
     * Close the Input/Output streams and disconnect
     */
    private void disconnect() {
        try {
            if(sInput != null) sInput.close();
        }
        catch(Exception e) {}
        try {
            if(sOutput != null) sOutput.close();
        }
        catch(Exception e) {}
        try{
            if(socket != null) socket.close();
        }
        catch(Exception e) {}

    }


     class ListenFromServer extends Thread{
        public String str;
        public void run() {
            while(true) {
                try {
                    // read the message form the input datastream
                    String msg = sInput.readLine();

                    if(msg.contains("X")){
                        System.out.println(msg);
                        display(msg);
                        break;
                    }

                }
                catch(IOException e) {
                    display(notif + "Server has closed the connection: " + e + notif);
                    break;
                }
                catch(Exception e2) {
                }
            }
        }

        public  String getFromServer(){
            return str;
        }
}
}


