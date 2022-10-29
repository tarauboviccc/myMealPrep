package persistence;

import org.json.JSONObject;

public interface Writable {
    // CITATIONS: used the example given in JsonSerializationDemo
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
