package pine.toast.toastrpg.worldevents

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

open class WorldEventTime(
   year: Int,
   month: Int,
   day: Int,
   hour: Int,
   minute: Int,
   sec: Int,
) {

   private val startDate: Instant = LocalDateTime.of(year, month, day, hour, minute, sec).toInstant(ZoneOffset.UTC)
   private var lastCheck: Long = 0
   private var expire: Boolean = false

   fun getStartDate(): Instant {
      return startDate
   }

   fun isExpired(): Boolean {
      return !expire
   }

   fun setExpired() {
      expire = true
   }

   fun getLastCheck(): Long {
      return lastCheck
   }

   fun check() : Boolean {
        return System.currentTimeMillis() >= startDate.toEpochMilli()
   }

   fun update() {
      lastCheck = System.currentTimeMillis()
   }



}