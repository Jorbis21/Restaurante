package restaurante.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T>{

	private List<Builder<T>> b;
	private List<JSONObject> l;
	
	public BuilderBasedFactory (List<Builder<T>> builders) {
		b = builders;
		l = new ArrayList<JSONObject>();
		for(Builder<T> i : b) {
			l.add(i.getBuilderInfo());
		}
	}
	
	public T createInstance(JSONObject info) {
		for (Builder<T> i : b) {
			if(info.getString("type").equals(i._typeTag)) {
				return i.createInstance(info);
			}
		}
		throw new IllegalArgumentException();
	}

	public List<JSONObject> getInfo() {
		return l;
	}

}
