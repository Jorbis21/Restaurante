/**
 * Constructora generica de los objetos
 */
package restaurante.factories;
import org.json.JSONObject;

public abstract class Builder<T>{
	//------------------------
	//Atributos
	//------------------------
	protected String _typeTag;
	protected String _desc;
	//------------------------
	//Metodos
	//------------------------
	/**
	 * Constructor
	 * @param type
	 * @param desc
	 */
	public Builder(String type, String desc) {
		this._typeTag = type;
		this._desc = desc;
	}
	/**
	 * Busca y crea el objeto adecuado
	 * @param info
	 * @return
	 */
	public T createInstance(JSONObject info) {
		T x = null;
		if(_typeTag != null && _typeTag.equals(info.getString("type")));
			x = createTheInstance(info.getJSONObject("data"));
		return x;
	}
	/**
	 * Crea el objeto
	 * @param jsonObject
	 * @return
	 */
	protected abstract T createTheInstance(JSONObject jsonObject);
	/**
	 * Devuelve la informacion del constructor
	 * @return
	 */
	public JSONObject getBuilderInfo() {
		JSONObject info = new JSONObject();
		info.put("type", _typeTag);
		info.put("data", createData());
		info.put("desc", _desc);
		return info;
	}
	/**
	 * Crea el ojeto donde van a ir los datos
	 * @return
	 */
	protected JSONObject createData() {
		return new JSONObject();
	}
}
