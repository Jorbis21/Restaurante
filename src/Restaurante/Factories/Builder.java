package Restaurante.Factories;
import org.json.JSONObject;

public abstract class Builder<T>{
	protected String _typeTag;
	protected String _desc;
	public Builder(String type, String desc) {
		this._typeTag = type;
		this._desc = desc;
	}
	public T createInstance(JSONObject info) {
		T x = null;
		if(_typeTag != null && _typeTag.equals(info.getString("type")));
			x = createTheInstance(info.getJSONObject("data"));
		return x;
	}
	protected abstract T createTheInstance(JSONObject jsonObject);
	public JSONObject getBuilderInfo() {
		JSONObject info = new JSONObject();
		info.put("type", _typeTag);
		info.put("data", createData());
		info.put("desc", _desc);
		return info;
	}
	protected JSONObject createData() {
		return new JSONObject();
	}
}
