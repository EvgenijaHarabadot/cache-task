import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	Cache ch = new Cache();
	Socket clientSocket;

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
			obS.date = requestDate(id);

			if (obC.date != obS.date) { // needs update data
				return ch.update(id, obS.date);
			} else
				return obC;

		} else { // object exist in server
			if ((obS = requestObj(id)) != null)
				return ch.add(obS);
			else
				return null; // Message object does not exist

		}
	}

	// gets data from server
	public String requestDate(int id) {
		try {

			ObjectOutputStream out = new ObjectOutputStream(
					clientSocket.getOutputStream());
			out.writeObject("date");
			out.close();

			ObjectInputStream in1 = new ObjectInputStream(
					clientSocket.getInputStream());
			String message = (String) in1.readObject();
			in1.close();
			return message;
		} catch (IOException e) {

			System.err.println("Exseption: " + e);

		} catch (Exception e) {

			System.err.println("Exseption: " + e);

		}
		return null;
		
	}

	// gets object from server
	public Objects requestObj(int id) {
		try {

			ObjectOutputStream out = new ObjectOutputStream(
					clientSocket.getOutputStream());
			out.writeObject("object");
			out.close();

			ObjectInputStream in1 = new ObjectInputStream(
					clientSocket.getInputStream());
			Objects message = (Objects) in1.readObject();
			in1.close();
			return message;
		} catch (IOException e) {

			System.err.println("Exseption: " + e);

		} catch (Exception e) {

			System.err.println("Exseption: " + e);

		}
		return null;
}
}