package ua.cn.stu.navigation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        return true
    }

}