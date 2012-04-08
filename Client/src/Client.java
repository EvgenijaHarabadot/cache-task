import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	Cache ch = new Cache();
	Socket clientSocket;

	enum Command {
		date, object, stop
	};

	// creates connection with server
	public void connect(String host, int port) throws Exception {

		try {
			clientSocket = new Socket(host, port);
		} catch (IOException e) {

			System.err.println("Exseption: " + e);

		}

	}

	// returns object from server or from cache
	public Object get(int id) {
		Objects obC; // reference on the object from the cache
		Objects obS = null; // reference on the object from the server

		if ((obC = ch.search(id)) != null) { // object exist in cache
			obS.date = (String) request(id, Command.date);

			if (obC.date != obS.date) { // needs update data
				return ch.update(id, obS.date);
			} else
				return obC;

		} else { // object exist in server
			if ((obS = (Objects) request(id, Command.object)) != null)
				return ch.add(obS);
			else
				return null; // Message object does not exist

		}
	}

	// forwards the request to server and gets object or its date
	public Object request(int id, Command s) {
		try {

			ObjectOutputStream out = new ObjectOutputStream(
					clientSocket.getOutputStream());
			out.writeObject(s.name());
			out.close();

			switch (s.ordinal()) {

			case 0: {
				ObjectInputStream in1 = new ObjectInputStream(
						clientSocket.getInputStream());
				String mes1 = (String) in1.readObject();
				in1.close();
				return mes1;

			}

			case 1: {
				ObjectInputStream in1 = new ObjectInputStream(
						clientSocket.getInputStream());
				Objects mes2 = (Objects) in1.readObject();
				in1.close();
				return mes2;
			}
			default:
				break;

			}

		} catch (IOException e) {

			System.err.println("Exseption: " + e);

		} catch (Exception e) {

			System.err.println("Exseption: " + e);

		}
		return null;

	}
}
