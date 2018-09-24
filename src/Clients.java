import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Clients implements Runnable {

    // client socket
    private static Socket clientSocket = null;
    // output stream
    private static PrintStream os = null;
    // input stream
    private static DataInputStream is = null;

    private static BufferedReader inputLine = null;
    private static boolean closed = false;
    private int nbClients =0; // nombre total de clients connectÃ©s

    public static void main(String[] args) {

        // port par défault
        int portNumber = 18000;
        // host par default
        String host = "localhost";

        if (args.length < 2) {
            System.out
                    .println("Usage: java MultiThreadChatClient <host> <portNumber>\n"
                            + "Now using host=" + host + ", portNumber=" + portNumber);
        } else {
            host = args[0];
            portNumber = Integer.valueOf(args[1]).intValue();
        }

        /*
         * ouverture (ports, socket ....
         */
        try {
            clientSocket = new Socket(host, portNumber);
            inputLine = new BufferedReader(new InputStreamReader(System.in));
            os = new PrintStream(clientSocket.getOutputStream());
            is = new DataInputStream(clientSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host "
                    + host);
        }

        /*
         * Si tout a été initialisé alors nous voulons écrire quelques données dans le fichier
         * socket sur lequel nous avons ouvert une connexion sur le port portNumber.
         */
        if (clientSocket != null && os != null && is != null) {
            try {

                /* création du thread de lecture du server. */
                new Thread(new Clients()).start();
                while (!closed) {
                    os.println(inputLine.readLine().trim());
                }
                /*
                 * clos tout le reste
                 */
                os.close();
                is.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }

    /*
     * Create a thread to read from the server. (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    public void run() {
        /*
         * continue à lire dans la socket jusqu'à ce que nous recevions "Bye" de la part du
         * serveur. Une fois que nous avons reçu cela, nous voulons rompre.
         */
        String responseLine;
        try {
            while ((responseLine = is.readLine()) != null) {
                System.out.println(responseLine);
                if (responseLine.indexOf("*** Bye") != -1)
                    break;
            }
            closed = true;
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}