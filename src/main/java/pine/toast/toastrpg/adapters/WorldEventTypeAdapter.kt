package pine.toast.toastrpg.adapters

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import pine.toast.toastrpg.worldevents.WorldEvent
import pine.toast.toastrpg.worldevents.WorldEventTime


class WorldEventTypeAdapter : TypeAdapter<Any?>() {


    override fun read(`in`: JsonReader): Any? {

        val jsonObject = readJsonObject(`in`)
        return when (val eventType = jsonObject.getAsJsonPrimitive("eventType").asString) {
            "WorldEvent" -> Gson().fromJson(jsonObject, WorldEvent::class.java)
            "WorldEventTime" -> Gson().fromJson(jsonObject, WorldEventTime::class.java)
            else -> throw IllegalArgumentException("Unknown eventType: $eventType")
        }
    }
    override fun write(out: JsonWriter, value: Any?) {
        if (value == null) {
            out.nullValue()
            return
        }


        out.beginObject()

        when (value) {
            is WorldEvent -> {
                out.name("eventName").value(value.getName())
                out.name("eventType").value("WorldEvent")
                out.name("eventTime").value(value.getSpawnTime().toString())
            }

            is WorldEventTime -> {
                out.name("eventTime").value(value.toString())
            }

            else -> {
                throw IllegalArgumentException("Unknown type: ${value?.javaClass?.simpleName}")
            }
        }

    }

    private fun readJsonObject(`in`: JsonReader): JsonObject {
        return Gson().fromJson(`in`, JsonObject::class.java)
    }
}