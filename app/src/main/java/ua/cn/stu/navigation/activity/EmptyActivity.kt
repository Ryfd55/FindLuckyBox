package ua.cn.stu.navigation.activity

import android.content.Intent
import android.os.Bundle
import ua.cn.stu.navigation.R
import ua.cn.stu.navigation.databinding.ActivityEmptyBinding


class EmptyActivity : BaseActivity() {

    private lateinit var binding: ActivityEmptyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEmptyBinding.inflate(layoutInflater).also { setContentView(it.root) }
        binding.buttonBack.setOnClickListener { onButtonBack() }
    }
    private fun onButtonBack() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }
}