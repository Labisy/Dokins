package rgrtu.team.dokins

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.se.omapi.Session
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.directions.driving.DrivingRoute
import com.yandex.mapkit.directions.driving.DrivingSession
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.Error
import rgrtu.team.dokins.authorization.Authorization
import rgrtu.team.dokins.initialize.MapKitInitialize

class MainActivity : AppCompatActivity(), DrivingSession.DrivingRouteListener {
    //#55FFFFFF
    lateinit var mapView: MapView
    lateinit var mapObjects: MapObject
    lateinit var searchSession: Session
    lateinit var startLocation: Point
    lateinit var menu: ImageButton
    lateinit var apiKey: String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiKey = "c669c943-e038-4046-9c2f-0004b106a3b7"

        MapKitInitialize.initialize(apiKey, this)
        setContentView(R.layout.activity_main)

        val mapkit: MapKit = MapKitFactory.getInstance()
        mapView = findViewById(R.id.mapview)
        mapView.map.move(
            CameraPosition(Point(54.629946, 39.710315), 11.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 1.5f), null
        )
        requestLocationPermission()
        val locationOnMapkit = mapkit.createUserLocationLayer(mapView.mapWindow)
        locationOnMapkit.isVisible = true

        menu = findViewById(R.id.menu_button)
        menu.setOnClickListener {
            showPopupMenu()
        }
    }

    private fun showPopupMenu() {
        val popupMenu = PopupMenu(this, menu)
        popupMenu.inflate(R.menu.menu_main)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.sign -> {
                    toAuthorizationActivity()
                }

            }
            false
        }
        popupMenu.show()
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                0
            )
            return
        }
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }

    private fun toAuthorizationActivity() {
        val intent = Intent(this, Authorization::class.java)
        startActivity(intent)
    }

    override fun onDrivingRoutes(p0: MutableList<DrivingRoute>) {
        TODO("Not yet implemented")
    }

    override fun onDrivingRoutesError(p0: Error) {
        TODO("Not yet implemented")
    }
}