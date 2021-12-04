package com.kerollosragaie.globalfly.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kerollosragaie.globalfly.R
import com.kerollosragaie.globalfly.helpers.DestinationAdapter
import com.kerollosragaie.globalfly.models.Destination
import com.kerollosragaie.globalfly.services.DestinationService
import com.kerollosragaie.globalfly.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destiny_list.*
import kotlinx.android.synthetic.main.error.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationListActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_destiny_list)

		setSupportActionBar(toolbar)
		toolbar.title = title


		fab.setOnClickListener {
			val intent = Intent(this@DestinationListActivity, DestinationCreateActivity::class.java)
			startActivity(intent)
		}
	}

	override fun onResume() {
		super.onResume()

		loadDestinations()
	}

	private fun loadDestinations() {
		//show loading indicator:
		loading_bar2.visibility = View.VISIBLE
        // To be replaced by retrofit code
		//destiny_recycler_view.adapter = DestinationAdapter(SampleData.DESTINATIONS)
		val destinationService : DestinationService =  ServiceBuilder.buildService(DestinationService::class.java)
		val filter = HashMap<String,String>()
		//filter["country"] = "India"
		//filter["count"]="1"

		val requestCall: Call<List<Destination>> = destinationService.getDestinationList(filter)

		requestCall.enqueue(object : Callback<List<Destination>> {
			override fun onResponse(
				call: Call<List<Destination>>,
				response: Response<List<Destination>>
			) {
				loading_bar2.visibility = View.GONE
				if(response.isSuccessful){
					val destinationList:List<Destination> = response.body()!!
					destiny_recycler_view.adapter = DestinationAdapter(destinationList)
				}else{
					showErrorMessage("Failed to retrieve data.")
				}
			}

			override fun onFailure(call: Call<List<Destination>>, t: Throwable) {
				loading_bar2.visibility = View.GONE
				//requestCall.isCanceled if user pressed on button and canceled request by himself
				if(requestCall.isCanceled){
					showErrorMessage("Request canceled by the user.")
				}else{
					showErrorMessage(t.toString())
				}
			}

		})
    }


	private fun showErrorMessage(message:String){
		if(error_box.visibility==View.GONE){
			error_box.visibility=View.VISIBLE
		}else{
			error_box.visibility=View.GONE
		}
		error_box.tv_error_title.text="Error"
		error_box.tv_error_message.text=message

		error_box.btn_error_retry.setOnClickListener {
			error_box.visibility=View.GONE
			loadDestinations()
		}


	}


}
