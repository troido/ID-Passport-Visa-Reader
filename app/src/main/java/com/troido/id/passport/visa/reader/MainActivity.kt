package com.troido.id.passport.visa.reader


import android.app.Activity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.troido.id.passport.visa.reader.databinding.ActivityMainBinding
import com.troido.mrzscanner.common.MrzData
import com.troido.mrzscanner.scanner.MrzScanActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val scanContent =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                    val mrzData =
                        it.data?.getSerializableExtra(MrzScanActivity.INTENT_MRZ_DATA_KEY) as MrzData?

                    binding.resultTv.text = buildString {
                        append("${mrzData?.name}, ${mrzData?.surname} ")
                        append("DocumentID: ${mrzData?.documentID} ")
                        append("Date of birth: ${mrzData?.date} ")
                        append("Country code: ${mrzData?.countryCode}")
                    }
                }
            }

        binding.startDetection.setOnClickListener {
            scanContent.launch(MrzScanActivity.getIntent(this))
        }
    }
}