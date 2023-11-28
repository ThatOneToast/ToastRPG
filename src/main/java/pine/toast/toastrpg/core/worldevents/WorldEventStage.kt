package pine.toast.toastrpg.core.worldevents

enum class WorldEventStage {
    ONE_DAY,
    TWELVE_HOURS,
    SIX_HOURS,
    ONE_HOUR,
    THIRTY_MINUTES,
    FIFTEEN_MINUTES,
    FIVE_MINUTES,
    ONE_MINUTE

    ;

   companion object {

      fun getStage(time: Long): WorldEventStage {
         return when (time) {
            in 0 until 172800000 -> ONE_DAY
            in 172800000 until 259200000 -> TWELVE_HOURS
            in 259200000 until 302400000 -> SIX_HOURS
            in 302400000 until 311040000 -> ONE_HOUR
            in 311040000 until 314496000 -> THIRTY_MINUTES
            in 314496000 until 315360000 -> FIFTEEN_MINUTES
            in 315360000 until 315576000 -> FIVE_MINUTES
            else -> ONE_MINUTE
         }
      }
   }


}