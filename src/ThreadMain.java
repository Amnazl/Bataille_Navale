import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadMain extends Thread {

    // socket serveur
    private static ServerSocket serverSocket = null;
    // socket client
    private static Socket clientSocket = null;

    // nombre max de clients.
    private  int maxClientsCount = 10;
    private  clientThread[] threads = new clientThread[maxClientsCount];

    public void run() {

        /* Début du serveur */
        // attribution du port par défaut
        int portNumber = 18000;
          /*
           Ouvrez une socket serveur sur le portNumber
           */
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.out.println(e);
        }

        /*
         * Créez une Socket client pour chaque connexion et passez-la à un nouveau client.
         * thread.
         */
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                int i;
                for (i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == null) {
                        (threads[i] = new clientThread(clientSocket, threads)).start();
                        break;
                    }
                }
                if (i == maxClientsCount) {
                    PrintStream os = new PrintStream(clientSocket.getOutputStream());
                    os.println("Server too busy. Try later.");
                    os.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
