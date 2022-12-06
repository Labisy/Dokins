package rgrtu.team.dokins.initialize

import android.content.Context
import com.yandex.mapkit.MapKitFactory

object MapKitInitialize {
    private var initialized = false

    fun initialize(apiKey: String, context: Context) {
        if (initialized) {
            return
        }

        MapKitFactory.setApiKey(apiKey)
        MapKitFactory.initialize(context)
        initialized = true
    }
}