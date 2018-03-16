package workspace.xyz.com.kontin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button

class Main22Activity : AppCompatActivity() {

    private var bt: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main22)
        initDate("1")
        bt = findViewById(R.id.bt_) as Button?

    }

    private fun initDate(s: String) {

    }


}
