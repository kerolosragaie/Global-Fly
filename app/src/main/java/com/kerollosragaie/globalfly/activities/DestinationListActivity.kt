package com.kerollosragaie.globalfly.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kerollosragaie.globalfly.R
import com.kerollosragaie.globalfly.helpers.DestinationAdapter
import com.kerollosragaie.globalfly.models.Destination
import com.kerollosragaie.globalfly.services.DestinationService
import com.kerollosragaie.globalfly.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destiny_list.*
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

        // To be replaced by retrofit code
		//destiny_recycler_view.adapter = DestinationAdapter(SampleData.DESTINATIONS)
		val destinationService :DestinationService = ServiceBuilder.buildService(DestinationService::class.java)
		/*val filter = HashMap<String,String>()
		filter["country"] = "India"
		filter["count"]="1"*/

		val requestCall: Call<List<Destination>> = destinationService.getDestinationList()
		requestCall.enqueue(object : Callback<List<Destination>> {
			override fun onResponse(
				call: Call<List<Destination>>,
				response: Response<List<Destination>>
			) {
				if(response.isSuccessful){
					val destinationList:List<Destination> = response.body()!!
					destiny_recycler_view.adapter = DestinationAdapter(destinationList)
				}else{//Application level failure:
					Toast.makeText(this@DestinationListActivity,"Failed to retrieve items",Toast.LENGTH_LONG).show()
				}
			}

			override fun onFailure(call: Call<List<Destination>>, t: Throwable) {
				Toast.makeText(this@DestinationListActivity,t.toString(),Toast.LENGTH_LONG).show()
			}

		})
    }
}
