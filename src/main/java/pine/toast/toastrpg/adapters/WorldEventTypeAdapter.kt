package pine.toast.toastrpg.adapters

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import pine.toast.toastrpg.worldevents.WorldEvent

class WorldEventTypeAdapter : TypeAdapter<WorldEvent>() {

    override fun read(`in`: JsonReader): WorldEvent {
        val jsonObject = readJsonObject(`in`)
        // Add logic to create the appropriate subclass based on eventType
        when (val eventType = jsonObject.getAsJsonPrimitive("eventType").asString) {
            "WorldEvent" -> return Gson().fromJson(jsonObject, WorldEvent::class.java)
            // Add more cases for other concrete event types
            else -> throw IllegalArgumentException("Unknown eventType: $eventType")
        }
    }
    override fun write(out: JsonWriter, value: WorldEvent?) {
        if (value == null) {
            out.nullValue()
            return
        }

        out.beginObject()
        out.name("eventName").value(value.getName())
        out.name("eventType").value(value.getType().toString())
        out.name("eventTime").value(value.getSpawnTime().toString())
        out.endObject()
    }

    private fun readJsonObject(`in`: JsonReader): JsonObject {
        // Implement reading a JsonObject from the JsonReader
        // This can vary depending on the structure of your JSON
        // For simplicity, you can use Gson to parse the JsonObject
        return Gson().fromJson(`in`, JsonObject::class.java)
    }
}