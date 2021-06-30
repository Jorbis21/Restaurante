/**
 * Interfaz de las factorias
 */
package restaurante.factories;

import java.util.List;

import org.json.JSONObject;

public interface Factory<T> {
	/**
	 * Crea la instancia a traves del JSONObject
	 * @param info
	 * @return
	 */
	public T createInstance(JSONObject info);
	/**
	 * Coge la informacion
	 * @return
	 */
	public List<JSONObject> getInfo();
	
}
