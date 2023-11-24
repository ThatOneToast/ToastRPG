package pine.toast.toastrpg.worldevents

import java.time.LocalDateTime
import java.time.ZoneOffset

open class WorldEventTime(
   year: Int,
   month: Int,
   day: Int,
   hour: Int,
   minute: Int,
) {

   private val startDate: Long = LocalDateTime.of(year, month, day, hour, minute).toEpochSecond(ZoneOffset.UTC)
   private var lastCheck: Long = 0
   private var expire: Boolean = false

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

   fun check() : Boolean {
        return System.currentTimeMillis() >= startDate
   }

   fun update() {
      lastCheck = System.currentTimeMillis()
   }



}