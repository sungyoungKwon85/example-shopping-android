package com.example.shopping.intro

import android.app.Activity
import android.os.Bundle
import com.example.shopping.common.Prefs
import com.example.shopping.product.ProductMainActivity
import com.example.shopping.signin.SigninActivity
import com.example.shopping.signup.SignupActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class IntroActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        IntroActivityUI().setContentView(this)

//        runBlocking {
//            try {
//                val response = ShoppingApi.instance.hello()
//                response.data?.let { Log.d("IntroActivity", it) }
//            } catch (e: Exception) {
//                Log.e("IntroActivity", "Hello API 호출 오류")
//            }
//        }

        GlobalScope.launch {
            delay(1500)
            if (Prefs.token.isNullOrEmpty()) {
                startActivity<SigninActivity>()
            } else {
                startActivity<ProductMainActivity>()
            }
            finish()
        }
    }
}