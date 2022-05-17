package ua.cn.stu.navigation.activity

import android.content.Intent
import android.os.Bundle
import ua.cn.stu.navigation.BuildConfig
import ua.cn.stu.navigation.databinding.ActivityAboutBinding

class AboutActivity : BaseActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater).also { setContentView(it.root) }
        binding.versionNameTextView.text = BuildConfig.VERSION_NAME
        binding.versionCodeTextView.text = BuildConfig.VERSION_CODE.toString()
        binding.okButton.setOnClickListener { onOkPressed() }
        binding.button33.setOnClickListener { onButton33() }

    }

    private fun onOkPressed() {
        finish()
    }

    private fun onButton33() {
        val intent = Intent(this, EmptyActivity::class.java)
        startActivity(intent)
    }
}