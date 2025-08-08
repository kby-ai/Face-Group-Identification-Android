package com.kbyai.facerecognition

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    companion object {
        var identified_face_0: Bitmap? = null
        var enrolled_face_0: Bitmap? = null
        var identified_face_1: Bitmap? = null
        var enrolled_face_1: Bitmap? = null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

//        val identifyedFace0 = intent.getParcelableExtra("identified_face_0") as? Bitmap
//        val enrolledFace0 = intent.getParcelableExtra("enrolled_face_0") as? Bitmap
        val identifiedName0 = intent.getStringExtra("identified_name_0")
        val similarity0 = intent.getFloatExtra("similarity_0", 0f)
        val livenessScore0 = intent.getFloatExtra("liveness_0", 0f)
//        val yaw = intent.getFloatExtra("yaw", 0f)
//        val roll = intent.getFloatExtra("roll", 0f)
//        val pitch = intent.getFloatExtra("pitch", 0f)

//        val identifyedFace1 = intent.getParcelableExtra("identified_face_1") as? Bitmap
//        val enrolledFace1 = intent.getParcelableExtra("enrolled_face_1") as? Bitmap
        val identifiedName1 = intent.getStringExtra("identified_name_1")
        val similarity1 = intent.getFloatExtra("similarity_1", 0f)
        val livenessScore1 = intent.getFloatExtra("liveness_1", 0f)

        findViewById<ImageView>(R.id.imageEnrolled).setImageBitmap(ResultActivity.enrolled_face_0)
        findViewById<ImageView>(R.id.imageIdentified).setImageBitmap(ResultActivity.identified_face_0)
        findViewById<TextView>(R.id.textPerson).text = "Identified 1: " + identifiedName0
        findViewById<TextView>(R.id.textSimilarity).text = "Similarity 1: " + similarity0
        findViewById<TextView>(R.id.textLiveness).text = "Liveness score 1: " + livenessScore0
//        findViewById<TextView>(R.id.textYaw).text = "Yaw: " + yaw
//        findViewById<TextView>(R.id.textRoll).text = "Roll: " + roll
//        findViewById<TextView>(R.id.textPitch).text = "Pitch: " + pitch

        findViewById<ImageView>(R.id.imageEnrolled1).setImageBitmap(ResultActivity.enrolled_face_1)
        findViewById<ImageView>(R.id.imageIdentified1).setImageBitmap(ResultActivity.identified_face_1)
        findViewById<TextView>(R.id.textPerson1).text = "Identified 1: " + identifiedName1
        findViewById<TextView>(R.id.textSimilarity1).text = "Similarity 1: " + similarity1
        findViewById<TextView>(R.id.textLiveness1).text = "Liveness score 1: " + livenessScore1
    }
}