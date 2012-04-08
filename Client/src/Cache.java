import java.util.HashSet;
import java.util.Set;


public class Cache {
	Set<Objects> set = new HashSet<Objects>();

	// returns object if id of objects was found in cache
	public Objects search(int id) {
		for (Objects temp : set) {
			if (temp.getId() == id)
				return temp;
		}
		return null;
	}

	// update objects
	public Objects update(int id, String data) {
		Objects ob = search(id);
		ob.setDate(data);
		return ob;
	}

	
	// adds a new element in the cache
	public Objects add(Objects ob) {
		set.add(ob.clone());
		return ob;
	}
}
