import androidx.room.TypeConverter
import java.util.Date

class Converters {
    // Date -> Long konvertálás
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    // Long -> Date konvertálás
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }
}