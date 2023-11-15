package pine.toast.EventTimeManagement

import java.time.LocalDateTime
import java.time.ZoneOffset

class EventTime(
   private val year: Int,
   private val month: Int,
   private val day: Int,
   private val hour: Int,
   private val minute: Int,
   private val startDate: Long = LocalDateTime.of(year, month, day, hour, minute).toEpochSecond(ZoneOffset.UTC),
   private var lastCheck: Long,
   private var expire: Boolean
) {

   fun getStartDate(): Long {
      return startDate
   }

   fun isExpired(): Boolean {
      return expire
   }

   fun setExpired() {
      expire = true
   }

   fun getLastCheck(): Long {
      return lastCheck
   }

   fun update() {
      lastCheck = System.currentTimeMillis()
   }



}