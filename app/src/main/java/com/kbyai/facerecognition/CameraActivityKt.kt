package com.kbyai.facerecognition

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Size
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.kbyai.facerecognition.SettingsActivity.Companion.getIdentifyThreshold
import com.kbyai.facerecognition.SettingsActivity.Companion.getLivenessLevel
import com.kbyai.facerecognition.SettingsActivity.Companion.getLivenessThreshold
import com.kbyai.facesdk.FaceDetectionParam
import com.kbyai.facesdk.FaceSDK
import io.fotoapparat.Fotoapparat
import io.fotoapparat.parameter.Resolution
import io.fotoapparat.preview.Frame
import io.fotoapparat.preview.FrameProcessor
import io.fotoapparat.selector.front
import io.fotoapparat.view.CameraView

class CameraActivityKt : AppCompatActivity() {

    val TAG = CameraActivity::class.java.simpleName
    val PREVIEW_WIDTH = 720
    val PREVIEW_HEIGHT = 1280

    private lateinit var cameraView: CameraView
    private lateinit var faceView: FaceView
    private lateinit var fotoapparat: Fotoapparat
    private lateinit var context: Context

    private var recognized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_kt)

        context = this
        cameraView = findViewById(R.id.preview)
        faceView = findViewById(R.id.faceView)

        fotoapparat = Fotoapparat.with(this)
            .into(cameraView)
            .lensPosition(front())
            .frameProcessor(FaceFrameProcessor())
            .previewResolution { Resolution(PREVIEW_HEIGHT,PREVIEW_WIDTH) }
            .build()

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)
        } else {
            fotoapparat.start()
        }
    }

    override fun onResume() {
        super.onResume()
        recognized = false
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED
        ) {
            fotoapparat.start()
        }
    }

    override fun onPause() {
        super.onPause()
        fotoapparat.stop()
        faceView.setFaceBoxes(null)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED
            ) {
                fotoapparat.start()
            }
        }
    }

    inner class FaceFrameProcessor : FrameProcessor {

        @SuppressLint("SetTextI18n")
        override fun process(frame: Frame) {

            if(recognized == true) {
                return
            }

            var cameraMode = 7
            if (SettingsActivity.getCameraLens(context) == CameraSelector.LENS_FACING_BACK) {
                cameraMode = 6
            }

            val bitmap = FaceSDK.yuv2Bitmap(frame.image, frame.size.width, frame.size.height, cameraMode)

            val faceDetectionParam = FaceDetectionParam()
            faceDetectionParam.check_liveness = true
            faceDetectionParam.check_liveness_level = getLivenessLevel(context)
            val faceBoxes = FaceSDK.faceDetection(bitmap, faceDetectionParam)

            runOnUiThread {
                faceView.setFrameSize(Size(bitmap.width, bitmap.height))
                faceView.setFaceBoxes(faceBoxes)
            }

            if(faceBoxes.size > 0) {
                if(faceBoxes.size == 2){
                    val faceBox0 = faceBoxes[0]
                    val faceBox1 = faceBoxes[1]
                    if (faceBox0.liveness > SettingsActivity.getLivenessThreshold(context) && faceBox1.liveness > SettingsActivity.getLivenessThreshold(context)) {
                        val templates0 = FaceSDK.templateExtraction(bitmap, faceBox0)
                        val templates1 = FaceSDK.templateExtraction(bitmap, faceBox1)

                        var maxSimiarlity0 = 0f
                        var maximiarlityPerson0: Person? = null
                        var maxSimiarlity1 = 0f
                        var maximiarlityPerson1: Person? = null

                        for (person in DBManager.personList) {
                            val similarity = FaceSDK.similarityCalculation(templates0, person.templates)
                            if (similarity > maxSimiarlity0) {
                                maxSimiarlity0 = similarity
                                maximiarlityPerson0 = person
                            }
                        }

                        for (person in DBManager.personList) {
                            val similarity = FaceSDK.similarityCalculation(templates1, person.templates)
                            if (similarity > maxSimiarlity1) {
                                maxSimiarlity1 = similarity
                                maximiarlityPerson1 = person
                            }
                        }

                        if (maxSimiarlity0 > SettingsActivity.getIdentifyThreshold(context) && maxSimiarlity1 > SettingsActivity.getIdentifyThreshold(context)) {
                            recognized = true
                            val identifiedPerson0 = maximiarlityPerson0
                            val identifiedSimilarity0 = maxSimiarlity0
                            val identifiedPerson1 = maximiarlityPerson1
                            val identifiedSimilarity1 = maxSimiarlity1

                            runOnUiThread {
                                val faceImage0 = Utils.cropFace(bitmap, faceBox0)
                                val intent = Intent(context, ResultActivity::class.java)
//                                intent.putExtra("identified_face_0", faceImage0)
//                                intent.putExtra("enrolled_face_0", identifiedPerson0!!.face)
                                intent.putExtra("identified_name_0", identifiedPerson0!!.name)
                                intent.putExtra("similarity_0", identifiedSimilarity0)
                                intent.putExtra("liveness_0", faceBox0.liveness)

                                ResultActivity.identified_face_0 = faceImage0
                                ResultActivity.enrolled_face_0 = identifiedPerson0!!.face
//                                intent.putExtra("yaw", faceBox.yaw)
//                                intent.putExtra("roll", faceBox.roll)
//                                intent.putExtra("pitch", faceBox.pitch)

                                val faceImage1 = Utils.cropFace(bitmap, faceBox1)
//                                intent.putExtra("identified_face_1", faceImage1)
//                                intent.putExtra("enrolled_face_1", identifiedPerson1!!.face)
                                ResultActivity.identified_face_1 = faceImage1
                                ResultActivity.enrolled_face_1 = identifiedPerson1!!.face
                                intent.putExtra("identified_name_1", identifiedPerson1!!.name)
                                intent.putExtra("similarity_1", identifiedSimilarity1)
                                intent.putExtra("liveness_1", faceBox1.liveness)
                                startActivity(intent)
                            }
                        }
                    }
                } else {
                    val faceBox = faceBoxes[0]
                    if (faceBox.liveness > SettingsActivity.getLivenessThreshold(context)) {
                        val templates = FaceSDK.templateExtraction(bitmap, faceBox)

                        var maxSimiarlity = 0f
                        var maximiarlityPerson: Person? = null
                        for (person in DBManager.personList) {
                            val similarity = FaceSDK.similarityCalculation(templates, person.templates)
                            if (similarity > maxSimiarlity) {
                                maxSimiarlity = similarity
                                maximiarlityPerson = person
                            }
                        }

                        if (maxSimiarlity > SettingsActivity.getIdentifyThreshold(context)){
                            runOnUiThread {
                                val tv = findViewById<View>(R.id.notify) as TextView
                                val identifiedPerson = maximiarlityPerson
                                tv.text = "Now one face ${identifiedPerson?.name!!} was identified\nPlease try with 2 faces to get more details "
                            }
                        } else {
                            runOnUiThread {
                                val tv = findViewById<View>(R.id.notify) as TextView
                                tv.text = "Now you are trying with one face\nPlease try with 2 faces to get more details "
                            }
                        }
                    }
                }

            }
        }
    }
}