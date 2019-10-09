package kt.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonMapper<T> {

	@SuppressWarnings("unchecked")
	public JSONObject getJSONObject(Object instance) throws Exception {
		JSONObject jsonObject = null;
		
		if (instance == null) {
			return jsonObject;
		}
		
		Class<?> clazz = instance.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		if (fields == null || fields.length == 0) {
			return jsonObject;
		}
		
		jsonObject = new JSONObject();
		
		for (Field field : fields) {
			StringBuffer methodName = new StringBuffer();
			
			if (field.getType() == boolean.class) {
				methodName.append("is");
			} else {
				methodName.append("get");
			}
			
			methodName.append(field.getName().substring(0, 1).toUpperCase());
			methodName.append(field.getName().substring(1));
			
			Method method = clazz.getDeclaredMethod(methodName.toString());
			jsonObject.put(field.getName(), method.invoke(instance));
		}
		
		return jsonObject;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray getJSONArraySimple(List<T> list) throws Exception {
		JSONArray rtArray = null;
		
		if (list == null || list.size() == 0) {
			return rtArray;
		}
		
		rtArray = new JSONArray();
		for (T obj : list) {
			rtArray.add(obj);
		}
		
		return rtArray;
	}

	@SuppressWarnings("unchecked")
	public JSONArray getJSONArray(List<T> list) throws Exception {
		JSONArray rtArray = null;
		
		if (list == null || list.size() == 0) {
			return rtArray;
		}
		
		rtArray = new JSONArray();
		for (T obj : list) {
			rtArray.add(getJSONObject(obj));
		}
		
		return rtArray;
	}
}
