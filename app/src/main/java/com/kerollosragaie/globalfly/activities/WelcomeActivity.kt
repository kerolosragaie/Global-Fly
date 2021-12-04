package com.kerollosragaie.globalfly.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kerollosragaie.globalfly.R
import com.kerollosragaie.globalfly.services.MessageService
import com.kerollosragaie.globalfly.services.ServiceBuilder
import id.ionbit.ionalert.IonAlert
import kotlinx.android.synthetic.main.activity_destiny_list.*
import kotlinx.android.synthetic.main.activity_welcome.*
import retrofit2.Response
import javax.security.auth.callback.Callback

class WelcomeActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_welcome)

		callAPI()

		button.setOnClickListener {
			getStarted()
		}
	}

	private fun getStarted() {
		val intent = Intent(this, DestinationListActivity::class.java)
		startActivity(intent)
		finish()
	}

	private fun callAPI(){
		// To be replaced by retrofit code
		//message.text = "Black Friday! Get 50% cash back on saving your first spot."
		//Show loading progress:
		loading_bar.visibility=View.VISIBLE
		val messageService: MessageService = ServiceBuilder.buildService(MessageService::class.java)
		val requestCall = messageService.getMessages("http://10.0.2.2:7000/messages")
		requestCall.enqueue(object : Callback, retrofit2.Callback<String> {
			override fun onResponse(call: retrofit2.Call<String>, response: Response<String>) {
				loading_bar.visibility=View.GONE
				if(response.isSuccessful){
					val mas : String?= response.body()
					mas?.let {
						message.text = it
					}
				}else{
					//Application level failure:
					IonAlert(this@WelcomeActivity, IonAlert.ERROR_TYPE)
						.setTitleText("Oops...")
						.setContentText("Failed to retrieve items.")
						.setConfirmText("Retry")
						.setCancelText("Cancel")
						.setConfirmClickListener {
							callAPI()
							it.dismiss()
						}
						.setCancelClickListener {
							it.dismiss()
						}
						.show()
				}
			}

			override fun onFailure(call: retrofit2.Call<String>, t: Throwable) {
				loading_bar.visibility=View.GONE
				IonAlert(this@WelcomeActivity, IonAlert.ERROR_TYPE)
					.setTitleText("Oops...")
					.setContentText("Failed to retrieve items.")
					.setConfirmText("Retry")
					.setCancelText("Cancel")
					.setConfirmClickListener {
						callAPI()
						it.dismiss()
					}
					.setCancelClickListener {
						it.dismiss()
					}
					.show()
			}

		})
	}

}
