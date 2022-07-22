package com.ml.truckingandconstructionwork.presentation.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.databinding.FragmentMapBinding
import com.ml.truckingandconstructionwork.presentation.base.BaseFragment
import com.ml.truckingandconstructionwork.presentation.utils.viewBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapFragment: BaseFragment<MapViewModel, FragmentMapBinding>()

 {
    override val viewModel: MapViewModel by viewModel()
    override val binding: FragmentMapBinding by viewBinding()


     private val MAPKIT_API_KEY = "a0255a66-b26a-452f-9b38-744fdb9a74fb"
     private val TARGET_LOCATION: Point = Point(40.177200, 44.503490)
     lateinit var  mapView:MapView


     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View {

         MapKitFactory.setApiKey(MAPKIT_API_KEY);
         MapKitFactory.initialize(context);

        return inflater.inflate(R.layout.fragment_map, container, false)
     }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // initMap()
         mapView = view.findViewById(R.id.mapview) as MapView


         mapView.map.move(
             CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
             Animation(Animation.Type.SMOOTH, 5F),
             null
         )
     }

     override fun onView() {




         // Перемещение камеры в центр Санкт-Петербурга.
//
//        mapView.map.move(
//             CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
//             Animation(Animation.Type.SMOOTH, 5F),
//             null
//         )
     }


    override fun onStop() {
         // Вызов onStop нужно передавать инстансам MapView и MapKit.
         mapView.onStop()
         MapKitFactory.getInstance().onStop()
         super.onStop()
     }

 override fun onStart() {
         // Вызов onStart нужно передавать инстансам MapView и MapKit.
         super.onStart()
         MapKitFactory.getInstance().onStart()
         mapView.onStart()
     }


}