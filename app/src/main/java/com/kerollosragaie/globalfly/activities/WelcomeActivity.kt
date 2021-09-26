package com.kerollosragaie.globalfly.activities

import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kerollosragaie.globalfly.R
import com.kerollosragaie.globalfly.services.MessageService
import com.kerollosragaie.globalfly.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destiny_list.*
import kotlinx.android.synthetic.main.activity_welcome.*
import retrofit2.Response
import javax.security.auth.callback.Callback

class WelcomeActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_welcome)

		// To be replaced by retrofit code
		//message.text = "Black Friday! Get 50% cash back on saving your first spot."
		val messageService: MessageService = ServiceBuilder.buildService(MessageService::class.java)
		val requestCall = messageService.getMessages("http://10.0.2.2:7000/messages")
		requestCall.enqueue(object : Callback, retrofit2.Callback<String> {
			override fun onResponse(call: retrofit2.Call<String>, response: Response<String>) {
				if(response.isSuccessful){
					if(response.isSuccessful){
						val mas : String?= response.body()
						mas?.let {
							message.text = it
						}
					}else{//Application level failure:
						Toast.makeText(this@WelcomeActivity,"Failed to retrieve items",
							Toast.LENGTH_LONG).show()
					}
				}
			}

			override fun onFailure(call: retrofit2.Call<String>, t: Throwable) {
				Toast.makeText(this@WelcomeActivity,"Failed to retrieve items",
					Toast.LENGTH_LONG).show()
			}

		})
	}

	fun getStarted(view: View) {
		val intent = Intent(this, DestinationListActivity::class.java)
		startActivity(intent)
		finish()
	}
}
